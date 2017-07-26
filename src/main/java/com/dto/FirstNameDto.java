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
        return "{" +
                "firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirstNameDto that = (FirstNameDto) o;

        return getFirstName() != null ? getFirstName().equals(that.getFirstName()) : that.getFirstName() == null;
    }

    @Override
    public int hashCode() {
        return getFirstName() != null ? getFirstName().hashCode() : 0;
    }
}
