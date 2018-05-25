package main.com.handu.scada.enums;

import java.util.Objects;

/**
 * Created by 柳梦 on 2018/01/25.
 */
public enum DeviceTypeEnum {

    DTU(0, "DTU", DeviceTableEnum.Device_Dtu.getTableName().toLowerCase(), DeviceGroup.DTU),
    DTU4G(99, "4G营配模块", DeviceTableEnum.Device_Dtu.getTableName().toLowerCase(), 1, DeviceGroup.DTU),
    LP2007(8, "国标漏保", DeviceTableEnum.Device_Rcd.getTableName().toLowerCase(), 1, DeviceGroup.LP2007),
    SECOND_LP2007(8, "国标二级漏保", DeviceTableEnum.Device_Rcd.getTableName().toLowerCase(), 2, DeviceGroup.LP2007),
    LP1997(9, "乾隆漏保", DeviceTableEnum.Device_Rcd.getTableName().toLowerCase(), 1, DeviceGroup.LP1997),
    SWITCH(-99, "开关", DeviceTableEnum.Device_IntelligentSwitch.getTableName().toLowerCase(), DeviceGroup.PROTOCOL101_DEVICE),
    HM(-97, "台区总表", DeviceTableEnum.Device_HM.getTableName().toLowerCase(), DeviceGroup.HM),
    FALL_TYPE_SWITCH(98, "跌落开关", DeviceTableEnum.Device_Falling_Type_Switch.getTableName().toLowerCase(), DeviceGroup.FALL_TYPE_SWITCH),
    REACTIVE_POWER(18, "智能电容器(无功设备)", DeviceTableEnum.Device_Reactive_Power.getTableName().toLowerCase(), DeviceGroup.REACTIVE_POWER),
    WIRED_TEMPERATURE(24, "优科测温", DeviceTableEnum.Device_Temperature.getTableName().toLowerCase(), DeviceGroup.WIRED_TEMPERATURE),
    WIRELESS_TEMPERATURE(97, "跌落无线测温", DeviceTableEnum.Device_Temperature.getTableName().toLowerCase(), DeviceGroup.WIRELESS_TEMPERATURE);

    private int deviceType;
    private String name;
    private String tableName;
    private int level;
    private DeviceGroup group;

    public int getDeviceType() {
        return deviceType;
    }

    public DeviceGroup getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getTableName() {
        return tableName;
    }

    public int getLevel() {
        return level;
    }

    DeviceTypeEnum(int deviceType, String name, String tableName, int level) {
        this.deviceType = deviceType;
        this.tableName = tableName;
        this.name = name;
        this.level = level;
    }

    DeviceTypeEnum(int deviceType, String name, String tableName, int level, DeviceGroup group) {
        this.deviceType = deviceType;
        this.tableName = tableName;
        this.name = name;
        this.level = level;
        this.group = group;
    }

    DeviceTypeEnum(int deviceType, String name, String tableName, DeviceGroup group) {
        this.deviceType = deviceType;
        this.tableName = tableName;
        this.name = name;
        this.group = group;
    }

    public static DeviceTypeEnum getDeviceTypeByLevel(int level) {
        for (DeviceTypeEnum deviceTypeEnum : DeviceTypeEnum.values()) {
            if (deviceTypeEnum.level == level) {
                return deviceTypeEnum;
            }
        }
        return null;
    }

    public static DeviceTypeEnum getDeviceTypeByTableName(String tableName) {
        for (DeviceTypeEnum deviceTypeEnum : DeviceTypeEnum.values()) {
            if (Objects.equals(deviceTypeEnum.tableName, tableName)) {
                return deviceTypeEnum;
            }
        }
        return null;
    }
}
