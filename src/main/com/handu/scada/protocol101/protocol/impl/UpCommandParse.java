package main.com.handu.scada.protocol101.protocol.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol101.protocol.IProtocol101;
import main.com.handu.scada.protocol101.protocol.Protocol101UpParse;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.factory.Protocol101UpAnalysisFactory;

/**
 * Created by 柳梦 on 2018/03/14.
 */

@Protocol101UpParse
public class UpCommandParse extends BaseProtocol101 implements IProtocol101 {

    @Override
    public boolean valid(byte[] data) {
        try {
            if (data == null) return false;
            if (data.length < 15) return false;
            if (data[0] != (byte) 0x68 || data[3] != (byte) 0x68 || data[data.length - 1] != 0x16) return false;
            byte checksum = data[data.length - 2];
            byte[] b = new byte[data.length - 6];
            System.arraycopy(data, 4, b, 0, b.length);
            byte b1 = 0;
            for (byte b2 : b) {
                b1 += b2;
            }
            return checksum == b1;
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return false;
    }

    @Override
    public Protocol101Data parse(byte[] data) throws InstantiationException, IllegalAccessException {
        if (valid(data)) {
            try {
                baseData = new Protocol101Data(data);
                return Protocol101UpAnalysisFactory.getInstance(baseData).analysis();
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }
        return null;
    }
}
