package com.dto;

public class ConditionDto {
    private String firstField;
    private String firstOperator;
    private String firstValue;
    private String standardLogicalOperation;
    private Boolean extended;
    private String secondField;
    private String secondOperator;
    private String secondValue;

    public String getStandardLogicalOperation() {
        return standardLogicalOperation;
    }

    public void setStandardLogicalOperation(String standardLogicalOperation) {
        this.standardLogicalOperation = standardLogicalOperation;
    }

    public String getFirstField() {
        return firstField;
    }

    public void setFirstField(String firstField) {
        this.firstField = firstField;
    }

    public String getFirstOperator() {
        return firstOperator;
    }

    public void setFirstOperator(String firstOperator) {
        this.firstOperator = firstOperator;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public Boolean getExtended() {
        return extended;
    }

    public void setExtended(Boolean extended) {
        this.extended = extended;
    }

    public String getSecondField() {
        return secondField;
    }

    public void setSecondField(String secondField) {
        this.secondField = secondField;
    }

    public String getSecondOperator() {
        return secondOperator;
    }

    public void setSecondOperator(String secondOperator) {
        this.secondOperator = secondOperator;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public String toString() {
        return "ConditionDto{" +
                "firstField='" + firstField + '\'' +
                ", firstOperator='" + firstOperator + '\'' +
                ", firstValue='" + firstValue + '\'' +
                ", standardLogicalOperation='" + standardLogicalOperation + '\'' +
                ", extended=" + extended +
                ", secondField='" + secondField + '\'' +
                ", secondOperator='" + secondOperator + '\'' +
                ", secondValue='" + secondValue + '\'' +
                '}';
    }
}
