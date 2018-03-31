package main.com.handu.scada.protocol.protocol.Thermometry;

import main.com.handu.scada.protocol.IProtocol;

/**
 * Created by 柳梦 on 2018/01/25.
 */
public abstract class BaseDownStreamTemperature implements IProtocol {

    public byte address;
    public String dtuAddress;
    public String msgName;
    public byte[] cmdByte;
    public int arrLength;

    protected abstract void getBytes();
}
