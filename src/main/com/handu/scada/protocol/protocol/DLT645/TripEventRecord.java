package main.com.handu.scada.protocol.protocol.DLT645;

import main.com.handu.scada.enums.DeviceGroup;
import main.com.handu.scada.protocol.enums.LPState;
import main.com.handu.scada.protocol.protocol.DLT645.LP1997.TripReason1997Enum;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.TripReason2007Enum;

import java.util.Date;

public class TripEventRecord {
    //锁死状态
    public String lock = "";
    //漏保类型
    public DeviceGroup deviceGroup = DeviceGroup.LP2007;
    ///[Description("地址")]
    public String Address;//状态
    ///[Description("状态")]
    public LPState State;//状态
    ///[Description("跳闸原因")]
    public TripReason1997Enum tripReason1997;//状态
    public TripReason2007Enum tripReason2007;//状态
    ///[Description("")]
    public String AlarmReason = "";//故障原因
    ///[Description("")]
    public String AlarmPhase = "";//故障相别
    ///[Description("")]
    public Date AlarmTime;//跳闸发生时刻（6个字节）
    ///[Description("mA")]
    public float ResidualCurrent;//跳闸前剩余电流值（2个字节）
    ///[Description("V")]
    public float APhaseVoltage;//跳闸前A相电压（2个字节）
    ///[Description("V")]
    public float BPhaseVoltage;//跳闸前B相电压（2个字节）
    ///[Description("V")]
    public float CPhaseVoltage;//跳闸前C相电压（2个字节）
    ///[Description("A")]
    public float APhaseCurrent;//跳闸前A相电流（3个字节）
    ///[Description("A")]
    public float BPhaseCurrent;//跳闸前B相电流（3个字节）
    ///[Description("A")]
    public float CPhaseCurrent;//跳闸前C相电流（3个字节）
    ///[Description("A")]
    public float AlarmActionValue;//跳闸前动作电流（3个字节）

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public LPState getState() {
        return State;
    }

    public void setState(LPState state) {
        State = state;
    }

    public String getAlarmReason() {
        return AlarmReason;
    }

    public void setAlarmReason(String alarmReason) {
        if (AlarmReason == null) {
            AlarmReason = "";
        }
        AlarmReason += alarmReason;
    }

    public String getAlarmPhase() {
        return AlarmPhase;
    }

    public void setAlarmPhase(String alarmPhase) {
        if (AlarmPhase == null) {
            AlarmPhase = "";
        }
        AlarmPhase += alarmPhase;
    }

    public Date getAlarmTime() {
        return AlarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        AlarmTime = alarmTime;
    }

    public float getResidualCurrent() {
        return ResidualCurrent;
    }

    public void setResidualCurrent(float residualCurrent) {
        ResidualCurrent = residualCurrent;
    }

    public float getAPhaseVoltage() {
        return APhaseVoltage;
    }

    public void setAPhaseVoltage(float APhaseVoltage) {
        this.APhaseVoltage = APhaseVoltage;
    }

    public void setVoltage(float value) {
        switch (AlarmPhase) {
            case "A相":
                setAPhaseVoltage(value);
                break;
            case "B相":
                setBPhaseVoltage(value);
                break;
            case "C相":
                setCPhaseVoltage(value);
                break;
        }
    }

    public void setCurrent(float value) {
        switch (AlarmPhase) {
            case "A相":
                setAPhaseCurrent(value);
                break;
            case "B相":
                setBPhaseCurrent(value);
                break;
            case "C相":
                setCPhaseCurrent(value);
                break;
        }
    }

    public float getBPhaseVoltage() {
        return BPhaseVoltage;
    }

    public void setBPhaseVoltage(float BPhaseVoltage) {
        this.BPhaseVoltage = BPhaseVoltage;
    }

    public float getCPhaseVoltage() {
        return CPhaseVoltage;
    }

    public void setCPhaseVoltage(float CPhaseVoltage) {
        this.CPhaseVoltage = CPhaseVoltage;
    }

    public float getAPhaseCurrent() {
        return APhaseCurrent;
    }

    public void setAPhaseCurrent(float APhaseCurrent) {
        this.APhaseCurrent = APhaseCurrent;
    }

    public float getBPhaseCurrent() {
        return BPhaseCurrent;
    }

    public void setBPhaseCurrent(float BPhaseCurrent) {
        this.BPhaseCurrent = BPhaseCurrent;
    }

    public float getCPhaseCurrent() {
        return CPhaseCurrent;
    }

    public void setCPhaseCurrent(float CPhaseCurrent) {
        this.CPhaseCurrent = CPhaseCurrent;
    }

    public float getAlarmActionValue() {
        return AlarmActionValue;
    }

    public void setAlarmActionValue(float alarmActionValue) {
        AlarmActionValue = alarmActionValue;
    }

    @Override
    public String toString() {
        return AlarmReason + "," + AlarmPhase;
    }

    public String toString1() {
        return AlarmReason +
                "," + AlarmPhase +
                "," + State.name() +
                ",跳闸前 Ua=" + APhaseVoltage + "V" +
                ", Ub=" + BPhaseVoltage + "V" +
                ", Uc=" + CPhaseVoltage + "V" +
                ", Io=" + ResidualCurrent + "mA" +
                ", Ia=" + APhaseCurrent + "A" +
                ", Ib=" + BPhaseCurrent + "A" +
                ", Ic=" + CPhaseCurrent + "A" +
                ", 动作电流=" + AlarmActionValue + "A";
    }
}
