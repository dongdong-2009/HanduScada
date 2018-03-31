package main.com.handu.scada.event.subscribe;

import main.com.handu.scada.event.events.BaseEvent;

/**
 * 订阅者接口
 * Created by 柳梦 on 2017/12/11.
 */
public interface ISubscriber {

    /**
     * @Description: 订阅
     * @param: subscribePublish 订阅器
     */
    void subscribe(SubscribePublish subscribePublish);

    /**
     * @Description: 退订
     * @param: subscribePublish 订阅器
     */
    void unSubscribe(SubscribePublish subscribePublish);

    /**
     * @Description: 接收消息
     * @param: publisher 发布者
     * @param: message 消息
     */
    void onEvent(String publisher, BaseEvent event);
}
