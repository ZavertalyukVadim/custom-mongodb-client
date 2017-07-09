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

    public void getAllFieldsFromEntity(SqlDto query) {

        if (entityDao.findAll().isEmpty()) {
            test();
        }
        List<EntityDto> list = entityTestDao.findForAll(getInformationConditionForEntity(query.getConditionDto()), getInformationForGroupByForEntity(query.getGroupByDto()), getInformationForOrderForEntity(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
        System.out.println(list);
    }

    public void getFieldSex(SqlDto query) {
        if (entityDao.findAll().isEmpty()) {
            test();
        }
        List<SexDto> list = entityTestDao.findForFieldSex(getInformationConditionForEntity(query.getConditionDto()), getInformationForGroupByForEntity(query.getGroupByDto()), getInformationForOrderForEntity(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
        System.out.println(list);
    }

    public void getFieldName(SqlDto query) {
        if (entityDao.findAll().isEmpty()) {
            test();
        }
        List<NameDto> list = entityTestDao.findForFieldName(getInformationConditionForEntity(query.getConditionDto()), getInformationForGroupByForEntity(query.getGroupByDto()), getInformationForOrderForEntity(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
        System.out.println(list);
    }

    public void getAllFieldsFromSubField(SqlDto query) {

        List<ObjectDto> list = entityTestDao.findFieldsFromSubField(getInformationConditionForObject(query.getConditionDto()), getInformationForGroupByForObject(query.getGroupByDto()), getInformationForOrderForObject(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
        System.out.println(list);
    }

    public void getSubFieldFirstName(SqlDto query) {
        List<FirstNameDto> list = entityTestDao.findSubFieldFirstName(getInformationConditionForObject(query.getConditionDto()), getInformationForGroupByForObject(query.getGroupByDto()), getInformationForOrderForObject(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
        System.out.println(list);
    }

    public void getSubFieldLastName(SqlDto query) {
        List<LastNameDto> list = entityTestDao.findSubFieldLastName(getInformationConditionForObject(query.getConditionDto()), getInformationForGroupByForObject(query.getGroupByDto()), getInformationForOrderForObject(query.getSortDto()), getInformationForSkip(query.getSkip()), getInformationForLimit(query.getLimit()));
        System.out.println(list);
    }

    private Integer getInformationForSkip(Integer skip) {
        return skip;
    }

    private Integer getInformationForLimit(Integer limit) {
        return limit;
    }

    private GroupByDto getInformationForGroupByForEntity(GroupByDto groupByDto) {
//        GroupByDto groupByDto = new GroupByDto();
//        groupByDto.setFirstFiled("sex");
//        groupByDto.setSecondField("name");
//        groupByDto.setThirdField("object");
        return groupByDto;
    }

    private SortDto getInformationForOrderForEntity(SortDto sortDto) {
//        SortDto sortDto = new SortDto();
//        sortDto.setOrderByType(Sort.Direction.DESC);
//        sortDto.setOrderByFields("name");
        return sortDto;
    }

    private ConditionDto getInformationConditionForEntity(ConditionDto conditionDto) {
//        ConditionDto conditionDto = new ConditionDto();
//        conditionDto.setFirstField("name");
//        conditionDto.setFirstOperator("=");
//        conditionDto.setFirstValue("lol");
//        conditionDto.setExtended(true);
//        conditionDto.setStandardLogicalOperation("OR");
//        conditionDto.setSecondField("sex");
//        conditionDto.setSecondOperator("<>");
//        conditionDto.setSecondValue("M");
        return conditionDto;
    }


    private GroupByDto getInformationForGroupByForObject(GroupByDto groupByDto) {
//        GroupByDto groupByDto = new GroupByDto();
//        groupByDto.setFirstFiled("firstName");
//        groupByDto.setSecondField("lastName");
        return groupByDto;
    }

    private SortDto getInformationForOrderForObject(SortDto sortDto) {
//        SortDto sortDto = new SortDto();
//        sortDto.setOrderByType(Sort.Direction.DESC);
//        sortDto.setOrderByFields("firstName");
        return sortDto;
    }

    private ConditionDto getInformationConditionForObject(ConditionDto conditionDto) {
//        ConditionDto conditionDto = new ConditionDto();
//        conditionDto.setFirstField("firstName");
//        conditionDto.setFirstOperator("=");
//        conditionDto.setFirstValue("first");
//        conditionDto.setExtended(false);
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
