package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class BaseSmssend implements Serializable {
    private String oid;

    private String phoneno;

    private String smscontent;

    private Date recordtime;

    private Integer issend;

    private Date sendtime;

    private String sendno;

    private Integer result;

    private String resultcontent;

    private Integer priority;

    private String msgsendid;

    private static final long serialVersionUID = 1L;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent == null ? null : smscontent.trim();
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public Integer getIssend() {
        return issend;
    }

    public void setIssend(Integer issend) {
        this.issend = issend;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getSendno() {
        return sendno;
    }

    public void setSendno(String sendno) {
        this.sendno = sendno == null ? null : sendno.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getResultcontent() {
        return resultcontent;
    }

    public void setResultcontent(String resultcontent) {
        this.resultcontent = resultcontent == null ? null : resultcontent.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMsgsendid() {
        return msgsendid;
    }

    public void setMsgsendid(String msgsendid) {
        this.msgsendid = msgsendid == null ? null : msgsendid.trim();
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
        BaseSmssend other = (BaseSmssend) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
            && (this.getPhoneno() == null ? other.getPhoneno() == null : this.getPhoneno().equals(other.getPhoneno()))
            && (this.getSmscontent() == null ? other.getSmscontent() == null : this.getSmscontent().equals(other.getSmscontent()))
            && (this.getRecordtime() == null ? other.getRecordtime() == null : this.getRecordtime().equals(other.getRecordtime()))
            && (this.getIssend() == null ? other.getIssend() == null : this.getIssend().equals(other.getIssend()))
            && (this.getSendtime() == null ? other.getSendtime() == null : this.getSendtime().equals(other.getSendtime()))
            && (this.getSendno() == null ? other.getSendno() == null : this.getSendno().equals(other.getSendno()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getResultcontent() == null ? other.getResultcontent() == null : this.getResultcontent().equals(other.getResultcontent()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getMsgsendid() == null ? other.getMsgsendid() == null : this.getMsgsendid().equals(other.getMsgsendid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getPhoneno() == null) ? 0 : getPhoneno().hashCode());
        result = prime * result + ((getSmscontent() == null) ? 0 : getSmscontent().hashCode());
        result = prime * result + ((getRecordtime() == null) ? 0 : getRecordtime().hashCode());
        result = prime * result + ((getIssend() == null) ? 0 : getIssend().hashCode());
        result = prime * result + ((getSendtime() == null) ? 0 : getSendtime().hashCode());
        result = prime * result + ((getSendno() == null) ? 0 : getSendno().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getResultcontent() == null) ? 0 : getResultcontent().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getMsgsendid() == null) ? 0 : getMsgsendid().hashCode());
        return result;
    }
}