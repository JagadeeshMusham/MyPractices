package com.musham.mySpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring initializr
 * 	By using Spring initializer we have created this basic Spring Boot Application
 */
@SpringBootApplication
public class MySpringProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(MySpringProjectApplication.class, args);

		System.out.println("Spring Boot Application Initialized");
	}
}
