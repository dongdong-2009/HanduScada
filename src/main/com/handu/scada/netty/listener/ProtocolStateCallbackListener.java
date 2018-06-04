package main.com.handu.scada.netty.listener;

import main.com.handu.scada.netty.server.MsgType;

/**
 * Created by 柳梦 on 2018/01/05.
 */
public interface ProtocolStateCallbackListener {

    void online(String connectionId, String ip, String port, String deviceAddress, MsgType type);

    void offline(String connectionId, String ip, String port, String deviceAddress);
}
