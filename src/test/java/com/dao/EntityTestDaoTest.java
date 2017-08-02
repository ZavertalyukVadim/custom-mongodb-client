package com.dao;

import com.dto.*;
import com.parser.Parser;
import com.service.EntityService;
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
public class EntityTestDaoTest {
    @Autowired
    private EntityTestDao entityTestDao;
    @Autowired
    private ObjectDao objectDao;
    @Autowired
    private EntityDao repository;
    @Autowired
    private EntityService entityService;

    private void cleanDb() {
        repository.deleteAll();
    }

    @Before
    public void cleanAllDbBefore() {
        cleanDb();
    }

    @Test
    public void testFindForAll() {
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

        Assert.assertThat(entity, is(entityTestDao.findForAll(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindForAllForCondition() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT * FROM entity WHERE name = `lo1` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
        List<EntityDto> entity = new ArrayList<>();

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findForAll(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindForAllForOrderAsk() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT * FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
        List<EntityDto> list = new ArrayList<>();
        EntityDto entityDto = new EntityDto();
        entityDto.setName("lol");
        entityDto.setSex("M");
        entityDto.setObject(objectDao.getByLastName("last"));
        list.add(entityDto);
        EntityDto entityDto2 = new EntityDto();
        entityDto2.setName("lol");
        entityDto2.setSex("W");
        entityDto2.setObject(objectDao.getByLastName("last"));
        list.add(entityDto2);

        query = parser.parse(exampleSql);

        Assert.assertThat(list.get(0), is(entityTestDao.findForAll(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit()).get(0)));

    }

    @Test
    public void testFindForAllForOrderDesk() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT * FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name DESC LIMIT 3 OFFSET 0";
        List<EntityDto> list = new ArrayList<>();
        EntityDto entityDto2 = new EntityDto();
        entityDto2.setName("lol");
        entityDto2.setSex("W");
        entityDto2.setObject(objectDao.getByLastName("last"));
        list.add(entityDto2);
        EntityDto entityDto = new EntityDto();
        entityDto.setName("lol");
        entityDto.setSex("M");
        entityDto.setObject(objectDao.getByLastName("last"));
        list.add(entityDto);

        query = parser.parse(exampleSql);

        Assert.assertThat(list.get(0), is(entityTestDao.findForAll(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit()).get(0)));

    }

    @Test
    public void testFindForAllForSkip() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT * FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 1";
        List<EntityDto> list = new ArrayList<>();
        EntityDto entityDto2 = new EntityDto();
        entityDto2.setName("lol");
        entityDto2.setSex("W");
        entityDto2.setObject(objectDao.getByLastName("last"));
        list.add(entityDto2);

        query = parser.parse(exampleSql);

        Assert.assertThat(list, is(entityTestDao.findForAll(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindForAllForSkipAll() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT * FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 2";
        List<EntityDto> list = new ArrayList<>();

        query = parser.parse(exampleSql);

        Assert.assertThat(list, is(entityTestDao.findForAll(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindForAllForLimit() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT * FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name ASC LIMIT 1 OFFSET 0";
        List<EntityDto> list = new ArrayList<>();
        EntityDto entityDto2 = new EntityDto();
        entityDto2.setName("lol");
        entityDto2.setSex("W");
        entityDto2.setObject(objectDao.getByLastName("last"));
        list.add(entityDto2);

        query = parser.parse(exampleSql);

        Assert.assertThat(list, is(entityTestDao.findForAll(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindForAllNotFound() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT * FROM entity WHERE name = `khe` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
        List<EntityDto> entity = new ArrayList<>();

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findForAll(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindForFieldSex() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT sex FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
        List<SexDto> entity = new ArrayList<>();
        SexDto entityDto2 = new SexDto();
        entityDto2.setSex("M");
        entity.add(entityDto2);
        SexDto entityDto = new SexDto();
        entityDto.setSex("W");
        entity.add(entityDto);

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findForFieldSex(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindForFieldSexNotFound() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT sex FROM entity WHERE name = `khe` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
        List<SexDto> entity = new ArrayList<>();

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findForFieldSex(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindForFieldName() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT name FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
        List<NameDto> entity = new ArrayList<>();
        NameDto nameDto = new NameDto();
        nameDto.setName("lol");
        entity.add(nameDto);
        NameDto nameDto1 = new NameDto();
        nameDto1.setName("lol");
        entity.add(nameDto1);

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findForFieldName(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindForFieldNameNotFound() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT name FROM entity WHERE name = `khe` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
        List<NameDto> entity = new ArrayList<>();

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findForFieldName(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindFieldOnSubField() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT entity.* FROM entity WHERE firstName = `first` GROUP BY firstName, lastName ORDER BY firstName ASC LIMIT 3 OFFSET 0";
        List<ObjectDto> entity = new ArrayList<>();
        ObjectDto objectDto = new ObjectDto();
        objectDto.setFirstName("first");
        objectDto.setLastName("last");
        entity.add(objectDto);

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findFieldOnSubField(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindFieldOnSubFieldNotFound() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT entity.* FROM entity WHERE firstName = `khe` GROUP BY firstName, lastName ORDER BY firstName ASC LIMIT 3 OFFSET 0";
        List<ObjectDto> entity = new ArrayList<>();

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findFieldOnSubField(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindSubFieldLastName() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT object.lastName FROM entity WHERE firstName = `first` GROUP BY firstName, lastName ORDER BY firstName ASC LIMIT 3 OFFSET 0";
        List<LastNameDto> entity = new ArrayList<>();
        LastNameDto lastNameDto = new LastNameDto();
        lastNameDto.setLastName("last");
        entity.add(lastNameDto);

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findSubFieldLastName(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindSubFieldLastNameNotFound() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT object.lastName FROM entity WHERE firstName = `khe` GROUP BY firstName, lastName ORDER BY firstName ASC LIMIT 3 OFFSET 0";
        List<LastNameDto> entity = new ArrayList<>();

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findSubFieldLastName(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindSubFieldFirstName() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT object.firstName FROM entity WHERE firstName = `first` GROUP BY firstName, lastName ORDER BY firstName ASC LIMIT 3 OFFSET 0";
        List<FirstNameDto> entity = new ArrayList<>();
        FirstNameDto firstNameDto = new FirstNameDto();
        firstNameDto.setFirstName("first");
        entity.add(firstNameDto);

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findSubFieldFirstName(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @Test
    public void testFindSubFieldFirstNameNotFound() {
        if (entityService.checkEmptyDb()) {
            entityService.addTestData();
        }
        SqlDto query;
        Parser parser = new Parser();
        String exampleSql = "SELECT object.firstName FROM entity WHERE firstName = `khe` GROUP BY firstName, lastName ORDER BY firstName ASC LIMIT 3 OFFSET 0";
        List<FirstNameDto> entity = new ArrayList<>();

        query = parser.parse(exampleSql);

        Assert.assertThat(entity, is(entityTestDao.findSubFieldFirstName(query.getConditionDto(), query.getGroupByDto(), query.getSortDto(), query.getSkip(), query.getLimit())));

    }

    @After
    public void cleanAllDb() {
        cleanDb();
    }
}