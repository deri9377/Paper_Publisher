package com.paper.publisher.app.components;

import java.util.UUID;


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

    public User(String name) {
        this.name = name;   
        Id = UUID.randomUUID().toString();
    }

    
}
