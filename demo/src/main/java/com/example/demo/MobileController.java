package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileController {

    @Autowired
    private Sim sim;


    @GetMapping("/makeCall")
    public String makeCall(){
        sim.calling();
        return "Success";
    }

    @GetMapping("/showData")
    public String showData(){
        sim.data();
        return "Success";
    }

    @PostMapping("/setSim")
    public String setSim(@RequestBody String simName){
        if ("jio".equalsIgnoreCase(simName)) {
            this.sim = new Jio();
        } else if ("airtel".equalsIgnoreCase(simName)) {
            this.sim = new Airtel();
        }
        System.out.println("Set successfully");
        return "Success";
    }
}
