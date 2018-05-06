package main.com.handu.scada.protocol.base;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.enums.DeviceTypeEnum;

/**
 * Created by 柳梦 on 2017/12/22.
 */
public class MediaData {
    public boolean isWaitReceive = true;
    public byte[] CommandData = null;
    public long DeviceAddress;
    public String DTUString;
    public String PostalAddress;
    /// <summary>
    /// 是否添加DtuHead字节
    /// </summary>
    public boolean HasDTUHead = true;
    /// <summary>
    /// 协议名称
    /// </summary>
    public String MsgName;
    //操作类型
    public DeviceCmdTypeEnum cmdTypeEnum;
    //设备类型
    public DeviceTypeEnum deviceTypeEnum;
}
