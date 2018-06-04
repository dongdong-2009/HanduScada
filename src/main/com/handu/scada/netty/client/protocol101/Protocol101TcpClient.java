package main.com.handu.scada.netty.client.protocol101;

import main.com.handu.scada.event.Subscriber;
import main.com.handu.scada.event.events.BaseEvent;
import main.com.handu.scada.event.events.DownProtocolEvent;
import main.com.handu.scada.event.subscribe.SubscribePublish;
import main.com.handu.scada.netty.client.BaseTcpClient;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.netty.server.protocol101.Protocol101CommandHandler;
import main.com.handu.scada.netty.server.protocol101.Protocol101CtxManager;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;

/**
 * Created by 柳梦 on 2018/05/10.
 * 开关故障指示器发送入口
 */
@Subscriber
public class Protocol101TcpClient extends BaseTcpClient implements IProtocolClient {

    private Protocol101TcpClient() {
    }

    @Override
    public void subscribe(SubscribePublish subscribePublish) {
        subscribePublish.subscribe(this);
    }

    @Override
    public void unSubscribe(SubscribePublish subscribePublish) {
        subscribePublish.unSubscribe(this);
    }

    @Override
    public void onEvent(String publisher, BaseEvent event) {
        if (event instanceof DownProtocolEvent) {
            if (event.data != null && event.data instanceof Protocol101Data) {
                Protocol101Data data = (Protocol101Data) event.data;
                service.execute(() -> send(event.priority, data));
            }
        }
    }

    @Override
    public void send(MsgPriority priority, Protocol101Data data) {
        String address = data.getAddress();
        if (StringsUtils.isNotEmpty(address)) {
            Protocol101CommandHandler handler = Protocol101CtxManager.getHandler(address);
            if (handler != null) {
                handler.downAnalysis(data);
            } else {
                LogUtils.error("deviceAddress " + address + " is not connection!");
            }
        } else {
            LogUtils.error("deviceAddress is not correct!");
        }
    }
}
