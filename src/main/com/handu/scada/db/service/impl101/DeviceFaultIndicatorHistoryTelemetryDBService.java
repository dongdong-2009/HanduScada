package main.com.handu.scada.db.service.impl101;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceFaultindicatorHistoryTelemetry;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/25.
 */
public class DeviceFaultIndicatorHistoryTelemetryDBService extends BaseDBService implements IDBService {

    @Override
    protected String startSql() {
        return " insert into device_faultIndicator_history_telemetry (Id,DeviceId, DataItem, Value, DataItemName, Unit, Description, RecordTime) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData data : dataList) {
                DeviceFaultindicatorHistoryTelemetry telemetry = (DeviceFaultindicatorHistoryTelemetry) data.getData();
                if (telemetry != null) {
                    sb.append(getStartColumn(telemetry.getId()))
                            .append(getColumn(telemetry.getDeviceid()))
                            .append(getColumn(telemetry.getDataitem()))
                            .append(getColumn(telemetry.getValue()))
                            .append(getColumn(telemetry.getDataitemname()))
                            .append(getColumn(telemetry.getUnit()))
                            .append(getColumn(telemetry.getDescription()))
                            .append(getEndColumn(telemetry.getRecordtime()))
                            .append(",");
                    num++;
                }
            }
        }
    }

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }
}
