package com.paper.publisher.app.components;

public class Comment {

    private User user;
    private String text;

    public Comment(User user, String text) {
        this.user = user;
        this.text = text;
    }


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
}
