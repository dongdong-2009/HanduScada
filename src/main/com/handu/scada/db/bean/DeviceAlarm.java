package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceAlarm implements Serializable {
    private String alarmid;

    private String devicetablename;

    private String deviceid;

    private Double ua;

    private Double ub;

    private Double uc;

    private Double ia;

    private Double ib;

    private Double ic;

    private Double io;

    private Integer alarmtype;

    private String alarmphase;

    private Integer alarmlevel;

    private Double durationtime;

    private Date outagetime;

    private Date powerontime;

    private Date alarmtime;

    private String workorderid;

    private Integer isdeal;

    private Date dealtime;

    private Integer issendworkorder;

    private Date sendtime;

    private Integer sortcode;

    private Integer deletemark;

    private Integer enabledmark;

    private String description;

    private Date createdate;

    private String createuserid;

    private String createusername;

    private Date modifydate;

    private String modifyuserid;

    private String modifyusername;

    private String alarmcontent;

    private static final long serialVersionUID = 1L;

    public String getAlarmid() {
        return alarmid;
    }

    public void setAlarmid(String alarmid) {
        this.alarmid = alarmid == null ? null : alarmid.trim();
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

    public Double getUa() {
        return ua;
    }

    public void setUa(Double ua) {
        this.ua = ua;
    }

    public Double getUb() {
        return ub;
    }

    public void setUb(Double ub) {
        this.ub = ub;
    }

    public Double getUc() {
        return uc;
    }

    public void setUc(Double uc) {
        this.uc = uc;
    }

    public Double getIa() {
        return ia;
    }

    public void setIa(Double ia) {
        this.ia = ia;
    }

    public Double getIb() {
        return ib;
    }

    public void setIb(Double ib) {
        this.ib = ib;
    }

    public Double getIc() {
        return ic;
    }

    public void setIc(Double ic) {
        this.ic = ic;
    }

    public Double getIo() {
        return io;
    }

    public void setIo(Double io) {
        this.io = io;
    }

    public Integer getAlarmtype() {
        return alarmtype;
    }

    public void setAlarmtype(Integer alarmtype) {
        this.alarmtype = alarmtype;
    }

    public String getAlarmphase() {
        return alarmphase;
    }

    public void setAlarmphase(String alarmphase) {
        this.alarmphase = alarmphase == null ? null : alarmphase.trim();
    }

    public Integer getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(Integer alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public Double getDurationtime() {
        return durationtime;
    }

    public void setDurationtime(Double durationtime) {
        this.durationtime = durationtime;
    }

    public Date getOutagetime() {
        return outagetime;
    }

    public void setOutagetime(Date outagetime) {
        this.outagetime = outagetime;
    }

    public Date getPowerontime() {
        return powerontime;
    }

    public void setPowerontime(Date powerontime) {
        this.powerontime = powerontime;
    }

    public Date getAlarmtime() {
        return alarmtime;
    }

    public void setAlarmtime(Date alarmtime) {
        this.alarmtime = alarmtime;
    }

    public String getWorkorderid() {
        return workorderid;
    }

    public void setWorkorderid(String workorderid) {
        this.workorderid = workorderid == null ? null : workorderid.trim();
    }

    public Integer getIsdeal() {
        return isdeal;
    }

    public void setIsdeal(Integer isdeal) {
        this.isdeal = isdeal;
    }

    public Date getDealtime() {
        return dealtime;
    }

    public void setDealtime(Date dealtime) {
        this.dealtime = dealtime;
    }

    public Integer getIssendworkorder() {
        return issendworkorder;
    }

    public void setIssendworkorder(Integer issendworkorder) {
        this.issendworkorder = issendworkorder;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getSortcode() {
        return sortcode;
    }

    public void setSortcode(Integer sortcode) {
        this.sortcode = sortcode;
    }

    public Integer getDeletemark() {
        return deletemark;
    }

    public void setDeletemark(Integer deletemark) {
        this.deletemark = deletemark;
    }

    public Integer getEnabledmark() {
        return enabledmark;
    }

    public void setEnabledmark(Integer enabledmark) {
        this.enabledmark = enabledmark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername == null ? null : createusername.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getModifyuserid() {
        return modifyuserid;
    }

    public void setModifyuserid(String modifyuserid) {
        this.modifyuserid = modifyuserid == null ? null : modifyuserid.trim();
    }

    public String getModifyusername() {
        return modifyusername;
    }

    public void setModifyusername(String modifyusername) {
        this.modifyusername = modifyusername == null ? null : modifyusername.trim();
    }

    public String getAlarmcontent() {
        return alarmcontent;
    }

    public void setAlarmcontent(String alarmcontent) {
        this.alarmcontent = alarmcontent == null ? null : alarmcontent.trim();
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
        DeviceAlarm other = (DeviceAlarm) that;
        return (this.getAlarmid() == null ? other.getAlarmid() == null : this.getAlarmid().equals(other.getAlarmid()))
            && (this.getDevicetablename() == null ? other.getDevicetablename() == null : this.getDevicetablename().equals(other.getDevicetablename()))
            && (this.getDeviceid() == null ? other.getDeviceid() == null : this.getDeviceid().equals(other.getDeviceid()))
            && (this.getUa() == null ? other.getUa() == null : this.getUa().equals(other.getUa()))
            && (this.getUb() == null ? other.getUb() == null : this.getUb().equals(other.getUb()))
            && (this.getUc() == null ? other.getUc() == null : this.getUc().equals(other.getUc()))
            && (this.getIa() == null ? other.getIa() == null : this.getIa().equals(other.getIa()))
            && (this.getIb() == null ? other.getIb() == null : this.getIb().equals(other.getIb()))
            && (this.getIc() == null ? other.getIc() == null : this.getIc().equals(other.getIc()))
            && (this.getIo() == null ? other.getIo() == null : this.getIo().equals(other.getIo()))
            && (this.getAlarmtype() == null ? other.getAlarmtype() == null : this.getAlarmtype().equals(other.getAlarmtype()))
            && (this.getAlarmphase() == null ? other.getAlarmphase() == null : this.getAlarmphase().equals(other.getAlarmphase()))
            && (this.getAlarmlevel() == null ? other.getAlarmlevel() == null : this.getAlarmlevel().equals(other.getAlarmlevel()))
            && (this.getDurationtime() == null ? other.getDurationtime() == null : this.getDurationtime().equals(other.getDurationtime()))
            && (this.getOutagetime() == null ? other.getOutagetime() == null : this.getOutagetime().equals(other.getOutagetime()))
            && (this.getPowerontime() == null ? other.getPowerontime() == null : this.getPowerontime().equals(other.getPowerontime()))
            && (this.getAlarmtime() == null ? other.getAlarmtime() == null : this.getAlarmtime().equals(other.getAlarmtime()))
            && (this.getWorkorderid() == null ? other.getWorkorderid() == null : this.getWorkorderid().equals(other.getWorkorderid()))
            && (this.getIsdeal() == null ? other.getIsdeal() == null : this.getIsdeal().equals(other.getIsdeal()))
            && (this.getDealtime() == null ? other.getDealtime() == null : this.getDealtime().equals(other.getDealtime()))
            && (this.getIssendworkorder() == null ? other.getIssendworkorder() == null : this.getIssendworkorder().equals(other.getIssendworkorder()))
            && (this.getSendtime() == null ? other.getSendtime() == null : this.getSendtime().equals(other.getSendtime()))
            && (this.getSortcode() == null ? other.getSortcode() == null : this.getSortcode().equals(other.getSortcode()))
            && (this.getDeletemark() == null ? other.getDeletemark() == null : this.getDeletemark().equals(other.getDeletemark()))
            && (this.getEnabledmark() == null ? other.getEnabledmark() == null : this.getEnabledmark().equals(other.getEnabledmark()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getCreateuserid() == null ? other.getCreateuserid() == null : this.getCreateuserid().equals(other.getCreateuserid()))
            && (this.getCreateusername() == null ? other.getCreateusername() == null : this.getCreateusername().equals(other.getCreateusername()))
            && (this.getModifydate() == null ? other.getModifydate() == null : this.getModifydate().equals(other.getModifydate()))
            && (this.getModifyuserid() == null ? other.getModifyuserid() == null : this.getModifyuserid().equals(other.getModifyuserid()))
            && (this.getModifyusername() == null ? other.getModifyusername() == null : this.getModifyusername().equals(other.getModifyusername()))
            && (this.getAlarmcontent() == null ? other.getAlarmcontent() == null : this.getAlarmcontent().equals(other.getAlarmcontent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAlarmid() == null) ? 0 : getAlarmid().hashCode());
        result = prime * result + ((getDevicetablename() == null) ? 0 : getDevicetablename().hashCode());
        result = prime * result + ((getDeviceid() == null) ? 0 : getDeviceid().hashCode());
        result = prime * result + ((getUa() == null) ? 0 : getUa().hashCode());
        result = prime * result + ((getUb() == null) ? 0 : getUb().hashCode());
        result = prime * result + ((getUc() == null) ? 0 : getUc().hashCode());
        result = prime * result + ((getIa() == null) ? 0 : getIa().hashCode());
        result = prime * result + ((getIb() == null) ? 0 : getIb().hashCode());
        result = prime * result + ((getIc() == null) ? 0 : getIc().hashCode());
        result = prime * result + ((getIo() == null) ? 0 : getIo().hashCode());
        result = prime * result + ((getAlarmtype() == null) ? 0 : getAlarmtype().hashCode());
        result = prime * result + ((getAlarmphase() == null) ? 0 : getAlarmphase().hashCode());
        result = prime * result + ((getAlarmlevel() == null) ? 0 : getAlarmlevel().hashCode());
        result = prime * result + ((getDurationtime() == null) ? 0 : getDurationtime().hashCode());
        result = prime * result + ((getOutagetime() == null) ? 0 : getOutagetime().hashCode());
        result = prime * result + ((getPowerontime() == null) ? 0 : getPowerontime().hashCode());
        result = prime * result + ((getAlarmtime() == null) ? 0 : getAlarmtime().hashCode());
        result = prime * result + ((getWorkorderid() == null) ? 0 : getWorkorderid().hashCode());
        result = prime * result + ((getIsdeal() == null) ? 0 : getIsdeal().hashCode());
        result = prime * result + ((getDealtime() == null) ? 0 : getDealtime().hashCode());
        result = prime * result + ((getIssendworkorder() == null) ? 0 : getIssendworkorder().hashCode());
        result = prime * result + ((getSendtime() == null) ? 0 : getSendtime().hashCode());
        result = prime * result + ((getSortcode() == null) ? 0 : getSortcode().hashCode());
        result = prime * result + ((getDeletemark() == null) ? 0 : getDeletemark().hashCode());
        result = prime * result + ((getEnabledmark() == null) ? 0 : getEnabledmark().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getCreateuserid() == null) ? 0 : getCreateuserid().hashCode());
        result = prime * result + ((getCreateusername() == null) ? 0 : getCreateusername().hashCode());
        result = prime * result + ((getModifydate() == null) ? 0 : getModifydate().hashCode());
        result = prime * result + ((getModifyuserid() == null) ? 0 : getModifyuserid().hashCode());
        result = prime * result + ((getModifyusername() == null) ? 0 : getModifyusername().hashCode());
        result = prime * result + ((getAlarmcontent() == null) ? 0 : getAlarmcontent().hashCode());
        return result;
    }
}