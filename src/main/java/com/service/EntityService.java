package com.service;

import com.dao.EntityDao;
import com.dao.EntityTestDao;
import com.dao.ObjectDao;
import com.dto.*;
import com.entity.Entity;
import com.entity.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public void getAllFieldsFromEntity(String query) {

        if (entityDao.findAll().isEmpty()) {
            test();
        }
        QueryDto queryDto = new QueryDto(query);
        queryDto.setLimit(3);
        queryDto.setSkip(0);
        List<EntityDto> list = entityTestDao.findForAll(getInformationConditionForEntity(queryDto.getCondition()), getInformationForGroupByForEntity(queryDto.getGroupBy()), getInformationForOrderForEntity(queryDto.getOrderBy()), getInformationForSkip(queryDto.getSkip()), getInformationForLimit(queryDto.getLimit()));
        System.out.println(list);
    }

    public void getFieldSex(String query) {
        if (entityDao.findAll().isEmpty()) {
            test();
        }
        QueryDto queryDto = new QueryDto(query);
        queryDto.setLimit(3);
        queryDto.setSkip(0);
        List<SexDto> list = entityTestDao.findForFieldSex(getInformationConditionForEntity(queryDto.getCondition()), getInformationForGroupByForEntity(queryDto.getGroupBy()), getInformationForOrderForEntity(queryDto.getOrderBy()), getInformationForSkip(queryDto.getSkip()), getInformationForLimit(queryDto.getLimit()));
        System.out.println(list);
    }

    public void getFieldName(String query) {
        if (entityDao.findAll().isEmpty()) {
            test();
        }
        QueryDto queryDto = new QueryDto(query);
        queryDto.setLimit(3);
        queryDto.setSkip(0);
        List<NameDto> list = entityTestDao.findForFieldName(getInformationConditionForEntity(queryDto.getCondition()), getInformationForGroupByForEntity(queryDto.getGroupBy()), getInformationForOrderForEntity(queryDto.getOrderBy()), getInformationForSkip(queryDto.getSkip()), getInformationForLimit(queryDto.getLimit()));
        System.out.println(list);
    }

    public void getAllFieldsFromSubField(String query) {
        QueryDto queryDto = new QueryDto(query);
        queryDto.setLimit(3);
        queryDto.setSkip(0);
        List<ObjectDto> list = entityTestDao.findFieldsFromSubField(getInformationConditionForObject(queryDto.getCondition()), getInformationForGroupByForObject(queryDto.getGroupBy()), getInformationForOrderForObject(queryDto.getOrderBy()), getInformationForSkip(queryDto.getSkip()), getInformationForLimit(queryDto.getLimit()));
        System.out.println(list);
    }

    public void getSubFieldFirstName(String query) {
        QueryDto queryDto = new QueryDto(query);
        queryDto.setLimit(3);
        queryDto.setSkip(0);
        List<FirstNameDto> list = entityTestDao.findSubFieldFirstName(getInformationConditionForObject(queryDto.getCondition()), getInformationForGroupByForObject(queryDto.getGroupBy()), getInformationForOrderForObject(queryDto.getOrderBy()), getInformationForSkip(queryDto.getSkip()), getInformationForLimit(queryDto.getLimit()));
        System.out.println(list);
    }

    public void getSubFieldLastName(String query) {
        QueryDto queryDto = new QueryDto(query);
        queryDto.setLimit(3);
        queryDto.setSkip(0);
        List<LastNameDto> list = entityTestDao.findSubFieldLastName(getInformationConditionForObject(queryDto.getCondition()), getInformationForGroupByForObject(queryDto.getGroupBy()), getInformationForOrderForObject(queryDto.getOrderBy()), getInformationForSkip(queryDto.getSkip()), getInformationForLimit(queryDto.getLimit()));
        System.out.println(list);
    }

    private Integer getInformationForSkip(Integer skip) {
        return skip;
    }

    private Integer getInformationForLimit(Integer limit) {
        return limit;
    }

    private GroupByDto getInformationForGroupByForEntity(String groupBy) {
        GroupByDto groupByDto = new GroupByDto();
        groupByDto.setFirstFiled("sex");
        groupByDto.setSecondField("name");
        groupByDto.setThirdField("object");
        return groupByDto;
    }

    private SortDto getInformationForOrderForEntity(String orderBy) {
        SortDto sortDto = new SortDto();
        sortDto.setOrderByType(Sort.Direction.DESC);
        sortDto.setOrderByFields("name");
        return sortDto;
    }

    private ConditionDto getInformationConditionForEntity(String condition) {
        ConditionDto conditionDto = new ConditionDto();
        conditionDto.setFirstField("name");
        conditionDto.setFirstOperator("=");
        conditionDto.setFirstValue("lol");
        conditionDto.setExtended(true);
        conditionDto.setStandardLogicalOperation("OR");
        conditionDto.setSecondField("sex");
        conditionDto.setSecondOperator("<>");
        conditionDto.setSecondValue("M");
        return conditionDto;
    }


    private GroupByDto getInformationForGroupByForObject(String groupBy) {
        GroupByDto groupByDto = new GroupByDto();
        groupByDto.setFirstFiled("firstName");
        groupByDto.setSecondField("lastName");
        return groupByDto;
    }

    private SortDto getInformationForOrderForObject(String orderBy) {
        SortDto sortDto = new SortDto();
        sortDto.setOrderByType(Sort.Direction.DESC);
        sortDto.setOrderByFields("firstName");
        return sortDto;
    }

    private ConditionDto getInformationConditionForObject(String condition) {
        ConditionDto conditionDto = new ConditionDto();
        conditionDto.setFirstField("firstName");
        conditionDto.setFirstOperator("=");
        conditionDto.setFirstValue("first");
        conditionDto.setExtended(false);
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
    }
}
