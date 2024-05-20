package com.paper.publisher.app.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

import com.paper.publisher.app.components.Comment;
import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.components.User;
import com.paper.publisher.app.services.CommentService;

@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
public class CommentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CommentService commentServiceMock;

    @BeforeEach
    public void setUp() {
      this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getPosts() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);
        Comment comment = new Comment(user, "This is the best Paper Ive read");
        comment.setPostId(post.getId());
        List<Comment> comments = new ArrayList<>(){{add(comment);}};
        
        when(commentServiceMock.getComments()).thenReturn(comments);

        mockMvc.perform(MockMvcRequestBuilders.get("/comments")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(comment.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].text").value(comment.getText()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].postId").value(comment.getPostId()));
    }

    @Test
    void getCommentById() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);
        Comment comment = new Comment(user, "This is the best Paper Ive read");
        comment.setPostId(post.getId());
        
        when(commentServiceMock.getCommentById("12345")).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders.get("/comment/12345")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(comment.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(comment.getText()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.postId").value(comment.getPostId()));
    }

    @Test
    void getCommentsByUser() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);
        Comment comment = new Comment(user, "This is the best Paper Ive read");
        comment.setPostId(post.getId());
        List<Comment> comments = new ArrayList<>(){{add(comment);}};
        
        when(commentServiceMock.getCommentsByUser("12345")).thenReturn(comments);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/12345/comments")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(comment.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].text").value(comment.getText()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].postId").value(comment.getPostId()));
    }


    @Test
    void getCommentsByPost() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);
        Comment comment = new Comment(user, "This is the best Paper Ive read");
        comment.setPostId(post.getId());
        List<Comment> comments = new ArrayList<>(){{add(comment);}};
        
        when(commentServiceMock.getCommentsByPost("12345")).thenReturn(comments);

        mockMvc.perform(MockMvcRequestBuilders.get("/post/12345/comments")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(comment.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].text").value(comment.getText()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].postId").value(comment.getPostId()));
    }

    @Test
    void createPost() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        Post post = new Post(paper, user);
        Comment comment = new Comment(user, "This is the best Paper Ive read");
        comment.setPostId(post.getId());
        

        when(commentServiceMock.createComment(any(Comment.class), eq("12345"))).thenReturn(comment);

         mockMvc.perform(MockMvcRequestBuilders.post("/post/12345/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(""" 
                {
                    "user": {
                        "name": "Bobby Flay",
                        "id": "45040402-5839-4dac-9e9c-c57c8b616ea1"
                    },
                    "id": "c846751c-a4c9-4954-8ec8-bb53f22a2186",
                    "message":"This is the best Paper Ive read",
                    "postId": "%s"
                }
                    """, post.getId()))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", "/post/12345/comment/" + comment.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(comment.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.postId").exists());
    }
}
