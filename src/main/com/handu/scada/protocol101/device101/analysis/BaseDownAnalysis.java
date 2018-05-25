package main.com.handu.scada.protocol101.device101.analysis;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101BaseData;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2018/05/11.
 */
public abstract class BaseDownAnalysis implements IAnalysis {

    protected Protocol101BaseData baseData;
    protected byte reasonL;
    protected byte reasonH = 0x00;
    //ti
    protected byte ti;
    //vsq
    protected byte vsq;
    //设备地址
    protected int address;
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

    public BaseDownAnalysis(Protocol101BaseData baseData) {
        this.baseData = baseData;
        if (baseData != null) {
            if (StringsUtils.isNotEmpty(baseData.getAddress())) {
                try {
                    address = Integer.parseInt(baseData.getAddress());
                    addressH = HexUtils.getIntHigh(address);
                    addressL = HexUtils.getIntLow(address);
                    controlCode = baseData.getControlCode();
                } catch (NumberFormatException e) {
                    ExceptionHandler.handle(e);
                }
            }
        }
    }

    protected byte[] getBytes() {
        if (baseData == null) return null;
        if (ASDU == null) return null;
        length = (byte) (0x07 + ASDU.length);
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
