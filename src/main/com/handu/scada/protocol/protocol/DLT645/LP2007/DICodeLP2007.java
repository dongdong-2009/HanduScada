package main.com.handu.scada.protocol.protocol.DLT645.LP2007;

import main.com.handu.scada.protocol.base.BaseDICode;

public class DICodeLP2007 extends BaseDICode {
    /// <summary>
    ///  电压值DI
    /// </summary>
    public static byte[] Voltage = new byte[]{
            0x00,
            (byte) 0xFF,
            0x01,
            0x02
    };
    /// <summary>
    ///  电流值
    /// </summary>
    public static byte[] Current = new byte[]{
            0x00,
            (byte) 0xFF,
            0x02,
            0x02
    };
    /// <summary>
    ///  最大相 剩余电流值
    /// </summary>
    public static byte[] Residual = new byte[]{
            0x00,
            (byte) 0xFF,
            (byte) 0x90,
            0x02
    };

    /// <summary>
    ///  当前额度剩余电流动作值 极限不驱动时间
    /// </summary>
    public static byte[] ResidualActionValue = new byte[]{
            0x00,
            (byte) 0xFF,
            (byte) 0x91,
            0x02
    };
    /// <summary>
    ///  分合状态
    /// </summary>
    public static byte[] RunState = new byte[]{
            0x01,
            0x05,
            0x00,
            0x04
    };
    /// <summary>
    ///  设备上日期
    /// </summary>
    public static byte[] RunDate = new byte[]{
            0x01,
            0x01,
            0x00,
            0x04
    };
    /// <summary>
    ///   设备上时间
    /// </summary>
    public static byte[] RunTime = new byte[]{
            0x02,
            0x01,
            0x00,
            0x04
    };
    /// <summary>
    ///   读控制字参数模块
    /// </summary>
    public static byte[] ReadControlWordParameterModule = new byte[]{
            (byte) 0xFF,
            0x10,
            0x00,
            0x04
    };
    /// <summary>
    ///   剩余电流整定
    /// </summary>
    public static byte[] ResidualCurrentSettingParameterBlock = new byte[]{
            (byte) 0xFF,
            0x11,
            0x00,
            0x04
    };
    /// <summary>
    ///   电压整定
    /// </summary>
    public static byte[] VoltageSettingParameterBlock = new byte[]{
            (byte) 0xFF,
            0x13,
            0x00,
            0x04
    };
    /// <summary>
    ///   电流整定
    /// </summary>
    public static byte[] CurrentSettingParameterBlock = new byte[]{
            (byte) 0xFF,
            0x14,
            0x00,
            0x04
    };
    /// <summary>
    ///   额定电流
    /// </summary>
    public static byte[] RatedCurrent = new byte[]{
            0x05,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    ///   额定电压
    /// </summary>
    public static byte[] RatedVoltage = new byte[]{
            0x04,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    ///   自动重合闸时间范围
    /// </summary>
    public static byte[] AutoReclosingTimeRange = new byte[]{
            0x14,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    ///   读设备型号
    /// </summary>
    public static byte[] DeviceModel = new byte[]{
            0x0B,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    ///   远程分闸
    /// </summary>
    public static byte[] ExecuteOFF = new byte[]{
            0x01,
            0x01,
            0x01,
            0x06
    };
    /// <summary>
    ///   远程合闸
    /// </summary>
    public static byte[] ExecuteON = new byte[]{
            0x01,
            0x02,
            0x01,
            0x06
    };
    /// <summary>
    ///   远程试跳
    /// </summary>
    public static byte[] SwitchTrip = new byte[]{
            0x01,
            0x03,
            0x01,
            0x06
    };
    /// <summary>
    ///   写控制字参数模块
    /// </summary>
    public static byte[] WriteControlWordParameterModule = new byte[]{
            (byte) 0xFF,
            0x10,
            0x00,
            0x04
    };
    /// <summary>
    ///   写控制字1
    /// </summary>
    public static byte[] WriteControlWordParameterModule1 = new byte[]{
            0x01,
            0x10,
            0x00,
            0x04
    };
    /// <summary>
    ///   写控制字2
    /// </summary>
    public static byte[] WriteControlWordParameterModule2 = new byte[]{
            0x02,
            0x10,
            0x00,
            0x04
    };
    /// <summary>
    ///   写控制字3
    /// </summary>
    public static byte[] WriteControlWordParameterModule3 = new byte[]{
            0x03,
            0x10,
            0x00,
            0x04
    };
    ///   写控制字4
    /// </summary>
    public static byte[] WriteControlWordParameterModule4 = new byte[]{
            0x04,
            0x10,
            0x00,
            0x04
    };
    /// <summary>
    ///   写控制字5
    /// </summary>
    public static byte[] WriteControlWordParameterModule5 = new byte[]{
            0x05,
            0x10,
            0x00,
            0x04
    };
    /// <summary>
    ///   写电流超限报警整定值
    /// </summary>
    public static byte[] WriteCurrentoverloadwarningsettingvalue = new byte[]{
            0x02,
            0x14,
            0x00,
            0x04
    };
    /// <summary>
    ///   写过电压整定值
    /// </summary>
    public static byte[] WriteOvervoltagesettingvalue = new byte[]{
            0x01,
            0x13,
            0x00,
            0x04
    };
    /// <summary>
    ///   写欠电压整定值
    /// </summary>
    public static byte[] WriteUndervoltagesettingvalue = new byte[]{
            0x02,
            0x13,
            0x00,
            0x04
    };
    /// <summary>
    ///   写断相电压整定值
    /// </summary>
    public static byte[] Writephasebreakvoltagesettingvalue = new byte[]{
            0x03,
            0x13,
            0x00,
            0x04
    };
    /// <summary>
    ///   写剩余电流超限报警整定值
    /// </summary>
    public static byte[] WriteResidualCurrentAlarmSettingValue = new byte[]{
            0x01,
            0x11,
            0x00,
            0x04
    };
    /// <summary>
    ///   读设备号
    /// </summary>
    public static byte[] DeviceNumber = new byte[]{
            0x02,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    ///   最大（ 壳架 ）电流 (Inm)
    /// </summary>
    public static byte[] MaxShellCurrent = new byte[]{
            0x06,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    /// 生产日期
    /// </summary>
    public static byte[] ProduceDate = new byte[]{
            0x0C,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    /// 协议版本号
    /// </summary>
    public static byte[] ProtocolVerNumber = new byte[]{
            0x0D,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    /// 厂家固件版本号
    /// </summary>
    public static byte[] FirmwareVersionNumber = new byte[]{
            0x0F,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    /// 厂家固件版本号
    /// </summary>
    public static byte[] FirmwareVersionNumber1 = new byte[]{
            0x0F,
            0x04,
            0x20,
            0x53
    };
    /// <summary>
    /// 厂家硬件件版本号
    /// </summary>
    public static byte[] HardWareVersionNumber = new byte[]{
            0x10,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    /// 剩余电流动作特性（ A型或 AC 型）
    /// </summary>
    public static byte[] AAC = new byte[]{
            0x15,
            0x04,
            0x00,
            0x04
    };
    /// <summary>
    /// 跳闸次数参模块
    /// </summary>
    public static byte[] TripTimes = new byte[]{
            (byte) 0xFF,
            0x00,
            (byte) 0x81,
            0x03
    };
    /// <summary>
    /// 退出剩余电流保护次数
    /// </summary>
    public static byte[] ExitResidualModule = new byte[]{
            0x01,
            0x01,
            (byte) 0x81,
            0x03
    };
    /// <summary>
    /// 保护器运行时间总累计
    /// </summary>
    public static byte[] RunTotalTime = new byte[]{
            0x01,
            0x02,
            (byte) 0x81,
            0x03
    };
    /// <summary>
    /// 读取波特率
    /// </summary>
    public static byte[] CommunicationBaudRateCharacter = new byte[]{
            0x03,
            0x07,
            0x00,
            0x04
    };

    /// <summary>
    ///   剩余电流超限事件记录
    /// </summary>
    public static byte[] ResidualLimitEventRecord = new byte[]{
            0x01,
            0x00,
            (byte) 0x88,
            0x03
    };
    /// <summary>
    ///   剩余电流报警事件记录
    /// </summary>
    public static byte[] ResidualCurrentAlarmEventRecord = new byte[]{
            0x01,
            0x00,
            (byte) 0x8F,
            0x03
    };
    /// <summary>
    ///   保护器自检事件记录
    /// </summary>
    public static byte[] ProtectorSelfCheckEventRecord = new byte[]{
            0x01,
            0x00,
            (byte) 0x8D,
            0x03
    };
    /// <summary>
    ///   漏保跳闸事件记录
    /// </summary>
    public static byte[] ProtectorTripEventRecord = new byte[]{
            0x01,
            0x00,
            (byte) 0x8E,
            0x03
    };


    /// <summary>
    /// 剩余电流最大相， 最大值及发生时刻
    /// </summary>
    public static byte[] MaxRC = new byte[]{
            0x02,
            0x00,
            (byte) 0x82,
            0x03
    };
    /// <summary>
    /// A相最大电压及发生的时刻
    /// </summary>
    public static byte[] MaxAV = new byte[]{
            0x02,
            0x01,
            (byte) 0x82,
            0x03
    };
    /// <summary>
    /// B相最大电压及发生的时刻
    /// </summary>
    public static byte[] MaxBV = new byte[]{
            0x02,
            0x02,
            (byte) 0x82,
            0x03
    };
    /// <summary>
    /// C相最大电压及发生的时刻
    /// </summary>
    public static byte[] MaxCV = new byte[]{
            0x02,
            0x03,
            (byte) 0x82,
            0x03
    };
    /// <summary>
    /// A相最大电流及发生的时刻
    /// </summary>
    public static byte[] MaxAC = new byte[]{
            0x02,
            0x04,
            (byte) 0x82,
            0x03
    };
    /// <summary>
    /// B相最大电流及发生的时刻
    /// </summary>
    public static byte[] MaxBC = new byte[]{
            0x02,
            0x05,
            (byte) 0x82,
            0x03
    };
    /// <summary>
    /// C相最大电流及发生的时刻
    /// </summary>
    public static byte[] MaxCC = new byte[]{
            0x02,
            0x06,
            (byte) 0x82,
            0x03
    };
    /// <summary>
    /// 剩余电流最大相， 最小 值及发生的时刻
    /// </summary>
    public static byte[] MinRC = new byte[]{
            0x02,
            0x00,
            (byte) 0x83,
            0x03
    };
    /// <summary>
    /// A相最小电压及发生的时刻
    /// </summary>
    public static byte[] MinAV = new byte[]{
            0x02,
            0x01,
            (byte) 0x83,
            0x03
    };
    /// <summary>
    /// B相最小电压及发生的时刻
    /// </summary>
    public static byte[] MinBV = new byte[]{
            0x02,
            0x02,
            (byte) 0x83,
            0x03
    };
    /// <summary>
    /// C相最小电压及发生的时刻
    /// </summary>
    public static byte[] MinCV = new byte[]{
            0x02,
            0x03,
            (byte) 0x83,
            0x03
    };
    /// <summary>
    /// A相最小电流及发生的时刻
    /// </summary>
    public static byte[] MinAC = new byte[]{
            0x02,
            0x04,
            (byte) 0x83,
            0x03
    };

    /// <summary>
    /// B相最小电流及发生的时刻
    /// </summary>
    public static byte[] MinBC = new byte[]{
            0x02,
            0x05,
            (byte) 0x83,
            0x03
    };
    /// <summary>
    /// C相最小电流及发生的时刻
    /// </summary>
    public static byte[] MinCC = new byte[]{
            0x02,
            0x06,
            (byte) 0x83,
            0x03
    };

    /// <summary>
    /// 二级漏保整点数据
    /// </summary>
    public static byte[] SecondHourData = new byte[]{
            0x00,
            0x01,
            0x01,
            0x07
    };

    /// <summary>
    /// 二级漏保低电压数据
    /// </summary>
    public static byte[] LowVoltage = new byte[]{
            0x01,
            0x01,
            0x01,
            0x07
    };


}
