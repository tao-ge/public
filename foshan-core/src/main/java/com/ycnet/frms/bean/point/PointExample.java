package com.ycnet.frms.bean.point;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PointExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PointExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPointNameIsNull() {
            addCriterion("point_name is null");
            return (Criteria) this;
        }

        public Criteria andPointNameIsNotNull() {
            addCriterion("point_name is not null");
            return (Criteria) this;
        }

        public Criteria andPointNameEqualTo(String value) {
            addCriterion("point_name =", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotEqualTo(String value) {
            addCriterion("point_name <>", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameGreaterThan(String value) {
            addCriterion("point_name >", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameGreaterThanOrEqualTo(String value) {
            addCriterion("point_name >=", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameLessThan(String value) {
            addCriterion("point_name <", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameLessThanOrEqualTo(String value) {
            addCriterion("point_name <=", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameLike(String value) {
            addCriterion("point_name like", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotLike(String value) {
            addCriterion("point_name not like", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameIn(List<String> values) {
            addCriterion("point_name in", values, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotIn(List<String> values) {
            addCriterion("point_name not in", values, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameBetween(String value1, String value2) {
            addCriterion("point_name between", value1, value2, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotBetween(String value1, String value2) {
            addCriterion("point_name not between", value1, value2, "pointName");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andBaiduXIsNull() {
            addCriterion("baidu_x is null");
            return (Criteria) this;
        }

        public Criteria andBaiduXIsNotNull() {
            addCriterion("baidu_x is not null");
            return (Criteria) this;
        }

        public Criteria andBaiduXEqualTo(BigDecimal value) {
            addCriterion("baidu_x =", value, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduXNotEqualTo(BigDecimal value) {
            addCriterion("baidu_x <>", value, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduXGreaterThan(BigDecimal value) {
            addCriterion("baidu_x >", value, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduXGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("baidu_x >=", value, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduXLessThan(BigDecimal value) {
            addCriterion("baidu_x <", value, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduXLessThanOrEqualTo(BigDecimal value) {
            addCriterion("baidu_x <=", value, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduXIn(List<BigDecimal> values) {
            addCriterion("baidu_x in", values, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduXNotIn(List<BigDecimal> values) {
            addCriterion("baidu_x not in", values, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduXBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("baidu_x between", value1, value2, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduXNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("baidu_x not between", value1, value2, "baiduX");
            return (Criteria) this;
        }

        public Criteria andBaiduYIsNull() {
            addCriterion("baidu_y is null");
            return (Criteria) this;
        }

        public Criteria andBaiduYIsNotNull() {
            addCriterion("baidu_y is not null");
            return (Criteria) this;
        }

        public Criteria andBaiduYEqualTo(BigDecimal value) {
            addCriterion("baidu_y =", value, "baiduY");
            return (Criteria) this;
        }

        public Criteria andBaiduYNotEqualTo(BigDecimal value) {
            addCriterion("baidu_y <>", value, "baiduY");
            return (Criteria) this;
        }

        public Criteria andBaiduYGreaterThan(BigDecimal value) {
            addCriterion("baidu_y >", value, "baiduY");
            return (Criteria) this;
        }

        public Criteria andBaiduYGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("baidu_y >=", value, "baiduY");
            return (Criteria) this;
        }

        public Criteria andBaiduYLessThan(BigDecimal value) {
            addCriterion("baidu_y <", value, "baiduY");
            return (Criteria) this;
        }

        public Criteria andBaiduYLessThanOrEqualTo(BigDecimal value) {
            addCriterion("baidu_y <=", value, "baiduY");
            return (Criteria) this;
        }

        public Criteria andBaiduYIn(List<BigDecimal> values) {
            addCriterion("baidu_y in", values, "baiduY");
            return (Criteria) this;
        }

        public Criteria andBaiduYNotIn(List<BigDecimal> values) {
            addCriterion("baidu_y not in", values, "baiduY");
            return (Criteria) this;
        }

        public Criteria andBaiduYBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("baidu_y between", value1, value2, "baiduY");
            return (Criteria) this;
        }

        public Criteria andBaiduYNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("baidu_y not between", value1, value2, "baiduY");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedIsNull() {
            addCriterion("is_translated is null");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedIsNotNull() {
            addCriterion("is_translated is not null");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedEqualTo(String value) {
            addCriterion("is_translated =", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedNotEqualTo(String value) {
            addCriterion("is_translated <>", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedGreaterThan(String value) {
            addCriterion("is_translated >", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedGreaterThanOrEqualTo(String value) {
            addCriterion("is_translated >=", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedLessThan(String value) {
            addCriterion("is_translated <", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedLessThanOrEqualTo(String value) {
            addCriterion("is_translated <=", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedLike(String value) {
            addCriterion("is_translated like", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedNotLike(String value) {
            addCriterion("is_translated not like", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedIn(List<String> values) {
            addCriterion("is_translated in", values, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedNotIn(List<String> values) {
            addCriterion("is_translated not in", values, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedBetween(String value1, String value2) {
            addCriterion("is_translated between", value1, value2, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedNotBetween(String value1, String value2) {
            addCriterion("is_translated not between", value1, value2, "isTranslated");
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