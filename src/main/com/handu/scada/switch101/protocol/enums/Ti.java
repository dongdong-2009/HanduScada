package main.com.handu.scada.switch101.protocol.enums;

/**
 * Created by 柳梦 on 2018/03/15.
 * 报文ti值类型
 */
public enum Ti {

    /**
     * 总召
     */
    C_IC_NA_1(DataType.NONE, (byte) 0x64, "召唤命令", "C_IC_NA_1"),

    /**
     * 初始化结束
     */
    M_EI_NA_1(DataType.NONE, (byte) 0x46, "初始化结束", "M_EI_NA_1"),

    /**
     * 故障信息
     */
    M_FT_NA_1(DataType.SOE, (byte) 0x2a, "故障值信息", "M_FT_NA_1"),

    /**
     * 遥控
     */
    C_SC_NA_1(DataType.YK, (byte) 0x2d, "单命令", "C_SC_NA_1"),
    C_SC_NB_1(DataType.YK, (byte) 0x2e, "双命令", "C_SC_NB_1"),

    /**
     * 对时
     */
    C_CS_NA_1(DataType.YC, (byte) 0x67, "时钟同步命令", "C_CS_NA_1"),

    /**
     * 遥测
     */
    M_ME_NA_1(DataType.YC, (byte) 0x09, "测量值，归一化值", "M_ME_NA_1"),
    M_ME_NB_1(DataType.YC, (byte) 0x0b, "测量值，标度化值", "M_ME_NB_1"),
    M_ME_NC_1(DataType.YC, (byte) 0x0d, "测量值，短浮点数", "M_ME_NC_1"),


    /**
     * 遥信
     */
    M_SP_NA_1(DataType.YX, (byte) 0x01, "单点信息", "M_SP_NA_1"),
    M_DP_NA_1(DataType.YX, (byte) 0x03, "双点信息", "M_DP_NA_1"),

    /**
     * SOE事件
     */
    M_ME_TE_1(DataType.SOE, (byte) 0x1e, "带CP56Time2a时标的单点信息", "M_ME_TE_1"),
    M_ME_TF_1(DataType.SOE, (byte) 0x1f, "带CP56Time2a时标的双点信息", "M_ME_TF_1");


    private DataType dataType;
    private byte tiType;
    private String name;
    private String sign;


    Ti(DataType dataType, byte tiType, String name, String sign) {
        this.dataType = dataType;
        this.tiType = tiType;
        this.name = name;
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public DataType getDataType() {
        return dataType;
    }

    public byte getTiType() {
        return tiType;
    }

    public String getName() {
        return name;
    }

    public static Ti getTI(byte ti) {
        for (Ti type : Ti.values()) {
            if (type.getTiType() == ti) {
                return type;
            }
        }
        return null;
    }
}
