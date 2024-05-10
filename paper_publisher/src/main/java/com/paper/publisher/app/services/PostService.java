package com.paper.publisher.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paper.publisher.app.components.Comment;
import com.paper.publisher.app.components.Paper;
import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.components.User;

@Service
public class PostService {
    
    private List<Post> posts;

    public PostService() {
        posts = new ArrayList<>();
        populatePosts();
    }

    private void populatePosts() {
        posts.add(new Post(new Paper(new User("Bobby Flay"), "The_Universe", "the_universe.txt"), new User("Bobby Flay")));
        posts.add(new Post(new Paper(new User("Miley Cyrus"), "On_Tour", "On_Tour.txt"), new User("Miley Cyrus")));
    }

    public Post createPost(Post newPost) {
        Post post = new Post(newPost.getPaper(), newPost.getUser());
        posts.add(post);
        return post;
    }

    public List<Post> getPostsByUser(String id) {
        List<Post> postsByUser = new ArrayList<>();
        for (Post post : posts) {
            if (post.getUser().getId().equals(id)) {
                postsByUser.add(post);
            }
        }
        return postsByUser;
    }

    public List<Post> getPostsByTitle(String title) {
        List<Post> postsByTitle = new ArrayList<>();
        for (Post post: posts) {
            if (post.getPaper().getTitle().equals(title)) {
                postsByTitle.add(post);
            }
        }
        return postsByTitle;
    }

    public Post getPostById(String Id) {
        for (Post post: posts) {
            if (post.getId().equals(Id)) {
                return post;
            }
        }
        return null;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post addComment(String id, Comment comment) {
        Post post = getPostById(id);
        post.addComment(comment);
        return post;

    }

    public void removePost(Post post) {
        posts.remove(post);
    }

}