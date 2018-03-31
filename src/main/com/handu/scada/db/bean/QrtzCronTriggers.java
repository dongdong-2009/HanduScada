package main.com.handu.scada.db.bean;

import java.io.Serializable;

public class QrtzCronTriggers implements Serializable {
    private String SCHED_NAME;

    private String TRIGGER_NAME;

    private String TRIGGER_GROUP;

    private String CRON_EXPRESSION;

    private String TIME_ZONE_ID;

    private static final long serialVersionUID = 1L;

    public String getSCHED_NAME() {
        return SCHED_NAME;
    }

    public void setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME == null ? null : SCHED_NAME.trim();
    }

    public String getTRIGGER_NAME() {
        return TRIGGER_NAME;
    }

    public void setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME == null ? null : TRIGGER_NAME.trim();
    }

    public String getTRIGGER_GROUP() {
        return TRIGGER_GROUP;
    }

    public void setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP == null ? null : TRIGGER_GROUP.trim();
    }

    public String getCRON_EXPRESSION() {
        return CRON_EXPRESSION;
    }

    public void setCRON_EXPRESSION(String CRON_EXPRESSION) {
        this.CRON_EXPRESSION = CRON_EXPRESSION == null ? null : CRON_EXPRESSION.trim();
    }

    public String getTIME_ZONE_ID() {
        return TIME_ZONE_ID;
    }

    public void setTIME_ZONE_ID(String TIME_ZONE_ID) {
        this.TIME_ZONE_ID = TIME_ZONE_ID == null ? null : TIME_ZONE_ID.trim();
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
        QrtzCronTriggers other = (QrtzCronTriggers) that;
        return (this.getSCHED_NAME() == null ? other.getSCHED_NAME() == null : this.getSCHED_NAME().equals(other.getSCHED_NAME()))
            && (this.getTRIGGER_NAME() == null ? other.getTRIGGER_NAME() == null : this.getTRIGGER_NAME().equals(other.getTRIGGER_NAME()))
            && (this.getTRIGGER_GROUP() == null ? other.getTRIGGER_GROUP() == null : this.getTRIGGER_GROUP().equals(other.getTRIGGER_GROUP()))
            && (this.getCRON_EXPRESSION() == null ? other.getCRON_EXPRESSION() == null : this.getCRON_EXPRESSION().equals(other.getCRON_EXPRESSION()))
            && (this.getTIME_ZONE_ID() == null ? other.getTIME_ZONE_ID() == null : this.getTIME_ZONE_ID().equals(other.getTIME_ZONE_ID()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSCHED_NAME() == null) ? 0 : getSCHED_NAME().hashCode());
        result = prime * result + ((getTRIGGER_NAME() == null) ? 0 : getTRIGGER_NAME().hashCode());
        result = prime * result + ((getTRIGGER_GROUP() == null) ? 0 : getTRIGGER_GROUP().hashCode());
        result = prime * result + ((getCRON_EXPRESSION() == null) ? 0 : getCRON_EXPRESSION().hashCode());
        result = prime * result + ((getTIME_ZONE_ID() == null) ? 0 : getTIME_ZONE_ID().hashCode());
        return result;
    }
}