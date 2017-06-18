package com.service;

import com.dao.EntityDao;
import com.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService {
    private final EntityDao entityDao;

    @Autowired
    public EntityService(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    public List<Entity> getAllEntity() {
        return entityDao.findAll();
    }
}
