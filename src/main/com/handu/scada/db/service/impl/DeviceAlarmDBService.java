package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceAlarm;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;
import main.com.handu.scada.utils.DateUtils;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceAlarmDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_alarm(AlarmId , DeviceTableName, DeviceId , State , AlarmContent ," +
                " Ua , Ub , Uc , Ia , Ib , Ic , Io , AlarmType , AlarmPhase , AlarmLevel , DurationTime , " +
                "OutageTime , PowerOnTime , AlarmTime , WorkOrderId , IsDeal , DealTime , IsSendWorkOrder," +
                " SendTime , SortCode , DeleteMark , EnabledMark , Description , CreateDate , CreateUserId ," +
                " CreateUserName , ModifyDate , ModifyUserId , ModifyUserName) values ";
    }

    @Override
    protected String endSql() {
        return (" on duplicate key update AlarmId = values(AlarmId ), " +
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
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceAlarm alarm = (DeviceAlarm) deviceData.getData();
                if (alarm != null) {
                    sb.append("('")
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
                            .append(")")
                            .append(",");
                    num++;
                }
            }
        }
    }
}
