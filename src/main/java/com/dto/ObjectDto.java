package com.dto;


public class ObjectDto {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        ObjectDto objectDto = (ObjectDto) o;
        if (this.getFirstName() == o) return true;
        return (getFirstName() != null ? getFirstName().equals(objectDto.getFirstName()) : objectDto.getFirstName() == null) && (getLastName() != null ? getLastName().equals(objectDto.getLastName()) : objectDto.getLastName() == null);
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
    }
}
