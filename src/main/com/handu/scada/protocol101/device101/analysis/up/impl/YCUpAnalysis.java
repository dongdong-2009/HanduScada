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
                    startInfoAddress = pointData[0] + pointData[1] * 256;
                    if (TI == Ti.M_ME_NA_1) {
                        pointLength = 5;
                    } else if (TI == Ti.M_ME_NB_1) {
                        pointLength = 5;
                    } else if (TI == Ti.M_ME_NC_1) {
                        pointLength = 7;
                    }
                    for (int i = 0; i < pointCount; i++) {
                        byte[] b = new byte[pointLength];
                        System.arraycopy(pointData, i * pointLength, b, 0, b.length);
                        if (pointLength == 5) {
                            int value = HexUtils.byteToInt(b[3]) * 256 + HexUtils.byteToInt(b[2]);
                            DataAttr dataAttr = pointAnalysis(value);
                            if (dataAttr != null) {
                                dataAttrs.add(dataAttr);
                            }
                        } else if (pointLength == 7) {

                        }
                        startInfoAddress++;
                    }
                } else if (SQ == 1) {
                    startInfoAddress = pointData[0] + pointData[1] * 256;
                    byte[] bs = new byte[pointData.length - 2];
                    System.arraycopy(pointData, 2, bs, 0, bs.length);
                    pointData = bs;

                    if (TI == Ti.M_ME_NA_1) {
                        pointLength = 3;
                    } else if (TI == Ti.M_ME_NB_1) {
                        pointLength = 3;
                    } else if (TI == Ti.M_ME_NC_1) {
                        pointLength = 5;
                    }
                    for (int i = 0; i < pointCount; i++) {
                        byte[] b = new byte[pointLength];
                        System.arraycopy(pointData, i * pointLength, b, 0, b.length);
                        if (pointLength == 3) {
                            int value = HexUtils.byteToInt(b[1]) * 256 + HexUtils.byteToInt(b[0]);
                            DataAttr dataAttr = pointAnalysis(value);
                            if (dataAttr != null) {
                                dataAttrs.add(dataAttr);
                            }
                        } else if (pointLength == 5) {

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
