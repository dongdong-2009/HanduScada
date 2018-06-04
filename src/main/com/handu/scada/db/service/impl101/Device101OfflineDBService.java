package main.com.handu.scada.db.service.impl101;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.Device101Offline;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/28.
 */
public class Device101OfflineDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_101_offline (Id,DeviceId,Ip,Port,State,OfflineTime,Description) values ";
    }

    @Override
    protected String endSql() {
        return " on duplicate key update Id = values(Id),DeviceId = values(DeviceId),Ip=values(Ip),Port = values(Port)" +
                ",State=values(State),OfflineTime =values(OfflineTime),Description=values(Description)";
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData data : dataList) {
                Device101Offline offline = (Device101Offline) data.getData();
                if (offline != null) {
                    sb.append(getStartColumn(offline.getId()))
                            .append(getColumn(offline.getDeviceid()))
                            .append(getColumn(offline.getIp()))
                            .append(getColumn(offline.getPort()))
                            .append(getColumn(offline.getState()))
                            .append(getColumn(offline.getOfflinetime()))
                            .append(getEndColumn(offline.getDescription()))
                            .append(",");
                    num++;
                }
            }
        }
    }
}
