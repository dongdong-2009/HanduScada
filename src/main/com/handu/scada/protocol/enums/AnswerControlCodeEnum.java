package main.com.handu.scada.protocol.enums;

public enum AnswerControlCodeEnum {
    ///[Description("上行读取数据]
    ReadData(0x91),
    ///[Description("下行设置数据]
    WriteData(0x94),
    ///[Description("读漏保地址]
    ReadAddress(0x93),
    ///[Description("写漏保地址]
    WriteAddress(0x95),
    ///[Description("修改波特率]
    ChangeRate(0x97),
    ///[Description("修改密码]
    UpdatePwd(0x98),
    ///[Description("远程控制指令]
    ControlCmd(0x9C),
    ///[Description("上行应答读数据]
    ReadDataQLLP1997(0x81),
    ///[Description("上行应答写数据]
    WriteDataQLLP1997(0x84),
    ///[Description("上行错误读取数据]
    ReadDataQLLP1997Error(0xC1),
    ///[Description("上行错误读取数据]
    WriteDataQLLP1997Error(0xff);

    private int value;

    public int getValue() {
        return value;
    }

    AnswerControlCodeEnum(int value) {
        this.value = value;
    }

    public static AnswerControlCodeEnum getControlCode(byte b) {

        for (AnswerControlCodeEnum controlCodeEnum : AnswerControlCodeEnum.values()) {
            if ((byte) controlCodeEnum.getValue() == b) {

                return controlCodeEnum;
            }
        }
        return null;
    }

}
