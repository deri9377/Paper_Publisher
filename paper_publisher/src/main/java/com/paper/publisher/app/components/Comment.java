package com.paper.publisher.app.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    private User user;
    private String message;

    @JsonCreator
    public Comment(@JsonProperty("user") User user,@JsonProperty("message") String message) {
        this.user = user;
        this.message = message;
    }


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return this.message;
    }

    public void setText(String text) {
        this.message = text;
    }
    
    
}
