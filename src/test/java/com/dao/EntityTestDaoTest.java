package com.dao;

import com.dto.EntityDto;
import com.dto.SqlDto;
import com.parser.Parser;
import org.junit.Assert;
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
public class EntityTestDaoTest {
    @Autowired
    private EntityTestDao entityTestDao;
    @Autowired
    private ObjectDao objectDao;

    @Test
    public void testFindForAll(){
        SqlDto query;
        Parser parser =  new Parser();
        String exampleSql = "SELECT * FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
        List<EntityDto> entity = new ArrayList<>();
        EntityDto entityDto= new EntityDto();
        entityDto.setName("lol");
        entityDto.setSex("M");
        entityDto.setObject(objectDao.getByLastName("last"));
        entity.add(entityDto);

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findForAll(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }
}