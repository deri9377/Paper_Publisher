package com.paper.publisher.app.controllers;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.paper.publisher.app.components.User;
import com.paper.publisher.app.services.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;



@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
public class UserControllerTest {
    
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userServiceMock;

    @BeforeEach
    public void setUp() {
      this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void getUserById() throws Exception {

        User user = new User("Bobby Flay");

        when(userServiceMock.getById("12345")).thenReturn(user);


        mockMvc.perform(MockMvcRequestBuilders
  			    .get("/users/12345")
  			    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bobby Flay"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()));
    }


    @Test
    void getUsers() throws Exception {
        
        User user = new User("Bobby Flay");

        when(userServiceMock.getUsers()).thenReturn(new ArrayList<User>(){{add(user);}});

        mockMvc.perform(MockMvcRequestBuilders
  			    .get("/users")
  			    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$..name").value("Bobby Flay"))
            .andExpect(MockMvcResultMatchers.jsonPath("$..id").value(user.getId()));

    }



    @Test
    void createNewUser() throws Exception {
        
        User user = new User("Bobby Flay");
        
        when(userServiceMock.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Bobby Flay\" }")
                .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isCreated())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(header().string("Location", "user/" + user.getId()))
           .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bobby Flay")) 
           .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()));
        
        
    }
    
}
