package main.com.handu.scada.protocol101.protocol.bean;

import main.com.handu.scada.protocol101.protocol.enums.DataType;

import java.util.Date;

/**
 * Created by 柳梦 on 2018/03/15.
 */
public class DataAttr {

    private String name;
    private Object value;
    private Date recordTime;
    private String unit;
    private DataType dataType;
    private Date soeTime;
    private int pointPosition;

    public int getPointPosition() {
        return pointPosition;
    }

    public void setPointPosition(int pointPosition) {
        this.pointPosition = pointPosition;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getSoeTime() {
        return soeTime;
    }

    public void setSoeTime(Date soeTime) {
        this.soeTime = soeTime;
    }

    @Override
    public String toString() {
        return "DataAttr{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", recordTime=" + recordTime +
                ", unit='" + unit + '\'' +
                ", dataType=" + dataType +
                ", soeTime=" + soeTime +
                ", pointPosition=" + pointPosition +
                '}';
    }
}
