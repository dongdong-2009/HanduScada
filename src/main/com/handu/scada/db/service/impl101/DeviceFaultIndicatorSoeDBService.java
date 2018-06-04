package main.com.handu.scada.db.service.impl101;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceFaultindicatorSoe;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/06/01.
 */
public class DeviceFaultIndicatorSoeDBService extends BaseDBService implements IDBService {

    @Override
    protected String startSql() {
        return " insert into device_faultindicator_soe(SoeId,DeviceId,DataItem,Value,DataItemName,Unit,SoeTime,RecordTime,Description) values ";
    }

    @Override
    protected String endSql() {
        return null;
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData data : dataList) {
                DeviceFaultindicatorSoe soe = (DeviceFaultindicatorSoe) data.getData();
                if (soe != null) {
                    sb.append(getStartColumn(soe.getSoeid()))
                            .append(getColumn(soe.getDeviceid()))
                            .append(getColumn(soe.getDataitem()))
                            .append(getColumn(soe.getValue()))
                            .append(getColumn(soe.getDataitemname()))
                            .append(getColumn(soe.getUnit()))
                            .append(getColumn(soe.getSoetime()))
                            .append(getColumn(soe.getRecordtime()))
                            .append(getEndColumn(soe.getDescription()))
                            .append(",");
                    num++;
                }
            }
        }
    }

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }
}
