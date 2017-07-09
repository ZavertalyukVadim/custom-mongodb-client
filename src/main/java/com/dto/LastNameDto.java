package com.dto;


public class LastNameDto {
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "LastNameDto{" +
                "lastName='" + lastName + '\'' +
                '}';
    }
}
