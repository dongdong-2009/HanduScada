package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseSmssendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseSmssendExample() {
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

        public Criteria andPhonenoIsNull() {
            addCriterion("PhoneNo is null");
            return (Criteria) this;
        }

        public Criteria andPhonenoIsNotNull() {
            addCriterion("PhoneNo is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenoEqualTo(String value) {
            addCriterion("PhoneNo =", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoNotEqualTo(String value) {
            addCriterion("PhoneNo <>", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoGreaterThan(String value) {
            addCriterion("PhoneNo >", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoGreaterThanOrEqualTo(String value) {
            addCriterion("PhoneNo >=", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoLessThan(String value) {
            addCriterion("PhoneNo <", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoLessThanOrEqualTo(String value) {
            addCriterion("PhoneNo <=", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoLike(String value) {
            addCriterion("PhoneNo like", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoNotLike(String value) {
            addCriterion("PhoneNo not like", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoIn(List<String> values) {
            addCriterion("PhoneNo in", values, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoNotIn(List<String> values) {
            addCriterion("PhoneNo not in", values, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoBetween(String value1, String value2) {
            addCriterion("PhoneNo between", value1, value2, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoNotBetween(String value1, String value2) {
            addCriterion("PhoneNo not between", value1, value2, "phoneno");
            return (Criteria) this;
        }

        public Criteria andSmscontentIsNull() {
            addCriterion("SmsContent is null");
            return (Criteria) this;
        }

        public Criteria andSmscontentIsNotNull() {
            addCriterion("SmsContent is not null");
            return (Criteria) this;
        }

        public Criteria andSmscontentEqualTo(String value) {
            addCriterion("SmsContent =", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentNotEqualTo(String value) {
            addCriterion("SmsContent <>", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentGreaterThan(String value) {
            addCriterion("SmsContent >", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentGreaterThanOrEqualTo(String value) {
            addCriterion("SmsContent >=", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentLessThan(String value) {
            addCriterion("SmsContent <", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentLessThanOrEqualTo(String value) {
            addCriterion("SmsContent <=", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentLike(String value) {
            addCriterion("SmsContent like", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentNotLike(String value) {
            addCriterion("SmsContent not like", value, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentIn(List<String> values) {
            addCriterion("SmsContent in", values, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentNotIn(List<String> values) {
            addCriterion("SmsContent not in", values, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentBetween(String value1, String value2) {
            addCriterion("SmsContent between", value1, value2, "smscontent");
            return (Criteria) this;
        }

        public Criteria andSmscontentNotBetween(String value1, String value2) {
            addCriterion("SmsContent not between", value1, value2, "smscontent");
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

        public Criteria andIssendIsNull() {
            addCriterion("IsSend is null");
            return (Criteria) this;
        }

        public Criteria andIssendIsNotNull() {
            addCriterion("IsSend is not null");
            return (Criteria) this;
        }

        public Criteria andIssendEqualTo(Integer value) {
            addCriterion("IsSend =", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendNotEqualTo(Integer value) {
            addCriterion("IsSend <>", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendGreaterThan(Integer value) {
            addCriterion("IsSend >", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsSend >=", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendLessThan(Integer value) {
            addCriterion("IsSend <", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendLessThanOrEqualTo(Integer value) {
            addCriterion("IsSend <=", value, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendIn(List<Integer> values) {
            addCriterion("IsSend in", values, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendNotIn(List<Integer> values) {
            addCriterion("IsSend not in", values, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendBetween(Integer value1, Integer value2) {
            addCriterion("IsSend between", value1, value2, "issend");
            return (Criteria) this;
        }

        public Criteria andIssendNotBetween(Integer value1, Integer value2) {
            addCriterion("IsSend not between", value1, value2, "issend");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNull() {
            addCriterion("SendTime is null");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNotNull() {
            addCriterion("SendTime is not null");
            return (Criteria) this;
        }

        public Criteria andSendtimeEqualTo(Date value) {
            addCriterion("SendTime =", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotEqualTo(Date value) {
            addCriterion("SendTime <>", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThan(Date value) {
            addCriterion("SendTime >", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SendTime >=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThan(Date value) {
            addCriterion("SendTime <", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThanOrEqualTo(Date value) {
            addCriterion("SendTime <=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIn(List<Date> values) {
            addCriterion("SendTime in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotIn(List<Date> values) {
            addCriterion("SendTime not in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeBetween(Date value1, Date value2) {
            addCriterion("SendTime between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotBetween(Date value1, Date value2) {
            addCriterion("SendTime not between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendnoIsNull() {
            addCriterion("SendNo is null");
            return (Criteria) this;
        }

        public Criteria andSendnoIsNotNull() {
            addCriterion("SendNo is not null");
            return (Criteria) this;
        }

        public Criteria andSendnoEqualTo(String value) {
            addCriterion("SendNo =", value, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoNotEqualTo(String value) {
            addCriterion("SendNo <>", value, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoGreaterThan(String value) {
            addCriterion("SendNo >", value, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoGreaterThanOrEqualTo(String value) {
            addCriterion("SendNo >=", value, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoLessThan(String value) {
            addCriterion("SendNo <", value, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoLessThanOrEqualTo(String value) {
            addCriterion("SendNo <=", value, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoLike(String value) {
            addCriterion("SendNo like", value, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoNotLike(String value) {
            addCriterion("SendNo not like", value, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoIn(List<String> values) {
            addCriterion("SendNo in", values, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoNotIn(List<String> values) {
            addCriterion("SendNo not in", values, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoBetween(String value1, String value2) {
            addCriterion("SendNo between", value1, value2, "sendno");
            return (Criteria) this;
        }

        public Criteria andSendnoNotBetween(String value1, String value2) {
            addCriterion("SendNo not between", value1, value2, "sendno");
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

        public Criteria andResultcontentIsNull() {
            addCriterion("ResultContent is null");
            return (Criteria) this;
        }

        public Criteria andResultcontentIsNotNull() {
            addCriterion("ResultContent is not null");
            return (Criteria) this;
        }

        public Criteria andResultcontentEqualTo(String value) {
            addCriterion("ResultContent =", value, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentNotEqualTo(String value) {
            addCriterion("ResultContent <>", value, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentGreaterThan(String value) {
            addCriterion("ResultContent >", value, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentGreaterThanOrEqualTo(String value) {
            addCriterion("ResultContent >=", value, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentLessThan(String value) {
            addCriterion("ResultContent <", value, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentLessThanOrEqualTo(String value) {
            addCriterion("ResultContent <=", value, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentLike(String value) {
            addCriterion("ResultContent like", value, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentNotLike(String value) {
            addCriterion("ResultContent not like", value, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentIn(List<String> values) {
            addCriterion("ResultContent in", values, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentNotIn(List<String> values) {
            addCriterion("ResultContent not in", values, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentBetween(String value1, String value2) {
            addCriterion("ResultContent between", value1, value2, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andResultcontentNotBetween(String value1, String value2) {
            addCriterion("ResultContent not between", value1, value2, "resultcontent");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("Priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("Priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("Priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("Priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("Priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("Priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("Priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("Priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("Priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("Priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("Priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("Priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andMsgsendidIsNull() {
            addCriterion("MsgSendId is null");
            return (Criteria) this;
        }

        public Criteria andMsgsendidIsNotNull() {
            addCriterion("MsgSendId is not null");
            return (Criteria) this;
        }

        public Criteria andMsgsendidEqualTo(String value) {
            addCriterion("MsgSendId =", value, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidNotEqualTo(String value) {
            addCriterion("MsgSendId <>", value, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidGreaterThan(String value) {
            addCriterion("MsgSendId >", value, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidGreaterThanOrEqualTo(String value) {
            addCriterion("MsgSendId >=", value, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidLessThan(String value) {
            addCriterion("MsgSendId <", value, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidLessThanOrEqualTo(String value) {
            addCriterion("MsgSendId <=", value, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidLike(String value) {
            addCriterion("MsgSendId like", value, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidNotLike(String value) {
            addCriterion("MsgSendId not like", value, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidIn(List<String> values) {
            addCriterion("MsgSendId in", values, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidNotIn(List<String> values) {
            addCriterion("MsgSendId not in", values, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidBetween(String value1, String value2) {
            addCriterion("MsgSendId between", value1, value2, "msgsendid");
            return (Criteria) this;
        }

        public Criteria andMsgsendidNotBetween(String value1, String value2) {
            addCriterion("MsgSendId not between", value1, value2, "msgsendid");
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