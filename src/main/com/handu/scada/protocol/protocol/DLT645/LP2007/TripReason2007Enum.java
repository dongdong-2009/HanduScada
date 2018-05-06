package main.com.handu.scada.protocol.protocol.DLT645.LP2007;

public enum TripReason2007Enum {
    /// <summary>
    /// 正常运行
    /// </summary>
    /// [Description("正常运行]
    Normal(0x00, "正常运行"),
    /// <summary>
    /// 漏电跳闸
    /// </summary>
    /// [Description("漏电跳闸]
    LeakageTrip(0x02, "漏电跳闸"),
    /// <summary>
    /// 缺零跳闸
    /// </summary>
    /// [Description("缺零跳闸]
    ZeroTrip(0x04, "缺零跳闸"),
    /// <summary>
    /// 过载跳闸
    /// </summary>
    /// [Description("过载跳闸]
    OverloadTrip(0x05, "过载跳闸"),
    /// <summary>
    /// 短路跳闸
    /// </summary>
    /// [Description("短路跳闸]
    ShortCircuitTrip(0x06, "短路跳闸"),
    /// <summary>
    /// 缺相跳闸
    /// </summary>
    /// [Description("缺相跳闸]
    PhaseTrip(0x07, "缺相跳闸"),
    /// <summary>
    /// 欠压跳闸
    /// </summary>
    /// [Description("欠压跳闸]
    UndervoltageTrip(0x08, "欠压跳闸"),
    /// <summary>
    /// 过压跳闸
    /// </summary>
    /// [Description("过压跳闸]
    OvervoltageTrip(0x09, "过压跳闸"),
    /// <summary>
    /// 接地跳闸
    /// </summary>
    /// [Description("接地跳闸]
    GroundTrip(0x0a, "接地跳闸"),
    /// <summary>
    /// 停电跳闸
    /// </summary>
    /// [Description("停电跳闸]
    OutageTrip(0x0b, "停电跳闸"),
    /// <summary>
    /// 定时试验
    /// </summary>
    /// [Description("定时试验]
    TimingTestTrip(0x0c, "定时试验"),
    /// <summary>
    /// 远程跳闸
    /// </summary>
    /// [Description("远程跳闸]
    RemoteTrip(0x0d, "远程跳闸"),
    /// <summary>
    /// 按键试验
    /// </summary>
    /// [Description("按键试验]
    KeyTestTrip(0x0e, "按键试验"),
    /// <summary>
    /// 闭锁状态
    /// </summary>
    /// [Description("闭锁状态]
    LockingState(0x0f, "闭锁状态"),
    /// <summary>
    /// 手动跳闸
    /// </summary>
    /// [Description("手动跳闸]
    ManualTrip(0x12, "手动跳闸"),
    /// <summary>
    /// 互感器故障跳闸
    /// </summary>
    /// [Description("互感器故障跳闸]
    TransformerFaultTrip(0x10, "互感器故障跳闸"),
    /// <summary>
    /// 合闸失败
    /// </summary>
    /// [Description("合闸失败]
    ClosingFailure(0x11, "合闸失败"),
    /// <summary>
    /// 设置更改
    /// </summary>
    /// [Description("设置更改]
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
