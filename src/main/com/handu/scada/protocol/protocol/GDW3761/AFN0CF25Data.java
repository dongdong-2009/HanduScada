package main.com.handu.scada.protocol.protocol.GDW3761;

import main.com.handu.scada.db.bean.DeviceHmRealAfn0c25;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.protocol.protocol.GDW3761.A.*;
import main.com.handu.scada.protocol.protocol.GDW3761.Facility.*;
import main.com.handu.scada.utils.DateUtils;

/**
 * Created by 柳梦 on 2018/04/26.
 */
public class AFN0CF25Data extends BaseAFNData {

    private DeviceHmRealAfn0c25 realAfn0c25 = new DeviceHmRealAfn0c25();

    private A15 timeToReadMeter;
    private NowDayActivePower nowDayActivePower;
    private NowDayReactivePower nowDayReactivePower;
    private NowDayPowerFactor nowDayPowerFactor;
    private NowDayPhaseVoltage nowDayPhaseVoltage;
    private NowDayPhaseCurrent nowDayPhaseCurrent;
    private NowDayApparentPower nowDayApparentPower;

    public AFN0CF25Data(byte[] dataOfAFN) {

        byte[] bytes = new byte[A15.LENGTH];
        System.arraycopy(dataOfAFN, dataLength, bytes, 0, bytes.length);
        timeToReadMeter = new A15();
        timeToReadMeter.setDataOfAFN(bytes);
        timeToReadMeter.setName("抄表时间");
        timeToReadMeter.setUnit("年月日时分");
        timeToReadMeter.setItemName("TimeToReadMeter");
        realAfn0c25.setReadmetertime(DateUtils.strToDate((String) timeToReadMeter.value));
        realAfn0c25.setRecordtime(DateUtils.getNowSqlDateTime());
        dataAttrs.add(new DataAttr() {{
            setValue(timeToReadMeter.value);
            setName(timeToReadMeter.itemName);
            setUnit(timeToReadMeter.unit);
            setCnname(timeToReadMeter.name);
            setDateType(RemoteType.YC);
            setDtime(DateUtils.getNowSqlDateTime());
        }});
        dataLength += A15.LENGTH;

        bytes = new byte[A09.LENGTH * (phaseNumber + 1)];
        System.arraycopy(dataOfAFN, dataLength, bytes, 0, bytes.length);
        nowDayActivePower = new NowDayActivePower(bytes);
        dataLength += bytes.length;

        bytes = new byte[A09.LENGTH * (phaseNumber + 1)];
        System.arraycopy(dataOfAFN, dataLength, bytes, 0, bytes.length);
        nowDayReactivePower = new NowDayReactivePower(bytes);
        dataLength += bytes.length;

        bytes = new byte[A05.LENGTH * (phaseNumber + 1)];
        System.arraycopy(dataOfAFN, dataLength, bytes, 0, bytes.length);
        nowDayPowerFactor = new NowDayPowerFactor(bytes);
        dataLength += bytes.length;

        bytes = new byte[A07.LENGTH * phaseNumber];
        System.arraycopy(dataOfAFN, dataLength, bytes, 0, bytes.length);
        nowDayPhaseVoltage = new NowDayPhaseVoltage(bytes);
        dataLength += bytes.length;

        bytes = new byte[A25.LENGTH * (phaseNumber + 1)];
        System.arraycopy(dataOfAFN, dataLength, bytes, 0, bytes.length);
        nowDayPhaseCurrent = new NowDayPhaseCurrent(bytes);
        dataLength += bytes.length;

        bytes = new byte[A09.LENGTH * (phaseNumber + 1)];
        System.arraycopy(dataOfAFN, dataLength, bytes, 0, bytes.length);
        nowDayApparentPower = new NowDayApparentPower(bytes);
        dataLength += bytes.length;

        if (nowDayActivePower != null) {
            realAfn0c25.setNowdaytotalactivepower((Double) nowDayActivePower.NowDayTotalActivePower.value);
            realAfn0c25.setNowdayaphaseactivepower((Double) nowDayActivePower.NowDayPhaseActivePower[0].value);
            realAfn0c25.setNowdaybphaseactivepower((Double) nowDayActivePower.NowDayPhaseActivePower[1].value);
            realAfn0c25.setNowdaycphaseactivepower((Double) nowDayActivePower.NowDayPhaseActivePower[2].value);
            dataAttrs.add(new DataAttr() {{
                setValue(nowDayActivePower.NowDayTotalActivePower.value);
                setName(nowDayActivePower.NowDayTotalActivePower.itemName);
                setUnit(nowDayActivePower.NowDayTotalActivePower.unit);
                setCnname(nowDayActivePower.NowDayTotalActivePower.name);
                setDateType(RemoteType.YC);
                setDtime(DateUtils.getNowSqlDateTime());
            }});
            for (A09 a09 : nowDayActivePower.NowDayPhaseActivePower) {
                dataAttrs.add(new DataAttr() {{
                    setValue(a09.value);
                    setName(a09.itemName);
                    setUnit(a09.unit);
                    setCnname(a09.name);
                    setDateType(RemoteType.YC);
                    setDtime(DateUtils.getNowSqlDateTime());
                }});
            }
        }
        if (nowDayReactivePower != null) {
            realAfn0c25.setNowdaytotalreactivepower((Double) nowDayReactivePower.NowDayTotalReactivePower.value);
            realAfn0c25.setNowdayaphasereactivepower((Double) nowDayReactivePower.NowDayPhaseReactivePower[0].value);
            realAfn0c25.setNowdaybphasereactivepower((Double) nowDayReactivePower.NowDayPhaseReactivePower[1].value);
            realAfn0c25.setNowdaycphasereactivepower((Double) nowDayReactivePower.NowDayPhaseReactivePower[2].value);
            dataAttrs.add(new DataAttr() {{
                setValue(nowDayReactivePower.NowDayTotalReactivePower.value);
                setName(nowDayReactivePower.NowDayTotalReactivePower.itemName);
                setUnit(nowDayReactivePower.NowDayTotalReactivePower.unit);
                setCnname(nowDayReactivePower.NowDayTotalReactivePower.name);
                setDateType(RemoteType.YC);
                setDtime(DateUtils.getNowSqlDateTime());
            }});
            for (A09 a09 : nowDayReactivePower.NowDayPhaseReactivePower) {
                dataAttrs.add(new DataAttr() {{
                    setValue(a09.value);
                    setName(a09.itemName);
                    setUnit(a09.unit);
                    setCnname(a09.name);
                    setDateType(RemoteType.YC);
                    setDtime(DateUtils.getNowSqlDateTime());
                }});
            }
        }
        if (nowDayPowerFactor != null) {
            realAfn0c25.setNowdaytotalpowerfactor((Double) nowDayPowerFactor.NowDayTotalPowerFactor.value);
            realAfn0c25.setNowdayaphasepowerfactor((Double) nowDayPowerFactor.NowDayPhasePowerFactor[0].value);
            realAfn0c25.setNowdaybphasepowerfactor((Double) nowDayPowerFactor.NowDayPhasePowerFactor[1].value);
            realAfn0c25.setNowdaycphasepowerfactor((Double) nowDayPowerFactor.NowDayPhasePowerFactor[2].value);
            dataAttrs.add(new DataAttr() {{
                setValue(nowDayPowerFactor.NowDayTotalPowerFactor.value);
                setName(nowDayPowerFactor.NowDayTotalPowerFactor.itemName);
                setUnit(nowDayPowerFactor.NowDayTotalPowerFactor.unit);
                setCnname(nowDayPowerFactor.NowDayTotalPowerFactor.name);
                setDateType(RemoteType.YC);
                setDtime(DateUtils.getNowSqlDateTime());
            }});
            for (A05 a05 : nowDayPowerFactor.NowDayPhasePowerFactor) {
                dataAttrs.add(new DataAttr() {{
                    setValue(a05.value);
                    setName(a05.itemName);
                    setUnit(a05.unit);
                    setCnname(a05.name);
                    setDateType(RemoteType.YC);
                    setDtime(DateUtils.getNowSqlDateTime());
                }});
            }
        }
        if (nowDayPhaseVoltage != null) {
            realAfn0c25.setNowdayaphasevoltage((Double) nowDayPhaseVoltage.PhaseVoltage[0].value);
            realAfn0c25.setNowdaybphasevoltage((Double) nowDayPhaseVoltage.PhaseVoltage[1].value);
            realAfn0c25.setNowdaycphasevoltage((Double) nowDayPhaseVoltage.PhaseVoltage[2].value);
            for (A07 a07 : nowDayPhaseVoltage.PhaseVoltage) {
                dataAttrs.add(new DataAttr() {{
                    setValue(a07.value);
                    setName(a07.itemName);
                    setUnit(a07.unit);
                    setCnname(a07.name);
                    setGroup(DeviceCmdTypeEnum.Voltage.name());
                    setDateType(RemoteType.YC);
                    setDtime(DateUtils.getNowSqlDateTime());
                }});
            }
        }
        if (nowDayPhaseCurrent != null) {
            realAfn0c25.setNowdayaphasecurrent((Double) nowDayPhaseCurrent.PhaseCurrent[0].value);
            realAfn0c25.setNowdaybphasecurrent((Double) nowDayPhaseCurrent.PhaseCurrent[1].value);
            realAfn0c25.setNowdaycphasecurrent((Double) nowDayPhaseCurrent.PhaseCurrent[2].value);
            realAfn0c25.setNowdayresidualcurrent((Double) nowDayPhaseCurrent.NowDayResidualCurrent.value);
            realAfn0c25.setUtpc((Double) nowDayPhaseCurrent.UTPC.value);

            for (A25 a25 : nowDayPhaseCurrent.PhaseCurrent) {
                dataAttrs.add(new DataAttr() {{
                    setValue(a25.value);
                    setName(a25.itemName);
                    setUnit(a25.unit);
                    setCnname(a25.name);
                    setGroup(DeviceCmdTypeEnum.Current.name());
                    setDateType(RemoteType.YC);
                    setDtime(DateUtils.getNowSqlDateTime());
                }});
            }
            dataAttrs.add(new DataAttr() {{
                setValue(nowDayPhaseCurrent.NowDayResidualCurrent.value);
                setName(nowDayPhaseCurrent.NowDayResidualCurrent.itemName);
                setUnit(nowDayPhaseCurrent.NowDayResidualCurrent.unit);
                setCnname(nowDayPhaseCurrent.NowDayResidualCurrent.name);
                setDateType(RemoteType.YC);
                setDtime(DateUtils.getNowSqlDateTime());
            }});
            dataAttrs.add(new DataAttr() {{
                setValue(nowDayPhaseCurrent.UTPC.value);
                setName(nowDayPhaseCurrent.UTPC.itemName);
                setUnit(nowDayPhaseCurrent.UTPC.unit);
                setCnname(nowDayPhaseCurrent.UTPC.name);
                setDateType(RemoteType.YC);
                setDtime(DateUtils.getNowSqlDateTime());
            }});
        }
        if (nowDayApparentPower != null) {
            realAfn0c25.setNowdaytotalapparentpower((Double) nowDayApparentPower.NowDayTotalApparentPower.value);
            realAfn0c25.setNowdayaphaseapparentpower((Double) nowDayApparentPower.NowDayPhaseApparentPower[0].value);
            realAfn0c25.setNowdaybphaseapparentpower((Double) nowDayApparentPower.NowDayPhaseApparentPower[1].value);
            realAfn0c25.setNowdaycphaseapparentpower((Double) nowDayApparentPower.NowDayPhaseApparentPower[2].value);
            dataAttrs.add(new DataAttr() {{
                setValue(nowDayApparentPower.NowDayTotalApparentPower.value);
                setName(nowDayApparentPower.NowDayTotalApparentPower.itemName);
                setUnit(nowDayApparentPower.NowDayTotalApparentPower.unit);
                setCnname(nowDayApparentPower.NowDayTotalApparentPower.name);
                setDateType(RemoteType.YC);
                setDtime(DateUtils.getNowSqlDateTime());
            }});
            for (A09 a09 : nowDayApparentPower.NowDayPhaseApparentPower) {
                dataAttrs.add(new DataAttr() {{
                    setValue(a09.value);
                    setName(a09.itemName);
                    setUnit(a09.unit);
                    setCnname(a09.name);
                    setDateType(RemoteType.YC);
                    setDtime(DateUtils.getNowSqlDateTime());
                }});
            }
        }
        if (nowDayActivePower != null && nowDayApparentPower != null) {
            double tp = (double) nowDayApparentPower.NowDayTotalApparentPower.value;
            double ap = (double) nowDayActivePower.NowDayTotalActivePower.value;
            double value = 0;
            if (tp != 0) {
                value = tp / ap * 1d;
            }
            realAfn0c25.setOverload(value);
            double finalValue = value;
            dataAttrs.add(new DataAttr() {{
                setValue(finalValue);
                setCnname("过载重载");
                setUnit("%");
                setName("Overload");
                setDateType(RemoteType.YC);
                setDtime(DateUtils.getNowSqlDateTime());
            }});
        }
        dataAttrs.add(new DataAttr() {{
            setValue(realAfn0c25);
            setCnname("台区总表afn0c25数据");
            setUnit("");
            setName("afn0c25");
            setGroup(DeviceCmdTypeEnum.HM_AFN0C25.name());
            setDateType(RemoteType.YC);
            setDtime(DateUtils.getNowSqlDateTime());
        }});
    }

    @Override
    public String toString() {
        return "AFN0CF25Data{" +
                "timeToReadMeter=" + timeToReadMeter +
                ", nowDayActivePower=" + nowDayActivePower +
                ", nowDayReactivePower=" + nowDayReactivePower +
                ", nowDayPowerFactor=" + nowDayPowerFactor +
                ", nowDayPhaseVoltage=" + nowDayPhaseVoltage +
                ", nowDayPhaseCurrent=" + nowDayPhaseCurrent +
                ", nowDayApparentPower=" + nowDayApparentPower +
                '}';
    }
}
