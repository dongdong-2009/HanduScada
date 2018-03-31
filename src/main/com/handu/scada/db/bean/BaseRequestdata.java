package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class BaseRequestdata implements Serializable {
    private String oid;

    private Integer state;

    private Date recordtime;

    private String tabname;

    private String tabid;

    private String cmdtype;

    private String deviceaddress;

    private String dtuaddress;

    private String datafrom;

    private String info;

    private static final long serialVersionUID = 1L;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public String getTabname() {
        return tabname;
    }

    public void setTabname(String tabname) {
        this.tabname = tabname == null ? null : tabname.trim();
    }

    public String getTabid() {
        return tabid;
    }

    public void setTabid(String tabid) {
        this.tabid = tabid == null ? null : tabid.trim();
    }

    public String getCmdtype() {
        return cmdtype;
    }

    public void setCmdtype(String cmdtype) {
        this.cmdtype = cmdtype == null ? null : cmdtype.trim();
    }

    public String getDeviceaddress() {
        return deviceaddress;
    }

    public void setDeviceaddress(String deviceaddress) {
        this.deviceaddress = deviceaddress == null ? null : deviceaddress.trim();
    }

    public String getDtuaddress() {
        return dtuaddress;
    }

    public void setDtuaddress(String dtuaddress) {
        this.dtuaddress = dtuaddress == null ? null : dtuaddress.trim();
    }

    public String getDatafrom() {
        return datafrom;
    }

    public void setDatafrom(String datafrom) {
        this.datafrom = datafrom == null ? null : datafrom.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
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
        BaseRequestdata other = (BaseRequestdata) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getRecordtime() == null ? other.getRecordtime() == null : this.getRecordtime().equals(other.getRecordtime()))
            && (this.getTabname() == null ? other.getTabname() == null : this.getTabname().equals(other.getTabname()))
            && (this.getTabid() == null ? other.getTabid() == null : this.getTabid().equals(other.getTabid()))
            && (this.getCmdtype() == null ? other.getCmdtype() == null : this.getCmdtype().equals(other.getCmdtype()))
            && (this.getDeviceaddress() == null ? other.getDeviceaddress() == null : this.getDeviceaddress().equals(other.getDeviceaddress()))
            && (this.getDtuaddress() == null ? other.getDtuaddress() == null : this.getDtuaddress().equals(other.getDtuaddress()))
            && (this.getDatafrom() == null ? other.getDatafrom() == null : this.getDatafrom().equals(other.getDatafrom()))
            && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getRecordtime() == null) ? 0 : getRecordtime().hashCode());
        result = prime * result + ((getTabname() == null) ? 0 : getTabname().hashCode());
        result = prime * result + ((getTabid() == null) ? 0 : getTabid().hashCode());
        result = prime * result + ((getCmdtype() == null) ? 0 : getCmdtype().hashCode());
        result = prime * result + ((getDeviceaddress() == null) ? 0 : getDeviceaddress().hashCode());
        result = prime * result + ((getDtuaddress() == null) ? 0 : getDtuaddress().hashCode());
        result = prime * result + ((getDatafrom() == null) ? 0 : getDatafrom().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
        return result;
    }
}