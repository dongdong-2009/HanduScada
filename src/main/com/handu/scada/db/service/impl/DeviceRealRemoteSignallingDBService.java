package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceRealRemotesignalling;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceRealRemoteSignallingDBService extends BaseDBService implements IDBService {
    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_real_remotesignalling (DeviceId, DataItem, Value, Unit, RecordTime, Description ) values ";
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
                DeviceRealRemotesignalling remotesignalling = (DeviceRealRemotesignalling) deviceData.getData();
                if (remotesignalling != null) {
                    sb.append("('")
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
                            .append("')")
                            .append(",");
                    num++;
                }
            }
        }
    }
}
