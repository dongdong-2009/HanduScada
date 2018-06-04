package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceFaultindicatorSoeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceFaultindicatorSoeExample() {
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

        public Criteria andSoeidIsNull() {
            addCriterion("SoeId is null");
            return (Criteria) this;
        }

        public Criteria andSoeidIsNotNull() {
            addCriterion("SoeId is not null");
            return (Criteria) this;
        }

        public Criteria andSoeidEqualTo(String value) {
            addCriterion("SoeId =", value, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidNotEqualTo(String value) {
            addCriterion("SoeId <>", value, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidGreaterThan(String value) {
            addCriterion("SoeId >", value, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidGreaterThanOrEqualTo(String value) {
            addCriterion("SoeId >=", value, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidLessThan(String value) {
            addCriterion("SoeId <", value, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidLessThanOrEqualTo(String value) {
            addCriterion("SoeId <=", value, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidLike(String value) {
            addCriterion("SoeId like", value, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidNotLike(String value) {
            addCriterion("SoeId not like", value, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidIn(List<String> values) {
            addCriterion("SoeId in", values, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidNotIn(List<String> values) {
            addCriterion("SoeId not in", values, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidBetween(String value1, String value2) {
            addCriterion("SoeId between", value1, value2, "soeid");
            return (Criteria) this;
        }

        public Criteria andSoeidNotBetween(String value1, String value2) {
            addCriterion("SoeId not between", value1, value2, "soeid");
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

        public Criteria andDataitemIsNull() {
            addCriterion("DataItem is null");
            return (Criteria) this;
        }

        public Criteria andDataitemIsNotNull() {
            addCriterion("DataItem is not null");
            return (Criteria) this;
        }

        public Criteria andDataitemEqualTo(String value) {
            addCriterion("DataItem =", value, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemNotEqualTo(String value) {
            addCriterion("DataItem <>", value, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemGreaterThan(String value) {
            addCriterion("DataItem >", value, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemGreaterThanOrEqualTo(String value) {
            addCriterion("DataItem >=", value, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemLessThan(String value) {
            addCriterion("DataItem <", value, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemLessThanOrEqualTo(String value) {
            addCriterion("DataItem <=", value, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemLike(String value) {
            addCriterion("DataItem like", value, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemNotLike(String value) {
            addCriterion("DataItem not like", value, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemIn(List<String> values) {
            addCriterion("DataItem in", values, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemNotIn(List<String> values) {
            addCriterion("DataItem not in", values, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemBetween(String value1, String value2) {
            addCriterion("DataItem between", value1, value2, "dataitem");
            return (Criteria) this;
        }

        public Criteria andDataitemNotBetween(String value1, String value2) {
            addCriterion("DataItem not between", value1, value2, "dataitem");
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

        public Criteria andDataitemnameIsNull() {
            addCriterion("DataItemName is null");
            return (Criteria) this;
        }

        public Criteria andDataitemnameIsNotNull() {
            addCriterion("DataItemName is not null");
            return (Criteria) this;
        }

        public Criteria andDataitemnameEqualTo(String value) {
            addCriterion("DataItemName =", value, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameNotEqualTo(String value) {
            addCriterion("DataItemName <>", value, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameGreaterThan(String value) {
            addCriterion("DataItemName >", value, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameGreaterThanOrEqualTo(String value) {
            addCriterion("DataItemName >=", value, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameLessThan(String value) {
            addCriterion("DataItemName <", value, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameLessThanOrEqualTo(String value) {
            addCriterion("DataItemName <=", value, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameLike(String value) {
            addCriterion("DataItemName like", value, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameNotLike(String value) {
            addCriterion("DataItemName not like", value, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameIn(List<String> values) {
            addCriterion("DataItemName in", values, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameNotIn(List<String> values) {
            addCriterion("DataItemName not in", values, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameBetween(String value1, String value2) {
            addCriterion("DataItemName between", value1, value2, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andDataitemnameNotBetween(String value1, String value2) {
            addCriterion("DataItemName not between", value1, value2, "dataitemname");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("Unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("Unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("Unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("Unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("Unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("Unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("Unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("Unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("Unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("Unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("Unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("Unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("Unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("Unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andPhaseIsNull() {
            addCriterion("Phase is null");
            return (Criteria) this;
        }

        public Criteria andPhaseIsNotNull() {
            addCriterion("Phase is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseEqualTo(String value) {
            addCriterion("Phase =", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotEqualTo(String value) {
            addCriterion("Phase <>", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseGreaterThan(String value) {
            addCriterion("Phase >", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseGreaterThanOrEqualTo(String value) {
            addCriterion("Phase >=", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseLessThan(String value) {
            addCriterion("Phase <", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseLessThanOrEqualTo(String value) {
            addCriterion("Phase <=", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseLike(String value) {
            addCriterion("Phase like", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotLike(String value) {
            addCriterion("Phase not like", value, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseIn(List<String> values) {
            addCriterion("Phase in", values, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotIn(List<String> values) {
            addCriterion("Phase not in", values, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseBetween(String value1, String value2) {
            addCriterion("Phase between", value1, value2, "phase");
            return (Criteria) this;
        }

        public Criteria andPhaseNotBetween(String value1, String value2) {
            addCriterion("Phase not between", value1, value2, "phase");
            return (Criteria) this;
        }

        public Criteria andSoetypeIsNull() {
            addCriterion("SoeType is null");
            return (Criteria) this;
        }

        public Criteria andSoetypeIsNotNull() {
            addCriterion("SoeType is not null");
            return (Criteria) this;
        }

        public Criteria andSoetypeEqualTo(Integer value) {
            addCriterion("SoeType =", value, "soetype");
            return (Criteria) this;
        }

        public Criteria andSoetypeNotEqualTo(Integer value) {
            addCriterion("SoeType <>", value, "soetype");
            return (Criteria) this;
        }

        public Criteria andSoetypeGreaterThan(Integer value) {
            addCriterion("SoeType >", value, "soetype");
            return (Criteria) this;
        }

        public Criteria andSoetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("SoeType >=", value, "soetype");
            return (Criteria) this;
        }

        public Criteria andSoetypeLessThan(Integer value) {
            addCriterion("SoeType <", value, "soetype");
            return (Criteria) this;
        }

        public Criteria andSoetypeLessThanOrEqualTo(Integer value) {
            addCriterion("SoeType <=", value, "soetype");
            return (Criteria) this;
        }

        public Criteria andSoetypeIn(List<Integer> values) {
            addCriterion("SoeType in", values, "soetype");
            return (Criteria) this;
        }

        public Criteria andSoetypeNotIn(List<Integer> values) {
            addCriterion("SoeType not in", values, "soetype");
            return (Criteria) this;
        }

        public Criteria andSoetypeBetween(Integer value1, Integer value2) {
            addCriterion("SoeType between", value1, value2, "soetype");
            return (Criteria) this;
        }

        public Criteria andSoetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("SoeType not between", value1, value2, "soetype");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("Level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("Level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("Level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("Level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("Level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("Level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("Level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("Level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("Level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("Level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("Level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("Level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andSoetimeIsNull() {
            addCriterion("SoeTime is null");
            return (Criteria) this;
        }

        public Criteria andSoetimeIsNotNull() {
            addCriterion("SoeTime is not null");
            return (Criteria) this;
        }

        public Criteria andSoetimeEqualTo(Date value) {
            addCriterion("SoeTime =", value, "soetime");
            return (Criteria) this;
        }

        public Criteria andSoetimeNotEqualTo(Date value) {
            addCriterion("SoeTime <>", value, "soetime");
            return (Criteria) this;
        }

        public Criteria andSoetimeGreaterThan(Date value) {
            addCriterion("SoeTime >", value, "soetime");
            return (Criteria) this;
        }

        public Criteria andSoetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SoeTime >=", value, "soetime");
            return (Criteria) this;
        }

        public Criteria andSoetimeLessThan(Date value) {
            addCriterion("SoeTime <", value, "soetime");
            return (Criteria) this;
        }

        public Criteria andSoetimeLessThanOrEqualTo(Date value) {
            addCriterion("SoeTime <=", value, "soetime");
            return (Criteria) this;
        }

        public Criteria andSoetimeIn(List<Date> values) {
            addCriterion("SoeTime in", values, "soetime");
            return (Criteria) this;
        }

        public Criteria andSoetimeNotIn(List<Date> values) {
            addCriterion("SoeTime not in", values, "soetime");
            return (Criteria) this;
        }

        public Criteria andSoetimeBetween(Date value1, Date value2) {
            addCriterion("SoeTime between", value1, value2, "soetime");
            return (Criteria) this;
        }

        public Criteria andSoetimeNotBetween(Date value1, Date value2) {
            addCriterion("SoeTime not between", value1, value2, "soetime");
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