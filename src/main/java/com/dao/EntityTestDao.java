package com.dao;

import com.dto.*;
import com.entity.Entity;
import com.entity.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EntityTestDao {
    private final MongoOperations mongoOperations;

    @Autowired
    public EntityTestDao(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<EntityDto> findForAll(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        Aggregation agg = getAggregation(conditionDto, groupBy, sortDto, skip, limit);
        AggregationResults<EntityDto> results = mongoOperations.aggregate(agg, Entity.class, EntityDto.class);
        return results.getMappedResults();
    }

    public List<SexDto> findForFieldSex(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        Aggregation agg = getAggregation(conditionDto, groupBy, sortDto, skip, limit);
        AggregationResults<SexDto> results = mongoOperations.aggregate(agg, Entity.class, SexDto.class);
        return results.getMappedResults();
    }

    public List<NameDto> findForFieldName(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        Aggregation agg = getAggregation(conditionDto, groupBy, sortDto, skip, limit);
        AggregationResults<NameDto> results = mongoOperations.aggregate(agg, Entity.class, NameDto.class);
        return results.getMappedResults();
    }

    public List<ObjectDto> findFieldOnSubField(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        Aggregation agg = getAggregation(conditionDto, groupBy, sortDto, skip, limit);
        AggregationResults<ObjectDto> results = mongoOperations.aggregate(agg, Object.class, ObjectDto.class);
        return results.getMappedResults();
    }

    public List<ObjectDto> findFieldsFromSubField(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        Aggregation agg = getAggregation(conditionDto, groupBy, sortDto, skip, limit);
        AggregationResults<ObjectDto> results = mongoOperations.aggregate(agg, Object.class, ObjectDto.class);
        return results.getMappedResults();
    }

    public List<FirstNameDto> findSubFieldFirstName(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        Aggregation agg = getAggregation(conditionDto, groupBy, sortDto, skip, limit);
        AggregationResults<FirstNameDto> results = mongoOperations.aggregate(agg, Object.class, FirstNameDto.class);
        return results.getMappedResults();
    }

    public List<LastNameDto> findSubFieldLastName(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        Aggregation agg = getAggregation(conditionDto, groupBy, sortDto, skip, limit);
        AggregationResults<LastNameDto> results = mongoOperations.aggregate(agg, Object.class, LastNameDto.class);
        return results.getMappedResults();
    }

    private Aggregation getAggregation(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {

        AggregationOperation match = Aggregation.match(getCriteriaForCondition(conditionDto));
        AggregationOperation group;
        try {
            group = Aggregation.group(groupBy.getFirstFiled(), groupBy.getSecondField(), groupBy.getThirdField());
        } catch (Exception e) {
            group = Aggregation.group(groupBy.getFirstFiled(), groupBy.getSecondField());
        }
        AggregationOperation sort = Aggregation.sort(sortDto.getOrderByType(), sortDto.getOrderByFields());
        AggregationOperation lim = Aggregation.limit(limit);
        AggregationOperation sk = Aggregation.skip(skip);
        return Aggregation.newAggregation(
                match,
                group,
                sort,
                lim,
                sk
        );
    }

    private Criteria getCriteriaForCondition(ConditionDto conditionDto) {
        Criteria criteria = null;
        switch (conditionDto.getOperator()) {
            case "=":
                criteria = Criteria.where(conditionDto.getField()).is(conditionDto.getValue());
                break;
            case "<>":
                criteria = Criteria.where(conditionDto.getField()).ne(conditionDto.getValue());
                break;
            case ">":
                criteria = Criteria.where(conditionDto.getField()).gt(conditionDto.getValue());
                break;
            case ">=":
                criteria = Criteria.where(conditionDto.getField()).gte(conditionDto.getValue());
                break;
            case "<":
                criteria = Criteria.where(conditionDto.getField()).lt(conditionDto.getValue());
                break;
            case "<=":
                criteria = Criteria.where(conditionDto.getField()).lte(conditionDto.getValue());
                break;
        }
        return criteria;
    }
}
