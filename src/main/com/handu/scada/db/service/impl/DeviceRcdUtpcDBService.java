package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceRcdutpc;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceRcdUtpcDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_rcdutpc (utpcId,deviceId,maxutpc,beginTime,endTime,duration,phase) values ";
    }

    @Override
    protected String endSql() {
        return "";
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceRcdutpc rcdutpc = (DeviceRcdutpc) deviceData.getData();
                if (rcdutpc != null) {
                    sb.append("('")
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
                            .append("')")
                            .append(",");

                    num++;
                }
            }
        }
    }
}
