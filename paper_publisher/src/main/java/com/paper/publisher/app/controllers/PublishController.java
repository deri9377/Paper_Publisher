package com.paper.publisher.app.controllers;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paper.publisher.app.components.Comment;
import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.services.PostService;

import jakarta.servlet.http.HttpServletRequest;





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
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.ofNullable(null);
        }
    }
    
    @GetMapping("/posts/title/{title}")
    public ResponseEntity<List<Post>> getPostByTitle(@PathVariable String title) {
        return ResponseEntity.ok(postService.getPostByTitle(title));
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post newPost, HttpServletRequest request) throws ServerException {
        
        Post post = postService.createPost(newPost);
        if (post != null) {
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(post.getId())
                    .toUri();
            return ResponseEntity.created(location).body(post);
        } else {
            throw new ServerException("Error in creating the Post resourse. Try again.");
        }
    }
    
    @PostMapping("/posts/{id}")
    public ResponseEntity<Post> postMethodName(@PathVariable String id, @RequestBody Comment comment, HttpServletRequest request) throws ServerException {
        
        Post post = postService.addComment(postService.getPostById(id), comment);
        if (post != null) {
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}/comment")
                    .buildAndExpand(post.getId())
                    .toUri();
            return ResponseEntity.created(location).body(post);
        } else {
            throw new ServerException("Error in adding the comment to the Post. Try again.");
        }
    }
    
}