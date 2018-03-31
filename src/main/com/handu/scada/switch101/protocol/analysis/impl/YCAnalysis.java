package main.com.handu.scada.switch101.protocol.analysis.impl;

import main.com.handu.scada.switch101.protocol.analysis.BaseAnalysis;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.bean.DataAttr;
import main.com.handu.scada.switch101.protocol.enums.Ti;
import main.com.handu.scada.utils.HexUtils;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class YCAnalysis extends BaseAnalysis  {

    public YCAnalysis(BaseData baseData) {
        super(baseData);
    }

    @Override
    public BaseData analysis(BaseData baseData) {
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
                baseData.setDataAttrs(dataAttrs);
                return baseData;
            }
        }
        return null;
    }
}
