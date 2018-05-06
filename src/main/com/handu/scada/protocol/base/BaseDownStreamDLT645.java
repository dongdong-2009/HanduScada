package main.com.handu.scada.protocol.base;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.enums.WR;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2017/12/26.
 */
public class BaseDownStreamDLT645 {
    public String dtuAddress;
    public Object data;
    public byte length;
    protected byte[] diCode;
    public String address;
    protected byte[] pwd;
    protected byte[] operCode;
    protected byte[] datas;
    protected byte controlCode;
    public String msgName;
    public byte[] cmdByte;
    protected WR wr;

    protected static byte[] const_pwd = new byte[]{
            0x33,
            0x33,
            0x33,
            0x33
    };
    protected static byte[] const_opercode = new byte[]{
            0x33,
            0x33,
            0x33,
            0x33};

    protected boolean getBytes() {
        try {
            List<Byte> list = new ArrayList<>();
            byte headHex = 0x68;
            list.add(headHex);
            byte[] bytes = HexUtils.stringToBytes(StringsUtils.padLeft(this.address, 12, "0"));
            for (byte b : bytes) {
                list.add(b);
            }
            list.add(headHex);
            list.add(controlCode);
            list.add(length);
            if (diCode != null && diCode.length > 0) {
                for (byte b : diCode) {
                    list.add(b);
                }
            }
            if (pwd != null && pwd.length > 0) {
                for (byte b : pwd) {
                    list.add(b);
                }
            }
            if (operCode != null && operCode.length > 0) {
                for (byte b : operCode) {
                    list.add(b);
                }
            }
            if (datas != null && datas.length > 0) {
                for (byte b : datas) {
                    list.add(b);
                }
            }
            list.add((byte) 0x00);
            byte endHex = 0x16;
            list.add(endHex);
            cmdByte = new byte[list.size()];
            for (int i = 0; i < list.size(); i++) {
                cmdByte[i] = list.get(i);
            }
            cmdByte[cmdByte.length - 2] = HexUtils.getCheckCode(cmdByte);
            return true;
        } catch (Exception e) {
            ExceptionHandler.print(e);
        }
        return false;
    }
}
