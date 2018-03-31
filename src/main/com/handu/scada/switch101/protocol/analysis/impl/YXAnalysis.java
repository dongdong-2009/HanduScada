package main.com.handu.scada.switch101.protocol.analysis.impl;

import main.com.handu.scada.switch101.protocol.analysis.BaseAnalysis;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.bean.DataAttr;
import main.com.handu.scada.switch101.protocol.enums.Ti;
import main.com.handu.scada.utils.HexUtils;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class YXAnalysis extends BaseAnalysis {

    public YXAnalysis(BaseData baseData) {
        super(baseData);
    }

    @Override
    public BaseData analysis(BaseData baseData) {
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
                            dataAttrs.add(dataAttr);
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

                if (TI == Ti.M_SP_NA_1) {
                    pointLength = 1;
                } else if (TI == Ti.M_DP_NA_1) {
                    pointLength = 1;
                }
                for (int i = 0; i < pointCount; i++) {
                    byte[] b = new byte[pointLength];
                    System.arraycopy(pointData, i * pointLength, b, 0, b.length);
                    if (pointLength == 1) {
                        int value = HexUtils.byteToInt(b[0]);
                        DataAttr dataAttr = pointAnalysis(value);
                        if (dataAttr != null) {
                            dataAttrs.add(dataAttr);
                        }
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
