package com.musham.mySpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.module.Configuration;

/**
 * spring initializr
 * 	By using Spring initializer we have created this basic Spring Boot Application
 */
@SpringBootApplication
public class MySpringProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(MySpringProjectApplication.class, args);

		//We can replace above line below commented statement
//		SpringApplication.run(MySpringProjectApplication.class, args);

		System.out.println("Spring Boot Application Initialized with: " +
				context.getEnvironment().getActiveProfiles()[0]);
	}
}
