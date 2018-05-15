package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceHmLowVoltage;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;
import main.com.handu.scada.utils.DateUtils;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceHmLowVoltageDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_hm_low_voltage (dtuAddress, minU, phase, beginTime, endTime, duration) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceHmLowVoltage hmLowVoltage = (DeviceHmLowVoltage) deviceData.getData();
                if (hmLowVoltage != null) {
                    sb
                            .append(getStartColumn(hmLowVoltage.getDtuaddress()))
                            .append(getColumn(hmLowVoltage.getMinu()))
                            .append(getColumn(hmLowVoltage.getPhase()))
                            .append(getColumn(DateUtils.dateToStr(hmLowVoltage.getBegintime())))
                            .append(getColumn(DateUtils.dateToStr(hmLowVoltage.getEndtime())))
                            .append(getEndColumn(hmLowVoltage.getDuration()))
                            .append(",");
                    num++;
                }
            }
        }
    }
}
