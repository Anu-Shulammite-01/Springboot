package com.example.SocialMedia.repository;

import com.example.SocialMedia.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    Optional<Follower> findByFollowerIdAndFollowingId(Long followerId, Long followingId);

    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);

    List<Long> findFollowerIdsByFollowingId(Long userId);

    List<Long> findFollowingIdsByFollowerId(Long userId);
}