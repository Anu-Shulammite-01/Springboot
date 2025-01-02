package com.example.AopExample.service;

import com.example.AopExample.entity.User;
import com.example.AopExample.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getUser(){
        return userRepo.findAll();
    }
}
