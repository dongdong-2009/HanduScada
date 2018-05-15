package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceHmOverload;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;
import main.com.handu.scada.utils.DateUtils;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 * 台区总表重过载
 */
public class DeviceHmOverloadDBService extends BaseDBService implements IDBService {

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceHmOverload hmOverload = (DeviceHmOverload) deviceData.getData();
                if (hmOverload != null) {
                    sb.append(getStartColumn(hmOverload.getDtuaddress()))
                            .append(getColumn(hmOverload.getOverload()))
                            .append(getColumn(DateUtils.dateToStr(hmOverload.getBegintime())))
                            .append(getColumn(DateUtils.dateToStr(hmOverload.getEndtime())))
                            .append(getEndColumn(hmOverload.getDuration()))
                            .append(",");
                    num++;
                }
            }
        }
    }


    @Override
    public int submit(CommonMapper mapper, List<DeviceData> dataList) {
        return super.submit(mapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_hm_overload (dtuAddress, overload, beginTime, endTime, duration) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }
}