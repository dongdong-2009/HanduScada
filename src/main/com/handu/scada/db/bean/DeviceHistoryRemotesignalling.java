package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceHistoryRemotesignalling implements Serializable {
    private String remotesignallingid;

    private Date recordtime;

    private String remoteindexsid;

    private Integer value;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getRemotesignallingid() {
        return remotesignallingid;
    }

    public void setRemotesignallingid(String remotesignallingid) {
        this.remotesignallingid = remotesignallingid == null ? null : remotesignallingid.trim();
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public String getRemoteindexsid() {
        return remoteindexsid;
    }

    public void setRemoteindexsid(String remoteindexsid) {
        this.remoteindexsid = remoteindexsid == null ? null : remoteindexsid.trim();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
        DeviceHistoryRemotesignalling other = (DeviceHistoryRemotesignalling) that;
        return (this.getRemotesignallingid() == null ? other.getRemotesignallingid() == null : this.getRemotesignallingid().equals(other.getRemotesignallingid()))
            && (this.getRecordtime() == null ? other.getRecordtime() == null : this.getRecordtime().equals(other.getRecordtime()))
            && (this.getRemoteindexsid() == null ? other.getRemoteindexsid() == null : this.getRemoteindexsid().equals(other.getRemoteindexsid()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRemotesignallingid() == null) ? 0 : getRemotesignallingid().hashCode());
        result = prime * result + ((getRecordtime() == null) ? 0 : getRecordtime().hashCode());
        result = prime * result + ((getRemoteindexsid() == null) ? 0 : getRemoteindexsid().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}