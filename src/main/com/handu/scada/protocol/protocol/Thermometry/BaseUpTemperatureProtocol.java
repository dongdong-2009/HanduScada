package main.com.handu.scada.protocol.protocol.Thermometry;

import main.com.handu.scada.protocol.IProtocol;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;

import java.util.List;
import java.util.Map;

/**
 * Created by 柳梦 on 2018/01/25.
 */
public abstract class BaseUpTemperatureProtocol implements IProtocol {

    public ProtocolLayerData protocolLayerData;
    public byte byteCount;
    public byte funcode;
    public boolean IsNormal;
    public byte probe;
    public boolean IsSuccess;
    public byte[] crc16;
    public byte[] data;
    public byte[] telltableAddress;
    public byte[] telltableCount;
    public byte address;
    public DirectionType Direct;
    public Map<Byte, Double> probedic;
    public String dtuAddress;
    public List<DataAttr> values;
    public TemperatureExtern TemperatureItem;

}
