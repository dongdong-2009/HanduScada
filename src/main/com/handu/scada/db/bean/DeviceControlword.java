package main.com.handu.scada.db.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceControlword implements Serializable {
    private String deviceid;

    private Boolean flagallalarm;

    private Boolean flaglightalarm;

    private Boolean flagaudioalarm;

    private Boolean flagtimelytrial;

    private Boolean flaglevelreturn;

    private Boolean flagreclosing;

    private Boolean flagundervoltagealarm;

    private Boolean flagundervoltagecontrol;

    private Boolean flagovervoltagealarm;

    private Boolean flagovervoltagecontrol;

    private Boolean flagmissphasealarm;

    private Boolean flagmissphasecontrol;

    private Boolean flagovercurrentalarm;

    private Boolean flagovercurrentcontrol;

    private Boolean flagtrialsource;

    private Boolean flagmissearthlinealarm;

    private Boolean flagmissearthlinecontrol;

    private Integer residualthresholdlevel;

    private Integer delaytimelevel;

    private Integer residualalarmtimelevel;

    private Date recordtime;

    private static final long serialVersionUID = 1L;

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public Boolean getFlagallalarm() {
        return flagallalarm;
    }

    public void setFlagallalarm(Boolean flagallalarm) {
        this.flagallalarm = flagallalarm;
    }

    public Boolean getFlaglightalarm() {
        return flaglightalarm;
    }

    public void setFlaglightalarm(Boolean flaglightalarm) {
        this.flaglightalarm = flaglightalarm;
    }

    public Boolean getFlagaudioalarm() {
        return flagaudioalarm;
    }

    public void setFlagaudioalarm(Boolean flagaudioalarm) {
        this.flagaudioalarm = flagaudioalarm;
    }

    public Boolean getFlagtimelytrial() {
        return flagtimelytrial;
    }

    public void setFlagtimelytrial(Boolean flagtimelytrial) {
        this.flagtimelytrial = flagtimelytrial;
    }

    public Boolean getFlaglevelreturn() {
        return flaglevelreturn;
    }

    public void setFlaglevelreturn(Boolean flaglevelreturn) {
        this.flaglevelreturn = flaglevelreturn;
    }

    public Boolean getFlagreclosing() {
        return flagreclosing;
    }

    public void setFlagreclosing(Boolean flagreclosing) {
        this.flagreclosing = flagreclosing;
    }

    public Boolean getFlagundervoltagealarm() {
        return flagundervoltagealarm;
    }

    public void setFlagundervoltagealarm(Boolean flagundervoltagealarm) {
        this.flagundervoltagealarm = flagundervoltagealarm;
    }

    public Boolean getFlagundervoltagecontrol() {
        return flagundervoltagecontrol;
    }

    public void setFlagundervoltagecontrol(Boolean flagundervoltagecontrol) {
        this.flagundervoltagecontrol = flagundervoltagecontrol;
    }

    public Boolean getFlagovervoltagealarm() {
        return flagovervoltagealarm;
    }

    public void setFlagovervoltagealarm(Boolean flagovervoltagealarm) {
        this.flagovervoltagealarm = flagovervoltagealarm;
    }

    public Boolean getFlagovervoltagecontrol() {
        return flagovervoltagecontrol;
    }

    public void setFlagovervoltagecontrol(Boolean flagovervoltagecontrol) {
        this.flagovervoltagecontrol = flagovervoltagecontrol;
    }

    public Boolean getFlagmissphasealarm() {
        return flagmissphasealarm;
    }

    public void setFlagmissphasealarm(Boolean flagmissphasealarm) {
        this.flagmissphasealarm = flagmissphasealarm;
    }

    public Boolean getFlagmissphasecontrol() {
        return flagmissphasecontrol;
    }

    public void setFlagmissphasecontrol(Boolean flagmissphasecontrol) {
        this.flagmissphasecontrol = flagmissphasecontrol;
    }

    public Boolean getFlagovercurrentalarm() {
        return flagovercurrentalarm;
    }

    public void setFlagovercurrentalarm(Boolean flagovercurrentalarm) {
        this.flagovercurrentalarm = flagovercurrentalarm;
    }

    public Boolean getFlagovercurrentcontrol() {
        return flagovercurrentcontrol;
    }

    public void setFlagovercurrentcontrol(Boolean flagovercurrentcontrol) {
        this.flagovercurrentcontrol = flagovercurrentcontrol;
    }

    public Boolean getFlagtrialsource() {
        return flagtrialsource;
    }

    public void setFlagtrialsource(Boolean flagtrialsource) {
        this.flagtrialsource = flagtrialsource;
    }

    public Boolean getFlagmissearthlinealarm() {
        return flagmissearthlinealarm;
    }

    public void setFlagmissearthlinealarm(Boolean flagmissearthlinealarm) {
        this.flagmissearthlinealarm = flagmissearthlinealarm;
    }

    public Boolean getFlagmissearthlinecontrol() {
        return flagmissearthlinecontrol;
    }

    public void setFlagmissearthlinecontrol(Boolean flagmissearthlinecontrol) {
        this.flagmissearthlinecontrol = flagmissearthlinecontrol;
    }

    public Integer getResidualthresholdlevel() {
        return residualthresholdlevel;
    }

    public void setResidualthresholdlevel(Integer residualthresholdlevel) {
        this.residualthresholdlevel = residualthresholdlevel;
    }

    public Integer getDelaytimelevel() {
        return delaytimelevel;
    }

    public void setDelaytimelevel(Integer delaytimelevel) {
        this.delaytimelevel = delaytimelevel;
    }

    public Integer getResidualalarmtimelevel() {
        return residualalarmtimelevel;
    }

    public void setResidualalarmtimelevel(Integer residualalarmtimelevel) {
        this.residualalarmtimelevel = residualalarmtimelevel;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
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
        DeviceControlword other = (DeviceControlword) that;
        return (this.getDeviceid() == null ? other.getDeviceid() == null : this.getDeviceid().equals(other.getDeviceid()))
            && (this.getFlagallalarm() == null ? other.getFlagallalarm() == null : this.getFlagallalarm().equals(other.getFlagallalarm()))
            && (this.getFlaglightalarm() == null ? other.getFlaglightalarm() == null : this.getFlaglightalarm().equals(other.getFlaglightalarm()))
            && (this.getFlagaudioalarm() == null ? other.getFlagaudioalarm() == null : this.getFlagaudioalarm().equals(other.getFlagaudioalarm()))
            && (this.getFlagtimelytrial() == null ? other.getFlagtimelytrial() == null : this.getFlagtimelytrial().equals(other.getFlagtimelytrial()))
            && (this.getFlaglevelreturn() == null ? other.getFlaglevelreturn() == null : this.getFlaglevelreturn().equals(other.getFlaglevelreturn()))
            && (this.getFlagreclosing() == null ? other.getFlagreclosing() == null : this.getFlagreclosing().equals(other.getFlagreclosing()))
            && (this.getFlagundervoltagealarm() == null ? other.getFlagundervoltagealarm() == null : this.getFlagundervoltagealarm().equals(other.getFlagundervoltagealarm()))
            && (this.getFlagundervoltagecontrol() == null ? other.getFlagundervoltagecontrol() == null : this.getFlagundervoltagecontrol().equals(other.getFlagundervoltagecontrol()))
            && (this.getFlagovervoltagealarm() == null ? other.getFlagovervoltagealarm() == null : this.getFlagovervoltagealarm().equals(other.getFlagovervoltagealarm()))
            && (this.getFlagovervoltagecontrol() == null ? other.getFlagovervoltagecontrol() == null : this.getFlagovervoltagecontrol().equals(other.getFlagovervoltagecontrol()))
            && (this.getFlagmissphasealarm() == null ? other.getFlagmissphasealarm() == null : this.getFlagmissphasealarm().equals(other.getFlagmissphasealarm()))
            && (this.getFlagmissphasecontrol() == null ? other.getFlagmissphasecontrol() == null : this.getFlagmissphasecontrol().equals(other.getFlagmissphasecontrol()))
            && (this.getFlagovercurrentalarm() == null ? other.getFlagovercurrentalarm() == null : this.getFlagovercurrentalarm().equals(other.getFlagovercurrentalarm()))
            && (this.getFlagovercurrentcontrol() == null ? other.getFlagovercurrentcontrol() == null : this.getFlagovercurrentcontrol().equals(other.getFlagovercurrentcontrol()))
            && (this.getFlagtrialsource() == null ? other.getFlagtrialsource() == null : this.getFlagtrialsource().equals(other.getFlagtrialsource()))
            && (this.getFlagmissearthlinealarm() == null ? other.getFlagmissearthlinealarm() == null : this.getFlagmissearthlinealarm().equals(other.getFlagmissearthlinealarm()))
            && (this.getFlagmissearthlinecontrol() == null ? other.getFlagmissearthlinecontrol() == null : this.getFlagmissearthlinecontrol().equals(other.getFlagmissearthlinecontrol()))
            && (this.getResidualthresholdlevel() == null ? other.getResidualthresholdlevel() == null : this.getResidualthresholdlevel().equals(other.getResidualthresholdlevel()))
            && (this.getDelaytimelevel() == null ? other.getDelaytimelevel() == null : this.getDelaytimelevel().equals(other.getDelaytimelevel()))
            && (this.getResidualalarmtimelevel() == null ? other.getResidualalarmtimelevel() == null : this.getResidualalarmtimelevel().equals(other.getResidualalarmtimelevel()))
            && (this.getRecordtime() == null ? other.getRecordtime() == null : this.getRecordtime().equals(other.getRecordtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeviceid() == null) ? 0 : getDeviceid().hashCode());
        result = prime * result + ((getFlagallalarm() == null) ? 0 : getFlagallalarm().hashCode());
        result = prime * result + ((getFlaglightalarm() == null) ? 0 : getFlaglightalarm().hashCode());
        result = prime * result + ((getFlagaudioalarm() == null) ? 0 : getFlagaudioalarm().hashCode());
        result = prime * result + ((getFlagtimelytrial() == null) ? 0 : getFlagtimelytrial().hashCode());
        result = prime * result + ((getFlaglevelreturn() == null) ? 0 : getFlaglevelreturn().hashCode());
        result = prime * result + ((getFlagreclosing() == null) ? 0 : getFlagreclosing().hashCode());
        result = prime * result + ((getFlagundervoltagealarm() == null) ? 0 : getFlagundervoltagealarm().hashCode());
        result = prime * result + ((getFlagundervoltagecontrol() == null) ? 0 : getFlagundervoltagecontrol().hashCode());
        result = prime * result + ((getFlagovervoltagealarm() == null) ? 0 : getFlagovervoltagealarm().hashCode());
        result = prime * result + ((getFlagovervoltagecontrol() == null) ? 0 : getFlagovervoltagecontrol().hashCode());
        result = prime * result + ((getFlagmissphasealarm() == null) ? 0 : getFlagmissphasealarm().hashCode());
        result = prime * result + ((getFlagmissphasecontrol() == null) ? 0 : getFlagmissphasecontrol().hashCode());
        result = prime * result + ((getFlagovercurrentalarm() == null) ? 0 : getFlagovercurrentalarm().hashCode());
        result = prime * result + ((getFlagovercurrentcontrol() == null) ? 0 : getFlagovercurrentcontrol().hashCode());
        result = prime * result + ((getFlagtrialsource() == null) ? 0 : getFlagtrialsource().hashCode());
        result = prime * result + ((getFlagmissearthlinealarm() == null) ? 0 : getFlagmissearthlinealarm().hashCode());
        result = prime * result + ((getFlagmissearthlinecontrol() == null) ? 0 : getFlagmissearthlinecontrol().hashCode());
        result = prime * result + ((getResidualthresholdlevel() == null) ? 0 : getResidualthresholdlevel().hashCode());
        result = prime * result + ((getDelaytimelevel() == null) ? 0 : getDelaytimelevel().hashCode());
        result = prime * result + ((getResidualalarmtimelevel() == null) ? 0 : getResidualalarmtimelevel().hashCode());
        result = prime * result + ((getRecordtime() == null) ? 0 : getRecordtime().hashCode());
        return result;
    }
}