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
    public ResponseEntity<List<Comment>> getUsers() {
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

    @PostMapping(value = "/comment")
    public ResponseEntity<Comment> createUser(@RequestBody Comment newComment, HttpServletRequest request) throws ServerException {
        
        Comment comment = commentService.createComment(newComment);

        URI location = ServletUriComponentsBuilder.fromPath("comment")
                .path("/{id}")
                .buildAndExpand(comment.getId())
                .toUri();
                
        return ResponseEntity.created(location).body(comment);
    }


}
