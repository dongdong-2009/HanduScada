package main.com.handu.scada.protocol.protocol.Data;

import main.com.handu.scada.protocol.enums.RemoteType;

import java.util.Date;

public class DataAttr {

    private RemoteType dateType;
    private String name;
    private String cnname;
    private String group;
    private Object value;
    private String unit;
    private Date dtime;
    private boolean recordchange;
    //是否入历史库
    private boolean isInsertHistory = true;

    public boolean isRecordchange() {
        return recordchange;
    }

    public boolean isInsertHistory() {
        return isInsertHistory;
    }

    public void setInsertHistory(boolean insertHistory) {
        isInsertHistory = insertHistory;
    }

    public RemoteType getDateType() {
        return dateType;
    }

    public void setDateType(RemoteType dateType) {
        this.dateType = dateType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public boolean getRecordchange() {
        return recordchange;
    }

    public void setRecordchange(boolean recordchange) {
        this.recordchange = recordchange;
    }

    @Override
    public String toString() {
        return "DataAttr{" +
                "dateType=" + dateType +
                ", name='" + name + '\'' +
                ", cnname='" + cnname + '\'' +
                ", group='" + group + '\'' +
                ", value=" + value +
                ", unit='" + unit + '\'' +
                ", dtime=" + dtime +
                ", recordchange=" + recordchange +
                ", isInsertHistory=" + isInsertHistory +
                '}';
    }
}
