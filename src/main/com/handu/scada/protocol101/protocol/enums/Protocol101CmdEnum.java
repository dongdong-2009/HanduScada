package main.com.handu.scada.protocol101.protocol.enums;

/**
 * Created by 柳梦 on 2018/05/11.
 * 开关下发命令类型
 */
public enum Protocol101CmdEnum {

    PROTOCOL101_ON_LINE("101设备上线"),
    PROTOCOL101_OFF_LINE("101设备下线"),
    DEFAULT("其它"),
    ALL_CALL("总召"),
    CONFIRM("确认帧"),
    READ_FILE_START("读录波文件激活"),
    READ_FILE_CONFIRM("读录波文件数据传输确认"),
    READ_FILE_CATALOGUE("读录波取文件目录和文件名称"),
    READ_FILE_SUCCESS("读取录波文件完成"),
    CONSTANT_VALUE("读参数和定值");

    private String name = "";


    public String getName() {
        return name;
    }

    Protocol101CmdEnum(String name) {
        this.name = name;
    }
}
