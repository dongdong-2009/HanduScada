package main.com.handu.scada.netty.server.dtu;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import main.com.handu.scada.utils.Command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 柳梦 on 2018/02/05.
 */
public class DtuCmdHandler extends SimpleChannelInboundHandler<String> {

    public static ConcurrentHashMap<String, DtuCmdHandler> clientMap = new ConcurrentHashMap<>();

    private ChannelHandlerContext ctx;

    public ChannelHandlerContext getChannelHandlerContext() {
        return ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] byteResult = new byte[result.readableBytes()];
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        result.readBytes(byteResult);
        result.release();
        String s = new String(byteResult);
        Command.command(s);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel() != null && !ctx.channel().isActive()) {
            String clientId = ctx.channel().id().asShortText();
            if (clientMap.containsKey(clientId)) {
                clientMap.remove(clientId);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        if (ctx.channel() != null && ctx.channel().isActive()) {
            for (Map.Entry<String, DtuCmdHandler> entry : clientMap.entrySet()) {
                DtuCmdHandler handler = entry.getValue();
                if (handler != null) {
                    handler.getChannelHandlerContext().channel().close().sync();
                    String key = entry.getKey();
                    clientMap.remove(key);
                }
            }
            String clientId = ctx.channel().id().asShortText();
            clientMap.put(clientId, this);
        }
    }

    /**
     * 发送
     *
     * @param ctx
     * @param bytes
     */
    public void sendCommand(ChannelHandlerContext ctx, byte[] bytes) {
        ByteBuf byteBuf = ctx.alloc().buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        ctx.writeAndFlush(byteBuf);
    }
}
