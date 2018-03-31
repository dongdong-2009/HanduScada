package main.com.handu.scada.event.events;

import main.com.handu.scada.netty.client.dtu.MsgPriority;

/**
 * Created by 柳梦 on 2017/12/12.
 */
public class DownProtocolEvent extends BaseEvent {

    public DownProtocolEvent(int type, Object data) {
        super(type, data);
    }

    public DownProtocolEvent(Object data) {
        super(data);
    }

    public DownProtocolEvent(int type) {
        super(type);
    }

    public DownProtocolEvent(MsgPriority priority, Object data) {
        super(priority, data);
    }
}
