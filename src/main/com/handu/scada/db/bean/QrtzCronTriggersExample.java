package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.List;

public class QrtzCronTriggersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QrtzCronTriggersExample() {
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

        public Criteria andSCHED_NAMEIsNull() {
            addCriterion("SCHED_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMEIsNotNull() {
            addCriterion("SCHED_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMEEqualTo(String value) {
            addCriterion("SCHED_NAME =", value, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMENotEqualTo(String value) {
            addCriterion("SCHED_NAME <>", value, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMEGreaterThan(String value) {
            addCriterion("SCHED_NAME >", value, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMEGreaterThanOrEqualTo(String value) {
            addCriterion("SCHED_NAME >=", value, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMELessThan(String value) {
            addCriterion("SCHED_NAME <", value, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMELessThanOrEqualTo(String value) {
            addCriterion("SCHED_NAME <=", value, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMELike(String value) {
            addCriterion("SCHED_NAME like", value, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMENotLike(String value) {
            addCriterion("SCHED_NAME not like", value, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMEIn(List<String> values) {
            addCriterion("SCHED_NAME in", values, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMENotIn(List<String> values) {
            addCriterion("SCHED_NAME not in", values, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMEBetween(String value1, String value2) {
            addCriterion("SCHED_NAME between", value1, value2, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andSCHED_NAMENotBetween(String value1, String value2) {
            addCriterion("SCHED_NAME not between", value1, value2, "SCHED_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMEIsNull() {
            addCriterion("TRIGGER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMEIsNotNull() {
            addCriterion("TRIGGER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMEEqualTo(String value) {
            addCriterion("TRIGGER_NAME =", value, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMENotEqualTo(String value) {
            addCriterion("TRIGGER_NAME <>", value, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMEGreaterThan(String value) {
            addCriterion("TRIGGER_NAME >", value, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMEGreaterThanOrEqualTo(String value) {
            addCriterion("TRIGGER_NAME >=", value, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMELessThan(String value) {
            addCriterion("TRIGGER_NAME <", value, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMELessThanOrEqualTo(String value) {
            addCriterion("TRIGGER_NAME <=", value, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMELike(String value) {
            addCriterion("TRIGGER_NAME like", value, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMENotLike(String value) {
            addCriterion("TRIGGER_NAME not like", value, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMEIn(List<String> values) {
            addCriterion("TRIGGER_NAME in", values, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMENotIn(List<String> values) {
            addCriterion("TRIGGER_NAME not in", values, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMEBetween(String value1, String value2) {
            addCriterion("TRIGGER_NAME between", value1, value2, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_NAMENotBetween(String value1, String value2) {
            addCriterion("TRIGGER_NAME not between", value1, value2, "TRIGGER_NAME");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPIsNull() {
            addCriterion("TRIGGER_GROUP is null");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPIsNotNull() {
            addCriterion("TRIGGER_GROUP is not null");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPEqualTo(String value) {
            addCriterion("TRIGGER_GROUP =", value, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPNotEqualTo(String value) {
            addCriterion("TRIGGER_GROUP <>", value, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPGreaterThan(String value) {
            addCriterion("TRIGGER_GROUP >", value, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPGreaterThanOrEqualTo(String value) {
            addCriterion("TRIGGER_GROUP >=", value, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPLessThan(String value) {
            addCriterion("TRIGGER_GROUP <", value, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPLessThanOrEqualTo(String value) {
            addCriterion("TRIGGER_GROUP <=", value, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPLike(String value) {
            addCriterion("TRIGGER_GROUP like", value, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPNotLike(String value) {
            addCriterion("TRIGGER_GROUP not like", value, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPIn(List<String> values) {
            addCriterion("TRIGGER_GROUP in", values, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPNotIn(List<String> values) {
            addCriterion("TRIGGER_GROUP not in", values, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPBetween(String value1, String value2) {
            addCriterion("TRIGGER_GROUP between", value1, value2, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andTRIGGER_GROUPNotBetween(String value1, String value2) {
            addCriterion("TRIGGER_GROUP not between", value1, value2, "TRIGGER_GROUP");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONIsNull() {
            addCriterion("CRON_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONIsNotNull() {
            addCriterion("CRON_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONEqualTo(String value) {
            addCriterion("CRON_EXPRESSION =", value, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONNotEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <>", value, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONGreaterThan(String value) {
            addCriterion("CRON_EXPRESSION >", value, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONGreaterThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION >=", value, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONLessThan(String value) {
            addCriterion("CRON_EXPRESSION <", value, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONLessThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <=", value, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONLike(String value) {
            addCriterion("CRON_EXPRESSION like", value, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONNotLike(String value) {
            addCriterion("CRON_EXPRESSION not like", value, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONIn(List<String> values) {
            addCriterion("CRON_EXPRESSION in", values, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONNotIn(List<String> values) {
            addCriterion("CRON_EXPRESSION not in", values, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION between", value1, value2, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andCRON_EXPRESSIONNotBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION not between", value1, value2, "CRON_EXPRESSION");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDIsNull() {
            addCriterion("TIME_ZONE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDIsNotNull() {
            addCriterion("TIME_ZONE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDEqualTo(String value) {
            addCriterion("TIME_ZONE_ID =", value, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDNotEqualTo(String value) {
            addCriterion("TIME_ZONE_ID <>", value, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDGreaterThan(String value) {
            addCriterion("TIME_ZONE_ID >", value, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDGreaterThanOrEqualTo(String value) {
            addCriterion("TIME_ZONE_ID >=", value, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDLessThan(String value) {
            addCriterion("TIME_ZONE_ID <", value, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDLessThanOrEqualTo(String value) {
            addCriterion("TIME_ZONE_ID <=", value, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDLike(String value) {
            addCriterion("TIME_ZONE_ID like", value, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDNotLike(String value) {
            addCriterion("TIME_ZONE_ID not like", value, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDIn(List<String> values) {
            addCriterion("TIME_ZONE_ID in", values, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDNotIn(List<String> values) {
            addCriterion("TIME_ZONE_ID not in", values, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDBetween(String value1, String value2) {
            addCriterion("TIME_ZONE_ID between", value1, value2, "TIME_ZONE_ID");
            return (Criteria) this;
        }

        public Criteria andTIME_ZONE_IDNotBetween(String value1, String value2) {
            addCriterion("TIME_ZONE_ID not between", value1, value2, "TIME_ZONE_ID");
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