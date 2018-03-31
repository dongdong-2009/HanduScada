package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceHistoryStaterecord implements Serializable {
    private String recordid;

    private String devicetablename;

    private String deviceid;

    private Integer state;

    private Date onlinetime;

    private Date unlinetime;

    private String duration;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid == null ? null : recordid.trim();
    }

    public String getDevicetablename() {
        return devicetablename;
    }

    public void setDevicetablename(String devicetablename) {
        this.devicetablename = devicetablename == null ? null : devicetablename.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOnlinetime() {
        return onlinetime;
    }

    public void setOnlinetime(Date onlinetime) {
        this.onlinetime = onlinetime;
    }

    public Date getUnlinetime() {
        return unlinetime;
    }

    public void setUnlinetime(Date unlinetime) {
        this.unlinetime = unlinetime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
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
        DeviceHistoryStaterecord other = (DeviceHistoryStaterecord) that;
        return (this.getRecordid() == null ? other.getRecordid() == null : this.getRecordid().equals(other.getRecordid()))
            && (this.getDevicetablename() == null ? other.getDevicetablename() == null : this.getDevicetablename().equals(other.getDevicetablename()))
            && (this.getDeviceid() == null ? other.getDeviceid() == null : this.getDeviceid().equals(other.getDeviceid()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getOnlinetime() == null ? other.getOnlinetime() == null : this.getOnlinetime().equals(other.getOnlinetime()))
            && (this.getUnlinetime() == null ? other.getUnlinetime() == null : this.getUnlinetime().equals(other.getUnlinetime()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordid() == null) ? 0 : getRecordid().hashCode());
        result = prime * result + ((getDevicetablename() == null) ? 0 : getDevicetablename().hashCode());
        result = prime * result + ((getDeviceid() == null) ? 0 : getDeviceid().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getOnlinetime() == null) ? 0 : getOnlinetime().hashCode());
        result = prime * result + ((getUnlinetime() == null) ? 0 : getUnlinetime().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}