package com.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@DataMongoTest
public class EntityTestDaoTest {
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private EntityDao entityTestDao;

    @Test
    public void createTest() throws Exception {


        assertThat(null, is(entityTestDao.findAll()));
    }

}