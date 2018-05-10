package main.com.handu.scada.protocol.protocol.DLT645;

import main.com.handu.scada.enums.DeviceGroup;

/**
 * Created by 柳梦 on 2018/05/08.
 * 漏保跳闸原因
 * 整合各种漏保的跳闸类型
 */
public enum CommonTripReasonEnum {

    /**
     * 2007国标漏保
     */
    Other(0xfe, "其它原因跳闸", 0xfe),

    Normal(0x00, "正常状态", 0x00, 0x0f),

    LeakageTrip(0x02, "漏电跳闸", 0x02, 0x00),

    ZeroTrip(0x04, "缺零跳闸", 0x04, 0xfe),

    OverloadTrip(0x05, "过载跳闸", 0x05, 0x03),

    ShortCircuitTrip(0x06, "短路跳闸", 0x06, 0x06),

    PhaseTrip(0x07, "缺相跳闸", 0x07, 0xfe),

    UnderVoltageTrip(0x08, "欠压跳闸", 0x08, 0x05),

    OverVoltageTrip(0x09, "过压跳闸", 0x09, 0x04),

    GroundTrip(0x0a, "接地跳闸", 0x0a, 0xfe),

    OutageTrip(0x0b, "停电跳闸", 0x0b, 0x08),

    TimingTestTrip(0x0c, "定时试验", 0x0c, 0xfe),

    RemoteTrip(0x0d, "远程跳闸", 0x0d, 0x0a),

    KeyTestTrip(0x0e, "按键试验", 0x0e, 0xfe),

    LockingState(0x0f, "闭锁状态", 0x0f, 0xfe),

    TransformerFaultTrip(0x10, "互感器故障跳闸", 0x10, 0x09),

    ClosingFailure(0x11, "合闸失败", 0x11, 0x0d),

    ManualTrip(0x12, "手动跳闸", 0x12, 0x07),

    SetChange(0x13, "设置更改", 0x13, 0xfe),


    /**
     * 97乾隆漏保
     */
    MutationTrip(0x14, "突变跳闸", 0xfe, 0x01),

    TopTrip(0x15, "特波跳闸", 0xfe, 0x02),

    Closing(0x16, "合闸过程中", 0xfe, 0x0c),

    NoHistory(0x17, "无历史数据", 0xfe, 0x0e);


    private int value;
    private int[] values;
    private String name;

    public int getValue() {
        return value;
    }

    public int[] getValues() {
        return values;
    }

    public String getName() {
        return name;
    }

    CommonTripReasonEnum(int value, String name, int... values) {
        this.value = value;
        this.name = name;
        this.values = values;
    }

    public static CommonTripReasonEnum getTripReasonEnumByValue(int value, DeviceGroup group) {
        int index = group == DeviceGroup.LP2007 ? 0 : 1;
        for (CommonTripReasonEnum item : CommonTripReasonEnum.values()) {
            if (item != Other) {
                int[] values = item.getValues();
                if (values[index] == value) {
                    return item;
                }
            }
        }
        return Other;
    }
}
