package main.com.handu.scada.protocol101.protocol.enums;

/**
 * Created by 柳梦 on 2018/06/01.
 */
public enum SOE {

    FAULT_RECORD("录播闭锁");

    private String name;

    public String getName() {
        return name;
    }

    SOE(String name) {
        this.name = name;
    }
}
