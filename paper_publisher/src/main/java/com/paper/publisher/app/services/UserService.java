package com.paper.publisher.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paper.publisher.app.components.User;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<User>() {{
            add(new User("Bobby Flay"));
            add(new User("Bill Cosby"));
            add(new User("Miley Cyrus"));
        }};
    }


    public List<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Optional<User> getById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return Optional.ofNullable(user);
            }
        }
        return null;
    }

}