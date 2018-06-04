package main.com.handu.scada.protocol101.protocol.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol101.protocol.IProtocol101;
import main.com.handu.scada.protocol101.protocol.Protocol101DownParse;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.factory.Protocol101DownAnalysisFactory;

/**
 * Created by 柳梦 on 2018/03/14.
 */

@Protocol101DownParse
public class DownCommandParse implements IProtocol101 {

    @Override
    public Protocol101Data send(Protocol101Data data) {
        try {
            return Protocol101DownAnalysisFactory.getInstance().analysis(data);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
