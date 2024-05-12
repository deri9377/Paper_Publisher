package com.paper.publisher.app.components;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Post")
public class Post {
    
    @OneToOne
    private Paper paper;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Id
    private String id;
    @OneToMany
	@JoinColumn(name = "post_id",referencedColumnName = "id")
    private List<Comment> comments;

    public Post() {
        id = UUID.randomUUID().toString();
    }

    @JsonCreator
    public Post(@JsonProperty("paper") Paper paper, @JsonProperty("user") User user) {
        this.paper = paper;
        this.user = user;
        id = UUID.randomUUID().toString();
    }

    public Paper getPaper() {
        return this.paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }


    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public boolean equals(Post post) {
        return id.equals(post.getId());
    }

    
}   
