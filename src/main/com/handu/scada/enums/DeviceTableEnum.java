package main.com.handu.scada.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by 柳梦 on 2017/12/11.
 */
public enum DeviceTableEnum {

    Device_NONE("未知设备", ""),
    Device_Dtu("DTU", "device_dtu", DeviceGroup.DTU),
    Device_Rcd("漏保", "device_rcd", DeviceGroup.DTU_DEVICE, ""),
    Device_Temperature("测温装置", "device_temperature", DeviceGroup.DTU_DEVICE, " SELECT TemperatureId AS deviceId, Address AS name, LimitValue AS value, MountingPosition AS description, '°C' AS unit FROM device_temperatureprobe WHERE TemperatureId IN ( "),
    Device_Falling_Type_Switch("跌落熔断器", "device_fallingtypeswitch", DeviceGroup.DTU_DEVICE, " SELECT DeviceId AS deviceId, DataItem AS name, Value AS value, Description AS description, Unit AS unit FROM device_real_remotesignalling WHERE DeviceId IN ( "),
    Device_Reactive_Power("无功设备", "device_reactive_power", DeviceGroup.DTU_DEVICE, ""),
    Device_IntelligentSwitch("开关", "device_switch", DeviceGroup.PROTOCOL101_DEVICE),
    Device_FaultIndicator("故障指示器", "device_faultIndicator", DeviceGroup.PROTOCOL101_DEVICE),
    Device_HM("台区总表", "device_hm", DeviceGroup.HM);

    private String deviceName;
    private String tableName;
    private DeviceGroup group;
    private String sqlAdditionProperty;

    public String getSqlAdditionProperty() {
        return sqlAdditionProperty;
    }

    public DeviceGroup getGroup() {
        return group;
    }

    DeviceTableEnum(String deviceName, String tableName, DeviceGroup group) {
        this.deviceName = deviceName;
        this.tableName = tableName;
        this.group = group;

    }

    DeviceTableEnum(String deviceName, String tableName, DeviceGroup group, String sqlAdditionProperty) {
        this.deviceName = deviceName;

        this.tableName = tableName;
        this.group = group;
        this.sqlAdditionProperty = sqlAdditionProperty;
    }

    DeviceTableEnum(String deviceName, String tableName) {
        this.deviceName = deviceName;
        this.tableName = tableName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getTableName() {
        return tableName;
    }


    /**
     * 根据表类型获取设备
     *
     * @param tableName
     * @return
     */
    public static DeviceTableEnum getDeviceByTableName(String tableName) {
        for (DeviceTableEnum c : DeviceTableEnum.values()) {
            if (Objects.equals(c.getTableName().toLowerCase(), tableName.toLowerCase())) {
                return c;
            }
        }
        return Device_NONE;
    }

    public static List<DeviceTableEnum> getDeviceByGroup(DeviceGroup deviceGroup) {
        List<DeviceTableEnum> list = new ArrayList<>();
        for (DeviceTableEnum c : DeviceTableEnum.values()) {
            if (c.getGroup() == deviceGroup) {
                list.add(c);
            }
        }
        return list;
    }
}
