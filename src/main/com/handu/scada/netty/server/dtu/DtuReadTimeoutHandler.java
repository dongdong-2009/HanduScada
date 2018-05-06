package main.com.handu.scada.netty.server.dtu;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by 柳梦 on 2018/05/04.
 */
public class DtuReadTimeoutHandler extends IdleStateHandler {

    public DtuReadTimeoutHandler(int timeoutSeconds) {
        this((long) timeoutSeconds, TimeUnit.SECONDS);
    }

    public DtuReadTimeoutHandler(long timeout, TimeUnit unit) {
        super(timeout, 0L, 0L, unit);
    }

    protected final void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        assert evt.state() == IdleState.READER_IDLE;
        this.readTimedOut(ctx);
    }

    protected void readTimedOut(ChannelHandlerContext ctx) throws Exception {
        if (ctx != null) {
            String clientId = ctx.channel().id().asShortText();
            DtuNetworkConnection connection = DtuChannelManager.getNetworkState(clientId);
            if (connection != null) {
                connection.readTimeout();
            }
        }
    }
}
