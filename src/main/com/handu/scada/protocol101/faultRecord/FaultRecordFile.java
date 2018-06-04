package main.com.handu.scada.protocol101.faultRecord;

/**
 * Created by 柳梦 on 2018/05/30.
 * 录波文件信息
 */
public class FaultRecordFile {

    //文件id
    private byte id1;
    private byte id2;
    private byte id3;
    private byte id4;
    //文件大小
    private byte size1;
    private byte size2;
    private byte size3;
    private byte size4;
    //文件名称
    private String name;
    //产生日期
    private String date;
    //文件内容
    private byte file[];
    //数据编号
    private byte[] dataNum;
    //后续标识
    private byte more;
    //已传输数据
    private int size;

    //是否读取成功
    public boolean isSuccess() {
        //这里只判断了前两位,后两位应该都是0x00
        return (size2 << 8) + size1 == size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size += size;
    }

    public FaultRecordFile() {
    }

    public FaultRecordFile(String name, byte id1, byte id2, byte id3, byte id4, byte size1, byte size2, byte size3, byte size4) {
        this.name = name;
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
        this.id4 = id4;
        this.size1 = size1;
        this.size2 = size2;
        this.size3 = size3;
        this.size4 = size4;
    }

    public byte getMore() {
        return more;
    }

    public void setMore(byte more) {
        this.more = more;
    }

    public byte[] getDataNum() {
        return dataNum;
    }

    public void setDataNum(byte[] dataNum) {
        this.dataNum = dataNum;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        if (this.file != null) {
            byte[] data = new byte[this.file.length + file.length];
            System.arraycopy(this.file, 0, data, 0, this.file.length);
            System.arraycopy(file, 0, data, this.file.length, file.length);
            this.file = data;
        } else {
            this.file = file;
        }
    }

    public byte getId1() {
        return id1;
    }

    public void setId1(byte id1) {
        this.id1 = id1;
    }

    public byte getId2() {
        return id2;
    }

    public void setId2(byte id2) {
        this.id2 = id2;
    }

    public byte getId3() {
        return id3;
    }

    public void setId3(byte id3) {
        this.id3 = id3;
    }

    public byte getId4() {
        return id4;
    }

    public void setId4(byte id4) {
        this.id4 = id4;
    }

    public byte getSize1() {
        return size1;
    }

    public void setSize1(byte size1) {
        this.size1 = size1;
    }

    public byte getSize2() {
        return size2;
    }

    public void setSize2(byte size2) {
        this.size2 = size2;
    }

    public byte getSize3() {
        return size3;
    }

    public void setSize3(byte size3) {
        this.size3 = size3;
    }

    public byte getSize4() {
        return size4;
    }

    public void setSize4(byte size4) {
        this.size4 = size4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean findFiledById(byte[] id) {
        return this.id1 == id[0] && this.id2 == id[1] && this.id3 == id[2] && this.id4 == id[3];
    }
}
