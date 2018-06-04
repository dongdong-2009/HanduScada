package main.com.handu.scada.protocol101.device101.analysis.down;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol101.device101.analysis.IAnalysis;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2018/05/11.
 */
public abstract class BaseDownAnalysis implements IAnalysis {

    protected Protocol101Data protocol101Data;
    protected byte reasonL;
    protected byte reasonH = 0x00;
    //ti
    protected byte ti;
    //vsq
    protected byte vsq;
    //设备地址
    protected String address;
    //地址高位
    protected byte addressH;
    //地址低位
    protected byte addressL;
    //应用服务数据单元
    protected byte[] ASDU;
    //长度
    protected byte length;
    //控制码
    protected byte controlCode;
    //校验码
    protected byte checksum;
    //帧头
    protected byte head = 0x68;
    //帧尾
    protected byte end = 0x16;

    public BaseDownAnalysis(Protocol101Data protocol101Data) {
        this.protocol101Data = protocol101Data;
        if (protocol101Data != null) {
            if (StringsUtils.isNotEmpty(protocol101Data.getAddress())) {
                try {
                    address = protocol101Data.getAddress();
                    addressH = HexUtils.getIntHigh(Integer.parseInt(address));
                    addressL = HexUtils.getIntLow(Integer.parseInt(address));
                    controlCode = protocol101Data.getControlCode();
                } catch (NumberFormatException e) {
                    ExceptionHandler.handle(e);
                }
            }
        }
    }

    protected byte[] getBytes() {
        if (protocol101Data == null) return null;
        if (ASDU == null) return null;
        length = (byte) (0x09 + ASDU.length);
        List<Byte> bytes = new ArrayList<>();
        bytes.add(head);
        bytes.add(length);
        bytes.add(length);
        bytes.add(head);
        bytes.add(controlCode);
        bytes.add(addressL);
        bytes.add(addressH);
        bytes.add(ti);
        bytes.add(vsq);
        bytes.add(reasonL);
        bytes.add(reasonH);
        bytes.add(addressL);
        bytes.add(addressH);
        for (byte b : ASDU) {
            bytes.add(b);
        }
        for (int i = 4; i < bytes.size(); i++) {
            checksum += bytes.get(i);
        }
        bytes.add(checksum);
        bytes.add(end);
        byte[] b = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            b[i] = bytes.get(i);
        }
        return b;
    }
}
