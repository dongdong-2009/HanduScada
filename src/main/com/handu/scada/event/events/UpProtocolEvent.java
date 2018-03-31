package main.com.handu.scada.event.events;

/**
 * Created by 柳梦 on 2017/12/12.
 */
public class UpProtocolEvent extends BaseEvent {

    public UpProtocolEvent(int type, Object data) {
        super(type, data);
    }

    public UpProtocolEvent(Object data) {
        super(data);
    }

    public UpProtocolEvent(int type) {
        super(type);
    }
}
