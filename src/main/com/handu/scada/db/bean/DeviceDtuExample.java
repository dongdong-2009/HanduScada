package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceDtuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceDtuExample() {
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

        public Criteria andDepartmentidIsNull() {
            addCriterion("DepartmentId is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIsNotNull() {
            addCriterion("DepartmentId is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidEqualTo(String value) {
            addCriterion("DepartmentId =", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotEqualTo(String value) {
            addCriterion("DepartmentId <>", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThan(String value) {
            addCriterion("DepartmentId >", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThanOrEqualTo(String value) {
            addCriterion("DepartmentId >=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThan(String value) {
            addCriterion("DepartmentId <", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThanOrEqualTo(String value) {
            addCriterion("DepartmentId <=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLike(String value) {
            addCriterion("DepartmentId like", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotLike(String value) {
            addCriterion("DepartmentId not like", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIn(List<String> values) {
            addCriterion("DepartmentId in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotIn(List<String> values) {
            addCriterion("DepartmentId not in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidBetween(String value1, String value2) {
            addCriterion("DepartmentId between", value1, value2, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotBetween(String value1, String value2) {
            addCriterion("DepartmentId not between", value1, value2, "departmentid");
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

        public Criteria andPhonenumIsNull() {
            addCriterion("PhoneNum is null");
            return (Criteria) this;
        }

        public Criteria andPhonenumIsNotNull() {
            addCriterion("PhoneNum is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenumEqualTo(String value) {
            addCriterion("PhoneNum =", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotEqualTo(String value) {
            addCriterion("PhoneNum <>", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumGreaterThan(String value) {
            addCriterion("PhoneNum >", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumGreaterThanOrEqualTo(String value) {
            addCriterion("PhoneNum >=", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLessThan(String value) {
            addCriterion("PhoneNum <", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLessThanOrEqualTo(String value) {
            addCriterion("PhoneNum <=", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLike(String value) {
            addCriterion("PhoneNum like", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotLike(String value) {
            addCriterion("PhoneNum not like", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumIn(List<String> values) {
            addCriterion("PhoneNum in", values, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotIn(List<String> values) {
            addCriterion("PhoneNum not in", values, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumBetween(String value1, String value2) {
            addCriterion("PhoneNum between", value1, value2, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotBetween(String value1, String value2) {
            addCriterion("PhoneNum not between", value1, value2, "phonenum");
            return (Criteria) this;
        }

        public Criteria andIpaddressIsNull() {
            addCriterion("IPAddress is null");
            return (Criteria) this;
        }

        public Criteria andIpaddressIsNotNull() {
            addCriterion("IPAddress is not null");
            return (Criteria) this;
        }

        public Criteria andIpaddressEqualTo(String value) {
            addCriterion("IPAddress =", value, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressNotEqualTo(String value) {
            addCriterion("IPAddress <>", value, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressGreaterThan(String value) {
            addCriterion("IPAddress >", value, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressGreaterThanOrEqualTo(String value) {
            addCriterion("IPAddress >=", value, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressLessThan(String value) {
            addCriterion("IPAddress <", value, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressLessThanOrEqualTo(String value) {
            addCriterion("IPAddress <=", value, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressLike(String value) {
            addCriterion("IPAddress like", value, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressNotLike(String value) {
            addCriterion("IPAddress not like", value, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressIn(List<String> values) {
            addCriterion("IPAddress in", values, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressNotIn(List<String> values) {
            addCriterion("IPAddress not in", values, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressBetween(String value1, String value2) {
            addCriterion("IPAddress between", value1, value2, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andIpaddressNotBetween(String value1, String value2) {
            addCriterion("IPAddress not between", value1, value2, "ipaddress");
            return (Criteria) this;
        }

        public Criteria andBackipIsNull() {
            addCriterion("BackIP is null");
            return (Criteria) this;
        }

        public Criteria andBackipIsNotNull() {
            addCriterion("BackIP is not null");
            return (Criteria) this;
        }

        public Criteria andBackipEqualTo(String value) {
            addCriterion("BackIP =", value, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipNotEqualTo(String value) {
            addCriterion("BackIP <>", value, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipGreaterThan(String value) {
            addCriterion("BackIP >", value, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipGreaterThanOrEqualTo(String value) {
            addCriterion("BackIP >=", value, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipLessThan(String value) {
            addCriterion("BackIP <", value, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipLessThanOrEqualTo(String value) {
            addCriterion("BackIP <=", value, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipLike(String value) {
            addCriterion("BackIP like", value, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipNotLike(String value) {
            addCriterion("BackIP not like", value, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipIn(List<String> values) {
            addCriterion("BackIP in", values, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipNotIn(List<String> values) {
            addCriterion("BackIP not in", values, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipBetween(String value1, String value2) {
            addCriterion("BackIP between", value1, value2, "backip");
            return (Criteria) this;
        }

        public Criteria andBackipNotBetween(String value1, String value2) {
            addCriterion("BackIP not between", value1, value2, "backip");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("PORT is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("PORT is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("PORT =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("PORT <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("PORT >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("PORT >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("PORT <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("PORT <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("PORT in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("PORT not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("PORT between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("PORT not between", value1, value2, "port");
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

        public Criteria andOrganizeidIsNull() {
            addCriterion("OrganizeId is null");
            return (Criteria) this;
        }

        public Criteria andOrganizeidIsNotNull() {
            addCriterion("OrganizeId is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizeidEqualTo(String value) {
            addCriterion("OrganizeId =", value, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidNotEqualTo(String value) {
            addCriterion("OrganizeId <>", value, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidGreaterThan(String value) {
            addCriterion("OrganizeId >", value, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidGreaterThanOrEqualTo(String value) {
            addCriterion("OrganizeId >=", value, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidLessThan(String value) {
            addCriterion("OrganizeId <", value, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidLessThanOrEqualTo(String value) {
            addCriterion("OrganizeId <=", value, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidLike(String value) {
            addCriterion("OrganizeId like", value, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidNotLike(String value) {
            addCriterion("OrganizeId not like", value, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidIn(List<String> values) {
            addCriterion("OrganizeId in", values, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidNotIn(List<String> values) {
            addCriterion("OrganizeId not in", values, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidBetween(String value1, String value2) {
            addCriterion("OrganizeId between", value1, value2, "organizeid");
            return (Criteria) this;
        }

        public Criteria andOrganizeidNotBetween(String value1, String value2) {
            addCriterion("OrganizeId not between", value1, value2, "organizeid");
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

        public Criteria andDtumodelIsNull() {
            addCriterion("DtuModel is null");
            return (Criteria) this;
        }

        public Criteria andDtumodelIsNotNull() {
            addCriterion("DtuModel is not null");
            return (Criteria) this;
        }

        public Criteria andDtumodelEqualTo(String value) {
            addCriterion("DtuModel =", value, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelNotEqualTo(String value) {
            addCriterion("DtuModel <>", value, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelGreaterThan(String value) {
            addCriterion("DtuModel >", value, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelGreaterThanOrEqualTo(String value) {
            addCriterion("DtuModel >=", value, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelLessThan(String value) {
            addCriterion("DtuModel <", value, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelLessThanOrEqualTo(String value) {
            addCriterion("DtuModel <=", value, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelLike(String value) {
            addCriterion("DtuModel like", value, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelNotLike(String value) {
            addCriterion("DtuModel not like", value, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelIn(List<String> values) {
            addCriterion("DtuModel in", values, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelNotIn(List<String> values) {
            addCriterion("DtuModel not in", values, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelBetween(String value1, String value2) {
            addCriterion("DtuModel between", value1, value2, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andDtumodelNotBetween(String value1, String value2) {
            addCriterion("DtuModel not between", value1, value2, "dtumodel");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorIsNull() {
            addCriterion("TelecomOperator is null");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorIsNotNull() {
            addCriterion("TelecomOperator is not null");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorEqualTo(String value) {
            addCriterion("TelecomOperator =", value, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorNotEqualTo(String value) {
            addCriterion("TelecomOperator <>", value, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorGreaterThan(String value) {
            addCriterion("TelecomOperator >", value, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorGreaterThanOrEqualTo(String value) {
            addCriterion("TelecomOperator >=", value, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorLessThan(String value) {
            addCriterion("TelecomOperator <", value, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorLessThanOrEqualTo(String value) {
            addCriterion("TelecomOperator <=", value, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorLike(String value) {
            addCriterion("TelecomOperator like", value, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorNotLike(String value) {
            addCriterion("TelecomOperator not like", value, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorIn(List<String> values) {
            addCriterion("TelecomOperator in", values, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorNotIn(List<String> values) {
            addCriterion("TelecomOperator not in", values, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorBetween(String value1, String value2) {
            addCriterion("TelecomOperator between", value1, value2, "telecomoperator");
            return (Criteria) this;
        }

        public Criteria andTelecomoperatorNotBetween(String value1, String value2) {
            addCriterion("TelecomOperator not between", value1, value2, "telecomoperator");
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