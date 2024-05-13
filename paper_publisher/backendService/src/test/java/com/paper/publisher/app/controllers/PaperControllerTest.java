package com.paper.publisher.app.controllers;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
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

import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.User;
import com.paper.publisher.app.services.PaperService;

/**
 *
 * @author devin
 */
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
public class PaperControllerTest {
    
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PaperService paperServiceMock;

    @BeforeEach
    public void setUp() {
      this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void getPapers() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");
        ArrayList<Paper> papers = new ArrayList<>();
        papers.add(paper);

        when(paperServiceMock.getAllPapers()).thenReturn(papers);

        mockMvc.perform(MockMvcRequestBuilders.get("/papers")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user.id").value(user.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(paper.getTitle()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(paper.getId()));
    }


    @Test
    void getPapersById() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Zorldo", "dio.txt");

        when(paperServiceMock.getPaperById("12345")).thenReturn(paper);

        mockMvc.perform(MockMvcRequestBuilders.get("/papers/12345")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.user.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(user.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(paper.getTitle()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(paper.getId()));
    }


    @Test
    void getPapersByTitle() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Worldo", "dio.txt");
        ArrayList<Paper> papers = new ArrayList<>();
        papers.add(paper);

        when(paperServiceMock.getPapersByTitle("Za Worldo")).thenReturn(papers);

        mockMvc.perform(MockMvcRequestBuilders.get("/papers/title/Za Worldo")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user.id").value(user.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(paper.getTitle()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(paper.getId()));
    }


    @Test
    void getPapersByUser() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Worldo", "dio.txt");
        ArrayList<Paper> papers = new ArrayList<>();
        papers.add(paper);

        when(paperServiceMock.getPapersByUser("12345")).thenReturn(papers);

        mockMvc.perform(MockMvcRequestBuilders.get("/papers/user/12345")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user.id").value(user.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(paper.getTitle()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(paper.getId()));
    }


    @Test
    void createPost() throws Exception {

        User user = new User("Bobby Flay");
        Paper paper = new Paper(user, "Za Worldo", "dio.txt");

        when(paperServiceMock.createPaper(any(Paper.class))).thenReturn(paper);

        mockMvc.perform(MockMvcRequestBuilders.post("/paper")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""" 
                    {
                        \"user\":{
                            \"name\":\"Bobby Flay\"
                        },
                        \"title\":\"Za Worldo\",
                        \"filename\":\"dio.txt\"
                    }
                    """)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", "paper/" + paper.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.user.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(user.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(paper.getTitle()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(paper.getId()));
    }
}
