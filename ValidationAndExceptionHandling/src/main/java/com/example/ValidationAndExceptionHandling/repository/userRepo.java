package com.example.ValidationAndExceptionHandling.repository;

import com.example.ValidationAndExceptionHandling.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
}
