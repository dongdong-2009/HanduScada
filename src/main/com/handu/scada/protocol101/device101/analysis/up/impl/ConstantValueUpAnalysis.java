package main.com.handu.scada.protocol101.device101.analysis.up.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol101.device101.analysis.up.BaseUpAnalysis;
import main.com.handu.scada.protocol101.protocol.bean.ConstantValue;
import main.com.handu.scada.protocol101.protocol.bean.DataAttr;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;

/**
 * Created by 柳梦 on 2018/05/29.
 * 读定值
 */
public class ConstantValueUpAnalysis extends BaseUpAnalysis {

    public ConstantValueUpAnalysis(Protocol101Data baseData) {
        super(baseData);
    }

    @Override
    public Protocol101Data analysis() {
        try {
            if (init()) {
                byte[] data = new byte[pointData.length - 3];
                System.arraycopy(pointData, 3, data, 0, data.length);
                getPoData(data);
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


    private void getPoData(byte[] data) {
        if (data.length == 0) return;
        byte length = data[3];
        byte[] po = new byte[length + 3];
        System.arraycopy(data, 0, po, 0, po.length);
        pointAnalysis(po);
        po = new byte[data.length - po.length];
        System.arraycopy(data, length + 3, po, 0, po.length);
        getPoData(po);
    }


    private void pointAnalysis(byte[] po) {
        DataAttr attr = ConstantValue.getDataAttr(po);
        if (attr != null) dataAttrs.add(attr);
    }
}
