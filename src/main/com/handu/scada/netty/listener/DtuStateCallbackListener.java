package main.com.handu.scada.netty.listener;

import main.com.handu.scada.netty.server.MsgType;

/**
 * Created by 柳梦 on 2018/01/05.
 */
public interface DtuStateCallbackListener {

    void online(String clientId, String dtuAddress, MsgType type);

    void offline(String clientId, String dtuAddress);
}
