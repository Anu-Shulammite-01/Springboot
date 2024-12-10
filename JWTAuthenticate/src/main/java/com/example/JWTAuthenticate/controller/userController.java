package com.example.JWTAuthenticate.controller;

import com.example.JWTAuthenticate.entity.AuthReq;
import com.example.JWTAuthenticate.entity.UserInfo;
import com.example.JWTAuthenticate.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class userController {

    @Autowired
    private UserInfoService service;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthReq userInfo) {
        return service.authenticateByUsernameAndPassword(userInfo);

    }
    @PostMapping("/MPinToken")
    public ResponseEntity<String> authenticateMPinAndGetToken(@RequestBody AuthReq userInfo) {
        return service.authenticateMPin(userInfo);

    }
}
