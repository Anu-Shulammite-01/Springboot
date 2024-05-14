package com.example.ApplicationContextEx.config;

import com.example.ApplicationContextEx.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    //Method
    public Student student(){
        return new Student(1,"Anu");
    }
}
