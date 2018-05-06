package main.com.handu.scada.protocol.protocol.DLT645.LP1997;

import main.com.handu.scada.enums.DeviceGroup;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.base.BaseDataAnalyze;
import main.com.handu.scada.protocol.base.BaseIdentifyCodeDesc;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.LPState;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.DLT645.TripEventRecord;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by 柳梦 on 2018/04/24.
 */
public class DataAnalyze extends BaseDataAnalyze {

    private LPState lpState;

    private BaseIdentifyCodeDesc identifyCodeDesc;

    @Override
    public List<DataAttr> getData(BaseIdentifyCodeDesc item) {
        if (item == null) return null;
        this.cmdType = item.cmdType;
        this.identifyCodeDesc = item;
        try {
            if (item.data == null) return null;
            if (item.data.length == 0) return null;
            item.data = (DICodeLP1997.decrease33(item.data));
            dataAttrs = new ArrayList<>();
            switch (item.cmdType) {
                case VoltageCurrentAndResidualCurrent:
                case BroadcastTime:
                case WritePostalAddress:
                case ExecuteON:
                case ExecuteOFF:
                case SwitchTrip:
                case WriteControlWordParameterModule:
                    parseVoltageCurrentAndResidualCurrent();
                    break;
                case ReadClock:
                    parseReadClock();
                    break;
                case ReadPostalAddress:
                    break;
                case RunState:
                case ProtectorTripEventRecord:
                    parseRunStateAndTripEvent();
                    break;
                case ReadControlWordParameterModule:
                    parseReadControlWordParameterModule();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            ExceptionHandler.print(e);
        }
        return dataAttrs;
    }

    /**
     * 电压电流和剩余电流
     */
    private void parseVoltageCurrentAndResidualCurrent() {
        byte data[] = identifyCodeDesc.data;
        if (data != null && identifyCodeDesc.data.length == 15) {
            itemNames = new String[]{"content", "Ua", "Ub", "Uc", "Ia", "Ib", "Ic", "Io", "UTPC"};
            itemCnNames = new String[]{"内容", "A相电压", "B相电压", "C相电压", "A相电流", "B相电流", "C相电流", "剩余电流", "三相不平衡"};
            int index = 0;

            //状态
            int state = data[index];
            lpState = getLpState(state);
            index += 1;

            //三项电压
            BCD1 = HexUtils.bcdByteToInt(data[index]);
            BCD2 = HexUtils.bcdByteToInt(data[index + 1]);
            value = BCD2 * 100 + BCD1;
            dataAttr = new DataAttr() {{
                setName(itemNames[1]);
                setValue(value);
                setCnname(itemCnNames[1]);
                setGroup(DeviceCmdTypeEnum.Voltage.name());
                setUnit(identifyCodeDesc.unit.split(",")[0]);
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
            }};
            dataAttrs.add(dataAttr);
            index += 2;

            BCD1 = HexUtils.bcdByteToInt(data[index]);
            BCD2 = HexUtils.bcdByteToInt(data[index + 1]);
            value = BCD2 * 100 + BCD1;
            dataAttr = new DataAttr() {{
                setName(itemNames[2]);
                setValue(value);
                setGroup(DeviceCmdTypeEnum.Voltage.name());
                setCnname(itemCnNames[2]);
                setUnit(identifyCodeDesc.unit.split(",")[0]);
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
            }};
            dataAttrs.add(dataAttr);
            index += 2;

            BCD1 = HexUtils.bcdByteToInt(data[index]);
            BCD2 = HexUtils.bcdByteToInt(data[index + 1]);
            value = BCD2 * 100 + BCD1;
            dataAttr = new DataAttr() {{
                setName(itemNames[3]);
                setValue(value);
                setGroup(DeviceCmdTypeEnum.Voltage.name());
                setCnname(itemCnNames[3]);
                setUnit(identifyCodeDesc.unit.split(",")[0]);
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
            }};
            dataAttrs.add(dataAttr);
            index += 2;

            //三项电流
            BCD1 = HexUtils.bcdByteToInt(data[index]);
            BCD2 = HexUtils.bcdByteToInt(data[index + 1]);
            value = BCD2 * 100 + BCD1;
            double IA = Double.parseDouble(String.valueOf(value));
            dataAttr = new DataAttr() {{
                setName(itemNames[4]);
                setValue(value);
                setGroup(DeviceCmdTypeEnum.Current.name());
                setCnname(itemCnNames[4]);
                setUnit(identifyCodeDesc.unit.split(",")[1]);
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
            }};
            dataAttrs.add(dataAttr);
            index += 2;

            BCD1 = HexUtils.bcdByteToInt(data[index]);
            BCD2 = HexUtils.bcdByteToInt(data[index + 1]);
            double IB = Double.parseDouble(String.valueOf(value));
            value = BCD2 * 100 + BCD1;
            dataAttr = new DataAttr() {{
                setName(itemNames[5]);
                setValue(value);
                setGroup(DeviceCmdTypeEnum.Current.name());
                setCnname(itemCnNames[5]);
                setUnit(identifyCodeDesc.unit.split(",")[1]);
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
            }};
            dataAttrs.add(dataAttr);
            index += 2;

            BCD1 = HexUtils.bcdByteToInt(data[index]);
            BCD2 = HexUtils.bcdByteToInt(data[index + 1]);
            value = BCD2 * 100 + BCD1;
            double IC = Double.parseDouble(String.valueOf(value));
            dataAttr = new DataAttr() {{
                setName(itemNames[6]);
                setValue(value);
                setGroup(DeviceCmdTypeEnum.Current.name());
                setCnname(itemCnNames[6]);
                setUnit(identifyCodeDesc.unit.split(",")[1]);
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
            }};
            dataAttrs.add(dataAttr);
            index += 2;

            double max = 0;
            double min = 0;
            double utpc;

            Optional<Double> maxT = Stream.of(IA, IB, IC).max((o1, o2) -> o1 < o2 ? -1 : Objects.equals(o1, o2) ? 0 : 1);
            if (maxT.isPresent()) {
                max = maxT.get();
            }
            Optional<Double> minT = Stream.of(IA, IB, IC).max((o1, o2) -> o1 > o2 ? -1 : Objects.equals(o1, o2) ? 0 : 1);
            if (minT.isPresent()) {
                min = minT.get();
            }
            if (min == 0) {
                utpc = 1d;
            } else if (max == 0) {
                utpc = 0d;
            } else {
                utpc = ((max - min) / max * 1d);
            }
            dataAttr = new DataAttr() {{
                setName(itemNames[8]);
                setValue(utpc);
                setGroup(itemNames[8]);
                setCnname(itemCnNames[8]);
                setUnit("%");
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
            }};

            //剩余电流
            BCD1 = HexUtils.bcdByteToInt(data[index]);
            BCD2 = HexUtils.bcdByteToInt(data[index + 1]);
            value = BCD2 * 100 + BCD1;
            dataAttr = new DataAttr() {{
                setName(itemNames[7]);
                setValue(value);
                setGroup(cmdType.name());
                setCnname(itemCnNames[7]);
                setUnit(identifyCodeDesc.unit.split(",")[2]);
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
            }};
            dataAttrs.add(dataAttr);

            //分合状态
            dataAttr = new DataAttr() {{
                setName("RunState");
                setValue(lpState.getValue());
                setCnname(lpState.getDescription());
                setUnit("分合状态");
                setGroup(DeviceCmdTypeEnum.RunState.name());
                setDtime(DateUtils.getNowSqlDateTime());
                setDateType(RemoteType.YX);
            }};
            dataAttrs.add(dataAttr);
        }
    }

    /**
     * 时钟
     */
    private void parseReadClock() {
        byte[] data = identifyCodeDesc.data;
        if (data != null && data.length == 8) {
            itemNames = new String[]{"content", "dateTime"};
            itemCnNames = new String[]{"内容", "时间"};
            int index = 0;
            int state = data[index++];
            lpState = getLpState(state);
            int ss = HexUtils.bcdByteToInt(data[index++]);
            int mm = HexUtils.bcdByteToInt(data[index++]);
            int h = HexUtils.bcdByteToInt(data[index++]);
            int w = HexUtils.bcdByteToInt(data[index++]);
            int d = HexUtils.bcdByteToInt(data[index++]);
            int m = HexUtils.bcdByteToInt(data[index++]);
            Calendar calendar = Calendar.getInstance();
            int y = calendar.get(Calendar.YEAR);
            if (data[index] != (byte) 0xff) {
                y = HexUtils.bcdByteToInt(data[index]);
            }
            calendar.set(y, m - 1, d, h, mm, ss);
            Date date = DateUtils.date2SqlDate(calendar.getTime());
            dataAttr = new DataAttr() {{
                setName(itemNames[1]);
                setCnname(itemCnNames[1]);
                setValue(date);
                setUnit("年月日时分秒");
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
            }};
            dataAttrs.add(dataAttr);
        }
    }

    /**
     * 分合状态和跳闸记录
     */
    private void parseRunStateAndTripEvent() {
        byte[] data = identifyCodeDesc.data;
        if (data != null && data.length == 7) {
            itemNames = new String[]{"content", "dateTime"};
            itemCnNames = new String[]{"跳闸记录", "时间"};
            int index = 0;
            int state = data[index++];
            int actionValue = data[index + 1] << 8 + data[index];
            tripEventRecord = parseEventContent(state, actionValue);

            index += 2;

            int mm = HexUtils.bcdByteToInt(data[index++]);
            int h = HexUtils.bcdByteToInt(data[index++]);
            int d = HexUtils.bcdByteToInt(data[index++]);
            int m = HexUtils.bcdByteToInt(data[index]);
            Calendar calendar = Calendar.getInstance();
            int y = calendar.get(Calendar.YEAR);
            calendar.set(y, m, d, h, mm, 0);
            Date alarmTime = calendar.getTime();
            tripEventRecord.setAlarmTime(alarmTime);
        }
    }

    /**
     * 控制字
     */
    private void parseReadControlWordParameterModule() {
        byte[] data = identifyCodeDesc.data;
        if (data != null && identifyCodeDesc.length != 0) {
            itemNames = new String[]{"content", "ReadControlWordParameterModule"};
            itemCnNames = new String[]{"内容", "控制字参数"};
            int index = 0;
            int state = data[index++];
            lpState = getLpState(state);
            byte[] params = new byte[11];
            System.arraycopy(data, index, params, 0, params.length);
            dataAttr = new DataAttr() {{
                setCnname(itemCnNames[1]);
                setName(itemNames[1]);
                setUnit("");
                setDtime(identifyCodeDesc.dTime);
                setDateType(RemoteType.YC);
                setValue(params);
            }};
            dataAttrs.add(dataAttr);
        }
    }

    /**
     * 获取分合状态
     *
     * @param state
     * @return
     */
    private LPState getLpState(int state) {
        if (state >> 7 == 0x01) {
            return LPState.OFF;
        } else {
            tripEventRecord = new TripEventRecord();
            tripEventRecord.setState(LPState.ON);
            tripEventRecord.deviceGroup = DeviceGroup.LP1997;
            tripEventRecord.tripReason1997 = TripReason1997Enum.NORMAL;
            return LPState.ON;
        }
    }

    /**
     * 跳闸记录解析
     *
     * @param state
     * @param actionValue
     * @return
     */
    private TripEventRecord parseEventContent(int state, float actionValue) {

        tripEventRecord = new TripEventRecord();
        tripEventRecord.deviceGroup = DeviceGroup.LP1997;
        tripEventRecord.setAddress(getAddress());

        TripReason1997Enum e = TripReason1997Enum.getTripReasonEnumByValue(HexUtils.byteToInt((byte) (state & 0x1f)));
        if (e != null) {
            tripEventRecord.tripReason1997 = e;
            tripEventRecord.setAlarmReason(e.getName());
        }
        tripEventRecord.setState(LPState.OFF);
        if ((state & 0x40) >> 6 == 0x01) {
            tripEventRecord.lock = ",锁死";
        } else {
            tripEventRecord.lock = ",未锁死";
        }
        tripEventRecord.setAlarmReason(tripEventRecord.lock);

        switch ((state & 0x30) >> 4) {
            case 0x01:
                tripEventRecord.setAlarmPhase("A相");
                break;
            case 0x02:
                tripEventRecord.setAlarmPhase("B相");
                break;
            case 0x03:
                tripEventRecord.setAlarmPhase("C相");
                break;
            case 0x00:
                tripEventRecord.setAlarmPhase("未知相");
                break;
            default:
                tripEventRecord.setAlarmPhase("未知相");
                break;
        }

        switch ((byte) (state & 0x0f)) {
            case 0x00:
                tripEventRecord.setResidualCurrent(actionValue);
                break;
            case 0x01:
                tripEventRecord.setResidualCurrent(actionValue);
                break;
            case 0x02:
                tripEventRecord.setResidualCurrent(actionValue);
                break;
            case 0x03:
                tripEventRecord.setCurrent(actionValue);
                break;
            case 0x04:
                tripEventRecord.setVoltage(actionValue);
                break;
            case 0x05:
                tripEventRecord.setVoltage(actionValue);
                break;
            case 0x06:
                tripEventRecord.setCurrent(actionValue);
                break;
        }
        return tripEventRecord;
    }
}
