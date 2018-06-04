package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceFaultindicatorSoe implements Serializable {
    private String soeid;

    private String deviceid;

    private String dataitem;

    private String value;

    private String dataitemname;

    private String unit;

    private String phase;

    private Integer soetype;

    private Integer level;

    private Date soetime;

    private Date recordtime;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getSoeid() {
        return soeid;
    }

    public void setSoeid(String soeid) {
        this.soeid = soeid == null ? null : soeid.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getDataitem() {
        return dataitem;
    }

    public void setDataitem(String dataitem) {
        this.dataitem = dataitem == null ? null : dataitem.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getDataitemname() {
        return dataitemname;
    }

    public void setDataitemname(String dataitemname) {
        this.dataitemname = dataitemname == null ? null : dataitemname.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase == null ? null : phase.trim();
    }

    public Integer getSoetype() {
        return soetype;
    }

    public void setSoetype(Integer soetype) {
        this.soetype = soetype;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getSoetime() {
        return soetime;
    }

    public void setSoetime(Date soetime) {
        this.soetime = soetime;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DeviceFaultindicatorSoe other = (DeviceFaultindicatorSoe) that;
        return (this.getSoeid() == null ? other.getSoeid() == null : this.getSoeid().equals(other.getSoeid()))
            && (this.getDeviceid() == null ? other.getDeviceid() == null : this.getDeviceid().equals(other.getDeviceid()))
            && (this.getDataitem() == null ? other.getDataitem() == null : this.getDataitem().equals(other.getDataitem()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getDataitemname() == null ? other.getDataitemname() == null : this.getDataitemname().equals(other.getDataitemname()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getPhase() == null ? other.getPhase() == null : this.getPhase().equals(other.getPhase()))
            && (this.getSoetype() == null ? other.getSoetype() == null : this.getSoetype().equals(other.getSoetype()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getSoetime() == null ? other.getSoetime() == null : this.getSoetime().equals(other.getSoetime()))
            && (this.getRecordtime() == null ? other.getRecordtime() == null : this.getRecordtime().equals(other.getRecordtime()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSoeid() == null) ? 0 : getSoeid().hashCode());
        result = prime * result + ((getDeviceid() == null) ? 0 : getDeviceid().hashCode());
        result = prime * result + ((getDataitem() == null) ? 0 : getDataitem().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getDataitemname() == null) ? 0 : getDataitemname().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getPhase() == null) ? 0 : getPhase().hashCode());
        result = prime * result + ((getSoetype() == null) ? 0 : getSoetype().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getSoetime() == null) ? 0 : getSoetime().hashCode());
        result = prime * result + ((getRecordtime() == null) ? 0 : getRecordtime().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}