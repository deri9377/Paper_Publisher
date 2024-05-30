package com.paper.publisher.app.controllers;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.services.PaperService;
import com.paper.publisher.app.services.PostService;
import com.paper.publisher.app.services.UserService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class PostController {
    
    @Autowired
    private PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    PaperService paperService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.ofNullable(null);
        }
    }
    
    @GetMapping("/posts/title/{title}")
    public ResponseEntity<List<Post>> getPostsByTitle(@PathVariable String title) {
        return ResponseEntity.ok(postService.getPostsByTitle(title));
    }

    @GetMapping("/posts/user/{id}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable String id) {
        return ResponseEntity.ok(postService.getPostsByUser(id));
    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post newPost, HttpServletRequest request) throws ServerException {
        
        Post post = postService.createPost(newPost);
        
        URI location = ServletUriComponentsBuilder.fromPath("post")
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        System.out.println("Post Created: " + " User: " + post.getUser().getId() + " Paper: " + post.getPaper().getId());

        return ResponseEntity.created(location).body(post);
    }


    @DeleteMapping(value="/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
    
}