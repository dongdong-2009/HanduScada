package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceConcentratorLastHeartbeatTime;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;
import main.com.handu.scada.utils.LogUtils;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceConcentratorLastHeartbeatTimeDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_concentrator_last_heartbeat_time (dtuId,lastHeartbeatTime,recordTime) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceConcentratorLastHeartbeatTime time = (DeviceConcentratorLastHeartbeatTime) deviceData.getData();
                if (time != null) {
                    String dtuId = getDtuIdByDtuAddress(time.getDtuAddress());
                    if (dtuId != null) {
                        sb.append("('")
                                .append(dtuId)
                                .append("','")
                                .append(time.getLastheartbeattime())
                                .append("','")
                                .append(time.getRecordtime())
                                .append("')")
                                .append(",");
                        num++;
                    } else {
                        LogUtils.error("not find dtuId by dtuAddress " + time.getDtuAddress());
                    }
                }
            }
        }
    }


}
