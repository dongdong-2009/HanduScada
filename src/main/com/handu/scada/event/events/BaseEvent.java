package main.com.handu.scada.event.events;

import main.com.handu.scada.netty.client.dtu.MsgPriority;

/**
 * Created by 柳梦 on 2017/12/11.
 */
public class BaseEvent {

    public int type = -1;
    public Object data;
    public MsgPriority priority;

    public BaseEvent(int type, Object data) {
        this.type = type;
        this.data = data;
    }

    public BaseEvent(int type, Object data, MsgPriority priority) {
        this.type = type;
        this.data = data;
        this.priority = priority;
    }

    public BaseEvent(Object data) {
        this.data = data;
    }

    public BaseEvent(MsgPriority priority, Object data) {
        this.priority = priority;
        this.data = data;
    }

    public BaseEvent(int type) {
        this.type = type;
    }
}
