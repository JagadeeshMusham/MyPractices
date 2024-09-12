package com.musham.mySpringProject.service;

import com.musham.mySpringProject.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientWeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String api = "https://weatherapi-com.p.rapidapi.com/current.json?q=Hyderabad%2C%20Telangana";

    // Create RestTemplate instance
    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<?> getWeather() {
        ResponseEntity<?> responseEntity;
        try {

            // Set up the headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-rapidapi-host", "weatherapi-com.p.rapidapi.com");
            headers.set("x-rapidapi-key", apiKey);
            headers.set("Accept", "application/json"); // Request JSON response

            // Create an entity with the headers
            HttpEntity<String> entity = new HttpEntity<>(headers);


            // Make the GET request
            responseEntity = restTemplate.exchange(api, HttpMethod.GET, entity, WeatherResponse.class);

            // Print the response
            System.out.println("Response: " + responseEntity.getBody());

        } catch (HttpClientErrorException e) {
            System.err.println("Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
