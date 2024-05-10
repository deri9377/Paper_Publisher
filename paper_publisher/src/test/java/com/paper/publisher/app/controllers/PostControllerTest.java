package com.paper.publisher.app.controllers;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

import com.paper.publisher.app.components.Comment;
import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.components.User;
import com.paper.publisher.app.services.PostService;

import ch.qos.logback.core.util.COWArrayList;

@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
public class PostControllerTest {
    
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PostService postServiceMock;

    @BeforeEach
    public void setUp() {
      this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void getPosts() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);
        ArrayList<Post> posts = new ArrayList<>(){{add(post);}};
        
        when(postServiceMock.getPosts()).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(post.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].paper").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user").exists());
    }


    @Test
    void getPostById() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);

        when(postServiceMock.getPostById("12345")).thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.get("/post/12345")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(post.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.paper").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists());
    }


    @Test
    void getPostsByTitle() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);
        ArrayList<Post> posts = new ArrayList<>(){{add(post);}};

        when(postServiceMock.getPostsByTitle("Za Zorldo")).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/title/Za Zorldo")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(post.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].paper.title").value(paper.getTitle()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user").exists());
    }

    @Test
    void getPostsByUser() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);
        ArrayList<Post> posts = new ArrayList<>(){{add(post);}};

        when(postServiceMock.getPostsByUser("12345")).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/user/12345")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(post.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].paper").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user.id").value(user.getId()));
    }


    @Test
    void createPost() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);

        when(postServiceMock.createPost(any(Post.class))).thenReturn(post);

         mockMvc.perform(MockMvcRequestBuilders.post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""" 
                {
                    "paper": {
                        "user": {
                            "name": "Bobby Flay",
                            "id": "c4d99125-4800-481f-977d-f2f962629d87"
                        },
                        "title": "The_Universe",
                        "filename": "the_universe.txt",
                        "id": "e5e1afff-2e74-4d95-af85-5395a2b74fb0"
                    },
                    "user": {
                        "name": "Bobby Flay",
                        "id": "45040402-5839-4dac-9e9c-c57c8b616ea1"
                    },
                    "id": "c846751c-a4c9-4954-8ec8-bb53f22a2186",
                    "comments": []
                }
                    """)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", "post/" + post.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(post.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.paper").exists());
    }


    @Test
    void addComment() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);
        Comment comment = new Comment(user, "this book slaps");
        post.addComment(comment);

        when(postServiceMock.addComment(eq("12345"), any(Comment.class))).thenReturn(post);

         mockMvc.perform(MockMvcRequestBuilders.post("/post/12345")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""" 
                {
                    "user":{
                        "name":"Bobby Flay"
                    },
                    "message":"this book slaps"
                }
                    """)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", "post/" + post.getId() + "/comment"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(post.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.paper").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.comments").exists());
            
    }



}
