package com.example.DependecyInjectionEx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class VehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleApplication.class, args);
		System.out.println("Dependency Injection Example");

		ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");

		//Instantiate the obj1 via Constructor DI
		Vehicle obj1 = (Vehicle) context.getBean("InjectWithConstructor");

		//Instantiate the obj2 via Setter DI
		Vehicle obj2 = (Vehicle) context.getBean("InjectWithSetter");

		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(obj1 == obj2);
	}

}
