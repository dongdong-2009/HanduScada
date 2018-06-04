package main.com.handu.scada.db.service.impl101;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.Device101Online;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/28.
 */
public class Device101OnlineDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_101_online (Id,DeviceId,Ip,Port,State,OnlineTime,Description) values ";
    }

    @Override
    protected String endSql() {
        return " on duplicate key update Id = values(Id),DeviceId = values(DeviceId),Ip=values(Ip),Port = values(Port)" +
                ",State=values(State),OnlineTime =values(OnlineTime),Description=values(Description)";
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData data : dataList) {
                Device101Online online = (Device101Online) data.getData();
                if (online != null) {
                    sb.append(getStartColumn(online.getId()))
                            .append(getColumn(online.getDeviceid()))
                            .append(getColumn(online.getIp()))
                            .append(getColumn(online.getPort()))
                            .append(getColumn(online.getState()))
                            .append(getColumn(online.getOnlinetime()))
                            .append(getEndColumn(online.getDescription()))
                            .append(",");
                    num++;
                }
            }
        }
    }
}
