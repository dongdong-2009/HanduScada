package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceHmUtpc;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;
import main.com.handu.scada.utils.DateUtils;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceHmUtpcDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_hm_utpc (dtuAddress, maxUtpc, beginTime, endTime, duration, phase) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceHmUtpc hmUtpc = (DeviceHmUtpc) deviceData.getData();
                if (hmUtpc != null) {
                    sb
                            .append(getStartColumn(hmUtpc.getDtuaddress()))
                            .append(getColumn(hmUtpc.getMaxutpc()))
                            .append(getColumn(DateUtils.dateToStr(hmUtpc.getBegintime())))
                            .append(getColumn(DateUtils.dateToStr(hmUtpc.getEndtime())))
                            .append(getColumn(hmUtpc.getDuration()))
                            .append(getEndColumn(hmUtpc.getPhase()))
                            .append(",");
                    num++;
                }
            }
        }
    }
}
