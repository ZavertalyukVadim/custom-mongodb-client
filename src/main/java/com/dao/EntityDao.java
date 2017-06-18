package com.dao;

import com.entity.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntityDao extends MongoRepository<Entity,String> {
}
