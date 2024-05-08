package com.paper.publisher.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.services.PostService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
public class PublishController {
    
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
    
    @GetMapping("/posts/title/{title}")
    public ResponseEntity<List<Post>> getPostByTitle(@PathVariable String title) {
        return ResponseEntity.ok(postService.getPostByTitle(title));
    }
    
}