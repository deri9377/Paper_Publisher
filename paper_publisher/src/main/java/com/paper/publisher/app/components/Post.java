package com.paper.publisher.app.components;

import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
    
    private Paper paper;
    private User user;
    private String Id;
    private ArrayList<Comment> comments;

    @JsonCreator
    public Post(@JsonProperty("paper") Paper paper, @JsonProperty("user") User user) {
        this.paper = paper;
        this.user = user;
        this.comments = new ArrayList<>();
         Id = UUID.randomUUID().toString();
    }

    public Paper getPaper() {
        return this.paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public ArrayList<Comment> getCommnets() {
        return this.comments;
    }

    public void setCommnets(ArrayList<Comment> commnets) {
        this.comments = commnets;
    }


    public String getId() {
        return Id;
    }

    public User getUser() {
        return user;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }


    
}   
