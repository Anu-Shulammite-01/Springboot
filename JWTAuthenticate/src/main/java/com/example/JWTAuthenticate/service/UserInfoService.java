package com.example.JWTAuthenticate.service;

import com.example.JWTAuthenticate.entity.AuthReq;
import com.example.JWTAuthenticate.repository.UserInfoRepo;
import com.example.JWTAuthenticate.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepo repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByName(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public ResponseEntity<String> authenticateByUsernameAndPassword(AuthReq user) {
        Optional<UserInfo> userOptional = repository.findByName(user.getUsername());

        if (userOptional.isEmpty()) {
            log.info("User not found in the database");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
        }

        UserInfo foundUser = userOptional.get();

        if (!user.getUsername().equals(foundUser.getName())) {
            log.info("Entered username does not match the name in the database");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username");
        }

        String userEnteredPassword = user.getPassword();
        String storedHashedPassword = foundUser.getPassword();
        if (!encoder.matches(userEnteredPassword, storedHashedPassword)) {
            log.info("Password does not match for user: " + user.getUsername());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
        }

        String token = jwtService.generateToken(foundUser.getName());
        return ResponseEntity.ok(token);
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }


    //M-pin authentication
    public ResponseEntity<String> authenticateMPin(AuthReq user) {
        Optional<UserInfo> userOptional = repository.findByName(user.getUsername());

        if (userOptional.isEmpty()) {
            log.info("User not found in the db");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
        }

        UserInfo foundUser = userOptional.get();

        if (!user.getUsername().equals(foundUser.getName())) {
            log.info("Entered username does not match the name in the db");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username");
        }

        if (!foundUser.getMpin().equals(user.getMpin())) {
            log.info("MPin does not match for user: " + user.getUsername());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
        }

        String token = jwtService.generateToken(foundUser.getName());
        return ResponseEntity.ok(token);
    }

}
