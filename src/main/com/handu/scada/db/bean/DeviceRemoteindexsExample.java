package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.List;

public class DeviceRemoteindexsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceRemoteindexsExample() {
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

        public Criteria andGroupnameIsNull() {
            addCriterion("GroupName is null");
            return (Criteria) this;
        }

        public Criteria andGroupnameIsNotNull() {
            addCriterion("GroupName is not null");
            return (Criteria) this;
        }

        public Criteria andGroupnameEqualTo(String value) {
            addCriterion("GroupName =", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotEqualTo(String value) {
            addCriterion("GroupName <>", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThan(String value) {
            addCriterion("GroupName >", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("GroupName >=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThan(String value) {
            addCriterion("GroupName <", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThanOrEqualTo(String value) {
            addCriterion("GroupName <=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLike(String value) {
            addCriterion("GroupName like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotLike(String value) {
            addCriterion("GroupName not like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameIn(List<String> values) {
            addCriterion("GroupName in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotIn(List<String> values) {
            addCriterion("GroupName not in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameBetween(String value1, String value2) {
            addCriterion("GroupName between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotBetween(String value1, String value2) {
            addCriterion("GroupName not between", value1, value2, "groupname");
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