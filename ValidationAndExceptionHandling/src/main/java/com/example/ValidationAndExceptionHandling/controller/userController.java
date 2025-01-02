package com.example.ValidationAndExceptionHandling.controller;

import com.example.ValidationAndExceptionHandling.entity.User;
import com.example.ValidationAndExceptionHandling.service.userService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private userService service;

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user){
        log.info("User Added");
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        return ResponseEntity.ok(service.getUserById(id));
    }

}
