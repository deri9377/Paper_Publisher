package com.paper.publisher.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paper.publisher.app.components.Comment;
import com.paper.publisher.app.components.Post;
import com.paper.publisher.app.repository.PostRepository;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
       return postRepository.save(post);
    }

    public List<Post> getPostsByUser(String id) {
        List<Post> postsByUser = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            if (post.getUser().getId().equals(id)) {
                postsByUser.add(post);
            }
        }
        return postsByUser;
    }

    public List<Post> getPostsByTitle(String title) {
        List<Post> postsByTitle = new ArrayList<>();
        for (Post post: postRepository.findAll()) {
            if (post.getPaper().getTitle().equals(title)) {
                postsByTitle.add(post);
            }
        }
        return postsByTitle;
    }

    public Post getPostById(String id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return null;
        }
        return post.get();
    }

    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        for (Post post: postRepository.findAll()) {
            posts.add(post);
        }
        return posts;
    }

    public Post addComment(String id, Comment comment) {
        Post post = getPostById(id);
        return post;

    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

}