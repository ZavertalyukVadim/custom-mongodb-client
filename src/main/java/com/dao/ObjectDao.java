package com.dao;

import com.entity.Object;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ObjectDao extends MongoRepository<Object, String> {
    Object getByLastName(String last);
}
