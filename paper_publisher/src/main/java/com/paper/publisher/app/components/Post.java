package com.paper.publisher.app.components;

import java.util.ArrayList;
import java.util.UUID;

public class Post {
    
    private Paper paper;
    private User user;
    private String Id;
    private ArrayList<Comment> commnets;

    public Paper getPaper() {
        return this.paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public ArrayList<Comment> getCommnets() {
        return this.commnets;
    }

    public void setCommnets(ArrayList<Comment> commnets) {
        this.commnets = commnets;
    }

    public Post(Paper paper, User user) {
        this.paper = paper;
        this.user = user;
         Id = UUID.randomUUID().toString();
    }

    public String getId() {
        return Id;
    }

    public User getUser() {
        return user;
    }


    
}   
