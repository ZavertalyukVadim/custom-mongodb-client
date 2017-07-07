package com.dto;

import com.entity.Object;

public class EntityDto {
    private String name;
    private Integer age;
    private Object object ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "EntityDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", object=" + object +
                '}';
    }
}
