package com.paper.publisher.app.components;

import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
    
    private Paper paper;
    private User user;
    private String id;
    private ArrayList<Comment> comments;

    @JsonCreator
    public Post(@JsonProperty("paper") Paper paper, @JsonProperty("user") User user) {
        this.paper = paper;
        this.user = user;
        this.comments = new ArrayList<>();
         id = UUID.randomUUID().toString();
    }

    public Paper getPaper() {
        return this.paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void setCommnets(ArrayList<Comment> comments) {
        this.comments = comments;
    }


    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public boolean equals(Post post) {
        return id.equals(post.getId());
    }

    
}   
