package main.com.handu.scada.event.publish;

import main.com.handu.scada.event.events.BaseEvent;
import main.com.handu.scada.event.subscribe.SubscribePublish;
import main.com.handu.scada.netty.client.dtu.MsgPriority;

/**
 * 发布者接口
 * Created by 柳梦 on 2017/12/11.
 */
public interface IPublisher {


    /**
     * @param subscribePublish 订阅器
     * @param message          消息
     * @param isInstant        是否立即发送
     * @param priority         优先级
     * @Description: 向订阅器发布消息
     */
    void publish(SubscribePublish subscribePublish, BaseEvent message, boolean isInstant, MsgPriority priority);
}
