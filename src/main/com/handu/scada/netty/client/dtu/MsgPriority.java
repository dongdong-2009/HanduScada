package main.com.handu.scada.netty.client.dtu;

/**
 * Created by 柳梦 on 2018/03/12.
 * 命令优先级
 */
public enum MsgPriority {
    HIGH(1),//高
    LOW(-1),//低
    NORMAL(0);//普通

    private int priority;

    MsgPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
