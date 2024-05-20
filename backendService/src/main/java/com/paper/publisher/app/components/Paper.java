package com.paper.publisher.app.components;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Paper")
public class Paper {

    @Id
    private final String id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String title;
    @Lob
    private String file;
    

    public Paper() {
        this.id = UUID.randomUUID().toString();
    }
    
    @JsonCreator
    public Paper(@JsonProperty("user") User author, @JsonProperty("title") String title, @JsonProperty("filename") String filename) {
        this.user = author;
        this.title = title;
        this.file = filename;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String filename) {
        this.file = filename;
    }

    public boolean equals(Paper paper) {
        return id.equals(paper.getId());
    }   

    
}


