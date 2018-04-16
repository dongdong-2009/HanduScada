package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceConcentratorLastHeartbeatTime implements Serializable {
    private Long id;

    private String dtuid;

    private Date lastheartbeattime;

    private Date recordtime;

    private String dtuAddress;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDtuid() {
        return dtuid;
    }

    public void setDtuid(String dtuid) {
        this.dtuid = dtuid == null ? null : dtuid.trim();
    }

    public Date getLastheartbeattime() {
        return lastheartbeattime;
    }

    public void setLastheartbeattime(Date lastheartbeattime) {
        this.lastheartbeattime = lastheartbeattime;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public String getDtuAddress() {
        return dtuAddress;
    }

    public void setDtuAddress(String dtuAddress) {
        this.dtuAddress = dtuAddress;
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
        DeviceConcentratorLastHeartbeatTime other = (DeviceConcentratorLastHeartbeatTime) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDtuid() == null ? other.getDtuid() == null : this.getDtuid().equals(other.getDtuid()))
            && (this.getLastheartbeattime() == null ? other.getLastheartbeattime() == null : this.getLastheartbeattime().equals(other.getLastheartbeattime()))
            && (this.getRecordtime() == null ? other.getRecordtime() == null : this.getRecordtime().equals(other.getRecordtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDtuid() == null) ? 0 : getDtuid().hashCode());
        result = prime * result + ((getLastheartbeattime() == null) ? 0 : getLastheartbeattime().hashCode());
        result = prime * result + ((getRecordtime() == null) ? 0 : getRecordtime().hashCode());
        return result;
    }
}