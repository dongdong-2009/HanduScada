package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseRequestdataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseRequestdataExample() {
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

        public Criteria andTabnameIsNull() {
            addCriterion("TabName is null");
            return (Criteria) this;
        }

        public Criteria andTabnameIsNotNull() {
            addCriterion("TabName is not null");
            return (Criteria) this;
        }

        public Criteria andTabnameEqualTo(String value) {
            addCriterion("TabName =", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameNotEqualTo(String value) {
            addCriterion("TabName <>", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameGreaterThan(String value) {
            addCriterion("TabName >", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameGreaterThanOrEqualTo(String value) {
            addCriterion("TabName >=", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameLessThan(String value) {
            addCriterion("TabName <", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameLessThanOrEqualTo(String value) {
            addCriterion("TabName <=", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameLike(String value) {
            addCriterion("TabName like", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameNotLike(String value) {
            addCriterion("TabName not like", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameIn(List<String> values) {
            addCriterion("TabName in", values, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameNotIn(List<String> values) {
            addCriterion("TabName not in", values, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameBetween(String value1, String value2) {
            addCriterion("TabName between", value1, value2, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameNotBetween(String value1, String value2) {
            addCriterion("TabName not between", value1, value2, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabidIsNull() {
            addCriterion("TabId is null");
            return (Criteria) this;
        }

        public Criteria andTabidIsNotNull() {
            addCriterion("TabId is not null");
            return (Criteria) this;
        }

        public Criteria andTabidEqualTo(String value) {
            addCriterion("TabId =", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidNotEqualTo(String value) {
            addCriterion("TabId <>", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidGreaterThan(String value) {
            addCriterion("TabId >", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidGreaterThanOrEqualTo(String value) {
            addCriterion("TabId >=", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidLessThan(String value) {
            addCriterion("TabId <", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidLessThanOrEqualTo(String value) {
            addCriterion("TabId <=", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidLike(String value) {
            addCriterion("TabId like", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidNotLike(String value) {
            addCriterion("TabId not like", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidIn(List<String> values) {
            addCriterion("TabId in", values, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidNotIn(List<String> values) {
            addCriterion("TabId not in", values, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidBetween(String value1, String value2) {
            addCriterion("TabId between", value1, value2, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidNotBetween(String value1, String value2) {
            addCriterion("TabId not between", value1, value2, "tabid");
            return (Criteria) this;
        }

        public Criteria andCmdtypeIsNull() {
            addCriterion("CmdType is null");
            return (Criteria) this;
        }

        public Criteria andCmdtypeIsNotNull() {
            addCriterion("CmdType is not null");
            return (Criteria) this;
        }

        public Criteria andCmdtypeEqualTo(String value) {
            addCriterion("CmdType =", value, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeNotEqualTo(String value) {
            addCriterion("CmdType <>", value, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeGreaterThan(String value) {
            addCriterion("CmdType >", value, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeGreaterThanOrEqualTo(String value) {
            addCriterion("CmdType >=", value, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeLessThan(String value) {
            addCriterion("CmdType <", value, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeLessThanOrEqualTo(String value) {
            addCriterion("CmdType <=", value, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeLike(String value) {
            addCriterion("CmdType like", value, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeNotLike(String value) {
            addCriterion("CmdType not like", value, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeIn(List<String> values) {
            addCriterion("CmdType in", values, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeNotIn(List<String> values) {
            addCriterion("CmdType not in", values, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeBetween(String value1, String value2) {
            addCriterion("CmdType between", value1, value2, "cmdtype");
            return (Criteria) this;
        }

        public Criteria andCmdtypeNotBetween(String value1, String value2) {
            addCriterion("CmdType not between", value1, value2, "cmdtype");
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

        public Criteria andDatafromIsNull() {
            addCriterion("DataFrom is null");
            return (Criteria) this;
        }

        public Criteria andDatafromIsNotNull() {
            addCriterion("DataFrom is not null");
            return (Criteria) this;
        }

        public Criteria andDatafromEqualTo(String value) {
            addCriterion("DataFrom =", value, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromNotEqualTo(String value) {
            addCriterion("DataFrom <>", value, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromGreaterThan(String value) {
            addCriterion("DataFrom >", value, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromGreaterThanOrEqualTo(String value) {
            addCriterion("DataFrom >=", value, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromLessThan(String value) {
            addCriterion("DataFrom <", value, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromLessThanOrEqualTo(String value) {
            addCriterion("DataFrom <=", value, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromLike(String value) {
            addCriterion("DataFrom like", value, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromNotLike(String value) {
            addCriterion("DataFrom not like", value, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromIn(List<String> values) {
            addCriterion("DataFrom in", values, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromNotIn(List<String> values) {
            addCriterion("DataFrom not in", values, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromBetween(String value1, String value2) {
            addCriterion("DataFrom between", value1, value2, "datafrom");
            return (Criteria) this;
        }

        public Criteria andDatafromNotBetween(String value1, String value2) {
            addCriterion("DataFrom not between", value1, value2, "datafrom");
            return (Criteria) this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("Info is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("Info is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("Info =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("Info <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("Info >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("Info >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("Info <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("Info <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("Info like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("Info not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("Info in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("Info not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("Info between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("Info not between", value1, value2, "info");
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