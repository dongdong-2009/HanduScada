package main.com.handu.scada.netty.server.protocol101.factory;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.netty.server.protocol101.BaseHandler;
import main.com.handu.scada.netty.server.protocol101.Protocol101CommandHandler;
import main.com.handu.scada.netty.server.protocol101.impl.Custom101Handler;

/**
 * Created by 柳梦 on 2018/05/29.
 */
public class Protocol101HandlerFactory {

    private static Protocol101HandlerFactory factory = new Protocol101HandlerFactory();

    public static Protocol101HandlerFactory getInstance() {
        return factory;
    }

    public BaseHandler getHandler(Protocol101CommandHandler inboundHandler, DeviceTypeEnum deviceTypeEnum) {
        if (deviceTypeEnum != null) {
            BaseHandler handler;
            switch (deviceTypeEnum) {
                default:
                    handler = new Custom101Handler(inboundHandler);
                    break;
            }
            return handler;
        }
        return null;
    }
}
