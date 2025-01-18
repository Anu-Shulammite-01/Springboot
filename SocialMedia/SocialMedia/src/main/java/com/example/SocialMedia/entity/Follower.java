package com.example.SocialMedia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long followerId;
    private Long followingId;

    @ManyToOne
    @JoinColumn(name = "followerId", insertable = false, updatable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followingId", insertable = false, updatable = false)
    private User following;


    public void setFollower(User follower) {
        this.follower = follower;
    }


    public void setFollowing(User following) {
        this.following = following;
    }
}
