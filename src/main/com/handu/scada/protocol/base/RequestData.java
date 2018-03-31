package main.com.handu.scada.protocol.base;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;

/**
 * Created by 柳梦 on 2018/01/03.
 */
public class RequestData {

    private String tableName;
    private String tableId;
    private String dtuAddress;
    private String deviceAddress;
    private String hexdata;
    private DeviceCmdTypeEnum cmdType;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getDtuAddress() {
        return dtuAddress;
    }

    public void setDtuAddress(String dtuAddress) {
        this.dtuAddress = dtuAddress;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getHexdata() {
        return hexdata;
    }

    public void setHexdata(String hexdata) {
        this.hexdata = hexdata;
    }

    public DeviceCmdTypeEnum getCmdType() {
        return cmdType;
    }

    public void setCmdType(DeviceCmdTypeEnum cmdType) {
        this.cmdType = cmdType;
    }
    //    public TemperatureCmdType tempCmdType ;
}
