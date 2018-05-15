package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.business.dtu.DtuState;
import main.com.handu.scada.business.dtu.DtuStateResult;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;
import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceDtuOfflineDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_staterecord(RecordId,DeviceTableName,DeviceId,State,Description,UnLineTime) values ";
    }

    @Override
    protected String endSql() {
        return " on duplicate key update RecordId=values(RecordId),DeviceTableName=values(DeviceTableName),DeviceId=values(DeviceId),State=values(State),Description=values(Description),UnLineTime=values(UnLineTime)";
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            // List<DtuStateResult> results = dataList.stream().filter(e -> {
            //     DtuStateResult result = (DtuStateResult) e.getData();
            //     return StringsUtils.isEmpty(result.getDtuId());
            // }).map(e -> {
            //     DtuStateResult result = (DtuStateResult) e.getData();
            //     if (StringsUtils.isEmpty(result.getDtuId())) {
            //         result.setDtuId(getDtuIdByDtuAddress(result.getDtuAddress()));
            //     }
            //     return result;
            // }).collect(Collectors.toList());
            dataList.stream()
                    .map(e -> (DtuStateResult) e.getData())
                    .collect(Collectors.toList())
                    .stream()
                    .filter(result -> result != null && StringsUtils.isNotEmpty(result.getDtuAddress()))
                    .forEach(result -> {
                        if (result.getState() == DtuState.OFFLINE) {
                            sb.append(getStartColumn(result.getDtuAddress()))
                                    .append(getColumn(DeviceTableEnum.Device_Dtu.getTableName().toLowerCase()))
                                    .append(getColumn(result.getDtuAddress()))
                                    .append("'2','离线',")
                                    .append(getEndColumn(DateUtils.dateToStr(result.getTime())))
                                    .append(",");
                            num++;
                        }
                    });
        }
    }
}
