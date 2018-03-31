package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceLowvoltage implements Serializable {
    private String lowuid;

    private String phase;

    private Date begintime;

    private Date endtime;

    private String deviceid;

    private Integer duration;

    private Integer minu;

    private String workorderid;

    private Integer issendworkorder;

    private static final long serialVersionUID = 1L;

    public String getLowuid() {
        return lowuid;
    }

    public void setLowuid(String lowuid) {
        this.lowuid = lowuid == null ? null : lowuid.trim();
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase == null ? null : phase.trim();
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

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getMinu() {
        return minu;
    }

    public void setMinu(Integer minu) {
        this.minu = minu;
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
        DeviceLowvoltage other = (DeviceLowvoltage) that;
        return (this.getLowuid() == null ? other.getLowuid() == null : this.getLowuid().equals(other.getLowuid()))
            && (this.getPhase() == null ? other.getPhase() == null : this.getPhase().equals(other.getPhase()))
            && (this.getBegintime() == null ? other.getBegintime() == null : this.getBegintime().equals(other.getBegintime()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getDeviceid() == null ? other.getDeviceid() == null : this.getDeviceid().equals(other.getDeviceid()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getMinu() == null ? other.getMinu() == null : this.getMinu().equals(other.getMinu()))
            && (this.getWorkorderid() == null ? other.getWorkorderid() == null : this.getWorkorderid().equals(other.getWorkorderid()))
            && (this.getIssendworkorder() == null ? other.getIssendworkorder() == null : this.getIssendworkorder().equals(other.getIssendworkorder()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLowuid() == null) ? 0 : getLowuid().hashCode());
        result = prime * result + ((getPhase() == null) ? 0 : getPhase().hashCode());
        result = prime * result + ((getBegintime() == null) ? 0 : getBegintime().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getDeviceid() == null) ? 0 : getDeviceid().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getMinu() == null) ? 0 : getMinu().hashCode());
        result = prime * result + ((getWorkorderid() == null) ? 0 : getWorkorderid().hashCode());
        result = prime * result + ((getIssendworkorder() == null) ? 0 : getIssendworkorder().hashCode());
        return result;
    }
}