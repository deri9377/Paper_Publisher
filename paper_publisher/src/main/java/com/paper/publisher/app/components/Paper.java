package com.paper.publisher.app.components;


public class Paper {

    private User author;
    private String title;
    private String filename;

    public Paper(User author, String title, String filename) {
        this.author = author;
        this.title = title;
        this.filename = filename;
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


