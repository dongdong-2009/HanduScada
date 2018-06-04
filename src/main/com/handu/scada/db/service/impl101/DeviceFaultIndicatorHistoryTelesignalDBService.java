package main.com.handu.scada.db.service.impl101;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceFaultindicatorHistoryTelesignal;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/25.
 */
public class DeviceFaultIndicatorHistoryTelesignalDBService extends BaseDBService implements IDBService {

    @Override
    protected String startSql() {
        return " insert into device_faultIndicator_history_telesignal (Id,DeviceId, DataItem, Value, DataItemName, Unit, Description, RecordTime) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData data : dataList) {
                DeviceFaultindicatorHistoryTelesignal telesignal = (DeviceFaultindicatorHistoryTelesignal) data.getData();
                if (telesignal != null) {
                    sb.append(getStartColumn(telesignal.getId()))
                            .append(getColumn(telesignal.getDeviceid()))
                            .append(getColumn(telesignal.getDataitem()))
                            .append(getColumn(telesignal.getValue()))
                            .append(getColumn(telesignal.getDataitemname()))
                            .append(getColumn(telesignal.getUnit()))
                            .append(getColumn(telesignal.getDescription()))
                            .append(getEndColumn(telesignal.getRecordtime()))
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
