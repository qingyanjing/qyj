package org.wisdom.pms.bp.model.crud;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RbxConfigCrossExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RbxConfigCrossExample() {
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

        public Criteria andCrossNoIsNull() {
            addCriterion("CROSS_NO is null");
            return (Criteria) this;
        }

        public Criteria andCrossNoIsNotNull() {
            addCriterion("CROSS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCrossNoEqualTo(String value) {
            addCriterion("CROSS_NO =", value, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoNotEqualTo(String value) {
            addCriterion("CROSS_NO <>", value, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoGreaterThan(String value) {
            addCriterion("CROSS_NO >", value, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoGreaterThanOrEqualTo(String value) {
            addCriterion("CROSS_NO >=", value, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoLessThan(String value) {
            addCriterion("CROSS_NO <", value, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoLessThanOrEqualTo(String value) {
            addCriterion("CROSS_NO <=", value, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoLike(String value) {
            addCriterion("CROSS_NO like", value, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoNotLike(String value) {
            addCriterion("CROSS_NO not like", value, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoIn(List<String> values) {
            addCriterion("CROSS_NO in", values, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoNotIn(List<String> values) {
            addCriterion("CROSS_NO not in", values, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoBetween(String value1, String value2) {
            addCriterion("CROSS_NO between", value1, value2, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andCrossNoNotBetween(String value1, String value2) {
            addCriterion("CROSS_NO not between", value1, value2, "CrossNo");
            return (Criteria) this;
        }

        public Criteria andMacIsNull() {
            addCriterion("MAC is null");
            return (Criteria) this;
        }

        public Criteria andMacIsNotNull() {
            addCriterion("MAC is not null");
            return (Criteria) this;
        }

        public Criteria andMacEqualTo(String value) {
            addCriterion("MAC =", value, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacNotEqualTo(String value) {
            addCriterion("MAC <>", value, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThan(String value) {
            addCriterion("MAC >", value, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThanOrEqualTo(String value) {
            addCriterion("MAC >=", value, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThan(String value) {
            addCriterion("MAC <", value, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThanOrEqualTo(String value) {
            addCriterion("MAC <=", value, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacLike(String value) {
            addCriterion("MAC like", value, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacNotLike(String value) {
            addCriterion("MAC not like", value, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacIn(List<String> values) {
            addCriterion("MAC in", values, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacNotIn(List<String> values) {
            addCriterion("MAC not in", values, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacBetween(String value1, String value2) {
            addCriterion("MAC between", value1, value2, "Mac");
            return (Criteria) this;
        }

        public Criteria andMacNotBetween(String value1, String value2) {
            addCriterion("MAC not between", value1, value2, "Mac");
            return (Criteria) this;
        }

        public Criteria andCrossDescIsNull() {
            addCriterion("CROSS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andCrossDescIsNotNull() {
            addCriterion("CROSS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andCrossDescEqualTo(String value) {
            addCriterion("CROSS_DESC =", value, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescNotEqualTo(String value) {
            addCriterion("CROSS_DESC <>", value, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescGreaterThan(String value) {
            addCriterion("CROSS_DESC >", value, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescGreaterThanOrEqualTo(String value) {
            addCriterion("CROSS_DESC >=", value, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescLessThan(String value) {
            addCriterion("CROSS_DESC <", value, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescLessThanOrEqualTo(String value) {
            addCriterion("CROSS_DESC <=", value, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescLike(String value) {
            addCriterion("CROSS_DESC like", value, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescNotLike(String value) {
            addCriterion("CROSS_DESC not like", value, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescIn(List<String> values) {
            addCriterion("CROSS_DESC in", values, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescNotIn(List<String> values) {
            addCriterion("CROSS_DESC not in", values, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescBetween(String value1, String value2) {
            addCriterion("CROSS_DESC between", value1, value2, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andCrossDescNotBetween(String value1, String value2) {
            addCriterion("CROSS_DESC not between", value1, value2, "CrossDesc");
            return (Criteria) this;
        }

        public Criteria andScatsIdIsNull() {
            addCriterion("SCATS_ID is null");
            return (Criteria) this;
        }

        public Criteria andScatsIdIsNotNull() {
            addCriterion("SCATS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andScatsIdEqualTo(String value) {
            addCriterion("SCATS_ID =", value, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdNotEqualTo(String value) {
            addCriterion("SCATS_ID <>", value, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdGreaterThan(String value) {
            addCriterion("SCATS_ID >", value, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCATS_ID >=", value, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdLessThan(String value) {
            addCriterion("SCATS_ID <", value, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdLessThanOrEqualTo(String value) {
            addCriterion("SCATS_ID <=", value, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdLike(String value) {
            addCriterion("SCATS_ID like", value, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdNotLike(String value) {
            addCriterion("SCATS_ID not like", value, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdIn(List<String> values) {
            addCriterion("SCATS_ID in", values, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdNotIn(List<String> values) {
            addCriterion("SCATS_ID not in", values, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdBetween(String value1, String value2) {
            addCriterion("SCATS_ID between", value1, value2, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andScatsIdNotBetween(String value1, String value2) {
            addCriterion("SCATS_ID not between", value1, value2, "ScatsId");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("IP is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("IP is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("IP =", value, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("IP <>", value, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("IP >", value, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("IP >=", value, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("IP <", value, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("IP <=", value, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("IP like", value, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("IP not like", value, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("IP in", values, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("IP not in", values, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("IP between", value1, value2, "Ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("IP not between", value1, value2, "Ip");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("AREA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("AREA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("AREA_CODE =", value, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("AREA_CODE <>", value, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("AREA_CODE >", value, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_CODE >=", value, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("AREA_CODE <", value, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("AREA_CODE <=", value, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("AREA_CODE like", value, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("AREA_CODE not like", value, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("AREA_CODE in", values, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("AREA_CODE not in", values, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("AREA_CODE between", value1, value2, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("AREA_CODE not between", value1, value2, "AreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaDescIsNull() {
            addCriterion("AREA_DESC is null");
            return (Criteria) this;
        }

        public Criteria andAreaDescIsNotNull() {
            addCriterion("AREA_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andAreaDescEqualTo(String value) {
            addCriterion("AREA_DESC =", value, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotEqualTo(String value) {
            addCriterion("AREA_DESC <>", value, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescGreaterThan(String value) {
            addCriterion("AREA_DESC >", value, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_DESC >=", value, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescLessThan(String value) {
            addCriterion("AREA_DESC <", value, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescLessThanOrEqualTo(String value) {
            addCriterion("AREA_DESC <=", value, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescLike(String value) {
            addCriterion("AREA_DESC like", value, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotLike(String value) {
            addCriterion("AREA_DESC not like", value, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescIn(List<String> values) {
            addCriterion("AREA_DESC in", values, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotIn(List<String> values) {
            addCriterion("AREA_DESC not in", values, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescBetween(String value1, String value2) {
            addCriterion("AREA_DESC between", value1, value2, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotBetween(String value1, String value2) {
            addCriterion("AREA_DESC not between", value1, value2, "AreaDesc");
            return (Criteria) this;
        }

        public Criteria andLongItudeIsNull() {
            addCriterion("LONGITUDE is null");
            return (Criteria) this;
        }

        public Criteria andLongItudeIsNotNull() {
            addCriterion("LONGITUDE is not null");
            return (Criteria) this;
        }

        public Criteria andLongItudeEqualTo(BigDecimal value) {
            addCriterion("LONGITUDE =", value, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLongItudeNotEqualTo(BigDecimal value) {
            addCriterion("LONGITUDE <>", value, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLongItudeGreaterThan(BigDecimal value) {
            addCriterion("LONGITUDE >", value, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLongItudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LONGITUDE >=", value, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLongItudeLessThan(BigDecimal value) {
            addCriterion("LONGITUDE <", value, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLongItudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LONGITUDE <=", value, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLongItudeIn(List<BigDecimal> values) {
            addCriterion("LONGITUDE in", values, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLongItudeNotIn(List<BigDecimal> values) {
            addCriterion("LONGITUDE not in", values, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLongItudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LONGITUDE between", value1, value2, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLongItudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LONGITUDE not between", value1, value2, "LongItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeIsNull() {
            addCriterion("LATITUDE is null");
            return (Criteria) this;
        }

        public Criteria andLatItudeIsNotNull() {
            addCriterion("LATITUDE is not null");
            return (Criteria) this;
        }

        public Criteria andLatItudeEqualTo(BigDecimal value) {
            addCriterion("LATITUDE =", value, "LatItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeNotEqualTo(BigDecimal value) {
            addCriterion("LATITUDE <>", value, "LatItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeGreaterThan(BigDecimal value) {
            addCriterion("LATITUDE >", value, "LatItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LATITUDE >=", value, "LatItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeLessThan(BigDecimal value) {
            addCriterion("LATITUDE <", value, "LatItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LATITUDE <=", value, "LatItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeIn(List<BigDecimal> values) {
            addCriterion("LATITUDE in", values, "LatItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeNotIn(List<BigDecimal> values) {
            addCriterion("LATITUDE not in", values, "LatItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LATITUDE between", value1, value2, "LatItude");
            return (Criteria) this;
        }

        public Criteria andLatItudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LATITUDE not between", value1, value2, "LatItude");
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