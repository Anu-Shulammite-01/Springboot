package com.example.JWTAuthenticate.repository;

import com.example.JWTAuthenticate.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
//    @Query("SELECT u FROM UserInfo u WHERE u.name = ?1 COLLATE utf8_bin")
    Optional<UserInfo> findByName(String name);
}
