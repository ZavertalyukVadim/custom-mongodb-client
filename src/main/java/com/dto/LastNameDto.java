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
        return "{" +
                "lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LastNameDto that = (LastNameDto) o;

        return getLastName() != null ? getLastName().equals(that.getLastName()) : that.getLastName() == null;
    }

    @Override
    public int hashCode() {
        return getLastName() != null ? getLastName().hashCode() : 0;
    }
}
