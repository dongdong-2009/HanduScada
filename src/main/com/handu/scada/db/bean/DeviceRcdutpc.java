package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceRcdutpc implements Serializable {
    private String utpcid;

    private String deviceid;

    private Double maxutpc;

    private Date begintime;

    private Date endtime;

    private Integer duration;

    private String phase;

    private String workorderid;

    private Integer issendworkorder;

    private static final long serialVersionUID = 1L;

    public String getUtpcid() {
        return utpcid;
    }

    public void setUtpcid(String utpcid) {
        this.utpcid = utpcid == null ? null : utpcid.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public Double getMaxutpc() {
        return maxutpc;
    }

    public void setMaxutpc(Double maxutpc) {
        this.maxutpc = maxutpc;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase == null ? null : phase.trim();
    }

    public String getWorkorderid() {
        return workorderid;
    }

    public void setWorkorderid(String workorderid) {
        this.workorderid = workorderid == null ? null : workorderid.trim();
    }

    public Integer getIssendworkorder() {
        return issendworkorder;
    }

    public void setIssendworkorder(Integer issendworkorder) {
        this.issendworkorder = issendworkorder;
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
        DeviceRcdutpc other = (DeviceRcdutpc) that;
        return (this.getUtpcid() == null ? other.getUtpcid() == null : this.getUtpcid().equals(other.getUtpcid()))
            && (this.getDeviceid() == null ? other.getDeviceid() == null : this.getDeviceid().equals(other.getDeviceid()))
            && (this.getMaxutpc() == null ? other.getMaxutpc() == null : this.getMaxutpc().equals(other.getMaxutpc()))
            && (this.getBegintime() == null ? other.getBegintime() == null : this.getBegintime().equals(other.getBegintime()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getPhase() == null ? other.getPhase() == null : this.getPhase().equals(other.getPhase()))
            && (this.getWorkorderid() == null ? other.getWorkorderid() == null : this.getWorkorderid().equals(other.getWorkorderid()))
            && (this.getIssendworkorder() == null ? other.getIssendworkorder() == null : this.getIssendworkorder().equals(other.getIssendworkorder()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUtpcid() == null) ? 0 : getUtpcid().hashCode());
        result = prime * result + ((getDeviceid() == null) ? 0 : getDeviceid().hashCode());
        result = prime * result + ((getMaxutpc() == null) ? 0 : getMaxutpc().hashCode());
        result = prime * result + ((getBegintime() == null) ? 0 : getBegintime().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getPhase() == null) ? 0 : getPhase().hashCode());
        result = prime * result + ((getWorkorderid() == null) ? 0 : getWorkorderid().hashCode());
        result = prime * result + ((getIssendworkorder() == null) ? 0 : getIssendworkorder().hashCode());
        return result;
    }
}