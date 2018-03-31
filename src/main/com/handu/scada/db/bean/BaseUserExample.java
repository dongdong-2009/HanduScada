package main.com.handu.scada.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseUserExample() {
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

        public Criteria andUseridIsNull() {
            addCriterion("UserId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("UserId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("UserId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("UserId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("UserId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("UserId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("UserId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("UserId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("UserId like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("UserId not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("UserId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("UserId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("UserId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("UserId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andEncodeIsNull() {
            addCriterion("EnCode is null");
            return (Criteria) this;
        }

        public Criteria andEncodeIsNotNull() {
            addCriterion("EnCode is not null");
            return (Criteria) this;
        }

        public Criteria andEncodeEqualTo(String value) {
            addCriterion("EnCode =", value, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeNotEqualTo(String value) {
            addCriterion("EnCode <>", value, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeGreaterThan(String value) {
            addCriterion("EnCode >", value, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeGreaterThanOrEqualTo(String value) {
            addCriterion("EnCode >=", value, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeLessThan(String value) {
            addCriterion("EnCode <", value, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeLessThanOrEqualTo(String value) {
            addCriterion("EnCode <=", value, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeLike(String value) {
            addCriterion("EnCode like", value, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeNotLike(String value) {
            addCriterion("EnCode not like", value, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeIn(List<String> values) {
            addCriterion("EnCode in", values, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeNotIn(List<String> values) {
            addCriterion("EnCode not in", values, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeBetween(String value1, String value2) {
            addCriterion("EnCode between", value1, value2, "encode");
            return (Criteria) this;
        }

        public Criteria andEncodeNotBetween(String value1, String value2) {
            addCriterion("EnCode not between", value1, value2, "encode");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("Account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("Account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("Account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("Account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("Account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("Account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("Account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("Account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("Account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("Account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("Account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("Account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("Account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("Account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("Password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("Password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("Password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("Password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("Password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("Password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("Password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("Password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("Password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("Password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("Password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("Password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("Password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("Password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andSecretkeyIsNull() {
            addCriterion("Secretkey is null");
            return (Criteria) this;
        }

        public Criteria andSecretkeyIsNotNull() {
            addCriterion("Secretkey is not null");
            return (Criteria) this;
        }

        public Criteria andSecretkeyEqualTo(String value) {
            addCriterion("Secretkey =", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyNotEqualTo(String value) {
            addCriterion("Secretkey <>", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyGreaterThan(String value) {
            addCriterion("Secretkey >", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyGreaterThanOrEqualTo(String value) {
            addCriterion("Secretkey >=", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyLessThan(String value) {
            addCriterion("Secretkey <", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyLessThanOrEqualTo(String value) {
            addCriterion("Secretkey <=", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyLike(String value) {
            addCriterion("Secretkey like", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyNotLike(String value) {
            addCriterion("Secretkey not like", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyIn(List<String> values) {
            addCriterion("Secretkey in", values, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyNotIn(List<String> values) {
            addCriterion("Secretkey not in", values, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyBetween(String value1, String value2) {
            addCriterion("Secretkey between", value1, value2, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyNotBetween(String value1, String value2) {
            addCriterion("Secretkey not between", value1, value2, "secretkey");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("RealName is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("RealName is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("RealName =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("RealName <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("RealName >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("RealName >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("RealName <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("RealName <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("RealName like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("RealName not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("RealName in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("RealName not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("RealName between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("RealName not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("NickName is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("NickName is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("NickName =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("NickName <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("NickName >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("NickName >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("NickName <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("NickName <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("NickName like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("NickName not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("NickName in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("NickName not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("NickName between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("NickName not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andHeadiconIsNull() {
            addCriterion("HeadIcon is null");
            return (Criteria) this;
        }

        public Criteria andHeadiconIsNotNull() {
            addCriterion("HeadIcon is not null");
            return (Criteria) this;
        }

        public Criteria andHeadiconEqualTo(String value) {
            addCriterion("HeadIcon =", value, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconNotEqualTo(String value) {
            addCriterion("HeadIcon <>", value, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconGreaterThan(String value) {
            addCriterion("HeadIcon >", value, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconGreaterThanOrEqualTo(String value) {
            addCriterion("HeadIcon >=", value, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconLessThan(String value) {
            addCriterion("HeadIcon <", value, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconLessThanOrEqualTo(String value) {
            addCriterion("HeadIcon <=", value, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconLike(String value) {
            addCriterion("HeadIcon like", value, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconNotLike(String value) {
            addCriterion("HeadIcon not like", value, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconIn(List<String> values) {
            addCriterion("HeadIcon in", values, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconNotIn(List<String> values) {
            addCriterion("HeadIcon not in", values, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconBetween(String value1, String value2) {
            addCriterion("HeadIcon between", value1, value2, "headicon");
            return (Criteria) this;
        }

        public Criteria andHeadiconNotBetween(String value1, String value2) {
            addCriterion("HeadIcon not between", value1, value2, "headicon");
            return (Criteria) this;
        }

        public Criteria andQuickqueryIsNull() {
            addCriterion("QuickQuery is null");
            return (Criteria) this;
        }

        public Criteria andQuickqueryIsNotNull() {
            addCriterion("QuickQuery is not null");
            return (Criteria) this;
        }

        public Criteria andQuickqueryEqualTo(String value) {
            addCriterion("QuickQuery =", value, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryNotEqualTo(String value) {
            addCriterion("QuickQuery <>", value, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryGreaterThan(String value) {
            addCriterion("QuickQuery >", value, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryGreaterThanOrEqualTo(String value) {
            addCriterion("QuickQuery >=", value, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryLessThan(String value) {
            addCriterion("QuickQuery <", value, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryLessThanOrEqualTo(String value) {
            addCriterion("QuickQuery <=", value, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryLike(String value) {
            addCriterion("QuickQuery like", value, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryNotLike(String value) {
            addCriterion("QuickQuery not like", value, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryIn(List<String> values) {
            addCriterion("QuickQuery in", values, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryNotIn(List<String> values) {
            addCriterion("QuickQuery not in", values, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryBetween(String value1, String value2) {
            addCriterion("QuickQuery between", value1, value2, "quickquery");
            return (Criteria) this;
        }

        public Criteria andQuickqueryNotBetween(String value1, String value2) {
            addCriterion("QuickQuery not between", value1, value2, "quickquery");
            return (Criteria) this;
        }

        public Criteria andSimplespellingIsNull() {
            addCriterion("SimpleSpelling is null");
            return (Criteria) this;
        }

        public Criteria andSimplespellingIsNotNull() {
            addCriterion("SimpleSpelling is not null");
            return (Criteria) this;
        }

        public Criteria andSimplespellingEqualTo(String value) {
            addCriterion("SimpleSpelling =", value, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingNotEqualTo(String value) {
            addCriterion("SimpleSpelling <>", value, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingGreaterThan(String value) {
            addCriterion("SimpleSpelling >", value, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingGreaterThanOrEqualTo(String value) {
            addCriterion("SimpleSpelling >=", value, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingLessThan(String value) {
            addCriterion("SimpleSpelling <", value, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingLessThanOrEqualTo(String value) {
            addCriterion("SimpleSpelling <=", value, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingLike(String value) {
            addCriterion("SimpleSpelling like", value, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingNotLike(String value) {
            addCriterion("SimpleSpelling not like", value, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingIn(List<String> values) {
            addCriterion("SimpleSpelling in", values, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingNotIn(List<String> values) {
            addCriterion("SimpleSpelling not in", values, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingBetween(String value1, String value2) {
            addCriterion("SimpleSpelling between", value1, value2, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andSimplespellingNotBetween(String value1, String value2) {
            addCriterion("SimpleSpelling not between", value1, value2, "simplespelling");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("Gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("Gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Integer value) {
            addCriterion("Gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Integer value) {
            addCriterion("Gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Integer value) {
            addCriterion("Gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("Gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Integer value) {
            addCriterion("Gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Integer value) {
            addCriterion("Gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Integer> values) {
            addCriterion("Gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Integer> values) {
            addCriterion("Gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Integer value1, Integer value2) {
            addCriterion("Gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("Gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("Birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("Birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterion("Birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("Birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("Birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("Birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("Birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("Birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("Birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("Birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("Birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("Birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("Mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("Mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("Mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("Mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("Mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("Mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("Mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("Mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("Mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("Mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("Mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("Mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("Mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("Mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("Telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("Telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("Telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("Telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("Telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("Telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("Telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("Telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("Telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("Telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("Telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("Telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("Telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("Telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("Email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("Email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("Email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("Email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("Email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("Email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("Email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("Email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("Email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("Email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("Email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("Email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("Email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("Email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andOicqIsNull() {
            addCriterion("OICQ is null");
            return (Criteria) this;
        }

        public Criteria andOicqIsNotNull() {
            addCriterion("OICQ is not null");
            return (Criteria) this;
        }

        public Criteria andOicqEqualTo(String value) {
            addCriterion("OICQ =", value, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqNotEqualTo(String value) {
            addCriterion("OICQ <>", value, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqGreaterThan(String value) {
            addCriterion("OICQ >", value, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqGreaterThanOrEqualTo(String value) {
            addCriterion("OICQ >=", value, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqLessThan(String value) {
            addCriterion("OICQ <", value, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqLessThanOrEqualTo(String value) {
            addCriterion("OICQ <=", value, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqLike(String value) {
            addCriterion("OICQ like", value, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqNotLike(String value) {
            addCriterion("OICQ not like", value, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqIn(List<String> values) {
            addCriterion("OICQ in", values, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqNotIn(List<String> values) {
            addCriterion("OICQ not in", values, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqBetween(String value1, String value2) {
            addCriterion("OICQ between", value1, value2, "oicq");
            return (Criteria) this;
        }

        public Criteria andOicqNotBetween(String value1, String value2) {
            addCriterion("OICQ not between", value1, value2, "oicq");
            return (Criteria) this;
        }

        public Criteria andWechatIsNull() {
            addCriterion("WeChat is null");
            return (Criteria) this;
        }

        public Criteria andWechatIsNotNull() {
            addCriterion("WeChat is not null");
            return (Criteria) this;
        }

        public Criteria andWechatEqualTo(String value) {
            addCriterion("WeChat =", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotEqualTo(String value) {
            addCriterion("WeChat <>", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThan(String value) {
            addCriterion("WeChat >", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThanOrEqualTo(String value) {
            addCriterion("WeChat >=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThan(String value) {
            addCriterion("WeChat <", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThanOrEqualTo(String value) {
            addCriterion("WeChat <=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLike(String value) {
            addCriterion("WeChat like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotLike(String value) {
            addCriterion("WeChat not like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatIn(List<String> values) {
            addCriterion("WeChat in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotIn(List<String> values) {
            addCriterion("WeChat not in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatBetween(String value1, String value2) {
            addCriterion("WeChat between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotBetween(String value1, String value2) {
            addCriterion("WeChat not between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andMsnIsNull() {
            addCriterion("MSN is null");
            return (Criteria) this;
        }

        public Criteria andMsnIsNotNull() {
            addCriterion("MSN is not null");
            return (Criteria) this;
        }

        public Criteria andMsnEqualTo(String value) {
            addCriterion("MSN =", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnNotEqualTo(String value) {
            addCriterion("MSN <>", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnGreaterThan(String value) {
            addCriterion("MSN >", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnGreaterThanOrEqualTo(String value) {
            addCriterion("MSN >=", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnLessThan(String value) {
            addCriterion("MSN <", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnLessThanOrEqualTo(String value) {
            addCriterion("MSN <=", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnLike(String value) {
            addCriterion("MSN like", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnNotLike(String value) {
            addCriterion("MSN not like", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnIn(List<String> values) {
            addCriterion("MSN in", values, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnNotIn(List<String> values) {
            addCriterion("MSN not in", values, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnBetween(String value1, String value2) {
            addCriterion("MSN between", value1, value2, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnNotBetween(String value1, String value2) {
            addCriterion("MSN not between", value1, value2, "msn");
            return (Criteria) this;
        }

        public Criteria andManageridIsNull() {
            addCriterion("ManagerId is null");
            return (Criteria) this;
        }

        public Criteria andManageridIsNotNull() {
            addCriterion("ManagerId is not null");
            return (Criteria) this;
        }

        public Criteria andManageridEqualTo(String value) {
            addCriterion("ManagerId =", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridNotEqualTo(String value) {
            addCriterion("ManagerId <>", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridGreaterThan(String value) {
            addCriterion("ManagerId >", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridGreaterThanOrEqualTo(String value) {
            addCriterion("ManagerId >=", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridLessThan(String value) {
            addCriterion("ManagerId <", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridLessThanOrEqualTo(String value) {
            addCriterion("ManagerId <=", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridLike(String value) {
            addCriterion("ManagerId like", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridNotLike(String value) {
            addCriterion("ManagerId not like", value, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridIn(List<String> values) {
            addCriterion("ManagerId in", values, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridNotIn(List<String> values) {
            addCriterion("ManagerId not in", values, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridBetween(String value1, String value2) {
            addCriterion("ManagerId between", value1, value2, "managerid");
            return (Criteria) this;
        }

        public Criteria andManageridNotBetween(String value1, String value2) {
            addCriterion("ManagerId not between", value1, value2, "managerid");
            return (Criteria) this;
        }

        public Criteria andManagerIsNull() {
            addCriterion("Manager is null");
            return (Criteria) this;
        }

        public Criteria andManagerIsNotNull() {
            addCriterion("Manager is not null");
            return (Criteria) this;
        }

        public Criteria andManagerEqualTo(String value) {
            addCriterion("Manager =", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotEqualTo(String value) {
            addCriterion("Manager <>", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThan(String value) {
            addCriterion("Manager >", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerGreaterThanOrEqualTo(String value) {
            addCriterion("Manager >=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThan(String value) {
            addCriterion("Manager <", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLessThanOrEqualTo(String value) {
            addCriterion("Manager <=", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerLike(String value) {
            addCriterion("Manager like", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotLike(String value) {
            addCriterion("Manager not like", value, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerIn(List<String> values) {
            addCriterion("Manager in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotIn(List<String> values) {
            addCriterion("Manager not in", values, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerBetween(String value1, String value2) {
            addCriterion("Manager between", value1, value2, "manager");
            return (Criteria) this;
        }

        public Criteria andManagerNotBetween(String value1, String value2) {
            addCriterion("Manager not between", value1, value2, "manager");
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

        public Criteria andRoleidIsNull() {
            addCriterion("RoleId is null");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("RoleId is not null");
            return (Criteria) this;
        }

        public Criteria andRoleidEqualTo(String value) {
            addCriterion("RoleId =", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotEqualTo(String value) {
            addCriterion("RoleId <>", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThan(String value) {
            addCriterion("RoleId >", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(String value) {
            addCriterion("RoleId >=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThan(String value) {
            addCriterion("RoleId <", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThanOrEqualTo(String value) {
            addCriterion("RoleId <=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLike(String value) {
            addCriterion("RoleId like", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotLike(String value) {
            addCriterion("RoleId not like", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidIn(List<String> values) {
            addCriterion("RoleId in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotIn(List<String> values) {
            addCriterion("RoleId not in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidBetween(String value1, String value2) {
            addCriterion("RoleId between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotBetween(String value1, String value2) {
            addCriterion("RoleId not between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andDutyidIsNull() {
            addCriterion("DutyId is null");
            return (Criteria) this;
        }

        public Criteria andDutyidIsNotNull() {
            addCriterion("DutyId is not null");
            return (Criteria) this;
        }

        public Criteria andDutyidEqualTo(String value) {
            addCriterion("DutyId =", value, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidNotEqualTo(String value) {
            addCriterion("DutyId <>", value, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidGreaterThan(String value) {
            addCriterion("DutyId >", value, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidGreaterThanOrEqualTo(String value) {
            addCriterion("DutyId >=", value, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidLessThan(String value) {
            addCriterion("DutyId <", value, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidLessThanOrEqualTo(String value) {
            addCriterion("DutyId <=", value, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidLike(String value) {
            addCriterion("DutyId like", value, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidNotLike(String value) {
            addCriterion("DutyId not like", value, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidIn(List<String> values) {
            addCriterion("DutyId in", values, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidNotIn(List<String> values) {
            addCriterion("DutyId not in", values, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidBetween(String value1, String value2) {
            addCriterion("DutyId between", value1, value2, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutyidNotBetween(String value1, String value2) {
            addCriterion("DutyId not between", value1, value2, "dutyid");
            return (Criteria) this;
        }

        public Criteria andDutynameIsNull() {
            addCriterion("DutyName is null");
            return (Criteria) this;
        }

        public Criteria andDutynameIsNotNull() {
            addCriterion("DutyName is not null");
            return (Criteria) this;
        }

        public Criteria andDutynameEqualTo(String value) {
            addCriterion("DutyName =", value, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameNotEqualTo(String value) {
            addCriterion("DutyName <>", value, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameGreaterThan(String value) {
            addCriterion("DutyName >", value, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameGreaterThanOrEqualTo(String value) {
            addCriterion("DutyName >=", value, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameLessThan(String value) {
            addCriterion("DutyName <", value, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameLessThanOrEqualTo(String value) {
            addCriterion("DutyName <=", value, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameLike(String value) {
            addCriterion("DutyName like", value, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameNotLike(String value) {
            addCriterion("DutyName not like", value, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameIn(List<String> values) {
            addCriterion("DutyName in", values, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameNotIn(List<String> values) {
            addCriterion("DutyName not in", values, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameBetween(String value1, String value2) {
            addCriterion("DutyName between", value1, value2, "dutyname");
            return (Criteria) this;
        }

        public Criteria andDutynameNotBetween(String value1, String value2) {
            addCriterion("DutyName not between", value1, value2, "dutyname");
            return (Criteria) this;
        }

        public Criteria andPostidIsNull() {
            addCriterion("PostId is null");
            return (Criteria) this;
        }

        public Criteria andPostidIsNotNull() {
            addCriterion("PostId is not null");
            return (Criteria) this;
        }

        public Criteria andPostidEqualTo(String value) {
            addCriterion("PostId =", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotEqualTo(String value) {
            addCriterion("PostId <>", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidGreaterThan(String value) {
            addCriterion("PostId >", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidGreaterThanOrEqualTo(String value) {
            addCriterion("PostId >=", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidLessThan(String value) {
            addCriterion("PostId <", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidLessThanOrEqualTo(String value) {
            addCriterion("PostId <=", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidLike(String value) {
            addCriterion("PostId like", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotLike(String value) {
            addCriterion("PostId not like", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidIn(List<String> values) {
            addCriterion("PostId in", values, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotIn(List<String> values) {
            addCriterion("PostId not in", values, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidBetween(String value1, String value2) {
            addCriterion("PostId between", value1, value2, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotBetween(String value1, String value2) {
            addCriterion("PostId not between", value1, value2, "postid");
            return (Criteria) this;
        }

        public Criteria andPostnameIsNull() {
            addCriterion("PostName is null");
            return (Criteria) this;
        }

        public Criteria andPostnameIsNotNull() {
            addCriterion("PostName is not null");
            return (Criteria) this;
        }

        public Criteria andPostnameEqualTo(String value) {
            addCriterion("PostName =", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotEqualTo(String value) {
            addCriterion("PostName <>", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameGreaterThan(String value) {
            addCriterion("PostName >", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameGreaterThanOrEqualTo(String value) {
            addCriterion("PostName >=", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameLessThan(String value) {
            addCriterion("PostName <", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameLessThanOrEqualTo(String value) {
            addCriterion("PostName <=", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameLike(String value) {
            addCriterion("PostName like", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotLike(String value) {
            addCriterion("PostName not like", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameIn(List<String> values) {
            addCriterion("PostName in", values, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotIn(List<String> values) {
            addCriterion("PostName not in", values, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameBetween(String value1, String value2) {
            addCriterion("PostName between", value1, value2, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotBetween(String value1, String value2) {
            addCriterion("PostName not between", value1, value2, "postname");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidIsNull() {
            addCriterion("WorkGroupId is null");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidIsNotNull() {
            addCriterion("WorkGroupId is not null");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidEqualTo(String value) {
            addCriterion("WorkGroupId =", value, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidNotEqualTo(String value) {
            addCriterion("WorkGroupId <>", value, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidGreaterThan(String value) {
            addCriterion("WorkGroupId >", value, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidGreaterThanOrEqualTo(String value) {
            addCriterion("WorkGroupId >=", value, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidLessThan(String value) {
            addCriterion("WorkGroupId <", value, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidLessThanOrEqualTo(String value) {
            addCriterion("WorkGroupId <=", value, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidLike(String value) {
            addCriterion("WorkGroupId like", value, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidNotLike(String value) {
            addCriterion("WorkGroupId not like", value, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidIn(List<String> values) {
            addCriterion("WorkGroupId in", values, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidNotIn(List<String> values) {
            addCriterion("WorkGroupId not in", values, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidBetween(String value1, String value2) {
            addCriterion("WorkGroupId between", value1, value2, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andWorkgroupidNotBetween(String value1, String value2) {
            addCriterion("WorkGroupId not between", value1, value2, "workgroupid");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelIsNull() {
            addCriterion("SecurityLevel is null");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelIsNotNull() {
            addCriterion("SecurityLevel is not null");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelEqualTo(Integer value) {
            addCriterion("SecurityLevel =", value, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelNotEqualTo(Integer value) {
            addCriterion("SecurityLevel <>", value, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelGreaterThan(Integer value) {
            addCriterion("SecurityLevel >", value, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("SecurityLevel >=", value, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelLessThan(Integer value) {
            addCriterion("SecurityLevel <", value, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelLessThanOrEqualTo(Integer value) {
            addCriterion("SecurityLevel <=", value, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelIn(List<Integer> values) {
            addCriterion("SecurityLevel in", values, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelNotIn(List<Integer> values) {
            addCriterion("SecurityLevel not in", values, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelBetween(Integer value1, Integer value2) {
            addCriterion("SecurityLevel between", value1, value2, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andSecuritylevelNotBetween(Integer value1, Integer value2) {
            addCriterion("SecurityLevel not between", value1, value2, "securitylevel");
            return (Criteria) this;
        }

        public Criteria andUseronlineIsNull() {
            addCriterion("UserOnLine is null");
            return (Criteria) this;
        }

        public Criteria andUseronlineIsNotNull() {
            addCriterion("UserOnLine is not null");
            return (Criteria) this;
        }

        public Criteria andUseronlineEqualTo(Integer value) {
            addCriterion("UserOnLine =", value, "useronline");
            return (Criteria) this;
        }

        public Criteria andUseronlineNotEqualTo(Integer value) {
            addCriterion("UserOnLine <>", value, "useronline");
            return (Criteria) this;
        }

        public Criteria andUseronlineGreaterThan(Integer value) {
            addCriterion("UserOnLine >", value, "useronline");
            return (Criteria) this;
        }

        public Criteria andUseronlineGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserOnLine >=", value, "useronline");
            return (Criteria) this;
        }

        public Criteria andUseronlineLessThan(Integer value) {
            addCriterion("UserOnLine <", value, "useronline");
            return (Criteria) this;
        }

        public Criteria andUseronlineLessThanOrEqualTo(Integer value) {
            addCriterion("UserOnLine <=", value, "useronline");
            return (Criteria) this;
        }

        public Criteria andUseronlineIn(List<Integer> values) {
            addCriterion("UserOnLine in", values, "useronline");
            return (Criteria) this;
        }

        public Criteria andUseronlineNotIn(List<Integer> values) {
            addCriterion("UserOnLine not in", values, "useronline");
            return (Criteria) this;
        }

        public Criteria andUseronlineBetween(Integer value1, Integer value2) {
            addCriterion("UserOnLine between", value1, value2, "useronline");
            return (Criteria) this;
        }

        public Criteria andUseronlineNotBetween(Integer value1, Integer value2) {
            addCriterion("UserOnLine not between", value1, value2, "useronline");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("OpenId is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("OpenId is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(Integer value) {
            addCriterion("OpenId =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(Integer value) {
            addCriterion("OpenId <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(Integer value) {
            addCriterion("OpenId >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(Integer value) {
            addCriterion("OpenId >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(Integer value) {
            addCriterion("OpenId <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(Integer value) {
            addCriterion("OpenId <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<Integer> values) {
            addCriterion("OpenId in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<Integer> values) {
            addCriterion("OpenId not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(Integer value1, Integer value2) {
            addCriterion("OpenId between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(Integer value1, Integer value2) {
            addCriterion("OpenId not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andQuestionIsNull() {
            addCriterion("Question is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIsNotNull() {
            addCriterion("Question is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionEqualTo(String value) {
            addCriterion("Question =", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotEqualTo(String value) {
            addCriterion("Question <>", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThan(String value) {
            addCriterion("Question >", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("Question >=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThan(String value) {
            addCriterion("Question <", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThanOrEqualTo(String value) {
            addCriterion("Question <=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLike(String value) {
            addCriterion("Question like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotLike(String value) {
            addCriterion("Question not like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionIn(List<String> values) {
            addCriterion("Question in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotIn(List<String> values) {
            addCriterion("Question not in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionBetween(String value1, String value2) {
            addCriterion("Question between", value1, value2, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotBetween(String value1, String value2) {
            addCriterion("Question not between", value1, value2, "question");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionIsNull() {
            addCriterion("AnswerQuestion is null");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionIsNotNull() {
            addCriterion("AnswerQuestion is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionEqualTo(String value) {
            addCriterion("AnswerQuestion =", value, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionNotEqualTo(String value) {
            addCriterion("AnswerQuestion <>", value, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionGreaterThan(String value) {
            addCriterion("AnswerQuestion >", value, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionGreaterThanOrEqualTo(String value) {
            addCriterion("AnswerQuestion >=", value, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionLessThan(String value) {
            addCriterion("AnswerQuestion <", value, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionLessThanOrEqualTo(String value) {
            addCriterion("AnswerQuestion <=", value, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionLike(String value) {
            addCriterion("AnswerQuestion like", value, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionNotLike(String value) {
            addCriterion("AnswerQuestion not like", value, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionIn(List<String> values) {
            addCriterion("AnswerQuestion in", values, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionNotIn(List<String> values) {
            addCriterion("AnswerQuestion not in", values, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionBetween(String value1, String value2) {
            addCriterion("AnswerQuestion between", value1, value2, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andAnswerquestionNotBetween(String value1, String value2) {
            addCriterion("AnswerQuestion not between", value1, value2, "answerquestion");
            return (Criteria) this;
        }

        public Criteria andCheckonlineIsNull() {
            addCriterion("CheckOnLine is null");
            return (Criteria) this;
        }

        public Criteria andCheckonlineIsNotNull() {
            addCriterion("CheckOnLine is not null");
            return (Criteria) this;
        }

        public Criteria andCheckonlineEqualTo(Integer value) {
            addCriterion("CheckOnLine =", value, "checkonline");
            return (Criteria) this;
        }

        public Criteria andCheckonlineNotEqualTo(Integer value) {
            addCriterion("CheckOnLine <>", value, "checkonline");
            return (Criteria) this;
        }

        public Criteria andCheckonlineGreaterThan(Integer value) {
            addCriterion("CheckOnLine >", value, "checkonline");
            return (Criteria) this;
        }

        public Criteria andCheckonlineGreaterThanOrEqualTo(Integer value) {
            addCriterion("CheckOnLine >=", value, "checkonline");
            return (Criteria) this;
        }

        public Criteria andCheckonlineLessThan(Integer value) {
            addCriterion("CheckOnLine <", value, "checkonline");
            return (Criteria) this;
        }

        public Criteria andCheckonlineLessThanOrEqualTo(Integer value) {
            addCriterion("CheckOnLine <=", value, "checkonline");
            return (Criteria) this;
        }

        public Criteria andCheckonlineIn(List<Integer> values) {
            addCriterion("CheckOnLine in", values, "checkonline");
            return (Criteria) this;
        }

        public Criteria andCheckonlineNotIn(List<Integer> values) {
            addCriterion("CheckOnLine not in", values, "checkonline");
            return (Criteria) this;
        }

        public Criteria andCheckonlineBetween(Integer value1, Integer value2) {
            addCriterion("CheckOnLine between", value1, value2, "checkonline");
            return (Criteria) this;
        }

        public Criteria andCheckonlineNotBetween(Integer value1, Integer value2) {
            addCriterion("CheckOnLine not between", value1, value2, "checkonline");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeIsNull() {
            addCriterion("AllowStartTime is null");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeIsNotNull() {
            addCriterion("AllowStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeEqualTo(Date value) {
            addCriterion("AllowStartTime =", value, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeNotEqualTo(Date value) {
            addCriterion("AllowStartTime <>", value, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeGreaterThan(Date value) {
            addCriterion("AllowStartTime >", value, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AllowStartTime >=", value, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeLessThan(Date value) {
            addCriterion("AllowStartTime <", value, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeLessThanOrEqualTo(Date value) {
            addCriterion("AllowStartTime <=", value, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeIn(List<Date> values) {
            addCriterion("AllowStartTime in", values, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeNotIn(List<Date> values) {
            addCriterion("AllowStartTime not in", values, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeBetween(Date value1, Date value2) {
            addCriterion("AllowStartTime between", value1, value2, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowstarttimeNotBetween(Date value1, Date value2) {
            addCriterion("AllowStartTime not between", value1, value2, "allowstarttime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeIsNull() {
            addCriterion("AllowEndTime is null");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeIsNotNull() {
            addCriterion("AllowEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeEqualTo(Date value) {
            addCriterion("AllowEndTime =", value, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeNotEqualTo(Date value) {
            addCriterion("AllowEndTime <>", value, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeGreaterThan(Date value) {
            addCriterion("AllowEndTime >", value, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AllowEndTime >=", value, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeLessThan(Date value) {
            addCriterion("AllowEndTime <", value, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeLessThanOrEqualTo(Date value) {
            addCriterion("AllowEndTime <=", value, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeIn(List<Date> values) {
            addCriterion("AllowEndTime in", values, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeNotIn(List<Date> values) {
            addCriterion("AllowEndTime not in", values, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeBetween(Date value1, Date value2) {
            addCriterion("AllowEndTime between", value1, value2, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andAllowendtimeNotBetween(Date value1, Date value2) {
            addCriterion("AllowEndTime not between", value1, value2, "allowendtime");
            return (Criteria) this;
        }

        public Criteria andLockstartdateIsNull() {
            addCriterion("LockStartDate is null");
            return (Criteria) this;
        }

        public Criteria andLockstartdateIsNotNull() {
            addCriterion("LockStartDate is not null");
            return (Criteria) this;
        }

        public Criteria andLockstartdateEqualTo(Date value) {
            addCriterion("LockStartDate =", value, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockstartdateNotEqualTo(Date value) {
            addCriterion("LockStartDate <>", value, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockstartdateGreaterThan(Date value) {
            addCriterion("LockStartDate >", value, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockstartdateGreaterThanOrEqualTo(Date value) {
            addCriterion("LockStartDate >=", value, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockstartdateLessThan(Date value) {
            addCriterion("LockStartDate <", value, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockstartdateLessThanOrEqualTo(Date value) {
            addCriterion("LockStartDate <=", value, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockstartdateIn(List<Date> values) {
            addCriterion("LockStartDate in", values, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockstartdateNotIn(List<Date> values) {
            addCriterion("LockStartDate not in", values, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockstartdateBetween(Date value1, Date value2) {
            addCriterion("LockStartDate between", value1, value2, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockstartdateNotBetween(Date value1, Date value2) {
            addCriterion("LockStartDate not between", value1, value2, "lockstartdate");
            return (Criteria) this;
        }

        public Criteria andLockenddateIsNull() {
            addCriterion("LockEndDate is null");
            return (Criteria) this;
        }

        public Criteria andLockenddateIsNotNull() {
            addCriterion("LockEndDate is not null");
            return (Criteria) this;
        }

        public Criteria andLockenddateEqualTo(Date value) {
            addCriterion("LockEndDate =", value, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andLockenddateNotEqualTo(Date value) {
            addCriterion("LockEndDate <>", value, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andLockenddateGreaterThan(Date value) {
            addCriterion("LockEndDate >", value, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andLockenddateGreaterThanOrEqualTo(Date value) {
            addCriterion("LockEndDate >=", value, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andLockenddateLessThan(Date value) {
            addCriterion("LockEndDate <", value, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andLockenddateLessThanOrEqualTo(Date value) {
            addCriterion("LockEndDate <=", value, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andLockenddateIn(List<Date> values) {
            addCriterion("LockEndDate in", values, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andLockenddateNotIn(List<Date> values) {
            addCriterion("LockEndDate not in", values, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andLockenddateBetween(Date value1, Date value2) {
            addCriterion("LockEndDate between", value1, value2, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andLockenddateNotBetween(Date value1, Date value2) {
            addCriterion("LockEndDate not between", value1, value2, "lockenddate");
            return (Criteria) this;
        }

        public Criteria andFirstvisitIsNull() {
            addCriterion("FirstVisit is null");
            return (Criteria) this;
        }

        public Criteria andFirstvisitIsNotNull() {
            addCriterion("FirstVisit is not null");
            return (Criteria) this;
        }

        public Criteria andFirstvisitEqualTo(Date value) {
            addCriterion("FirstVisit =", value, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andFirstvisitNotEqualTo(Date value) {
            addCriterion("FirstVisit <>", value, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andFirstvisitGreaterThan(Date value) {
            addCriterion("FirstVisit >", value, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andFirstvisitGreaterThanOrEqualTo(Date value) {
            addCriterion("FirstVisit >=", value, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andFirstvisitLessThan(Date value) {
            addCriterion("FirstVisit <", value, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andFirstvisitLessThanOrEqualTo(Date value) {
            addCriterion("FirstVisit <=", value, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andFirstvisitIn(List<Date> values) {
            addCriterion("FirstVisit in", values, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andFirstvisitNotIn(List<Date> values) {
            addCriterion("FirstVisit not in", values, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andFirstvisitBetween(Date value1, Date value2) {
            addCriterion("FirstVisit between", value1, value2, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andFirstvisitNotBetween(Date value1, Date value2) {
            addCriterion("FirstVisit not between", value1, value2, "firstvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitIsNull() {
            addCriterion("PreviousVisit is null");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitIsNotNull() {
            addCriterion("PreviousVisit is not null");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitEqualTo(Date value) {
            addCriterion("PreviousVisit =", value, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitNotEqualTo(Date value) {
            addCriterion("PreviousVisit <>", value, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitGreaterThan(Date value) {
            addCriterion("PreviousVisit >", value, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitGreaterThanOrEqualTo(Date value) {
            addCriterion("PreviousVisit >=", value, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitLessThan(Date value) {
            addCriterion("PreviousVisit <", value, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitLessThanOrEqualTo(Date value) {
            addCriterion("PreviousVisit <=", value, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitIn(List<Date> values) {
            addCriterion("PreviousVisit in", values, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitNotIn(List<Date> values) {
            addCriterion("PreviousVisit not in", values, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitBetween(Date value1, Date value2) {
            addCriterion("PreviousVisit between", value1, value2, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andPreviousvisitNotBetween(Date value1, Date value2) {
            addCriterion("PreviousVisit not between", value1, value2, "previousvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitIsNull() {
            addCriterion("LastVisit is null");
            return (Criteria) this;
        }

        public Criteria andLastvisitIsNotNull() {
            addCriterion("LastVisit is not null");
            return (Criteria) this;
        }

        public Criteria andLastvisitEqualTo(Date value) {
            addCriterion("LastVisit =", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitNotEqualTo(Date value) {
            addCriterion("LastVisit <>", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitGreaterThan(Date value) {
            addCriterion("LastVisit >", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitGreaterThanOrEqualTo(Date value) {
            addCriterion("LastVisit >=", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitLessThan(Date value) {
            addCriterion("LastVisit <", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitLessThanOrEqualTo(Date value) {
            addCriterion("LastVisit <=", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitIn(List<Date> values) {
            addCriterion("LastVisit in", values, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitNotIn(List<Date> values) {
            addCriterion("LastVisit not in", values, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitBetween(Date value1, Date value2) {
            addCriterion("LastVisit between", value1, value2, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitNotBetween(Date value1, Date value2) {
            addCriterion("LastVisit not between", value1, value2, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateIsNull() {
            addCriterion("PwReviseDate is null");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateIsNotNull() {
            addCriterion("PwReviseDate is not null");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateEqualTo(Date value) {
            addCriterion("PwReviseDate =", value, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateNotEqualTo(Date value) {
            addCriterion("PwReviseDate <>", value, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateGreaterThan(Date value) {
            addCriterion("PwReviseDate >", value, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateGreaterThanOrEqualTo(Date value) {
            addCriterion("PwReviseDate >=", value, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateLessThan(Date value) {
            addCriterion("PwReviseDate <", value, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateLessThanOrEqualTo(Date value) {
            addCriterion("PwReviseDate <=", value, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateIn(List<Date> values) {
            addCriterion("PwReviseDate in", values, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateNotIn(List<Date> values) {
            addCriterion("PwReviseDate not in", values, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateBetween(Date value1, Date value2) {
            addCriterion("PwReviseDate between", value1, value2, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andPwrevisedateNotBetween(Date value1, Date value2) {
            addCriterion("PwReviseDate not between", value1, value2, "pwrevisedate");
            return (Criteria) this;
        }

        public Criteria andErrlogcountIsNull() {
            addCriterion("ErrLogCount is null");
            return (Criteria) this;
        }

        public Criteria andErrlogcountIsNotNull() {
            addCriterion("ErrLogCount is not null");
            return (Criteria) this;
        }

        public Criteria andErrlogcountEqualTo(Integer value) {
            addCriterion("ErrLogCount =", value, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andErrlogcountNotEqualTo(Integer value) {
            addCriterion("ErrLogCount <>", value, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andErrlogcountGreaterThan(Integer value) {
            addCriterion("ErrLogCount >", value, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andErrlogcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("ErrLogCount >=", value, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andErrlogcountLessThan(Integer value) {
            addCriterion("ErrLogCount <", value, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andErrlogcountLessThanOrEqualTo(Integer value) {
            addCriterion("ErrLogCount <=", value, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andErrlogcountIn(List<Integer> values) {
            addCriterion("ErrLogCount in", values, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andErrlogcountNotIn(List<Integer> values) {
            addCriterion("ErrLogCount not in", values, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andErrlogcountBetween(Integer value1, Integer value2) {
            addCriterion("ErrLogCount between", value1, value2, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andErrlogcountNotBetween(Integer value1, Integer value2) {
            addCriterion("ErrLogCount not between", value1, value2, "errlogcount");
            return (Criteria) this;
        }

        public Criteria andLogoncountIsNull() {
            addCriterion("LogOnCount is null");
            return (Criteria) this;
        }

        public Criteria andLogoncountIsNotNull() {
            addCriterion("LogOnCount is not null");
            return (Criteria) this;
        }

        public Criteria andLogoncountEqualTo(Integer value) {
            addCriterion("LogOnCount =", value, "logoncount");
            return (Criteria) this;
        }

        public Criteria andLogoncountNotEqualTo(Integer value) {
            addCriterion("LogOnCount <>", value, "logoncount");
            return (Criteria) this;
        }

        public Criteria andLogoncountGreaterThan(Integer value) {
            addCriterion("LogOnCount >", value, "logoncount");
            return (Criteria) this;
        }

        public Criteria andLogoncountGreaterThanOrEqualTo(Integer value) {
            addCriterion("LogOnCount >=", value, "logoncount");
            return (Criteria) this;
        }

        public Criteria andLogoncountLessThan(Integer value) {
            addCriterion("LogOnCount <", value, "logoncount");
            return (Criteria) this;
        }

        public Criteria andLogoncountLessThanOrEqualTo(Integer value) {
            addCriterion("LogOnCount <=", value, "logoncount");
            return (Criteria) this;
        }

        public Criteria andLogoncountIn(List<Integer> values) {
            addCriterion("LogOnCount in", values, "logoncount");
            return (Criteria) this;
        }

        public Criteria andLogoncountNotIn(List<Integer> values) {
            addCriterion("LogOnCount not in", values, "logoncount");
            return (Criteria) this;
        }

        public Criteria andLogoncountBetween(Integer value1, Integer value2) {
            addCriterion("LogOnCount between", value1, value2, "logoncount");
            return (Criteria) this;
        }

        public Criteria andLogoncountNotBetween(Integer value1, Integer value2) {
            addCriterion("LogOnCount not between", value1, value2, "logoncount");
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