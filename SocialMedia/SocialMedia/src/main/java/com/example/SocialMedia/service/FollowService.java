package com.example.SocialMedia.service;

import com.example.SocialMedia.entity.Follower;
import com.example.SocialMedia.entity.User;
import com.example.SocialMedia.repository.FollowerRepository;
import com.example.SocialMedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private UserRepository userRepository;

    // Follow a user (create a follower-following relationship)
    public Follower followUser(Long followerId, Long followingId) {
        // Validate existence of users
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Follower not found"));

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Following not found"));

        // Check if the follower is the same as the following user (avoid self-following)
        if (followerId.equals(followingId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot follow yourself");
        }

        // Check if the follower is already following the user
        if (followerRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already following this user");
        }

        // Create a new Follower entity
        Follower followerEntity = new Follower();
        followerEntity.setFollower(follower);
        followerEntity.setFollowing(following);

        // Save and return the follower relationship
        return followerRepository.save(followerEntity);
    }

    // Unfollow a user (remove the follower-following relationship)
    public String unfollowUser(Long followerId, Long followingId) {
        // Validate existence of users
        userRepository.findById(followerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Follower not found"));

        userRepository.findById(followingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Following not found"));

        // Find the follower-following relationship
        Follower followerEntity = followerRepository.findByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not following this user"));

        // Delete the follower relationship
        followerRepository.delete(followerEntity);
        return "Successfully unfollowed user with ID: " + followingId;
    }

    // Get list of followers for a user
    public List<User> getFollowers(Long userId) {
        // Validate user existence
        userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Get followers of the user
        List<Long> followerIds = followerRepository.findFollowerIdsByFollowingId(userId);
        return userRepository.findAllById(followerIds);
    }

    // Get list of users a user is following
    public List<User> getFollowing(Long userId) {
        // Validate user existence
        userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Get following users
        List<Long> followingIds = followerRepository.findFollowingIdsByFollowerId(userId);
        return userRepository.findAllById(followingIds);
    }
}
