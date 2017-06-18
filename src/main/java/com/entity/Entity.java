package com.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="entity")
public class Entity {
    @org.springframework.data.annotation.Id
    private
    String id;

    private
    String first_name;

    private
    String last_name;

    private
    String username;

    public Entity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
