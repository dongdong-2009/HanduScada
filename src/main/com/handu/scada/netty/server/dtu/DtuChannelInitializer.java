package main.com.handu.scada.netty.server.dtu;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.nio.ByteOrder;

/**
 * Created by 柳梦 on 2018/03/13.
 */
public class DtuChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(ByteOrder.BIG_ENDIAN, 64 * 1024, 0, 2, 0, 2, true));
        p.addLast("frameEncoder", new LengthFieldPrepender(2));
        p.addLast("handler", new DtuCommandHandler());
    }
}
