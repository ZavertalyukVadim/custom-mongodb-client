package com.dto;

public class GroupByDto {
    private String firstFiled;
    private String secondField;
    private String thirdField;

    public String getFirstFiled() {
        return firstFiled;
    }

    public void setFirstFiled(String firstFiled) {
        this.firstFiled = firstFiled;
    }

    public String getSecondField() {
        return secondField;
    }

    public void setSecondField(String secondField) {
        this.secondField = secondField;
    }

    public String getThirdField() {
        return thirdField;
    }

    public void setThirdField(String thirdField) {
        this.thirdField = thirdField;
    }

    @Override
    public String toString() {
        return "GroupByDto{" +
                "firstFiled='" + firstFiled + '\'' +
                ", secondField='" + secondField + '\'' +
                ", thirdField='" + thirdField + '\'' +
                '}';
    }
}
