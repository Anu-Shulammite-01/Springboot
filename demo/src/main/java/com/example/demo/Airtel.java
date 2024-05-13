package com.example.demo;

import org.springframework.stereotype.Component;

@Component("airtel")
public class Airtel implements Sim{
    @Override
    public void calling() {
        System.out.println("Airtel Calling");
    }

    @Override
    public void data() {
        System.out.println("Airtel Data");
    }
}