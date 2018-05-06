package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceRcdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceRcdExample() {
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

        public Criteria andDaidIsNull() {
            addCriterion("DaId is null");
            return (Criteria) this;
        }

        public Criteria andDaidIsNotNull() {
            addCriterion("DaId is not null");
            return (Criteria) this;
        }

        public Criteria andDaidEqualTo(String value) {
            addCriterion("DaId =", value, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidNotEqualTo(String value) {
            addCriterion("DaId <>", value, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidGreaterThan(String value) {
            addCriterion("DaId >", value, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidGreaterThanOrEqualTo(String value) {
            addCriterion("DaId >=", value, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidLessThan(String value) {
            addCriterion("DaId <", value, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidLessThanOrEqualTo(String value) {
            addCriterion("DaId <=", value, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidLike(String value) {
            addCriterion("DaId like", value, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidNotLike(String value) {
            addCriterion("DaId not like", value, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidIn(List<String> values) {
            addCriterion("DaId in", values, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidNotIn(List<String> values) {
            addCriterion("DaId not in", values, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidBetween(String value1, String value2) {
            addCriterion("DaId between", value1, value2, "daid");
            return (Criteria) this;
        }

        public Criteria andDaidNotBetween(String value1, String value2) {
            addCriterion("DaId not between", value1, value2, "daid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("Address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("Address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("Address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("Address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("Address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("Address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("Address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("Address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("Address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("Address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("Address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("Address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("Address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("Address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andBaudrateIsNull() {
            addCriterion("BaudRate is null");
            return (Criteria) this;
        }

        public Criteria andBaudrateIsNotNull() {
            addCriterion("BaudRate is not null");
            return (Criteria) this;
        }

        public Criteria andBaudrateEqualTo(String value) {
            addCriterion("BaudRate =", value, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateNotEqualTo(String value) {
            addCriterion("BaudRate <>", value, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateGreaterThan(String value) {
            addCriterion("BaudRate >", value, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateGreaterThanOrEqualTo(String value) {
            addCriterion("BaudRate >=", value, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateLessThan(String value) {
            addCriterion("BaudRate <", value, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateLessThanOrEqualTo(String value) {
            addCriterion("BaudRate <=", value, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateLike(String value) {
            addCriterion("BaudRate like", value, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateNotLike(String value) {
            addCriterion("BaudRate not like", value, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateIn(List<String> values) {
            addCriterion("BaudRate in", values, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateNotIn(List<String> values) {
            addCriterion("BaudRate not in", values, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateBetween(String value1, String value2) {
            addCriterion("BaudRate between", value1, value2, "baudrate");
            return (Criteria) this;
        }

        public Criteria andBaudrateNotBetween(String value1, String value2) {
            addCriterion("BaudRate not between", value1, value2, "baudrate");
            return (Criteria) this;
        }

        public Criteria andCheckdigitIsNull() {
            addCriterion("Checkdigit is null");
            return (Criteria) this;
        }

        public Criteria andCheckdigitIsNotNull() {
            addCriterion("Checkdigit is not null");
            return (Criteria) this;
        }

        public Criteria andCheckdigitEqualTo(String value) {
            addCriterion("Checkdigit =", value, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitNotEqualTo(String value) {
            addCriterion("Checkdigit <>", value, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitGreaterThan(String value) {
            addCriterion("Checkdigit >", value, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitGreaterThanOrEqualTo(String value) {
            addCriterion("Checkdigit >=", value, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitLessThan(String value) {
            addCriterion("Checkdigit <", value, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitLessThanOrEqualTo(String value) {
            addCriterion("Checkdigit <=", value, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitLike(String value) {
            addCriterion("Checkdigit like", value, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitNotLike(String value) {
            addCriterion("Checkdigit not like", value, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitIn(List<String> values) {
            addCriterion("Checkdigit in", values, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitNotIn(List<String> values) {
            addCriterion("Checkdigit not in", values, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitBetween(String value1, String value2) {
            addCriterion("Checkdigit between", value1, value2, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andCheckdigitNotBetween(String value1, String value2) {
            addCriterion("Checkdigit not between", value1, value2, "checkdigit");
            return (Criteria) this;
        }

        public Criteria andTerminalidIsNull() {
            addCriterion("TerminalId is null");
            return (Criteria) this;
        }

        public Criteria andTerminalidIsNotNull() {
            addCriterion("TerminalId is not null");
            return (Criteria) this;
        }

        public Criteria andTerminalidEqualTo(String value) {
            addCriterion("TerminalId =", value, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidNotEqualTo(String value) {
            addCriterion("TerminalId <>", value, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidGreaterThan(String value) {
            addCriterion("TerminalId >", value, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidGreaterThanOrEqualTo(String value) {
            addCriterion("TerminalId >=", value, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidLessThan(String value) {
            addCriterion("TerminalId <", value, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidLessThanOrEqualTo(String value) {
            addCriterion("TerminalId <=", value, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidLike(String value) {
            addCriterion("TerminalId like", value, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidNotLike(String value) {
            addCriterion("TerminalId not like", value, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidIn(List<String> values) {
            addCriterion("TerminalId in", values, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidNotIn(List<String> values) {
            addCriterion("TerminalId not in", values, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidBetween(String value1, String value2) {
            addCriterion("TerminalId between", value1, value2, "terminalid");
            return (Criteria) this;
        }

        public Criteria andTerminalidNotBetween(String value1, String value2) {
            addCriterion("TerminalId not between", value1, value2, "terminalid");
            return (Criteria) this;
        }

        public Criteria andRcdmodelIsNull() {
            addCriterion("RcdModel is null");
            return (Criteria) this;
        }

        public Criteria andRcdmodelIsNotNull() {
            addCriterion("RcdModel is not null");
            return (Criteria) this;
        }

        public Criteria andRcdmodelEqualTo(String value) {
            addCriterion("RcdModel =", value, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelNotEqualTo(String value) {
            addCriterion("RcdModel <>", value, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelGreaterThan(String value) {
            addCriterion("RcdModel >", value, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelGreaterThanOrEqualTo(String value) {
            addCriterion("RcdModel >=", value, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelLessThan(String value) {
            addCriterion("RcdModel <", value, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelLessThanOrEqualTo(String value) {
            addCriterion("RcdModel <=", value, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelLike(String value) {
            addCriterion("RcdModel like", value, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelNotLike(String value) {
            addCriterion("RcdModel not like", value, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelIn(List<String> values) {
            addCriterion("RcdModel in", values, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelNotIn(List<String> values) {
            addCriterion("RcdModel not in", values, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelBetween(String value1, String value2) {
            addCriterion("RcdModel between", value1, value2, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andRcdmodelNotBetween(String value1, String value2) {
            addCriterion("RcdModel not between", value1, value2, "rcdmodel");
            return (Criteria) this;
        }

        public Criteria andMfrsIsNull() {
            addCriterion("Mfrs is null");
            return (Criteria) this;
        }

        public Criteria andMfrsIsNotNull() {
            addCriterion("Mfrs is not null");
            return (Criteria) this;
        }

        public Criteria andMfrsEqualTo(String value) {
            addCriterion("Mfrs =", value, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsNotEqualTo(String value) {
            addCriterion("Mfrs <>", value, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsGreaterThan(String value) {
            addCriterion("Mfrs >", value, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsGreaterThanOrEqualTo(String value) {
            addCriterion("Mfrs >=", value, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsLessThan(String value) {
            addCriterion("Mfrs <", value, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsLessThanOrEqualTo(String value) {
            addCriterion("Mfrs <=", value, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsLike(String value) {
            addCriterion("Mfrs like", value, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsNotLike(String value) {
            addCriterion("Mfrs not like", value, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsIn(List<String> values) {
            addCriterion("Mfrs in", values, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsNotIn(List<String> values) {
            addCriterion("Mfrs not in", values, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsBetween(String value1, String value2) {
            addCriterion("Mfrs between", value1, value2, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMfrsNotBetween(String value1, String value2) {
            addCriterion("Mfrs not between", value1, value2, "mfrs");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueIsNull() {
            addCriterion("MeasureValue is null");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueIsNotNull() {
            addCriterion("MeasureValue is not null");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueEqualTo(Integer value) {
            addCriterion("MeasureValue =", value, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueNotEqualTo(Integer value) {
            addCriterion("MeasureValue <>", value, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueGreaterThan(Integer value) {
            addCriterion("MeasureValue >", value, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueGreaterThanOrEqualTo(Integer value) {
            addCriterion("MeasureValue >=", value, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueLessThan(Integer value) {
            addCriterion("MeasureValue <", value, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueLessThanOrEqualTo(Integer value) {
            addCriterion("MeasureValue <=", value, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueIn(List<Integer> values) {
            addCriterion("MeasureValue in", values, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueNotIn(List<Integer> values) {
            addCriterion("MeasureValue not in", values, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueBetween(Integer value1, Integer value2) {
            addCriterion("MeasureValue between", value1, value2, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andMeasurevalueNotBetween(Integer value1, Integer value2) {
            addCriterion("MeasureValue not between", value1, value2, "measurevalue");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("Code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("Code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("Code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("Code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("Code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("Code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("Code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("Code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("Code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("Code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("Code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("Code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("Code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("Code not between", value1, value2, "code");
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

        public Criteria andLevelEqualTo(String value) {
            addCriterion("Level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("Level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("Level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("Level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("Level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("Level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("Level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("Level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("Level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("Level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("Level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("Level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("Pwd is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("Pwd is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("Pwd =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("Pwd <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("Pwd >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("Pwd >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("Pwd <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("Pwd <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("Pwd like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("Pwd not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("Pwd in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("Pwd not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("Pwd between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("Pwd not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("Size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("Size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(String value) {
            addCriterion("Size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(String value) {
            addCriterion("Size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(String value) {
            addCriterion("Size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(String value) {
            addCriterion("Size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(String value) {
            addCriterion("Size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(String value) {
            addCriterion("Size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLike(String value) {
            addCriterion("Size like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotLike(String value) {
            addCriterion("Size not like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<String> values) {
            addCriterion("Size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<String> values) {
            addCriterion("Size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(String value1, String value2) {
            addCriterion("Size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(String value1, String value2) {
            addCriterion("Size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andCycleIsNull() {
            addCriterion("Cycle is null");
            return (Criteria) this;
        }

        public Criteria andCycleIsNotNull() {
            addCriterion("Cycle is not null");
            return (Criteria) this;
        }

        public Criteria andCycleEqualTo(String value) {
            addCriterion("Cycle =", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotEqualTo(String value) {
            addCriterion("Cycle <>", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThan(String value) {
            addCriterion("Cycle >", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThanOrEqualTo(String value) {
            addCriterion("Cycle >=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThan(String value) {
            addCriterion("Cycle <", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThanOrEqualTo(String value) {
            addCriterion("Cycle <=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLike(String value) {
            addCriterion("Cycle like", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotLike(String value) {
            addCriterion("Cycle not like", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleIn(List<String> values) {
            addCriterion("Cycle in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotIn(List<String> values) {
            addCriterion("Cycle not in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleBetween(String value1, String value2) {
            addCriterion("Cycle between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotBetween(String value1, String value2) {
            addCriterion("Cycle not between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeIsNull() {
            addCriterion("WorkDateTime is null");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeIsNotNull() {
            addCriterion("WorkDateTime is not null");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeEqualTo(Date value) {
            addCriterion("WorkDateTime =", value, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeNotEqualTo(Date value) {
            addCriterion("WorkDateTime <>", value, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeGreaterThan(Date value) {
            addCriterion("WorkDateTime >", value, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("WorkDateTime >=", value, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeLessThan(Date value) {
            addCriterion("WorkDateTime <", value, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("WorkDateTime <=", value, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeIn(List<Date> values) {
            addCriterion("WorkDateTime in", values, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeNotIn(List<Date> values) {
            addCriterion("WorkDateTime not in", values, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeBetween(Date value1, Date value2) {
            addCriterion("WorkDateTime between", value1, value2, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andWorkdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("WorkDateTime not between", value1, value2, "workdatetime");
            return (Criteria) this;
        }

        public Criteria andValidateIsNull() {
            addCriterion("Validate is null");
            return (Criteria) this;
        }

        public Criteria andValidateIsNotNull() {
            addCriterion("Validate is not null");
            return (Criteria) this;
        }

        public Criteria andValidateEqualTo(String value) {
            addCriterion("Validate =", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateNotEqualTo(String value) {
            addCriterion("Validate <>", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateGreaterThan(String value) {
            addCriterion("Validate >", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateGreaterThanOrEqualTo(String value) {
            addCriterion("Validate >=", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateLessThan(String value) {
            addCriterion("Validate <", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateLessThanOrEqualTo(String value) {
            addCriterion("Validate <=", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateLike(String value) {
            addCriterion("Validate like", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateNotLike(String value) {
            addCriterion("Validate not like", value, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateIn(List<String> values) {
            addCriterion("Validate in", values, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateNotIn(List<String> values) {
            addCriterion("Validate not in", values, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateBetween(String value1, String value2) {
            addCriterion("Validate between", value1, value2, "validate");
            return (Criteria) this;
        }

        public Criteria andValidateNotBetween(String value1, String value2) {
            addCriterion("Validate not between", value1, value2, "validate");
            return (Criteria) this;
        }

        public Criteria andIsappsaveIsNull() {
            addCriterion("IsAppSave is null");
            return (Criteria) this;
        }

        public Criteria andIsappsaveIsNotNull() {
            addCriterion("IsAppSave is not null");
            return (Criteria) this;
        }

        public Criteria andIsappsaveEqualTo(Integer value) {
            addCriterion("IsAppSave =", value, "isappsave");
            return (Criteria) this;
        }

        public Criteria andIsappsaveNotEqualTo(Integer value) {
            addCriterion("IsAppSave <>", value, "isappsave");
            return (Criteria) this;
        }

        public Criteria andIsappsaveGreaterThan(Integer value) {
            addCriterion("IsAppSave >", value, "isappsave");
            return (Criteria) this;
        }

        public Criteria andIsappsaveGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsAppSave >=", value, "isappsave");
            return (Criteria) this;
        }

        public Criteria andIsappsaveLessThan(Integer value) {
            addCriterion("IsAppSave <", value, "isappsave");
            return (Criteria) this;
        }

        public Criteria andIsappsaveLessThanOrEqualTo(Integer value) {
            addCriterion("IsAppSave <=", value, "isappsave");
            return (Criteria) this;
        }

        public Criteria andIsappsaveIn(List<Integer> values) {
            addCriterion("IsAppSave in", values, "isappsave");
            return (Criteria) this;
        }

        public Criteria andIsappsaveNotIn(List<Integer> values) {
            addCriterion("IsAppSave not in", values, "isappsave");
            return (Criteria) this;
        }

        public Criteria andIsappsaveBetween(Integer value1, Integer value2) {
            addCriterion("IsAppSave between", value1, value2, "isappsave");
            return (Criteria) this;
        }

        public Criteria andIsappsaveNotBetween(Integer value1, Integer value2) {
            addCriterion("IsAppSave not between", value1, value2, "isappsave");
            return (Criteria) this;
        }

        public Criteria andSortcodeIsNull() {
            addCriterion("SortCode is null");
            return (Criteria) this;
        }

        public Criteria andSortcodeIsNotNull() {
            addCriterion("SortCode is not null");
            return (Criteria) this;
        }

        public Criteria andSortcodeEqualTo(Integer value) {
            addCriterion("SortCode =", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotEqualTo(Integer value) {
            addCriterion("SortCode <>", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThan(Integer value) {
            addCriterion("SortCode >", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("SortCode >=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThan(Integer value) {
            addCriterion("SortCode <", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThanOrEqualTo(Integer value) {
            addCriterion("SortCode <=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeIn(List<Integer> values) {
            addCriterion("SortCode in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotIn(List<Integer> values) {
            addCriterion("SortCode not in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeBetween(Integer value1, Integer value2) {
            addCriterion("SortCode between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("SortCode not between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andDeletemarkIsNull() {
            addCriterion("DeleteMark is null");
            return (Criteria) this;
        }

        public Criteria andDeletemarkIsNotNull() {
            addCriterion("DeleteMark is not null");
            return (Criteria) this;
        }

        public Criteria andDeletemarkEqualTo(Integer value) {
            addCriterion("DeleteMark =", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkNotEqualTo(Integer value) {
            addCriterion("DeleteMark <>", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkGreaterThan(Integer value) {
            addCriterion("DeleteMark >", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("DeleteMark >=", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkLessThan(Integer value) {
            addCriterion("DeleteMark <", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkLessThanOrEqualTo(Integer value) {
            addCriterion("DeleteMark <=", value, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkIn(List<Integer> values) {
            addCriterion("DeleteMark in", values, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkNotIn(List<Integer> values) {
            addCriterion("DeleteMark not in", values, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkBetween(Integer value1, Integer value2) {
            addCriterion("DeleteMark between", value1, value2, "deletemark");
            return (Criteria) this;
        }

        public Criteria andDeletemarkNotBetween(Integer value1, Integer value2) {
            addCriterion("DeleteMark not between", value1, value2, "deletemark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkIsNull() {
            addCriterion("EnabledMark is null");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkIsNotNull() {
            addCriterion("EnabledMark is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkEqualTo(Integer value) {
            addCriterion("EnabledMark =", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkNotEqualTo(Integer value) {
            addCriterion("EnabledMark <>", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkGreaterThan(Integer value) {
            addCriterion("EnabledMark >", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("EnabledMark >=", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkLessThan(Integer value) {
            addCriterion("EnabledMark <", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkLessThanOrEqualTo(Integer value) {
            addCriterion("EnabledMark <=", value, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkIn(List<Integer> values) {
            addCriterion("EnabledMark in", values, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkNotIn(List<Integer> values) {
            addCriterion("EnabledMark not in", values, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkBetween(Integer value1, Integer value2) {
            addCriterion("EnabledMark between", value1, value2, "enabledmark");
            return (Criteria) this;
        }

        public Criteria andEnabledmarkNotBetween(Integer value1, Integer value2) {
            addCriterion("EnabledMark not between", value1, value2, "enabledmark");
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

        public Criteria andCreatedateIsNull() {
            addCriterion("CreateDate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("CreateDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("CreateDate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("CreateDate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("CreateDate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateDate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("CreateDate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("CreateDate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("CreateDate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("CreateDate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("CreateDate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("CreateDate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreateuseridIsNull() {
            addCriterion("CreateUserId is null");
            return (Criteria) this;
        }

        public Criteria andCreateuseridIsNotNull() {
            addCriterion("CreateUserId is not null");
            return (Criteria) this;
        }

        public Criteria andCreateuseridEqualTo(String value) {
            addCriterion("CreateUserId =", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotEqualTo(String value) {
            addCriterion("CreateUserId <>", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridGreaterThan(String value) {
            addCriterion("CreateUserId >", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridGreaterThanOrEqualTo(String value) {
            addCriterion("CreateUserId >=", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridLessThan(String value) {
            addCriterion("CreateUserId <", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridLessThanOrEqualTo(String value) {
            addCriterion("CreateUserId <=", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridLike(String value) {
            addCriterion("CreateUserId like", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotLike(String value) {
            addCriterion("CreateUserId not like", value, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridIn(List<String> values) {
            addCriterion("CreateUserId in", values, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotIn(List<String> values) {
            addCriterion("CreateUserId not in", values, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridBetween(String value1, String value2) {
            addCriterion("CreateUserId between", value1, value2, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateuseridNotBetween(String value1, String value2) {
            addCriterion("CreateUserId not between", value1, value2, "createuserid");
            return (Criteria) this;
        }

        public Criteria andCreateusernameIsNull() {
            addCriterion("CreateUserName is null");
            return (Criteria) this;
        }

        public Criteria andCreateusernameIsNotNull() {
            addCriterion("CreateUserName is not null");
            return (Criteria) this;
        }

        public Criteria andCreateusernameEqualTo(String value) {
            addCriterion("CreateUserName =", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameNotEqualTo(String value) {
            addCriterion("CreateUserName <>", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameGreaterThan(String value) {
            addCriterion("CreateUserName >", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameGreaterThanOrEqualTo(String value) {
            addCriterion("CreateUserName >=", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameLessThan(String value) {
            addCriterion("CreateUserName <", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameLessThanOrEqualTo(String value) {
            addCriterion("CreateUserName <=", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameLike(String value) {
            addCriterion("CreateUserName like", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameNotLike(String value) {
            addCriterion("CreateUserName not like", value, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameIn(List<String> values) {
            addCriterion("CreateUserName in", values, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameNotIn(List<String> values) {
            addCriterion("CreateUserName not in", values, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameBetween(String value1, String value2) {
            addCriterion("CreateUserName between", value1, value2, "createusername");
            return (Criteria) this;
        }

        public Criteria andCreateusernameNotBetween(String value1, String value2) {
            addCriterion("CreateUserName not between", value1, value2, "createusername");
            return (Criteria) this;
        }

        public Criteria andModifydateIsNull() {
            addCriterion("ModifyDate is null");
            return (Criteria) this;
        }

        public Criteria andModifydateIsNotNull() {
            addCriterion("ModifyDate is not null");
            return (Criteria) this;
        }

        public Criteria andModifydateEqualTo(Date value) {
            addCriterion("ModifyDate =", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotEqualTo(Date value) {
            addCriterion("ModifyDate <>", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateGreaterThan(Date value) {
            addCriterion("ModifyDate >", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateGreaterThanOrEqualTo(Date value) {
            addCriterion("ModifyDate >=", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateLessThan(Date value) {
            addCriterion("ModifyDate <", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateLessThanOrEqualTo(Date value) {
            addCriterion("ModifyDate <=", value, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateIn(List<Date> values) {
            addCriterion("ModifyDate in", values, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotIn(List<Date> values) {
            addCriterion("ModifyDate not in", values, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateBetween(Date value1, Date value2) {
            addCriterion("ModifyDate between", value1, value2, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifydateNotBetween(Date value1, Date value2) {
            addCriterion("ModifyDate not between", value1, value2, "modifydate");
            return (Criteria) this;
        }

        public Criteria andModifyuseridIsNull() {
            addCriterion("ModifyUserId is null");
            return (Criteria) this;
        }

        public Criteria andModifyuseridIsNotNull() {
            addCriterion("ModifyUserId is not null");
            return (Criteria) this;
        }

        public Criteria andModifyuseridEqualTo(String value) {
            addCriterion("ModifyUserId =", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridNotEqualTo(String value) {
            addCriterion("ModifyUserId <>", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridGreaterThan(String value) {
            addCriterion("ModifyUserId >", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridGreaterThanOrEqualTo(String value) {
            addCriterion("ModifyUserId >=", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridLessThan(String value) {
            addCriterion("ModifyUserId <", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridLessThanOrEqualTo(String value) {
            addCriterion("ModifyUserId <=", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridLike(String value) {
            addCriterion("ModifyUserId like", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridNotLike(String value) {
            addCriterion("ModifyUserId not like", value, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridIn(List<String> values) {
            addCriterion("ModifyUserId in", values, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridNotIn(List<String> values) {
            addCriterion("ModifyUserId not in", values, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridBetween(String value1, String value2) {
            addCriterion("ModifyUserId between", value1, value2, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyuseridNotBetween(String value1, String value2) {
            addCriterion("ModifyUserId not between", value1, value2, "modifyuserid");
            return (Criteria) this;
        }

        public Criteria andModifyusernameIsNull() {
            addCriterion("ModifyUserName is null");
            return (Criteria) this;
        }

        public Criteria andModifyusernameIsNotNull() {
            addCriterion("ModifyUserName is not null");
            return (Criteria) this;
        }

        public Criteria andModifyusernameEqualTo(String value) {
            addCriterion("ModifyUserName =", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameNotEqualTo(String value) {
            addCriterion("ModifyUserName <>", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameGreaterThan(String value) {
            addCriterion("ModifyUserName >", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameGreaterThanOrEqualTo(String value) {
            addCriterion("ModifyUserName >=", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameLessThan(String value) {
            addCriterion("ModifyUserName <", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameLessThanOrEqualTo(String value) {
            addCriterion("ModifyUserName <=", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameLike(String value) {
            addCriterion("ModifyUserName like", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameNotLike(String value) {
            addCriterion("ModifyUserName not like", value, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameIn(List<String> values) {
            addCriterion("ModifyUserName in", values, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameNotIn(List<String> values) {
            addCriterion("ModifyUserName not in", values, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameBetween(String value1, String value2) {
            addCriterion("ModifyUserName between", value1, value2, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andModifyusernameNotBetween(String value1, String value2) {
            addCriterion("ModifyUserName not between", value1, value2, "modifyusername");
            return (Criteria) this;
        }

        public Criteria andUaIsNull() {
            addCriterion("Ua is null");
            return (Criteria) this;
        }

        public Criteria andUaIsNotNull() {
            addCriterion("Ua is not null");
            return (Criteria) this;
        }

        public Criteria andUaEqualTo(Double value) {
            addCriterion("Ua =", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotEqualTo(Double value) {
            addCriterion("Ua <>", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaGreaterThan(Double value) {
            addCriterion("Ua >", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaGreaterThanOrEqualTo(Double value) {
            addCriterion("Ua >=", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaLessThan(Double value) {
            addCriterion("Ua <", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaLessThanOrEqualTo(Double value) {
            addCriterion("Ua <=", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaIn(List<Double> values) {
            addCriterion("Ua in", values, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotIn(List<Double> values) {
            addCriterion("Ua not in", values, "ua");
            return (Criteria) this;
        }

        public Criteria andUaBetween(Double value1, Double value2) {
            addCriterion("Ua between", value1, value2, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotBetween(Double value1, Double value2) {
            addCriterion("Ua not between", value1, value2, "ua");
            return (Criteria) this;
        }

        public Criteria andUbIsNull() {
            addCriterion("Ub is null");
            return (Criteria) this;
        }

        public Criteria andUbIsNotNull() {
            addCriterion("Ub is not null");
            return (Criteria) this;
        }

        public Criteria andUbEqualTo(Double value) {
            addCriterion("Ub =", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbNotEqualTo(Double value) {
            addCriterion("Ub <>", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbGreaterThan(Double value) {
            addCriterion("Ub >", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbGreaterThanOrEqualTo(Double value) {
            addCriterion("Ub >=", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbLessThan(Double value) {
            addCriterion("Ub <", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbLessThanOrEqualTo(Double value) {
            addCriterion("Ub <=", value, "ub");
            return (Criteria) this;
        }

        public Criteria andUbIn(List<Double> values) {
            addCriterion("Ub in", values, "ub");
            return (Criteria) this;
        }

        public Criteria andUbNotIn(List<Double> values) {
            addCriterion("Ub not in", values, "ub");
            return (Criteria) this;
        }

        public Criteria andUbBetween(Double value1, Double value2) {
            addCriterion("Ub between", value1, value2, "ub");
            return (Criteria) this;
        }

        public Criteria andUbNotBetween(Double value1, Double value2) {
            addCriterion("Ub not between", value1, value2, "ub");
            return (Criteria) this;
        }

        public Criteria andUcIsNull() {
            addCriterion("Uc is null");
            return (Criteria) this;
        }

        public Criteria andUcIsNotNull() {
            addCriterion("Uc is not null");
            return (Criteria) this;
        }

        public Criteria andUcEqualTo(Double value) {
            addCriterion("Uc =", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotEqualTo(Double value) {
            addCriterion("Uc <>", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcGreaterThan(Double value) {
            addCriterion("Uc >", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcGreaterThanOrEqualTo(Double value) {
            addCriterion("Uc >=", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcLessThan(Double value) {
            addCriterion("Uc <", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcLessThanOrEqualTo(Double value) {
            addCriterion("Uc <=", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcIn(List<Double> values) {
            addCriterion("Uc in", values, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotIn(List<Double> values) {
            addCriterion("Uc not in", values, "uc");
            return (Criteria) this;
        }

        public Criteria andUcBetween(Double value1, Double value2) {
            addCriterion("Uc between", value1, value2, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotBetween(Double value1, Double value2) {
            addCriterion("Uc not between", value1, value2, "uc");
            return (Criteria) this;
        }

        public Criteria andIaIsNull() {
            addCriterion("Ia is null");
            return (Criteria) this;
        }

        public Criteria andIaIsNotNull() {
            addCriterion("Ia is not null");
            return (Criteria) this;
        }

        public Criteria andIaEqualTo(Double value) {
            addCriterion("Ia =", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaNotEqualTo(Double value) {
            addCriterion("Ia <>", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaGreaterThan(Double value) {
            addCriterion("Ia >", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaGreaterThanOrEqualTo(Double value) {
            addCriterion("Ia >=", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaLessThan(Double value) {
            addCriterion("Ia <", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaLessThanOrEqualTo(Double value) {
            addCriterion("Ia <=", value, "ia");
            return (Criteria) this;
        }

        public Criteria andIaIn(List<Double> values) {
            addCriterion("Ia in", values, "ia");
            return (Criteria) this;
        }

        public Criteria andIaNotIn(List<Double> values) {
            addCriterion("Ia not in", values, "ia");
            return (Criteria) this;
        }

        public Criteria andIaBetween(Double value1, Double value2) {
            addCriterion("Ia between", value1, value2, "ia");
            return (Criteria) this;
        }

        public Criteria andIaNotBetween(Double value1, Double value2) {
            addCriterion("Ia not between", value1, value2, "ia");
            return (Criteria) this;
        }

        public Criteria andIbIsNull() {
            addCriterion("Ib is null");
            return (Criteria) this;
        }

        public Criteria andIbIsNotNull() {
            addCriterion("Ib is not null");
            return (Criteria) this;
        }

        public Criteria andIbEqualTo(Double value) {
            addCriterion("Ib =", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbNotEqualTo(Double value) {
            addCriterion("Ib <>", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbGreaterThan(Double value) {
            addCriterion("Ib >", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbGreaterThanOrEqualTo(Double value) {
            addCriterion("Ib >=", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbLessThan(Double value) {
            addCriterion("Ib <", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbLessThanOrEqualTo(Double value) {
            addCriterion("Ib <=", value, "ib");
            return (Criteria) this;
        }

        public Criteria andIbIn(List<Double> values) {
            addCriterion("Ib in", values, "ib");
            return (Criteria) this;
        }

        public Criteria andIbNotIn(List<Double> values) {
            addCriterion("Ib not in", values, "ib");
            return (Criteria) this;
        }

        public Criteria andIbBetween(Double value1, Double value2) {
            addCriterion("Ib between", value1, value2, "ib");
            return (Criteria) this;
        }

        public Criteria andIbNotBetween(Double value1, Double value2) {
            addCriterion("Ib not between", value1, value2, "ib");
            return (Criteria) this;
        }

        public Criteria andIcIsNull() {
            addCriterion("Ic is null");
            return (Criteria) this;
        }

        public Criteria andIcIsNotNull() {
            addCriterion("Ic is not null");
            return (Criteria) this;
        }

        public Criteria andIcEqualTo(Double value) {
            addCriterion("Ic =", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcNotEqualTo(Double value) {
            addCriterion("Ic <>", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcGreaterThan(Double value) {
            addCriterion("Ic >", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcGreaterThanOrEqualTo(Double value) {
            addCriterion("Ic >=", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcLessThan(Double value) {
            addCriterion("Ic <", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcLessThanOrEqualTo(Double value) {
            addCriterion("Ic <=", value, "ic");
            return (Criteria) this;
        }

        public Criteria andIcIn(List<Double> values) {
            addCriterion("Ic in", values, "ic");
            return (Criteria) this;
        }

        public Criteria andIcNotIn(List<Double> values) {
            addCriterion("Ic not in", values, "ic");
            return (Criteria) this;
        }

        public Criteria andIcBetween(Double value1, Double value2) {
            addCriterion("Ic between", value1, value2, "ic");
            return (Criteria) this;
        }

        public Criteria andIcNotBetween(Double value1, Double value2) {
            addCriterion("Ic not between", value1, value2, "ic");
            return (Criteria) this;
        }

        public Criteria andIoIsNull() {
            addCriterion("Io is null");
            return (Criteria) this;
        }

        public Criteria andIoIsNotNull() {
            addCriterion("Io is not null");
            return (Criteria) this;
        }

        public Criteria andIoEqualTo(Double value) {
            addCriterion("Io =", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoNotEqualTo(Double value) {
            addCriterion("Io <>", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoGreaterThan(Double value) {
            addCriterion("Io >", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoGreaterThanOrEqualTo(Double value) {
            addCriterion("Io >=", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoLessThan(Double value) {
            addCriterion("Io <", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoLessThanOrEqualTo(Double value) {
            addCriterion("Io <=", value, "io");
            return (Criteria) this;
        }

        public Criteria andIoIn(List<Double> values) {
            addCriterion("Io in", values, "io");
            return (Criteria) this;
        }

        public Criteria andIoNotIn(List<Double> values) {
            addCriterion("Io not in", values, "io");
            return (Criteria) this;
        }

        public Criteria andIoBetween(Double value1, Double value2) {
            addCriterion("Io between", value1, value2, "io");
            return (Criteria) this;
        }

        public Criteria andIoNotBetween(Double value1, Double value2) {
            addCriterion("Io not between", value1, value2, "io");
            return (Criteria) this;
        }

        public Criteria andDtuidIsNull() {
            addCriterion("DtuId is null");
            return (Criteria) this;
        }

        public Criteria andDtuidIsNotNull() {
            addCriterion("DtuId is not null");
            return (Criteria) this;
        }

        public Criteria andDtuidEqualTo(String value) {
            addCriterion("DtuId =", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidNotEqualTo(String value) {
            addCriterion("DtuId <>", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidGreaterThan(String value) {
            addCriterion("DtuId >", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidGreaterThanOrEqualTo(String value) {
            addCriterion("DtuId >=", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidLessThan(String value) {
            addCriterion("DtuId <", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidLessThanOrEqualTo(String value) {
            addCriterion("DtuId <=", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidLike(String value) {
            addCriterion("DtuId like", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidNotLike(String value) {
            addCriterion("DtuId not like", value, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidIn(List<String> values) {
            addCriterion("DtuId in", values, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidNotIn(List<String> values) {
            addCriterion("DtuId not in", values, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidBetween(String value1, String value2) {
            addCriterion("DtuId between", value1, value2, "dtuid");
            return (Criteria) this;
        }

        public Criteria andDtuidNotBetween(String value1, String value2) {
            addCriterion("DtuId not between", value1, value2, "dtuid");
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