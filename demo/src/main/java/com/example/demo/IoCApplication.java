package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IoCApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoCApplication.class, args);
		System.out.println("This is an example for IoC with Dependency Injection!");
	}

}
