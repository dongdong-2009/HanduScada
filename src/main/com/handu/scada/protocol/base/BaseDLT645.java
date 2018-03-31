package main.com.handu.scada.protocol.base;

import main.com.handu.scada.protocol.IProtocol;

/**
 * Created by 柳梦 on 2017/12/24.
 */
public abstract class BaseDLT645 implements IProtocol {

    public BaseIdentifyCodeDesc identifyCodeDesc;

    public ProtocolLayerData protocolLayerData;

    /// <summary>
    /// 是否正确
    /// </summary>
    public boolean IsSuccess;
    /// <summary>
    /// 通讯地址
    /// </summary>
    public String PostalAddress;
    /// <summary>
    /// 控制码
    /// </summary>
    public byte ControlCode;
    /// <summary>
    /// 数据长度
    /// </summary>
    public int DataLength;
    /// <summary>
    /// 校验码
    /// </summary>
    public byte CheckCode;
    /// <summary>
    /// 通讯地址
    /// </summary>
    public byte[] AddressCode;
    /// <summary>
    /// 标识码
    /// </summary>
    public byte[] IdentifyCode;
    /// <summary>
    /// 数据域
    /// </summary>
    public byte[] Datas;
    /// <summary>
    /// 前导字节
    /// </summary>
    public byte[] LeadByte;

    /// <summary>
    /// DTU地址
    /// </summary>
    public String dtuAddress;
}
