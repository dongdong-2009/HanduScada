package main.com.handu.scada.protocol.base;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.DltControlWord;
import main.com.handu.scada.protocol.protocol.DLT645.TripEventRecord;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;

import java.util.List;

/**
 * Created by 柳梦 on 2017/12/26.
 */
public class ProtocolLayerData {
    public boolean isWaitReceive = true;
    public String strData;
    public DeviceTypeEnum deviceTypeEnum;
    public TripEventRecord tripEventRecord;
    public DltControlWord controlWord;
    public byte[] CommandData;
    public String DTUString;
    public DeviceCmdTypeEnum CmdType;
    public byte[] DLT645Address;
    //漏保地址，透传地址
    public String PostalAddress;
    public String TabName;
    /// <summary>
    /// 命令名称
    /// </summary>
    public String CommandName;
    /// <summary>
    /// 是否添加DtuHead字节
    /// </summary>
    public boolean HasDTUHead = true;
    /**
     * 数据域
     */
    public List<DataAttr> attrList;
    /**
     * 二级漏保数据域
     */
    public List<List<DataAttr>> secondAttrList;

    /**
     * 二级漏保档案
     */
    public List<SecondLpRecord> secondLpRecords;

    /**
     * 连接id
     */
    public String clientId;

    /**
     * Dtu地址
     */
    public String dtuAddress;


    public ProtocolLayerData() {
    }
}
