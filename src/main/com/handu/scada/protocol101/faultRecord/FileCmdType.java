package main.com.handu.scada.protocol101.faultRecord;

/**
 * Created by 柳梦 on 2018/05/30.
 * 文件操作类型
 */
public enum FileCmdType {

    //读目录
    READ_CATALOGUE((byte) 0X02),
    //读文件激活
    READ_FILE_START((byte) 0X03),
    //读文件激活确认
    READ_FILE_START_CONFIRM((byte) 0X04),
    //读文件响应
    READ_FILE_RESPONSE((byte) 0X05),
    //读文件响应确认
    READ_FILE_RESPONSE_CONFIRM((byte) 0X06);

    private byte id;

    FileCmdType(byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    public static FileCmdType getFileCmdType(byte cmdType) {
        for (FileCmdType type : FileCmdType.values()) {
            if (type.id == cmdType) return type;
        }
        return null;
    }
}
