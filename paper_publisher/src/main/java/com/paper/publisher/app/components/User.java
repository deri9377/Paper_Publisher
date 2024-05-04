package com.paper.publisher.app.components;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
    
    private String name;
    private String Id;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.Id;
    }

    @JsonCreator
    public User(@JsonProperty("name") String name) {
        this.name = name;   
        Id = UUID.randomUUID().toString();
    }

    
}
