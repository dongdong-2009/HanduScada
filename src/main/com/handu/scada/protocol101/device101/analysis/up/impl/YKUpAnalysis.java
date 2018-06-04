package main.com.handu.scada.protocol101.device101.analysis.up.impl;

import main.com.handu.scada.protocol101.device101.analysis.up.BaseUpAnalysis;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.Ti;

/**
 * Created by 柳梦 on 2018/03/20.
 */
public class YKUpAnalysis extends BaseUpAnalysis {

    public YKUpAnalysis(Protocol101Data baseData) {
        super(baseData);
    }

    @Override
    public Protocol101Data analysis() {
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
