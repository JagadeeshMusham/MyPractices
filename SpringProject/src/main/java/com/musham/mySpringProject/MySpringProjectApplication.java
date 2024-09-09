package com.musham.mySpringProject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.module.Configuration;

/**
 * spring initializr
 * 	By using Spring initializer we have created this basic Spring Boot Application
 */
@SpringBootApplication
@Slf4j
public class MySpringProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(MySpringProjectApplication.class, args);

		log.trace("This is trace log");
		log.debug("This is debug log");
		log.info("This is info log");
		log.warn("This is warn log");
		log.error("This is error log");
		//We can replace above line below commented statement
//		SpringApplication.run(MySpringProjectApplication.class, args);

		System.out.println("Spring Boot Application Initialized with: " +
				context.getEnvironment().getActiveProfiles()[0]);
	}
}
