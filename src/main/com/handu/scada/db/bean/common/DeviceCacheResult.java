package main.com.handu.scada.db.bean.common;

import main.com.handu.scada.cache.CacheCmdType;
import main.com.handu.scada.utils.StringsUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 柳梦 on 2018/04/20.
 */
public class DeviceCacheResult {

    private String dtuId;
    private String dtuAddress;
    private String deviceId;
    private String deviceName;
    private String deviceAddress;
    private int deviceType;
    private String deviceTableName;
    private String daId;
    private String daName;
    private String deviceLevel;
    private String terminalId;
    //附加属性
    private Map<String, AdditionProperty> additionProperties;
    //是否发送短信
    private boolean isSendMsg;
    //告警时间
    private Date alarmTime;

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public boolean isSendMsg() {
        return isSendMsg;
    }

    public void setSendMsg(boolean sendMsg) {
        isSendMsg = sendMsg;
    }

    private CacheCmdType cmdType = CacheCmdType.DEFAULT;

    public CacheCmdType getCmdType() {
        return cmdType;
    }

    public void setCmdType(CacheCmdType cmdType) {
        this.cmdType = cmdType;
    }

    public String getDaName() {
        return daName == null ? "" : daName;
    }

    public void setDaName(String daName) {
        this.daName = daName;
    }

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

    public String getDaId() {
        return daId;
    }

    public void setDaId(String daId) {
        this.daId = daId;
    }

    public int getDeviceLevel() {
        try {
            if (StringsUtils.isNotEmpty(deviceLevel)) {
                return Integer.parseInt(deviceLevel);
            }
        } catch (NumberFormatException e) {
            return 1;
        }
        return 1;
    }

    public void setDeviceLevel(String deviceLevel) {
        this.deviceLevel = deviceLevel;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public Map<String, AdditionProperty> getAdditionProperties() {
        return additionProperties;
    }

    public void setAdditionProperties(List<AdditionProperty> additionProperties) {
        this.additionProperties = new HashMap<>();
        additionProperties.forEach(e -> this.additionProperties.put(e.getName(), e));
    }
}
