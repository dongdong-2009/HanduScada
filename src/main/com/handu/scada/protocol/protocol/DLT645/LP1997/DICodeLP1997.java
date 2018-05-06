package main.com.handu.scada.protocol.protocol.DLT645.LP1997;

import main.com.handu.scada.protocol.base.BaseDICode;

/**
 * Created by 柳梦 on 2018/04/24.
 */
public class DICodeLP1997 extends BaseDICode {

    /// <summary>
    ///  三相电压电流和剩余电流
    /// </summary>
    public static byte[] VoltageCurrentAndResidualCurrent = new byte[]{0x6f, (byte) 0xb6};
    /// <summary>
    ///  读时钟
    /// </summary>
    public static byte[] ReadClock = new byte[]{0x12, (byte) 0xc0};
    /// <summary>
    ///  校对时钟
    /// </summary>
    public static byte[] BroadcastTime = new byte[]{0x12, (byte) 0xc0};
    /// <summary>
    ///  读设备地址
    /// </summary>
    public static byte[] ReadPostalAddress = new byte[]{0x32, (byte) 0xc0};
    /// <summary>
    ///  写设备地址
    /// </summary>
    public static byte[] WritePostalAddress = new byte[]{0x32, (byte) 0xc0};
    /// <summary>
    ///  合闸
    /// </summary>
    public static byte[] ExecuteON = new byte[]{0x36, (byte) 0xc0};
    /// <summary>
    ///  分闸
    /// </summary>
    public static byte[] ExecuteOFF = new byte[]{0x36, (byte) 0xc0};
    /// <summary>
    ///  开关试跳
    /// </summary>
    public static byte[] SwitchTrip = new byte[]{0x37, (byte) 0xc0};
    /// <summary>
    ///  读当前开关信息及动作值和时间
    /// </summary>
    public static byte[] RunState = new byte[]{0x40, (byte) 0xc0};
    /// <summary>
    ///  读参数
    /// </summary>
    public static byte[] ReadControlWordParameterModule = new byte[]{0x4f, (byte) 0xc0};
    /// <summary>
    ///  写参数
    /// </summary>
    public static byte[] WriteControlWordParameterModule = new byte[]{0x4f, (byte) 0xc0};
    /// <summary>
    ///  读最近第1次跳闸类型动作值及时间
    /// </summary>
    public static byte[] ProtectorTripEventRecord = new byte[]{0x10, (byte) 0xe5};
}
