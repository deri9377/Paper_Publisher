package com.paper.publisher.app.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paper.publisher.app.components.Comment;
import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.components.User;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class CommentServiceTest {
    
    @Autowired
    PostService postService;
    
    @Autowired
    UserService userService;

    @Autowired
    PaperService paperService;

    @Autowired
    CommentService commentService;

    @Test
    void getCommentById() {
        
        User user = userService.createUser(new User("bobby flay"));
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        Post post = postService.createPost(new Post(paper, user));
        Comment comment = commentService.createComment(new Comment(user, "This is the best paper ever"), post.getId());

        assertTrue(comment.equals(commentService.getCommentById(comment.getId())));
    } 

    @Test
    void getCommentsByUser() {

        User user = userService.createUser(new User("bobby flay"));
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        Post post = postService.createPost(new Post(paper, user));
        Comment comment = commentService.createComment(new Comment(user, "This is the best paper ever"), post.getId());

        assertTrue(commentService.getCommentsByUser(user.getId()).size() == 1);
    }


    @Test
    void getCommentsByPost() {

        User user = userService.createUser(new User("bobby flay"));
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        Post post = postService.createPost(new Post(paper, user));
        Comment comment = commentService.createComment(new Comment(user, "This is the best paper ever"), post.getId());

        assertTrue(commentService.getCommentsByPost(post.getId()).size() == 1);
    }

    @Test
    void createComment() {

       User user = userService.createUser(new User("bobby flay"));
        Paper paper = paperService.createPaper(new Paper(user, "Flavor Town", "flaver_town.txt"));
        Post post = postService.createPost(new Post(paper, user));
        Comment comment = commentService.createComment(new Comment(user, "This is the best paper ever"), post.getId());

        assertTrue(comment.equals(commentService.getComments().get(0)));
    }

    
}
