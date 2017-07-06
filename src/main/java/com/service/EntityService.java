package com.service;

import com.dao.EntityDao;
import com.dao.EntityTestDao;
import com.dto.ConditionDto;
import com.dto.EntityDto;
import com.dto.SortDto;
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

    public List<EntityDto> getAllEntity() {
        if (entityDao.findAll().isEmpty()) {
            test();
        }
        String select = "*";//
        String condition="";
        String groupBy = "`age` and `name`";
        String orderBy = "name";
        Integer skip =1 ;
        Integer limit=3;
        return entityTestDao.find(getCondition(condition),groupBy, getInformationForOrder(orderBy),skip,limit);
    }
    private SortDto  getInformationForOrder(String orderBy){
        SortDto sortDto =  new SortDto();
        sortDto.setOrderByType(Sort.Direction.DESC);
        sortDto.setOrderByFields("age");
        return sortDto;
    }
    private ConditionDto getCondition(String condition){
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
