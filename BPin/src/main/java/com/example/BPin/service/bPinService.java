package com.example.BPin.service;

import com.example.BPin.entity.bPin;
import com.example.BPin.repository.bPinRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
@Slf4j
public class bPinService implements UserDetailsService {
    @Autowired
    private bPinRepo repo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<bPin> userDetail = repo.findByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(bPinDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));

    }
    //BPIN
    public String addBPinUser(bPin user) {
        repo.save(user);
        return "User Added Successfully";
    }

    //Random UDID
    public static String generateRandomUDID() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[16];
        secureRandom.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }

    public String UDID = generateRandomUDID();

    //To generate string
    public ResponseEntity<String> generateUdid(bPin user) {
        Optional<bPin> userOptional = repo.findByUsername(user.getUsername());

        if (userOptional.isEmpty()) {
            log.info("User not found");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
        }

        bPin foundUser = userOptional.get();

        if (!user.getUsername().equals(foundUser.getUsername())) {
            log.info("Entered username does not match the name in db");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username");
        }

        if (!foundUser.getBpin().equals(user.getBpin())) {
            log.info("BPin does not match for user: " + user.getUsername());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
        }
        bPin udid1 = new bPin(foundUser.getUsername(), foundUser.getBpin(), UDID);
        repo.save(udid1);
        return ResponseEntity.ok(UDID);
    }

    //To generate Token using Random UDID string
    public ResponseEntity<String> authenticateBPin(bPin user) {
        Optional<bPin> userOptional = repo.findByUsername(user.getUsername());

        if (userOptional.isEmpty()) {
            log.info("User not found in db");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
        }

        bPin foundUser = userOptional.get();

        if (!user.getUsername().equals(foundUser.getUsername())) {
            log.info("Entered username does not match the name in database");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username");
        }

        if (!foundUser.getUdid().equals(user.getUdid())) {
            log.info("BPin does not match for user: " + user.getUsername());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
        }

        String token = jwtService.generateToken(foundUser.getUsername());
        return ResponseEntity.ok(token);
    }
}

