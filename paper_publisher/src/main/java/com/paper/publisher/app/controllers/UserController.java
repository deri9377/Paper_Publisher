package com.paper.publisher.app.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.paper.publisher.app.components.User;
import com.paper.publisher.app.services.UserService;



@Controller
public class UserController {

    @Autowired
    UserService userService;

    
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
    
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        Optional<User> user = userService.getById(id);
        return user.map(ResponseEntity::ok)
      	    .orElseThrow();
    }
    
}