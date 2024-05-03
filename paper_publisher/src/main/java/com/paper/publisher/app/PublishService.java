package com.paper.publisher.app;

import org.springframework.stereotype.Service;

import com.paper.publisher.app.components.Post;

@Service
public class PublishService {
    
    private Post post;

    public void publishPost() {
        // TODO publish paper to the database
        System.out.println(post.getPaper().getTitle() + " paper was published " + post.getPaper().getAuthor().getName());
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}