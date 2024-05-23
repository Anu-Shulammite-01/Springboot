package com.example.JWTAuthenticate.repository;

import com.example.JWTAuthenticate.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);
}
