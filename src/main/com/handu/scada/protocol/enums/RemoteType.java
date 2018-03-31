package main.com.handu.scada.protocol.enums;

public enum RemoteType {
    /// <summary>
    /// 遥信
    /// </summary>
    YX(1),
    /// <summary>
    /// 遥测
    /// </summary>
    YC(2),
    /// <summary>
    /// 遥控
    /// </summary>
    YCC(3),
    /// <summary>
    /// 遥调
    /// </summary>
    YT(4);

    private int value;
    RemoteType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
