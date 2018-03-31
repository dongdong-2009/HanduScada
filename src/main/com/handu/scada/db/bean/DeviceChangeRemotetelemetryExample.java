package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceChangeRemotetelemetryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceChangeRemotetelemetryExample() {
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

        public Criteria andRemotetelemetryidIsNull() {
            addCriterion("RemoteTelemetryId is null");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidIsNotNull() {
            addCriterion("RemoteTelemetryId is not null");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidEqualTo(String value) {
            addCriterion("RemoteTelemetryId =", value, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidNotEqualTo(String value) {
            addCriterion("RemoteTelemetryId <>", value, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidGreaterThan(String value) {
            addCriterion("RemoteTelemetryId >", value, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidGreaterThanOrEqualTo(String value) {
            addCriterion("RemoteTelemetryId >=", value, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidLessThan(String value) {
            addCriterion("RemoteTelemetryId <", value, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidLessThanOrEqualTo(String value) {
            addCriterion("RemoteTelemetryId <=", value, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidLike(String value) {
            addCriterion("RemoteTelemetryId like", value, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidNotLike(String value) {
            addCriterion("RemoteTelemetryId not like", value, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidIn(List<String> values) {
            addCriterion("RemoteTelemetryId in", values, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidNotIn(List<String> values) {
            addCriterion("RemoteTelemetryId not in", values, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidBetween(String value1, String value2) {
            addCriterion("RemoteTelemetryId between", value1, value2, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemotetelemetryidNotBetween(String value1, String value2) {
            addCriterion("RemoteTelemetryId not between", value1, value2, "remotetelemetryid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidIsNull() {
            addCriterion("RemoteIndexsId is null");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidIsNotNull() {
            addCriterion("RemoteIndexsId is not null");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidEqualTo(String value) {
            addCriterion("RemoteIndexsId =", value, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidNotEqualTo(String value) {
            addCriterion("RemoteIndexsId <>", value, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidGreaterThan(String value) {
            addCriterion("RemoteIndexsId >", value, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidGreaterThanOrEqualTo(String value) {
            addCriterion("RemoteIndexsId >=", value, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidLessThan(String value) {
            addCriterion("RemoteIndexsId <", value, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidLessThanOrEqualTo(String value) {
            addCriterion("RemoteIndexsId <=", value, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidLike(String value) {
            addCriterion("RemoteIndexsId like", value, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidNotLike(String value) {
            addCriterion("RemoteIndexsId not like", value, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidIn(List<String> values) {
            addCriterion("RemoteIndexsId in", values, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidNotIn(List<String> values) {
            addCriterion("RemoteIndexsId not in", values, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidBetween(String value1, String value2) {
            addCriterion("RemoteIndexsId between", value1, value2, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andRemoteindexsidNotBetween(String value1, String value2) {
            addCriterion("RemoteIndexsId not between", value1, value2, "remoteindexsid");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("Value is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("Value is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(String value) {
            addCriterion("Value =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(String value) {
            addCriterion("Value <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(String value) {
            addCriterion("Value >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(String value) {
            addCriterion("Value >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(String value) {
            addCriterion("Value <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(String value) {
            addCriterion("Value <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLike(String value) {
            addCriterion("Value like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotLike(String value) {
            addCriterion("Value not like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<String> values) {
            addCriterion("Value in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<String> values) {
            addCriterion("Value not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(String value1, String value2) {
            addCriterion("Value between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(String value1, String value2) {
            addCriterion("Value not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andRecordtimeIsNull() {
            addCriterion("RecordTime is null");
            return (Criteria) this;
        }

        public Criteria andRecordtimeIsNotNull() {
            addCriterion("RecordTime is not null");
            return (Criteria) this;
        }

        public Criteria andRecordtimeEqualTo(Date value) {
            addCriterion("RecordTime =", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeNotEqualTo(Date value) {
            addCriterion("RecordTime <>", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeGreaterThan(Date value) {
            addCriterion("RecordTime >", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("RecordTime >=", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeLessThan(Date value) {
            addCriterion("RecordTime <", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeLessThanOrEqualTo(Date value) {
            addCriterion("RecordTime <=", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeIn(List<Date> values) {
            addCriterion("RecordTime in", values, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeNotIn(List<Date> values) {
            addCriterion("RecordTime not in", values, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeBetween(Date value1, Date value2) {
            addCriterion("RecordTime between", value1, value2, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeNotBetween(Date value1, Date value2) {
            addCriterion("RecordTime not between", value1, value2, "recordtime");
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