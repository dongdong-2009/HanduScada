package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceLowvoltage;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceRcdLowvoltageDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_lowvoltage (lowUId,phase,beginTime,endTime,deviceId,duration,minU) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceLowvoltage lowvoltage = (DeviceLowvoltage) deviceData.getData();
                if (lowvoltage != null) {
                    sb.append("('")
                            .append(lowvoltage.getLowuid())
                            .append("','")
                            .append(lowvoltage.getPhase())
                            .append("','")
                            .append(lowvoltage.getBegintime())
                            .append("','")
                            .append(lowvoltage.getEndtime())
                            .append("','")
                            .append(lowvoltage.getDeviceid())
                            .append("',")
                            .append(lowvoltage.getDuration())
                            .append(",")
                            .append(lowvoltage.getMinu())
                            .append(")");
                    num++;
                }
            }
        }
    }
}
