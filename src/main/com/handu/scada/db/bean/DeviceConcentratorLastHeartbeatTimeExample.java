package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceConcentratorLastHeartbeatTimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceConcentratorLastHeartbeatTimeExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDtuidIsNull() {
            addCriterion("dtuId is null");
            return (Criteria) this;
        }

        public Criteria andDtuidIsNotNull() {
            addCriterion("dtuId is not null");
            return (Criteria) this;
        }

        public Criteria andDtuidEqualTo(String value) {
            addCriterion("dtuId =", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidNotEqualTo(String value) {
            addCriterion("dtuId <>", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidGreaterThan(String value) {
            addCriterion("dtuId >", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidGreaterThanOrEqualTo(String value) {
            addCriterion("dtuId >=", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidLessThan(String value) {
            addCriterion("dtuId <", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidLessThanOrEqualTo(String value) {
            addCriterion("dtuId <=", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidLike(String value) {
            addCriterion("dtuId like", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidNotLike(String value) {
            addCriterion("dtuId not like", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidIn(List<String> values) {
            addCriterion("dtuId in", values, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidNotIn(List<String> values) {
            addCriterion("dtuId not in", values, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidBetween(String value1, String value2) {
            addCriterion("dtuId between", value1, value2, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidNotBetween(String value1, String value2) {
            addCriterion("dtuId not between", value1, value2, "dtuid");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeIsNull() {
            addCriterion("lastHeartbeatTime is null");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeIsNotNull() {
            addCriterion("lastHeartbeatTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeEqualTo(Date value) {
            addCriterion("lastHeartbeatTime =", value, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeNotEqualTo(Date value) {
            addCriterion("lastHeartbeatTime <>", value, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeGreaterThan(Date value) {
            addCriterion("lastHeartbeatTime >", value, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastHeartbeatTime >=", value, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeLessThan(Date value) {
            addCriterion("lastHeartbeatTime <", value, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeLessThanOrEqualTo(Date value) {
            addCriterion("lastHeartbeatTime <=", value, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeIn(List<Date> values) {
            addCriterion("lastHeartbeatTime in", values, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeNotIn(List<Date> values) {
            addCriterion("lastHeartbeatTime not in", values, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeBetween(Date value1, Date value2) {
            addCriterion("lastHeartbeatTime between", value1, value2, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andLastheartbeattimeNotBetween(Date value1, Date value2) {
            addCriterion("lastHeartbeatTime not between", value1, value2, "lastheartbeattime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeIsNull() {
            addCriterion("recordTime is null");
            return (Criteria) this;
        }

        public Criteria andRecordtimeIsNotNull() {
            addCriterion("recordTime is not null");
            return (Criteria) this;
        }

        public Criteria andRecordtimeEqualTo(Date value) {
            addCriterion("recordTime =", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeNotEqualTo(Date value) {
            addCriterion("recordTime <>", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeGreaterThan(Date value) {
            addCriterion("recordTime >", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recordTime >=", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeLessThan(Date value) {
            addCriterion("recordTime <", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeLessThanOrEqualTo(Date value) {
            addCriterion("recordTime <=", value, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeIn(List<Date> values) {
            addCriterion("recordTime in", values, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeNotIn(List<Date> values) {
            addCriterion("recordTime not in", values, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeBetween(Date value1, Date value2) {
            addCriterion("recordTime between", value1, value2, "recordtime");
            return (Criteria) this;
        }

        public Criteria andRecordtimeNotBetween(Date value1, Date value2) {
            addCriterion("recordTime not between", value1, value2, "recordtime");
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