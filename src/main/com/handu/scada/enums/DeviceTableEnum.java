package main.com.handu.scada.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by 柳梦 on 2017/12/11.
 */
public enum DeviceTableEnum {
    Device_Dtu(1, "device_dtu", DeviceGroup.DTU),
    Device_Rcd(2, "device_rcd", DeviceGroup.DTU_DEVICE, ""),
    Device_Temperature(5, "device_temperature", DeviceGroup.DTU_DEVICE, " SELECT TemperatureId AS deviceId, Address AS name, LimitValue AS value, MountingPosition AS description, '°C' AS unit FROM device_temperatureprobe WHERE TemperatureId IN ( "),
    Device_Falling_Type_Switch(7, "device_fallingtypeswitch", DeviceGroup.DTU_DEVICE, " SELECT DeviceId AS deviceId, DataItem AS name, Value AS value, Description AS description, Unit AS unit FROM device_real_remotesignalling WHERE DeviceId IN ( "),
    Device_Reactive_Power(9, "device_reactive_power", DeviceGroup.DTU_DEVICE, ""),
    Device_IntelligentSwitch(4, "device_intelligentswitch", DeviceGroup.SWITCH),
    Device_HM(9, "device_hm", DeviceGroup.HM),
    Device_TemperatureProbe(6, "device_temperatureprobe"),
    Device_Terminal(8, "device_terminal");

    private int tableType;
    private String tableName;
    private DeviceGroup group;
    private String sqlAdditionProperty;

    public String getSqlAdditionProperty() {
        return sqlAdditionProperty;
    }

    public DeviceGroup getGroup() {
        return group;
    }

    DeviceTableEnum(int tableType, String tableName, DeviceGroup group) {
        this.tableType = tableType;
        this.tableName = tableName;
        this.group = group;

    }

    DeviceTableEnum(int tableType, String tableName, DeviceGroup group, String sqlAdditionProperty) {
        this.tableType = tableType;
        this.tableName = tableName;
        this.group = group;
        this.sqlAdditionProperty = sqlAdditionProperty;
    }

    DeviceTableEnum(int tableType, String tableName) {
        this.tableType = tableType;
        this.tableName = tableName;
    }

    public int getTableType() {
        return tableType;
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
        return null;
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
