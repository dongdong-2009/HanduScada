package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceHmRealAfn0c25Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceHmRealAfn0c25Example() {
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

        public Criteria andDtuaddressIsNull() {
            addCriterion("DtuAddress is null");
            return (Criteria) this;
        }

        public Criteria andDtuaddressIsNotNull() {
            addCriterion("DtuAddress is not null");
            return (Criteria) this;
        }

        public Criteria andDtuaddressEqualTo(String value) {
            addCriterion("DtuAddress =", value, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressNotEqualTo(String value) {
            addCriterion("DtuAddress <>", value, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressGreaterThan(String value) {
            addCriterion("DtuAddress >", value, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressGreaterThanOrEqualTo(String value) {
            addCriterion("DtuAddress >=", value, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressLessThan(String value) {
            addCriterion("DtuAddress <", value, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressLessThanOrEqualTo(String value) {
            addCriterion("DtuAddress <=", value, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressLike(String value) {
            addCriterion("DtuAddress like", value, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressNotLike(String value) {
            addCriterion("DtuAddress not like", value, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressIn(List<String> values) {
            addCriterion("DtuAddress in", values, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressNotIn(List<String> values) {
            addCriterion("DtuAddress not in", values, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressBetween(String value1, String value2) {
            addCriterion("DtuAddress between", value1, value2, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andDtuaddressNotBetween(String value1, String value2) {
            addCriterion("DtuAddress not between", value1, value2, "dtuaddress");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeIsNull() {
            addCriterion("ReadMeterTime is null");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeIsNotNull() {
            addCriterion("ReadMeterTime is not null");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeEqualTo(Date value) {
            addCriterion("ReadMeterTime =", value, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeNotEqualTo(Date value) {
            addCriterion("ReadMeterTime <>", value, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeGreaterThan(Date value) {
            addCriterion("ReadMeterTime >", value, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ReadMeterTime >=", value, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeLessThan(Date value) {
            addCriterion("ReadMeterTime <", value, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeLessThanOrEqualTo(Date value) {
            addCriterion("ReadMeterTime <=", value, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeIn(List<Date> values) {
            addCriterion("ReadMeterTime in", values, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeNotIn(List<Date> values) {
            addCriterion("ReadMeterTime not in", values, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeBetween(Date value1, Date value2) {
            addCriterion("ReadMeterTime between", value1, value2, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andReadmetertimeNotBetween(Date value1, Date value2) {
            addCriterion("ReadMeterTime not between", value1, value2, "readmetertime");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerIsNull() {
            addCriterion("NowDayTotalActivePower is null");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerIsNotNull() {
            addCriterion("NowDayTotalActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerEqualTo(Double value) {
            addCriterion("NowDayTotalActivePower =", value, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerNotEqualTo(Double value) {
            addCriterion("NowDayTotalActivePower <>", value, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerGreaterThan(Double value) {
            addCriterion("NowDayTotalActivePower >", value, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayTotalActivePower >=", value, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerLessThan(Double value) {
            addCriterion("NowDayTotalActivePower <", value, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayTotalActivePower <=", value, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerIn(List<Double> values) {
            addCriterion("NowDayTotalActivePower in", values, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerNotIn(List<Double> values) {
            addCriterion("NowDayTotalActivePower not in", values, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerBetween(Double value1, Double value2) {
            addCriterion("NowDayTotalActivePower between", value1, value2, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalactivepowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayTotalActivePower not between", value1, value2, "nowdaytotalactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerIsNull() {
            addCriterion("NowDayAPhaseActivePower is null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerIsNotNull() {
            addCriterion("NowDayAPhaseActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerEqualTo(Double value) {
            addCriterion("NowDayAPhaseActivePower =", value, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerNotEqualTo(Double value) {
            addCriterion("NowDayAPhaseActivePower <>", value, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerGreaterThan(Double value) {
            addCriterion("NowDayAPhaseActivePower >", value, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseActivePower >=", value, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerLessThan(Double value) {
            addCriterion("NowDayAPhaseActivePower <", value, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseActivePower <=", value, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerIn(List<Double> values) {
            addCriterion("NowDayAPhaseActivePower in", values, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerNotIn(List<Double> values) {
            addCriterion("NowDayAPhaseActivePower not in", values, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseActivePower between", value1, value2, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseactivepowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseActivePower not between", value1, value2, "nowdayaphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerIsNull() {
            addCriterion("NowDayBPhaseActivePower is null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerIsNotNull() {
            addCriterion("NowDayBPhaseActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerEqualTo(Double value) {
            addCriterion("NowDayBPhaseActivePower =", value, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerNotEqualTo(Double value) {
            addCriterion("NowDayBPhaseActivePower <>", value, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerGreaterThan(Double value) {
            addCriterion("NowDayBPhaseActivePower >", value, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseActivePower >=", value, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerLessThan(Double value) {
            addCriterion("NowDayBPhaseActivePower <", value, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseActivePower <=", value, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerIn(List<Double> values) {
            addCriterion("NowDayBPhaseActivePower in", values, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerNotIn(List<Double> values) {
            addCriterion("NowDayBPhaseActivePower not in", values, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseActivePower between", value1, value2, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseactivepowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseActivePower not between", value1, value2, "nowdaybphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerIsNull() {
            addCriterion("NowDayCPhaseActivePower is null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerIsNotNull() {
            addCriterion("NowDayCPhaseActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerEqualTo(Double value) {
            addCriterion("NowDayCPhaseActivePower =", value, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerNotEqualTo(Double value) {
            addCriterion("NowDayCPhaseActivePower <>", value, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerGreaterThan(Double value) {
            addCriterion("NowDayCPhaseActivePower >", value, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseActivePower >=", value, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerLessThan(Double value) {
            addCriterion("NowDayCPhaseActivePower <", value, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseActivePower <=", value, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerIn(List<Double> values) {
            addCriterion("NowDayCPhaseActivePower in", values, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerNotIn(List<Double> values) {
            addCriterion("NowDayCPhaseActivePower not in", values, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseActivePower between", value1, value2, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseactivepowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseActivePower not between", value1, value2, "nowdaycphaseactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerIsNull() {
            addCriterion("NowDayTotalReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerIsNotNull() {
            addCriterion("NowDayTotalReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerEqualTo(Double value) {
            addCriterion("NowDayTotalReactivePower =", value, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerNotEqualTo(Double value) {
            addCriterion("NowDayTotalReactivePower <>", value, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerGreaterThan(Double value) {
            addCriterion("NowDayTotalReactivePower >", value, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayTotalReactivePower >=", value, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerLessThan(Double value) {
            addCriterion("NowDayTotalReactivePower <", value, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayTotalReactivePower <=", value, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerIn(List<Double> values) {
            addCriterion("NowDayTotalReactivePower in", values, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerNotIn(List<Double> values) {
            addCriterion("NowDayTotalReactivePower not in", values, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerBetween(Double value1, Double value2) {
            addCriterion("NowDayTotalReactivePower between", value1, value2, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalreactivepowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayTotalReactivePower not between", value1, value2, "nowdaytotalreactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerIsNull() {
            addCriterion("NowDayAPhaseReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerIsNotNull() {
            addCriterion("NowDayAPhaseReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerEqualTo(Double value) {
            addCriterion("NowDayAPhaseReactivePower =", value, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerNotEqualTo(Double value) {
            addCriterion("NowDayAPhaseReactivePower <>", value, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerGreaterThan(Double value) {
            addCriterion("NowDayAPhaseReactivePower >", value, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseReactivePower >=", value, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerLessThan(Double value) {
            addCriterion("NowDayAPhaseReactivePower <", value, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseReactivePower <=", value, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerIn(List<Double> values) {
            addCriterion("NowDayAPhaseReactivePower in", values, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerNotIn(List<Double> values) {
            addCriterion("NowDayAPhaseReactivePower not in", values, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseReactivePower between", value1, value2, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasereactivepowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseReactivePower not between", value1, value2, "nowdayaphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerIsNull() {
            addCriterion("NowDayBPhaseReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerIsNotNull() {
            addCriterion("NowDayBPhaseReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerEqualTo(Double value) {
            addCriterion("NowDayBPhaseReactivePower =", value, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerNotEqualTo(Double value) {
            addCriterion("NowDayBPhaseReactivePower <>", value, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerGreaterThan(Double value) {
            addCriterion("NowDayBPhaseReactivePower >", value, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseReactivePower >=", value, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerLessThan(Double value) {
            addCriterion("NowDayBPhaseReactivePower <", value, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseReactivePower <=", value, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerIn(List<Double> values) {
            addCriterion("NowDayBPhaseReactivePower in", values, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerNotIn(List<Double> values) {
            addCriterion("NowDayBPhaseReactivePower not in", values, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseReactivePower between", value1, value2, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasereactivepowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseReactivePower not between", value1, value2, "nowdaybphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerIsNull() {
            addCriterion("NowDayCPhaseReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerIsNotNull() {
            addCriterion("NowDayCPhaseReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerEqualTo(Double value) {
            addCriterion("NowDayCPhaseReactivePower =", value, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerNotEqualTo(Double value) {
            addCriterion("NowDayCPhaseReactivePower <>", value, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerGreaterThan(Double value) {
            addCriterion("NowDayCPhaseReactivePower >", value, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseReactivePower >=", value, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerLessThan(Double value) {
            addCriterion("NowDayCPhaseReactivePower <", value, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseReactivePower <=", value, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerIn(List<Double> values) {
            addCriterion("NowDayCPhaseReactivePower in", values, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerNotIn(List<Double> values) {
            addCriterion("NowDayCPhaseReactivePower not in", values, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseReactivePower between", value1, value2, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasereactivepowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseReactivePower not between", value1, value2, "nowdaycphasereactivepower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorIsNull() {
            addCriterion("NowDayTotalPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorIsNotNull() {
            addCriterion("NowDayTotalPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorEqualTo(Double value) {
            addCriterion("NowDayTotalPowerFactor =", value, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorNotEqualTo(Double value) {
            addCriterion("NowDayTotalPowerFactor <>", value, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorGreaterThan(Double value) {
            addCriterion("NowDayTotalPowerFactor >", value, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayTotalPowerFactor >=", value, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorLessThan(Double value) {
            addCriterion("NowDayTotalPowerFactor <", value, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("NowDayTotalPowerFactor <=", value, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorIn(List<Double> values) {
            addCriterion("NowDayTotalPowerFactor in", values, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorNotIn(List<Double> values) {
            addCriterion("NowDayTotalPowerFactor not in", values, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorBetween(Double value1, Double value2) {
            addCriterion("NowDayTotalPowerFactor between", value1, value2, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("NowDayTotalPowerFactor not between", value1, value2, "nowdaytotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorIsNull() {
            addCriterion("NowDayAPhasePowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorIsNotNull() {
            addCriterion("NowDayAPhasePowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorEqualTo(Double value) {
            addCriterion("NowDayAPhasePowerFactor =", value, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorNotEqualTo(Double value) {
            addCriterion("NowDayAPhasePowerFactor <>", value, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorGreaterThan(Double value) {
            addCriterion("NowDayAPhasePowerFactor >", value, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhasePowerFactor >=", value, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorLessThan(Double value) {
            addCriterion("NowDayAPhasePowerFactor <", value, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhasePowerFactor <=", value, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorIn(List<Double> values) {
            addCriterion("NowDayAPhasePowerFactor in", values, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorNotIn(List<Double> values) {
            addCriterion("NowDayAPhasePowerFactor not in", values, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhasePowerFactor between", value1, value2, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasepowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhasePowerFactor not between", value1, value2, "nowdayaphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorIsNull() {
            addCriterion("NowDayBPhasePowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorIsNotNull() {
            addCriterion("NowDayBPhasePowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorEqualTo(Double value) {
            addCriterion("NowDayBPhasePowerFactor =", value, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorNotEqualTo(Double value) {
            addCriterion("NowDayBPhasePowerFactor <>", value, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorGreaterThan(Double value) {
            addCriterion("NowDayBPhasePowerFactor >", value, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhasePowerFactor >=", value, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorLessThan(Double value) {
            addCriterion("NowDayBPhasePowerFactor <", value, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhasePowerFactor <=", value, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorIn(List<Double> values) {
            addCriterion("NowDayBPhasePowerFactor in", values, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorNotIn(List<Double> values) {
            addCriterion("NowDayBPhasePowerFactor not in", values, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhasePowerFactor between", value1, value2, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasepowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhasePowerFactor not between", value1, value2, "nowdaybphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorIsNull() {
            addCriterion("NowDayCPhasePowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorIsNotNull() {
            addCriterion("NowDayCPhasePowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorEqualTo(Double value) {
            addCriterion("NowDayCPhasePowerFactor =", value, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorNotEqualTo(Double value) {
            addCriterion("NowDayCPhasePowerFactor <>", value, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorGreaterThan(Double value) {
            addCriterion("NowDayCPhasePowerFactor >", value, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhasePowerFactor >=", value, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorLessThan(Double value) {
            addCriterion("NowDayCPhasePowerFactor <", value, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhasePowerFactor <=", value, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorIn(List<Double> values) {
            addCriterion("NowDayCPhasePowerFactor in", values, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorNotIn(List<Double> values) {
            addCriterion("NowDayCPhasePowerFactor not in", values, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhasePowerFactor between", value1, value2, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasepowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhasePowerFactor not between", value1, value2, "nowdaycphasepowerfactor");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageIsNull() {
            addCriterion("NowDayAPhaseVoltage is null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageIsNotNull() {
            addCriterion("NowDayAPhaseVoltage is not null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageEqualTo(Double value) {
            addCriterion("NowDayAPhaseVoltage =", value, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageNotEqualTo(Double value) {
            addCriterion("NowDayAPhaseVoltage <>", value, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageGreaterThan(Double value) {
            addCriterion("NowDayAPhaseVoltage >", value, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseVoltage >=", value, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageLessThan(Double value) {
            addCriterion("NowDayAPhaseVoltage <", value, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageLessThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseVoltage <=", value, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageIn(List<Double> values) {
            addCriterion("NowDayAPhaseVoltage in", values, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageNotIn(List<Double> values) {
            addCriterion("NowDayAPhaseVoltage not in", values, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseVoltage between", value1, value2, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasevoltageNotBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseVoltage not between", value1, value2, "nowdayaphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageIsNull() {
            addCriterion("NowDayBPhaseVoltage is null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageIsNotNull() {
            addCriterion("NowDayBPhaseVoltage is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageEqualTo(Double value) {
            addCriterion("NowDayBPhaseVoltage =", value, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageNotEqualTo(Double value) {
            addCriterion("NowDayBPhaseVoltage <>", value, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageGreaterThan(Double value) {
            addCriterion("NowDayBPhaseVoltage >", value, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseVoltage >=", value, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageLessThan(Double value) {
            addCriterion("NowDayBPhaseVoltage <", value, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageLessThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseVoltage <=", value, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageIn(List<Double> values) {
            addCriterion("NowDayBPhaseVoltage in", values, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageNotIn(List<Double> values) {
            addCriterion("NowDayBPhaseVoltage not in", values, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseVoltage between", value1, value2, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasevoltageNotBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseVoltage not between", value1, value2, "nowdaybphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageIsNull() {
            addCriterion("NowDayCPhaseVoltage is null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageIsNotNull() {
            addCriterion("NowDayCPhaseVoltage is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageEqualTo(Double value) {
            addCriterion("NowDayCPhaseVoltage =", value, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageNotEqualTo(Double value) {
            addCriterion("NowDayCPhaseVoltage <>", value, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageGreaterThan(Double value) {
            addCriterion("NowDayCPhaseVoltage >", value, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseVoltage >=", value, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageLessThan(Double value) {
            addCriterion("NowDayCPhaseVoltage <", value, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageLessThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseVoltage <=", value, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageIn(List<Double> values) {
            addCriterion("NowDayCPhaseVoltage in", values, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageNotIn(List<Double> values) {
            addCriterion("NowDayCPhaseVoltage not in", values, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseVoltage between", value1, value2, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasevoltageNotBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseVoltage not between", value1, value2, "nowdaycphasevoltage");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentIsNull() {
            addCriterion("NowDayAPhaseCurrent is null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentIsNotNull() {
            addCriterion("NowDayAPhaseCurrent is not null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentEqualTo(Double value) {
            addCriterion("NowDayAPhaseCurrent =", value, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentNotEqualTo(Double value) {
            addCriterion("NowDayAPhaseCurrent <>", value, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentGreaterThan(Double value) {
            addCriterion("NowDayAPhaseCurrent >", value, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseCurrent >=", value, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentLessThan(Double value) {
            addCriterion("NowDayAPhaseCurrent <", value, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentLessThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseCurrent <=", value, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentIn(List<Double> values) {
            addCriterion("NowDayAPhaseCurrent in", values, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentNotIn(List<Double> values) {
            addCriterion("NowDayAPhaseCurrent not in", values, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseCurrent between", value1, value2, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayaphasecurrentNotBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseCurrent not between", value1, value2, "nowdayaphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentIsNull() {
            addCriterion("NowDayBPhaseCurrent is null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentIsNotNull() {
            addCriterion("NowDayBPhaseCurrent is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentEqualTo(Double value) {
            addCriterion("NowDayBPhaseCurrent =", value, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentNotEqualTo(Double value) {
            addCriterion("NowDayBPhaseCurrent <>", value, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentGreaterThan(Double value) {
            addCriterion("NowDayBPhaseCurrent >", value, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseCurrent >=", value, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentLessThan(Double value) {
            addCriterion("NowDayBPhaseCurrent <", value, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentLessThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseCurrent <=", value, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentIn(List<Double> values) {
            addCriterion("NowDayBPhaseCurrent in", values, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentNotIn(List<Double> values) {
            addCriterion("NowDayBPhaseCurrent not in", values, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseCurrent between", value1, value2, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaybphasecurrentNotBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseCurrent not between", value1, value2, "nowdaybphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentIsNull() {
            addCriterion("NowDayCPhaseCurrent is null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentIsNotNull() {
            addCriterion("NowDayCPhaseCurrent is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentEqualTo(Double value) {
            addCriterion("NowDayCPhaseCurrent =", value, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentNotEqualTo(Double value) {
            addCriterion("NowDayCPhaseCurrent <>", value, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentGreaterThan(Double value) {
            addCriterion("NowDayCPhaseCurrent >", value, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseCurrent >=", value, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentLessThan(Double value) {
            addCriterion("NowDayCPhaseCurrent <", value, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentLessThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseCurrent <=", value, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentIn(List<Double> values) {
            addCriterion("NowDayCPhaseCurrent in", values, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentNotIn(List<Double> values) {
            addCriterion("NowDayCPhaseCurrent not in", values, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseCurrent between", value1, value2, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaycphasecurrentNotBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseCurrent not between", value1, value2, "nowdaycphasecurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentIsNull() {
            addCriterion("NowDayResidualCurrent is null");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentIsNotNull() {
            addCriterion("NowDayResidualCurrent is not null");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentEqualTo(Double value) {
            addCriterion("NowDayResidualCurrent =", value, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentNotEqualTo(Double value) {
            addCriterion("NowDayResidualCurrent <>", value, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentGreaterThan(Double value) {
            addCriterion("NowDayResidualCurrent >", value, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayResidualCurrent >=", value, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentLessThan(Double value) {
            addCriterion("NowDayResidualCurrent <", value, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentLessThanOrEqualTo(Double value) {
            addCriterion("NowDayResidualCurrent <=", value, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentIn(List<Double> values) {
            addCriterion("NowDayResidualCurrent in", values, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentNotIn(List<Double> values) {
            addCriterion("NowDayResidualCurrent not in", values, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentBetween(Double value1, Double value2) {
            addCriterion("NowDayResidualCurrent between", value1, value2, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdayresidualcurrentNotBetween(Double value1, Double value2) {
            addCriterion("NowDayResidualCurrent not between", value1, value2, "nowdayresidualcurrent");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerIsNull() {
            addCriterion("NowDayTotalApparentPower is null");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerIsNotNull() {
            addCriterion("NowDayTotalApparentPower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerEqualTo(Double value) {
            addCriterion("NowDayTotalApparentPower =", value, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerNotEqualTo(Double value) {
            addCriterion("NowDayTotalApparentPower <>", value, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerGreaterThan(Double value) {
            addCriterion("NowDayTotalApparentPower >", value, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayTotalApparentPower >=", value, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerLessThan(Double value) {
            addCriterion("NowDayTotalApparentPower <", value, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayTotalApparentPower <=", value, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerIn(List<Double> values) {
            addCriterion("NowDayTotalApparentPower in", values, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerNotIn(List<Double> values) {
            addCriterion("NowDayTotalApparentPower not in", values, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerBetween(Double value1, Double value2) {
            addCriterion("NowDayTotalApparentPower between", value1, value2, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaytotalapparentpowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayTotalApparentPower not between", value1, value2, "nowdaytotalapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerIsNull() {
            addCriterion("NowDayAPhaseApparentPower is null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerIsNotNull() {
            addCriterion("NowDayAPhaseApparentPower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerEqualTo(Double value) {
            addCriterion("NowDayAPhaseApparentPower =", value, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerNotEqualTo(Double value) {
            addCriterion("NowDayAPhaseApparentPower <>", value, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerGreaterThan(Double value) {
            addCriterion("NowDayAPhaseApparentPower >", value, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseApparentPower >=", value, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerLessThan(Double value) {
            addCriterion("NowDayAPhaseApparentPower <", value, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayAPhaseApparentPower <=", value, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerIn(List<Double> values) {
            addCriterion("NowDayAPhaseApparentPower in", values, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerNotIn(List<Double> values) {
            addCriterion("NowDayAPhaseApparentPower not in", values, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseApparentPower between", value1, value2, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdayaphaseapparentpowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayAPhaseApparentPower not between", value1, value2, "nowdayaphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerIsNull() {
            addCriterion("NowDayBPhaseApparentPower is null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerIsNotNull() {
            addCriterion("NowDayBPhaseApparentPower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerEqualTo(Double value) {
            addCriterion("NowDayBPhaseApparentPower =", value, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerNotEqualTo(Double value) {
            addCriterion("NowDayBPhaseApparentPower <>", value, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerGreaterThan(Double value) {
            addCriterion("NowDayBPhaseApparentPower >", value, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseApparentPower >=", value, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerLessThan(Double value) {
            addCriterion("NowDayBPhaseApparentPower <", value, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayBPhaseApparentPower <=", value, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerIn(List<Double> values) {
            addCriterion("NowDayBPhaseApparentPower in", values, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerNotIn(List<Double> values) {
            addCriterion("NowDayBPhaseApparentPower not in", values, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseApparentPower between", value1, value2, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaybphaseapparentpowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayBPhaseApparentPower not between", value1, value2, "nowdaybphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerIsNull() {
            addCriterion("NowDayCPhaseApparentPower is null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerIsNotNull() {
            addCriterion("NowDayCPhaseApparentPower is not null");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerEqualTo(Double value) {
            addCriterion("NowDayCPhaseApparentPower =", value, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerNotEqualTo(Double value) {
            addCriterion("NowDayCPhaseApparentPower <>", value, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerGreaterThan(Double value) {
            addCriterion("NowDayCPhaseApparentPower >", value, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerGreaterThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseApparentPower >=", value, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerLessThan(Double value) {
            addCriterion("NowDayCPhaseApparentPower <", value, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerLessThanOrEqualTo(Double value) {
            addCriterion("NowDayCPhaseApparentPower <=", value, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerIn(List<Double> values) {
            addCriterion("NowDayCPhaseApparentPower in", values, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerNotIn(List<Double> values) {
            addCriterion("NowDayCPhaseApparentPower not in", values, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseApparentPower between", value1, value2, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andNowdaycphaseapparentpowerNotBetween(Double value1, Double value2) {
            addCriterion("NowDayCPhaseApparentPower not between", value1, value2, "nowdaycphaseapparentpower");
            return (Criteria) this;
        }

        public Criteria andUtpcIsNull() {
            addCriterion("UTPC is null");
            return (Criteria) this;
        }

        public Criteria andUtpcIsNotNull() {
            addCriterion("UTPC is not null");
            return (Criteria) this;
        }

        public Criteria andUtpcEqualTo(Double value) {
            addCriterion("UTPC =", value, "utpc");
            return (Criteria) this;
        }

        public Criteria andUtpcNotEqualTo(Double value) {
            addCriterion("UTPC <>", value, "utpc");
            return (Criteria) this;
        }

        public Criteria andUtpcGreaterThan(Double value) {
            addCriterion("UTPC >", value, "utpc");
            return (Criteria) this;
        }

        public Criteria andUtpcGreaterThanOrEqualTo(Double value) {
            addCriterion("UTPC >=", value, "utpc");
            return (Criteria) this;
        }

        public Criteria andUtpcLessThan(Double value) {
            addCriterion("UTPC <", value, "utpc");
            return (Criteria) this;
        }

        public Criteria andUtpcLessThanOrEqualTo(Double value) {
            addCriterion("UTPC <=", value, "utpc");
            return (Criteria) this;
        }

        public Criteria andUtpcIn(List<Double> values) {
            addCriterion("UTPC in", values, "utpc");
            return (Criteria) this;
        }

        public Criteria andUtpcNotIn(List<Double> values) {
            addCriterion("UTPC not in", values, "utpc");
            return (Criteria) this;
        }

        public Criteria andUtpcBetween(Double value1, Double value2) {
            addCriterion("UTPC between", value1, value2, "utpc");
            return (Criteria) this;
        }

        public Criteria andUtpcNotBetween(Double value1, Double value2) {
            addCriterion("UTPC not between", value1, value2, "utpc");
            return (Criteria) this;
        }

        public Criteria andOverloadIsNull() {
            addCriterion("Overload is null");
            return (Criteria) this;
        }

        public Criteria andOverloadIsNotNull() {
            addCriterion("Overload is not null");
            return (Criteria) this;
        }

        public Criteria andOverloadEqualTo(Double value) {
            addCriterion("Overload =", value, "overload");
            return (Criteria) this;
        }

        public Criteria andOverloadNotEqualTo(Double value) {
            addCriterion("Overload <>", value, "overload");
            return (Criteria) this;
        }

        public Criteria andOverloadGreaterThan(Double value) {
            addCriterion("Overload >", value, "overload");
            return (Criteria) this;
        }

        public Criteria andOverloadGreaterThanOrEqualTo(Double value) {
            addCriterion("Overload >=", value, "overload");
            return (Criteria) this;
        }

        public Criteria andOverloadLessThan(Double value) {
            addCriterion("Overload <", value, "overload");
            return (Criteria) this;
        }

        public Criteria andOverloadLessThanOrEqualTo(Double value) {
            addCriterion("Overload <=", value, "overload");
            return (Criteria) this;
        }

        public Criteria andOverloadIn(List<Double> values) {
            addCriterion("Overload in", values, "overload");
            return (Criteria) this;
        }

        public Criteria andOverloadNotIn(List<Double> values) {
            addCriterion("Overload not in", values, "overload");
            return (Criteria) this;
        }

        public Criteria andOverloadBetween(Double value1, Double value2) {
            addCriterion("Overload between", value1, value2, "overload");
            return (Criteria) this;
        }

        public Criteria andOverloadNotBetween(Double value1, Double value2) {
            addCriterion("Overload not between", value1, value2, "overload");
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