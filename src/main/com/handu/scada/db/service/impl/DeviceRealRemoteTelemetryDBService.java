package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceRealRemotetelemetry;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceRealRemoteTelemetryDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_real_remotetelemetry (DeviceId, DataItem, Value, Unit, RecordTime, Description ) values ";
    }

    @Override
    protected String endSql() {
        return " on duplicate key update DeviceId=values(DeviceId)," +
                "DataItem=values(DataItem), Value=values(Value), " +
                "Unit=values(Unit), " +
                "RecordTime=values(RecordTime), Description=values(Description)";
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceRealRemotetelemetry remotetelemetry = (DeviceRealRemotetelemetry) deviceData.getData();
                if (remotetelemetry != null) {
                    sb.append("('")
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
                            .append("')")
                            .append(",");
                    num++;
                }
            }
        }
    }
}
