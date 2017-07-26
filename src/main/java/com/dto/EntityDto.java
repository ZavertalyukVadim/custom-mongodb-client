package com.dto;

import com.entity.Object;

import java.util.Objects;

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
        return "{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", object=" + object +
                '}';
    }

//    @Override
//    public boolean equals(java.lang.Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        EntityDto entityDto = (EntityDto) o;
//
//        if (getName() != null ? !getName().equals(entityDto.getName()) : entityDto.getName() != null) return false;
//        if (getSex() != null ? !getSex().equals(entityDto.getSex()) : entityDto.getSex() != null) return false;
//        return getObject() != null ? getObject().equals(entityDto.getObject()) : entityDto.getObject() == null;
//    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;

        EntityDto entityDto = (EntityDto) o;
        return this.getName() == ((EntityDto) o).getName() && this.getSex() == ((EntityDto) o).getSex() && this.getObject() == ((EntityDto) o).getObject() || Objects.equals(this.getName(), ((EntityDto) o).getName()) || (getName() != null ? getName().equals(entityDto.getName()) : entityDto.getName() == null) && (getName() != null ? getName().equals(entityDto.getName()) : entityDto.getName() == null);
    }
}
