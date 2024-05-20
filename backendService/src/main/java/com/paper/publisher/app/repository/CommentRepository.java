package com.paper.publisher.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paper.publisher.app.components.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {
    
}
