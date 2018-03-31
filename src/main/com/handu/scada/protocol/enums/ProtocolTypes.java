package main.com.handu.scada.protocol.enums;

/**
 * Created by 柳梦 on 2017/12/22.
 */
public enum ProtocolTypes {

    //("缺省")
    None(0),
    //("博威规约")
    BoWei(1),  // 1
    //("平衡式101协议")
    GD2002Blance101(2),  // 2
    //("非平衡式101规约")
    Nonequilibrium101(3),  // 3
    //("104规约")
    IEC104(4),  // 4
    //("台区台表协议DLT6452007")
    DLTHM6452007(5),
    //("交流采样装置通信协议3761")
    GDW3761(6),  // 6
    //("DL/T 645-1997协议")
    DLT6451997(7),  // 7
    //("国标协议DLT645-2007")
    DLTLP6452007(8),
    //("乾龙漏保协议")
    DLTLP6451997(9),
    //("天目漏保协议")
    TianMuLP(10),
    //("漏保DL/T20V5协议")
    DLT20V5(11),
    //("南瑞101协议")
    NRBalance101(13),
    //("GDW3761-2005")
    GDW37612005(14),
    //("保定101协议")
    BDBalance101(15),
    //("科林104协议")
    KLBalance101(16),
    //("保定104协议")
    BD104(17),
    //("科锐101协议")
    KRBalance101(18),
    //("苏益漏保协议")
    SUYI(19),
    //("亿安模拟串口协议")
    YIANCOM(20),
    //("浙江乾龙MODBUS协议")
    ZJQLMODBUS(21),
    //("浙江上力通信协议")
    ZJSHL(22),
    //("短信协议")
    GsmEnglish(23),
    //("有线测温协议")
    WiredTemperature(24),
    //("无线测温协议")
    WirelessTemperature(25);

    private int type;

    ProtocolTypes(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
