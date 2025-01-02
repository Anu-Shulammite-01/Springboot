package com.example.AopExample.controller;

import com.example.AopExample.entity.User;
import com.example.AopExample.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/getUsers")
    public List<User> getAllUser(){
        return userService.getUser();
    }
}
