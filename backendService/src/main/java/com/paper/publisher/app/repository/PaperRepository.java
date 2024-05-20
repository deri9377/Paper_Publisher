package com.paper.publisher.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paper.publisher.app.components.Paper;

@Repository
public interface PaperRepository extends CrudRepository<Paper, String> {
    
}
