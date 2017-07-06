package com.dao;

import com.dto.EntityDto;
import com.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class EntityTestDao {
    private final MongoOperations mongoOperations;

    @Autowired
    public EntityTestDao(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<Entity> getWithAsc(String name,Integer skip,Integer limit,String sortBy){
        return mongoOperations.find(Query.query(Criteria.where("name").is(name)).with(new Sort(Sort.Direction.ASC, ""+sortBy)).skip(skip).limit(limit), Entity.class);
    }
    public List<Entity> getWithDesc(String name,Integer skip,Integer limit,String sortBy){

        AggregationOperation match = Aggregation.match(Criteria.where("name").is(name));
        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, ""+sortBy);
        AggregationOperation lim = Aggregation.limit(limit);
        AggregationOperation sk = Aggregation.skip(skip);

        Aggregation agg = newAggregation(
                match,
                sort,
                lim,
                sk
        );

        AggregationResults<EntityDto> results = mongoOperations.aggregate(agg,Entity.class, EntityDto.class);
        List<EntityDto> mappedResult = results.getMappedResults();

        System.out.println(mappedResult);

        return mongoOperations.find(Query.query(Criteria.where("name").is(name)).with(new Sort(Sort.Direction.DESC, ""+sortBy)).skip(skip).limit(limit), Entity.class);
    }
}
