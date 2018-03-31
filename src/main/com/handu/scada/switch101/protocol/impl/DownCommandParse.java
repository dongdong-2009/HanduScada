package main.com.handu.scada.switch101.protocol.impl;

import main.com.handu.scada.switch101.protocol.ISwitchProtocol;
import main.com.handu.scada.switch101.protocol.SwitchDownParse;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.bean.DownData;

/**
 * Created by 柳梦 on 2018/03/14.
 */

@SwitchDownParse
public class DownCommandParse extends BaseProtocol implements ISwitchProtocol {

    @Override
    public boolean valid(byte[] data) {
        return false;
    }

    @Override
    public BaseData send(DownData data) {
        return null;
    }

    @Override
    public BaseData parse(byte[] data) {
        return null;
    }
}
