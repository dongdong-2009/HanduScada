package main.com.handu.scada.protocol.protocol.DLT645.LP1997;

/**
 * Created by 柳梦 on 2018/04/24.
 */
public enum TripReason1997Enum {

    OTHER(0xff, "未知类型"),

    LeakageTrip(0x00, "漏电跳闸"),

    MutationTrip(0x01, "突变跳闸"),

    TopTrip(0x02, "特波跳闸"),

    OverloadTrip(0x03, "过载跳闸"),

    OverVoltageTrip(0x04, "过压跳闸"),

    UnderVoltageTrip(0x05, "欠压跳闸"),

    ShortCircuitTrip(0x06, "短路跳闸"),

    KeyTestTrip(0x07, "手动跳闸"),

    OutageTrip(0x08, "停电跳闸"),

    TransformerFaultTrip(0x09, "互感器故障跳闸"),

    RemoteTrip(0x0a, "远程跳闸"),

    OtherTrip(0x0b, "其它原因跳闸"),

    Closing(0x0c, "合闸过程中"),

    ClosingFailure(0x0d, "合闸失败"),

    NoHistory(0x0e, "无历史数据"),

    NORMAL(0x0F, "正常状态");

    private int value;

    private String name;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    TripReason1997Enum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TripReason1997Enum getTripReasonEnumByValue(int value) {
        for (TripReason1997Enum item : TripReason1997Enum.values()) {
            if (value == item.getValue()) {
                return item;
            }
        }
        return null;
    }
}
