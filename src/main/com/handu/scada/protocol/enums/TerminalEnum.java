package main.com.handu.scada.protocol.enums;

/**
 * Created by 柳梦 on 2018/03/08.
 * 终端类型
 */
public enum TerminalEnum {

    DLTLP6452007("DLTLP6452007"),
    WiredTemperature("WiredTemperature"),
    DLTLP6451997("DLTLP6451997"),
    None("None"),
    BoWei("BoWei");

    private String name;

    public String getName() {
        return name;
    }

    TerminalEnum(String name) {
        this.name = name;
    }
}
