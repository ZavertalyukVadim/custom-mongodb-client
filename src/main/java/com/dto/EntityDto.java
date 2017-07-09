package com.dto;

import com.entity.Object;

public class EntityDto {
    private String name;
    private String sex;
    private Object object ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
                ", sex=" + sex +
                ", object=" + object +
                '}';
    }
}
