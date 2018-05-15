package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceHistoryRemotesignalling;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceHistoryRemoteSignallingDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_history_remotesignalling (RemoteSignallingId, DeviceId, DataItem, Value, Unit, RecordTime, Description ) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceHistoryRemotesignalling deviceHistoryRemotesignalling = (DeviceHistoryRemotesignalling) deviceData.getData();
                if (deviceHistoryRemotesignalling != null) {
                    sb.append("(")
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
                            .append("')")
                            .append(",");
                    num++;
                }
            }
        }
    }
}
