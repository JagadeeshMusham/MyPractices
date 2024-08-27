package com.musham.mySpringProject.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HealthCheckController {
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

}
