package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceInfochanges implements Serializable {
    private String oid;

    private String tabname;

    private String keyid;

    private String optype;

    private Date recordtime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getTabname() {
        return tabname;
    }

    public void setTabname(String tabname) {
        this.tabname = tabname == null ? null : tabname.trim();
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid == null ? null : keyid.trim();
    }

    public String getOptype() {
        return optype;
    }

    public void setOptype(String optype) {
        this.optype = optype == null ? null : optype.trim();
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        DeviceInfochanges other = (DeviceInfochanges) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
            && (this.getTabname() == null ? other.getTabname() == null : this.getTabname().equals(other.getTabname()))
            && (this.getKeyid() == null ? other.getKeyid() == null : this.getKeyid().equals(other.getKeyid()))
            && (this.getOptype() == null ? other.getOptype() == null : this.getOptype().equals(other.getOptype()))
            && (this.getRecordtime() == null ? other.getRecordtime() == null : this.getRecordtime().equals(other.getRecordtime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getTabname() == null) ? 0 : getTabname().hashCode());
        result = prime * result + ((getKeyid() == null) ? 0 : getKeyid().hashCode());
        result = prime * result + ((getOptype() == null) ? 0 : getOptype().hashCode());
        result = prime * result + ((getRecordtime() == null) ? 0 : getRecordtime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}