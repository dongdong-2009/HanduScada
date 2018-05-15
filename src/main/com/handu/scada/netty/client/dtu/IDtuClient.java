package main.com.handu.scada.netty.client.dtu;

import main.com.handu.scada.protocol.base.ProtocolLayerData;

/**
 * Created by 柳梦 on 2017/12/26.
 */
public interface IDtuClient {

    /**
     * 发送
     */
    void send(ProtocolLayerData protocolLayerData);

    void send(MsgPriority priority, ProtocolLayerData protocolLayerData);
}
