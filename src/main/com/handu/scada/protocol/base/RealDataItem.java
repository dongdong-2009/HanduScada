package main.com.handu.scada.protocol.base;

import main.com.handu.scada.protocol.protocol.Data.DataAttr;

import java.util.List;

/**
 * Created by 柳梦 on 2017/12/29.
 */
public class RealDataItem {

    public String address;
    public String deviceTableName;
    public String deviceId;
    public String postalAddress;
    public String dtuAddress;
    public List<DataAttr> list;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceTableName() {
        return deviceTableName;
    }

    public void setDeviceTableName(String deviceTableName) {
        this.deviceTableName = deviceTableName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public List<DataAttr> getList() {
        return list;
    }

    public void setList(List<DataAttr> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RealDataItem{" +
                "address='" + address + '\'' +
                ", deviceTableName='" + deviceTableName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", postalAddress='" + postalAddress + '\'' +
                ", list=" + list +
                '}';
    }
}
