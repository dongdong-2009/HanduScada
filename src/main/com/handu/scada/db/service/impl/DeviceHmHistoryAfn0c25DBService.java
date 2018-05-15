package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceHmRealAfn0c25;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;
import main.com.handu.scada.utils.DateUtils;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceHmHistoryAfn0c25DBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);

    }

    @Override
    protected String startSql() {
        return " INSERT INTO device_hm_history_afn0c25( DtuAddress, ReadMeterTime, NowDayTotalActivePower," +
                " NowDayAPhaseActivePower, NowDayBPhaseActivePower, NowDayCPhaseActivePower, NowDayTotalReactivePower," +
                " NowDayAPhaseReactivePower, NowDayBPhaseReactivePower, NowDayCPhaseReactivePower," +
                " NowDayTotalPowerFactor, NowDayAPhasePowerFactor, NowDayBPhasePowerFactor, NowDayCPhasePowerFactor," +
                " NowDayAPhaseVoltage, NowDayBPhaseVoltage, NowDayCPhaseVoltage, NowDayAPhaseCurrent, " +
                " NowDayBPhaseCurrent, NowDayCPhaseCurrent, NowDayResidualCurrent, NowDayTotalApparentPower," +
                " NowDayAPhaseApparentPower, NowDayBPhaseApparentPower, NowDayCPhaseApparentPower, UTPC, Overload, " +
                " RecordTime) VALUES ";
    }

    @Override
    protected String endSql() {
        return "";
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceHmRealAfn0c25 afn0c25 = (DeviceHmRealAfn0c25) deviceData.getData();
                if (afn0c25 != null) {
                    sb
                            .append(getStartColumn(afn0c25.getDtuaddress()))
                            .append(getColumn(DateUtils.dateToStr(afn0c25.getReadmetertime())))
                            .append(getColumn(afn0c25.getNowdaytotalactivepower()))
                            .append(getColumn(afn0c25.getNowdayaphaseactivepower()))
                            .append(getColumn(afn0c25.getNowdaybphaseactivepower()))
                            .append(getColumn(afn0c25.getNowdaycphaseactivepower()))
                            .append(getColumn(afn0c25.getNowdaytotalreactivepower()))
                            .append(getColumn(afn0c25.getNowdayaphasereactivepower()))
                            .append(getColumn(afn0c25.getNowdaybphasereactivepower()))
                            .append(getColumn(afn0c25.getNowdaycphasereactivepower()))
                            .append(getColumn(afn0c25.getNowdaytotalpowerfactor()))
                            .append(getColumn(afn0c25.getNowdayaphasepowerfactor()))
                            .append(getColumn(afn0c25.getNowdaybphasepowerfactor()))
                            .append(getColumn(afn0c25.getNowdaycphasepowerfactor()))
                            .append(getColumn(afn0c25.getNowdayaphasevoltage()))
                            .append(getColumn(afn0c25.getNowdaybphasevoltage()))
                            .append(getColumn(afn0c25.getNowdaycphasevoltage()))
                            .append(getColumn(afn0c25.getNowdayaphasecurrent()))
                            .append(getColumn(afn0c25.getNowdaybphasecurrent()))
                            .append(getColumn(afn0c25.getNowdaycphasecurrent()))
                            .append(getColumn(afn0c25.getNowdayresidualcurrent()))
                            .append(getColumn(afn0c25.getNowdaytotalapparentpower()))
                            .append(getColumn(afn0c25.getNowdayaphaseapparentpower()))
                            .append(getColumn(afn0c25.getNowdaybphaseapparentpower()))
                            .append(getColumn(afn0c25.getNowdaycphaseapparentpower()))
                            .append(getColumn(afn0c25.getUtpc()))
                            .append(getColumn(afn0c25.getOverload()))
                            .append(getEndColumn(DateUtils.dateToStr(afn0c25.getRecordtime())))
                            .append(",");
                    num++;
                }
            }
        }
    }
}
