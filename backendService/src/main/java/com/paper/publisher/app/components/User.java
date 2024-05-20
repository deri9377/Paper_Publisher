package com.paper.publisher.app.components;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author devin
 */
@Entity
@Table(name = "\"User\"") //Espcape User since user is a keyword in h2 database
public class User {
    
    private String name;
    @Id
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

    public User(){
        id = UUID.randomUUID().toString();
    }

    @JsonCreator
    public User(@JsonProperty("name") String name) {
        this.name = name;   
        id = UUID.randomUUID().toString();
    }

    public boolean equals(User user) {
        return name.equals(user.getName())  && id.equals(user.getId());
          
    }

    @Override
    public String toString() {
        return "The user's id is: " + this.getId() + "\nThe user's name is: " + this.getName();
    }

    
}
