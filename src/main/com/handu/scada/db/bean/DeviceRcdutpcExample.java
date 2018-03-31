package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceRcdutpcExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceRcdutpcExample() {
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

        public Criteria andUtpcidIsNull() {
            addCriterion("utpcId is null");
            return (Criteria) this;
        }

        public Criteria andUtpcidIsNotNull() {
            addCriterion("utpcId is not null");
            return (Criteria) this;
        }

        public Criteria andUtpcidEqualTo(String value) {
            addCriterion("utpcId =", value, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidNotEqualTo(String value) {
            addCriterion("utpcId <>", value, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidGreaterThan(String value) {
            addCriterion("utpcId >", value, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidGreaterThanOrEqualTo(String value) {
            addCriterion("utpcId >=", value, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidLessThan(String value) {
            addCriterion("utpcId <", value, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidLessThanOrEqualTo(String value) {
            addCriterion("utpcId <=", value, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidLike(String value) {
            addCriterion("utpcId like", value, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidNotLike(String value) {
            addCriterion("utpcId not like", value, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidIn(List<String> values) {
            addCriterion("utpcId in", values, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidNotIn(List<String> values) {
            addCriterion("utpcId not in", values, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidBetween(String value1, String value2) {
            addCriterion("utpcId between", value1, value2, "utpcid");
            return (Criteria) this;
        }

        public Criteria andUtpcidNotBetween(String value1, String value2) {
            addCriterion("utpcId not between", value1, value2, "utpcid");
            return (Criteria) this;
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

        public Criteria andMaxutpcIsNull() {
            addCriterion("maxutpc is null");
            return (Criteria) this;
        }

        public Criteria andMaxutpcIsNotNull() {
            addCriterion("maxutpc is not null");
            return (Criteria) this;
        }

        public Criteria andMaxutpcEqualTo(Double value) {
            addCriterion("maxutpc =", value, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andMaxutpcNotEqualTo(Double value) {
            addCriterion("maxutpc <>", value, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andMaxutpcGreaterThan(Double value) {
            addCriterion("maxutpc >", value, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andMaxutpcGreaterThanOrEqualTo(Double value) {
            addCriterion("maxutpc >=", value, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andMaxutpcLessThan(Double value) {
            addCriterion("maxutpc <", value, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andMaxutpcLessThanOrEqualTo(Double value) {
            addCriterion("maxutpc <=", value, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andMaxutpcIn(List<Double> values) {
            addCriterion("maxutpc in", values, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andMaxutpcNotIn(List<Double> values) {
            addCriterion("maxutpc not in", values, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andMaxutpcBetween(Double value1, Double value2) {
            addCriterion("maxutpc between", value1, value2, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andMaxutpcNotBetween(Double value1, Double value2) {
            addCriterion("maxutpc not between", value1, value2, "maxutpc");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNull() {
            addCriterion("beginTime is null");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNotNull() {
            addCriterion("beginTime is not null");
            return (Criteria) this;
        }

        public Criteria andBegintimeEqualTo(Date value) {
            addCriterion("beginTime =", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotEqualTo(Date value) {
            addCriterion("beginTime <>", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThan(Date value) {
            addCriterion("beginTime >", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("beginTime >=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThan(Date value) {
            addCriterion("beginTime <", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThanOrEqualTo(Date value) {
            addCriterion("beginTime <=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeIn(List<Date> values) {
            addCriterion("beginTime in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotIn(List<Date> values) {
            addCriterion("beginTime not in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeBetween(Date value1, Date value2) {
            addCriterion("beginTime between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotBetween(Date value1, Date value2) {
            addCriterion("beginTime not between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Integer value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Integer value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Integer value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Integer value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Integer value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Integer> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Integer> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Integer value1, Integer value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andPhaseIsNull() {
            addCriterion("phase is null");
            return (Criteria) this;
        }

        public Criteria andPhaseIsNotNull() {
            addCriterion("phase is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseEqualTo(String value) {
            addCriterion("phase =", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotEqualTo(String value) {
            addCriterion("phase <>", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseGreaterThan(String value) {
            addCriterion("phase >", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseGreaterThanOrEqualTo(String value) {
            addCriterion("phase >=", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseLessThan(String value) {
            addCriterion("phase <", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseLessThanOrEqualTo(String value) {
            addCriterion("phase <=", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseLike(String value) {
            addCriterion("phase like", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotLike(String value) {
            addCriterion("phase not like", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseIn(List<String> values) {
            addCriterion("phase in", values, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotIn(List<String> values) {
            addCriterion("phase not in", values, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseBetween(String value1, String value2) {
            addCriterion("phase between", value1, value2, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotBetween(String value1, String value2) {
            addCriterion("phase not between", value1, value2, "phase");
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