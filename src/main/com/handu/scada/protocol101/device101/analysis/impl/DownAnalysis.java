package main.com.handu.scada.protocol101.device101.analysis.impl;

import main.com.handu.scada.protocol101.protocol.bean.Protocol101BaseData;
import main.com.handu.scada.protocol101.protocol.enums.COT;
import main.com.handu.scada.protocol101.protocol.enums.Ti;
import main.com.handu.scada.protocol101.device101.analysis.BaseDownAnalysis;

/**
 * Created by 柳梦 on 2018/05/11.
 * 开关下发
 */
public class DownAnalysis extends BaseDownAnalysis {

    public DownAnalysis(Protocol101BaseData baseData) {
        super(baseData);
    }

    @Override
    public Protocol101BaseData analysis() throws Exception {
        if (baseData == null) return null;
        switch (baseData.getCmdType()) {
            case ALL_CALL:
                reasonL = COT.COT06.getCot();
                ti = Ti.C_IC_NA_1.getTiType();
                vsq = 0x01;
                ASDU = new byte[]{
                        addressL,
                        addressH,
                        0x00,
                        0x00,
                        0x14
                };
                baseData.setCommandData(getBytes());
                break;
            case CONFIRM:
                byte[] b = new byte[]{0x10, 0x00, addressL, addressH, 0x00, 0x16};
                byte checksum = (byte) (addressL + addressH);
                b[b.length - 2] = checksum;
                baseData.setCommandData(b);
                break;
        }
        return baseData;
    }
}
