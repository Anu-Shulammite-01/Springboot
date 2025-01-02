package com.example.AopExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AopExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopExampleApplication.class, args);
	}

}
