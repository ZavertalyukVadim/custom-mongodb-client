package com.dao;

import com.entity.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntityDao extends MongoRepository<Entity, String> {
    //    @Query(value = "{'name': ?0 }",fields = "{'name':1, 'age':1}")
    List<Entity> getAllByName(String name);


    void removeAllByNameLike(String name);
}