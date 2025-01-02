package com.example.ValidationAndExceptionHandling.service;

import com.example.ValidationAndExceptionHandling.entity.User;
import com.example.ValidationAndExceptionHandling.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    @Autowired
    private userRepo userrepo;

    public User saveUser(User user){
        return userrepo.save(user);
    }

    public List<User> getAllUsers(){
        return userrepo.findAll();
    }

    public User getUserById(Integer id){
        return userrepo.findUserById(id);
    }
}
