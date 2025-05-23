package org.musham;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);
        System.out.println("The Spring Boot Application got started");
    }
}