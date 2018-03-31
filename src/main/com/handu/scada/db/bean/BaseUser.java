package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class BaseUser implements Serializable {
    private String userid;

    private String encode;

    private String account;

    private String password;

    private String secretkey;

    private String realname;

    private String nickname;

    private String headicon;

    private String quickquery;

    private String simplespelling;

    private Integer gender;

    private Date birthday;

    private String mobile;

    private String telephone;

    private String email;

    private String oicq;

    private String wechat;

    private String msn;

    private String managerid;

    private String manager;

    private String organizeid;

    private String departmentid;

    private String roleid;

    private String dutyid;

    private String dutyname;

    private String postid;

    private String postname;

    private String workgroupid;

    private Integer securitylevel;

    private Integer useronline;

    private Integer openid;

    private String question;

    private String answerquestion;

    private Integer checkonline;

    private Date allowstarttime;

    private Date allowendtime;

    private Date lockstartdate;

    private Date lockenddate;

    private Date firstvisit;

    private Date previousvisit;

    private Date lastvisit;

    private Date pwrevisedate;

    private Integer errlogcount;

    private Integer logoncount;

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

    private static final long serialVersionUID = 1L;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode == null ? null : encode.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey == null ? null : secretkey.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadicon() {
        return headicon;
    }

    public void setHeadicon(String headicon) {
        this.headicon = headicon == null ? null : headicon.trim();
    }

    public String getQuickquery() {
        return quickquery;
    }

    public void setQuickquery(String quickquery) {
        this.quickquery = quickquery == null ? null : quickquery.trim();
    }

    public String getSimplespelling() {
        return simplespelling;
    }

    public void setSimplespelling(String simplespelling) {
        this.simplespelling = simplespelling == null ? null : simplespelling.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOicq() {
        return oicq;
    }

    public void setOicq(String oicq) {
        this.oicq = oicq == null ? null : oicq.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn == null ? null : msn.trim();
    }

    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid == null ? null : managerid.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getOrganizeid() {
        return organizeid;
    }

    public void setOrganizeid(String organizeid) {
        this.organizeid = organizeid == null ? null : organizeid.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getDutyid() {
        return dutyid;
    }

    public void setDutyid(String dutyid) {
        this.dutyid = dutyid == null ? null : dutyid.trim();
    }

    public String getDutyname() {
        return dutyname;
    }

    public void setDutyname(String dutyname) {
        this.dutyname = dutyname == null ? null : dutyname.trim();
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid == null ? null : postid.trim();
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname == null ? null : postname.trim();
    }

    public String getWorkgroupid() {
        return workgroupid;
    }

    public void setWorkgroupid(String workgroupid) {
        this.workgroupid = workgroupid == null ? null : workgroupid.trim();
    }

    public Integer getSecuritylevel() {
        return securitylevel;
    }

    public void setSecuritylevel(Integer securitylevel) {
        this.securitylevel = securitylevel;
    }

    public Integer getUseronline() {
        return useronline;
    }

    public void setUseronline(Integer useronline) {
        this.useronline = useronline;
    }

    public Integer getOpenid() {
        return openid;
    }

    public void setOpenid(Integer openid) {
        this.openid = openid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswerquestion() {
        return answerquestion;
    }

    public void setAnswerquestion(String answerquestion) {
        this.answerquestion = answerquestion == null ? null : answerquestion.trim();
    }

    public Integer getCheckonline() {
        return checkonline;
    }

    public void setCheckonline(Integer checkonline) {
        this.checkonline = checkonline;
    }

    public Date getAllowstarttime() {
        return allowstarttime;
    }

    public void setAllowstarttime(Date allowstarttime) {
        this.allowstarttime = allowstarttime;
    }

    public Date getAllowendtime() {
        return allowendtime;
    }

    public void setAllowendtime(Date allowendtime) {
        this.allowendtime = allowendtime;
    }

    public Date getLockstartdate() {
        return lockstartdate;
    }

    public void setLockstartdate(Date lockstartdate) {
        this.lockstartdate = lockstartdate;
    }

    public Date getLockenddate() {
        return lockenddate;
    }

    public void setLockenddate(Date lockenddate) {
        this.lockenddate = lockenddate;
    }

    public Date getFirstvisit() {
        return firstvisit;
    }

    public void setFirstvisit(Date firstvisit) {
        this.firstvisit = firstvisit;
    }

    public Date getPreviousvisit() {
        return previousvisit;
    }

    public void setPreviousvisit(Date previousvisit) {
        this.previousvisit = previousvisit;
    }

    public Date getLastvisit() {
        return lastvisit;
    }

    public void setLastvisit(Date lastvisit) {
        this.lastvisit = lastvisit;
    }

    public Date getPwrevisedate() {
        return pwrevisedate;
    }

    public void setPwrevisedate(Date pwrevisedate) {
        this.pwrevisedate = pwrevisedate;
    }

    public Integer getErrlogcount() {
        return errlogcount;
    }

    public void setErrlogcount(Integer errlogcount) {
        this.errlogcount = errlogcount;
    }

    public Integer getLogoncount() {
        return logoncount;
    }

    public void setLogoncount(Integer logoncount) {
        this.logoncount = logoncount;
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
        BaseUser other = (BaseUser) that;
        return (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getEncode() == null ? other.getEncode() == null : this.getEncode().equals(other.getEncode()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSecretkey() == null ? other.getSecretkey() == null : this.getSecretkey().equals(other.getSecretkey()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getHeadicon() == null ? other.getHeadicon() == null : this.getHeadicon().equals(other.getHeadicon()))
            && (this.getQuickquery() == null ? other.getQuickquery() == null : this.getQuickquery().equals(other.getQuickquery()))
            && (this.getSimplespelling() == null ? other.getSimplespelling() == null : this.getSimplespelling().equals(other.getSimplespelling()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getOicq() == null ? other.getOicq() == null : this.getOicq().equals(other.getOicq()))
            && (this.getWechat() == null ? other.getWechat() == null : this.getWechat().equals(other.getWechat()))
            && (this.getMsn() == null ? other.getMsn() == null : this.getMsn().equals(other.getMsn()))
            && (this.getManagerid() == null ? other.getManagerid() == null : this.getManagerid().equals(other.getManagerid()))
            && (this.getManager() == null ? other.getManager() == null : this.getManager().equals(other.getManager()))
            && (this.getOrganizeid() == null ? other.getOrganizeid() == null : this.getOrganizeid().equals(other.getOrganizeid()))
            && (this.getDepartmentid() == null ? other.getDepartmentid() == null : this.getDepartmentid().equals(other.getDepartmentid()))
            && (this.getRoleid() == null ? other.getRoleid() == null : this.getRoleid().equals(other.getRoleid()))
            && (this.getDutyid() == null ? other.getDutyid() == null : this.getDutyid().equals(other.getDutyid()))
            && (this.getDutyname() == null ? other.getDutyname() == null : this.getDutyname().equals(other.getDutyname()))
            && (this.getPostid() == null ? other.getPostid() == null : this.getPostid().equals(other.getPostid()))
            && (this.getPostname() == null ? other.getPostname() == null : this.getPostname().equals(other.getPostname()))
            && (this.getWorkgroupid() == null ? other.getWorkgroupid() == null : this.getWorkgroupid().equals(other.getWorkgroupid()))
            && (this.getSecuritylevel() == null ? other.getSecuritylevel() == null : this.getSecuritylevel().equals(other.getSecuritylevel()))
            && (this.getUseronline() == null ? other.getUseronline() == null : this.getUseronline().equals(other.getUseronline()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getQuestion() == null ? other.getQuestion() == null : this.getQuestion().equals(other.getQuestion()))
            && (this.getAnswerquestion() == null ? other.getAnswerquestion() == null : this.getAnswerquestion().equals(other.getAnswerquestion()))
            && (this.getCheckonline() == null ? other.getCheckonline() == null : this.getCheckonline().equals(other.getCheckonline()))
            && (this.getAllowstarttime() == null ? other.getAllowstarttime() == null : this.getAllowstarttime().equals(other.getAllowstarttime()))
            && (this.getAllowendtime() == null ? other.getAllowendtime() == null : this.getAllowendtime().equals(other.getAllowendtime()))
            && (this.getLockstartdate() == null ? other.getLockstartdate() == null : this.getLockstartdate().equals(other.getLockstartdate()))
            && (this.getLockenddate() == null ? other.getLockenddate() == null : this.getLockenddate().equals(other.getLockenddate()))
            && (this.getFirstvisit() == null ? other.getFirstvisit() == null : this.getFirstvisit().equals(other.getFirstvisit()))
            && (this.getPreviousvisit() == null ? other.getPreviousvisit() == null : this.getPreviousvisit().equals(other.getPreviousvisit()))
            && (this.getLastvisit() == null ? other.getLastvisit() == null : this.getLastvisit().equals(other.getLastvisit()))
            && (this.getPwrevisedate() == null ? other.getPwrevisedate() == null : this.getPwrevisedate().equals(other.getPwrevisedate()))
            && (this.getErrlogcount() == null ? other.getErrlogcount() == null : this.getErrlogcount().equals(other.getErrlogcount()))
            && (this.getLogoncount() == null ? other.getLogoncount() == null : this.getLogoncount().equals(other.getLogoncount()))
            && (this.getSortcode() == null ? other.getSortcode() == null : this.getSortcode().equals(other.getSortcode()))
            && (this.getDeletemark() == null ? other.getDeletemark() == null : this.getDeletemark().equals(other.getDeletemark()))
            && (this.getEnabledmark() == null ? other.getEnabledmark() == null : this.getEnabledmark().equals(other.getEnabledmark()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
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
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getEncode() == null) ? 0 : getEncode().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSecretkey() == null) ? 0 : getSecretkey().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getHeadicon() == null) ? 0 : getHeadicon().hashCode());
        result = prime * result + ((getQuickquery() == null) ? 0 : getQuickquery().hashCode());
        result = prime * result + ((getSimplespelling() == null) ? 0 : getSimplespelling().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getOicq() == null) ? 0 : getOicq().hashCode());
        result = prime * result + ((getWechat() == null) ? 0 : getWechat().hashCode());
        result = prime * result + ((getMsn() == null) ? 0 : getMsn().hashCode());
        result = prime * result + ((getManagerid() == null) ? 0 : getManagerid().hashCode());
        result = prime * result + ((getManager() == null) ? 0 : getManager().hashCode());
        result = prime * result + ((getOrganizeid() == null) ? 0 : getOrganizeid().hashCode());
        result = prime * result + ((getDepartmentid() == null) ? 0 : getDepartmentid().hashCode());
        result = prime * result + ((getRoleid() == null) ? 0 : getRoleid().hashCode());
        result = prime * result + ((getDutyid() == null) ? 0 : getDutyid().hashCode());
        result = prime * result + ((getDutyname() == null) ? 0 : getDutyname().hashCode());
        result = prime * result + ((getPostid() == null) ? 0 : getPostid().hashCode());
        result = prime * result + ((getPostname() == null) ? 0 : getPostname().hashCode());
        result = prime * result + ((getWorkgroupid() == null) ? 0 : getWorkgroupid().hashCode());
        result = prime * result + ((getSecuritylevel() == null) ? 0 : getSecuritylevel().hashCode());
        result = prime * result + ((getUseronline() == null) ? 0 : getUseronline().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getQuestion() == null) ? 0 : getQuestion().hashCode());
        result = prime * result + ((getAnswerquestion() == null) ? 0 : getAnswerquestion().hashCode());
        result = prime * result + ((getCheckonline() == null) ? 0 : getCheckonline().hashCode());
        result = prime * result + ((getAllowstarttime() == null) ? 0 : getAllowstarttime().hashCode());
        result = prime * result + ((getAllowendtime() == null) ? 0 : getAllowendtime().hashCode());
        result = prime * result + ((getLockstartdate() == null) ? 0 : getLockstartdate().hashCode());
        result = prime * result + ((getLockenddate() == null) ? 0 : getLockenddate().hashCode());
        result = prime * result + ((getFirstvisit() == null) ? 0 : getFirstvisit().hashCode());
        result = prime * result + ((getPreviousvisit() == null) ? 0 : getPreviousvisit().hashCode());
        result = prime * result + ((getLastvisit() == null) ? 0 : getLastvisit().hashCode());
        result = prime * result + ((getPwrevisedate() == null) ? 0 : getPwrevisedate().hashCode());
        result = prime * result + ((getErrlogcount() == null) ? 0 : getErrlogcount().hashCode());
        result = prime * result + ((getLogoncount() == null) ? 0 : getLogoncount().hashCode());
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
        return result;
    }
}