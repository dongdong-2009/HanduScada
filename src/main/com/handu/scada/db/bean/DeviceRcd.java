package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceRcd implements Serializable {
    private String oid;

    private String daid;

    private String name;

    private String address;

    private String baudrate;

    private String checkdigit;

    private String terminalid;

    private String rcdmodel;

    private String mfrs;

    private Integer measurevalue;

    private String code;

    private String level;

    private String pwd;

    private String size;

    private String cycle;

    private Date workdatetime;

    private String validate;

    private Integer isappsave;

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

    private Double ua;

    private Double ub;

    private Double uc;

    private Double ia;

    private Double ib;

    private Double ic;

    private Double io;

    private static final long serialVersionUID = 1L;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getDaid() {
        return daid;
    }

    public void setDaid(String daid) {
        this.daid = daid == null ? null : daid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(String baudrate) {
        this.baudrate = baudrate == null ? null : baudrate.trim();
    }

    public String getCheckdigit() {
        return checkdigit;
    }

    public void setCheckdigit(String checkdigit) {
        this.checkdigit = checkdigit == null ? null : checkdigit.trim();
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid == null ? null : terminalid.trim();
    }

    public String getRcdmodel() {
        return rcdmodel;
    }

    public void setRcdmodel(String rcdmodel) {
        this.rcdmodel = rcdmodel == null ? null : rcdmodel.trim();
    }

    public String getMfrs() {
        return mfrs;
    }

    public void setMfrs(String mfrs) {
        this.mfrs = mfrs == null ? null : mfrs.trim();
    }

    public Integer getMeasurevalue() {
        return measurevalue;
    }

    public void setMeasurevalue(Integer measurevalue) {
        this.measurevalue = measurevalue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle == null ? null : cycle.trim();
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

    public Integer getIsappsave() {
        return isappsave;
    }

    public void setIsappsave(Integer isappsave) {
        this.isappsave = isappsave;
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
        DeviceRcd other = (DeviceRcd) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
            && (this.getDaid() == null ? other.getDaid() == null : this.getDaid().equals(other.getDaid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getBaudrate() == null ? other.getBaudrate() == null : this.getBaudrate().equals(other.getBaudrate()))
            && (this.getCheckdigit() == null ? other.getCheckdigit() == null : this.getCheckdigit().equals(other.getCheckdigit()))
            && (this.getTerminalid() == null ? other.getTerminalid() == null : this.getTerminalid().equals(other.getTerminalid()))
            && (this.getRcdmodel() == null ? other.getRcdmodel() == null : this.getRcdmodel().equals(other.getRcdmodel()))
            && (this.getMfrs() == null ? other.getMfrs() == null : this.getMfrs().equals(other.getMfrs()))
            && (this.getMeasurevalue() == null ? other.getMeasurevalue() == null : this.getMeasurevalue().equals(other.getMeasurevalue()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getCycle() == null ? other.getCycle() == null : this.getCycle().equals(other.getCycle()))
            && (this.getWorkdatetime() == null ? other.getWorkdatetime() == null : this.getWorkdatetime().equals(other.getWorkdatetime()))
            && (this.getValidate() == null ? other.getValidate() == null : this.getValidate().equals(other.getValidate()))
            && (this.getIsappsave() == null ? other.getIsappsave() == null : this.getIsappsave().equals(other.getIsappsave()))
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
            && (this.getUa() == null ? other.getUa() == null : this.getUa().equals(other.getUa()))
            && (this.getUb() == null ? other.getUb() == null : this.getUb().equals(other.getUb()))
            && (this.getUc() == null ? other.getUc() == null : this.getUc().equals(other.getUc()))
            && (this.getIa() == null ? other.getIa() == null : this.getIa().equals(other.getIa()))
            && (this.getIb() == null ? other.getIb() == null : this.getIb().equals(other.getIb()))
            && (this.getIc() == null ? other.getIc() == null : this.getIc().equals(other.getIc()))
            && (this.getIo() == null ? other.getIo() == null : this.getIo().equals(other.getIo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getDaid() == null) ? 0 : getDaid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getBaudrate() == null) ? 0 : getBaudrate().hashCode());
        result = prime * result + ((getCheckdigit() == null) ? 0 : getCheckdigit().hashCode());
        result = prime * result + ((getTerminalid() == null) ? 0 : getTerminalid().hashCode());
        result = prime * result + ((getRcdmodel() == null) ? 0 : getRcdmodel().hashCode());
        result = prime * result + ((getMfrs() == null) ? 0 : getMfrs().hashCode());
        result = prime * result + ((getMeasurevalue() == null) ? 0 : getMeasurevalue().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getCycle() == null) ? 0 : getCycle().hashCode());
        result = prime * result + ((getWorkdatetime() == null) ? 0 : getWorkdatetime().hashCode());
        result = prime * result + ((getValidate() == null) ? 0 : getValidate().hashCode());
        result = prime * result + ((getIsappsave() == null) ? 0 : getIsappsave().hashCode());
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
        result = prime * result + ((getUa() == null) ? 0 : getUa().hashCode());
        result = prime * result + ((getUb() == null) ? 0 : getUb().hashCode());
        result = prime * result + ((getUc() == null) ? 0 : getUc().hashCode());
        result = prime * result + ((getIa() == null) ? 0 : getIa().hashCode());
        result = prime * result + ((getIb() == null) ? 0 : getIb().hashCode());
        result = prime * result + ((getIc() == null) ? 0 : getIc().hashCode());
        result = prime * result + ((getIo() == null) ? 0 : getIo().hashCode());
        return result;
    }
}