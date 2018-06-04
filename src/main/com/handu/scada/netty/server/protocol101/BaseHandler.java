package main.com.handu.scada.netty.server.protocol101;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by 柳梦 on 2018/05/29.
 * 101协议基础Handler
 */
public abstract class BaseHandler implements IHandler {

    protected Protocol101CommandHandler handler;
    protected ChannelHandlerContext context;

    public BaseHandler(Protocol101CommandHandler handler) {
        if (handler != null) {
            this.handler = handler;
            this.context = handler.getContext();
        }
    }
}
