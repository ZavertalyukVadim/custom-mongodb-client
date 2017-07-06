package com.dao;

import com.dto.ConditionDto;
import com.dto.EntityDto;
import com.dto.GroupByDto;
import com.dto.SortDto;
import com.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityTestDao {
    private final MongoOperations mongoOperations;

    @Autowired
    public EntityTestDao(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<EntityDto> find(ConditionDto conditionDto, GroupByDto groupBy, SortDto sortDto, Integer skip, Integer limit) {
        AggregationOperation match = Aggregation.match(Criteria.where(conditionDto.getField()).is(conditionDto.getValue()));
        AggregationOperation group = Aggregation.group(groupBy.getFirstFiled(), groupBy.getSecondField());
        AggregationOperation sort = Aggregation.sort(sortDto.getOrderByType(), sortDto.getOrderByFields());
        AggregationOperation lim = Aggregation.limit(limit);
        AggregationOperation sk = Aggregation.skip(skip);
        Aggregation agg = Aggregation.newAggregation(
                match,
                group,
                sort,
                lim,
                sk
        );
        AggregationResults<EntityDto> results = mongoOperations.aggregate(agg, Entity.class, EntityDto.class);
        return results.getMappedResults();
    }
}
