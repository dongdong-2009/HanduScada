package main.com.handu.scada.db.bean;

import java.io.Serializable;

public class DeviceRemoteindexs implements Serializable {
    private String remoteindexsid;

    private String devicetablename;

    private String deviceid;

    private String dataitem;

    private String unit;

    private String dataitemname;

    private String groupname;

    private static final long serialVersionUID = 1L;

    public String getRemoteindexsid() {
        return remoteindexsid;
    }

    public void setRemoteindexsid(String remoteindexsid) {
        this.remoteindexsid = remoteindexsid == null ? null : remoteindexsid.trim();
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

    public String getDataitem() {
        return dataitem;
    }

    public void setDataitem(String dataitem) {
        this.dataitem = dataitem == null ? null : dataitem.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getDataitemname() {
        return dataitemname;
    }

    public void setDataitemname(String dataitemname) {
        this.dataitemname = dataitemname == null ? null : dataitemname.trim();
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
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
        DeviceRemoteindexs other = (DeviceRemoteindexs) that;
        return (this.getRemoteindexsid() == null ? other.getRemoteindexsid() == null : this.getRemoteindexsid().equals(other.getRemoteindexsid()))
            && (this.getDevicetablename() == null ? other.getDevicetablename() == null : this.getDevicetablename().equals(other.getDevicetablename()))
            && (this.getDeviceid() == null ? other.getDeviceid() == null : this.getDeviceid().equals(other.getDeviceid()))
            && (this.getDataitem() == null ? other.getDataitem() == null : this.getDataitem().equals(other.getDataitem()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getDataitemname() == null ? other.getDataitemname() == null : this.getDataitemname().equals(other.getDataitemname()))
            && (this.getGroupname() == null ? other.getGroupname() == null : this.getGroupname().equals(other.getGroupname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRemoteindexsid() == null) ? 0 : getRemoteindexsid().hashCode());
        result = prime * result + ((getDevicetablename() == null) ? 0 : getDevicetablename().hashCode());
        result = prime * result + ((getDeviceid() == null) ? 0 : getDeviceid().hashCode());
        result = prime * result + ((getDataitem() == null) ? 0 : getDataitem().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getDataitemname() == null) ? 0 : getDataitemname().hashCode());
        result = prime * result + ((getGroupname() == null) ? 0 : getGroupname().hashCode());
        return result;
    }
}