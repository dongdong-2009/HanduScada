package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class BaseAlarmcache implements Serializable {
    private String oid;

    private Date happentime;

    private String devicetablename;

    private String deviceaddress;

    private String tripreason;

    private Integer state;

    private String flag;

    private String msgbuff;

    private static final long serialVersionUID = 1L;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public Date getHappentime() {
        return happentime;
    }

    public void setHappentime(Date happentime) {
        this.happentime = happentime;
    }

    public String getDevicetablename() {
        return devicetablename;
    }

    public void setDevicetablename(String devicetablename) {
        this.devicetablename = devicetablename == null ? null : devicetablename.trim();
    }

    public String getDeviceaddress() {
        return deviceaddress;
    }

    public void setDeviceaddress(String deviceaddress) {
        this.deviceaddress = deviceaddress == null ? null : deviceaddress.trim();
    }

    public String getTripreason() {
        return tripreason;
    }

    public void setTripreason(String tripreason) {
        this.tripreason = tripreason == null ? null : tripreason.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getMsgbuff() {
        return msgbuff;
    }

    public void setMsgbuff(String msgbuff) {
        this.msgbuff = msgbuff == null ? null : msgbuff.trim();
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
        BaseAlarmcache other = (BaseAlarmcache) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
            && (this.getHappentime() == null ? other.getHappentime() == null : this.getHappentime().equals(other.getHappentime()))
            && (this.getDevicetablename() == null ? other.getDevicetablename() == null : this.getDevicetablename().equals(other.getDevicetablename()))
            && (this.getDeviceaddress() == null ? other.getDeviceaddress() == null : this.getDeviceaddress().equals(other.getDeviceaddress()))
            && (this.getTripreason() == null ? other.getTripreason() == null : this.getTripreason().equals(other.getTripreason()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getMsgbuff() == null ? other.getMsgbuff() == null : this.getMsgbuff().equals(other.getMsgbuff()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getHappentime() == null) ? 0 : getHappentime().hashCode());
        result = prime * result + ((getDevicetablename() == null) ? 0 : getDevicetablename().hashCode());
        result = prime * result + ((getDeviceaddress() == null) ? 0 : getDeviceaddress().hashCode());
        result = prime * result + ((getTripreason() == null) ? 0 : getTripreason().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getMsgbuff() == null) ? 0 : getMsgbuff().hashCode());
        return result;
    }
}