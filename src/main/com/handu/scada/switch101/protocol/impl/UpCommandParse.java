package main.com.handu.scada.switch101.protocol.impl;

import main.com.handu.scada.switch101.protocol.ISwitchProtocol;
import main.com.handu.scada.switch101.protocol.SwitchUpParse;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.bean.DownData;
import main.com.handu.scada.switch101.protocol.factory.SwitchAnalysisFactory;

/**
 * Created by 柳梦 on 2018/03/14.
 */

@SwitchUpParse
public class UpCommandParse extends BaseProtocol implements ISwitchProtocol {

    @Override
    public boolean valid(byte[] data) {
        if (data != null) {
            byte checksum = data[data.length - 2];
            byte[] b = new byte[data.length - 6];
            System.arraycopy(data, 4, b, 0, b.length);
            byte b1 = 0;
            for (byte b2 : b) {
                b1 += b2;
            }
            if (checksum == b1) {
                baseData = new BaseData(data);
                return true;
            }
        }
        return false;
    }

    @Override
    public BaseData send(DownData data) {
        return null;
    }

    @Override
    public BaseData parse(byte[] data) throws InstantiationException, IllegalAccessException {
        if (valid(data)) {
            return SwitchAnalysisFactory.getInstance(baseData).analysis();
        }
        return null;
    }
}
