package com.paper.publisher.app.services;

import java.util.ArrayList;
import java.util.List;

import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.components.User;

public class PostService {
    
    private List<Post> posts;

    public PostService() {
        posts = new ArrayList<>();
    }

    private void populatePosts() {
        posts.add(new Post(new Paper(new User("Bobby Flay"), "The_Universe", "the_universe.txt")));
        posts.add(new Post(new Paper(new User("Miley Cyrus"), "The_Universe", "the_universe.txt")));
    }

}