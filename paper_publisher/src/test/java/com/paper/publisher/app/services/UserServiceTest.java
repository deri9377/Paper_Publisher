package com.paper.publisher.app.services;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paper.publisher.app.components.User;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;


    @Test
    void createUserSuccessful() {
        User user = userService.createUser(new User("bobby flay"));
        assertTrue(userService.getById(user.getId()).equals(user));
    }


    @Test
    void newUserAddedToList() {
        User user = userService.createUser(new User("bobby flay"));
        List<User> users = userService.getUsers();
        assertTrue(user.equals(users.get(0)));
    }

    @Test
    void removeUserFromList() {
        User user = userService.createUser(new User("bobby flay"));
        userService.removeUser(user.getId());
        assertNull(userService.getById(user.getId()));
    }

    
}
