package main.com.handu.scada.protocol.enums;

public enum LPState {
    /// <summary>
    /// 合闸
    /// </summary>
    ///  [Description("合闸")]
    ON (0),
    /// <summary>
    /// 分闸
    /// </summary>
    ///  [Description("分闸")]
    OFF (1),
    ///  [Description("未知")]
    NONE (2);

    private int value;
    public int getValue() {
        return value;
    }

    LPState(int value) {
        this.value = value;
    }
}
