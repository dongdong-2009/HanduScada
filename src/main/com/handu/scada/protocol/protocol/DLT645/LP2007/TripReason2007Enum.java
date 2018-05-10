package main.com.handu.scada.protocol.protocol.DLT645.LP2007;

public enum TripReason2007Enum {

    OTHER(0xff, "未知类型"),

    Normal(0x00, "正常运行"),

    LeakageTrip(0x02, "漏电跳闸"),

    ZeroTrip(0x04, "缺零跳闸"),

    OverloadTrip(0x05, "过载跳闸"),

    ShortCircuitTrip(0x06, "短路跳闸"),

    PhaseTrip(0x07, "缺相跳闸"),

    UnderVoltageTrip(0x08, "欠压跳闸"),

    OverVoltageTrip(0x09, "过压跳闸"),

    GroundTrip(0x0a, "接地跳闸"),

    OutageTrip(0x0b, "停电跳闸"),

    TimingTestTrip(0x0c, "定时试验"),

    RemoteTrip(0x0d, "远程跳闸"),

    KeyTestTrip(0x0e, "按键试验"),

    LockingState(0x0f, "闭锁状态"),

    TransformerFaultTrip(0x10, "互感器故障跳闸"),

    ClosingFailure(0x11, "合闸失败"),

    ManualTrip(0x12, "手动跳闸"),

    SetChange(0x13, "设置更改");

    private int value;

    private String name;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    TripReason2007Enum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TripReason2007Enum getTripReasonEnumByValue(int value) {
        for (TripReason2007Enum item : TripReason2007Enum.values()) {
            if (value == item.getValue()) {
                return item;
            }
        }
        return null;
    }
}
