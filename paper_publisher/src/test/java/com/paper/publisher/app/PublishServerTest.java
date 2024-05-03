package com.paper.publisher.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.components.User;

@SpringBootTest
public class PublishServerTest {

    @Autowired
    private PublishService publisher;

    private User user = new User("Bobby Flay");
	private Paper paper = new Paper(user, "The Universe", "the_universe.pdf");

    
    @Test
    void publishPaperTest__Success() {
        publisher.setPost(new Post(paper));
		assertTrue(publisher.getPost().getPaper().getAuthor().getName().equals(user.getName()));
        assertTrue(publisher.getPost().getPaper().getTitle().equals(paper.getTitle()));

    }
}
