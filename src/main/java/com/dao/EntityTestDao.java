package com.dao;

import com.dto.*;
import com.entity.Entity;
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

    public List<AgeDto> findForField(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        Aggregation agg = getAggregation(conditionDto, groupBy, sortDto, skip, limit);
        AggregationResults<AgeDto> results = mongoOperations.aggregate(agg, Entity.class, AgeDto.class);
        return results.getMappedResults();
    }

    private Aggregation getAggregation(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        AggregationOperation match = Aggregation.match(Criteria.where(conditionDto.getField()).is(conditionDto.getValue()));

        AggregationOperation group = Aggregation.group("name","age","object");
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
}
