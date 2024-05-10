package com.paper.publisher.app.components;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Comment")
public class Comment {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String message;
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;

    public Comment() {
        this.id = UUID.randomUUID().toString();
    }

    @JsonCreator
    public Comment(@JsonProperty("user") User user,@JsonProperty("message") String message) {
        this.user = user;
        this.message = message;
        this.id = UUID.randomUUID().toString();
    }


    public User getUser() {
        return this.user;
    }

    public String getId() {
        return this.id;
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

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
}
