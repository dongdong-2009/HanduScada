package main.com.handu.scada.netty.server.protocol101;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by 柳梦 on 2018/03/13.
 */
public class Protocol101ChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast("handler", new Protocol101CommandHandler());
    }
}