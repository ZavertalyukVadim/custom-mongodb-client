package com.dto;


public class SexDto {
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "SexDto{" +
                "sex='" + sex + '\'' +
                '}';
    }
}
