package main.com.handu.scada.event.events;

/**
 * Created by 柳梦 on 2017/12/13.
 */
public class DBEvent extends BaseEvent {

    public DBEvent(int type, Object data) {
        super(type, data);
    }

    public DBEvent(Object data) {
        super(data);
    }

    public DBEvent(int type) {
        super(type);
    }
}
