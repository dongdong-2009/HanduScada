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
import main.com.handu.scada.protocol.enums.*;
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

import static main.com.handu.scada.business.utpc.UTPCModel.UTPC;
import static main.com.handu.scada.protocol.enums.TrialResultType.on;

/**
 * Created by 柳梦 on 2017/12/22.
 */

@Subscriber
public class DBService implements ISubscriber {
    /**
     * 记录三项不平衡和低电压
     */
    private Map<String, UTPCModel> utpcMap = new ConcurrentHashMap<>();

    private DBService() {
    }

    /**
     * 创建自定义线程池
     */
    private ExecutorService executor = new MyThreadPoolExecutor();

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
            sqlSession = MyBatisUtil.getSqlSession(false);
            if (sqlSession != null) {
                this.protocolLayerData = protocolLayerData;
            }
        }

        @Override
        public void run() {
            if (protocolLayerData != null && sqlSession != null) {
                try {
                    if (protocolLayerData.deviceTypeEnum != null && protocolLayerData.CmdType != null) {
                        LogUtils.info("deviceType--" + protocolLayerData.deviceTypeEnum.name() + ",cmdType--" + protocolLayerData.CmdType.name());
                    }
                    //如果是告警
                    if (protocolLayerData.CmdType == DeviceCmdTypeEnum.ProtectorTripEventRecord) {
                        DeviceAlarm deviceAlarm = dealEventData(protocolLayerData.tripEventRecord);
                        if (deviceAlarm != null) {
                            saveDeviceAlarm(protocolLayerData.tripEventRecord, deviceAlarm);
                        }
                    }
                    //如果是二级漏保档案上报
                    else if (protocolLayerData.CmdType == DeviceCmdTypeEnum.SecondLpRecord) {
                        cacheResults.clear();
                        saveSecondLpRecord(new SecondLpRealData() {{
                            setDtuAddress(protocolLayerData.DTUString);
                            setSecondLpRecords(protocolLayerData.secondLpRecords);
                        }});
                    } else if (protocolLayerData.CmdType == DeviceCmdTypeEnum.DTU_LOGIN) {
                        online(protocolLayerData);
                    } else if (protocolLayerData.CmdType == DeviceCmdTypeEnum.DTU_OFF_LINE) {
                        offline(protocolLayerData);
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
                    LogUtils.error("dtuTask rollback...");
                    ExceptionHandler.handle(e);
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
                DeviceControlwordMapper deviceControlwordMapper = sqlSession.getMapper(DeviceControlwordMapper.class);
                DeviceControlword w = deviceControlwordMapper.selectByPrimaryKey(deviceId);
                if (w != null) {
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

                    w.setRecordtime(DateUtils.getNowSqlDateTime());
                    deviceControlwordMapper.updateByPrimaryKeySelective(w);
                } else {
                    w = new DeviceControlword();

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
                    deviceControlwordMapper.insert(w);
                }
            }
        }

        /**
         * 存入遥测表
         *
         * @param data 数据
         */
        private void saveDeviceTelemetering(RealDataItem data) {
            LogUtils.error("----1--data----" + data.toString());
            if (!StringsUtils.isEmpty(data.postalAddress) && !StringsUtils.isEmpty(data.deviceTableName)) {
                LogUtils.error("----2--postalAddress----" + data.postalAddress + "---deviceTableName---" + data.deviceTableName);
                //找到设备的id
                DeviceDtuCacheResult result = MyCacheManager.getInstance().getDeviceDtuCacheResult(data.dtuAddress, data.deviceTableName.toLowerCase(), data.getPostalAddress());
                if (result != null) data.deviceId = result.getDeviceId();
            }
            if (!StringsUtils.isEmpty(data.deviceId)) {
                LogUtils.error("----3--deviceId----" + data.deviceId);
                //判断如果是控制字则单独入库
                if (protocolLayerData.CmdType == DeviceCmdTypeEnum.ReadControlWordParameterModule) {
                    saveControlWord(data.deviceId);
                    return;
                }
                List<DataAttr> dataAttrs = data.list;
                if (dataAttrs == null) return;

                //DeviceRealRemotesignallingMapper deviceRealRemotesignallingMapper = sqlSession.getMapper(DeviceRealRemotesignallingMapper.class);
                //DeviceRealRemotetelemetryMapper deviceRealRemotetelemetryMapper = sqlSession.getMapper(DeviceRealRemotetelemetryMapper.class);
                DeviceRemoteindexsMapper deviceRemoteindexsMapper = sqlSession.getMapper(DeviceRemoteindexsMapper.class);

                //记录电流三项不平衡时发生在第几项
                int index = 0;
                double max = 0;
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
                        //3.5.1 先获取电流三项的最大值位置,并存储三项不平衡持续时间
                        if (dataAttr.getName().equals("Ia") || dataAttr.getName().equals("Ib") || dataAttr.getName().equals("Ic")) {
                            double value = Double.parseDouble(dataAttr.getValue() + "");
                            if (max < value) {
                                max = value;
                                index = i;
                            }
                            if (value <= UTPC) {
                                String key = dataAttr.getName() + data.getDeviceId();
                                if (utpcMap.containsKey(key)) {
                                    DeviceRcdutpcMapper deviceRcdutpcMapper = sqlSession.getMapper(DeviceRcdutpcMapper.class);
                                    UTPCModel utpcModel = utpcMap.get(key);
                                    utpcModel.setEndTime(dataAttr.getDtime());
                                    int m = (int) DateUtils.getDiffMinutes(utpcModel.getBeginTime(), utpcModel.getEndTime());
                                    utpcModel.setDuration(m);
                                    utpcMap.remove(key);

                                    DeviceRcdutpc rcd = new DeviceRcdutpc();
                                    rcd.setUtpcid(UUIDUtils.getUUId());
                                    rcd.setMaxutpc(utpcModel.getMaxUtpc());
                                    rcd.setDeviceid(utpcModel.getDeviceId());
                                    rcd.setDuration(m);
                                    rcd.setBegintime(utpcModel.getBeginTime());
                                    rcd.setEndtime(utpcModel.getEndTime());
                                    rcd.setPhase(dataAttr.getName());
                                    deviceRcdutpcMapper.insert(rcd);
                                }
                            }
                        }

                        //3.5.2 判断是否是三项不平衡
                        if (dataAttr.getName().equals("UTPC")) {
                            double val = Double.parseDouble(dataAttr.getValue() + "");
                            UTPCModel utpcModel;
                            if (val > UTPC) {
                                DataAttr maxDataAttr = dataAttrs.get(index);
                                String key = maxDataAttr.getName() + data.getDeviceId();
                                if (!utpcMap.containsKey(key)) {
                                    utpcModel = new UTPCModel();
                                    utpcModel.setDeviceId(data.getDeviceId());
                                    utpcModel.setBeginTime(dataAttr.getDtime());
                                    utpcModel.setMaxUtpc(val);
                                    utpcModel.setPhase(maxDataAttr.getName());
                                    utpcMap.put(key, utpcModel);
                                } else {
                                    utpcModel = utpcMap.get(key);
                                    utpcModel.setMaxUtpc(utpcModel.getMaxUtpc() > val ? utpcModel.getMaxUtpc() : val);
                                }
                            }
                        }

                        //3.6 低电压
                        if (dataAttr.getName().equals("Ua") || dataAttr.getName().equals("Ub") || dataAttr.getName().equals("Uc")) {
                            UTPCModel utpcModel;
                            double value = Double.parseDouble(dataAttr.getValue() + "");
                            double rate = (UTPCModel.VOLTAGE - value) * 1.0f / (UTPCModel.VOLTAGE * 1.0f);
                            String key = dataAttr.getName() + data.getDeviceId();
                            if (rate > UTPCModel.VOLTAGE_RATE) {
                                if (!utpcMap.containsKey(key)) {
                                    utpcModel = new UTPCModel();
                                    utpcModel.setDeviceId(data.getDeviceId());
                                    utpcModel.setBeginTime(dataAttr.getDtime());
                                    utpcModel.setPhase(dataAttr.getName());
                                    utpcModel.setMinU((int) value);
                                    utpcMap.put(key, utpcModel);
                                } else {
                                    utpcModel = utpcMap.get(key);
                                    utpcModel.setMinU((int) (utpcModel.getMinU() < value ? utpcModel.getMinU() : value));
                                }
                            } else {
                                if (utpcMap.containsKey(key)) {
                                    utpcModel = utpcMap.get(key);
                                    utpcModel.setEndTime(dataAttr.getDtime());
                                    int m = (int) DateUtils.getDiffMinutes(utpcModel.getBeginTime(), utpcModel.getEndTime());
                                    utpcModel.setDuration(m);
                                    utpcMap.remove(key);

                                    DeviceLowvoltageMapper deviceLowvoltageMapper = sqlSession.getMapper(DeviceLowvoltageMapper.class);
                                    DeviceLowvoltage deviceLowvoltage = new DeviceLowvoltage();
                                    deviceLowvoltage.setLowuid(UUIDUtils.getUUId());
                                    deviceLowvoltage.setMinu(utpcModel.getMinU());
                                    deviceLowvoltage.setDeviceid(utpcModel.getDeviceId());
                                    deviceLowvoltage.setDuration(utpcModel.getDuration());
                                    deviceLowvoltage.setBegintime(utpcModel.getBeginTime());
                                    deviceLowvoltage.setEndtime(utpcModel.getEndTime());
                                    deviceLowvoltage.setPhase(dataAttr.getName());
                                    deviceLowvoltageMapper.insert(deviceLowvoltage);
                                }
                            }
                        }

                        //3.7 读设备型号
                        if (dataAttr.getName().equals(DeviceCmdTypeEnum.ReadDeviceModel.name())) {
                            DeviceRcdMapper deviceRcdMapper = sqlSession.getMapper(DeviceRcdMapper.class);
                            DeviceRcd rcd = deviceRcdMapper.selectByPrimaryKey(data.getDeviceId());
                            if (rcd != null) {
                                rcd.setRcdmodel((String) dataAttr.getValue());
                                deviceRcdMapper.updateByPrimaryKey(rcd);
                            }
                        }
                    }
                }
            }
        }

        /**
         * 存入告警表
         */
        private void saveDeviceAlarm(TripEventRecord record, DeviceAlarm alarm) {

            //如果跳闸时间大于当前系统时间表明是误报
            if (record.getAlarmTime().getTime() > DateUtils.getNowDateTime().getTime()) {
                TxtUtils.alarm("错误的跳闸时间--" + DateUtils.dateToStr(record.AlarmTime) + "," + record.TripReason + "," + record.AlarmReason);
                return;
            }

            BaseAlarmcacheMapper baseAlarmcacheMapper = sqlSession.getMapper(BaseAlarmcacheMapper.class);
            DeviceRcdTrialswitchlogMapper rcdTrialswitchlogMapper = sqlSession.getMapper(DeviceRcdTrialswitchlogMapper.class);
            DeviceRcdMapper deviceRcdMapper = sqlSession.getMapper(DeviceRcdMapper.class);

            if (record.State == LPState.OFF) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(record.getAlarmTime());
                cal.add(Calendar.HOUR, 1);

                String alarmTime = DateUtils.getDateStr(record.AlarmTime);
                String now = DateUtils.getDateStr(new Date());
                String temp = DateUtils.getDateStr(cal.getTime());

                //只报当天的告警
                if (!(Objects.equals(alarmTime, now) || Objects.equals(temp, now))) {
                    return;
                }

                //查询分闸记录
                final int[] i = {0};
                BaseAlarmcacheExample baseAlarmcacheExample = new BaseAlarmcacheExample();
                baseAlarmcacheExample.createCriteria().andDeviceaddressEqualTo(record.Address);
                Optional<BaseAlarmcache> optional = baseAlarmcacheMapper.selectByExample(baseAlarmcacheExample).stream().findFirst();
                //查询漏保
                DeviceRcdExample deviceRcdExample = new DeviceRcdExample();
                deviceRcdExample.createCriteria().andAddressEqualTo(record.Address);
                Optional<DeviceRcd> rcdOptional = deviceRcdMapper.selectByExample(deviceRcdExample).stream().findFirst();

                optional.ifPresent(baseAlarmcache -> {
                    //如果不是重复采集
                    if (!baseAlarmcache.getHappentime().equals(record.AlarmTime)) {
                        //如果同一个设备，又发生了跳闸，
                        //按键试验和定时试验,记录跳闸是否成功
                        if (record.TripReason == TripReasonEnum.KeyTestTrip ||
                                record.TripReason == TripReasonEnum.TimingTestTrip ||
                                record.TripReason == TripReasonEnum.RemoteTrip) {
                            rcdOptional.ifPresent(deviceRcd -> {
                                ConcurrentHashMap<String, String> rcdTrialSwitchLogCache = MyCacheManager.getInstance().getDataByKey(MyCacheManager.DEVICE_RCD_TRIAL_SWITCH_LOG);
                                if (rcdTrialSwitchLogCache != null && rcdTrialSwitchLogCache.containsKey(deviceRcd.getOid())) {
                                    DeviceRcdTrialswitchlog trial = new DeviceRcdTrialswitchlog();
                                    trial.setOid(rcdTrialSwitchLogCache.get(deviceRcd.getOid()));
                                    trial.setResult(TrialResultType.trip.getType());
                                    trial.setTrialtime(DateUtils.getNowSqlDateTime());
                                    rcdTrialswitchlogMapper.updateByPrimaryKeySelective(trial);
                                }
                            });
                            baseAlarmcache.setHappentime(record.AlarmTime);
                            baseAlarmcache.setState(LPState.OFF.getValue());
                            i[0] = baseAlarmcacheMapper.updateByPrimaryKeySelective(baseAlarmcache);
                        }
                    }
                });
                if (!optional.isPresent()) {
                    BaseAlarmcache baseAlarmcache = new BaseAlarmcache();
                    baseAlarmcache.setDeviceaddress(record.Address);
                    baseAlarmcache.setDevicetablename(alarm.getDevicetablename());
                    baseAlarmcache.setFlag(alarm.getAlarmid());
                    baseAlarmcache.setHappentime(record.AlarmTime);
                    baseAlarmcache.setOid(UUIDUtils.getUUId());
                    baseAlarmcache.setState(record.State.getValue());
                    baseAlarmcache.setTripreason(record.AlarmReason);
                    i[0] = baseAlarmcacheMapper.insert(baseAlarmcache);
                }

                //分闸短信
                if (i[0] > 0) {
                    rcdOptional.ifPresent(rcd -> {
                        String name = rcd.getName();
                        String content = record.toString();
                        String alarmTime1 = DateUtils.dateToStr(record.AlarmTime);
                        String deviceId = rcd.getOid();
                        String msgContent = String.format("%s,于时间%s,%s,%s", name, alarmTime1, content, Config.getSystemName());
                        MsgManager.getInstance().putMsg(new Msg(deviceId, alarm.getAlarmtype(), msgContent));
                    });
                }

            } else if (record.State == LPState.ON) {
                //查询分闸记录
                BaseAlarmcacheExample baseAlarmcacheExample = new BaseAlarmcacheExample();
                baseAlarmcacheExample.createCriteria().andDeviceaddressEqualTo(record.Address).andStateEqualTo(LPState.OFF.getValue());
                baseAlarmcacheMapper.selectByExample(baseAlarmcacheExample).stream().findFirst().ifPresent(baseAlarmcache -> {
                    baseAlarmcache.setState(record.State.getValue());
                    int i = baseAlarmcacheMapper.updateByPrimaryKeySelective(baseAlarmcache);
                    if (i > 0) {
                        //查询漏保
                        DeviceRcdExample deviceRcdExample = new DeviceRcdExample();
                        deviceRcdExample.createCriteria().andAddressEqualTo(record.Address);
                        List<DeviceRcd> deviceRcds = deviceRcdMapper.selectByExample(deviceRcdExample);
                        if (deviceRcds != null && deviceRcds.size() > 0) {
                            DeviceRcd rcd = deviceRcds.get(0);
                            ConcurrentHashMap<String, String> rcdTrialSwitchLogCache = MyCacheManager.getInstance().getDataByKey(MyCacheManager.DEVICE_RCD_TRIAL_SWITCH_LOG);
                            //查询，是否有试跳情况，如果有，则需要说明试跳后合闸成功
                            if (rcdTrialSwitchLogCache.containsKey(rcd.getOid())) {
                                DeviceRcdTrialswitchlog trial = new DeviceRcdTrialswitchlog();
                                trial.setOid(rcdTrialSwitchLogCache.get(rcd.getOid()));
                                trial.setResult(on.getType());
                                trial.setTrialtime(DateUtils.getNowSqlDateTime());
                                rcdTrialswitchlogMapper.updateByPrimaryKeySelective(trial);
                                rcdTrialSwitchLogCache.remove(rcd.getOid());
                            }
                            String name = rcd.getName();
                            String content = "合闸成功";
                            String alarmTime = DateUtils.dateToStr(DateUtils.getNowSqlDateTime());
                            String deviceId = rcd.getOid();
                            String msgContent = String.format("%s,于时间%s,%s[%s]", name, alarmTime, content, Config.getSystemName());
                            MsgManager.getInstance().putMsg(new Msg(deviceId, alarm.getAlarmtype(), msgContent));
                        }
                    }
                });
            }
        }

        /**
         * 获取告警
         *
         * @param record
         * @return
         */
        private DeviceAlarm dealEventData(TripEventRecord record) {
            if (record != null && record.TripReason != null) {
                LogUtils.error("TripEventRecord:" + record.toString());
                DeviceAlarmMapper deviceAlarmMapper = sqlSession.getMapper(DeviceAlarmMapper.class);
                DeviceRcdMapper deviceRcdMapper = sqlSession.getMapper(DeviceRcdMapper.class);
                //分闸报文
                if (record.State == LPState.OFF) {
                    DeviceAlarm item = new DeviceAlarm();
                    item.setAlarmid(UUIDUtils.getUUId());
                    item.setUa((double) record.APhaseVoltage);
                    item.setUb((double) record.BPhaseVoltage);
                    item.setUc((double) record.CPhaseVoltage);
                    item.setIa((double) record.APhaseCurrent);
                    item.setIb((double) record.BPhaseCurrent);
                    item.setIc((double) record.CPhaseCurrent);
                    item.setIo((double) record.ResidualCurrent);
                    item.setAlarmphase(record.AlarmPhase);
                    item.setAlarmcontent(record.AlarmReason);
                    item.setAlarmtime(DateUtils.strToDate(DateUtils.dateToStr(record.AlarmTime)));
                    item.setAlarmtype(record.TripReason.getValue());
                    item.setCreatedate(DateUtils.getNowSqlDateTime());
                    item.setDevicetablename(TableEnum.Device_Rcd.getTableName().toLowerCase());
                    item.setOutagetime(DateUtils.strToDate(DateUtils.dateToStr(record.AlarmTime)));
                    item.setIsdeal(0);
                    item.setAlarmlevel(1);
                    item.setCreateusername("System");
                    item.setIssendworkorder(0);
                    item.setSortcode(0);

                    DeviceRcdExample deviceRcdExample = new DeviceRcdExample();
                    deviceRcdExample.createCriteria().andAddressEqualTo(record.Address);
                    deviceRcdMapper.selectByExample(deviceRcdExample).stream().findFirst().ifPresent(deviceRcd -> item.setDeviceid(deviceRcd.getOid()));
                    if (item.getDeviceid() != null) {
                        DeviceAlarmExample deviceAlarmExample = new DeviceAlarmExample();
                        deviceAlarmExample.createCriteria().andDeviceidEqualTo(item.getDeviceid()).andDevicetablenameEqualTo(item.getDevicetablename().toLowerCase()).andAlarmtimeEqualTo(item.getAlarmtime());
                        Optional<DeviceAlarm> optional = deviceAlarmMapper.selectByExample(deviceAlarmExample).stream().findFirst();
                        if (!optional.isPresent()) {
                            deviceAlarmMapper.insert(item);
                            return item;
                        }
                    }
                    return null;
                }
                //合闸报文
                else if (record.getState() == LPState.ON) {
                    final DeviceAlarm[] alarm = {null};
                    DeviceRcdExample deviceRcdExample = new DeviceRcdExample();
                    deviceRcdExample.createCriteria().andAddressEqualTo(record.Address);
                    deviceRcdMapper.selectByExample(deviceRcdExample).stream().findFirst().ifPresent(deviceRcd -> {
                        DeviceAlarmExample deviceAlarmExample = new DeviceAlarmExample();
                        deviceAlarmExample.createCriteria().andDeviceidEqualTo(deviceRcd.getOid());
                        deviceAlarmMapper.selectByExample(deviceAlarmExample)
                                .stream().findFirst().ifPresent(deviceAlarm -> {
                            deviceAlarm.setPowerontime(DateUtils.getNowSqlDateTime());
                            deviceAlarm.setDurationtime(DateUtils.getDiffMinutes(deviceAlarm.getOutagetime(), deviceAlarm.getPowerontime()));
                            deviceAlarm.setModifydate(DateUtils.getNowSqlDateTime());
                            deviceAlarmMapper.updateByPrimaryKey(deviceAlarm);
                            alarm[0] = deviceAlarm;
                        });
                    });
                    return alarm[0];
                }
            }
            return null;
        }

        /**
         * 二级漏保档案入库
         *
         * @param data
         */
        private void saveSecondLpRecord(SecondLpRealData data) {
            List<SecondLpRecord> records = data.getSecondLpRecords();
            if (records != null && records.size() > 0) {
                LogUtils.info(records.toString(), true);

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
                        cacheResult.setTerminalId(data.getTerminalId());
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
                                cacheResult.setTerminalId(rcd.getTerminalid());
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
