package main.com.handu.scada.protocol.enums;

public enum LPState {
    ON(0, "合闸"),
    OFF(1, "分闸"),
    NONE(2, "未知");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    LPState(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
