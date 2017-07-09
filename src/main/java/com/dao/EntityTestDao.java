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
        Criteria criteria = firstPart(conditionDto);
        if (conditionDto.getExtended()){
            switch (conditionDto.getStandardLogicalOperation()){
                case "AND":
                    criteria.andOperator(secondPart(conditionDto));
                    break;
                case "OR":
                    criteria.orOperator(secondPart(conditionDto));
                    break;
            }
        }
        return criteria;
    }

    private Criteria firstPart(ConditionDto conditionDto) {
        Criteria criteria = null;
        switch (conditionDto.getFirstOperator()) {
            case "=":
                criteria = Criteria.where(conditionDto.getFirstField()).is(conditionDto.getFirstValue());
                break;
            case "<>":
                criteria = Criteria.where(conditionDto.getFirstField()).ne(conditionDto.getFirstValue());
                break;
            case ">":
                criteria = Criteria.where(conditionDto.getFirstField()).gt(conditionDto.getFirstValue());
                break;
            case ">=":
                criteria = Criteria.where(conditionDto.getFirstField()).gte(conditionDto.getFirstValue());
                break;
            case "<":
                criteria = Criteria.where(conditionDto.getFirstField()).lt(conditionDto.getFirstValue());
                break;
            case "<=":
                criteria = Criteria.where(conditionDto.getFirstField()).lte(conditionDto.getFirstValue());
                break;
        }
        return criteria;
    }

    private Criteria secondPart(ConditionDto conditionDto) {
        Criteria criteria = null;
        switch (conditionDto.getSecondOperator()) {
            case "=":
                criteria = Criteria.where(conditionDto.getSecondField()).is(conditionDto.getSecondValue());
                break;
            case "<>":
                criteria = Criteria.where(conditionDto.getSecondField()).ne(conditionDto.getSecondValue());
                break;
            case ">":
                criteria = Criteria.where(conditionDto.getSecondField()).gt(conditionDto.getSecondValue());
                break;
            case ">=":
                criteria = Criteria.where(conditionDto.getSecondField()).gte(conditionDto.getSecondValue());
                break;
            case "<":
                criteria = Criteria.where(conditionDto.getSecondField()).lt(conditionDto.getSecondValue());
                break;
            case "<=":
                criteria = Criteria.where(conditionDto.getSecondField()).lte(conditionDto.getSecondValue());
                break;
        }
        return criteria;
    }
}
