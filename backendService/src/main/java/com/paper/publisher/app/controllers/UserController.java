package com.paper.publisher.app.controllers;
import java.net.URI;
import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paper.publisher.app.components.User;
import com.paper.publisher.app.services.UserService;

import jakarta.servlet.http.HttpServletRequest;




@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        User user = userService.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ofNullable(null);
        }
    }

    @GetMapping(value = "/user/name/{name}")
    public ResponseEntity<User> getByName(@PathVariable String name) {
        User user = userService.getByName(name);
        if (user != null) {
            System.out.println("Getting User: " + user.getName() + ", " + user.getId());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ofNullable(null);
        }
    }


    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@RequestBody User newUser, HttpServletRequest request) throws ServerException {
        
        User user = userService.createUser(newUser);

        URI location = ServletUriComponentsBuilder.fromPath("user")
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        System.out.println("User created: " + user.getName());
                
        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping(value="/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    
}