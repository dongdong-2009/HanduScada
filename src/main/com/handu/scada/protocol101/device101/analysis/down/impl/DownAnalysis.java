package main.com.handu.scada.protocol101.device101.analysis.down.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol101.device101.analysis.down.BaseDownAnalysis;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.COT;
import main.com.handu.scada.protocol101.protocol.enums.Ti;
import main.com.handu.scada.utils.HexUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2018/05/11.
 * 开关下发
 */
public class DownAnalysis extends BaseDownAnalysis {

    public DownAnalysis(Protocol101Data baseData) {
        super(baseData);
    }

    @Override
    public Protocol101Data analysis() {
        try {
            if (protocol101Data == null) return null;
            switch (protocol101Data.getCmdType()) {
                case ALL_CALL:
                    ti = Ti.C_IC_NA_1.getTiType();
                    vsq = 0x01;
                    byte QOI = 0x14;
                    reasonL = COT.COT06.getCot();
                    ASDU = new byte[]{
                            0x00,
                            0x00,
                            QOI
                    };
                    protocol101Data.setCommandData(getBytes());
                    break;
                case CONFIRM:
                    byte[] b = new byte[]{0x10, 0x00, addressL, addressH, 0x00, 0x16};
                    byte checksum = (byte) (addressL + addressH);
                    b[b.length - 2] = checksum;
                    protocol101Data.setCommandData(b);
                    break;
                case CONSTANT_VALUE:
                    reasonL = COT.COT06.getCot();
                    ti = Ti.C_RS_NA_1.getTiType();
                    byte SN = 0x00;
                    int ps = 0x8220;
                    int pe = 0x822F;
                    List<Integer> list = new ArrayList<>();
                    byte h = HexUtils.getIntHigh(ps);
                    byte l = HexUtils.getIntLow(ps);
                    list.add((int) l);
                    list.add((int) h);
                    while (ps < pe) {
                        ps += 1;
                        h = HexUtils.getIntHigh(ps);
                        l = HexUtils.getIntLow(ps);
                        list.add((int) l);
                        list.add((int) h);
                    }
                    vsq = (byte) (0x80 + list.size());
                    ASDU = new byte[1 + list.size()];
                    ASDU[0] = SN;
                    for (int i = 0; i < list.size(); i++) {
                        byte p = HexUtils.intToByte(list.get(i));
                        ASDU[i + 1] = p;
                    }
                    protocol101Data.setCommandData(getBytes());
                    break;
                case READ_FILE_CATALOGUE:
                    ti = Ti.F_FR_NA_1.getTiType();
                    vsq = 0x01;
                    reasonL = COT.COT05.getCot();
                    ASDU = new byte[]{
                            //信息体地址
                            0x00, 0x00,
                            //附加数据包类型 2表示文件传输
                            0x02,
                            //操作标识 1标识读目录
                            0x01,
                            //目录Id
                            0x00, 0x00, 0x00, 0x00,
                            //目录名长度
                            0x08,
                            //目录名COMTRADE
                            0x43, 0x4F, 0x4D, 0x54, 0x52, 0x41, 0x44, 0x45,
                            //召唤标志 0所有文件 1 满足搜索时间段的文件
                            0x00,
                            //查询起始时间
                            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                            //查询终止时间
                            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    };
                    protocol101Data.setCommandData(getBytes());
                    break;
                //读文件激活
                case READ_FILE_START:
                    if (protocol101Data.getCommandData() != null) {
                        ASDU = protocol101Data.getCommandData();
                        protocol101Data.setCommandData(getBytes());
                    }
                    break;
                //读文件传输确认
                case READ_FILE_CONFIRM:
                    if (protocol101Data.getCommandData() != null) {
                        ASDU = protocol101Data.getCommandData();
                        protocol101Data.setCommandData(getBytes());
                    }
                    break;
            }
            return protocol101Data;
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
