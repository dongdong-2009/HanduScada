package main.com.handu.scada.switch101.protocol.analysis.impl;

import main.com.handu.scada.switch101.protocol.analysis.BaseAnalysis;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.enums.Ti;

/**
 * Created by 柳梦 on 2018/03/20.
 */
public class YKAnalysis extends BaseAnalysis  {

    public YKAnalysis(BaseData baseData) {
        super(baseData);
    }

    @Override
    public BaseData analysis(BaseData baseData) {
        if (init()) {
            if (SQ == 0) {
                startInfoAddress = pointData[0] + pointData[1] * 256;
                if (TI == Ti.C_SC_NA_1) {
                    byte SCO = pointData[2];
                } else if (TI == Ti.C_SC_NB_1) {
                    byte DOC = pointData[2];
                }
            }
        }
        return null;
    }
}
