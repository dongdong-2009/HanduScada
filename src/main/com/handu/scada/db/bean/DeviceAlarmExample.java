package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceAlarmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceAlarmExample() {
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

        public Criteria andAlarmidIsNull() {
            addCriterion("AlarmId is null");
            return (Criteria) this;
        }

        public Criteria andAlarmidIsNotNull() {
            addCriterion("AlarmId is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmidEqualTo(String value) {
            addCriterion("AlarmId =", value, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidNotEqualTo(String value) {
            addCriterion("AlarmId <>", value, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidGreaterThan(String value) {
            addCriterion("AlarmId >", value, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidGreaterThanOrEqualTo(String value) {
            addCriterion("AlarmId >=", value, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidLessThan(String value) {
            addCriterion("AlarmId <", value, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidLessThanOrEqualTo(String value) {
            addCriterion("AlarmId <=", value, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidLike(String value) {
            addCriterion("AlarmId like", value, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidNotLike(String value) {
            addCriterion("AlarmId not like", value, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidIn(List<String> values) {
            addCriterion("AlarmId in", values, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidNotIn(List<String> values) {
            addCriterion("AlarmId not in", values, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidBetween(String value1, String value2) {
            addCriterion("AlarmId between", value1, value2, "alarmid");
            return (Criteria) this;
        }

        public Criteria andAlarmidNotBetween(String value1, String value2) {
            addCriterion("AlarmId not between", value1, value2, "alarmid");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameIsNull() {
            addCriterion("DeviceTableName is null");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameIsNotNull() {
            addCriterion("DeviceTableName is not null");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameEqualTo(String value) {
            addCriterion("DeviceTableName =", value, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameNotEqualTo(String value) {
            addCriterion("DeviceTableName <>", value, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameGreaterThan(String value) {
            addCriterion("DeviceTableName >", value, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameGreaterThanOrEqualTo(String value) {
            addCriterion("DeviceTableName >=", value, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameLessThan(String value) {
            addCriterion("DeviceTableName <", value, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameLessThanOrEqualTo(String value) {
            addCriterion("DeviceTableName <=", value, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameLike(String value) {
            addCriterion("DeviceTableName like", value, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameNotLike(String value) {
            addCriterion("DeviceTableName not like", value, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameIn(List<String> values) {
            addCriterion("DeviceTableName in", values, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameNotIn(List<String> values) {
            addCriterion("DeviceTableName not in", values, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameBetween(String value1, String value2) {
            addCriterion("DeviceTableName between", value1, value2, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDevicetablenameNotBetween(String value1, String value2) {
            addCriterion("DeviceTableName not between", value1, value2, "devicetablename");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNull() {
            addCriterion("DeviceId is null");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNotNull() {
            addCriterion("DeviceId is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceidEqualTo(String value) {
            addCriterion("DeviceId =", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotEqualTo(String value) {
            addCriterion("DeviceId <>", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThan(String value) {
            addCriterion("DeviceId >", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThanOrEqualTo(String value) {
            addCriterion("DeviceId >=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThan(String value) {
            addCriterion("DeviceId <", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThanOrEqualTo(String value) {
            addCriterion("DeviceId <=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLike(String value) {
            addCriterion("DeviceId like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotLike(String value) {
            addCriterion("DeviceId not like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIn(List<String> values) {
            addCriterion("DeviceId in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotIn(List<String> values) {
            addCriterion("DeviceId not in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidBetween(String value1, String value2) {
            addCriterion("DeviceId between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotBetween(String value1, String value2) {
            addCriterion("DeviceId not between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andUaIsNull() {
            addCriterion("Ua is null");
            return (Criteria) this;
        }

        public Criteria andUaIsNotNull() {
            addCriterion("Ua is not null");
            return (Criteria) this;
        }

        public Criteria andUaEqualTo(Double value) {
            addCriterion("Ua =", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotEqualTo(Double value) {
            addCriterion("Ua <>", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaGreaterThan(Double value) {
            addCriterion("Ua >", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaGreaterThanOrEqualTo(Double value) {
            addCriterion("Ua >=", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaLessThan(Double value) {
            addCriterion("Ua <", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaLessThanOrEqualTo(Double value) {
            addCriterion("Ua <=", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaIn(List<Double> values) {
            addCriterion("Ua in", values, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotIn(List<Double> values) {
            addCriterion("Ua not in", values, "ua");
            return (Criteria) this;
        }

        public Criteria andUaBetween(Double value1, Double value2) {
            addCriterion("Ua between", value1, value2, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotBetween(Double value1, Double value2) {
            addCriterion("Ua not between", value1, value2, "ua");
            return (Criteria) this;
        }

        public Criteria andUbIsNull() {
            addCriterion("Ub is null");
            return (Criteria) this;
        }

        public Criteria andUbIsNotNull() {
            addCriterion("Ub is not null");
            return (Criteria) this;
        }

        public Criteria andUbEqualTo(Double value) {
            addCriterion("Ub =", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbNotEqualTo(Double value) {
            addCriterion("Ub <>", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbGreaterThan(Double value) {
            addCriterion("Ub >", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbGreaterThanOrEqualTo(Double value) {
            addCriterion("Ub >=", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbLessThan(Double value) {
            addCriterion("Ub <", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbLessThanOrEqualTo(Double value) {
            addCriterion("Ub <=", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbIn(List<Double> values) {
            addCriterion("Ub in", values, "ub");
            return (Criteria) this;
        }

        public Criteria andUbNotIn(List<Double> values) {
            addCriterion("Ub not in", values, "ub");
            return (Criteria) this;
        }

        public Criteria andUbBetween(Double value1, Double value2) {
            addCriterion("Ub between", value1, value2, "ub");
            return (Criteria) this;
        }

        public Criteria andUbNotBetween(Double value1, Double value2) {
            addCriterion("Ub not between", value1, value2, "ub");
            return (Criteria) this;
        }

        public Criteria andUcIsNull() {
            addCriterion("Uc is null");
            return (Criteria) this;
        }

        public Criteria andUcIsNotNull() {
            addCriterion("Uc is not null");
            return (Criteria) this;
        }

        public Criteria andUcEqualTo(Double value) {
            addCriterion("Uc =", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotEqualTo(Double value) {
            addCriterion("Uc <>", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcGreaterThan(Double value) {
            addCriterion("Uc >", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcGreaterThanOrEqualTo(Double value) {
            addCriterion("Uc >=", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcLessThan(Double value) {
            addCriterion("Uc <", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcLessThanOrEqualTo(Double value) {
            addCriterion("Uc <=", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcIn(List<Double> values) {
            addCriterion("Uc in", values, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotIn(List<Double> values) {
            addCriterion("Uc not in", values, "uc");
            return (Criteria) this;
        }

        public Criteria andUcBetween(Double value1, Double value2) {
            addCriterion("Uc between", value1, value2, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotBetween(Double value1, Double value2) {
            addCriterion("Uc not between", value1, value2, "uc");
            return (Criteria) this;
        }

        public Criteria andIaIsNull() {
            addCriterion("Ia is null");
            return (Criteria) this;
        }

        public Criteria andIaIsNotNull() {
            addCriterion("Ia is not null");
            return (Criteria) this;
        }

        public Criteria andIaEqualTo(Double value) {
            addCriterion("Ia =", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaNotEqualTo(Double value) {
            addCriterion("Ia <>", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaGreaterThan(Double value) {
            addCriterion("Ia >", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaGreaterThanOrEqualTo(Double value) {
            addCriterion("Ia >=", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaLessThan(Double value) {
            addCriterion("Ia <", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaLessThanOrEqualTo(Double value) {
            addCriterion("Ia <=", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaIn(List<Double> values) {
            addCriterion("Ia in", values, "ia");
            return (Criteria) this;
        }

        public Criteria andIaNotIn(List<Double> values) {
            addCriterion("Ia not in", values, "ia");
            return (Criteria) this;
        }

        public Criteria andIaBetween(Double value1, Double value2) {
            addCriterion("Ia between", value1, value2, "ia");
            return (Criteria) this;
        }

        public Criteria andIaNotBetween(Double value1, Double value2) {
            addCriterion("Ia not between", value1, value2, "ia");
            return (Criteria) this;
        }

        public Criteria andIbIsNull() {
            addCriterion("Ib is null");
            return (Criteria) this;
        }

        public Criteria andIbIsNotNull() {
            addCriterion("Ib is not null");
            return (Criteria) this;
        }

        public Criteria andIbEqualTo(Double value) {
            addCriterion("Ib =", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbNotEqualTo(Double value) {
            addCriterion("Ib <>", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbGreaterThan(Double value) {
            addCriterion("Ib >", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbGreaterThanOrEqualTo(Double value) {
            addCriterion("Ib >=", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbLessThan(Double value) {
            addCriterion("Ib <", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbLessThanOrEqualTo(Double value) {
            addCriterion("Ib <=", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbIn(List<Double> values) {
            addCriterion("Ib in", values, "ib");
            return (Criteria) this;
        }

        public Criteria andIbNotIn(List<Double> values) {
            addCriterion("Ib not in", values, "ib");
            return (Criteria) this;
        }

        public Criteria andIbBetween(Double value1, Double value2) {
            addCriterion("Ib between", value1, value2, "ib");
            return (Criteria) this;
        }

        public Criteria andIbNotBetween(Double value1, Double value2) {
            addCriterion("Ib not between", value1, value2, "ib");
            return (Criteria) this;
        }

        public Criteria andIcIsNull() {
            addCriterion("Ic is null");
            return (Criteria) this;
        }

        public Criteria andIcIsNotNull() {
            addCriterion("Ic is not null");
            return (Criteria) this;
        }

        public Criteria andIcEqualTo(Double value) {
            addCriterion("Ic =", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcNotEqualTo(Double value) {
            addCriterion("Ic <>", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcGreaterThan(Double value) {
            addCriterion("Ic >", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcGreaterThanOrEqualTo(Double value) {
            addCriterion("Ic >=", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcLessThan(Double value) {
            addCriterion("Ic <", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcLessThanOrEqualTo(Double value) {
            addCriterion("Ic <=", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcIn(List<Double> values) {
            addCriterion("Ic in", values, "ic");
            return (Criteria) this;
        }

        public Criteria andIcNotIn(List<Double> values) {
            addCriterion("Ic not in", values, "ic");
            return (Criteria) this;
        }

        public Criteria andIcBetween(Double value1, Double value2) {
            addCriterion("Ic between", value1, value2, "ic");
            return (Criteria) this;
        }

        public Criteria andIcNotBetween(Double value1, Double value2) {
            addCriterion("Ic not between", value1, value2, "ic");
            return (Criteria) this;
        }

        public Criteria andIoIsNull() {
            addCriterion("Io is null");
            return (Criteria) this;
        }

        public Criteria andIoIsNotNull() {
            addCriterion("Io is not null");
            return (Criteria) this;
        }

        public Criteria andIoEqualTo(Double value) {
            addCriterion("Io =", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoNotEqualTo(Double value) {
            addCriterion("Io <>", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoGreaterThan(Double value) {
            addCriterion("Io >", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoGreaterThanOrEqualTo(Double value) {
            addCriterion("Io >=", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoLessThan(Double value) {
            addCriterion("Io <", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoLessThanOrEqualTo(Double value) {
            addCriterion("Io <=", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoIn(List<Double> values) {
            addCriterion("Io in", values, "io");
            return (Criteria) this;
        }

        public Criteria andIoNotIn(List<Double> values) {
            addCriterion("Io not in", values, "io");
            return (Criteria) this;
        }

        public Criteria andIoBetween(Double value1, Double value2) {
            addCriterion("Io between", value1, value2, "io");
            return (Criteria) this;
        }

        public Criteria andIoNotBetween(Double value1, Double value2) {
            addCriterion("Io not between", value1, value2, "io");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeIsNull() {
            addCriterion("AlarmType is null");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeIsNotNull() {
            addCriterion("AlarmType is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeEqualTo(Integer value) {
            addCriterion("AlarmType =", value, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeNotEqualTo(Integer value) {
            addCriterion("AlarmType <>", value, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeGreaterThan(Integer value) {
            addCriterion("AlarmType >", value, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("AlarmType >=", value, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeLessThan(Integer value) {
            addCriterion("AlarmType <", value, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeLessThanOrEqualTo(Integer value) {
            addCriterion("AlarmType <=", value, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeIn(List<Integer> values) {
            addCriterion("AlarmType in", values, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeNotIn(List<Integer> values) {
            addCriterion("AlarmType not in", values, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeBetween(Integer value1, Integer value2) {
            addCriterion("AlarmType between", value1, value2, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("AlarmType not between", value1, value2, "alarmtype");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseIsNull() {
            addCriterion("AlarmPhase is null");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseIsNotNull() {
            addCriterion("AlarmPhase is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseEqualTo(String value) {
            addCriterion("AlarmPhase =", value, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseNotEqualTo(String value) {
            addCriterion("AlarmPhase <>", value, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseGreaterThan(String value) {
            addCriterion("AlarmPhase >", value, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseGreaterThanOrEqualTo(String value) {
            addCriterion("AlarmPhase >=", value, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseLessThan(String value) {
            addCriterion("AlarmPhase <", value, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseLessThanOrEqualTo(String value) {
            addCriterion("AlarmPhase <=", value, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseLike(String value) {
            addCriterion("AlarmPhase like", value, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseNotLike(String value) {
            addCriterion("AlarmPhase not like", value, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseIn(List<String> values) {
            addCriterion("AlarmPhase in", values, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseNotIn(List<String> values) {
            addCriterion("AlarmPhase not in", values, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseBetween(String value1, String value2) {
            addCriterion("AlarmPhase between", value1, value2, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmphaseNotBetween(String value1, String value2) {
            addCriterion("AlarmPhase not between", value1, value2, "alarmphase");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelIsNull() {
            addCriterion("AlarmLevel is null");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelIsNotNull() {
            addCriterion("AlarmLevel is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelEqualTo(Integer value) {
            addCriterion("AlarmLevel =", value, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelNotEqualTo(Integer value) {
            addCriterion("AlarmLevel <>", value, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelGreaterThan(Integer value) {
            addCriterion("AlarmLevel >", value, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("AlarmLevel >=", value, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelLessThan(Integer value) {
            addCriterion("AlarmLevel <", value, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelLessThanOrEqualTo(Integer value) {
            addCriterion("AlarmLevel <=", value, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelIn(List<Integer> values) {
            addCriterion("AlarmLevel in", values, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelNotIn(List<Integer> values) {
            addCriterion("AlarmLevel not in", values, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelBetween(Integer value1, Integer value2) {
            addCriterion("AlarmLevel between", value1, value2, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andAlarmlevelNotBetween(Integer value1, Integer value2) {
            addCriterion("AlarmLevel not between", value1, value2, "alarmlevel");
            return (Criteria) this;
        }

        public Criteria andDurationtimeIsNull() {
            addCriterion("DurationTime is null");
            return (Criteria) this;
        }

        public Criteria andDurationtimeIsNotNull() {
            addCriterion("DurationTime is not null");
            return (Criteria) this;
        }

        public Criteria andDurationtimeEqualTo(Double value) {
            addCriterion("DurationTime =", value, "durationtime");
            return (Criteria) this;
        }

        public Criteria andDurationtimeNotEqualTo(Double value) {
            addCriterion("DurationTime <>", value, "durationtime");
            return (Criteria) this;
        }

        public Criteria andDurationtimeGreaterThan(Double value) {
            addCriterion("DurationTime >", value, "durationtime");
            return (Criteria) this;
        }

        public Criteria andDurationtimeGreaterThanOrEqualTo(Double value) {
            addCriterion("DurationTime >=", value, "durationtime");
            return (Criteria) this;
        }

        public Criteria andDurationtimeLessThan(Double value) {
            addCriterion("DurationTime <", value, "durationtime");
            return (Criteria) this;
        }

        public Criteria andDurationtimeLessThanOrEqualTo(Double value) {
            addCriterion("DurationTime <=", value, "durationtime");
            return (Criteria) this;
        }

        public Criteria andDurationtimeIn(List<Double> values) {
            addCriterion("DurationTime in", values, "durationtime");
            return (Criteria) this;
        }

        public Criteria andDurationtimeNotIn(List<Double> values) {
            addCriterion("DurationTime not in", values, "durationtime");
            return (Criteria) this;
        }

        public Criteria andDurationtimeBetween(Double value1, Double value2) {
            addCriterion("DurationTime between", value1, value2, "durationtime");
            return (Criteria) this;
        }

        public Criteria andDurationtimeNotBetween(Double value1, Double value2) {
            addCriterion("DurationTime not between", value1, value2, "durationtime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeIsNull() {
            addCriterion("OutageTime is null");
            return (Criteria) this;
        }

        public Criteria andOutagetimeIsNotNull() {
            addCriterion("OutageTime is not null");
            return (Criteria) this;
        }

        public Criteria andOutagetimeEqualTo(Date value) {
            addCriterion("OutageTime =", value, "outagetime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeNotEqualTo(Date value) {
            addCriterion("OutageTime <>", value, "outagetime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeGreaterThan(Date value) {
            addCriterion("OutageTime >", value, "outagetime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("OutageTime >=", value, "outagetime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeLessThan(Date value) {
            addCriterion("OutageTime <", value, "outagetime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeLessThanOrEqualTo(Date value) {
            addCriterion("OutageTime <=", value, "outagetime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeIn(List<Date> values) {
            addCriterion("OutageTime in", values, "outagetime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeNotIn(List<Date> values) {
            addCriterion("OutageTime not in", values, "outagetime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeBetween(Date value1, Date value2) {
            addCriterion("OutageTime between", value1, value2, "outagetime");
            return (Criteria) this;
        }

        public Criteria andOutagetimeNotBetween(Date value1, Date value2) {
            addCriterion("OutageTime not between", value1, value2, "outagetime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeIsNull() {
            addCriterion("PowerOnTime is null");
            return (Criteria) this;
        }

        public Criteria andPowerontimeIsNotNull() {
            addCriterion("PowerOnTime is not null");
            return (Criteria) this;
        }

        public Criteria andPowerontimeEqualTo(Date value) {
            addCriterion("PowerOnTime =", value, "powerontime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeNotEqualTo(Date value) {
            addCriterion("PowerOnTime <>", value, "powerontime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeGreaterThan(Date value) {
            addCriterion("PowerOnTime >", value, "powerontime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PowerOnTime >=", value, "powerontime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeLessThan(Date value) {
            addCriterion("PowerOnTime <", value, "powerontime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeLessThanOrEqualTo(Date value) {
            addCriterion("PowerOnTime <=", value, "powerontime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeIn(List<Date> values) {
            addCriterion("PowerOnTime in", values, "powerontime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeNotIn(List<Date> values) {
            addCriterion("PowerOnTime not in", values, "powerontime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeBetween(Date value1, Date value2) {
            addCriterion("PowerOnTime between", value1, value2, "powerontime");
            return (Criteria) this;
        }

        public Criteria andPowerontimeNotBetween(Date value1, Date value2) {
            addCriterion("PowerOnTime not between", value1, value2, "powerontime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeIsNull() {
            addCriterion("AlarmTime is null");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeIsNotNull() {
            addCriterion("AlarmTime is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeEqualTo(Date value) {
            addCriterion("AlarmTime =", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeNotEqualTo(Date value) {
            addCriterion("AlarmTime <>", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeGreaterThan(Date value) {
            addCriterion("AlarmTime >", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AlarmTime >=", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeLessThan(Date value) {
            addCriterion("AlarmTime <", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeLessThanOrEqualTo(Date value) {
            addCriterion("AlarmTime <=", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeIn(List<Date> values) {
            addCriterion("AlarmTime in", values, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeNotIn(List<Date> values) {
            addCriterion("AlarmTime not in", values, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeBetween(Date value1, Date value2) {
            addCriterion("AlarmTime between", value1, value2, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeNotBetween(Date value1, Date value2) {
            addCriterion("AlarmTime not between", value1, value2, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andWorkorderidIsNull() {
            addCriterion("WorkOrderId is null");
            return (Criteria) this;
        }

        public Criteria andWorkorderidIsNotNull() {
            addCriterion("WorkOrderId is not null");
            return (Criteria) this;
        }

        public Criteria andWorkorderidEqualTo(String value) {
            addCriterion("WorkOrderId =", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidNotEqualTo(String value) {
            addCriterion("WorkOrderId <>", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidGreaterThan(String value) {
            addCriterion("WorkOrderId >", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidGreaterThanOrEqualTo(String value) {
            addCriterion("WorkOrderId >=", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidLessThan(String value) {
            addCriterion("WorkOrderId <", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidLessThanOrEqualTo(String value) {
            addCriterion("WorkOrderId <=", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidLike(String value) {
            addCriterion("WorkOrderId like", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidNotLike(String value) {
            addCriterion("WorkOrderId not like", value, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidIn(List<String> values) {
            addCriterion("WorkOrderId in", values, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidNotIn(List<String> values) {
            addCriterion("WorkOrderId not in", values, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidBetween(String value1, String value2) {
            addCriterion("WorkOrderId between", value1, value2, "workorderid");
            return (Criteria) this;
        }

        public Criteria andWorkorderidNotBetween(String value1, String value2) {
            addCriterion("WorkOrderId not between", value1, value2, "workorderid");
            return (Criteria) this;
        }

        public Criteria andIsdealIsNull() {
            addCriterion("IsDeal is null");
            return (Criteria) this;
        }

        public Criteria andIsdealIsNotNull() {
            addCriterion("IsDeal is not null");
            return (Criteria) this;
        }

        public Criteria andIsdealEqualTo(Integer value) {
            addCriterion("IsDeal =", value, "isdeal");
            return (Criteria) this;
        }

        public Criteria andIsdealNotEqualTo(Integer value) {
            addCriterion("IsDeal <>", value, "isdeal");
            return (Criteria) this;
        }

        public Criteria andIsdealGreaterThan(Integer value) {
            addCriterion("IsDeal >", value, "isdeal");
            return (Criteria) this;
        }

        public Criteria andIsdealGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsDeal >=", value, "isdeal");
            return (Criteria) this;
        }

        public Criteria andIsdealLessThan(Integer value) {
            addCriterion("IsDeal <", value, "isdeal");
            return (Criteria) this;
        }

        public Criteria andIsdealLessThanOrEqualTo(Integer value) {
            addCriterion("IsDeal <=", value, "isdeal");
            return (Criteria) this;
        }

        public Criteria andIsdealIn(List<Integer> values) {
            addCriterion("IsDeal in", values, "isdeal");
            return (Criteria) this;
        }

        public Criteria andIsdealNotIn(List<Integer> values) {
            addCriterion("IsDeal not in", values, "isdeal");
            return (Criteria) this;
        }

        public Criteria andIsdealBetween(Integer value1, Integer value2) {
            addCriterion("IsDeal between", value1, value2, "isdeal");
            return (Criteria) this;
        }

        public Criteria andIsdealNotBetween(Integer value1, Integer value2) {
            addCriterion("IsDeal not between", value1, value2, "isdeal");
            return (Criteria) this;
        }

        public Criteria andDealtimeIsNull() {
            addCriterion("DealTime is null");
            return (Criteria) this;
        }

        public Criteria andDealtimeIsNotNull() {
            addCriterion("DealTime is not null");
            return (Criteria) this;
        }

        public Criteria andDealtimeEqualTo(Date value) {
            addCriterion("DealTime =", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeNotEqualTo(Date value) {
            addCriterion("DealTime <>", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeGreaterThan(Date value) {
            addCriterion("DealTime >", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DealTime >=", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeLessThan(Date value) {
            addCriterion("DealTime <", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeLessThanOrEqualTo(Date value) {
            addCriterion("DealTime <=", value, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeIn(List<Date> values) {
            addCriterion("DealTime in", values, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeNotIn(List<Date> values) {
            addCriterion("DealTime not in", values, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeBetween(Date value1, Date value2) {
            addCriterion("DealTime between", value1, value2, "dealtime");
            return (Criteria) this;
        }

        public Criteria andDealtimeNotBetween(Date value1, Date value2) {
            addCriterion("DealTime not between", value1, value2, "dealtime");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderIsNull() {
            addCriterion("IsSendWorkOrder is null");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderIsNotNull() {
            addCriterion("IsSendWorkOrder is not null");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderEqualTo(Integer value) {
            addCriterion("IsSendWorkOrder =", value, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderNotEqualTo(Integer value) {
            addCriterion("IsSendWorkOrder <>", value, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderGreaterThan(Integer value) {
            addCriterion("IsSendWorkOrder >", value, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsSendWorkOrder >=", value, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderLessThan(Integer value) {
            addCriterion("IsSendWorkOrder <", value, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderLessThanOrEqualTo(Integer value) {
            addCriterion("IsSendWorkOrder <=", value, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderIn(List<Integer> values) {
            addCriterion("IsSendWorkOrder in", values, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderNotIn(List<Integer> values) {
            addCriterion("IsSendWorkOrder not in", values, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderBetween(Integer value1, Integer value2) {
            addCriterion("IsSendWorkOrder between", value1, value2, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andIssendworkorderNotBetween(Integer value1, Integer value2) {
            addCriterion("IsSendWorkOrder not between", value1, value2, "issendworkorder");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNull() {
            addCriterion("SendTime is null");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNotNull() {
            addCriterion("SendTime is not null");
            return (Criteria) this;
        }

        public Criteria andSendtimeEqualTo(Date value) {
            addCriterion("SendTime =", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotEqualTo(Date value) {
            addCriterion("SendTime <>", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThan(Date value) {
            addCriterion("SendTime >", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SendTime >=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThan(Date value) {
            addCriterion("SendTime <", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThanOrEqualTo(Date value) {
            addCriterion("SendTime <=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIn(List<Date> values) {
            addCriterion("SendTime in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotIn(List<Date> values) {
            addCriterion("SendTime not in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeBetween(Date value1, Date value2) {
            addCriterion("SendTime between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotBetween(Date value1, Date value2) {
            addCriterion("SendTime not between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSortcodeIsNull() {
            addCriterion("SortCode is null");
            return (Criteria) this;
        }

        public Criteria andSortcodeIsNotNull() {
            addCriterion("SortCode is not null");
            return (Criteria) this;
        }

        public Criteria andSortcodeEqualTo(Integer value) {
            addCriterion("SortCode =", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotEqualTo(Integer value) {
            addCriterion("SortCode <>", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThan(Integer value) {
            addCriterion("SortCode >", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("SortCode >=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThan(Integer value) {
            addCriterion("SortCode <", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThanOrEqualTo(Integer value) {
            addCriterion("SortCode <=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeIn(List<Integer> values) {
            addCriterion("SortCode in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotIn(List<Integer> values) {
            addCriterion("SortCode not in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeBetween(Integer value1, Integer value2) {
            addCriterion("SortCode between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("SortCode not between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andDeletemarkIsNull() {
            addCriterion("DeleteMark is null");
            return (Criteria) this;
        }

        public Criteria andDeletemarkIsNotNull() {
            addCriterion("DeleteMark is not null");
            return (Criteria) this;
        }

        public Criteria andDeletemarkEqualTo(Integer value) {
            addCriterion("DeleteMark =", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkNotEqualTo(Integer value) {
            addCriterion("DeleteMark <>", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkGreaterThan(Integer value) {
            addCriterion("DeleteMark >", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("DeleteMark >=", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkLessThan(Integer value) {
            addCriterion("DeleteMark <", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkLessThanOrEqualTo(Integer value) {
            addCriterion("DeleteMark <=", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkIn(List<Integer> values) {
            addCriterion("DeleteMark in", values, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkNotIn(List<Integer> values) {
            addCriterion("DeleteMark not in", values, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkBetween(Integer value1, Integer value2) {
            addCriterion("DeleteMark between", value1, value2, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkNotBetween(Integer value1, Integer value2) {
            addCriterion("DeleteMark not between", value1, value2, "deletemark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkIsNull() {
            addCriterion("EnabledMark is null");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkIsNotNull() {
            addCriterion("EnabledMark is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkEqualTo(Integer value) {
            addCriterion("EnabledMark =", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkNotEqualTo(Integer value) {
            addCriterion("EnabledMark <>", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkGreaterThan(Integer value) {
            addCriterion("EnabledMark >", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("EnabledMark >=", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkLessThan(Integer value) {
            addCriterion("EnabledMark <", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkLessThanOrEqualTo(Integer value) {
            addCriterion("EnabledMark <=", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkIn(List<Integer> values) {
            addCriterion("EnabledMark in", values, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkNotIn(List<Integer> values) {
            addCriterion("EnabledMark not in", values, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkBetween(Integer value1, Integer value2) {
            addCriterion("EnabledMark between", value1, value2, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkNotBetween(Integer value1, Integer value2) {
            addCriterion("EnabledMark not between", value1, value2, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("Description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("Description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("Description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("Description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("Description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("Description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("Description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("Description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("Description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("Description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("Description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("Description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("Description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("Description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("CreateDate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("CreateDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("CreateDate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("CreateDate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("CreateDate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateDate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("CreateDate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("CreateDate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("CreateDate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("CreateDate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("CreateDate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("CreateDate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreateuseridIsNull() {
            addCriterion("CreateUserId is null");
            return (Criteria) this;
        }

        public Criteria andCreateuseridIsNotNull() {
            addCriterion("CreateUserId is not null");
            return (Criteria) this;
        }

        public Criteria andCreateuseridEqualTo(String value) {
            addCriterion("CreateUserId =", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotEqualTo(String value) {
            addCriterion("CreateUserId <>", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridGreaterThan(String value) {
            addCriterion("CreateUserId >", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridGreaterThanOrEqualTo(String value) {
            addCriterion("CreateUserId >=", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridLessThan(String value) {
            addCriterion("CreateUserId <", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridLessThanOrEqualTo(String value) {
            addCriterion("CreateUserId <=", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridLike(String value) {
            addCriterion("CreateUserId like", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotLike(String value) {
            addCriterion("CreateUserId not like", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridIn(List<String> values) {
            addCriterion("CreateUserId in", values, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotIn(List<String> values) {
            addCriterion("CreateUserId not in", values, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridBetween(String value1, String value2) {
            addCriterion("CreateUserId between", value1, value2, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotBetween(String value1, String value2) {
            addCriterion("CreateUserId not between", value1, value2, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateusernameIsNull() {
            addCriterion("CreateUserName is null");
            return (Criteria) this;
        }

        public Criteria andCreateusernameIsNotNull() {
            addCriterion("CreateUserName is not null");
            return (Criteria) this;
        }

        public Criteria andCreateusernameEqualTo(String value) {
            addCriterion("CreateUserName =", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameNotEqualTo(String value) {
            addCriterion("CreateUserName <>", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameGreaterThan(String value) {
            addCriterion("CreateUserName >", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameGreaterThanOrEqualTo(String value) {
            addCriterion("CreateUserName >=", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameLessThan(String value) {
            addCriterion("CreateUserName <", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameLessThanOrEqualTo(String value) {
            addCriterion("CreateUserName <=", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameLike(String value) {
            addCriterion("CreateUserName like", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameNotLike(String value) {
            addCriterion("CreateUserName not like", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameIn(List<String> values) {
            addCriterion("CreateUserName in", values, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameNotIn(List<String> values) {
            addCriterion("CreateUserName not in", values, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameBetween(String value1, String value2) {
            addCriterion("CreateUserName between", value1, value2, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameNotBetween(String value1, String value2) {
            addCriterion("CreateUserName not between", value1, value2, "createusername");
            return (Criteria) this;
        }

        public Criteria andModifydateIsNull() {
            addCriterion("ModifyDate is null");
            return (Criteria) this;
        }

        public Criteria andModifydateIsNotNull() {
            addCriterion("ModifyDate is not null");
            return (Criteria) this;
        }

        public Criteria andModifydateEqualTo(Date value) {
            addCriterion("ModifyDate =", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotEqualTo(Date value) {
            addCriterion("ModifyDate <>", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateGreaterThan(Date value) {
            addCriterion("ModifyDate >", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateGreaterThanOrEqualTo(Date value) {
            addCriterion("ModifyDate >=", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateLessThan(Date value) {
            addCriterion("ModifyDate <", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateLessThanOrEqualTo(Date value) {
            addCriterion("ModifyDate <=", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateIn(List<Date> values) {
            addCriterion("ModifyDate in", values, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotIn(List<Date> values) {
            addCriterion("ModifyDate not in", values, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateBetween(Date value1, Date value2) {
            addCriterion("ModifyDate between", value1, value2, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotBetween(Date value1, Date value2) {
            addCriterion("ModifyDate not between", value1, value2, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifyuseridIsNull() {
            addCriterion("ModifyUserId is null");
            return (Criteria) this;
        }

        public Criteria andModifyuseridIsNotNull() {
            addCriterion("ModifyUserId is not null");
            return (Criteria) this;
        }

        public Criteria andModifyuseridEqualTo(String value) {
            addCriterion("ModifyUserId =", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridNotEqualTo(String value) {
            addCriterion("ModifyUserId <>", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridGreaterThan(String value) {
            addCriterion("ModifyUserId >", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridGreaterThanOrEqualTo(String value) {
            addCriterion("ModifyUserId >=", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridLessThan(String value) {
            addCriterion("ModifyUserId <", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridLessThanOrEqualTo(String value) {
            addCriterion("ModifyUserId <=", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridLike(String value) {
            addCriterion("ModifyUserId like", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridNotLike(String value) {
            addCriterion("ModifyUserId not like", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridIn(List<String> values) {
            addCriterion("ModifyUserId in", values, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridNotIn(List<String> values) {
            addCriterion("ModifyUserId not in", values, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridBetween(String value1, String value2) {
            addCriterion("ModifyUserId between", value1, value2, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridNotBetween(String value1, String value2) {
            addCriterion("ModifyUserId not between", value1, value2, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyusernameIsNull() {
            addCriterion("ModifyUserName is null");
            return (Criteria) this;
        }

        public Criteria andModifyusernameIsNotNull() {
            addCriterion("ModifyUserName is not null");
            return (Criteria) this;
        }

        public Criteria andModifyusernameEqualTo(String value) {
            addCriterion("ModifyUserName =", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameNotEqualTo(String value) {
            addCriterion("ModifyUserName <>", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameGreaterThan(String value) {
            addCriterion("ModifyUserName >", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameGreaterThanOrEqualTo(String value) {
            addCriterion("ModifyUserName >=", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameLessThan(String value) {
            addCriterion("ModifyUserName <", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameLessThanOrEqualTo(String value) {
            addCriterion("ModifyUserName <=", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameLike(String value) {
            addCriterion("ModifyUserName like", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameNotLike(String value) {
            addCriterion("ModifyUserName not like", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameIn(List<String> values) {
            addCriterion("ModifyUserName in", values, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameNotIn(List<String> values) {
            addCriterion("ModifyUserName not in", values, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameBetween(String value1, String value2) {
            addCriterion("ModifyUserName between", value1, value2, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameNotBetween(String value1, String value2) {
            addCriterion("ModifyUserName not between", value1, value2, "modifyusername");
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