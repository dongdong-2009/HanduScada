package main.com.handu.scada.protocol.protocol.DLT645.LP2007;

import main.com.handu.scada.protocol.enums.LPState;
import main.com.handu.scada.protocol.enums.TripReasonEnum;
import main.com.handu.scada.utils.HexUtils;

import java.util.Calendar;
import java.util.Date;

public class TripEventRecordAnalyze {

    private TripEventRecord tripEventRecord;

    public TripEventRecord getTripEventRecord() {
        return tripEventRecord;
    }

    public void setTripEventRecord(TripEventRecord tripEventRecord) {
        this.tripEventRecord = tripEventRecord;
    }

    public TripEventRecordAnalyze(byte[] data) {
        tripEventRecord = new TripEventRecord();
        tripEventRecord.setState(LPState.OFF);
        int index = 0;
        byte reason = data[index];
        TripReasonEnum tripReasonEnum = TripReasonEnum.getTripReasonEnumByValue(HexUtils.byteToInt((byte) (reason & 0x1f)));
        if (tripReasonEnum != null) {
            tripEventRecord.setTripReason(tripReasonEnum);
            tripEventRecord.setAlarmReason(tripReasonEnum.getName());
        }
        index++;
        byte Phase = data[index];

        if ((Phase & 0x01) == 0x01)
            tripEventRecord.setAlarmPhase("A");
        else if ((Phase & 0x02) == 0x02)
            tripEventRecord.setAlarmPhase("B");
        else if ((Phase & 0x04) == 0x04)
            tripEventRecord.setAlarmPhase("C");
        else
            tripEventRecord.setAlarmPhase("未知");

        tripEventRecord.setAlarmPhase("相");

        index++;

        int BCD1;
        int BCD2;
        int BCD3;
        int BCD4;
        int BCD5;
        int BCD6;

        BCD1 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD2 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD3 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD4 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD5 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD6 = HexUtils.bcdByteToInt(data[index]);
        index++;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000 + BCD6, BCD5 - 1, BCD4, BCD3, BCD2, BCD1);
        Date alarmTime = calendar.getTime();
        //如果时间大于当前系统时间则采用系统时间
        //alarmTime = alarmTime.getTime() > new Date().getTime() ? DateUtils.getNowSqlDateTime() : alarmTime;
        tripEventRecord.setAlarmTime(alarmTime);

        BCD1 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD2 = HexUtils.bcdByteToInt(data[index]);
        index++;
        tripEventRecord.setResidualCurrent(Short.parseShort(String.valueOf(BCD2 * 100 + BCD1)));

        BCD1 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD2 = HexUtils.bcdByteToInt(data[index]);
        index++;
        tripEventRecord.setAPhaseVoltage(BCD2 * 10 + BCD1 / 10);

        BCD1 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD2 = HexUtils.bcdByteToInt(data[index]);
        index++;
        tripEventRecord.setBPhaseVoltage(BCD2 * 10 + BCD1 / 10);

        BCD1 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD2 = HexUtils.bcdByteToInt(data[index]);
        index++;
        tripEventRecord.setCPhaseVoltage(BCD2 * 10 + BCD1 / 10);

        BCD1 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD2 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD3 = HexUtils.bcdByteToInt(data[index]);
        index++;
        tripEventRecord.setAPhaseCurrent(BCD3 * 1000 + BCD2 * 10 + BCD1 / 10);

        BCD1 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD2 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD3 = HexUtils.bcdByteToInt(data[index]);
        index++;
        tripEventRecord.setBPhaseCurrent(BCD3 * 1000 + BCD2 * 10 + BCD1 / 10);

        BCD1 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD2 = HexUtils.bcdByteToInt(data[index]);
        index++;
        BCD3 = HexUtils.bcdByteToInt(data[index]);
        //index++;
        tripEventRecord.setCPhaseCurrent(BCD3 * 1000 + BCD2 * 10 + BCD1 / 10);
    }
}
