package com.dto;


import org.springframework.data.domain.Sort;

public class SortDto {
    private Sort.Direction orderByType;
    private String orderByFields;

    public Sort.Direction getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(Sort.Direction orderByType) {
        this.orderByType = orderByType;
    }

    public String getOrderByFields() {
        return orderByFields;
    }

    public void setOrderByFields(String orderByFields) {
        this.orderByFields = orderByFields;
    }
}
