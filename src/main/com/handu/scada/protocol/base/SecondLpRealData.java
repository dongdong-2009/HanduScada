package main.com.handu.scada.protocol.base;

import main.com.handu.scada.db.bean.DeviceRcd;
import main.com.handu.scada.db.bean.RelationDtuDevice;

import java.util.List;

/**
 * Created by 柳梦 on 2018/03/08.
 */
public class SecondLpRealData {

    public String getDtuAddress() {
        return dtuAddress;
    }

    public void setDtuAddress(String dtuAddress) {
        this.dtuAddress = dtuAddress;
    }

    public String getDtuId() {
        return dtuId;
    }

    public void setDtuId(String dtuId) {
        this.dtuId = dtuId;
    }

    public List<SecondLpRecord> getSecondLpRecords() {
        return secondLpRecords;
    }

    public void setSecondLpRecords(List<SecondLpRecord> secondLpRecords) {
        this.secondLpRecords = secondLpRecords;
    }

    public String getDaId() {
        return daId;
    }

    public void setDaId(String daId) {
        this.daId = daId;
    }

    private String dtuAddress;
    private String dtuId;
    private List<SecondLpRecord> secondLpRecords;
    private String daId;
    private String daName;
    //一级漏保Id
    private String deviceId;
    private String terminalId;
    private DeviceRcd deviceRcd;
    private RelationDtuDevice relationDtuDevice;

    public RelationDtuDevice getRelationDtuDevice() {
        return relationDtuDevice;
    }

    public void setRelationDtuDevice(RelationDtuDevice relationDtuDevice) {
        this.relationDtuDevice = relationDtuDevice;
    }

    public String getDaName() {
        return daName;
    }

    public void setDaName(String daName) {
        this.daName = daName;
    }

    public DeviceRcd getDeviceRcd() {
        return deviceRcd;
    }

    public void setDeviceRcd(DeviceRcd deviceRcd) {
        this.deviceRcd = deviceRcd;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
