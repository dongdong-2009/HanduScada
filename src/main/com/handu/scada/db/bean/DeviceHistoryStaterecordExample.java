package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceHistoryStaterecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceHistoryStaterecordExample() {
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

        public Criteria andRecordidIsNull() {
            addCriterion("RecordId is null");
            return (Criteria) this;
        }

        public Criteria andRecordidIsNotNull() {
            addCriterion("RecordId is not null");
            return (Criteria) this;
        }

        public Criteria andRecordidEqualTo(String value) {
            addCriterion("RecordId =", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotEqualTo(String value) {
            addCriterion("RecordId <>", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidGreaterThan(String value) {
            addCriterion("RecordId >", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidGreaterThanOrEqualTo(String value) {
            addCriterion("RecordId >=", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidLessThan(String value) {
            addCriterion("RecordId <", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidLessThanOrEqualTo(String value) {
            addCriterion("RecordId <=", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidLike(String value) {
            addCriterion("RecordId like", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotLike(String value) {
            addCriterion("RecordId not like", value, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidIn(List<String> values) {
            addCriterion("RecordId in", values, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotIn(List<String> values) {
            addCriterion("RecordId not in", values, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidBetween(String value1, String value2) {
            addCriterion("RecordId between", value1, value2, "recordid");
            return (Criteria) this;
        }

        public Criteria andRecordidNotBetween(String value1, String value2) {
            addCriterion("RecordId not between", value1, value2, "recordid");
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

        public Criteria andOnlinetimeIsNull() {
            addCriterion("OnlineTime is null");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeIsNotNull() {
            addCriterion("OnlineTime is not null");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeEqualTo(Date value) {
            addCriterion("OnlineTime =", value, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeNotEqualTo(Date value) {
            addCriterion("OnlineTime <>", value, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeGreaterThan(Date value) {
            addCriterion("OnlineTime >", value, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("OnlineTime >=", value, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeLessThan(Date value) {
            addCriterion("OnlineTime <", value, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeLessThanOrEqualTo(Date value) {
            addCriterion("OnlineTime <=", value, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeIn(List<Date> values) {
            addCriterion("OnlineTime in", values, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeNotIn(List<Date> values) {
            addCriterion("OnlineTime not in", values, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeBetween(Date value1, Date value2) {
            addCriterion("OnlineTime between", value1, value2, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andOnlinetimeNotBetween(Date value1, Date value2) {
            addCriterion("OnlineTime not between", value1, value2, "onlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeIsNull() {
            addCriterion("UnLineTime is null");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeIsNotNull() {
            addCriterion("UnLineTime is not null");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeEqualTo(Date value) {
            addCriterion("UnLineTime =", value, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeNotEqualTo(Date value) {
            addCriterion("UnLineTime <>", value, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeGreaterThan(Date value) {
            addCriterion("UnLineTime >", value, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UnLineTime >=", value, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeLessThan(Date value) {
            addCriterion("UnLineTime <", value, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeLessThanOrEqualTo(Date value) {
            addCriterion("UnLineTime <=", value, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeIn(List<Date> values) {
            addCriterion("UnLineTime in", values, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeNotIn(List<Date> values) {
            addCriterion("UnLineTime not in", values, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeBetween(Date value1, Date value2) {
            addCriterion("UnLineTime between", value1, value2, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andUnlinetimeNotBetween(Date value1, Date value2) {
            addCriterion("UnLineTime not between", value1, value2, "unlinetime");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("Duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("Duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(String value) {
            addCriterion("Duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(String value) {
            addCriterion("Duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(String value) {
            addCriterion("Duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(String value) {
            addCriterion("Duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(String value) {
            addCriterion("Duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(String value) {
            addCriterion("Duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLike(String value) {
            addCriterion("Duration like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotLike(String value) {
            addCriterion("Duration not like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<String> values) {
            addCriterion("Duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<String> values) {
            addCriterion("Duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(String value1, String value2) {
            addCriterion("Duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(String value1, String value2) {
            addCriterion("Duration not between", value1, value2, "duration");
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