package main.com.handu.scada.protocol101.protocol.bean;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.protocol101.json.PointJsonData;
import main.com.handu.scada.protocol101.protocol.enums.DataType;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;
import main.com.handu.scada.protocol101.protocol.enums.Ti;

import java.util.List;

/**
 * Created by 柳梦 on 2018/03/15.
 * 101协议设备的数据类
 */
public class Protocol101BaseData {

    private String ip;
    private String port;
    private List<DataAttr> dataAttrs;
    private DataType dataType;
    private Ti TI;
    private List<PointJsonData> pointJsonDataList;
    private MsgPriority priority = MsgPriority.LOW;
    //控制码
    private byte controlCode;
    private String terminalCode;
    private String address;
    //上行或者下行报文
    private byte[] commandData;
    //命令类型
    private Protocol101CmdEnum cmdType;
    //设备类型，开关，故障指示器等等
    private DeviceTypeEnum deviceType = DeviceTypeEnum.SWITCH;

    public MsgPriority getPriority() {
        return priority;
    }

    public void setPriority(MsgPriority priority) {
        this.priority = priority;
    }

    public DeviceTypeEnum getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceTypeEnum deviceType) {
        this.deviceType = deviceType;
    }

    public Protocol101CmdEnum getCmdType() {
        return cmdType;
    }

    public void setCmdType(Protocol101CmdEnum cmdType) {
        this.cmdType = cmdType;
    }

    public Ti getTI() {
        return TI;
    }

    public void setTI(Ti TI) {
        this.TI = TI;
    }

    public Protocol101BaseData(byte[] commandData) {
        this.commandData = commandData;
    }

    public Protocol101BaseData() {
    }

    public byte getControlCode() {
        return controlCode;
    }

    public void setControlCode(byte controlCode) {
        this.controlCode = controlCode;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public List<PointJsonData> getPointJsonDataList() {
        return pointJsonDataList;
    }

    public void setPointJsonDataList(List<PointJsonData> pointJsonDataList) {
        this.pointJsonDataList = pointJsonDataList;
    }

    @Override
    public String toString() {
        return "Protocol101BaseData{" +
                "dataAttrs=" + dataAttrs +
                '}';
    }
}