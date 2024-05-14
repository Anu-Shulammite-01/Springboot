package com.example.BeanFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class BeanFactoryExApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeanFactoryExApplication.class, args);
		System.out.println("Example for BeanFactory");

		//creating object for Spring Container
		BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
		Student student = (Student) factory.getBean("student");

		System.out.println(student);
	}

}
