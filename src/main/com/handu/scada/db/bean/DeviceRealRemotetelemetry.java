package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceRealRemotetelemetry implements Serializable {
    private String remotetelemetryid;

    private String remoteindexsid;

    private String value;

    private Date recordtime;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getRemotetelemetryid() {
        return remotetelemetryid;
    }

    public void setRemotetelemetryid(String remotetelemetryid) {
        this.remotetelemetryid = remotetelemetryid == null ? null : remotetelemetryid.trim();
    }

    public String getRemoteindexsid() {
        return remoteindexsid;
    }

    public void setRemoteindexsid(String remoteindexsid) {
        this.remoteindexsid = remoteindexsid == null ? null : remoteindexsid.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
        DeviceRealRemotetelemetry other = (DeviceRealRemotetelemetry) that;
        return (this.getRemotetelemetryid() == null ? other.getRemotetelemetryid() == null : this.getRemotetelemetryid().equals(other.getRemotetelemetryid()))
            && (this.getRemoteindexsid() == null ? other.getRemoteindexsid() == null : this.getRemoteindexsid().equals(other.getRemoteindexsid()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getRecordtime() == null ? other.getRecordtime() == null : this.getRecordtime().equals(other.getRecordtime()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRemotetelemetryid() == null) ? 0 : getRemotetelemetryid().hashCode());
        result = prime * result + ((getRemoteindexsid() == null) ? 0 : getRemoteindexsid().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getRecordtime() == null) ? 0 : getRecordtime().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}