package main.com.handu.scada.protocol101.device101.analysis.up.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol101.device101.analysis.up.BaseUpAnalysis;
import main.com.handu.scada.protocol101.protocol.bean.DataAttr;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.Ti;
import main.com.handu.scada.utils.HexUtils;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class YCUpAnalysis extends BaseUpAnalysis {

    public YCUpAnalysis(Protocol101Data baseData) {
        super(baseData);
    }

    @Override
    public Protocol101Data analysis() {
        try {
            if (init()) {
                if (SQ == 0) {
                    //归一化值
                    if (TI == Ti.M_ME_NA_1) {
                        pointLength = 5;
                    }
                    //标度化值
                    else if (TI == Ti.M_ME_NB_1) {
                        pointLength = 5;
                    }
                    //短浮点数
                    else if (TI == Ti.M_ME_NC_1) {
                        pointLength = 7;
                    }
                    for (int i = 0; i < pointCount; i++) {
                        byte[] b = new byte[pointLength];
                        startInfoAddress = b[0] + (b[1] << 8);
                        System.arraycopy(pointData, i * pointLength, b, 0, b.length);
                        if (pointLength == 5) {
                            signBit = HexUtils.getBitFromByte(7, b[3]);
                            byte h = (byte) (b[3] & 0x7f);
                            int value = (h << 8) + HexUtils.byteToInt(b[2]);
                            DataAttr dataAttr = pointAnalysis(value);
                            if (dataAttr != null) {
                                dataAttrs.add(dataAttr);
                            }
                        } else if (pointLength == 7) {
                            byte v[] = new byte[4];
                            System.arraycopy(b, 2, v, 0, v.length);
                            float value = HexUtils.intBitsToFloat(v);
                            DataAttr dataAttr = pointAnalysis(value);
                            if (dataAttr != null) {
                                dataAttrs.add(dataAttr);
                            }
                        }
                    }
                } else if (SQ == 1) {
                    startInfoAddress = pointData[0] + (pointData[1] << 8);
                    byte[] bs = new byte[pointData.length - 2];
                    System.arraycopy(pointData, 2, bs, 0, bs.length);
                    pointData = bs;
                    //归一化值
                    if (TI == Ti.M_ME_NA_1) {
                        pointLength = 3;
                    }
                    //标度化值
                    else if (TI == Ti.M_ME_NB_1) {
                        pointLength = 3;
                    }
                    //短浮点数
                    else if (TI == Ti.M_ME_NC_1) {
                        pointLength = 5;
                    }
                    for (int i = 0; i < pointCount; i++) {
                        byte[] b = new byte[pointLength];
                        System.arraycopy(pointData, i * pointLength, b, 0, b.length);
                        if (pointLength == 3) {
                            signBit = HexUtils.getBitFromByte(7, b[1]);
                            byte h = (byte) (b[1] & 0x7f);
                            int value = (h << 8) + HexUtils.byteToInt(b[0]);
                            DataAttr dataAttr = pointAnalysis(value);
                            if (dataAttr != null) {
                                dataAttrs.add(dataAttr);
                            }
                        } else if (pointLength == 5) {
                            byte v[] = new byte[4];
                            System.arraycopy(b, 1, v, 0, v.length);
                            float value = HexUtils.intBitsToFloat(v);
                            DataAttr dataAttr = pointAnalysis(value);
                            if (dataAttr != null) {
                                dataAttrs.add(dataAttr);
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
}
