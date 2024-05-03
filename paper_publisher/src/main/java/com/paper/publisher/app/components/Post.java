package com.paper.publisher.app.components;

import java.util.ArrayList;

public class Post {
    
    private Paper paper;
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

    public Post(Paper paper) {
        this.paper = paper;
    }

    
}   
