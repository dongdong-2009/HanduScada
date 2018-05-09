package main.com.handu.scada.business.message;

/**
 * Created by 柳梦 on 2018/05/09.
 * 设备短信发送附属信息
 */
public class MsgAdditionProperty {

    private String deviceId;
    private String alarms;
    private String name;
    private String phone;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAlarms() {
        return alarms;
    }

    public void setAlarms(String alarms) {
        this.alarms = alarms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
