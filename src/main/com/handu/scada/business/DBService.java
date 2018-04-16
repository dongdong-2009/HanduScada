package main.com.handu.scada.business;

import main.com.handu.scada.business.device.DataType;
import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.business.dtu.DtuDBService;
import main.com.handu.scada.business.dtu.DtuState;
import main.com.handu.scada.business.dtu.DtuStateResult;
import main.com.handu.scada.business.message.Msg;
import main.com.handu.scada.business.message.MsgManager;
import main.com.handu.scada.business.utpc.UTPCModel;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.db.bean.*;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import main.com.handu.scada.db.mapper.*;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.enums.TableEnum;
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
import main.com.handu.scada.protocol.enums.TerminalEnum;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.DltControlWord;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.TripEventRecord;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.thread.MyThreadPoolExecutor;
import main.com.handu.scada.utils.*;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import static main.com.handu.scada.business.utpc.UTPCModel.UTPC_RATE;

/**
 * Created by 柳梦 on 2017/12/22.
 */

@Subscriber
public class DBService implements ISubscriber {
    /**
     * 记录三项不平衡和低电压
     */
    private static ConcurrentHashMap<String, UTPCModel> utpcMap = new ConcurrentHashMap<>();

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

        private SqlSession sqlSession;
        private ProtocolLayerData protocolLayerData;
        private List<DeviceRemoteindexs> remoteindexses = new ArrayList<>();
        private List<DeviceRealRemotetelemetry> realRemotetelemetries = new ArrayList<>();
        private List<DeviceRealRemotesignalling> realRemotesignallings = new ArrayList<>();
        private List<DeviceHistoryRemotetelemetry> historyRemotetelemetries = new ArrayList<>();
        private List<DeviceHistoryRemotesignalling> historyRemotesignallings = new ArrayList<>();
        private List<DeviceDtuCacheResult> cacheResults = new ArrayList<>();

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
                        cacheResults.clear();
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
                            DeviceDtuCacheResult result = MyCacheManager.getInstance().getDeviceDtuCacheResult(protocolLayerData.dtuAddress, protocolLayerData.TabName.toLowerCase(), protocolLayerData.PostalAddress);
                            if (result != null && result.getDeviceId() != null) {
                                saveControlWord(result.getDeviceId());
                            }
                        }
                    }
                    //其他遥测值上报
                    else {
                        clearAll();
                        saveDeviceTelemetering(new RealDataItem() {{
                            this.address = protocolLayerData.DTUString;
                            this.list = protocolLayerData.attrList;
                            this.dtuAddress = protocolLayerData.DTUString;
                            this.deviceTableName = protocolLayerData.TabName;
                            this.postalAddress = protocolLayerData.PostalAddress;
                        }});
                    }
                    if (sqlSession != null) sqlSession.commit(true);
                } catch (Exception e) {
                    if (sqlSession != null) sqlSession.rollback(true);
                    clearAll();
                    ExceptionHandler.print(e);
                } finally {
                    if (sqlSession != null) {
                        sqlSession.close();
                        //如果没有报错则丢进入库队列
                        insertIntoQueue();
                    }
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
         * 清空所有列表
         */
        private void clearAll() {
            remoteindexses.clear();
            realRemotesignallings.clear();
            realRemotetelemetries.clear();
            historyRemotesignallings.clear();
            historyRemotetelemetries.clear();
        }

        /**
         * 丢进入库队列
         */
        private void insertIntoQueue() {
            if (remoteindexses.size() != 0) {
                for (DeviceRemoteindexs remoteindexs : remoteindexses) {
                    MyCacheManager.getInstance().putDeviceRemoteindexes(remoteindexs.getDeviceid(), remoteindexs.getDevicetablename().toLowerCase(), remoteindexs.getDataitem(), remoteindexs);
                }
            }
            if (realRemotesignallings.size() != 0) {
                for (DeviceRealRemotesignalling remotesignalling : realRemotesignallings) {
                    MyCacheManager.getInstance().putDeviceRealRemotesignalling(remotesignalling.getRemoteindexsid(), remotesignalling);
                    DtuDBService.getInstance().push(new DeviceData(DataType.YX_REAL, remotesignalling));
                }
            }
            if (realRemotetelemetries.size() != 0) {
                for (DeviceRealRemotetelemetry remotetelemetry : realRemotetelemetries) {
                    MyCacheManager.getInstance().putDeviceRealRemotetelemetry(remotetelemetry.getRemoteindexsid(), remotetelemetry);
                    DtuDBService.getInstance().push(new DeviceData(DataType.YC_REAL, remotetelemetry));
                }
            }
            if (historyRemotesignallings.size() != 0) {
                for (DeviceHistoryRemotesignalling historyRemotesignalling : historyRemotesignallings) {
                    DtuDBService.getInstance().push(new DeviceData(DataType.YX_HISTORY, historyRemotesignalling));
                }
            }
            if (historyRemotetelemetries.size() != 0) {
                for (DeviceHistoryRemotetelemetry historyRemotetelemetry : historyRemotetelemetries) {
                    DtuDBService.getInstance().push(new DeviceData(DataType.YC_HISTORY, historyRemotetelemetry));
                }
            }
            //二级漏保最后更新缓存
            if (cacheResults.size() != 0) {
                MyCacheManager.getInstance().putDeviceDtuCacheResult(cacheResults);
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
            //如果是分合状态
            if (protocolLayerData.CmdType == DeviceCmdTypeEnum.RunState) {
                saveDeviceAlarm(protocolLayerData.tripEventRecord);
            }
            LogUtils.error("----1--data----" + data.toString());
            if (!StringsUtils.isEmpty(data.postalAddress) && !StringsUtils.isEmpty(data.deviceTableName)) {
                LogUtils.error("----2--postalAddress----" + data.postalAddress + "---deviceTableName---" + data.deviceTableName);
                //找到设备的id
                DeviceDtuCacheResult result = MyCacheManager.getInstance().getDeviceDtuCacheResult(data.dtuAddress, data.deviceTableName.toLowerCase(), data.getPostalAddress());
                if (result != null) data.deviceId = result.getDeviceId();
            }
            if (!StringsUtils.isEmpty(data.deviceId)) {
                LogUtils.error("----3--deviceId----" + data.deviceId);
                List<DataAttr> dataAttrs = data.list;
                if (dataAttrs == null) return;
                if (sqlSession == null) sqlSession = MyBatisUtil.getSqlSession(false);
                DeviceRemoteindexsMapper deviceRemoteindexsMapper = sqlSession.getMapper(DeviceRemoteindexsMapper.class);
                for (int i = 0; i < dataAttrs.size(); i++) {
                    DataAttr dataAttr = dataAttrs.get(i);
                    //判断索引缓存中有没有当前数据项，有则直接拿来用，没有则同时更新索引缓存和数据库缓存
                    DeviceRemoteindexs deviceRemoteindexsCache = MyCacheManager.getInstance().getDeviceRemoteindexes(data.deviceId, data.deviceTableName.toLowerCase(), dataAttr.getName());
                    DeviceRemoteindexs deviceRemoteindexs = new DeviceRemoteindexs();
                    if (deviceRemoteindexsCache != null) {
                        deviceRemoteindexs.setRemoteindexsid(deviceRemoteindexsCache.getRemoteindexsid());
                    } else {
                        deviceRemoteindexs.setDeviceid(data.deviceId);
                        deviceRemoteindexs.setDevicetablename(data.deviceTableName);
                        deviceRemoteindexs.setUnit(dataAttr.getUnit());
                        deviceRemoteindexs.setDataitem(dataAttr.getName());
                        deviceRemoteindexs.setDataitemname(dataAttr.getCnname());
                        deviceRemoteindexs.setGroupname(dataAttr.getGroup());
                        deviceRemoteindexs.setRemoteindexsid(UUIDUtils.getUUId());
                        int result = deviceRemoteindexsMapper.insert(deviceRemoteindexs);
                        if (result > 0) remoteindexses.add(deviceRemoteindexs);
                    }

                    //遥信
                    if (dataAttr.getDateType() == RemoteType.YX) {
                        DeviceRealRemotesignalling deviceRealRemotesignalling = new DeviceRealRemotesignalling();
                        DeviceRealRemotesignalling deviceRealRemotesignallingCache = MyCacheManager.getInstance().getDeviceRealRemotesignalling(deviceRemoteindexs.getRemoteindexsid());
                        //3.1 遥信存入实时库和数据更改记录库
                        if (deviceRealRemotesignallingCache != null) {
                            //实时表
                            deviceRealRemotesignalling.setRemoteindexsid(deviceRealRemotesignallingCache.getRemoteindexsid());
                            deviceRealRemotesignalling.setRemotesignallingid(deviceRealRemotesignallingCache.getRemotesignallingid());
                            deviceRealRemotesignalling.setRecordtime(dataAttr.getDtime());
                            deviceRealRemotesignalling.setValue((Integer) dataAttr.getValue());
                            deviceRealRemotesignalling.setDescription(deviceRealRemotesignallingCache.getDescription());
                        } else {
                            //实时表
                            deviceRealRemotesignalling.setRecordtime(dataAttr.getDtime());
                            deviceRealRemotesignalling.setValue((Integer) dataAttr.getValue());
                            deviceRealRemotesignalling.setRemoteindexsid(deviceRemoteindexs.getRemoteindexsid());
                            deviceRealRemotesignalling.setRemotesignallingid(UUIDUtils.getUUId());
                        }
                        realRemotesignallings.add(deviceRealRemotesignalling);

                        if (dataAttr.isInsertHistory()) {
                            //3.2插入历史库
                            DeviceHistoryRemotesignalling deviceHistoryRemotesignalling = new DeviceHistoryRemotesignalling();
                            deviceHistoryRemotesignalling.setRecordtime(dataAttr.getDtime());
                            deviceHistoryRemotesignalling.setValue((Integer) dataAttr.getValue());
                            deviceHistoryRemotesignalling.setRemoteindexsid(deviceRemoteindexs.getRemoteindexsid());
                            deviceHistoryRemotesignalling.setRemotesignallingid(UUIDUtils.getUUId());
                            historyRemotesignallings.add(deviceHistoryRemotesignalling);
                        }
                    }
                    //遥测
                    else if (dataAttr.getDateType() == RemoteType.YC) {
                        DeviceRealRemotetelemetry deviceRealRemotetelemetry = new DeviceRealRemotetelemetry();
                        //3.3 遥测存入实时库和数据更改记录库
                        DeviceRealRemotetelemetry deviceRealRemotetelemetryCache = MyCacheManager.getInstance().getDeviceRealRemotetelemetry(deviceRemoteindexs.getRemoteindexsid());
                        if (deviceRealRemotetelemetryCache != null) {
                            deviceRealRemotetelemetry.setRemoteindexsid(deviceRealRemotetelemetryCache.getRemoteindexsid());
                            deviceRealRemotetelemetry.setRemotetelemetryid(deviceRealRemotetelemetryCache.getRemotetelemetryid());
                            deviceRealRemotetelemetry.setRecordtime(dataAttr.getDtime());
                            deviceRealRemotetelemetry.setValue(String.valueOf(dataAttr.getValue()));
                            deviceRealRemotetelemetry.setDescription(deviceRealRemotetelemetryCache.getDescription());
                        } else {
                            //实时表
                            deviceRealRemotetelemetry = new DeviceRealRemotetelemetry();
                            deviceRealRemotetelemetry.setRecordtime(dataAttr.getDtime());
                            deviceRealRemotetelemetry.setValue(String.valueOf(dataAttr.getValue()));
                            deviceRealRemotetelemetry.setRemoteindexsid(deviceRemoteindexs.getRemoteindexsid());
                            deviceRealRemotetelemetry.setRemotetelemetryid(UUIDUtils.getUUId());
                        }
                        realRemotetelemetries.add(deviceRealRemotetelemetry);
                        if (dataAttr.isInsertHistory()) {
                            //3.4 插入历史库
                            DeviceHistoryRemotetelemetry historyRemotetelemetry = new DeviceHistoryRemotetelemetry();
                            historyRemotetelemetry.setRecordtime(dataAttr.getDtime());
                            historyRemotetelemetry.setValue(String.valueOf(dataAttr.getValue()));
                            historyRemotetelemetry.setRemoteindexsid(deviceRemoteindexs.getRemoteindexsid());
                            historyRemotetelemetry.setRemotetelemetryid(UUIDUtils.getUUId());
                            historyRemotetelemetries.add(historyRemotetelemetry);
                        }

                        //3.5 三项不平衡
                        //3.5.1 判断是否是三项不平衡
                        if (dataAttr.getName().equals("UTPC")) {
                            double val = Double.parseDouble(String.valueOf(dataAttr.getValue()));
                            if (val > UTPC_RATE) {
                                dataAttrs
                                        .stream()
                                        .filter(dataAttr1 -> !Objects.equals(dataAttr1.getName(), "UTPC"))
                                        .max((o1, o2) -> {
                                            double v1 = Double.parseDouble(String.valueOf(o1.getValue()));
                                            double v2 = Double.parseDouble(String.valueOf(o2.getValue()));
                                            return v1 < v2 ? -1 : v1 == v2 ? 0 : 1;
                                        })
                                        .ifPresent(maxDataAttr -> {
                                            String key = maxDataAttr.getName() + data.getDeviceId();
                                            UTPCModel utpcModel;
                                            //如果第一次发生了三相不平衡，记录下来
                                            if (!utpcMap.containsKey(key)) {
                                                utpcModel = new UTPCModel();
                                                utpcModel.setDeviceId(data.getDeviceId());
                                                utpcModel.setBeginTime(dataAttr.getDtime());
                                                utpcModel.setMaxUtpc(val);
                                                utpcModel.setPhase(maxDataAttr.getName());
                                                utpcMap.put(key, utpcModel);
                                            } else {
                                                //比较最大相三相不平衡值
                                                utpcModel = utpcMap.get(key);
                                                utpcModel.setMaxUtpc(utpcModel.getMaxUtpc() > val ? utpcModel.getMaxUtpc() : val);
                                            }
                                        });
                            }
                            //如果恢复三相平衡，从utpcMap中移除，并计算持续时间
                            else {
                                dataAttrs
                                        .stream()
                                        .filter(attr -> !Objects.equals(attr.getName(), "UTPC"))
                                        .forEach(dataAttr1 -> {
                                            String key = dataAttr1.getName() + data.getDeviceId();
                                            if (utpcMap.containsKey(key)) {
                                                UTPCModel utpcModel = utpcMap.get(key);
                                                utpcModel.setEndTime(dataAttr1.getDtime());
                                                int m = (int) DateUtils.getDiffMinutes(utpcModel.getBeginTime(), utpcModel.getEndTime());
                                                utpcModel.setDuration(m);
                                                //记录三项不平衡
                                                DeviceRcdutpc rcd = new DeviceRcdutpc();
                                                rcd.setUtpcid(UUIDUtils.getUUId());
                                                rcd.setMaxutpc(utpcModel.getMaxUtpc());
                                                rcd.setDeviceid(utpcModel.getDeviceId());
                                                rcd.setDuration(utpcModel.getDuration());
                                                rcd.setBegintime(utpcModel.getBeginTime());
                                                rcd.setEndtime(utpcModel.getEndTime());
                                                rcd.setPhase(utpcModel.getPhase());
                                                DtuDBService.getInstance().push(new DeviceData(DataType.UTPC, rcd));
                                                //移除
                                                utpcMap.remove(key);
                                            }
                                        });
                            }
                        }

                        //3.6 低电压
                        if (dataAttr.getName().equals("Ua") || dataAttr.getName().equals("Ub") || dataAttr.getName().equals("Uc")) {
                            double value = Double.parseDouble(String.valueOf(dataAttr.getValue()));
                            double rate = (UTPCModel.NORMAL_VOLTAGE - value) * 1.0f / (UTPCModel.NORMAL_VOLTAGE * 1.0f);
                            String key = dataAttr.getName() + data.getDeviceId();
                            UTPCModel utpcModel;
                            if (rate > UTPCModel.LOW_VOLTAGE_RATE) {
                                if (!utpcMap.containsKey(key)) {
                                    utpcModel = new UTPCModel();
                                    utpcModel.setDeviceId(data.getDeviceId());
                                    utpcModel.setBeginTime(dataAttr.getDtime());
                                    utpcModel.setPhase(dataAttr.getName());
                                    utpcModel.setMinU((int) value);
                                    utpcMap.put(key, utpcModel);
                                } else {
                                    //比较最低电压
                                    utpcModel = utpcMap.get(key);
                                    utpcModel.setMinU((int) (utpcModel.getMinU() < value ? utpcModel.getMinU() : value));
                                }
                            } else {
                                if (utpcMap.containsKey(key)) {
                                    utpcModel = utpcMap.get(key);
                                    utpcModel.setEndTime(dataAttr.getDtime());
                                    int m = (int) DateUtils.getDiffMinutes(utpcModel.getBeginTime(), utpcModel.getEndTime());
                                    utpcModel.setDuration(m);
                                    //记录低电压
                                    DeviceLowvoltage deviceLowvoltage = new DeviceLowvoltage();
                                    deviceLowvoltage.setLowuid(UUIDUtils.getUUId());
                                    deviceLowvoltage.setMinu(utpcModel.getMinU());
                                    deviceLowvoltage.setDeviceid(utpcModel.getDeviceId());
                                    deviceLowvoltage.setDuration(utpcModel.getDuration());
                                    deviceLowvoltage.setBegintime(utpcModel.getBeginTime());
                                    deviceLowvoltage.setEndtime(utpcModel.getEndTime());
                                    deviceLowvoltage.setPhase(dataAttr.getName());
                                    DtuDBService.getInstance().push(new DeviceData(DataType.LOW_VOLTAGE, deviceLowvoltage));
                                    //移除
                                    utpcMap.remove(key);
                                }
                            }
                        }
                        //3.7 读设备型号
                        //if (dataAttr.getName().equals(DeviceCmdTypeEnum.ReadDeviceModel.name())) {
                        //    DeviceRcdMapper deviceRcdMapper = sqlSession.getMapper(DeviceRcdMapper.class);
                        //    DeviceRcd rcd = deviceRcdMapper.selectByPrimaryKey(data.getDeviceId());
                        //    if (rcd != null) {
                        //        rcd.setRcdmodel((String) dataAttr.getValue());
                        //        //deviceRcdMapper.updateByPrimaryKey(rcd);
                        //    }
                        //}
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
            if (record != null && record.TripReason != null) {
                sqlSession = MyBatisUtil.getSqlSession(false);
                LogUtils.info("TripEventRecord:" + record.toString1());
                DeviceAlarmMapper deviceAlarmMapper = sqlSession.getMapper(DeviceAlarmMapper.class);
                //分闸报文
                if (record.State == LPState.OFF) {
                    DeviceDtuCacheResult result = MyCacheManager.getInstance().getDeviceDtuCacheResult(protocolLayerData.DTUString, TableEnum.Device_Rcd.getTableName().toLowerCase(), protocolLayerData.PostalAddress);
                    if (result != null) {
                        String deviceId = result.getDeviceId();
                        String tableName = TableEnum.Device_Rcd.getTableName().toLowerCase();
                        Date alarmTime = DateUtils.strToDate(DateUtils.dateToStr(record.AlarmTime));
                        DeviceAlarmExample deviceAlarmExample = new DeviceAlarmExample();
                        deviceAlarmExample
                                .createCriteria()
                                .andDeviceidEqualTo(deviceId)
                                .andDevicetablenameEqualTo(tableName)
                                .andStateEqualTo(LPState.OFF.getValue())
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
                            item.setAlarmtype(record.TripReason.getValue());
                            item.setCreatedate(DateUtils.getNowSqlDateTime());
                            item.setDevicetablename(tableName);
                            item.setOutagetime(alarmTime);
                            item.setIsdeal(0);
                            item.setAlarmlevel(1);
                            item.setIssendworkorder(0);
                            item.setSortcode(0);
                            //deviceAlarmMapper.insert(item);
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
                                String name = result.getName();
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
                    DeviceDtuCacheResult result = MyCacheManager.getInstance().getDeviceDtuCacheResult(protocolLayerData.DTUString, TableEnum.Device_Rcd.getTableName().toLowerCase(), protocolLayerData.PostalAddress);
                    if (result != null) {
                        DeviceAlarmExample deviceAlarmExample = new DeviceAlarmExample();
                        deviceAlarmExample
                                .createCriteria()
                                .andDeviceidEqualTo(result.getDeviceId())
                                .andDevicetablenameEqualTo(TableEnum.Device_Rcd.getTableName().toLowerCase())
                                .andStateEqualTo(LPState.OFF.getValue());

                        deviceAlarmMapper
                                .selectByExample(deviceAlarmExample)
                                .stream()
                                .max((o1, o2) -> o1.getAlarmtime().getTime() > o2.getAlarmtime().getTime() ? -1 : o1.getAlarmtime().getTime() == o1.getAlarmtime().getTime() ? 0 : 1)
                                .ifPresent(deviceAlarm -> {

                                    deviceAlarm.setPowerontime(DateUtils.getNowSqlDateTime());
                                    deviceAlarm.setDurationtime(DateUtils.getDiffMinutes(deviceAlarm.getOutagetime(), deviceAlarm.getPowerontime()));
                                    deviceAlarm.setModifydate(DateUtils.getNowSqlDateTime());
                                    deviceAlarm.setState(LPState.ON.getValue());
                                    //deviceAlarmMapper.updateByPrimaryKey(deviceAlarm);
                                    DtuDBService.getInstance().push(new DeviceData(DataType.ALARM, deviceAlarm));

                                    //发送合闸短信
                                    String name = result.getName();
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
                sqlSession = MyBatisUtil.getSqlSession(false);
                LogUtils.info(records.toString());
                RelationDtuDeviceMapper dtuDeviceMapper = sqlSession.getMapper(RelationDtuDeviceMapper.class);
                DeviceDtuMapper deviceDtuMapper = sqlSession.getMapper(DeviceDtuMapper.class);
                DeviceTerminalMapper terminalMapper = sqlSession.getMapper(DeviceTerminalMapper.class);
                DeviceRcdMapper deviceRcdMapper = sqlSession.getMapper(DeviceRcdMapper.class);

                //第一步 找到dtu
                DeviceDtuExample dtuExample = new DeviceDtuExample();
                dtuExample.createCriteria().andAddressEqualTo(data.getDtuAddress());
                deviceDtuMapper.selectByExample(dtuExample).stream().findFirst().ifPresent(deviceDtu -> data.setDtuId(deviceDtu.getOid()));

                //第二步 找到dtu相关联的一级漏保
                if (data.getDtuId() != null) {
                    RelationDtuDeviceExample dtuDeviceExample = new RelationDtuDeviceExample();
                    dtuDeviceExample.createCriteria().andDtuidEqualTo(data.getDtuId());
                    dtuDeviceMapper.selectByExample(dtuDeviceExample).stream().findFirst().ifPresent(relationDtuDevice -> data.setDeviceId(relationDtuDevice.getDeviceid()));
                }

                //第三步 获取该一级漏保的daId和daName
                if (data.getDeviceId() != null) {
                    DeviceRcd deviceRcd = deviceRcdMapper.selectByPrimaryKey(data.getDeviceId());
                    if (deviceRcd != null) {
                        data.setDaId(deviceRcd.getDaid());
                        DeviceDaMapper deviceDaMapper = sqlSession.getMapper(DeviceDaMapper.class);
                        DeviceDa deviceDa = deviceDaMapper.selectByPrimaryKey(deviceRcd.getDaid());
                        if (deviceDa != null) {
                            data.setDaName(deviceDa.getName());
                        }
                    }
                }

                //第四步 获取终端类型
                DeviceTerminalExample terminalExample = new DeviceTerminalExample();
                terminalExample.createCriteria().andCodeEqualTo(TerminalEnum.DLTLP6452007.getName());
                terminalMapper.selectByExample(terminalExample).stream().findFirst().ifPresent(deviceTerminal -> data.setTerminalId(deviceTerminal.getTerminalid()));

                for (SecondLpRecord record : records) {
                    //第五步 先查询是已经添加过,再添加或修改漏保信息
                    DeviceRcdExample example = new DeviceRcdExample();
                    example.createCriteria().andAddressEqualTo(record.getLpAddress());
                    deviceRcdMapper.selectByExample(example).stream().findFirst().ifPresent(data::setDeviceRcd);
                    //如果不存在就添加
                    DeviceRcd rcd = data.getDeviceRcd();
                    if (rcd == null) {
                        rcd = new DeviceRcd();
                        String deviceId = UUIDUtils.getUUId();
                        rcd.setOid(deviceId);
                        rcd.setAddress(record.getLpAddress());
                        rcd.setBaudrate(String.valueOf(record.getBaudRate()));
                        rcd.setCreatedate(DateUtils.getNowSqlDateTime());
                        rcd.setTerminalid(data.getTerminalId());
                        rcd.setName(data.getDaName() != null ? data.getDaName() + "二级漏保" + record.getLpAddress() : "二级漏保" + record.getLpAddress());
                        rcd.setCheckdigit("8e1");
                        rcd.setLevel("2");
                        rcd.setCycle("一年");
                        rcd.setValidate("一年");
                        rcd.setDaid(data.getDaId());
                        rcd.setUa(record.getRatedUa());
                        rcd.setUb(record.getRatedUb());
                        rcd.setUc(record.getRatedUc());
                        rcd.setIa(record.getRatedIa());
                        rcd.setIb(record.getRatedIb());
                        rcd.setIc(record.getRatedIc());
                        rcd.setIo(record.getRatedIo());
                        deviceRcdMapper.insert(rcd);

                        if (data.getDtuId() != null) {
                            //添加关联信息
                            RelationDtuDevice r = new RelationDtuDevice();
                            r.setId(UUIDUtils.getUUId());
                            r.setDtuid(data.getDtuId());
                            r.setDeviceid(deviceId);
                            r.setDevicetablename(TableEnum.Device_Rcd.getTableName());
                            r.setCreatedate(DateUtils.getNowSqlDateTime());
                            dtuDeviceMapper.insert(r);
                        }

                        //添加缓存信息,不管有没有dtu存在都添加进缓存
                        DeviceDtuCacheResult cacheResult = new DeviceDtuCacheResult();
                        cacheResult.setDeviceId(deviceId);
                        cacheResult.setDtuId(data.getDtuId());
                        cacheResult.setDaId(data.getDaId());
                        cacheResult.setDeviceAddress(record.getLpAddress());
                        cacheResult.setDtuAddress(data.getDtuAddress());
                        cacheResult.setDeviceTableName(TableEnum.Device_Rcd.getTableName());
                        cacheResult.setLevel("2");
                        cacheResults.add(cacheResult);
                    }
                    //修改
                    else {
                        rcd.setBaudrate(String.valueOf(record.getBaudRate()));
                        rcd.setTerminalid(data.getTerminalId());
                        rcd.setLevel("2");
                        rcd.setModifydate(DateUtils.getNowSqlDateTime());
                        rcd.setDaid(data.getDaId());
                        rcd.setName(data.getDaName() != null ? data.getDaName() + "二级漏保" + record.getLpAddress() : "二级漏保" + record.getLpAddress());
                        rcd.setBaudrate(String.valueOf(record.getBaudRate()));
                        rcd.setUa(record.getRatedUa());
                        rcd.setUb(record.getRatedUb());
                        rcd.setUc(record.getRatedUc());
                        rcd.setIa(record.getRatedIa());
                        rcd.setIb(record.getRatedIb());
                        rcd.setIc(record.getRatedIc());
                        rcd.setIo(record.getRatedIo());
                        deviceRcdMapper.updateByPrimaryKey(rcd);

                        //查询关联信息,修改或添加关联信息
                        if (data.getDtuId() != null) {
                            RelationDtuDeviceExample relationDtuDeviceExample = new RelationDtuDeviceExample();
                            relationDtuDeviceExample.createCriteria().andDtuidEqualTo(data.getDtuId()).andDeviceidEqualTo(rcd.getOid());
                            dtuDeviceMapper.selectByExample(relationDtuDeviceExample).stream().findFirst().ifPresent(data::setRelationDtuDevice);
                            RelationDtuDevice relationDtuDevice = data.getRelationDtuDevice();
                            if (relationDtuDevice == null) {
                                //添加关联信息
                                relationDtuDevice = new RelationDtuDevice();
                                relationDtuDevice.setId(UUIDUtils.getUUId());
                                relationDtuDevice.setDtuid(data.getDtuId());
                                relationDtuDevice.setDeviceid(rcd.getOid());
                                relationDtuDevice.setDevicetablename(TableEnum.Device_Rcd.getTableName());
                                relationDtuDevice.setCreatedate(DateUtils.getNowSqlDateTime());
                                dtuDeviceMapper.insert(relationDtuDevice);

                                //添加缓存信息
                                DeviceDtuCacheResult cacheResult = new DeviceDtuCacheResult();
                                cacheResult.setDeviceId(rcd.getOid());
                                cacheResult.setDaId(rcd.getDaid());
                                cacheResult.setDeviceAddress(rcd.getAddress());
                                cacheResult.setLevel(rcd.getLevel());
                                cacheResult.setDeviceTableName(TableEnum.Device_Rcd.getTableName().toLowerCase());
                                cacheResult.setDtuId(data.getDtuId());
                                cacheResult.setDtuAddress(data.getDtuAddress());
                                cacheResults.add(cacheResult);
                            }
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
