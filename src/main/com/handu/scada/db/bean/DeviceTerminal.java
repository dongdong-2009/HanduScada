package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class DeviceTerminal implements Serializable {
    private String terminalid;

    private String name;

    private String memo;

    private Integer protocoltype;

    private String code;

    private Date updatetime;

    private Integer flag;

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

    private String devparamfolder;

    private byte[] paraminfo;

    private static final long serialVersionUID = 1L;

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid == null ? null : terminalid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getProtocoltype() {
        return protocoltype;
    }

    public void setProtocoltype(Integer protocoltype) {
        this.protocoltype = protocoltype;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

    public String getDevparamfolder() {
        return devparamfolder;
    }

    public void setDevparamfolder(String devparamfolder) {
        this.devparamfolder = devparamfolder == null ? null : devparamfolder.trim();
    }

    public byte[] getParaminfo() {
        return paraminfo;
    }

    public void setParaminfo(byte[] paraminfo) {
        this.paraminfo = paraminfo;
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
        DeviceTerminal other = (DeviceTerminal) that;
        return (this.getTerminalid() == null ? other.getTerminalid() == null : this.getTerminalid().equals(other.getTerminalid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getProtocoltype() == null ? other.getProtocoltype() == null : this.getProtocoltype().equals(other.getProtocoltype()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
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
            && (this.getDevparamfolder() == null ? other.getDevparamfolder() == null : this.getDevparamfolder().equals(other.getDevparamfolder()))
            && (Arrays.equals(this.getParaminfo(), other.getParaminfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTerminalid() == null) ? 0 : getTerminalid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getProtocoltype() == null) ? 0 : getProtocoltype().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
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
        result = prime * result + ((getDevparamfolder() == null) ? 0 : getDevparamfolder().hashCode());
        result = prime * result + (Arrays.hashCode(getParaminfo()));
        return result;
    }
}