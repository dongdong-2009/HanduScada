package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceControlwordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceControlwordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDeviceidIsNull() {
            addCriterion("deviceId is null");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNotNull() {
            addCriterion("deviceId is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceidEqualTo(String value) {
            addCriterion("deviceId =", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotEqualTo(String value) {
            addCriterion("deviceId <>", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThan(String value) {
            addCriterion("deviceId >", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThanOrEqualTo(String value) {
            addCriterion("deviceId >=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThan(String value) {
            addCriterion("deviceId <", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThanOrEqualTo(String value) {
            addCriterion("deviceId <=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLike(String value) {
            addCriterion("deviceId like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotLike(String value) {
            addCriterion("deviceId not like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIn(List<String> values) {
            addCriterion("deviceId in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotIn(List<String> values) {
            addCriterion("deviceId not in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidBetween(String value1, String value2) {
            addCriterion("deviceId between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotBetween(String value1, String value2) {
            addCriterion("deviceId not between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmIsNull() {
            addCriterion("flagAllAlarm is null");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmIsNotNull() {
            addCriterion("flagAllAlarm is not null");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmEqualTo(Boolean value) {
            addCriterion("flagAllAlarm =", value, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmNotEqualTo(Boolean value) {
            addCriterion("flagAllAlarm <>", value, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmGreaterThan(Boolean value) {
            addCriterion("flagAllAlarm >", value, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagAllAlarm >=", value, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmLessThan(Boolean value) {
            addCriterion("flagAllAlarm <", value, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmLessThanOrEqualTo(Boolean value) {
            addCriterion("flagAllAlarm <=", value, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmIn(List<Boolean> values) {
            addCriterion("flagAllAlarm in", values, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmNotIn(List<Boolean> values) {
            addCriterion("flagAllAlarm not in", values, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmBetween(Boolean value1, Boolean value2) {
            addCriterion("flagAllAlarm between", value1, value2, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlagallalarmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagAllAlarm not between", value1, value2, "flagallalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmIsNull() {
            addCriterion("flagLightAlarm is null");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmIsNotNull() {
            addCriterion("flagLightAlarm is not null");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmEqualTo(Boolean value) {
            addCriterion("flagLightAlarm =", value, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmNotEqualTo(Boolean value) {
            addCriterion("flagLightAlarm <>", value, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmGreaterThan(Boolean value) {
            addCriterion("flagLightAlarm >", value, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagLightAlarm >=", value, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmLessThan(Boolean value) {
            addCriterion("flagLightAlarm <", value, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmLessThanOrEqualTo(Boolean value) {
            addCriterion("flagLightAlarm <=", value, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmIn(List<Boolean> values) {
            addCriterion("flagLightAlarm in", values, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmNotIn(List<Boolean> values) {
            addCriterion("flagLightAlarm not in", values, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmBetween(Boolean value1, Boolean value2) {
            addCriterion("flagLightAlarm between", value1, value2, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlaglightalarmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagLightAlarm not between", value1, value2, "flaglightalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmIsNull() {
            addCriterion("flagAudioAlarm is null");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmIsNotNull() {
            addCriterion("flagAudioAlarm is not null");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmEqualTo(Boolean value) {
            addCriterion("flagAudioAlarm =", value, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmNotEqualTo(Boolean value) {
            addCriterion("flagAudioAlarm <>", value, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmGreaterThan(Boolean value) {
            addCriterion("flagAudioAlarm >", value, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagAudioAlarm >=", value, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmLessThan(Boolean value) {
            addCriterion("flagAudioAlarm <", value, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmLessThanOrEqualTo(Boolean value) {
            addCriterion("flagAudioAlarm <=", value, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmIn(List<Boolean> values) {
            addCriterion("flagAudioAlarm in", values, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmNotIn(List<Boolean> values) {
            addCriterion("flagAudioAlarm not in", values, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmBetween(Boolean value1, Boolean value2) {
            addCriterion("flagAudioAlarm between", value1, value2, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagaudioalarmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagAudioAlarm not between", value1, value2, "flagaudioalarm");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialIsNull() {
            addCriterion("flagTimelyTrial is null");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialIsNotNull() {
            addCriterion("flagTimelyTrial is not null");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialEqualTo(Boolean value) {
            addCriterion("flagTimelyTrial =", value, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialNotEqualTo(Boolean value) {
            addCriterion("flagTimelyTrial <>", value, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialGreaterThan(Boolean value) {
            addCriterion("flagTimelyTrial >", value, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagTimelyTrial >=", value, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialLessThan(Boolean value) {
            addCriterion("flagTimelyTrial <", value, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialLessThanOrEqualTo(Boolean value) {
            addCriterion("flagTimelyTrial <=", value, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialIn(List<Boolean> values) {
            addCriterion("flagTimelyTrial in", values, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialNotIn(List<Boolean> values) {
            addCriterion("flagTimelyTrial not in", values, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialBetween(Boolean value1, Boolean value2) {
            addCriterion("flagTimelyTrial between", value1, value2, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlagtimelytrialNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagTimelyTrial not between", value1, value2, "flagtimelytrial");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnIsNull() {
            addCriterion("flagLevelReturn is null");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnIsNotNull() {
            addCriterion("flagLevelReturn is not null");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnEqualTo(Boolean value) {
            addCriterion("flagLevelReturn =", value, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnNotEqualTo(Boolean value) {
            addCriterion("flagLevelReturn <>", value, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnGreaterThan(Boolean value) {
            addCriterion("flagLevelReturn >", value, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagLevelReturn >=", value, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnLessThan(Boolean value) {
            addCriterion("flagLevelReturn <", value, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnLessThanOrEqualTo(Boolean value) {
            addCriterion("flagLevelReturn <=", value, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnIn(List<Boolean> values) {
            addCriterion("flagLevelReturn in", values, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnNotIn(List<Boolean> values) {
            addCriterion("flagLevelReturn not in", values, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnBetween(Boolean value1, Boolean value2) {
            addCriterion("flagLevelReturn between", value1, value2, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlaglevelreturnNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagLevelReturn not between", value1, value2, "flaglevelreturn");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingIsNull() {
            addCriterion("flagReclosing is null");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingIsNotNull() {
            addCriterion("flagReclosing is not null");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingEqualTo(Boolean value) {
            addCriterion("flagReclosing =", value, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingNotEqualTo(Boolean value) {
            addCriterion("flagReclosing <>", value, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingGreaterThan(Boolean value) {
            addCriterion("flagReclosing >", value, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagReclosing >=", value, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingLessThan(Boolean value) {
            addCriterion("flagReclosing <", value, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingLessThanOrEqualTo(Boolean value) {
            addCriterion("flagReclosing <=", value, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingIn(List<Boolean> values) {
            addCriterion("flagReclosing in", values, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingNotIn(List<Boolean> values) {
            addCriterion("flagReclosing not in", values, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingBetween(Boolean value1, Boolean value2) {
            addCriterion("flagReclosing between", value1, value2, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagreclosingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagReclosing not between", value1, value2, "flagreclosing");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmIsNull() {
            addCriterion("flagUnderVoltageAlarm is null");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmIsNotNull() {
            addCriterion("flagUnderVoltageAlarm is not null");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmEqualTo(Boolean value) {
            addCriterion("flagUnderVoltageAlarm =", value, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmNotEqualTo(Boolean value) {
            addCriterion("flagUnderVoltageAlarm <>", value, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmGreaterThan(Boolean value) {
            addCriterion("flagUnderVoltageAlarm >", value, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagUnderVoltageAlarm >=", value, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmLessThan(Boolean value) {
            addCriterion("flagUnderVoltageAlarm <", value, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmLessThanOrEqualTo(Boolean value) {
            addCriterion("flagUnderVoltageAlarm <=", value, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmIn(List<Boolean> values) {
            addCriterion("flagUnderVoltageAlarm in", values, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmNotIn(List<Boolean> values) {
            addCriterion("flagUnderVoltageAlarm not in", values, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmBetween(Boolean value1, Boolean value2) {
            addCriterion("flagUnderVoltageAlarm between", value1, value2, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagealarmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagUnderVoltageAlarm not between", value1, value2, "flagundervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolIsNull() {
            addCriterion("flagUnderVoltageControl is null");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolIsNotNull() {
            addCriterion("flagUnderVoltageControl is not null");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolEqualTo(Boolean value) {
            addCriterion("flagUnderVoltageControl =", value, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolNotEqualTo(Boolean value) {
            addCriterion("flagUnderVoltageControl <>", value, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolGreaterThan(Boolean value) {
            addCriterion("flagUnderVoltageControl >", value, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagUnderVoltageControl >=", value, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolLessThan(Boolean value) {
            addCriterion("flagUnderVoltageControl <", value, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolLessThanOrEqualTo(Boolean value) {
            addCriterion("flagUnderVoltageControl <=", value, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolIn(List<Boolean> values) {
            addCriterion("flagUnderVoltageControl in", values, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolNotIn(List<Boolean> values) {
            addCriterion("flagUnderVoltageControl not in", values, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolBetween(Boolean value1, Boolean value2) {
            addCriterion("flagUnderVoltageControl between", value1, value2, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagundervoltagecontrolNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagUnderVoltageControl not between", value1, value2, "flagundervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmIsNull() {
            addCriterion("flagOverVoltageAlarm is null");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmIsNotNull() {
            addCriterion("flagOverVoltageAlarm is not null");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmEqualTo(Boolean value) {
            addCriterion("flagOverVoltageAlarm =", value, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmNotEqualTo(Boolean value) {
            addCriterion("flagOverVoltageAlarm <>", value, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmGreaterThan(Boolean value) {
            addCriterion("flagOverVoltageAlarm >", value, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagOverVoltageAlarm >=", value, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmLessThan(Boolean value) {
            addCriterion("flagOverVoltageAlarm <", value, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmLessThanOrEqualTo(Boolean value) {
            addCriterion("flagOverVoltageAlarm <=", value, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmIn(List<Boolean> values) {
            addCriterion("flagOverVoltageAlarm in", values, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmNotIn(List<Boolean> values) {
            addCriterion("flagOverVoltageAlarm not in", values, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmBetween(Boolean value1, Boolean value2) {
            addCriterion("flagOverVoltageAlarm between", value1, value2, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagealarmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagOverVoltageAlarm not between", value1, value2, "flagovervoltagealarm");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolIsNull() {
            addCriterion("flagOverVoltageControl is null");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolIsNotNull() {
            addCriterion("flagOverVoltageControl is not null");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolEqualTo(Boolean value) {
            addCriterion("flagOverVoltageControl =", value, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolNotEqualTo(Boolean value) {
            addCriterion("flagOverVoltageControl <>", value, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolGreaterThan(Boolean value) {
            addCriterion("flagOverVoltageControl >", value, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagOverVoltageControl >=", value, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolLessThan(Boolean value) {
            addCriterion("flagOverVoltageControl <", value, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolLessThanOrEqualTo(Boolean value) {
            addCriterion("flagOverVoltageControl <=", value, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolIn(List<Boolean> values) {
            addCriterion("flagOverVoltageControl in", values, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolNotIn(List<Boolean> values) {
            addCriterion("flagOverVoltageControl not in", values, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolBetween(Boolean value1, Boolean value2) {
            addCriterion("flagOverVoltageControl between", value1, value2, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovervoltagecontrolNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagOverVoltageControl not between", value1, value2, "flagovervoltagecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmIsNull() {
            addCriterion("flagMissPhaseAlarm is null");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmIsNotNull() {
            addCriterion("flagMissPhaseAlarm is not null");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmEqualTo(Boolean value) {
            addCriterion("flagMissPhaseAlarm =", value, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmNotEqualTo(Boolean value) {
            addCriterion("flagMissPhaseAlarm <>", value, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmGreaterThan(Boolean value) {
            addCriterion("flagMissPhaseAlarm >", value, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagMissPhaseAlarm >=", value, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmLessThan(Boolean value) {
            addCriterion("flagMissPhaseAlarm <", value, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmLessThanOrEqualTo(Boolean value) {
            addCriterion("flagMissPhaseAlarm <=", value, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmIn(List<Boolean> values) {
            addCriterion("flagMissPhaseAlarm in", values, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmNotIn(List<Boolean> values) {
            addCriterion("flagMissPhaseAlarm not in", values, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmBetween(Boolean value1, Boolean value2) {
            addCriterion("flagMissPhaseAlarm between", value1, value2, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasealarmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagMissPhaseAlarm not between", value1, value2, "flagmissphasealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolIsNull() {
            addCriterion("flagMissPhaseControl is null");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolIsNotNull() {
            addCriterion("flagMissPhaseControl is not null");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolEqualTo(Boolean value) {
            addCriterion("flagMissPhaseControl =", value, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolNotEqualTo(Boolean value) {
            addCriterion("flagMissPhaseControl <>", value, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolGreaterThan(Boolean value) {
            addCriterion("flagMissPhaseControl >", value, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagMissPhaseControl >=", value, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolLessThan(Boolean value) {
            addCriterion("flagMissPhaseControl <", value, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolLessThanOrEqualTo(Boolean value) {
            addCriterion("flagMissPhaseControl <=", value, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolIn(List<Boolean> values) {
            addCriterion("flagMissPhaseControl in", values, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolNotIn(List<Boolean> values) {
            addCriterion("flagMissPhaseControl not in", values, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolBetween(Boolean value1, Boolean value2) {
            addCriterion("flagMissPhaseControl between", value1, value2, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissphasecontrolNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagMissPhaseControl not between", value1, value2, "flagmissphasecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmIsNull() {
            addCriterion("flagOverCurrentAlarm is null");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmIsNotNull() {
            addCriterion("flagOverCurrentAlarm is not null");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmEqualTo(Boolean value) {
            addCriterion("flagOverCurrentAlarm =", value, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmNotEqualTo(Boolean value) {
            addCriterion("flagOverCurrentAlarm <>", value, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmGreaterThan(Boolean value) {
            addCriterion("flagOverCurrentAlarm >", value, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagOverCurrentAlarm >=", value, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmLessThan(Boolean value) {
            addCriterion("flagOverCurrentAlarm <", value, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmLessThanOrEqualTo(Boolean value) {
            addCriterion("flagOverCurrentAlarm <=", value, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmIn(List<Boolean> values) {
            addCriterion("flagOverCurrentAlarm in", values, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmNotIn(List<Boolean> values) {
            addCriterion("flagOverCurrentAlarm not in", values, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmBetween(Boolean value1, Boolean value2) {
            addCriterion("flagOverCurrentAlarm between", value1, value2, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentalarmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagOverCurrentAlarm not between", value1, value2, "flagovercurrentalarm");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolIsNull() {
            addCriterion("flagOverCurrentControl is null");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolIsNotNull() {
            addCriterion("flagOverCurrentControl is not null");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolEqualTo(Boolean value) {
            addCriterion("flagOverCurrentControl =", value, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolNotEqualTo(Boolean value) {
            addCriterion("flagOverCurrentControl <>", value, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolGreaterThan(Boolean value) {
            addCriterion("flagOverCurrentControl >", value, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagOverCurrentControl >=", value, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolLessThan(Boolean value) {
            addCriterion("flagOverCurrentControl <", value, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolLessThanOrEqualTo(Boolean value) {
            addCriterion("flagOverCurrentControl <=", value, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolIn(List<Boolean> values) {
            addCriterion("flagOverCurrentControl in", values, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolNotIn(List<Boolean> values) {
            addCriterion("flagOverCurrentControl not in", values, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolBetween(Boolean value1, Boolean value2) {
            addCriterion("flagOverCurrentControl between", value1, value2, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagovercurrentcontrolNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagOverCurrentControl not between", value1, value2, "flagovercurrentcontrol");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceIsNull() {
            addCriterion("flagTrialSource is null");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceIsNotNull() {
            addCriterion("flagTrialSource is not null");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceEqualTo(Boolean value) {
            addCriterion("flagTrialSource =", value, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceNotEqualTo(Boolean value) {
            addCriterion("flagTrialSource <>", value, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceGreaterThan(Boolean value) {
            addCriterion("flagTrialSource >", value, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagTrialSource >=", value, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceLessThan(Boolean value) {
            addCriterion("flagTrialSource <", value, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceLessThanOrEqualTo(Boolean value) {
            addCriterion("flagTrialSource <=", value, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceIn(List<Boolean> values) {
            addCriterion("flagTrialSource in", values, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceNotIn(List<Boolean> values) {
            addCriterion("flagTrialSource not in", values, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceBetween(Boolean value1, Boolean value2) {
            addCriterion("flagTrialSource between", value1, value2, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagtrialsourceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagTrialSource not between", value1, value2, "flagtrialsource");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmIsNull() {
            addCriterion("flagMissEarthLineAlarm is null");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmIsNotNull() {
            addCriterion("flagMissEarthLineAlarm is not null");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmEqualTo(Boolean value) {
            addCriterion("flagMissEarthLineAlarm =", value, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmNotEqualTo(Boolean value) {
            addCriterion("flagMissEarthLineAlarm <>", value, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmGreaterThan(Boolean value) {
            addCriterion("flagMissEarthLineAlarm >", value, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagMissEarthLineAlarm >=", value, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmLessThan(Boolean value) {
            addCriterion("flagMissEarthLineAlarm <", value, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmLessThanOrEqualTo(Boolean value) {
            addCriterion("flagMissEarthLineAlarm <=", value, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmIn(List<Boolean> values) {
            addCriterion("flagMissEarthLineAlarm in", values, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmNotIn(List<Boolean> values) {
            addCriterion("flagMissEarthLineAlarm not in", values, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmBetween(Boolean value1, Boolean value2) {
            addCriterion("flagMissEarthLineAlarm between", value1, value2, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinealarmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagMissEarthLineAlarm not between", value1, value2, "flagmissearthlinealarm");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolIsNull() {
            addCriterion("flagMissEarthLineControl is null");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolIsNotNull() {
            addCriterion("flagMissEarthLineControl is not null");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolEqualTo(Boolean value) {
            addCriterion("flagMissEarthLineControl =", value, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolNotEqualTo(Boolean value) {
            addCriterion("flagMissEarthLineControl <>", value, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolGreaterThan(Boolean value) {
            addCriterion("flagMissEarthLineControl >", value, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flagMissEarthLineControl >=", value, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolLessThan(Boolean value) {
            addCriterion("flagMissEarthLineControl <", value, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolLessThanOrEqualTo(Boolean value) {
            addCriterion("flagMissEarthLineControl <=", value, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolIn(List<Boolean> values) {
            addCriterion("flagMissEarthLineControl in", values, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolNotIn(List<Boolean> values) {
            addCriterion("flagMissEarthLineControl not in", values, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolBetween(Boolean value1, Boolean value2) {
            addCriterion("flagMissEarthLineControl between", value1, value2, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andFlagmissearthlinecontrolNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flagMissEarthLineControl not between", value1, value2, "flagmissearthlinecontrol");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelIsNull() {
            addCriterion("residualThresholdLevel is null");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelIsNotNull() {
            addCriterion("residualThresholdLevel is not null");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelEqualTo(Integer value) {
            addCriterion("residualThresholdLevel =", value, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelNotEqualTo(Integer value) {
            addCriterion("residualThresholdLevel <>", value, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelGreaterThan(Integer value) {
            addCriterion("residualThresholdLevel >", value, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("residualThresholdLevel >=", value, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelLessThan(Integer value) {
            addCriterion("residualThresholdLevel <", value, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelLessThanOrEqualTo(Integer value) {
            addCriterion("residualThresholdLevel <=", value, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelIn(List<Integer> values) {
            addCriterion("residualThresholdLevel in", values, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelNotIn(List<Integer> values) {
            addCriterion("residualThresholdLevel not in", values, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelBetween(Integer value1, Integer value2) {
            addCriterion("residualThresholdLevel between", value1, value2, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andResidualthresholdlevelNotBetween(Integer value1, Integer value2) {
            addCriterion("residualThresholdLevel not between", value1, value2, "residualthresholdlevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelIsNull() {
            addCriterion("delayTimeLevel is null");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelIsNotNull() {
            addCriterion("delayTimeLevel is not null");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelEqualTo(Integer value) {
            addCriterion("delayTimeLevel =", value, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelNotEqualTo(Integer value) {
            addCriterion("delayTimeLevel <>", value, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelGreaterThan(Integer value) {
            addCriterion("delayTimeLevel >", value, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("delayTimeLevel >=", value, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelLessThan(Integer value) {
            addCriterion("delayTimeLevel <", value, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelLessThanOrEqualTo(Integer value) {
            addCriterion("delayTimeLevel <=", value, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelIn(List<Integer> values) {
            addCriterion("delayTimeLevel in", values, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelNotIn(List<Integer> values) {
            addCriterion("delayTimeLevel not in", values, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelBetween(Integer value1, Integer value2) {
            addCriterion("delayTimeLevel between", value1, value2, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andDelaytimelevelNotBetween(Integer value1, Integer value2) {
            addCriterion("delayTimeLevel not between", value1, value2, "delaytimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelIsNull() {
            addCriterion("residualAlarmTimeLevel is null");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelIsNotNull() {
            addCriterion("residualAlarmTimeLevel is not null");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelEqualTo(Integer value) {
            addCriterion("residualAlarmTimeLevel =", value, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelNotEqualTo(Integer value) {
            addCriterion("residualAlarmTimeLevel <>", value, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelGreaterThan(Integer value) {
            addCriterion("residualAlarmTimeLevel >", value, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("residualAlarmTimeLevel >=", value, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelLessThan(Integer value) {
            addCriterion("residualAlarmTimeLevel <", value, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelLessThanOrEqualTo(Integer value) {
            addCriterion("residualAlarmTimeLevel <=", value, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelIn(List<Integer> values) {
            addCriterion("residualAlarmTimeLevel in", values, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelNotIn(List<Integer> values) {
            addCriterion("residualAlarmTimeLevel not in", values, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelBetween(Integer value1, Integer value2) {
            addCriterion("residualAlarmTimeLevel between", value1, value2, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andResidualalarmtimelevelNotBetween(Integer value1, Integer value2) {
            addCriterion("residualAlarmTimeLevel not between", value1, value2, "residualalarmtimelevel");
            return (Criteria) this;
        }

        public Criteria andRecordtimeIsNull() {
            addCriterion("recordtime is null");
            return (Criteria) this;
        }

        public Criteria andRecordtimeIsNotNull() {
            addCriterion("recordtime is not null");
            return (Criteria) this;
        }

        public Criteria andRecordtimeEqualTo(Date value) {
            addCriterion("recordtime =", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeNotEqualTo(Date value) {
            addCriterion("recordtime <>", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeGreaterThan(Date value) {
            addCriterion("recordtime >", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recordtime >=", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeLessThan(Date value) {
            addCriterion("recordtime <", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeLessThanOrEqualTo(Date value) {
            addCriterion("recordtime <=", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeIn(List<Date> values) {
            addCriterion("recordtime in", values, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeNotIn(List<Date> values) {
            addCriterion("recordtime not in", values, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeBetween(Date value1, Date value2) {
            addCriterion("recordtime between", value1, value2, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeNotBetween(Date value1, Date value2) {
            addCriterion("recordtime not between", value1, value2, "recordtime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}