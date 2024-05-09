package com.paper.publisher.app.components;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Paper {

    private User author;
    private String title;
    private String filename;
    private final String id;

    @JsonCreator
    public Paper(@JsonProperty("user") User author, @JsonProperty("title") String title, @JsonProperty("filename") String filename) {
        this.author = author;
        this.title = title;
        this.filename = filename;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return this.id;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    
}


