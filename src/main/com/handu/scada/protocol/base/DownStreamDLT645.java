package main.com.handu.scada.protocol.base;

/**
 * Created by 柳梦 on 2017/12/26.
 */
public abstract class DownStreamDLT645 extends BaseDownStreamDLT645 {

    public abstract void GetBytes();
    public String dtuAddress;
}
