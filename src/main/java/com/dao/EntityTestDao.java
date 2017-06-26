package com.dao;

import com.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityTestDao {
    @Autowired
    private MongoOperations mongoOperations;

    public List<Entity> getWithAsc(String name,Integer skip,Integer limit,String sortBy){
        return mongoOperations.find(Query.query(Criteria.where("name").is(name)).with(new Sort(Sort.Direction.ASC, ""+sortBy)).skip(skip).limit(limit), Entity.class);
    }
    public List<Entity> getWithDesc(String name,Integer skip,Integer limit,String sortBy){
        return mongoOperations.find(Query.query(Criteria.where("name").is(name)).with(new Sort(Sort.Direction.DESC, ""+sortBy)).skip(skip).limit(limit), Entity.class);
    }
}
