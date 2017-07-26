package com.service;

import com.dao.EntityDao;
import com.dao.EntityTestDao;
import com.dao.ObjectDao;
import com.dto.*;
import com.entity.Entity;
import com.entity.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService {
    private final EntityDao entityDao;
    private final EntityTestDao entityTestDao;
    private final ObjectDao objectDao;

    @Autowired
    public EntityService(EntityDao entityDao, EntityTestDao entityTestDao, ObjectDao objectDao) {
        this.entityDao = entityDao;
        this.entityTestDao = entityTestDao;
        this.objectDao = objectDao;
    }
    public boolean checkEmptyDb(){
        return entityDao.findAll().isEmpty();
    }
    public void addTestData(){
        test();
    }
    public List<EntityDto> getAllFieldsFromEntity(SqlDto query) {

        if (entityDao.findAll().isEmpty()) {
            test();
        }
        return entityTestDao.findForAll(getInformationConditionForEntity(query.getConditionDto()), getInformationForGroupByForEntity(query.getGroupByDto()), getInformationForOrderForEntity(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));

    }

    List<SexDto> getFieldSex(SqlDto query) {
        if (entityDao.findAll().isEmpty()) {
            test();
        }
        return entityTestDao.findForFieldSex(getInformationConditionForEntity(query.getConditionDto()), getInformationForGroupByForEntity(query.getGroupByDto()), getInformationForOrderForEntity(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
    }

    List<NameDto> getFieldName(SqlDto query) {
        if (entityDao.findAll().isEmpty()) {
            test();
        }
        return entityTestDao.findForFieldName(getInformationConditionForEntity(query.getConditionDto()), getInformationForGroupByForEntity(query.getGroupByDto()), getInformationForOrderForEntity(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
    }

    List<ObjectDto> getAllFieldsFromSubField(SqlDto query) {
        return entityTestDao.findFieldsFromSubField(getInformationConditionForObject(query.getConditionDto()), getInformationForGroupByForObject(query.getGroupByDto()), getInformationForOrderForObject(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
    }

    List<FirstNameDto> getSubFieldFirstName(SqlDto query) {
        return entityTestDao.findSubFieldFirstName(getInformationConditionForObject(query.getConditionDto()), getInformationForGroupByForObject(query.getGroupByDto()), getInformationForOrderForObject(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
    }

    List<LastNameDto> getSubFieldLastName(SqlDto query) {
        return entityTestDao.findSubFieldLastName(getInformationConditionForObject(query.getConditionDto()), getInformationForGroupByForObject(query.getGroupByDto()), getInformationForOrderForObject(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));

    }

    private Integer getInformationForSkip(Integer skip) {
        return skip;
    }

    private Integer getInformationForLimit(Integer limit) {
        return limit;
    }

    private GroupByDto getInformationForGroupByForEntity(GroupByDto groupByDto) {
        return groupByDto;
    }

    private SortDto getInformationForOrderForEntity(SortDto sortDto) {
        return sortDto;
    }

    private ConditionDto getInformationConditionForEntity(ConditionDto conditionDto) {
        return conditionDto;
    }


    private GroupByDto getInformationForGroupByForObject(GroupByDto groupByDto) {
        return groupByDto;
    }

    private SortDto getInformationForOrderForObject(SortDto sortDto) {
        return sortDto;
    }

    private ConditionDto getInformationConditionForObject(ConditionDto conditionDto) {
        return conditionDto;
    }


    private void test() {
        Object object = new Object();
        object.setFirstName("first");
        object.setLastName("last");
        objectDao.save(object);
        Entity entity = new Entity();
        entity.setName("lol");
        entity.setSex("M");
        entity.setObject(object);
        entityDao.save(entity);
        Entity entity2 = new Entity();
        entity2.setName("lol1");
        entity2.setSex("W");
        entityDao.save(entity2);
        Entity entity3 = new Entity();
        entity3.setName("lol2");
        entity3.setSex("M");
        entityDao.save(entity3);
        Entity entity4 = new Entity();
        entity4.setName("lol");
        entity4.setSex("W");
        entity4.setObject(object);
        entityDao.save(entity4);
    }
}
