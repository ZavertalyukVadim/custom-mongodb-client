package com.dto;


public class FirstNameDto {
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "FirstNameDto{" +
                "firstName='" + firstName + '\'' +
                '}';
    }
}
