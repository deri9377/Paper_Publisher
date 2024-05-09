package com.paper.publisher.app.components;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
    
    private String name;
    private final String id;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    @JsonCreator
    public User(@JsonProperty("name") String name) {
        this.name = name;   
        id = UUID.randomUUID().toString();
    }

    public boolean equals(User user) {
        return name.equals(user.getName())  && id.equals(user.getId());
          
    }

    
}
