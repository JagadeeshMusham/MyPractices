package com.musham.mySpringProject.controller;

import com.musham.mySpringProject.service.ClientWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/weather-data")
public class ClientWeatherController {

    @Autowired
    ClientWeatherService weatherService;

    @GetMapping
    public ResponseEntity<?> getWeatherData() {
        try {
            return weatherService.getWeather();
        } catch (HttpClientErrorException e) {
            System.err.println("Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
