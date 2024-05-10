package com.paper.publisher.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paper.publisher.app.components.Comment;
import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.components.User;

@SpringBootTest
public class PostServiceTest {
    

    @Autowired
    PostService postService;

    @BeforeEach
    public void cleanUp() {
        postService.getPosts().clear();
    }


    @Test
    void createNewPost() {
        User user = new User("bobby flay");
        Paper paper = new Paper(user, "Flavor Town", "flaver_town.txt");
        Post post = postService.createPost(new Post(paper, user));

        assertTrue(postService.getPostById(post.getId()).equals(post));
    }

    @Test
    void getPostByUser() {
        User user = new User("bobby flay");
        Paper paper = new Paper(user, "Flavor Town", "flaver_town.txt");
        postService.createPost(new Post(paper, user));
        postService.createPost(new Post(paper, user));

        assertTrue(postService.getPostsByUser(user.getId()).size() == 2);
    }

    @Test
    void getPostsByTitle() {
        User user = new User("bobby flay");
        Paper paper = new Paper(user, "Flavor Town", "flaver_town.txt");
        postService.createPost(new Post(paper, user));
        postService.createPost(new Post(paper, user));

        assertTrue(postService.getPostsByTitle("Flavor Town").size() == 2);
    }

    @Test
    void getPostById() {
        User user = new User("bobby flay");
        Paper paper = new Paper(user, "Flavor Town", "flaver_town.txt");
        Post post = postService.createPost(new Post(paper, user));

        assertEquals(post, postService.getPostById(post.getId()));
    }

    @Test
    void addComment() {
        User user = new User("bobby flay");
        Paper paper = new Paper(user, "Flavor Town", "flaver_town.txt");
        Post post = postService.createPost(new Post(paper, user));
        post = postService.addComment(post.getId(), new Comment(user, "This is the wost paper ive ever read"));
        
        assertTrue(post.getComments().size() == 1);
    }

    @Test
    void removePost() {
        User user = new User("bobby flay");
        Paper paper = new Paper(user, "Flavor Town", "flaver_town.txt");
        Post post = postService.createPost(new Post(paper, user));
        postService.removePost(post);

        assertNull(postService.getPostById(post.getId()));
    }


}
