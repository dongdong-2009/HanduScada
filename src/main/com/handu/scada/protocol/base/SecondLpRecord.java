package main.com.handu.scada.protocol.base;

/**
 * Created by 柳梦 on 2018/03/08.
 */
public class SecondLpRecord {

    //dtu地址
    private String dtuAddress;
    //二级漏保地址
    private String lpAddress;
    //波特率
    private int baudRate;
    //串口
    private int serialPort;
    //漏保类型
    private int type;

    //额定电压,电流,剩余电流
    private Double ratedUa;
    private Double ratedUb;
    private Double ratedUc;
    private Double ratedIa;
    private Double ratedIb;
    private Double ratedIc;
    private Double ratedIo;

    public String getDtuAddress() {
        return dtuAddress;
    }

    public void setDtuAddress(String dtuAddress) {
        this.dtuAddress = dtuAddress;
    }

    public String getLpAddress() {
        return lpAddress;
    }

    public void setLpAddress(String lpAddress) {
        this.lpAddress = lpAddress;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(int serialPort) {
        this.serialPort = serialPort;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getRatedUa() {
        return ratedUa;
    }

    public void setRatedUa(Double ratedUa) {
        this.ratedUa = ratedUa;
    }

    public Double getRatedUb() {
        return ratedUb;
    }

    public void setRatedUb(Double ratedUb) {
        this.ratedUb = ratedUb;
    }

    public Double getRatedUc() {
        return ratedUc;
    }

    public void setRatedUc(Double ratedUc) {
        this.ratedUc = ratedUc;
    }

    public Double getRatedIa() {
        return ratedIa;
    }

    public void setRatedIa(Double ratedIa) {
        this.ratedIa = ratedIa;
    }

    public Double getRatedIb() {
        return ratedIb;
    }

    public void setRatedIb(Double ratedIb) {
        this.ratedIb = ratedIb;
    }

    public Double getRatedIc() {
        return ratedIc;
    }

    public void setRatedIc(Double ratedIc) {
        this.ratedIc = ratedIc;
    }

    public Double getRatedIo() {
        return ratedIo;
    }

    public void setRatedIo(Double ratedIo) {
        this.ratedIo = ratedIo;
    }

    @Override
    public String toString() {
        return "SecondLpRecord{" +
                "dtuAddress='" + dtuAddress + '\'' +
                ", lpAddress='" + lpAddress + '\'' +
                ", baudRate=" + baudRate +
                ", serialPort=" + serialPort +
                ", type=" + type +
                ", ratedUa=" + ratedUa +
                ", ratedUb=" + ratedUb +
                ", ratedUc=" + ratedUc +
                ", ratedIa=" + ratedIa +
                ", ratedIb=" + ratedIb +
                ", ratedIc=" + ratedIc +
                ", ratedIo=" + ratedIo +
                '}';
    }
}
