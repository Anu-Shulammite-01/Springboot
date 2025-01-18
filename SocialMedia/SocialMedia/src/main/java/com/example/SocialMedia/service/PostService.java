package com.example.SocialMedia.service;

import com.example.SocialMedia.entity.Post;
import com.example.SocialMedia.entity.User;
import com.example.SocialMedia.repository.PostRepository;
import com.example.SocialMedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PostService {
    private static final Logger logger = Logger.getLogger(PostService.class.getName());

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createPost(Long userId, Post post) {

        if (post.getContent() == null || post.getContent().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Content cannot be empty");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        post.setUser(user);
        logger.info("Creating post for user: " + user.getUsername());
        try {
            return postRepository.save(post);
        } catch (Exception e) {
            logger.info("Error while saving post: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create post");
        }
    }

    public List<Post> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        postRepository.delete(post);
    }
}
