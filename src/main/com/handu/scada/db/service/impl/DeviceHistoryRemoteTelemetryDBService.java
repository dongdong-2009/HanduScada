package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceHistoryRemotetelemetry;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceHistoryRemoteTelemetryDBService extends BaseDBService implements IDBService {
    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_history_remotetelemetry (RemoteTelemetryId, DeviceId, DataItem, Value, Unit, RecordTime, Description ) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceHistoryRemotetelemetry deviceHistoryRemotetelemetry = (DeviceHistoryRemotetelemetry) deviceData.getData();
                if (deviceHistoryRemotetelemetry != null) {
                    sb.append("(")
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
                            .append("')")
                            .append(",");
                    num++;
                }
            }
        }
    }
}
