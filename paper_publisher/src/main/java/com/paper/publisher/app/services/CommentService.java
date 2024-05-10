package com.paper.publisher.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paper.publisher.app.components.Comment;
import com.paper.publisher.app.repository.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().forEach(comments::add);
        return comments;
    }

    public Comment getCommentById(String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            return null;
        }
        return comment.get();
    }

    public List<Comment> getCommentsByUser(String id) {
        List<Comment> comments = new ArrayList<>();
        for (Comment comment:getComments()) {
            if (comment.getUser().getId().equals(id)) {
                comments.add(comment);
            }
        }
        return comments;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
