package main.com.handu.scada.protocol101.protocol.enums;

/**
 * Created by 柳梦 on 2018/05/11.
 * 开关下发命令类型
 */
public enum Protocol101CmdEnum {

    ALL_CALL(1, "总召"),
    CONFIRM(2, "确认帧");

    private int value;
    private String name = "";

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    Protocol101CmdEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
