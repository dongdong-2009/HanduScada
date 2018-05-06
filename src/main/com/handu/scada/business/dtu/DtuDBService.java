package main.com.handu.scada.business.dtu;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.*;
import main.com.handu.scada.db.bean.common.DtuCacheResult;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;
import main.com.handu.scada.utils.UUIDUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/04/03.
 * dtu相关数据入库
 */
public class DtuDBService extends BaseDBService {

    /**
     * 每次取数据的数量
     */
    private int takeSize = 10000;
    /**
     * 提交间隔时间
     */
    private int interval = 5000;

    /**
     * DTU状态更改队列
     */
    private BlockingQueue<DtuStateResult> dtuStateQueue = new LinkedBlockingDeque<>();

    /**
     * 设备数据遥信遥测入库队列
     */
    private BlockingQueue<DeviceData> dataQueue = new LinkedBlockingDeque<>();

    private static DtuDBService singleton;

    public static DtuDBService getInstance() {
        if (singleton == null) {
            synchronized (DtuDBService.class) {
                if (singleton == null) {
                    singleton = new DtuDBService();
                }
            }
        }
        return singleton;
    }

    private DtuDBService() {
        init();
        //提交间隔时间
        new Timer().schedule(new ChangeDtuStateTsk(), 0, interval);
        new Timer().schedule(new DeviceDataTask(), 0, interval);
    }

    private void init() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("configure.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                if (entry.getKey().equals("db.takeSize")) {
                    takeSize = Integer.valueOf(String.valueOf(entry.getValue()));
                } else if (entry.getKey().equals("db.interval")) {
                    interval = Integer.parseInt(String.valueOf(entry.getValue())) * 1000;
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * 消息入队
     *
     * @param result
     * @return
     */
    public void push(DtuStateResult result) {
        try {
            this.dtuStateQueue.put(result);
        } catch (InterruptedException e) {
            ExceptionHandler.print(e);
        }
    }

    /**
     * 消息入队
     *
     * @param result
     * @return
     */
    public void push(DeviceData result) {
        try {
            this.dataQueue.put(result);
        } catch (InterruptedException e) {
            ExceptionHandler.print(e);
        }
    }

    /**
     * 消息出队
     *
     * @return
     */
    private List<DtuStateResult> take() {
        List<DtuStateResult> results = new ArrayList<>();
        this.dtuStateQueue.drainTo(results, takeSize);
        return results;
    }

    /**
     * 消息出队
     *
     * @return
     */
    private List<DeviceData> take1() {
        List<DeviceData> results = new ArrayList<>();
        this.dataQueue.drainTo(results, takeSize);
        return results;
    }

    /**
     * 根据dtuId获取deviceId
     *
     * @param dtuAddress
     * @return
     */
    private String getDtuIdByDtuAddress(String dtuAddress) {
        if (StringsUtils.isNotEmpty(dtuAddress)) {
            DtuCacheResult dtuCacheResult = MyCacheManager.getDtuCacheResult(dtuAddress);
            if (dtuCacheResult != null) return dtuCacheResult.getDtuId();
        }
        return null;
    }

    private class ChangeDtuStateTsk extends TimerTask {

        @Override
        public void run() {
            SqlSession sqlSession = null;
            try {
                List<DtuStateResult> results = take().stream().map(result -> {
                    if (StringsUtils.isEmpty(result.getDtuId())) {
                        result.setDtuId(getDtuIdByDtuAddress(result.getDtuAddress()));
                    }
                    return result;
                }).collect(Collectors.toList());
                if (results.size() > 0) {
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(" insert into device_staterecord(RecordId,DeviceTableName,DeviceId,State,Description,OnlineTime) values ");
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(" insert into device_staterecord(RecordId,DeviceTableName,DeviceId,State,Description,UnLineTime) values ");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(" insert into device_history_staterecord(RecordId,DeviceTableName,DeviceId,State,Description,UnLineTime) values");
                    final int[] i = {0, 0};
                    results.stream()
                            .filter(result -> StringsUtils.isNotEmpty(result.getDtuId()))
                            .forEach(result -> {
                                if (result.getState() == DtuState.ONLINE) {
                                    if (i[0] != 0) sb1.append(",");
                                    sb1.append(getStartColumn(result.getDtuId()))
                                            .append(getColumn(DeviceTableEnum.Device_Dtu.getTableName().toLowerCase()))
                                            .append(getColumn(result.getDtuId()))
                                            .append("'1','在线',")
                                            .append(getEndColumn(DateUtils.dateToStr(result.getTime())));
                                    i[0]++;
                                } else if (result.getState() == DtuState.OFFLINE) {
                                    if (i[1] != 0) {
                                        sb2.append(",");
                                        sb3.append(",");
                                    }
                                    sb2.append(getStartColumn(result.getDtuId()))
                                            .append(getColumn(DeviceTableEnum.Device_Dtu.getTableName().toLowerCase()))
                                            .append(getColumn(result.getDtuId()))
                                            .append("'2','离线',")
                                            .append(getEndColumn(DateUtils.dateToStr(result.getTime())));
                                    sb3.append(getStartColumn(UUIDUtils.getUUId()))
                                            .append(getColumn(DeviceTableEnum.Device_Dtu.getTableName().toLowerCase()))
                                            .append(getColumn(result.getDtuId()))
                                            .append("'2','离线',")
                                            .append(getEndColumn(DateUtils.dateToStr(result.getTime())));
                                    i[1]++;
                                }
                            });

                    sqlSession = MyBatisUtil.getSqlSession();
                    CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
                    if (i[0] > 0) {
                        sb1.append(" on duplicate key update RecordId=values(RecordId),DeviceTableName=values(DeviceTableName),DeviceId=values(DeviceId),State=values(State),Description=values(Description),OnlineTime=values(OnlineTime)");
                        mapper.updateBySql(getSql(sb1));
                    }
                    if (i[1] > 0) {
                        sb2.append(" on duplicate key update RecordId=values(RecordId),DeviceTableName=values(DeviceTableName),DeviceId=values(DeviceId),State=values(State),Description=values(Description),UnLineTime=values(UnLineTime)");
                        mapper.updateBySql(getSql(sb2));
                        mapper.insertBySql(getSql(sb3));
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            } finally {
                if (sqlSession != null) sqlSession.close();
            }
        }
    }

    private class DeviceDataTask extends TimerTask {
        @Override
        public void run() {
            SqlSession sqlSession = null;
            try {
                List<DeviceData> results = take1();
                if (results.size() > 0) {
                    sqlSession = MyBatisUtil.getSqlSession();
                    CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);

                    //遥信历史库
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(" insert into device_history_remotesignalling (RemoteSignallingId, DeviceId, DataItem, Value, Unit, RecordTime, Description ) values ");

                    //遥测历史库
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(" insert into device_history_remotetelemetry (RemoteTelemetryId, DeviceId, DataItem, Value, Unit, RecordTime, Description ) values ");

                    //遥信实时库
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(" insert into device_real_remotesignalling (DeviceId, DataItem, Value, Unit, RecordTime, Description ) values ");

                    //遥测实时库
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(" insert into device_real_remotetelemetry (DeviceId, DataItem, Value, Unit, RecordTime, Description ) values ");

                    //集中器心跳时间
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(" insert into device_concentrator_last_heartbeat_time (dtuId,lastHeartbeatTime,recordTime) values ");

                    //三项不平衡
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(" insert into device_rcdutpc (utpcId,deviceId,maxutpc,beginTime,endTime,duration,phase) values ");

                    //低电压
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(" insert into device_lowvoltage (lowUId,phase,beginTime,endTime,deviceId,duration,minU) values ");

                    //控制字
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(" insert into device_controlword (deviceId,flagAllAlarm,flagLightAlarm,flagAudioAlarm,flagTimelyTrial,flagLevelReturn,flagReclosing,")
                            .append("flagUnderVoltageAlarm,flagUnderVoltageControl,flagOverVoltageAlarm,flagOverVoltageControl,flagMissPhaseAlarm,flagMissPhaseControl,")
                            .append("flagOverCurrentAlarm,flagOverCurrentControl,flagTrialSource,flagMissEarthLineAlarm,flagMissEarthLineControl,residualThresholdLevel,")
                            .append("delayTimeLevel,residualAlarmTimeLevel,recordTime) values ");

                    //告警
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append(" insert into device_alarm(AlarmId , DeviceTableName, DeviceId , State , AlarmContent ," +
                            " Ua , Ub , Uc , Ia , Ib , Ic , Io , AlarmType , AlarmPhase , AlarmLevel , DurationTime , " +
                            "OutageTime , PowerOnTime , AlarmTime , WorkOrderId , IsDeal , DealTime , IsSendWorkOrder," +
                            " SendTime , SortCode , DeleteMark , EnabledMark , Description , CreateDate , CreateUserId ," +
                            " CreateUserName , ModifyDate , ModifyUserId , ModifyUserName) values ");

                    //二级漏保档案上报
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append(" insert into device_rcd (Oid, DaId, Name, Address, BaudRate, Checkdigit," +
                            " TerminalId, Level, DtuId ,Ua,Ub,Uc,Ia,Ib,Ic,Io) values ");

                    //台区总表afn0c25数据
                    StringBuilder sb_afn0c25_real = new StringBuilder();
                    StringBuilder sb_afn0c25_history = new StringBuilder();
                    sb_afn0c25_real.append(" INSERT INTO device_hm_real_afn0c25( DtuAddress, ReadMeterTime, NowDayTotalActivePower," +
                            " NowDayAPhaseActivePower, NowDayBPhaseActivePower, NowDayCPhaseActivePower, NowDayTotalReactivePower," +
                            " NowDayAPhaseReactivePower, NowDayBPhaseReactivePower, NowDayCPhaseReactivePower," +
                            " NowDayTotalPowerFactor, NowDayAPhasePowerFactor, NowDayBPhasePowerFactor, NowDayCPhasePowerFactor," +
                            " NowDayAPhaseVoltage, NowDayBPhaseVoltage, NowDayCPhaseVoltage, NowDayAPhaseCurrent, " +
                            " NowDayBPhaseCurrent, NowDayCPhaseCurrent, NowDayResidualCurrent, NowDayTotalApparentPower," +
                            " NowDayAPhaseApparentPower, NowDayBPhaseApparentPower, NowDayCPhaseApparentPower, UTPC, Overload, " +
                            " RecordTime) VALUES ");
                    sb_afn0c25_history.append(" INSERT INTO device_hm_history_afn0c25( DtuAddress, ReadMeterTime, NowDayTotalActivePower," +
                            " NowDayAPhaseActivePower, NowDayBPhaseActivePower, NowDayCPhaseActivePower, NowDayTotalReactivePower," +
                            " NowDayAPhaseReactivePower, NowDayBPhaseReactivePower, NowDayCPhaseReactivePower," +
                            " NowDayTotalPowerFactor, NowDayAPhasePowerFactor, NowDayBPhasePowerFactor, NowDayCPhasePowerFactor," +
                            " NowDayAPhaseVoltage, NowDayBPhaseVoltage, NowDayCPhaseVoltage, NowDayAPhaseCurrent, " +
                            " NowDayBPhaseCurrent, NowDayCPhaseCurrent, NowDayResidualCurrent, NowDayTotalApparentPower," +
                            " NowDayAPhaseApparentPower, NowDayBPhaseApparentPower, NowDayCPhaseApparentPower, UTPC, Overload, " +
                            " RecordTime) VALUES ");


                    //台区总表三相不平衡、低电压、过载重载
                    StringBuilder sb_afn0c25_utpc = new StringBuilder();
                    sb_afn0c25_utpc.append(" insert into device_hm_utpc (dtuAddress, maxUtpc, beginTime, endTime, duration, phase) values ");
                    StringBuilder sb_afn0c25_low_voltage = new StringBuilder();
                    sb_afn0c25_low_voltage.append(" insert into device_hm_low_voltage (dtuAddress, minU, phase, beginTime, endTime, duration) values ");
                    StringBuilder sb_afn0c25_overload = new StringBuilder();
                    sb_afn0c25_overload.append(" insert into device_hm_overload (dtuAddress, overload, beginTime, endTime, duration) values ");

                    final int[] i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    for (DeviceData result : results) {
                        switch (result.getDataType()) {
                            case YX_HISTORY:
                                if (i[0] != 0) sb1.append(",");
                                DeviceHistoryRemotesignalling deviceHistoryRemotesignalling = (DeviceHistoryRemotesignalling) result.getData();
                                if (deviceHistoryRemotesignalling != null) {
                                    sb1.append("(")
                                            .append("'")
                                            .append(deviceHistoryRemotesignalling.getRemotesignallingid())
                                            .append("','")
                                            .append(deviceHistoryRemotesignalling.getDeviceid())
                                            .append("','")
                                            .append(deviceHistoryRemotesignalling.getDataitem())
                                            .append("',")
                                            .append(deviceHistoryRemotesignalling.getValue())
                                            .append(",'")
                                            .append(deviceHistoryRemotesignalling.getUnit())
                                            .append("','")
                                            .append(deviceHistoryRemotesignalling.getRecordtime())
                                            .append("','")
                                            .append(deviceHistoryRemotesignalling.getDescription() == null ? "" : deviceHistoryRemotesignalling.getDescription())
                                            .append("')");
                                    i[0]++;
                                }
                                break;
                            case YC_HISTORY:
                                if (i[1] != 0) sb2.append(",");
                                DeviceHistoryRemotetelemetry deviceHistoryRemotetelemetry = (DeviceHistoryRemotetelemetry) result.getData();
                                if (deviceHistoryRemotetelemetry != null) {
                                    sb2.append("(")
                                            .append("'")
                                            .append(deviceHistoryRemotetelemetry.getRemotetelemetryid())
                                            .append("','")
                                            .append(deviceHistoryRemotetelemetry.getDeviceid())
                                            .append("','")
                                            .append(deviceHistoryRemotetelemetry.getDataitem())
                                            .append("','")
                                            .append(deviceHistoryRemotetelemetry.getValue())
                                            .append("','")
                                            .append(deviceHistoryRemotetelemetry.getUnit())
                                            .append("','")
                                            .append(deviceHistoryRemotetelemetry.getRecordtime())
                                            .append("','")
                                            .append(deviceHistoryRemotetelemetry.getDescription() == null ? "" : deviceHistoryRemotetelemetry.getDescription())
                                            .append("')");
                                    i[1]++;
                                }
                                break;
                            case YX_REAL:
                                if (i[2] != 0) sb3.append(",");
                                DeviceRealRemotesignalling remotesignalling = (DeviceRealRemotesignalling) result.getData();
                                if (remotesignalling != null) {
                                    sb3.append("('")
                                            .append(remotesignalling.getDeviceid())
                                            .append("','")
                                            .append(remotesignalling.getDataitem())
                                            .append("','")
                                            .append(remotesignalling.getValue())
                                            .append("','")
                                            .append(remotesignalling.getUnit())
                                            .append("','")
                                            .append(remotesignalling.getRecordtime())
                                            .append("','")
                                            .append(remotesignalling.getDescription() == null ? "" : remotesignalling.getDescription())
                                            .append("')");
                                    i[2]++;
                                }
                                break;
                            case YC_REAL:
                                if (i[3] != 0) sb4.append(",");
                                DeviceRealRemotetelemetry remotetelemetry = (DeviceRealRemotetelemetry) result.getData();
                                if (remotetelemetry != null) {
                                    sb4.append("('")
                                            .append(remotetelemetry.getDeviceid())
                                            .append("','")
                                            .append(remotetelemetry.getDataitem())
                                            .append("','")
                                            .append(remotetelemetry.getValue())
                                            .append("','")
                                            .append(remotetelemetry.getUnit())
                                            .append("','")
                                            .append(remotetelemetry.getRecordtime())
                                            .append("','")
                                            .append(remotetelemetry.getDescription() == null ? "" : remotetelemetry.getDescription())
                                            .append("')");
                                    i[3]++;
                                }
                                break;
                            case CONCENTRATOR_STATE:
                                if (i[4] != 0) sb5.append(",");
                                DeviceConcentratorLastHeartbeatTime time = (DeviceConcentratorLastHeartbeatTime) result.getData();
                                if (time != null) {
                                    String dtuId = getDtuIdByDtuAddress(time.getDtuAddress());
                                    if (dtuId != null) {
                                        sb5.append("('")
                                                .append(dtuId)
                                                .append("','")
                                                .append(time.getLastheartbeattime())
                                                .append("','")
                                                .append(time.getRecordtime())
                                                .append("')");
                                        i[4]++;
                                    } else {
                                        LogUtils.error("not find dtuId by dtuAddress " + time.getDtuAddress());
                                    }
                                }
                                break;
                            case LP_UTPC:
                                if (i[5] != 0) sb6.append(",");
                                DeviceRcdutpc rcdutpc = (DeviceRcdutpc) result.getData();
                                if (rcdutpc != null) {
                                    sb6.append("('")
                                            .append(rcdutpc.getUtpcid())
                                            .append("','")
                                            .append(rcdutpc.getDeviceid())
                                            .append("',")
                                            .append(rcdutpc.getMaxutpc())
                                            .append(",'")
                                            .append(rcdutpc.getBegintime())
                                            .append("','")
                                            .append(rcdutpc.getEndtime())
                                            .append("',")
                                            .append(rcdutpc.getDuration())
                                            .append(",'")
                                            .append(rcdutpc.getPhase())
                                            .append("')");

                                    i[5]++;
                                }
                                break;
                            case LP_LOW_VOLTAGE:
                                if (i[6] != 0) sb7.append(",");
                                DeviceLowvoltage lowvoltage = (DeviceLowvoltage) result.getData();
                                if (lowvoltage != null) {
                                    sb7.append("('")
                                            .append(lowvoltage.getLowuid())
                                            .append("','")
                                            .append(lowvoltage.getPhase())
                                            .append("','")
                                            .append(lowvoltage.getBegintime())
                                            .append("','")
                                            .append(lowvoltage.getEndtime())
                                            .append("','")
                                            .append(lowvoltage.getDeviceid())
                                            .append("',")
                                            .append(lowvoltage.getDuration())
                                            .append(",")
                                            .append(lowvoltage.getMinu())
                                            .append(")");
                                    i[6]++;
                                }
                                break;
                            case CONTROL_WORD:
                                if (i[7] != 0) sb8.append(",");
                                DeviceControlword controlword = (DeviceControlword) result.getData();
                                if (controlword != null) {
                                    sb8.append("('")
                                            .append(controlword.getDeviceid())
                                            .append("',")
                                            .append(controlword.getFlagallalarm())
                                            .append(",")
                                            .append(controlword.getFlaglightalarm())
                                            .append(",")
                                            .append(controlword.getFlagaudioalarm())
                                            .append(",")
                                            .append(controlword.getFlagtimelytrial())
                                            .append(",")
                                            .append(controlword.getFlaglevelreturn())
                                            .append(",")
                                            .append(controlword.getFlagreclosing())
                                            .append(",")
                                            .append(controlword.getFlagundervoltagealarm())
                                            .append(",")
                                            .append(controlword.getFlagundervoltagecontrol())
                                            .append(",")
                                            .append(controlword.getFlagovervoltagealarm())
                                            .append(",")
                                            .append(controlword.getFlagovervoltagecontrol())
                                            .append(",")
                                            .append(controlword.getFlagmissphasealarm())
                                            .append(",")
                                            .append(controlword.getFlagmissphasecontrol())
                                            .append(",")
                                            .append(controlword.getFlagovercurrentalarm())
                                            .append(",")
                                            .append(controlword.getFlagovercurrentcontrol())
                                            .append(",")
                                            .append(controlword.getFlagtrialsource())
                                            .append(",")
                                            .append(controlword.getFlagmissearthlinealarm())
                                            .append(",")
                                            .append(controlword.getFlagmissearthlinecontrol())
                                            .append(",")
                                            .append(controlword.getResidualthresholdlevel())
                                            .append(",")
                                            .append(controlword.getDelaytimelevel())
                                            .append(",")
                                            .append(controlword.getResidualalarmtimelevel())
                                            .append(",'")
                                            .append(controlword.getRecordtime())
                                            .append("')");
                                    i[7]++;
                                }
                                break;
                            case ALARM:
                                if (i[8] != 0) sb9.append(",");
                                DeviceAlarm alarm = (DeviceAlarm) result.getData();
                                if (alarm != null) {
                                    sb9.append("('")
                                            .append(alarm.getAlarmid())
                                            .append("','")
                                            .append(alarm.getDevicetablename())
                                            .append("','")
                                            .append(alarm.getDeviceid())
                                            .append("',")
                                            .append(alarm.getState())
                                            .append(",")
                                            .append(alarm.getAlarmcontent() == null ? null : "'" + alarm.getAlarmcontent() + "'")
                                            .append(",")
                                            .append(alarm.getUa())
                                            .append(",")
                                            .append(alarm.getUb())
                                            .append(",")
                                            .append(alarm.getUc())
                                            .append(",")
                                            .append(alarm.getIa())
                                            .append(",")
                                            .append(alarm.getIb())
                                            .append(",")
                                            .append(alarm.getIc())
                                            .append(",")
                                            .append(alarm.getIo())
                                            .append(",")
                                            .append(alarm.getAlarmtype())
                                            .append(",")
                                            .append(alarm.getAlarmphase() == null ? null : "'" + alarm.getAlarmphase() + "'")
                                            .append(",")
                                            .append(alarm.getAlarmlevel())
                                            .append(",")
                                            .append(alarm.getDurationtime())
                                            .append(",")
                                            .append(alarm.getOutagetime() != null ? "'" + DateUtils.dateToStr(alarm.getOutagetime()) + "'" : null)
                                            .append(",")
                                            .append(alarm.getPowerontime() != null ? "'" + DateUtils.dateToStr(alarm.getPowerontime()) + "'" : null)
                                            .append(",")
                                            .append(alarm.getAlarmtime() != null ? "'" + DateUtils.dateToStr(alarm.getAlarmtime()) + "'" : null)
                                            .append(",")
                                            .append(alarm.getWorkorderid() == null ? null : "'" + alarm.getWorkorderid() + "'")
                                            .append(",")
                                            .append(alarm.getIsdeal())
                                            .append(",")
                                            .append(alarm.getDealtime() != null ? "'" + DateUtils.dateToStr(alarm.getDealtime()) + "'" : null)
                                            .append(",")
                                            .append(alarm.getIssendworkorder())
                                            .append(",")
                                            .append(alarm.getSendtime() != null ? "'" + DateUtils.dateToStr(alarm.getSendtime()) + "'" : null)
                                            .append(",")
                                            .append(alarm.getSortcode())
                                            .append(",")
                                            .append(alarm.getDeletemark())
                                            .append(",")
                                            .append(alarm.getEnabledmark())
                                            .append(",")
                                            .append(alarm.getDescription() == null ? null : "'" + alarm.getDescription() + "'")
                                            .append(",")
                                            .append(alarm.getCreatedate() != null ? "'" + DateUtils.dateToStr(alarm.getCreatedate()) + "'" : null)
                                            .append(",")
                                            .append(alarm.getCreateuserid() == null ? null : "'" + alarm.getCreateuserid() + "'")
                                            .append(",")
                                            .append(alarm.getCreateusername() == null ? null : "'" + alarm.getCreateusername() + "'")
                                            .append(",")
                                            .append(alarm.getModifydate() != null ? "'" + DateUtils.dateToStr(alarm.getModifydate()) + "'" : null)
                                            .append(",")
                                            .append(alarm.getModifyuserid() == null ? null : "'" + alarm.getModifyuserid() + "'")
                                            .append(",")
                                            .append(alarm.getModifyusername() == null ? null : "'" + alarm.getModifyusername() + "'")
                                            .append(")");
                                    i[8]++;
                                }
                                break;
                            case SECOND_LP_RECORD_CREATE:
                                if (i[9] != 0) sb9.append(",");
                                DeviceRcd rcd = (DeviceRcd) result.getData();
                                if (rcd != null) {
                                    sb10.append("('")
                                            .append(rcd.getOid())
                                            .append("','")
                                            .append(rcd.getDaid())
                                            .append("','")
                                            .append(rcd.getName())
                                            .append("','")
                                            .append(rcd.getAddress())
                                            .append("','")
                                            .append(rcd.getBaudrate())
                                            .append("','")
                                            .append(rcd.getCheckdigit())
                                            .append("','")
                                            .append(rcd.getTerminalid())
                                            .append("',")
                                            .append(Integer.parseInt(rcd.getLevel()))
                                            .append(",'")
                                            .append(rcd.getDtuid())
                                            .append("',")
                                            .append(rcd.getUa())
                                            .append(",")
                                            .append(rcd.getUb())
                                            .append(",")
                                            .append(rcd.getUc())
                                            .append(",")
                                            .append(rcd.getIa())
                                            .append(",")
                                            .append(rcd.getIb())
                                            .append(",")
                                            .append(rcd.getIc())
                                            .append(",")
                                            .append(rcd.getIo())
                                            .append(")");
                                    i[9]++;
                                }
                                break;
                            case HM_AFN0C25:
                                if (i[10] != 0) {
                                    sb_afn0c25_real.append(",");
                                    sb_afn0c25_history.append(",");
                                }
                                DeviceHmRealAfn0c25 afn0c25 = (DeviceHmRealAfn0c25) result.getData();
                                if (afn0c25 != null) {
                                    sb_afn0c25_real
                                            .append(getStartColumn(afn0c25.getDtuaddress()))
                                            .append(getColumn(DateUtils.dateToStr(afn0c25.getReadmetertime())))
                                            .append(getColumn(afn0c25.getNowdaytotalactivepower()))
                                            .append(getColumn(afn0c25.getNowdayaphaseactivepower()))
                                            .append(getColumn(afn0c25.getNowdaybphaseactivepower()))
                                            .append(getColumn(afn0c25.getNowdaycphaseactivepower()))
                                            .append(getColumn(afn0c25.getNowdaytotalreactivepower()))
                                            .append(getColumn(afn0c25.getNowdayaphasereactivepower()))
                                            .append(getColumn(afn0c25.getNowdaybphasereactivepower()))
                                            .append(getColumn(afn0c25.getNowdaycphasereactivepower()))
                                            .append(getColumn(afn0c25.getNowdaytotalpowerfactor()))
                                            .append(getColumn(afn0c25.getNowdayaphasepowerfactor()))
                                            .append(getColumn(afn0c25.getNowdaybphasepowerfactor()))
                                            .append(getColumn(afn0c25.getNowdaycphasepowerfactor()))
                                            .append(getColumn(afn0c25.getNowdayaphasevoltage()))
                                            .append(getColumn(afn0c25.getNowdaybphasevoltage()))
                                            .append(getColumn(afn0c25.getNowdaycphasevoltage()))
                                            .append(getColumn(afn0c25.getNowdayaphasecurrent()))
                                            .append(getColumn(afn0c25.getNowdaybphasecurrent()))
                                            .append(getColumn(afn0c25.getNowdaycphasecurrent()))
                                            .append(getColumn(afn0c25.getNowdayresidualcurrent()))
                                            .append(getColumn(afn0c25.getNowdaytotalapparentpower()))
                                            .append(getColumn(afn0c25.getNowdayaphaseapparentpower()))
                                            .append(getColumn(afn0c25.getNowdaybphaseapparentpower()))
                                            .append(getColumn(afn0c25.getNowdaycphaseapparentpower()))
                                            .append(getColumn(afn0c25.getUtpc()))
                                            .append(getColumn(afn0c25.getOverload()))
                                            .append(getEndColumn(DateUtils.dateToStr(afn0c25.getRecordtime())));

                                    sb_afn0c25_history
                                            .append(getStartColumn(afn0c25.getDtuaddress()))
                                            .append(getColumn(DateUtils.dateToStr(afn0c25.getReadmetertime())))
                                            .append(getColumn(afn0c25.getNowdaytotalactivepower()))
                                            .append(getColumn(afn0c25.getNowdayaphaseactivepower()))
                                            .append(getColumn(afn0c25.getNowdaybphaseactivepower()))
                                            .append(getColumn(afn0c25.getNowdaycphaseactivepower()))
                                            .append(getColumn(afn0c25.getNowdaytotalreactivepower()))
                                            .append(getColumn(afn0c25.getNowdayaphasereactivepower()))
                                            .append(getColumn(afn0c25.getNowdaybphasereactivepower()))
                                            .append(getColumn(afn0c25.getNowdaycphasereactivepower()))
                                            .append(getColumn(afn0c25.getNowdaytotalpowerfactor()))
                                            .append(getColumn(afn0c25.getNowdayaphasepowerfactor()))
                                            .append(getColumn(afn0c25.getNowdaybphasepowerfactor()))
                                            .append(getColumn(afn0c25.getNowdaycphasepowerfactor()))
                                            .append(getColumn(afn0c25.getNowdayaphasevoltage()))
                                            .append(getColumn(afn0c25.getNowdaybphasevoltage()))
                                            .append(getColumn(afn0c25.getNowdaycphasevoltage()))
                                            .append(getColumn(afn0c25.getNowdayaphasecurrent()))
                                            .append(getColumn(afn0c25.getNowdaybphasecurrent()))
                                            .append(getColumn(afn0c25.getNowdaycphasecurrent()))
                                            .append(getColumn(afn0c25.getNowdayresidualcurrent()))
                                            .append(getColumn(afn0c25.getNowdaytotalapparentpower()))
                                            .append(getColumn(afn0c25.getNowdayaphaseapparentpower()))
                                            .append(getColumn(afn0c25.getNowdaybphaseapparentpower()))
                                            .append(getColumn(afn0c25.getNowdaycphaseapparentpower()))
                                            .append(getColumn(afn0c25.getUtpc()))
                                            .append(getColumn(afn0c25.getOverload()))
                                            .append(getEndColumn(DateUtils.dateToStr(afn0c25.getRecordtime())));
                                    i[10]++;
                                }
                                break;
                            case HM_UTPC:
                                if (i[11] != 0) sb_afn0c25_utpc.append(",");
                                DeviceHmUtpc hmUtpc = (DeviceHmUtpc) result.getData();
                                if (hmUtpc != null) {
                                    sb_afn0c25_utpc
                                            .append(getStartColumn(hmUtpc.getDtuaddress()))
                                            .append(getColumn(hmUtpc.getMaxutpc()))
                                            .append(getColumn(DateUtils.dateToStr(hmUtpc.getBegintime())))
                                            .append(getColumn(DateUtils.dateToStr(hmUtpc.getEndtime())))
                                            .append(getColumn(hmUtpc.getDuration()))
                                            .append(getEndColumn(hmUtpc.getPhase()));
                                    i[11]++;
                                }
                                break;
                            case HM_LOW_VOLTAGE:
                                if (i[12] != 0) sb_afn0c25_low_voltage.append(",");
                                DeviceHmLowVoltage hmLowVoltage = (DeviceHmLowVoltage) result.getData();
                                if (hmLowVoltage != null) {
                                    sb_afn0c25_low_voltage
                                            .append(getStartColumn(hmLowVoltage.getDtuaddress()))
                                            .append(getColumn(hmLowVoltage.getMinu()))
                                            .append(getColumn(hmLowVoltage.getPhase()))
                                            .append(getColumn(DateUtils.dateToStr(hmLowVoltage.getBegintime())))
                                            .append(getColumn(DateUtils.dateToStr(hmLowVoltage.getEndtime())))
                                            .append(getEndColumn(hmLowVoltage.getDuration()));
                                    i[12]++;
                                }
                                break;
                            case HM_OVERLOAD:
                                if (i[13] != 0) sb_afn0c25_overload.append(",");
                                DeviceHmOverload hmOverload = (DeviceHmOverload) result.getData();
                                if (hmOverload != null) {
                                    sb_afn0c25_low_voltage
                                            .append(getStartColumn(hmOverload.getDtuaddress()))
                                            .append(getColumn(hmOverload.getOverload()))
                                            .append(getColumn(DateUtils.dateToStr(hmOverload.getBegintime())))
                                            .append(getColumn(DateUtils.dateToStr(hmOverload.getEndtime())))
                                            .append(getEndColumn(hmOverload.getDuration()));
                                    i[13]++;
                                }
                                break;
                        }
                    }
                    if (i[0] > 0) mapper.insertBySql(sb1.toString());
                    if (i[1] > 0) mapper.insertBySql(sb2.toString());
                    if (i[2] > 0) {
                        sb3.append(" on duplicate key update DeviceId=values(DeviceId),")
                                .append("DataItem=values(DataItem), Value=values(Value), ")
                                .append("Unit=values(Unit), ")
                                .append("RecordTime=values(RecordTime), Description=values(Description)");
                        mapper.updateBySql(sb3.toString());
                    }
                    if (i[3] > 0) {
                        sb4.append(" on duplicate key update DeviceId=values(DeviceId),")
                                .append("DataItem=values(DataItem), Value=values(Value), ")
                                .append("Unit=values(Unit), ")
                                .append("RecordTime=values(RecordTime), Description=values(Description)");
                        mapper.updateBySql(sb4.toString());
                    }
                    if (i[4] > 0) mapper.insertBySql(sb5.toString());
                    if (i[5] > 0) mapper.insertBySql(sb6.toString());
                    if (i[6] > 0) mapper.insertBySql(sb7.toString());
                    if (i[7] > 0) {
                        sb8.append(" on duplicate key update deviceId=values(deviceId),flagAllAlarm=values(flagAllAlarm),")
                                .append("flagLightAlarm=values(flagLightAlarm),flagAudioAlarm=values(flagAudioAlarm),")
                                .append("flagTimelyTrial=values(flagTimelyTrial),flagLevelReturn=values(flagLevelReturn),")
                                .append("flagReclosing=values(flagReclosing),flagUnderVoltageAlarm=values(flagUnderVoltageAlarm),")
                                .append("flagUnderVoltageControl=values(flagUnderVoltageControl),flagOverVoltageAlarm=values(flagOverVoltageAlarm),")
                                .append("flagOverVoltageControl=values(flagOverVoltageControl),flagMissPhaseAlarm=values(flagMissPhaseAlarm),")
                                .append("flagMissPhaseControl=values(flagMissPhaseControl),flagOverCurrentAlarm=values(flagOverCurrentAlarm),")
                                .append("flagOverCurrentControl=values(flagOverCurrentControl),flagTrialSource=values(flagTrialSource),")
                                .append("flagMissEarthLineAlarm=values(flagMissEarthLineAlarm),flagMissEarthLineControl=values(flagMissEarthLineControl),")
                                .append("residualThresholdLevel=values(residualThresholdLevel),delayTimeLevel=values(delayTimeLevel),")
                                .append("residualAlarmTimeLevel=values(residualAlarmTimeLevel),recordTime=values(recordTime)");
                        mapper.updateBySql(sb8.toString());
                    }
                    if (i[8] > 0) {
                        sb9.append(" on duplicate key update AlarmId = values(AlarmId ), " +
                                "DeviceTableName = values(DeviceTableName), DeviceId = values(DeviceId )," +
                                " State = values(State ), AlarmContent = values(AlarmContent ), Ua = values(Ua )," +
                                " Ub = values(Ub ), Uc = values(Uc ), Ia = values(Ia ), Ib = values(Ib ), " +
                                "Ic = values(Ic ), Io = values(Io ), AlarmType = values(AlarmType ), " +
                                "AlarmPhase = values(AlarmPhase ), AlarmLevel = values(AlarmLevel ), " +
                                "DurationTime = values(DurationTime ), OutageTime = values(OutageTime ), " +
                                "PowerOnTime = values(PowerOnTime ), AlarmTime = values(AlarmTime ), " +
                                "WorkOrderId = values(WorkOrderId ), IsDeal = values(IsDeal ), " +
                                "DealTime = values(DealTime ), IsSendWorkOrder = values(IsSendWorkOrder)," +
                                " SendTime = values(SendTime ), SortCode = values(SortCode ), " +
                                "DeleteMark = values(DeleteMark ), EnabledMark = values(EnabledMark )," +
                                " Description = values(Description ), CreateDate = values(CreateDate )," +
                                " CreateUserId = values(CreateUserId ), CreateUserName = values(CreateUserName )," +
                                " ModifyDate = values(ModifyDate ), ModifyUserId = values(ModifyUserId ), " +
                                "ModifyUserName = values(ModifyUserName )");
                        mapper.updateBySql(sb9.toString());
                        LogUtils.info("insert or update device_alarm " + i[8], true);
                    }
                    if (i[9] > 0) {
                        sb10.append(" on duplicate key update Oid =values(Oid ) , DaId =values(DaId ) , Name =values(Name ) ," +
                                " Address =values(Address ) ,BaudRate =values(BaudRate), Checkdigit =values (Checkdigit)," +
                                "TerminalId =values(TerminalId ) , Level =values(Level ) ," +
                                " DtuId =values(DtuId ),Ua=values(Ua),Ub=values(Ub),Uc=values(Uc)," +
                                "Ia=values(Ia),Ib=values(Ib),Ic=values(Ic),Io=values(Io) ");
                        mapper.updateBySql(sb10.toString());
                        LogUtils.info("insert or update device_rcd " + i[9], true);
                    }
                    if (i[10] > 0) {
                        sb_afn0c25_real.append(" on duplicate key update DtuAddress =values(DtuAddress), " +
                                "ReadMeterTime =values(ReadMeterTime)," +
                                " NowDayTotalActivePower =values(NowDayTotalActivePower), " +
                                "NowDayAPhaseActivePower =values(NowDayAPhaseActivePower), " +
                                "NowDayBPhaseActivePower =values(NowDayBPhaseActivePower), " +
                                "NowDayCPhaseActivePower =values(NowDayCPhaseActivePower), " +
                                "NowDayTotalReactivePower =values(NowDayTotalReactivePower)," +
                                " NowDayAPhaseReactivePower =values(NowDayAPhaseReactivePower)," +
                                " NowDayBPhaseReactivePower =values(NowDayBPhaseReactivePower)," +
                                " NowDayCPhaseReactivePower =values(NowDayCPhaseReactivePower), " +
                                "NowDayTotalPowerFactor =values(NowDayTotalPowerFactor), " +
                                "NowDayAPhasePowerFactor =values(NowDayAPhasePowerFactor), " +
                                "NowDayBPhasePowerFactor =values(NowDayBPhasePowerFactor)," +
                                " NowDayCPhasePowerFactor =values(NowDayCPhasePowerFactor)," +
                                " NowDayAPhaseVoltage =values(NowDayAPhaseVoltage), " +
                                "NowDayBPhaseVoltage =values(NowDayBPhaseVoltage)," +
                                " NowDayCPhaseVoltage =values(NowDayCPhaseVoltage), " +
                                "NowDayAPhaseCurrent =values(NowDayAPhaseCurrent), " +
                                "NowDayBPhaseCurrent =values(NowDayBPhaseCurrent), " +
                                "NowDayCPhaseCurrent =values(NowDayCPhaseCurrent)," +
                                " NowDayResidualCurrent =values(NowDayResidualCurrent)," +
                                " NowDayTotalApparentPower =values(NowDayTotalApparentPower)," +
                                " NowDayAPhaseApparentPower =values(NowDayAPhaseApparentPower)," +
                                " NowDayBPhaseApparentPower =values(NowDayBPhaseApparentPower), " +
                                "NowDayCPhaseApparentPower =values(NowDayCPhaseApparentPower), " +
                                "UTPC =values(UTPC), Overload =values(Overload), " +
                                "RecordTime =values(RecordTime)");
                        mapper.updateBySql(sb_afn0c25_real.toString());
                        mapper.insertBySql(sb_afn0c25_history.toString());
                    }
                    if (i[11] > 0) mapper.insertBySql(sb_afn0c25_utpc.toString());
                    if (i[12] > 0) mapper.insertBySql(sb_afn0c25_low_voltage.toString());
                    if (i[13] > 0) mapper.insertBySql(sb_afn0c25_overload.toString());
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            } finally {
                if (sqlSession != null) sqlSession.close();
            }
        }


    }
}
