package main.com.handu.scada.netty.client.dtu;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import main.com.handu.scada.exception.ExceptionHandler;

import java.util.Scanner;

/**
 * Created by 柳梦 on 2018/02/05.
 */
public class CmdClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private ChannelHandlerContext ctx;

    CmdClientHandler() {
        initInput();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ByteBuf buf = msg.readBytes(msg.readableBytes());
        System.out.println(buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        ExceptionHandler.handle(e);
    }

    private void initInput() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String command;
            while ((command = scanner.next()) != null) {
                ctx.writeAndFlush(Unpooled.copiedBuffer(command, CharsetUtil.UTF_8));
            }
        }).start();
    }
}
