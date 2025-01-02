package com.example.ApplicationContextEx;

import com.example.ApplicationContextEx.config.AppConfig;
import com.example.ApplicationContextEx.domain.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;

@SpringBootApplication
public class AcExampleApplication {

	public static void main(String[] args) {

		SpringApplication.run(AcExampleApplication.class, args);
		System.out.println("Example for Application Context");

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Student student = context.getBean(Student.class);

		System.out.println(student);
	}

}
