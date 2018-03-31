package main.com.handu.scada.switch101.protocol.bean;

import main.com.handu.scada.switch101.protocol.enums.DataType;
import main.com.handu.scada.switch101.protocol.enums.Ti;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 柳梦 on 2018/03/15.
 */
public class BaseData {

    private List<DataAttr> dataAttrs;
    private DataType dataType;
    private Ti TI;
    private String terminalCode;
    private String address;
    private byte[] commandData;

    public Ti getTI() {
        return TI;
    }

    public void setTI(Ti TI) {
        this.TI = TI;
    }

    public BaseData(byte[] commandData) {
        this.commandData = commandData;
    }

    public byte[] getCommandData() {
        return commandData;
    }

    public void setCommandData(byte[] commandData) {
        this.commandData = commandData;
    }

    public String getAddress() {
        return address;
    }

    public List<DataAttr> getDataAttrs() {
        return dataAttrs;
    }

    public void setDataAttrs(List<DataAttr> dataAttrs) {
        this.dataAttrs = dataAttrs;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "dataAttrs=" + dataAttrs +
                ", dataType=" + dataType +
                ", TI=" + TI +
                ", terminalCode='" + terminalCode + '\'' +
                ", address='" + address + '\'' +
                ", commandData=" + Arrays.toString(commandData) +
                '}';
    }
}
