package main.com.handu.scada.protocol101.protocol.enums;

import main.com.handu.scada.utils.HexUtils;

/**
 * Created by 柳梦 on 2018/03/15.
 * 报文ti值类型
 */
public enum Ti {

    /**
     * 总召
     */
    C_IC_NA_1(DataType.NONE, (byte) 0x64, "站总召唤命令", "C_IC_NA_1"),

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
    M_ME_TF_1(DataType.SOE, (byte) 0x1f, "带CP56Time2a时标的双点信息", "M_ME_TF_1"),


    C_CI_NA_1(DataType.NONE, (byte) 0x65, "电能量召唤命令", "C_CI_NA_1"),
    C_TS_NA_1(DataType.NONE, (byte) 0x68, "测试命令", "C_TS_NA_1"),
    C_RP_NA_1(DataType.NONE, (byte) 0x69, "复位进程命令", "C_RP_NA_1"),
    C_SR_NA_1(DataType.NONE, (byte) 0xc8, "切换定值区", "C_SR_NA_1"),
    C_RR_NA_1(DataType.NONE, (byte) 0xc9, "读定值区号", "C_RR_NA_1"),
    C_RS_NA_1(DataType.CV, (byte) 0xca, "读参数和定值", "C_RS_NA_1"),
    C_WS_NA_1(DataType.NONE, (byte) 0xcb, "写参数和定值", "C_WS_NA_1"),
    M_IT_NB_1(DataType.NONE, (byte) 0xce, "累计量，短浮点数", "M_IT_NB_1"),
    F_FR_NA_1(DataType.FILE, (byte) 0xd2, "文件传输", "F_FR_NA_1"),
    F_SR_NA_1(DataType.NONE, (byte) 0xd3, "软件升级", "F_SR_NA_1"),

    M_IT_TC_1(DataType.NONE, HexUtils.intToByte(207), "带CP56Time2a时标的累计量，短浮点数", "M_IT_TC_1");

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
