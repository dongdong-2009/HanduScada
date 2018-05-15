package main.com.handu.scada.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import main.com.handu.scada.netty.server.dtu.DtuChannelInitializer;
import main.com.handu.scada.netty.server.dtu.DtuCmdChannelInitializer;
import main.com.handu.scada.netty.server.protocol101.Protocol101ChannelInitializer;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Dtu端口启动入口
 */
public class TcpServer {

    private static TcpServer instance;

    private TcpServer() {
    }

    /**
     * @return
     */
    public static TcpServer getInstance() {
        if (instance == null) {
            synchronized (TcpServer.class) {
                if (instance == null) {
                    instance = new TcpServer();
                }
            }
        }
        return instance;
    }

    /**
     * 开始启动端口
     */
    public void start(String host, List<Ports> ports) {
        if (ports != null) {
            bind(host, ports);
        }
    }

    /**
     * ChannelOption.SO_BACKLOG, 1024
     * BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
     * <p>
     * ChannelOption.SO_KEEPALIVE, true
     * 是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
     * <p>
     * ChannelOption.TCP_NODELAY, true
     * 在TCP/IP协议中，无论发送多少数据，总是要在数据前面加上协议头，同时，对方接收到数据，也需要发送ACK表示确认。为了尽可能的利用网络带宽，TCP总是希望尽可能的发送足够大的数据。这里就涉及到一个名为Nagle的算法，
     * 该算法的目的就是为了尽可能发送大块数据，避免网络中充斥着许多小数据块。
     * <p>
     * TCP_NODELAY就是用于启用或关于Nagle算法。如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false。
     */
    private void bind(String host, List<Ports> allPorts) {
        if (allPorts.size() == 0) return;
        //EventLoopGroup是用来处理IO操作的多线程事件循环器
        //bossGroup 用来接收进来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup 用来处理已经被接收的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //boss线程内存池配置
                    //.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    //通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //保持长连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //worker线程内存池配置
                    //.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new LoggingHandler())
                    .childHandler(new DtuChannelInitializer());

            ArrayList<Channel> channels = new ArrayList<>();
            for (Ports port : allPorts) {
                PortType type = port.portType;
                String[] ports = port.ports;
                for (String p : ports) {
                    if (type == PortType.CMD) {
                        serverBootstrap.childHandler(new DtuCmdChannelInitializer());
                    } else if (type == PortType.DTU) {
                        serverBootstrap.childHandler(new DtuChannelInitializer());
                    } else if (type == PortType.SWITCH) {
                        serverBootstrap.childHandler(new Protocol101ChannelInitializer());
                    }
                    if (!StringsUtils.isEmpty(p)) {
                        Channel serverChannel = serverBootstrap.bind(Integer.parseInt(p)).sync().channel();
                        channels.add(serverChannel);
                        LogUtils.info("start " + type.name() + " listen socket------host = " + host + " and port = " + p, true);
                    }
                }
            }
            for (Channel channel : channels) {
                // 等待服务器 socket 关闭
                channel.closeFuture().sync();
            }
        } catch (InterruptedException e) {
            LogUtils.info("start listen socket error", true);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

