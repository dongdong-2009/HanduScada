package main.com.handu.scada.event.publish;

import main.com.handu.scada.event.subscribe.SubscribePublish;
import main.com.handu.scada.event.events.BaseEvent;
import main.com.handu.scada.netty.client.dtu.MsgPriority;

/**
 * 发布者
 * Created by 柳梦 on 2017/12/11.
 */
public class PublisherImp implements IPublisher {

    private String name;

    public PublisherImp() {
        super();
        this.name = this.getClass().getName();
    }

    @Override
    public void publish(SubscribePublish subscribePublish, BaseEvent event, boolean isInstantMsg, MsgPriority priority) {
        subscribePublish.publish(this.name, event, isInstantMsg, priority);
    }
}
