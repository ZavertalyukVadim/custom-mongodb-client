package com.service;

import com.dao.EntityDao;
import com.dao.ObjectDao;
import com.dto.EntityDto;
import com.dto.SqlDto;
import com.parser.Parser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class EntityServiceTest {
    @Autowired
    private EntityService entityService;
    @Autowired
    private ObjectDao objectDao;
    @Autowired
    private EntityDao repository;

    private void cleanDb() {
        repository.deleteAll();
    }

    @Before
    public void cleanAllDbBefore() {
        cleanDb();
    }

    @Test
    public void testGetAllFieldsFromEntity() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT * FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
        List<EntityDto> entity = new ArrayList<>();
        EntityDto entityDto = new EntityDto();
        entityDto.setName("lol");
        entityDto.setSex("M");
        entityDto.setObject(objectDao.getByLastName("last"));
        entity.add(entityDto);
        EntityDto entityDto2 = new EntityDto();
        entityDto2.setName("lol");
        entityDto2.setSex("W");
        entityDto2.setObject(objectDao.getByLastName("last"));
        entity.add(entityDto2);

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityService.getAllFieldsFromEntity(query)));

    }

    @After
    public void cleanAllDb() {
        cleanDb();
    }

}