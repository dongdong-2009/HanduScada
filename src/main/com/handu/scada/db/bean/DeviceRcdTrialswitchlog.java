package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceRcdTrialswitchlog implements Serializable {
    private String oid;

    private String rcdid;

    private Date trialtime;

    private Integer result;

    private String operator;

    private String resulttext;

    private Date ontime;

    private Date downtime;

    private String workorderid;

    private Integer issendworkorder;

    private static final long serialVersionUID = 1L;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getRcdid() {
        return rcdid;
    }

    public void setRcdid(String rcdid) {
        this.rcdid = rcdid == null ? null : rcdid.trim();
    }

    public Date getTrialtime() {
        return trialtime;
    }

    public void setTrialtime(Date trialtime) {
        this.trialtime = trialtime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getResulttext() {
        return resulttext;
    }

    public void setResulttext(String resulttext) {
        this.resulttext = resulttext == null ? null : resulttext.trim();
    }

    public Date getOntime() {
        return ontime;
    }

    public void setOntime(Date ontime) {
        this.ontime = ontime;
    }

    public Date getDowntime() {
        return downtime;
    }

    public void setDowntime(Date downtime) {
        this.downtime = downtime;
    }

    public String getWorkorderid() {
        return workorderid;
    }

    public void setWorkorderid(String workorderid) {
        this.workorderid = workorderid == null ? null : workorderid.trim();
    }

    public Integer getIssendworkorder() {
        return issendworkorder;
    }

    public void setIssendworkorder(Integer issendworkorder) {
        this.issendworkorder = issendworkorder;
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
        DeviceRcdTrialswitchlog other = (DeviceRcdTrialswitchlog) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
            && (this.getRcdid() == null ? other.getRcdid() == null : this.getRcdid().equals(other.getRcdid()))
            && (this.getTrialtime() == null ? other.getTrialtime() == null : this.getTrialtime().equals(other.getTrialtime()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getResulttext() == null ? other.getResulttext() == null : this.getResulttext().equals(other.getResulttext()))
            && (this.getOntime() == null ? other.getOntime() == null : this.getOntime().equals(other.getOntime()))
            && (this.getDowntime() == null ? other.getDowntime() == null : this.getDowntime().equals(other.getDowntime()))
            && (this.getWorkorderid() == null ? other.getWorkorderid() == null : this.getWorkorderid().equals(other.getWorkorderid()))
            && (this.getIssendworkorder() == null ? other.getIssendworkorder() == null : this.getIssendworkorder().equals(other.getIssendworkorder()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getRcdid() == null) ? 0 : getRcdid().hashCode());
        result = prime * result + ((getTrialtime() == null) ? 0 : getTrialtime().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getResulttext() == null) ? 0 : getResulttext().hashCode());
        result = prime * result + ((getOntime() == null) ? 0 : getOntime().hashCode());
        result = prime * result + ((getDowntime() == null) ? 0 : getDowntime().hashCode());
        result = prime * result + ((getWorkorderid() == null) ? 0 : getWorkorderid().hashCode());
        result = prime * result + ((getIssendworkorder() == null) ? 0 : getIssendworkorder().hashCode());
        return result;
    }
}