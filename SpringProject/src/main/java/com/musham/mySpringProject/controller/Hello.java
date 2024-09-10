package com.musham.mySpringProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Hello {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/external-data")
    public String getExternalData() {
        String url = "https://api.example.com/data";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
