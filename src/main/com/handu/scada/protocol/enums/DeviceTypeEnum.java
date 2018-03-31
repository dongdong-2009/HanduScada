package main.com.handu.scada.protocol.enums;

import main.com.handu.scada.enums.TableEnum;

import java.util.Objects;

/**
 * Created by 柳梦 on 2018/01/25.
 */
public enum DeviceTypeEnum {

    DTU("DTU", TableEnum.Device_Dtu.getTableName().toLowerCase()),
    LP("漏保", TableEnum.Device_Rcd.getTableName().toLowerCase(), 1),
    SECOND_LP("二级漏保", TableEnum.Device_Rcd.getTableName().toLowerCase(), 2),
    SWITCH("开关", TableEnum.Device_IntelligentSwitch.getTableName().toLowerCase()),
    FALL_TYPE_SWITCH("跌落开关", TableEnum.Device_Falling_Type_Switch.getTableName().toLowerCase()),
    THERMOMETRY("测温", TableEnum.Device_Temperature.getTableName().toLowerCase());

    private String name;
    private String tableName;
    private int level;

    public String getName() {
        return name;
    }

    public String getTableName() {
        return tableName;
    }

    public int getLevel() {
        return level;
    }

    DeviceTypeEnum(String name, String tableName, int level) {
        this.tableName = tableName;
        this.name = name;
        this.level = level;
    }

    DeviceTypeEnum(String name, String tableName) {
        this.tableName = tableName;
        this.name = name;
    }

    DeviceTypeEnum() {
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
