package com.paper.publisher.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paper.publisher.app.components.User;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }


    public List<User> getUsers() {
        return this.users;
    }

    public User createUser(User user) {
        User newUser = new User(user.getName());
        users.add(newUser);
        return newUser;
    }

    public User getById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void removeUser(User user) {
        users.remove(user);
    }


}