package com.example.BPin.controller;

import com.example.BPin.entity.bPin;
import com.example.BPin.service.bPinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@Slf4j
public class bPinController {
    @Autowired
    private bPinService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addBPinUser")
    public String addBPinUser(@RequestBody bPin user) {
        return service.addBPinUser(user);
    }

    @PostMapping("/getUdid")
    public ResponseEntity<String> generateUdidString(@RequestBody bPin user) {
        return service.generateUdid(user);
    }
    @PostMapping("/BPinToken")
    public ResponseEntity<String> authenticateBPinAndGetToken(@RequestBody bPin user) {
        return service.authenticateBPin(user);
    }

    @GetMapping("/authenticate")
    public String authenticateBPin() {
        return "Welcome ";
    }
}
