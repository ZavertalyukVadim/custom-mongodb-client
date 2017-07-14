package com.dao;

import com.entity.Entity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class ExampleDataMongoNonEmbeddedTests {
    @Autowired
    private EntityDao repository;

    private Entity dog, cat, bird, human, bear;
    private String id;
    private void cleanDb(){

        repository.deleteAll();
    }

    private void flushCategories() {
        dog = new Entity();
        dog.setName("dog");
        cat = new Entity();
        cat.setName("cat");
        bird = new Entity();
        bird.setName("bird");
        human = new Entity();
        human.setName("human");
        bear = new Entity();
        bear.setName("bear");

        this.repository.save(dog);
        this.repository.save(cat);
        this.repository.save(bird);
        this.repository.save(human);
        this.repository.save(bear);

        id = dog.getId();

        Assert.assertThat(id, is(notNullValue()));
        Assert.assertThat(cat.getId(), is(notNullValue()));
        Assert.assertThat(bird.getId(), is(notNullValue()));
        Assert.assertThat(human.getId(), is(notNullValue()));
        Assert.assertThat(bear.getId(), is(notNullValue()));

        Assert.assertThat(repository.exists(dog.getId()), is(true));
        Assert.assertThat(repository.exists(cat.getId()), is(true));
        Assert.assertThat(repository.exists(bird.getId()), is(true));
        Assert.assertThat(repository.exists(human.getId()), is(true));
        Assert.assertThat(repository.exists(bear.getId()), is(true));
    }

    @Test
    public void deleteAll() throws Exception {
        List<Entity> allCategories = this.repository.findAll();
        repository.deleteAll();
        List<Entity> entities = new ArrayList<>();
        Assert.assertThat(entities, is(repository.findAll()));
    }

    @Test
    public void createTest() throws Exception {
        cleanDb();
        flushCategories();
        final List<Entity> allCategories = this.repository.findAll();
        Long count = allCategories.stream().count();
        cleanDb();
        flushCategories();

        Assert.assertThat(this.repository.count(), is(count));
    }

    @Test
    public void testRead() throws Exception {
        cleanDb();
        flushCategories();

        Entity foundCategory = repository.findOne(id);
        Assert.assertThat(dog.getName(), is(foundCategory.getName()));
    }

    @Test
    public void testSavingEmptyCollectionIsNoOp() throws Exception {
        List<Entity> result = repository.save(new ArrayList<>());
        Assert.assertThat(result, is(notNullValue()));
        Assert.assertThat(result.isEmpty(), is(true));
    }

    @Test
    public void testUpdate() throws Exception {
        cleanDb();
        flushCategories();

        Entity foundCategory = repository.findOne(id);
        foundCategory.setName("super tag");
        repository.save(foundCategory);
        Entity updatedCategory = repository.findOne(id);
        Assert.assertThat(updatedCategory.getName(), is(foundCategory.getName()));
    }

    @Test
    public void testDelete() throws Exception {
        cleanDb();
        flushCategories();

        repository.delete(dog);
        Assert.assertThat(repository.exists(id), is(false));
        Assert.assertThat(repository.findOne(id), is(nullValue()));
    }

    @Test
    public void testDeleteTagById() throws Exception {
        cleanDb();
        flushCategories();

        repository.delete(dog.getId());
        Assert.assertThat(repository.exists(id), is(false));
        Assert.assertThat(repository.findOne(id), is(nullValue()));
    }

    @Test
    public void testDeleteAll() throws Exception {
        cleanDb();
        flushCategories();

        repository.deleteAll();
        Assert.assertThat(repository.count(), is(0L));
    }

//    @Test
//    public void testReturnsAllSortedCorrectly() throws Exception {
//        flushCategories();
//
//        List<Entity> result = repository.findAll(new Sort(ASC, "name"));
//        Assert.assertThat(result, is(notNullValue()));
//        Assert.assertThat(result.size(), is(5));
//        Assert.assertThat(result.get(0), is(bear));
//        Assert.assertThat(result.get(1), is(human));
//        Assert.assertThat(result.get(2), is(dog));
//        Assert.assertThat(result.get(3), is(bird));
//        Assert.assertThat(result.get(4), is(cat));
//    }

//    @Test
//    public void testFindByName() throws Exception {
//        flushCategories();
//
//        List<Entity> byName = repository.findByName("cat");
//
//        Assert.assertThat(byName.size(), is(1));
//        Assert.assertThat(byName.get(0), is(cat));
//    }

    @Test
    public void testFindByNameNotFound() throws Exception {
        cleanDb();
        flushCategories();

        List<Entity> byName = repository.findByName("sun");

        Assert.assertThat(byName.size(), is(0));
        Assert.assertThat(byName.isEmpty(), is(true));
    }

    @Test
    public void testCountsCorrectly() throws Exception {
        long count = repository.count();

        Entity entity = new Entity();
        entity.setName("God");
        repository.save(entity);

        Assert.assertThat(repository.count() == count + 1, is(true));
    }

    @Test
    public void testExecutesNotLikeCorrectly() throws Exception {
        cleanDb();
        flushCategories();

        List<Entity> result = repository.findByNameNotLike("%ee1%");
        Assert.assertThat(result.size(), is(5));
        cleanDb();
    }
}
