package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceRcdTrialswitchlogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceRcdTrialswitchlogExample() {
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

        public Criteria andRcdidIsNull() {
            addCriterion("RcdId is null");
            return (Criteria) this;
        }

        public Criteria andRcdidIsNotNull() {
            addCriterion("RcdId is not null");
            return (Criteria) this;
        }

        public Criteria andRcdidEqualTo(String value) {
            addCriterion("RcdId =", value, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidNotEqualTo(String value) {
            addCriterion("RcdId <>", value, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidGreaterThan(String value) {
            addCriterion("RcdId >", value, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidGreaterThanOrEqualTo(String value) {
            addCriterion("RcdId >=", value, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidLessThan(String value) {
            addCriterion("RcdId <", value, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidLessThanOrEqualTo(String value) {
            addCriterion("RcdId <=", value, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidLike(String value) {
            addCriterion("RcdId like", value, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidNotLike(String value) {
            addCriterion("RcdId not like", value, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidIn(List<String> values) {
            addCriterion("RcdId in", values, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidNotIn(List<String> values) {
            addCriterion("RcdId not in", values, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidBetween(String value1, String value2) {
            addCriterion("RcdId between", value1, value2, "rcdid");
            return (Criteria) this;
        }

        public Criteria andRcdidNotBetween(String value1, String value2) {
            addCriterion("RcdId not between", value1, value2, "rcdid");
            return (Criteria) this;
        }

        public Criteria andTrialtimeIsNull() {
            addCriterion("TrialTime is null");
            return (Criteria) this;
        }

        public Criteria andTrialtimeIsNotNull() {
            addCriterion("TrialTime is not null");
            return (Criteria) this;
        }

        public Criteria andTrialtimeEqualTo(Date value) {
            addCriterion("TrialTime =", value, "trialtime");
            return (Criteria) this;
        }

        public Criteria andTrialtimeNotEqualTo(Date value) {
            addCriterion("TrialTime <>", value, "trialtime");
            return (Criteria) this;
        }

        public Criteria andTrialtimeGreaterThan(Date value) {
            addCriterion("TrialTime >", value, "trialtime");
            return (Criteria) this;
        }

        public Criteria andTrialtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TrialTime >=", value, "trialtime");
            return (Criteria) this;
        }

        public Criteria andTrialtimeLessThan(Date value) {
            addCriterion("TrialTime <", value, "trialtime");
            return (Criteria) this;
        }

        public Criteria andTrialtimeLessThanOrEqualTo(Date value) {
            addCriterion("TrialTime <=", value, "trialtime");
            return (Criteria) this;
        }

        public Criteria andTrialtimeIn(List<Date> values) {
            addCriterion("TrialTime in", values, "trialtime");
            return (Criteria) this;
        }

        public Criteria andTrialtimeNotIn(List<Date> values) {
            addCriterion("TrialTime not in", values, "trialtime");
            return (Criteria) this;
        }

        public Criteria andTrialtimeBetween(Date value1, Date value2) {
            addCriterion("TrialTime between", value1, value2, "trialtime");
            return (Criteria) this;
        }

        public Criteria andTrialtimeNotBetween(Date value1, Date value2) {
            addCriterion("TrialTime not between", value1, value2, "trialtime");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("Result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("Result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(Integer value) {
            addCriterion("Result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(Integer value) {
            addCriterion("Result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(Integer value) {
            addCriterion("Result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("Result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(Integer value) {
            addCriterion("Result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(Integer value) {
            addCriterion("Result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<Integer> values) {
            addCriterion("Result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<Integer> values) {
            addCriterion("Result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(Integer value1, Integer value2) {
            addCriterion("Result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(Integer value1, Integer value2) {
            addCriterion("Result not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("Operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("Operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("Operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("Operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("Operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("Operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("Operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("Operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("Operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("Operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("Operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("Operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("Operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("Operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andResulttextIsNull() {
            addCriterion("ResultText is null");
            return (Criteria) this;
        }

        public Criteria andResulttextIsNotNull() {
            addCriterion("ResultText is not null");
            return (Criteria) this;
        }

        public Criteria andResulttextEqualTo(String value) {
            addCriterion("ResultText =", value, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextNotEqualTo(String value) {
            addCriterion("ResultText <>", value, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextGreaterThan(String value) {
            addCriterion("ResultText >", value, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextGreaterThanOrEqualTo(String value) {
            addCriterion("ResultText >=", value, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextLessThan(String value) {
            addCriterion("ResultText <", value, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextLessThanOrEqualTo(String value) {
            addCriterion("ResultText <=", value, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextLike(String value) {
            addCriterion("ResultText like", value, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextNotLike(String value) {
            addCriterion("ResultText not like", value, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextIn(List<String> values) {
            addCriterion("ResultText in", values, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextNotIn(List<String> values) {
            addCriterion("ResultText not in", values, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextBetween(String value1, String value2) {
            addCriterion("ResultText between", value1, value2, "resulttext");
            return (Criteria) this;
        }

        public Criteria andResulttextNotBetween(String value1, String value2) {
            addCriterion("ResultText not between", value1, value2, "resulttext");
            return (Criteria) this;
        }

        public Criteria andOntimeIsNull() {
            addCriterion("OnTime is null");
            return (Criteria) this;
        }

        public Criteria andOntimeIsNotNull() {
            addCriterion("OnTime is not null");
            return (Criteria) this;
        }

        public Criteria andOntimeEqualTo(Date value) {
            addCriterion("OnTime =", value, "ontime");
            return (Criteria) this;
        }

        public Criteria andOntimeNotEqualTo(Date value) {
            addCriterion("OnTime <>", value, "ontime");
            return (Criteria) this;
        }

        public Criteria andOntimeGreaterThan(Date value) {
            addCriterion("OnTime >", value, "ontime");
            return (Criteria) this;
        }

        public Criteria andOntimeGreaterThanOrEqualTo(Date value) {
            addCriterion("OnTime >=", value, "ontime");
            return (Criteria) this;
        }

        public Criteria andOntimeLessThan(Date value) {
            addCriterion("OnTime <", value, "ontime");
            return (Criteria) this;
        }

        public Criteria andOntimeLessThanOrEqualTo(Date value) {
            addCriterion("OnTime <=", value, "ontime");
            return (Criteria) this;
        }

        public Criteria andOntimeIn(List<Date> values) {
            addCriterion("OnTime in", values, "ontime");
            return (Criteria) this;
        }

        public Criteria andOntimeNotIn(List<Date> values) {
            addCriterion("OnTime not in", values, "ontime");
            return (Criteria) this;
        }

        public Criteria andOntimeBetween(Date value1, Date value2) {
            addCriterion("OnTime between", value1, value2, "ontime");
            return (Criteria) this;
        }

        public Criteria andOntimeNotBetween(Date value1, Date value2) {
            addCriterion("OnTime not between", value1, value2, "ontime");
            return (Criteria) this;
        }

        public Criteria andDowntimeIsNull() {
            addCriterion("DownTime is null");
            return (Criteria) this;
        }

        public Criteria andDowntimeIsNotNull() {
            addCriterion("DownTime is not null");
            return (Criteria) this;
        }

        public Criteria andDowntimeEqualTo(Date value) {
            addCriterion("DownTime =", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeNotEqualTo(Date value) {
            addCriterion("DownTime <>", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeGreaterThan(Date value) {
            addCriterion("DownTime >", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DownTime >=", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeLessThan(Date value) {
            addCriterion("DownTime <", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeLessThanOrEqualTo(Date value) {
            addCriterion("DownTime <=", value, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeIn(List<Date> values) {
            addCriterion("DownTime in", values, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeNotIn(List<Date> values) {
            addCriterion("DownTime not in", values, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeBetween(Date value1, Date value2) {
            addCriterion("DownTime between", value1, value2, "downtime");
            return (Criteria) this;
        }

        public Criteria andDowntimeNotBetween(Date value1, Date value2) {
            addCriterion("DownTime not between", value1, value2, "downtime");
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