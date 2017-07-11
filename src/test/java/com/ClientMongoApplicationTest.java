package com;

import com.dto.SqlDto;
import com.service.EntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ClientMongoApplicationTest {
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private EntityService entityService;

    @Test
    public void createTest() throws Exception {
        SqlDto sqlDto = new SqlDto();

        assertThat(null, is(entityService.getFieldName(sqlDto)));
    }
}