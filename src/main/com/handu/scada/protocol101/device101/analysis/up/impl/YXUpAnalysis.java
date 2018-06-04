package main.com.handu.scada.protocol101.device101.analysis.up.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.server.protocol101.Protocol101CommandHandler;
import main.com.handu.scada.netty.server.protocol101.Protocol101CtxManager;
import main.com.handu.scada.protocol101.device101.analysis.up.BaseUpAnalysis;
import main.com.handu.scada.protocol101.protocol.bean.DataAttr;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.SOE;
import main.com.handu.scada.protocol101.protocol.enums.Ti;
import main.com.handu.scada.utils.HexUtils;

import java.util.Objects;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class YXUpAnalysis extends BaseUpAnalysis {

    public YXUpAnalysis(Protocol101Data baseData) {
        super(baseData);
    }

    @Override
    public boolean isAnalysis() {
        return true;
    }

    @Override
    public Protocol101Data analysis() {
        try {
            if (init()) {
                if (SQ == 0) {
                    startInfoAddress = pointData[0] + pointData[1] * 256;
                    if (TI == Ti.M_SP_NA_1) {
                        pointLength = 3;
                    } else if (TI == Ti.M_DP_NA_1) {
                        pointLength = 3;
                    }
                    for (int i = 0; i < pointCount; i++) {
                        byte[] b = new byte[pointLength];
                        System.arraycopy(pointData, i * pointLength, b, 0, b.length);
                        if (pointLength == 3) {
                            int value = HexUtils.byteToInt(b[2]);
                            DataAttr dataAttr = pointAnalysis(value);
                            if (dataAttr != null) {
                                addDataAttr(dataAttr);
                            }
                        }
                        startInfoAddress++;
                    }
                } else if (SQ == 1) {
                    //获取起始地址
                    startInfoAddress = pointData[0] + pointData[1] * 256;
                    //获取数据信息域
                    byte[] pd = new byte[pointData.length - 2];
                    System.arraycopy(pointData, 2, pd, 0, pd.length);
                    pointData = pd;

                    //单点遥信
                    if (TI == Ti.M_SP_NA_1) {
                        pointLength = 1;
                    }
                    //双点遥信
                    else if (TI == Ti.M_DP_NA_1) {
                        pointLength = 1;
                    }
                    for (int i = 0; i < pointCount; i++) {
                        byte[] b = new byte[pointLength];
                        System.arraycopy(pointData, i * pointLength, b, 0, b.length);
                        if (pointLength == 1) {
                            int value = HexUtils.byteToInt(b[0]);
                            DataAttr dataAttr = pointAnalysis(value);
                            if (dataAttr != null) {
                                addDataAttr(dataAttr);
                            }
                        }
                        startInfoAddress++;
                    }
                }
                if (dataAttrs.size() > 0) {
                    protocol101Data.setDataAttrs(dataAttrs);
                    return protocol101Data;
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    private void addDataAttr(DataAttr dataAttr) {
        int value = (int) dataAttr.getValue();
        byte v = HexUtils.intToByte(value);
        if (HexUtils.getBitFromByte(7, v) == 0x01) {
            dataAttr.setValue(-1);
        } else {
            value = (TI == Ti.M_DP_NA_1) ? value - 1 : value;
            dataAttr.setValue(value);
            dataAttrs.add(dataAttr);
            //如果是录波闭锁就开始读取录波文件目录和文件名称
            if (Objects.equals(dataAttr.getName(), SOE.FAULT_RECORD.getName()) && value == 1) {
                Protocol101CommandHandler handler = Protocol101CtxManager.getHandler(address);
                if (handler != null) {
                    handler.readFaultFileCatalogue();
                }
            }
        }
    }
}
