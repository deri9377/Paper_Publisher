package com.paper.publisher.app.controllers;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paper.publisher.app.components.Comment;
import com.paper.publisher.app.services.CommentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/comments")
    public ResponseEntity<List<Comment>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }


    @GetMapping(value = "/comment/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        Comment comment = commentService.getCommentById(id);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.ofNullable(null);
        }
    }

    @GetMapping(value = "/user/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getCommentsByUser(id));
    }


    @GetMapping(value = "/post/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getCommentsByPost(id));
    }

    @PostMapping(value = "/post/{id}/comment")
    public ResponseEntity<Comment> createUser(@RequestBody Comment newComment, @PathVariable String id, HttpServletRequest request) throws ServerException {
        
        Comment comment = commentService.createComment(newComment, id);

        URI location = ServletUriComponentsBuilder.fromPath("/post/" + id)
                .path("/comment/{id}")
                .buildAndExpand(comment.getId())
                .toUri();
                
        return ResponseEntity.created(location).body(comment);
    }


}
