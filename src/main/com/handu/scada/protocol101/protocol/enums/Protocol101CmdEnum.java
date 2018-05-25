package main.com.handu.scada.protocol101.protocol.enums;

/**
 * Created by 柳梦 on 2018/05/11.
 * 开关下发命令类型
 */
public enum Protocol101CmdEnum {

    PROTOCOL101_ON_LINE(10000003, "101设备上线"),
    PROTOCOL101_OFF_LINE(10000004, "101设备下线"),
    DEFAULT(-1, "其它"),
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
