package com.paper.publisher.app.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paper.publisher.app.components.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}