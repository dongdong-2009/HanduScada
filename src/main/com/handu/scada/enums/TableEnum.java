package main.com.handu.scada.enums;

import main.com.handu.scada.db.bean.DeviceRcd;
import main.com.handu.scada.db.bean.DeviceRemoteindexs;

/**
 * Created by 柳梦 on 2017/12/11.
 */
public enum TableEnum {
    Device_Dtu(1, "device_dtu", Object.class, " Oid,Address,TerminalId,PORT "),
    Device_Rcd(2, "device_rcd", DeviceRcd.class, " Oid,Address,TerminalId "),
    Device_HM(3, "device_hm", Object.class, " * "),
    Device_Falling_Type_Switch(3, "device_fallingtypeswitch", Object.class, " * "),
    Device_IntelligentSwitch(4, "device_intelligentswitch", Object.class, " * "),
    Device_Temperature(5, "device_temperature", Object.class, " * "),
    Device_TemperatureProbe(6, "device_temperatureprobe", Object.class, " * "),
    Relation_Dtu_Device(7, "relation_dtu_device", Object.class, " Id,DtuId,DeviceId,DeviceTableName "),
    Device_Terminal(8, "device_terminal", Object.class, " * "),
    Device_Remote_index(9, "device_remoteindexs", DeviceRemoteindexs.class, " * ");

    private int tableType;
    private String tableName;
    private Class<?> entityClass;
    private String col;

    public String getCol() {
        return col;
    }

    TableEnum(int tableType, String tableName, Class<?> clazz, String col) {
        this.tableType = tableType;
        this.tableName = tableName;
        this.entityClass = clazz;
        this.col = col;
    }

    public int getTableType() {
        return tableType;
    }

    public String getTableName() {
        return tableName;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    /**
     * 根据类型获取表名
     *
     * @param tableType
     * @return
     */
    public static TableEnum getTableNameByType(int tableType) {
        for (TableEnum c : TableEnum.values()) {
            if (c.getTableType() == tableType) {
                return c;
            }
        }
        return null;
    }
}
