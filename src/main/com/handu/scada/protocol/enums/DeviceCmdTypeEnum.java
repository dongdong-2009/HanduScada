package main.com.handu.scada.protocol.enums;

import java.util.Objects;

public enum DeviceCmdTypeEnum {
    /// <summary>
    /// 总召
    /// </summary>
    ALL_CALL(0, "总召"),
    /// <summary>
    /// 无
    /// </summary>
    NONE(1, "无"),
    /// <summary>
    /// 三相电压
    /// </summary>
    ///[Description("当前 A、B、C三相电压")]
    Voltage(2, "当前 A、B、C三相电压"),
    /// <summary>
    /// 剩余电流
    /// </summary>
    ///   [Description("当前剩余电流最大相及剩余电流值")]
    ResidualAndMaxPhase(3, "当前剩余电流最大相及剩余电流值"),
    /// <summary>
    /// 三相电流
    /// </summary>
    /// [Description("当前 A、B、C三相电流")]
    Current(4, "当前 A、B、C三相电流"),
    /// <summary>
    /// 剩余电流动作值，当前额定极限不驱动时间
    /// </summary>
    ///  [Description("剩余电流动作值，当前额定极限不驱动时间")]
    ResidualActionValue(5, "剩余电流动作值，当前额定极限不驱动时间"),
    /// <summary>
    /// 分合状态
    /// </summary>
    /// [Description("分合状态")]
    RunState(6, "分合状态"),
    /// <summary>
    /// 设备上当前日期
    /// </summary>
    ///  [Description("日期")]
    RunDate(7, "日期"),
    /// <summary>
    /// 设备上当前时间
    /// </summary>
    ///   [Description("时间")]
    RunTime(8, "时间"),
    /// <summary>
    /// 通讯地址
    /// </summary>
    ///  [Description("通讯地址")]
    PostalAddress(9, "通讯地址"),
    /// <summary>
    /// 通讯地址
    /// </summary>
    /// [Description("读通讯地址")]
    ReadPostalAddress(10, "读通讯地址"),
    /// <summary>
    /// 通讯地址
    /// </summary>
    /// [Description("写通讯地址")]
    WritePostalAddress(11, "写通讯地址"),
    /// <summary>
    /// 设备号
    /// </summary>
    /// [Description("设备号")]
    DeviceNumber(12, "设备号"),
    /// <summary>
    /// 读设备型号
    /// </summary>
    ///  [Description("读设备型号")]
    ReadDeviceModel(13, "设备号"),
    /// <summary>
    /// 写设备型号
    /// </summary>
    ///   [Description("写设备型号")]
    WriteDeviceModel(14, "写设备型号"),
    /// <summary>
    /// 额定电压
    /// </summary>
    /// [Description("额定电压")]
    RatedVoltage(15, "额定电压"),
    /// <summary>
    /// 额定电流
    /// </summary>
    ///  [Description("额定电流")]
    RatedCurrent(16, "额定电流"),
    /// <summary>
    /// 自动重合闸时间范围
    /// </summary>
    ///   [Description("自动重合闸时间范围")]
    AutoReclosingTimeRange(17, "自动重合闸时间范围"),
    /// <summary>
    /// 通信波特率征字（ 0-9）
    /// </summary>
    ///  [Description("读通信波特率征字")]
    CommunicationBaudRateCharacter(18, "读通信波特率征字"),
    /// <summary>
    /// 通信波特率征字（ 0-9）
    /// </summary>
    /// [Description("写通信波特率征字")]
    WriteCommunicationBaudRateCharacter(19, "写通信波特率征字"),
    /// <summary>
    /// 控制字参数模块
    /// </summary>
    /// [Description("读控制字参数模块")]
    ReadControlWordParameterModule(20, "读控制字参数模块"),
    /// <summary>
    /// 控制字参数模块1
    /// </summary>
    /// [Description("写控制字1")]
    WriteControlWordParameterModule1(21, "写控制字1"),
    /// <summary>
    /// 控制字参数模块2
    /// </summary>
    /// [Description("写控制字2")]
    WriteControlWordParameterModule2(22, "写控制字2"),
    /// <summary>
    /// 控制字参数模块3
    /// </summary>
    /// [Description("写控制字3")]
    WriteControlWordParameterModule3(23, "写控制字3"),
    /// <summary>
    /// 控制字参数模块4
    /// </summary>
    /// [Description("写控制字4")]
    WriteControlWordParameterModule4(24, "写控制字4"),
    /// <summary>
    /// 控制字参数模块5
    /// </summary>
    /// [Description("写控制字5")]
    WriteControlWordParameterModule5(25, "写控制字5"),
    /// <summary>
    /// 控制字参数模块
    /// </summary>
    ///  [Description("写控制字参数模块")]
    WriteControlWordParameterModule(26, "写控制字参数模块"),
    /// <summary>
    /// 跳闸总次数
    /// </summary>
    /// [Description("总跳闸次数")]
    TripTimes(27, "总跳闸次数"),
    /// <summary>
    /// 跳闸总次数
    /// </summary>
    ///  [Description("闭锁性跳闸次数")]
    ClosedTripTimes(28, "闭锁性跳闸次数"),
    /// <summary>
    /// 剩余电流保护跳闸次数
    /// </summary>
    /// [Description("剩余电流保护跳闸次数")]
    ResidualTripTimes(29, "剩余电流保护跳闸次数"),
    /// <summary>
    /// 电流保护跳闸次数
    /// </summary>
    ///   [Description("电流保护跳闸次数")]
    CurrentTripTimes(30, "电流保护跳闸次数"),
    /// <summary>
    /// 电压保护跳闸次数
    /// </summary>
    ///  [Description("电压保护跳闸次数")]
    VoltageTripTimes(31, "电压保护跳闸次数"),
    /// <summary>
    /// 手动闭锁跳闸次数
    /// </summary>
    ///  [Description("手动闭锁跳闸次数")]
    ManualClosingTripTimes(32, "手动闭锁跳闸次数"),
    /// <summary>
    /// 缺零保护跳闸次数
    /// </summary>
    ///   [Description("缺零保护跳闸次数")]
    ZeroProtectionTrippingTimes(33, "缺零保护跳闸次数"),
    /// <summary>
    /// 试验跳闸次数（定时、远程(),按键）
    /// </summary>
    /// [Description("试验跳闸次数（定时、远程,按键")]
    TrippingTimes(34, "试验跳闸次数（定时,远程,按键"),
    /// <summary>
    /// 跳闸次数参数模块
    /// </summary>
    /// [Description("跳闸次数参数模块")]
    TrippingTimesModule(35, "跳闸次数参数模块"),
    /// <summary>
    /// 退出剩余电流保护次数
    /// </summary>
    /// [Description("退出剩余电流保护次数")]
    ExitResidualModule(36, "退出剩余电流保护次数"),
    /// <summary>
    /// 保护器运行时间总累计
    /// </summary>
    ///  [Description("保护器运行时间总累计")]
    RunTotalTime(37, "保护器运行时间总累计"),
    /// <summary>
    /// 剩余电流超限事件记录
    /// </summary>
    ///  [Description("剩余电流超限事件记录")]
    ResidualLimitEventRecord(38, "剩余电流超限事件记录"),
    /// <summary>
    /// 保护器自检事件记录
    /// </summary>
    ///  [Description("保护器自检事件记录")]
    ProtectorSelfCheckEventRecord(39, "保护器自检事件记录"),
    /// <summary>
    /// 保护器跳闸事件记录
    /// </summary>
    ///  [Description("保护器跳闸事件记录")]
    ProtectorTripEventRecord(40, "保护器跳闸事件记录"),
    /// <summary>
    /// 剩余电流报警事件记录
    /// </summary>
    ///  [Description("剩余电流报警事件记录")]
    ResidualCurrentAlarmEventRecord(41, "剩余电流报警事件记录"),
    /// <summary>
    /// 剩余电流记录1-256
    /// </summary>
    ///  [Description("剩余电流记录")]
    ResidualCurrentRecord1(42, "剩余电流记录1-256"),
    /// <summary>
    /// 剩余电流记录257-512
    /// </summary>
    ///  [Description("剩余电流记录")]
    ResidualCurrentRecord2(43, "剩余电流记录257-512"),
    /// <summary>
    /// 读剩余电流超限报警整定值
    /// </summary>
    /// [Description("读剩余电流超限报警整定值")]
    ReadResidualCurrentAlarmSettingValue(44, "读剩余电流超限报警整定值"),
    /// <summary>
    /// 剩余电流整定参数块Residual current setting parameter block
    /// </summary>
    /// [Description("剩余电流整定参数块")]
    ResidualCurrentSettingParameterBlock(45, "剩余电流整定参数块"),
    /// <summary>
    /// 电压整定参数块
    /// </summary>
    /// [Description("电压整定参数块")]
    VoltageSettingParameterBlock(46, "电压整定参数块"),
    /// <summary>
    /// 电流整定参数块
    /// </summary>
    /// [Description("电流整定参数块")]
    CurrentSettingParameterBlock(47, "电流整定参数块"),
    /// <summary>
    /// 广播校时
    /// </summary>
    /// [Description("广播校时")]
    BroadcastTime(48, "广播校时"),
    /// <summary>
    /// 写过电压整定值
    /// </summary>
    ///  [Description("写过电压整定值")]
    WriteOvervoltagesettingvalue(49, "写过电压整定值"),
    /// <summary>
    /// 写欠电压整定值
    /// </summary>
    ///  [Description("写欠电压整定值")]
    WriteUndervoltagesettingvalue(50, "写欠电压整定值"),
    /// <summary>
    /// 写断相电压整定值
    /// </summary>
    ///  [Description("写断相电压整定值")]
    Writephasebreakvoltagesettingvalue(51, "写断相电压整定值"),
    /// <summary>
    /// 写电流超限报警整定值
    /// </summary>
    /// [Description("写电流超限报警整定值")]
    WriteCurrentoverloadwarningsettingvalue(52, "写电流超限报警整定值"),
    /// <summary>
    /// 写剩余电流超限报警整定值
    /// </summary>
    /// [Description("写剩余电流超限报警整定值")]
    WriteResidualCurrentAlarmSettingValue(53, "写剩余电流超限报警整定值"),
    /// <summary>
    /// 三相电压电流和剩余电流
    /// </summary>
    /// [Description("三相电压电流和剩余电流")]
    VoltageCurrentAndResidualCurrent(54, "三相电压电流和剩余电流"),

    /// [Description("读时钟")]
    ReadClock(55, "读时钟"),

    /// <summary>
    /// 远程操作
    /// </summary>
    /// [Description("远程操作")]
    RemoteOPT(56, "远程操作"),
    /// <summary>
    /// 合闸命令
    /// </summary>
    ///  [Description("合闸")]
    ExecuteON(57, "合闸"),

    /// <summary>
    /// 分闸命令
    /// </summary>
    ///  [Description("分闸")]
    ExecuteOFF(58, "分闸"),

    /// <summary>
    /// 开关试跳
    /// </summary>
    ///  [Description("开关试跳")]
    SwitchTrip(59, "开关试跳"),

    /// <summary>
    /// 瞬时有功功率数据块
    /// </summary>
    /// [Description("瞬时有功功率数据块")]
    InstantaneousActivePower(60, "瞬时有功功率数据块"),
    /// <summary>
    /// 瞬时无功功率数据块
    /// </summary>
    /// [Description("瞬时无功功率数据块")]
    InstantaneousReactivePower(61, "瞬时无功功率数据块"),
    /// <summary>
    /// 瞬时视在功率数据块
    /// </summary>
    ///  [Description("瞬时视在功率数据块")]
    InstantaneousPpparentPower(62, "瞬时视在功率数据块"),
    /// <summary>
    /// 组合有功总电能
    /// </summary>
    /// [Description("组合有功总电能")]
    CombinationActiveTotalElectricity(63, "组合有功总电能"),
    /// <summary>
    /// 正向有功总电能
    /// </summary>
    ///  [Description("正向有功总电能")]
    PositiveActiveTotalElectricity(64, "正向有功总电能"),
    /// <summary>
    /// 反向有功总电能
    /// </summary>
    /// [Description("反向有功总电能")]
    ReverseActiveTotalElectricity(65, "反向有功总电能"),
    /// <summary>
    /// 组合无功1总电能
    /// </summary>
    /// [Description("组合无功1总电能")]
    CombinationReactive1TotalElectricity(66, "组合无功1总电能"),
    /// <summary>
    /// 组合无功2总电能
    /// </summary>
    /// [Description("组合无功2总电能")]
    CombinationReactive2TotalElectricity(67, "组合无功2总电能"),
    /// <summary>
    /// 电压不平衡总次数，总累计时间
    /// </summary>
    /// [Description("电压不平衡总次数，总累计时间")]
    VoltageUnbalanceNumberAndTime(68, "电压不平衡总次数，总累计时间"),
    /// <summary>
    /// （上1次） 电压不平衡记录
    /// </summary>
    /// [Description("（上1次） 电压不平衡记录")]
    LastVoltageUnbalance(69, "(上1次)电压不平衡记录"),
    /// <summary>
    /// （本月）电压合格率统计数据
    /// </summary>
    /// [Description("（本月）电压合格率统计数据")]
    ThisMonthVoltageQualifiedRate(70, "(本月)电压合格率统计数据"),
    /// <summary>
    /// 最大（ 壳架 ）电流 (Inm)
    /// </summary>
    /// [Description("最大（ 壳架 ）电流 (Inm)")]
    MaxShellCurrent(71, "最大(壳架)电流 (Inm)"),
    /// <summary>
    /// 生产日期
    /// </summary>
    ///[Description("生产日期")]
    ProduceDate(72, "生产日期"),
    /// <summary>
    /// 协议版本号
    /// </summary>
    ///  [Description("协议版本号")]
    ProtocolVerNumber(73, "协议版本号"),
    /// <summary>
    /// 厂家固件版本号
    /// </summary>
    ///  [Description("厂家固件版本号")]
    FirmwareVersionNumber(74, "厂家固件版本号"),
    /// <summary>
    /// 厂家硬件版本号
    /// </summary>
    /// [Description("厂家硬件版本号")]
    HardWareVersionNumber(75, "厂家硬件版本号"),
    /// <summary>
    ///剩余电流动作特性（ A型或 AC 型）
    /// </summary>
    ///  [Description("剩余电流动作特性（ A型或 AC 型）")]
    AAC(76, "剩余电流动作特性(A型或 AC 型)"),
    /// <summary>
    ///剩余电流最大相， 最大值及发生时刻
    /// </summary>
    /// [Description("剩余电流最大相,最大值及发生时刻")]
    MaxRC(77, "A相最大电压及发生的时刻"),
    /// <summary>
    ///A相最大电压及发生的时刻
    /// </summary>
    ///  [Description("A相最大电压及发生的时刻")]
    MaxAV(78, "A相最大电压及发生的时刻"),
    /// <summary>
    ///B相最大电压及发生的时刻
    /// </summary>
    ///  [Description("B相最大电压及发生的时刻")]
    MaxBV(79, "B相最大电压及发生的时刻"),
    /// <summary>
    ///C相最大电压及发生的时刻
    /// </summary>
    /// [Description("C相最大电压及发生的时刻")]
    MaxCV(80, "C相最大电压及发生的时刻"),
    /// <summary>
    ///A相最大电流及发生的时刻
    /// </summary>
    /// [Description("A相最大电流及发生的时刻")]
    MaxAC(81, "A相最大电流及发生的时刻"),
    /// <summary>
    ///B相最大电流及发生的时刻
    /// </summary>
    /// [Description("B相最大电流及发生的时刻")]
    MaxBC(82, "B相最大电流及发生的时刻"),
    /// <summary>
    ///C相最大电流及发生的时刻
    /// </summary>
    /// [Description("C相最大电流及发生的时刻")]
    MaxCC(83, "C相最大电流及发生的时刻"),
    /// <summary>
    ///剩余电流最大相， 最小 值及发生的时刻
    /// </summary>
    ///  [Description("剩余电流最大相， 最小 值及发生的时刻")]
    MinRC(84, "剩余电流最小相,最小值及发生的时刻"),
    /// <summary>
    ///A相最小电压及发生的时刻
    /// </summary>
    ///  [Description("A相最小电压及发生的时刻")]
    MinAV(85, "A相最小电压及发生的时刻"),
    /// <summary>
    ///B相最小电压及发生的时刻
    /// </summary>
    ///  [Description("B相最小电压及发生的时刻")]
    MinBV(86, "B相最小电压及发生的时刻"),
    /// <summary>
    ///C相最小电压及发生的时刻
    /// </summary>
    /// [Description("C相最小电压及发生的时刻")]
    MinCV(87, "C相最小电压及发生的时刻"),
    /// <summary>
    ///A相最小电流及发生的时刻
    /// </summary>
    /// [Description("A相最小电流及发生的时刻")]
    MinAC(88, "A相最小电流及发生的时刻"),
    /// <summary>
    ///B相最小电流及发生的时刻
    /// </summary>
    /// [Description("B相最小电流及发生的时刻")]
    MinBC(89, "B相最小电流及发生的时刻"),
    /// <summary>
    ///C相最小电流及发生的时刻
    /// </summary>
    /// [Description("C相最小电流及发生的时刻")]
    MinCC(90, "C相最小电流及发生的时刻"),
    Residual(91, "剩余电流"),

    SecondLpUploadDateTime(92, "二级漏保整点数据上报时间"),
    SecondLpCollectDateTime(93, "二级漏保整点数据采样时间"),
    SecondLpRecord(94, "二级漏保档案上报"),
    ConcentratorHeartbeatTime(95, "集中器最后一次心跳时间"),

    CapacitorControlParams(96, "无功设备电容器控制参数查询"),
    SecondSideSamplingInfo(97, "无功设备查询二次侧采样信息"),

    HM_AFN0C25(98, "台区总表AFN0C25采集"),

    DTU_LOGIN(10000001, "DTU上线登录"),
    DTU_OFF_LINE(10000002, "DTU下线"),
    SWITCH_ON_LINE(10000003, "开关上线"),
    SWITCH_OFF_LINE(10000004, "开关下线"),
    DTU_RESTART(10000005, "重启DTU"),
    READ_DTU_SIGNAL_STRENGTH(10000006, "读取DTU信号强度"),
    COLLECT_DTU_SIGNAL_STRENGTH(10000007, "采集DTU信号强度"),
    DTU_INFO(10000008, "查询dtu参数信息"),
    COMMUNICATION_MODEL(10000009, "模块信号模式");

    private String name = "";
    private int value;

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    DeviceCmdTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static DeviceCmdTypeEnum findDeviceCmdType(String typeEnum) {
        for (DeviceCmdTypeEnum cmdTypeEnum : DeviceCmdTypeEnum.values()) {
            if (Objects.equals(typeEnum, cmdTypeEnum.name())) {
                return cmdTypeEnum;
            }
        }
        return null;
    }

    public static DeviceCmdTypeEnum findDeviceCmdType(int value) {
        for (DeviceCmdTypeEnum cmdTypeEnum : DeviceCmdTypeEnum.values()) {
            if (cmdTypeEnum.getValue() == value) {
                return cmdTypeEnum;
            }
        }
        return null;
    }
}
