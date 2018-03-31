package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseAlarmcacheExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseAlarmcacheExample() {
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

        public Criteria andOidIsNull() {
            addCriterion("Oid is null");
            return (Criteria) this;
        }

        public Criteria andOidIsNotNull() {
            addCriterion("Oid is not null");
            return (Criteria) this;
        }

        public Criteria andOidEqualTo(String value) {
            addCriterion("Oid =", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotEqualTo(String value) {
            addCriterion("Oid <>", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThan(String value) {
            addCriterion("Oid >", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThanOrEqualTo(String value) {
            addCriterion("Oid >=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThan(String value) {
            addCriterion("Oid <", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThanOrEqualTo(String value) {
            addCriterion("Oid <=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLike(String value) {
            addCriterion("Oid like", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotLike(String value) {
            addCriterion("Oid not like", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidIn(List<String> values) {
            addCriterion("Oid in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotIn(List<String> values) {
            addCriterion("Oid not in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidBetween(String value1, String value2) {
            addCriterion("Oid between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotBetween(String value1, String value2) {
            addCriterion("Oid not between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andHappentimeIsNull() {
            addCriterion("HappenTime is null");
            return (Criteria) this;
        }

        public Criteria andHappentimeIsNotNull() {
            addCriterion("HappenTime is not null");
            return (Criteria) this;
        }

        public Criteria andHappentimeEqualTo(Date value) {
            addCriterion("HappenTime =", value, "happentime");
            return (Criteria) this;
        }

        public Criteria andHappentimeNotEqualTo(Date value) {
            addCriterion("HappenTime <>", value, "happentime");
            return (Criteria) this;
        }

        public Criteria andHappentimeGreaterThan(Date value) {
            addCriterion("HappenTime >", value, "happentime");
            return (Criteria) this;
        }

        public Criteria andHappentimeGreaterThanOrEqualTo(Date value) {
            addCriterion("HappenTime >=", value, "happentime");
            return (Criteria) this;
        }

        public Criteria andHappentimeLessThan(Date value) {
            addCriterion("HappenTime <", value, "happentime");
            return (Criteria) this;
        }

        public Criteria andHappentimeLessThanOrEqualTo(Date value) {
            addCriterion("HappenTime <=", value, "happentime");
            return (Criteria) this;
        }

        public Criteria andHappentimeIn(List<Date> values) {
            addCriterion("HappenTime in", values, "happentime");
            return (Criteria) this;
        }

        public Criteria andHappentimeNotIn(List<Date> values) {
            addCriterion("HappenTime not in", values, "happentime");
            return (Criteria) this;
        }

        public Criteria andHappentimeBetween(Date value1, Date value2) {
            addCriterion("HappenTime between", value1, value2, "happentime");
            return (Criteria) this;
        }

        public Criteria andHappentimeNotBetween(Date value1, Date value2) {
            addCriterion("HappenTime not between", value1, value2, "happentime");
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

        public Criteria andDeviceaddressIsNull() {
            addCriterion("DeviceAddress is null");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressIsNotNull() {
            addCriterion("DeviceAddress is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressEqualTo(String value) {
            addCriterion("DeviceAddress =", value, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressNotEqualTo(String value) {
            addCriterion("DeviceAddress <>", value, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressGreaterThan(String value) {
            addCriterion("DeviceAddress >", value, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressGreaterThanOrEqualTo(String value) {
            addCriterion("DeviceAddress >=", value, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressLessThan(String value) {
            addCriterion("DeviceAddress <", value, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressLessThanOrEqualTo(String value) {
            addCriterion("DeviceAddress <=", value, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressLike(String value) {
            addCriterion("DeviceAddress like", value, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressNotLike(String value) {
            addCriterion("DeviceAddress not like", value, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressIn(List<String> values) {
            addCriterion("DeviceAddress in", values, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressNotIn(List<String> values) {
            addCriterion("DeviceAddress not in", values, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressBetween(String value1, String value2) {
            addCriterion("DeviceAddress between", value1, value2, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andDeviceaddressNotBetween(String value1, String value2) {
            addCriterion("DeviceAddress not between", value1, value2, "deviceaddress");
            return (Criteria) this;
        }

        public Criteria andTripreasonIsNull() {
            addCriterion("TripReason is null");
            return (Criteria) this;
        }

        public Criteria andTripreasonIsNotNull() {
            addCriterion("TripReason is not null");
            return (Criteria) this;
        }

        public Criteria andTripreasonEqualTo(String value) {
            addCriterion("TripReason =", value, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonNotEqualTo(String value) {
            addCriterion("TripReason <>", value, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonGreaterThan(String value) {
            addCriterion("TripReason >", value, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonGreaterThanOrEqualTo(String value) {
            addCriterion("TripReason >=", value, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonLessThan(String value) {
            addCriterion("TripReason <", value, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonLessThanOrEqualTo(String value) {
            addCriterion("TripReason <=", value, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonLike(String value) {
            addCriterion("TripReason like", value, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonNotLike(String value) {
            addCriterion("TripReason not like", value, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonIn(List<String> values) {
            addCriterion("TripReason in", values, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonNotIn(List<String> values) {
            addCriterion("TripReason not in", values, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonBetween(String value1, String value2) {
            addCriterion("TripReason between", value1, value2, "tripreason");
            return (Criteria) this;
        }

        public Criteria andTripreasonNotBetween(String value1, String value2) {
            addCriterion("TripReason not between", value1, value2, "tripreason");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("State is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("State is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("State =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("State <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("State >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("State >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("State <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("State <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("State in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("State not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("State between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("State not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("Flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("Flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("Flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("Flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("Flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("Flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("Flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("Flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("Flag like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("Flag not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("Flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("Flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("Flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("Flag not between", value1, value2, "flag");
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