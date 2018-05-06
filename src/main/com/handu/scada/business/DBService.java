package main.com.handu.scada.business;

import main.com.handu.scada.business.device.DataType;
import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.business.dtu.DtuDBService;
import main.com.handu.scada.business.dtu.DtuState;
import main.com.handu.scada.business.dtu.DtuStateResult;
import main.com.handu.scada.business.message.Msg;
import main.com.handu.scada.business.message.MsgManager;
import main.com.handu.scada.business.utpc.AnalyzeRecord;
import main.com.handu.scada.business.utpc.AnalyzeRecordModel;
import main.com.handu.scada.cache.CacheCmdType;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.db.bean.*;
import main.com.handu.scada.db.bean.common.AdditionProperty;
import main.com.handu.scada.db.bean.common.DeviceCacheResult;
import main.com.handu.scada.db.mapper.DeviceAlarmMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.enums.DeviceGroup;
import main.com.handu.scada.event.Subscriber;
import main.com.handu.scada.event.events.BaseEvent;
import main.com.handu.scada.event.events.DBEvent;
import main.com.handu.scada.event.subscribe.ISubscriber;
import main.com.handu.scada.event.subscribe.SubscribePublish;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.base.RealDataItem;
import main.com.handu.scada.protocol.base.SecondLpRealData;
import main.com.handu.scada.protocol.base.SecondLpRecord;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.LPState;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.DltControlWord;
import main.com.handu.scada.protocol.protocol.DLT645.TripEventRecord;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.thread.MyThreadPoolExecutor;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;
import main.com.handu.scada.utils.UUIDUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static main.com.handu.scada.business.utpc.AnalyzeRecord.*;

/**
 * Created by 柳梦 on 2017/12/22.
 */

@Subscriber
public class DBService extends BaseDBService implements ISubscriber {

    /**
     * 创建自定义线程池
     */
    private ExecutorService executor = MyThreadPoolExecutor.getInstance();

    @Override
    public void subscribe(SubscribePublish subscribePublish) {
        subscribePublish.subscribe(this);
    }

    @Override
    public void unSubscribe(SubscribePublish subscribePublish) {
        subscribePublish.unSubscribe(this);
    }

    @Override
    public void onEvent(String publisher, BaseEvent event) {
        if (event instanceof DBEvent) {
            if (event.data != null) {
                if (event.data instanceof ProtocolLayerData) {
                    executor.execute(new DtuTask((ProtocolLayerData) event.data));
                } else if (event.data instanceof BaseData) {
                    executor.execute(new SwitchTask((BaseData) event.data));
                }
            }
        }
    }

    /**
     * DTU入库的任务
     */
    private class DtuTask implements Runnable {

        private ProtocolLayerData protocolLayerData;

        DtuTask(ProtocolLayerData protocolLayerData) {
            this.protocolLayerData = protocolLayerData;
        }

        @Override
        public void run() {
            if (protocolLayerData != null) {
                try {
                    //打印设备类型和操作类型
                    if (protocolLayerData.deviceTypeEnum != null && protocolLayerData.CmdType != null) {
                        LogUtils.info("deviceType--" + protocolLayerData.deviceTypeEnum.name() + ",cmdType--" + protocolLayerData.CmdType.name());
                    }
                    //如果是跳闸事件
                    if (protocolLayerData.CmdType == DeviceCmdTypeEnum.ProtectorTripEventRecord) {
                        saveDeviceAlarm(protocolLayerData.tripEventRecord);
                    }
                    //如果是二级漏保档案上报
                    else if (protocolLayerData.CmdType == DeviceCmdTypeEnum.SecondLpRecord) {
                        saveSecondLpRecord(new SecondLpRealData() {{
                            setDtuAddress(protocolLayerData.DTUString);
                            setSecondLpRecords(protocolLayerData.secondLpRecords);
                        }});
                    }
                    //上线登录
                    else if (protocolLayerData.CmdType == DeviceCmdTypeEnum.DTU_LOGIN) {
                        online(protocolLayerData);
                    }
                    //下线
                    else if (protocolLayerData.CmdType == DeviceCmdTypeEnum.DTU_OFF_LINE) {
                        offline(protocolLayerData);
                    }
                    //如果是集中器心跳时间,则判断集中器是否掉线
                    else if (protocolLayerData.CmdType == DeviceCmdTypeEnum.ConcentratorHeartbeatTime) {
                        saveConcentratorState(protocolLayerData);
                    }
                    //判断如果是控制字则单独入库
                    else if (protocolLayerData.CmdType == DeviceCmdTypeEnum.ReadControlWordParameterModule) {
                        if (!StringsUtils.isEmpty(protocolLayerData.PostalAddress) && !StringsUtils.isEmpty(protocolLayerData.TabName)) {
                            //找到设备的id
                            DeviceCacheResult result = MyCacheManager.getDeviceCacheResult(protocolLayerData.dtuAddress, protocolLayerData.TabName.toLowerCase(), protocolLayerData.PostalAddress);
                            if (result != null && result.getDeviceId() != null) {
                                saveControlWord(result.getDeviceId());
                            }
                        }
                    }
                    //台区总表数据
                    else if (protocolLayerData.CmdType == DeviceCmdTypeEnum.HM_AFN0C25) {
                        saveHMAFN0C25();
                    }
                    //其他遥测值上报
                    else {
                        saveDeviceTelemetering(new RealDataItem() {{
                            this.address = protocolLayerData.DTUString;
                            this.list = protocolLayerData.attrList;
                            this.dtuAddress = protocolLayerData.DTUString;
                            this.deviceTableName = protocolLayerData.TabName;
                            this.postalAddress = protocolLayerData.PostalAddress;
                        }});
                    }
                } catch (Exception e) {
                    ExceptionHandler.print(e);
                }
            }
        }

        /**
         * 写入台区总表0c25数据
         */
        private void saveHMAFN0C25() {
            if (protocolLayerData.attrList != null && StringsUtils.isNotEmpty(protocolLayerData.DTUString)) {
                List<DataAttr> attrList = protocolLayerData.attrList;
                if (attrList != null) {
                    //三相电流
                    List<DataAttr> attrsI = attrList
                            .stream()
                            .filter(e -> Objects.equals(e.getGroup(), DeviceCmdTypeEnum.Current.name()))
                            .collect(Collectors.toList());
                    //三相电压
                    List<DataAttr> attrsU = attrList
                            .stream()
                            .filter(e -> Objects.equals(e.getGroup(), DeviceCmdTypeEnum.Voltage.name()))
                            .collect(Collectors.toList());
                    //把所有数据入库
                    attrList.stream()
                            .filter(e -> Objects.equals(e.getGroup(), DeviceCmdTypeEnum.HM_AFN0C25.name()))
                            .findFirst()
                            .ifPresent(dataAttr -> {
                                if (dataAttr.getValue() instanceof DeviceHmRealAfn0c25) {

                                    DeviceHmRealAfn0c25 hmRealAfn0c25 = (DeviceHmRealAfn0c25) dataAttr.getValue();
                                    hmRealAfn0c25.setDtuaddress(protocolLayerData.DTUString);
                                    DtuDBService.getInstance().push(new DeviceData(DataType.HM_AFN0C25, hmRealAfn0c25));
                                    //三相不平衡
                                    double val = hmRealAfn0c25.getUtpc();
                                    if (val > AnalyzeRecord.UTPC_RATE) {
                                        attrsI.stream()
                                                .filter(e -> Double.parseDouble(String.valueOf(e.getValue())) > 0)
                                                .max((o1, o2) -> {
                                                    double v1 = Double.parseDouble(String.valueOf(o1.getValue()));
                                                    double v2 = Double.parseDouble(String.valueOf(o2.getValue()));
                                                    return v1 < v2 ? -1 : v1 == v2 ? 0 : 1;
                                                })
                                                .ifPresent(maxDataAttr -> {
                                                    String key = maxDataAttr.getName() + hmRealAfn0c25.getDtuaddress();
                                                    AnalyzeRecordModel recordModel;
                                                    //如果第一次发生了三相不平衡，记录下来
                                                    if (!AnalyzeRecord.containsKey(key)) {
                                                        recordModel = new AnalyzeRecordModel();
                                                        recordModel.setDtuAddress(hmRealAfn0c25.getDtuaddress());
                                                        recordModel.setBeginTime(maxDataAttr.getDtime());
                                                        recordModel.setMaxUtpc(val);
                                                        recordModel.setPhase(maxDataAttr.getName());
                                                        AnalyzeRecord.put(key, recordModel);
                                                    } else {
                                                        //比较最大相三相不平衡值
                                                        recordModel = AnalyzeRecord.get(key);
                                                        recordModel.setMaxUtpc(recordModel.getMaxUtpc() > val ? recordModel.getMaxUtpc() : val);
                                                    }
                                                });

                                    } else {
                                        attrsI.stream()
                                                .filter(e -> Double.parseDouble(String.valueOf(e.getValue())) > 0)
                                                .forEach(attr -> {
                                                    String key = attr.getName() + hmRealAfn0c25.getDtuaddress();
                                                    if (AnalyzeRecord.containsKey(key)) {
                                                        AnalyzeRecordModel recordModel = AnalyzeRecord.get(key);
                                                        recordModel.setEndTime(attr.getDtime());
                                                        int m = (int) DateUtils.getDiffMinutes(recordModel.getBeginTime(), recordModel.getEndTime());
                                                        recordModel.setDuration(m);
                                                        //记录三项不平衡
                                                        DeviceHmUtpc hmUtpc = new DeviceHmUtpc();
                                                        hmUtpc.setDtuaddress(recordModel.getDtuAddress());
                                                        hmUtpc.setMaxutpc(recordModel.getMaxUtpc());
                                                        hmUtpc.setDuration(recordModel.getDuration());
                                                        hmUtpc.setBegintime(recordModel.getBeginTime());
                                                        hmUtpc.setEndtime(recordModel.getEndTime());
                                                        hmUtpc.setPhase(recordModel.getPhase());
                                                        DtuDBService.getInstance().push(new DeviceData(DataType.HM_UTPC, hmUtpc));
                                                        //移除
                                                        AnalyzeRecord.remove(key);
                                                    }
                                                });
                                    }

                                    //低电压
                                    attrsU.forEach(attr -> {
                                        AnalyzeRecordModel model;
                                        double value = Double.parseDouble(String.valueOf(attr.getValue()));
                                        double rate = (NORMAL_VOLTAGE - value) * 1.0f / (NORMAL_VOLTAGE * 1.0f);
                                        String key = attr.getName() + hmRealAfn0c25.getDtuaddress();
                                        if (rate > LOW_VOLTAGE_RATE) {
                                            if (!AnalyzeRecord.containsKey(key)) {
                                                model = new AnalyzeRecordModel();
                                                model.setDtuAddress(hmRealAfn0c25.getDtuaddress());
                                                model.setBeginTime(attr.getDtime());
                                                model.setPhase(attr.getName());
                                                model.setMinU((int) value);
                                                AnalyzeRecord.put(key, model);
                                            } else {
                                                //比较最低电压
                                                model = AnalyzeRecord.get(key);
                                                model.setMinU((int) (model.getMinU() < value ? model.getMinU() : value));
                                            }
                                        } else {
                                            if (AnalyzeRecord.containsKey(key)) {
                                                model = AnalyzeRecord.get(key);
                                                model.setEndTime(attr.getDtime());
                                                int m = (int) DateUtils.getDiffMinutes(model.getBeginTime(), model.getEndTime());
                                                model.setDuration(m);
                                                //记录低电压
                                                DeviceHmLowVoltage lowVoltage = new DeviceHmLowVoltage();
                                                lowVoltage.setDtuaddress(model.getDtuAddress());
                                                lowVoltage.setMinu(model.getMinU());
                                                lowVoltage.setDuration(model.getDuration());
                                                lowVoltage.setBegintime(model.getBeginTime());
                                                lowVoltage.setEndtime(model.getEndTime());
                                                lowVoltage.setPhase(attr.getName());
                                                DtuDBService.getInstance().push(new DeviceData(DataType.HM_LOW_VOLTAGE, lowVoltage));
                                                //移除
                                                AnalyzeRecord.remove(key);
                                            }
                                        }
                                    });


                                    //重过载
                                    String key = AnalyzeRecord.OVERLOAD + hmRealAfn0c25.getDtuaddress();
                                    AnalyzeRecordModel model;
                                    double overload = hmRealAfn0c25.getOverload();
                                    if (overload >= AnalyzeRecord.OVERLOAD60) {
                                        if (!AnalyzeRecord.containsKey(key)) {
                                            model = new AnalyzeRecordModel();
                                            model.setDtuAddress(hmRealAfn0c25.getDtuaddress());
                                            model.setBeginTime(dataAttr.getDtime());
                                            model.setOverload(overload);
                                            AnalyzeRecord.put(key, model);
                                        } else {
                                            model = AnalyzeRecord.get(key);
                                            model.setOverload(overload);
                                        }
                                    } else {
                                        if (AnalyzeRecord.containsKey(key)) {
                                            model = AnalyzeRecord.get(key);
                                            model.setEndTime(dataAttr.getDtime());
                                            int m = (int) DateUtils.getDiffMinutes(model.getBeginTime(), model.getEndTime());
                                            model.setDuration(m);

                                            DeviceHmOverload hmOverload = new DeviceHmOverload();
                                            hmOverload.setDtuaddress(model.getDtuAddress());
                                            hmOverload.setOverload(overload);
                                            hmOverload.setBegintime(model.getBeginTime());
                                            hmOverload.setEndtime(model.getEndTime());
                                            hmOverload.setDuration(model.getDuration());
                                            DtuDBService.getInstance().push(new DeviceData(DataType.HM_OVERLOAD, hmOverload));
                                            AnalyzeRecord.remove(key);
                                        }
                                    }
                                }
                            });
                }
            }
        }

        /**
         * 写入集中器最后一次心跳时间，判断是否掉线
         *
         * @param protocolLayerData
         */
        private void saveConcentratorState(ProtocolLayerData protocolLayerData) {
            if (protocolLayerData.attrList != null) {
                protocolLayerData.attrList
                        .stream()
                        .findFirst()
                        .ifPresent(dataAttr -> {
                            DeviceConcentratorLastHeartbeatTime time = new DeviceConcentratorLastHeartbeatTime();
                            time.setDtuAddress(protocolLayerData.DTUString);
                            time.setLastheartbeattime((Date) dataAttr.getValue());
                            time.setRecordtime(dataAttr.getDtime());
                            DtuDBService.getInstance().push(new DeviceData(DataType.CONCENTRATOR_STATE, time));
                        });
            }
        }

        /**
         * 存入控制字
         *
         * @param deviceId
         */
        private void saveControlWord(String deviceId) {

            if (protocolLayerData != null && protocolLayerData.controlWord != null) {
                DltControlWord c = protocolLayerData.controlWord;
                DeviceControlword w = new DeviceControlword();

                w.setDeviceid(deviceId);

                w.setDelaytimelevel(c.DelayTimeLevel);
                w.setFlagallalarm(c.flagAllAlarm);
                w.setFlagaudioalarm(c.flagAudioAlarm);
                w.setFlaglevelreturn(c.flagLevelReturn);
                w.setFlaglightalarm(c.flagLightAlarm);

                w.setFlagmissearthlinealarm(c.flagMissEarthLineAlarm);
                w.setFlagmissearthlinecontrol(c.flagMissEarthLineControl);
                w.setFlagmissphasealarm(c.flagMissPhaseAlarm);
                w.setFlagmissphasecontrol(c.flagMissPhaseControl);
                w.setFlagovercurrentalarm(c.flagOverCurrentAlarm);

                w.setFlagovercurrentcontrol(c.flagOverCurrentControl);
                w.setFlagovervoltagealarm(c.flagOverVoltageAlarm);
                w.setFlagovervoltagecontrol(c.flagOverVoltageControl);
                w.setFlagreclosing(c.flagReclosing);
                w.setFlagtimelytrial(c.flagTimelyTrial);

                w.setFlagtrialsource(c.flagTrialSource);
                w.setFlagundervoltagealarm(c.flagUnderVoltageAlarm);
                w.setFlagundervoltagecontrol(c.flagUnderVoltageControl);
                w.setResidualalarmtimelevel(c.ResidualAlarmTimeLevel);
                w.setResidualthresholdlevel(c.ResidualThresholdLevel);

                w.setRecordtime(c.recordTime);

                DtuDBService.getInstance().push(new DeviceData(DataType.CONTROL_WORD, w));
            }
        }

        /**
         * 存入遥测表
         *
         * @param data 数据
         */
        private void saveDeviceTelemetering(RealDataItem data) {
            if (!StringsUtils.isEmpty(data.postalAddress) && !StringsUtils.isEmpty(data.deviceTableName)) {
                //找到设备
                DeviceCacheResult deviceCacheResult = MyCacheManager.getDeviceCacheResult(data.dtuAddress, data.deviceTableName.toLowerCase(), data.getPostalAddress());
                if (deviceCacheResult == null) return;
                data.deviceId = deviceCacheResult.getDeviceId();
                if (data.deviceId == null) return;
                List<DataAttr> dataAttrs = data.list;
                if (dataAttrs == null) return;
                LogUtils.info("----data----" + data.toString());
                for (int i = 0; i < dataAttrs.size(); i++) {
                    DataAttr dataAttr = dataAttrs.get(i);
                    //遥信
                    if (dataAttr.getDateType() == RemoteType.YX) {
                        DeviceRealRemotesignalling deviceRealRemotesignalling = new DeviceRealRemotesignalling();
                        deviceRealRemotesignalling.setDeviceid(data.deviceId);
                        deviceRealRemotesignalling.setDataitem(dataAttr.getName());
                        deviceRealRemotesignalling.setValue((Integer) dataAttr.getValue());
                        deviceRealRemotesignalling.setUnit(dataAttr.getUnit());
                        deviceRealRemotesignalling.setRecordtime(dataAttr.getDtime());
                        deviceRealRemotesignalling.setDescription(dataAttr.getCnname());
                        //遥信放入入库队列
                        DtuDBService.getInstance().push(new DeviceData(DataType.YX_REAL, deviceRealRemotesignalling));

                        if (dataAttr.isInsertHistory()) {
                            //3.2插入历史库
                            DeviceHistoryRemotesignalling deviceHistoryRemotesignalling = new DeviceHistoryRemotesignalling();
                            deviceHistoryRemotesignalling.setRemotesignallingid(UUIDUtils.getUUId());
                            deviceHistoryRemotesignalling.setDeviceid(data.deviceId);
                            deviceHistoryRemotesignalling.setDataitem(dataAttr.getName());
                            deviceHistoryRemotesignalling.setValue((Integer) dataAttr.getValue());
                            deviceHistoryRemotesignalling.setUnit(dataAttr.getUnit());
                            deviceHistoryRemotesignalling.setRecordtime(dataAttr.getDtime());
                            deviceHistoryRemotesignalling.setDescription(dataAttr.getCnname());
                            DtuDBService.getInstance().push(new DeviceData(DataType.YX_HISTORY, deviceHistoryRemotesignalling));
                        }

                        //如果是分合状态
                        if (Objects.equals(dataAttr.getGroup(), DeviceCmdTypeEnum.RunState.name())) {
                            saveDeviceAlarm(protocolLayerData.tripEventRecord);
                        }

                        //如果是跌落装置分合状态
                        if (Objects.equals(deviceCacheResult.getDeviceTableName(), DeviceTableEnum.Device_Falling_Type_Switch.getTableName())) {
                            Map<String, AdditionProperty> additionProperties = deviceCacheResult.getAdditionProperties();
                            if (additionProperties != null) {
                                AdditionProperty property = additionProperties.get(dataAttr.getName());
                                if (property != null) {
                                    float value = Float.parseFloat(String.valueOf(dataAttr.getValue()));
                                    if (value != property.getValue()) {
                                        //是否发送短信
                                        deviceCacheResult.setAlarmTime(dataAttr.getDtime());
                                        String content = property.getName() + "相" + (property.getValue() == 0 ? "合" : "分");
                                        property.setMsgContent(content);
                                        property.setValue(value);
                                        deviceCacheResult.setSendMsg(true);
                                    } else {
                                        String content = property.getName() + "相" + (property.getValue() == 0 ? "合" : "分");
                                        property.setMsgContent(content);
                                    }
                                }
                            }
                        }
                    }
                    //遥测
                    else if (dataAttr.getDateType() == RemoteType.YC) {
                        DeviceRealRemotetelemetry deviceRealRemotetelemetry = new DeviceRealRemotetelemetry();
                        deviceRealRemotetelemetry.setDeviceid(data.deviceId);
                        deviceRealRemotetelemetry.setDataitem(dataAttr.getName());
                        deviceRealRemotetelemetry.setValue(String.valueOf(dataAttr.getValue()));
                        deviceRealRemotetelemetry.setUnit(dataAttr.getUnit());
                        deviceRealRemotetelemetry.setRecordtime(dataAttr.getDtime());
                        deviceRealRemotetelemetry.setDescription(dataAttr.getCnname());
                        //遥测入库队列
                        DtuDBService.getInstance().push(new DeviceData(DataType.YC_REAL, deviceRealRemotetelemetry));

                        if (dataAttr.isInsertHistory()) {
                            //3.4 插入历史库
                            DeviceHistoryRemotetelemetry historyRemotetelemetry = new DeviceHistoryRemotetelemetry();
                            historyRemotetelemetry.setRemotetelemetryid(UUIDUtils.getUUId());
                            historyRemotetelemetry.setDeviceid(data.deviceId);
                            historyRemotetelemetry.setDataitem(dataAttr.getName());
                            historyRemotetelemetry.setValue(String.valueOf(dataAttr.getValue()));
                            historyRemotetelemetry.setUnit(dataAttr.getUnit());
                            historyRemotetelemetry.setRecordtime(dataAttr.getDtime());
                            historyRemotetelemetry.setDescription(dataAttr.getCnname());
                            DtuDBService.getInstance().push(new DeviceData(DataType.YC_HISTORY, historyRemotetelemetry));
                        }

                        //3.5 三项不平衡
                        //3.5.1 判断是否是三项不平衡
                        if (dataAttr.getName().equals(AnalyzeRecord.UTPC)) {
                            double val = Double.parseDouble(String.valueOf(dataAttr.getValue()));
                            if (val > UTPC_RATE) {
                                dataAttrs
                                        .stream()
                                        .filter(dataAttr1 -> Objects.equals(dataAttr1.getGroup(), DeviceCmdTypeEnum.Current.name()))
                                        .max((o1, o2) -> {
                                            double v1 = Double.parseDouble(String.valueOf(o1.getValue()));
                                            double v2 = Double.parseDouble(String.valueOf(o2.getValue()));
                                            return v1 < v2 ? -1 : v1 == v2 ? 0 : 1;
                                        })
                                        .ifPresent(maxDataAttr -> {
                                            String key = maxDataAttr.getName() + data.getDeviceId();
                                            AnalyzeRecordModel recordModel;
                                            //如果第一次发生了三相不平衡，记录下来
                                            if (!AnalyzeRecord.containsKey(key)) {
                                                recordModel = new AnalyzeRecordModel();
                                                recordModel.setDeviceId(data.getDeviceId());
                                                recordModel.setBeginTime(dataAttr.getDtime());
                                                recordModel.setMaxUtpc(val);
                                                recordModel.setPhase(maxDataAttr.getName());
                                                AnalyzeRecord.put(key, recordModel);
                                            } else {
                                                //比较最大相三相不平衡值
                                                recordModel = AnalyzeRecord.get(key);
                                                recordModel.setMaxUtpc(recordModel.getMaxUtpc() > val ? recordModel.getMaxUtpc() : val);
                                            }
                                        });
                            }
                            //如果恢复三相平衡，从utpcMap中移除，并计算持续时间
                            else {
                                dataAttrs
                                        .stream()
                                        .filter(attr -> Objects.equals(attr.getGroup(), DeviceCmdTypeEnum.Current.name()))
                                        .forEach(dataAttr1 -> {
                                            String key = dataAttr1.getName() + data.getDeviceId();
                                            if (AnalyzeRecord.containsKey(key)) {
                                                AnalyzeRecordModel recordModel = AnalyzeRecord.get(key);
                                                recordModel.setEndTime(dataAttr1.getDtime());
                                                int m = (int) DateUtils.getDiffMinutes(recordModel.getBeginTime(), recordModel.getEndTime());
                                                recordModel.setDuration(m);
                                                //记录三项不平衡
                                                DeviceRcdutpc rcd = new DeviceRcdutpc();
                                                rcd.setUtpcid(UUIDUtils.getUUId());
                                                rcd.setMaxutpc(recordModel.getMaxUtpc());
                                                rcd.setDeviceid(recordModel.getDeviceId());
                                                rcd.setDuration(recordModel.getDuration());
                                                rcd.setBegintime(recordModel.getBeginTime());
                                                rcd.setEndtime(recordModel.getEndTime());
                                                rcd.setPhase(recordModel.getPhase());
                                                DtuDBService.getInstance().push(new DeviceData(DataType.LP_UTPC, rcd));
                                                //移除
                                                AnalyzeRecord.remove(key);
                                            }
                                        });
                            }
                        }

                        //3.6 低电压
                        if (Objects.equals(dataAttr.getGroup(), DeviceCmdTypeEnum.Voltage.name())) {
                            double value = Double.parseDouble(String.valueOf(dataAttr.getValue()));
                            double rate = (NORMAL_VOLTAGE - value) * 1.0f / (NORMAL_VOLTAGE * 1.0f);
                            String key = dataAttr.getName() + data.getDeviceId();
                            AnalyzeRecordModel recordModel;
                            if (rate > LOW_VOLTAGE_RATE) {
                                if (!AnalyzeRecord.containsKey(key)) {
                                    recordModel = new AnalyzeRecordModel();
                                    recordModel.setDeviceId(data.getDeviceId());
                                    recordModel.setBeginTime(dataAttr.getDtime());
                                    recordModel.setPhase(dataAttr.getName());
                                    recordModel.setMinU((int) value);
                                    AnalyzeRecord.put(key, recordModel);
                                } else {
                                    //比较最低电压
                                    recordModel = AnalyzeRecord.get(key);
                                    recordModel.setMinU((int) (recordModel.getMinU() < value ? recordModel.getMinU() : value));
                                }
                            } else {
                                if (AnalyzeRecord.containsKey(key)) {
                                    recordModel = AnalyzeRecord.get(key);
                                    recordModel.setEndTime(dataAttr.getDtime());
                                    int m = (int) DateUtils.getDiffMinutes(recordModel.getBeginTime(), recordModel.getEndTime());
                                    recordModel.setDuration(m);
                                    //记录低电压
                                    DeviceLowvoltage deviceLowvoltage = new DeviceLowvoltage();
                                    deviceLowvoltage.setLowuid(UUIDUtils.getUUId());
                                    deviceLowvoltage.setMinu(recordModel.getMinU());
                                    deviceLowvoltage.setDeviceid(recordModel.getDeviceId());
                                    deviceLowvoltage.setDuration(recordModel.getDuration());
                                    deviceLowvoltage.setBegintime(recordModel.getBeginTime());
                                    deviceLowvoltage.setEndtime(recordModel.getEndTime());
                                    deviceLowvoltage.setPhase(dataAttr.getName());
                                    DtuDBService.getInstance().push(new DeviceData(DataType.LP_LOW_VOLTAGE, deviceLowvoltage));
                                    //移除
                                    AnalyzeRecord.remove(key);
                                }
                            }
                        }
                        //3.7 读设备型号
                        //if (dataAttr.getName().equals(DeviceCmdTypeEnum.ReadDeviceModel.name())) {
                        //    DeviceRcdMapper deviceRcdMapper = sqlSession.getMapper(DeviceRcdMapper.class);
                        //    DeviceRcd rcd = deviceRcdMapper.selectByPrimaryKey(data.getDeviceId());
                        //    if (rcd != null) {
                        //        rcd.setRcdmodel((String) dataAttr.getColumn());
                        //        //deviceRcdMapper.updateByPrimaryKey(rcd);
                        //    }
                        //}

                        //如果是测温
                        if (Objects.equals(deviceCacheResult.getDeviceTableName(), DeviceTableEnum.Device_Temperature.getTableName())) {
                            Map<String, AdditionProperty> additionProperties = deviceCacheResult.getAdditionProperties();
                            if (additionProperties != null) {
                                //上限值
                                AdditionProperty limitProperty = additionProperties.get(dataAttr.getName());
                                //上一次值
                                AdditionProperty lastProperty = additionProperties.get("last" + dataAttr.getName());
                                if (limitProperty != null) {
                                    //本次值
                                    float value = Float.parseFloat(String.valueOf(dataAttr.getValue()));
                                    if (lastProperty != null) {
                                        float lastValue = lastProperty.getValue();
                                        //本次值如果与上一次值不相等
                                        if (value != lastValue) {
                                            //如果本次值大于上限值发送短信
                                            if (value >= limitProperty.getValue()) {
                                                deviceCacheResult.setAlarmTime(dataAttr.getDtime());
                                                String content = limitProperty.getDescription() + "探头(" + (value + limitProperty.getUnit()) + ")" + (value >= limitProperty.getValue() ? "预警" : "正常");
                                                limitProperty.setMsgContent(content);
                                                lastProperty.setValue(value);
                                                deviceCacheResult.setSendMsg(true);
                                            }
                                        } else {
                                            String content = limitProperty.getDescription() + "探头(" + (value + limitProperty.getUnit()) + ")" + (value >= limitProperty.getValue() ? "预警" : "正常");
                                            limitProperty.setMsgContent(content);
                                        }
                                    } else {
                                        lastProperty = new AdditionProperty();
                                        lastProperty.setName("last" + dataAttr.getName());
                                        lastProperty.setValue(value);
                                        additionProperties.put(lastProperty.getName(), lastProperty);
                                    }
                                }
                            }
                        }
                    }
                }
                //是否发短信
                if (deviceCacheResult.isSendMsg()) {
                    Map<String, AdditionProperty> map = deviceCacheResult.getAdditionProperties();
                    if (map != null) {
                        StringBuilder sb = new StringBuilder();
                        map.entrySet()
                                .stream()
                                .filter(e -> StringsUtils.isNotEmpty(e.getValue().getMsgContent()))
                                .map(e -> e.getValue().getMsgContent())
                                .forEach(e -> sb.append(e).append(","));
                        String name = deviceCacheResult.getDaName() + deviceCacheResult.getDeviceName();
                        String systemName = Config.getSystemName();
                        String alarmTime = DateUtils.dateToStr(deviceCacheResult.getAlarmTime());
                        String msgContent = String.format("%s,于时间%s,%s%s", name, alarmTime, sb.toString(), "[" + systemName + "]");
                        MsgManager.getInstance().putMsg(new Msg(deviceCacheResult.getDeviceId(), -1, msgContent));
                        deviceCacheResult.setSendMsg(false);
                    }
                }
            }
        }

        /**
         * 获取跳闸记录并记录入库
         *
         * @param record
         * @return
         */
        private void saveDeviceAlarm(TripEventRecord record) {
            if (record != null) {
                int value = record.deviceGroup == DeviceGroup.LP1997 ? record.tripReason1997.getValue() : record.tripReason2007.getValue();
                LogUtils.info("TripEventRecord:" + record.toString1());
                SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
                DeviceAlarmMapper deviceAlarmMapper = sqlSession.getMapper(DeviceAlarmMapper.class);
                //分闸报文
                if (record.State == LPState.OFF) {
                    DeviceCacheResult result = MyCacheManager.getDeviceCacheResult(protocolLayerData.DTUString, DeviceTableEnum.Device_Rcd.getTableName().toLowerCase(), protocolLayerData.PostalAddress);
                    if (result != null) {
                        String deviceId = result.getDeviceId();
                        String tableName = DeviceTableEnum.Device_Rcd.getTableName().toLowerCase();
                        Date alarmTime = DateUtils.strToDate(DateUtils.dateToStr(record.AlarmTime));
                        DeviceAlarmExample deviceAlarmExample = new DeviceAlarmExample();
                        deviceAlarmExample
                                .createCriteria()
                                .andDeviceidEqualTo(deviceId)
                                .andDevicetablenameEqualTo(tableName)
                                .andAlarmtimeEqualTo(alarmTime);
                        Optional<DeviceAlarm> optional = deviceAlarmMapper
                                .selectByExample(deviceAlarmExample)
                                .stream()
                                .findFirst();
                        if (!optional.isPresent()) {
                            DeviceAlarm item = new DeviceAlarm();
                            item.setAlarmid(UUIDUtils.getUUId());
                            item.setDeviceid(deviceId);
                            item.setState(LPState.OFF.getValue());
                            item.setUa((double) record.APhaseVoltage);
                            item.setUb((double) record.BPhaseVoltage);
                            item.setUc((double) record.CPhaseVoltage);
                            item.setIa((double) record.APhaseCurrent);
                            item.setIb((double) record.BPhaseCurrent);
                            item.setIc((double) record.CPhaseCurrent);
                            item.setIo((double) record.ResidualCurrent);
                            item.setAlarmphase(record.AlarmPhase);
                            item.setAlarmcontent(record.AlarmReason);
                            item.setAlarmtime(alarmTime);
                            item.setAlarmtype(value);
                            item.setCreatedate(DateUtils.getNowSqlDateTime());
                            item.setDevicetablename(tableName);
                            item.setOutagetime(alarmTime);
                            item.setIsdeal(0);
                            item.setAlarmlevel(1);
                            item.setIssendworkorder(0);
                            item.setSortcode(0);
                            DtuDBService.getInstance().push(new DeviceData(DataType.ALARM, item));

                            //判断是否需要发短信
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(record.getAlarmTime());
                            cal.add(Calendar.HOUR, 1);
                            String alarmDate = DateUtils.getDateStr(record.AlarmTime);
                            String now = DateUtils.getDateStr(new Date());
                            String temp = DateUtils.getDateStr(cal.getTime());
                            //只报当天的告警，发送分闸短信
                            if (Objects.equals(alarmDate, now) || Objects.equals(temp, now)) {
                                String name = result.getDeviceName();
                                String systemName = Config.getSystemName();
                                String content = record.toString();
                                String alarmTime1 = DateUtils.dateToStr(record.AlarmTime);
                                String msgContent = String.format("%s,于时间%s,%s,%s", name, alarmTime1, content, "[" + systemName + "]");
                                MsgManager.getInstance().putMsg(new Msg(deviceId, item.getAlarmtype(), msgContent));
                            }
                        }
                    }
                }
                //合闸报文
                else if (record.getState() == LPState.ON) {
                    DeviceCacheResult result = MyCacheManager.getDeviceCacheResult(protocolLayerData.DTUString, DeviceTableEnum.Device_Rcd.getTableName().toLowerCase(), protocolLayerData.PostalAddress);
                    if (result != null) {
                        DeviceAlarmExample deviceAlarmExample = new DeviceAlarmExample();
                        deviceAlarmExample
                                .createCriteria()
                                .andDeviceidEqualTo(result.getDeviceId())
                                .andDevicetablenameEqualTo(DeviceTableEnum.Device_Rcd.getTableName().toLowerCase())
                                .andStateEqualTo(LPState.OFF.getValue());

                        deviceAlarmMapper
                                .selectByExample(deviceAlarmExample)
                                .stream()
                                .max((o1, o2) -> o1.getAlarmtime().getTime() < o2.getAlarmtime().getTime() ? -1 : o1.getAlarmtime().getTime() == o1.getAlarmtime().getTime() ? 0 : 1)
                                .ifPresent(deviceAlarm -> {

                                    deviceAlarm.setPowerontime(DateUtils.getNowSqlDateTime());
                                    deviceAlarm.setDurationtime(DateUtils.getDiffMinutes(deviceAlarm.getOutagetime(), deviceAlarm.getPowerontime()));
                                    deviceAlarm.setModifydate(DateUtils.getNowSqlDateTime());
                                    deviceAlarm.setState(LPState.ON.getValue());
                                    DtuDBService.getInstance().push(new DeviceData(DataType.ALARM, deviceAlarm));

                                    //发送合闸短信
                                    String name = result.getDeviceName();
                                    String content = "合闸成功";
                                    String systemName = Config.getSystemName();
                                    String alarmTime = DateUtils.dateToStr(DateUtils.getNowSqlDateTime());
                                    String deviceId = result.getDeviceId();
                                    String msgContent = String.format("%s,于时间%s,%s,%s", name, alarmTime, content, "[" + systemName + "]");
                                    MsgManager.getInstance().putMsg(new Msg(deviceId, deviceAlarm.getAlarmtype(), msgContent));
                                });
                    }
                }
            }
        }

        /**
         * 二级漏保档案入库
         *
         * @param data
         */
        private void saveSecondLpRecord(SecondLpRealData data) {
            List<SecondLpRecord> records = data.getSecondLpRecords();
            if (records != null && records.size() > 0) {
                LogUtils.info(records.toString());
                ConcurrentHashMap<String, DeviceCacheResult> result = MyCacheManager.getDeviceCacheMap();
                List<DeviceCacheResult> results = null;
                if (result != null) {
                    synchronized (result) {
                        results = result
                                .entrySet()
                                .stream()
                                .map(Map.Entry::getValue)
                                .collect(Collectors.toList());
                    }
                }
                if (results != null) {
                    //通过dtu地址找到相关联的一级漏保并且获取所需要的信息
                    Optional<DeviceCacheResult> optional = results.stream()
                            .filter(entry -> entry.getDtuAddress().equals(data.getDtuAddress())
                                    && entry.getDeviceTableName().equals(DeviceTableEnum.Device_Rcd.getTableName().toLowerCase())
                                    && entry.getDeviceLevel() == 1)
                            .findFirst();
                    if (optional.isPresent()) {

                        DeviceCacheResult result1 = optional.get();
                        data.setDtuId(result1.getDtuId());
                        data.setDeviceId(result1.getDeviceId());
                        data.setDaId(result1.getDaId());
                        data.setDaName(result1.getDaName());
                        data.setTerminalId(result1.getTerminalId());

                        List<DeviceCacheResult> cacheResults = new ArrayList<>();
                        for (SecondLpRecord record : records) {
                            //先查询缓存是否已经添加过,再添加或修改漏保信息
                            results
                                    .stream()
                                    .filter(e -> e.getDeviceAddress().equals(record.getLpAddress()))
                                    .findFirst()
                                    .ifPresent(cacheResult -> data.setDeviceRcd(new DeviceRcd() {{
                                        setOid(cacheResult.getDeviceId());
                                    }}));
                            //如果不存在就添加
                            DeviceRcd rcd = data.getDeviceRcd();
                            if (rcd == null) {
                                rcd = new DeviceRcd();
                                String deviceId = UUIDUtils.getUUId();
                                rcd.setOid(deviceId);

                                //添加缓存
                                DeviceCacheResult cacheResult = new DeviceCacheResult();
                                cacheResult.setDeviceId(deviceId);
                                cacheResult.setDtuId(data.getDtuId());
                                cacheResult.setDaId(data.getDaId());
                                cacheResult.setDeviceAddress(record.getLpAddress());
                                cacheResult.setDtuAddress(data.getDtuAddress());
                                cacheResult.setDeviceTableName(DeviceTableEnum.Device_Rcd.getTableName());
                                cacheResult.setDeviceLevel("2");
                                cacheResult.setCmdType(CacheCmdType.CREATE);
                                cacheResults.add(cacheResult);
                            }
                            rcd.setAddress(record.getLpAddress());
                            rcd.setBaudrate(String.valueOf(record.getBaudRate()));
                            rcd.setCreatedate(DateUtils.getNowSqlDateTime());
                            rcd.setTerminalid(data.getTerminalId());
                            rcd.setName(data.getDaName() != null ? data.getDaName() + "二级漏保" + record.getLpAddress() : "二级漏保" + record.getLpAddress());
                            rcd.setCheckdigit("8e1");
                            rcd.setBaudrate("2400");
                            rcd.setLevel("2");
                            rcd.setDaid(data.getDaId());
                            rcd.setUa(record.getRatedUa());
                            rcd.setUb(record.getRatedUb());
                            rcd.setUc(record.getRatedUc());
                            rcd.setIa(record.getRatedIa());
                            rcd.setIb(record.getRatedIb());
                            rcd.setIc(record.getRatedIc());
                            rcd.setIo(record.getRatedIo());
                            rcd.setDtuid(data.getDtuId());
                            DtuDBService.getInstance().push(new DeviceData(DataType.SECOND_LP_RECORD_CREATE, rcd));
                        }
                        if (cacheResults.size() > 0) {
                            //添加进缓存
                            MyCacheManager.updateDeviceCacheResult(cacheResults);
                        }
                    }
                }
            }
        }

        /**
         * dtu上线
         */
        private void online(ProtocolLayerData protocolLayerData) {
            DtuDBService.getInstance().push(new DtuStateResult(DtuState.ONLINE, protocolLayerData.dtuAddress, DateUtils.getNowSqlDateTime()));
        }

        /**
         * dtu下线
         */
        private void offline(ProtocolLayerData protocolLayerData) {
            DtuDBService.getInstance().push(new DtuStateResult(DtuState.OFFLINE, protocolLayerData.dtuAddress, DateUtils.getNowSqlDateTime()));
        }
    }

    /**
     * 开关入库任务
     */
    private class SwitchTask implements Runnable {

        private BaseData baseData;

        public SwitchTask(BaseData data) {
            this.baseData = data;
        }

        @Override
        public void run() {
        }
    }
}
