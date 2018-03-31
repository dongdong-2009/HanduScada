package main.com.handu.scada.netty.client.dtu;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import main.com.handu.scada.protocol.base.ProtocolLayerData;

/**
 * Created by 柳梦 on 2017/12/26.
 */
public interface IClient {

    /**
     * 发送
     */
    void send(ProtocolLayerData protocolLayerData);

    void send(MsgPriority priority, ProtocolLayerData protocolLayerData);

    void sendCommand(ChannelHandlerContext ctx, ByteBuf data);

    void sendCommand(Channel channel, byte[] data);
}
