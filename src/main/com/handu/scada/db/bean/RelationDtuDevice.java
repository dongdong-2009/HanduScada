package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class RelationDtuDevice implements Serializable {
    private String id;

    private String dtuid;

    private String deviceid;

    private String devicetablename;

    private Date createdate;

    private String createuserid;

    private String createusername;

    private Date modifydate;

    private String modifyuserid;

    private String modifyusername;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDtuid() {
        return dtuid;
    }

    public void setDtuid(String dtuid) {
        this.dtuid = dtuid == null ? null : dtuid.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getDevicetablename() {
        return devicetablename;
    }

    public void setDevicetablename(String devicetablename) {
        this.devicetablename = devicetablename == null ? null : devicetablename.trim();
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
        RelationDtuDevice other = (RelationDtuDevice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDtuid() == null ? other.getDtuid() == null : this.getDtuid().equals(other.getDtuid()))
            && (this.getDeviceid() == null ? other.getDeviceid() == null : this.getDeviceid().equals(other.getDeviceid()))
            && (this.getDevicetablename() == null ? other.getDevicetablename() == null : this.getDevicetablename().equals(other.getDevicetablename()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getCreateuserid() == null ? other.getCreateuserid() == null : this.getCreateuserid().equals(other.getCreateuserid()))
            && (this.getCreateusername() == null ? other.getCreateusername() == null : this.getCreateusername().equals(other.getCreateusername()))
            && (this.getModifydate() == null ? other.getModifydate() == null : this.getModifydate().equals(other.getModifydate()))
            && (this.getModifyuserid() == null ? other.getModifyuserid() == null : this.getModifyuserid().equals(other.getModifyuserid()))
            && (this.getModifyusername() == null ? other.getModifyusername() == null : this.getModifyusername().equals(other.getModifyusername()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDtuid() == null) ? 0 : getDtuid().hashCode());
        result = prime * result + ((getDeviceid() == null) ? 0 : getDeviceid().hashCode());
        result = prime * result + ((getDevicetablename() == null) ? 0 : getDevicetablename().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getCreateuserid() == null) ? 0 : getCreateuserid().hashCode());
        result = prime * result + ((getCreateusername() == null) ? 0 : getCreateusername().hashCode());
        result = prime * result + ((getModifydate() == null) ? 0 : getModifydate().hashCode());
        result = prime * result + ((getModifyuserid() == null) ? 0 : getModifyuserid().hashCode());
        result = prime * result + ((getModifyusername() == null) ? 0 : getModifyusername().hashCode());
        return result;
    }
}