package main.com.handu.scada.db.bean.common;

/**
 * Created by 柳梦 on 2018/05/24.
 * 101协议设备缓存
 */
public class Device101CacheResult {

    private String deviceId;
    private String deviceName;
    private String deviceAddress;
    private int deviceType;
    private String deviceTableName;
    private String lineId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceTableName() {
        return deviceTableName;
    }

    public void setDeviceTableName(String deviceTableName) {
        this.deviceTableName = deviceTableName;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    @Override
    public String toString() {
        return "Device101CacheResult{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", deviceType=" + deviceType +
                ", deviceTableName='" + deviceTableName + '\'' +
                ", lineId='" + lineId + '\'' +
                '}';
    }
}
