package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceDtu implements Serializable {
    private String oid;

    private String name;

    private String departmentid;

    private String address;

    private String terminalid;

    private String phonenum;

    private String ipaddress;

    private String backip;

    private Integer port;

    private Date workdatetime;

    private String validate;

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

    private String organizeid;

    private String mfrs;

    private String dtumodel;

    private String telecomoperator;

    private static final long serialVersionUID = 1L;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid == null ? null : terminalid.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }

    public String getBackip() {
        return backip;
    }

    public void setBackip(String backip) {
        this.backip = backip == null ? null : backip.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Date getWorkdatetime() {
        return workdatetime;
    }

    public void setWorkdatetime(Date workdatetime) {
        this.workdatetime = workdatetime;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate == null ? null : validate.trim();
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

    public String getOrganizeid() {
        return organizeid;
    }

    public void setOrganizeid(String organizeid) {
        this.organizeid = organizeid == null ? null : organizeid.trim();
    }

    public String getMfrs() {
        return mfrs;
    }

    public void setMfrs(String mfrs) {
        this.mfrs = mfrs == null ? null : mfrs.trim();
    }

    public String getDtumodel() {
        return dtumodel;
    }

    public void setDtumodel(String dtumodel) {
        this.dtumodel = dtumodel == null ? null : dtumodel.trim();
    }

    public String getTelecomoperator() {
        return telecomoperator;
    }

    public void setTelecomoperator(String telecomoperator) {
        this.telecomoperator = telecomoperator == null ? null : telecomoperator.trim();
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
        DeviceDtu other = (DeviceDtu) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDepartmentid() == null ? other.getDepartmentid() == null : this.getDepartmentid().equals(other.getDepartmentid()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getTerminalid() == null ? other.getTerminalid() == null : this.getTerminalid().equals(other.getTerminalid()))
            && (this.getPhonenum() == null ? other.getPhonenum() == null : this.getPhonenum().equals(other.getPhonenum()))
            && (this.getIpaddress() == null ? other.getIpaddress() == null : this.getIpaddress().equals(other.getIpaddress()))
            && (this.getBackip() == null ? other.getBackip() == null : this.getBackip().equals(other.getBackip()))
            && (this.getPort() == null ? other.getPort() == null : this.getPort().equals(other.getPort()))
            && (this.getWorkdatetime() == null ? other.getWorkdatetime() == null : this.getWorkdatetime().equals(other.getWorkdatetime()))
            && (this.getValidate() == null ? other.getValidate() == null : this.getValidate().equals(other.getValidate()))
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
            && (this.getOrganizeid() == null ? other.getOrganizeid() == null : this.getOrganizeid().equals(other.getOrganizeid()))
            && (this.getMfrs() == null ? other.getMfrs() == null : this.getMfrs().equals(other.getMfrs()))
            && (this.getDtumodel() == null ? other.getDtumodel() == null : this.getDtumodel().equals(other.getDtumodel()))
            && (this.getTelecomoperator() == null ? other.getTelecomoperator() == null : this.getTelecomoperator().equals(other.getTelecomoperator()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDepartmentid() == null) ? 0 : getDepartmentid().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getTerminalid() == null) ? 0 : getTerminalid().hashCode());
        result = prime * result + ((getPhonenum() == null) ? 0 : getPhonenum().hashCode());
        result = prime * result + ((getIpaddress() == null) ? 0 : getIpaddress().hashCode());
        result = prime * result + ((getBackip() == null) ? 0 : getBackip().hashCode());
        result = prime * result + ((getPort() == null) ? 0 : getPort().hashCode());
        result = prime * result + ((getWorkdatetime() == null) ? 0 : getWorkdatetime().hashCode());
        result = prime * result + ((getValidate() == null) ? 0 : getValidate().hashCode());
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
        result = prime * result + ((getOrganizeid() == null) ? 0 : getOrganizeid().hashCode());
        result = prime * result + ((getMfrs() == null) ? 0 : getMfrs().hashCode());
        result = prime * result + ((getDtumodel() == null) ? 0 : getDtumodel().hashCode());
        result = prime * result + ((getTelecomoperator() == null) ? 0 : getTelecomoperator().hashCode());
        return result;
    }
}