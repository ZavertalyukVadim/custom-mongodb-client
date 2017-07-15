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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SexDto sexDto = (SexDto) o;

        return getSex() != null ? getSex().equals(sexDto.getSex()) : sexDto.getSex() == null;
    }

    @Override
    public int hashCode() {
        return getSex() != null ? getSex().hashCode() : 0;
    }
}
