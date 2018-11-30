package com.ycnet.frms.bean.pipeline;

import java.util.ArrayList;
import java.util.List;

public class PipelineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipelineExample() {
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

        public Criteria andPipelineNameIsNull() {
            addCriterion("pipeline_name is null");
            return (Criteria) this;
        }

        public Criteria andPipelineNameIsNotNull() {
            addCriterion("pipeline_name is not null");
            return (Criteria) this;
        }

        public Criteria andPipelineNameEqualTo(String value) {
            addCriterion("pipeline_name =", value, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameNotEqualTo(String value) {
            addCriterion("pipeline_name <>", value, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameGreaterThan(String value) {
            addCriterion("pipeline_name >", value, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameGreaterThanOrEqualTo(String value) {
            addCriterion("pipeline_name >=", value, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameLessThan(String value) {
            addCriterion("pipeline_name <", value, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameLessThanOrEqualTo(String value) {
            addCriterion("pipeline_name <=", value, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameLike(String value) {
            addCriterion("pipeline_name like", value, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameNotLike(String value) {
            addCriterion("pipeline_name not like", value, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameIn(List<String> values) {
            addCriterion("pipeline_name in", values, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameNotIn(List<String> values) {
            addCriterion("pipeline_name not in", values, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameBetween(String value1, String value2) {
            addCriterion("pipeline_name between", value1, value2, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andPipelineNameNotBetween(String value1, String value2) {
            addCriterion("pipeline_name not between", value1, value2, "pipelineName");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdIsNull() {
            addCriterion("front_point_id is null");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdIsNotNull() {
            addCriterion("front_point_id is not null");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdEqualTo(Integer value) {
            addCriterion("front_point_id =", value, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdNotEqualTo(Integer value) {
            addCriterion("front_point_id <>", value, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdGreaterThan(Integer value) {
            addCriterion("front_point_id >", value, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("front_point_id >=", value, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdLessThan(Integer value) {
            addCriterion("front_point_id <", value, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdLessThanOrEqualTo(Integer value) {
            addCriterion("front_point_id <=", value, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdIn(List<Integer> values) {
            addCriterion("front_point_id in", values, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdNotIn(List<Integer> values) {
            addCriterion("front_point_id not in", values, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdBetween(Integer value1, Integer value2) {
            addCriterion("front_point_id between", value1, value2, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andFrontPointIdNotBetween(Integer value1, Integer value2) {
            addCriterion("front_point_id not between", value1, value2, "frontPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdIsNull() {
            addCriterion("back_point_id is null");
            return (Criteria) this;
        }

        public Criteria andBackPointIdIsNotNull() {
            addCriterion("back_point_id is not null");
            return (Criteria) this;
        }

        public Criteria andBackPointIdEqualTo(Integer value) {
            addCriterion("back_point_id =", value, "backPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdNotEqualTo(Integer value) {
            addCriterion("back_point_id <>", value, "backPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdGreaterThan(Integer value) {
            addCriterion("back_point_id >", value, "backPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("back_point_id >=", value, "backPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdLessThan(Integer value) {
            addCriterion("back_point_id <", value, "backPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdLessThanOrEqualTo(Integer value) {
            addCriterion("back_point_id <=", value, "backPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdIn(List<Integer> values) {
            addCriterion("back_point_id in", values, "backPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdNotIn(List<Integer> values) {
            addCriterion("back_point_id not in", values, "backPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdBetween(Integer value1, Integer value2) {
            addCriterion("back_point_id between", value1, value2, "backPointId");
            return (Criteria) this;
        }

        public Criteria andBackPointIdNotBetween(Integer value1, Integer value2) {
            addCriterion("back_point_id not between", value1, value2, "backPointId");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeIsNull() {
            addCriterion("pipeline_type is null");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeIsNotNull() {
            addCriterion("pipeline_type is not null");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeEqualTo(String value) {
            addCriterion("pipeline_type =", value, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeNotEqualTo(String value) {
            addCriterion("pipeline_type <>", value, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeGreaterThan(String value) {
            addCriterion("pipeline_type >", value, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pipeline_type >=", value, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeLessThan(String value) {
            addCriterion("pipeline_type <", value, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeLessThanOrEqualTo(String value) {
            addCriterion("pipeline_type <=", value, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeLike(String value) {
            addCriterion("pipeline_type like", value, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeNotLike(String value) {
            addCriterion("pipeline_type not like", value, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeIn(List<String> values) {
            addCriterion("pipeline_type in", values, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeNotIn(List<String> values) {
            addCriterion("pipeline_type not in", values, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeBetween(String value1, String value2) {
            addCriterion("pipeline_type between", value1, value2, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPipelineTypeNotBetween(String value1, String value2) {
            addCriterion("pipeline_type not between", value1, value2, "pipelineType");
            return (Criteria) this;
        }

        public Criteria andPoreIsNull() {
            addCriterion("pore is null");
            return (Criteria) this;
        }

        public Criteria andPoreIsNotNull() {
            addCriterion("pore is not null");
            return (Criteria) this;
        }

        public Criteria andPoreEqualTo(Integer value) {
            addCriterion("pore =", value, "pore");
            return (Criteria) this;
        }

        public Criteria andPoreNotEqualTo(Integer value) {
            addCriterion("pore <>", value, "pore");
            return (Criteria) this;
        }

        public Criteria andPoreGreaterThan(Integer value) {
            addCriterion("pore >", value, "pore");
            return (Criteria) this;
        }

        public Criteria andPoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("pore >=", value, "pore");
            return (Criteria) this;
        }

        public Criteria andPoreLessThan(Integer value) {
            addCriterion("pore <", value, "pore");
            return (Criteria) this;
        }

        public Criteria andPoreLessThanOrEqualTo(Integer value) {
            addCriterion("pore <=", value, "pore");
            return (Criteria) this;
        }

        public Criteria andPoreIn(List<Integer> values) {
            addCriterion("pore in", values, "pore");
            return (Criteria) this;
        }

        public Criteria andPoreNotIn(List<Integer> values) {
            addCriterion("pore not in", values, "pore");
            return (Criteria) this;
        }

        public Criteria andPoreBetween(Integer value1, Integer value2) {
            addCriterion("pore between", value1, value2, "pore");
            return (Criteria) this;
        }

        public Criteria andPoreNotBetween(Integer value1, Integer value2) {
            addCriterion("pore not between", value1, value2, "pore");
            return (Criteria) this;
        }

        public Criteria andPipeIsNull() {
            addCriterion("pipe is null");
            return (Criteria) this;
        }

        public Criteria andPipeIsNotNull() {
            addCriterion("pipe is not null");
            return (Criteria) this;
        }

        public Criteria andPipeEqualTo(String value) {
            addCriterion("pipe =", value, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeNotEqualTo(String value) {
            addCriterion("pipe <>", value, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeGreaterThan(String value) {
            addCriterion("pipe >", value, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeGreaterThanOrEqualTo(String value) {
            addCriterion("pipe >=", value, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeLessThan(String value) {
            addCriterion("pipe <", value, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeLessThanOrEqualTo(String value) {
            addCriterion("pipe <=", value, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeLike(String value) {
            addCriterion("pipe like", value, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeNotLike(String value) {
            addCriterion("pipe not like", value, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeIn(List<String> values) {
            addCriterion("pipe in", values, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeNotIn(List<String> values) {
            addCriterion("pipe not in", values, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeBetween(String value1, String value2) {
            addCriterion("pipe between", value1, value2, "pipe");
            return (Criteria) this;
        }

        public Criteria andPipeNotBetween(String value1, String value2) {
            addCriterion("pipe not between", value1, value2, "pipe");
            return (Criteria) this;
        }

        public Criteria andTrunkNameIsNull() {
            addCriterion("trunk_name is null");
            return (Criteria) this;
        }

        public Criteria andTrunkNameIsNotNull() {
            addCriterion("trunk_name is not null");
            return (Criteria) this;
        }

        public Criteria andTrunkNameEqualTo(String value) {
            addCriterion("trunk_name =", value, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameNotEqualTo(String value) {
            addCriterion("trunk_name <>", value, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameGreaterThan(String value) {
            addCriterion("trunk_name >", value, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameGreaterThanOrEqualTo(String value) {
            addCriterion("trunk_name >=", value, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameLessThan(String value) {
            addCriterion("trunk_name <", value, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameLessThanOrEqualTo(String value) {
            addCriterion("trunk_name <=", value, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameLike(String value) {
            addCriterion("trunk_name like", value, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameNotLike(String value) {
            addCriterion("trunk_name not like", value, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameIn(List<String> values) {
            addCriterion("trunk_name in", values, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameNotIn(List<String> values) {
            addCriterion("trunk_name not in", values, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameBetween(String value1, String value2) {
            addCriterion("trunk_name between", value1, value2, "trunkName");
            return (Criteria) this;
        }

        public Criteria andTrunkNameNotBetween(String value1, String value2) {
            addCriterion("trunk_name not between", value1, value2, "trunkName");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyIsNull() {
            addCriterion("pepeline_classify is null");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyIsNotNull() {
            addCriterion("pepeline_classify is not null");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyEqualTo(String value) {
            addCriterion("pepeline_classify =", value, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyNotEqualTo(String value) {
            addCriterion("pepeline_classify <>", value, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyGreaterThan(String value) {
            addCriterion("pepeline_classify >", value, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyGreaterThanOrEqualTo(String value) {
            addCriterion("pepeline_classify >=", value, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyLessThan(String value) {
            addCriterion("pepeline_classify <", value, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyLessThanOrEqualTo(String value) {
            addCriterion("pepeline_classify <=", value, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyLike(String value) {
            addCriterion("pepeline_classify like", value, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyNotLike(String value) {
            addCriterion("pepeline_classify not like", value, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyIn(List<String> values) {
            addCriterion("pepeline_classify in", values, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyNotIn(List<String> values) {
            addCriterion("pepeline_classify not in", values, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyBetween(String value1, String value2) {
            addCriterion("pepeline_classify between", value1, value2, "pepelineClassify");
            return (Criteria) this;
        }

        public Criteria andPepelineClassifyNotBetween(String value1, String value2) {
            addCriterion("pepeline_classify not between", value1, value2, "pepelineClassify");
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