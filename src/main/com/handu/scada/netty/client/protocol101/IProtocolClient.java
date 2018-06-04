package main.com.handu.scada.netty.client.protocol101;

import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;

/**
 * Created by 柳梦 on 2018/05/11.
 */
public interface IProtocolClient {

    default void send(MsgPriority priority, Protocol101Data data) {

    }

}
