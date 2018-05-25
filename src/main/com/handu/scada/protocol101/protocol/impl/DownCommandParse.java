package main.com.handu.scada.protocol101.protocol.impl;

import main.com.handu.scada.protocol101.protocol.IProtocol101;
import main.com.handu.scada.protocol101.protocol.Protocol101DownParse;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101BaseData;
import main.com.handu.scada.protocol101.protocol.factory.Protocol101DownAnalysisFactory;

/**
 * Created by 柳梦 on 2018/03/14.
 */

@Protocol101DownParse
public class DownCommandParse implements IProtocol101 {

    @Override
    public Protocol101BaseData send(Protocol101BaseData data) {
        try {
            return Protocol101DownAnalysisFactory.getInstance().analysis(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
