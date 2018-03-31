package main.com.handu.scada.netty.client.dtu;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import main.com.handu.scada.exception.ExceptionHandler;

import java.net.InetSocketAddress;

/**
 * Created by 柳梦 on 2018/02/05.
 */
public class CmdClient {

    private static EventLoopGroup group = new NioEventLoopGroup();

    private String host = "127.0.0.1";
    private int port = 0;

    public CmdClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        System.out.println("start connect...");
        try {
            Bootstrap b = new Bootstrap();
            b.group(group) // 注册线程池
                    .channel(NioSocketChannel.class) //使用NioSocketChannel来作为连接用的channel类
                    .remoteAddress(new InetSocketAddress(this.host, this.port)) //绑定连接端口和host信息
                    .handler(new ChannelInitializer<SocketChannel>() { //绑定连接初始化器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new CmdClientHandler());
                        }
                    });
            ChannelFuture cf = b.connect().sync().addListener(future -> {
                System.out.println("connect success..."); //连接完成
            }); //异步连接服务器
            cf.channel().closeFuture().sync().addListener(future -> {
                System.out.println("connect closed..."); //关闭完成
            }); //异步等待关闭连接channel
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        } finally {
            try {
                group.shutdownGracefully().sync(); //释放线程池资源
            } catch (InterruptedException e) {
                ExceptionHandler.handle(e);
            }
        }
    }
}
