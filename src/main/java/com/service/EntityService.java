package com.service;

import com.dao.EntityDao;
import com.dao.EntityTestDao;
import com.dto.*;
import com.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService {
    private final EntityDao entityDao;
    private final EntityTestDao entityTestDao;

    @Autowired
    public EntityService(EntityDao entityDao, EntityTestDao entityTestDao) {
        this.entityDao = entityDao;
        this.entityTestDao = entityTestDao;
    }

    public void getAllEntity(String query) {
        if (entityDao.findAll().isEmpty()) {
            test();
        }
        QueryDto queryDto =  new QueryDto(query);
        queryDto.setLimit(3);
        queryDto.setSkip(1);
        List<EntityDto> list= entityTestDao.find(getInformationCondition(queryDto.getCondition()), getInformationForGroupBy(queryDto.getGroupBy()), getInformationForOrder(queryDto.getOrderBy()), getInformationForSkip(queryDto.getSkip()), getInformationForLimit(queryDto.getLimit()));
        System.out.println(list);
    }

    private Integer getInformationForSkip(Integer skip) {
        return skip;
    }

    private Integer getInformationForLimit(Integer limit) {
        return limit;
    }

    private GroupByDto getInformationForGroupBy(String groupBy) {
        GroupByDto groupByDto = new GroupByDto();
        groupByDto.setFirstFiled("name");
        groupByDto.setSecondField("age");
        return groupByDto;
    }

    private SortDto getInformationForOrder(String orderBy) {
        SortDto sortDto = new SortDto();
        sortDto.setOrderByType(Sort.Direction.DESC);
        sortDto.setOrderByFields("age");
        return sortDto;
    }

    private ConditionDto getInformationCondition(String condition) {
        ConditionDto conditionDto = new ConditionDto();
        conditionDto.setField("name");
        conditionDto.setOperator("=");
        conditionDto.setValue("lol");
        return conditionDto;
    }

    private void test() {
        Entity entity = new Entity();
        entity.setName("lol");
        entity.setAge(10);
        entityDao.save(entity);
        Entity entity2 = new Entity();
        entity2.setName("lol1");
        entity2.setAge(102);
        entityDao.save(entity2);
        Entity entity3 = new Entity();
        entity3.setName("lol2");
        entity3.setAge(103);
        entityDao.save(entity3);
    }
}
