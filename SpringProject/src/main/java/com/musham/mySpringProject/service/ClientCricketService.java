package com.musham.mySpringProject.service;

import com.musham.mySpringProject.api.response.CricketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientCricketService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String api = "https://cricbuzz-cricket.p.rapidapi.com/teams/v1/international";

    // Create RestTemplate instance
    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<?> GetInformation() {
        ResponseEntity<?> responseEntity;
        try {

            // Set up the headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-rapidapi-host", "cricbuzz-cricket.p.rapidapi.com");
            headers.set("x-rapidapi-key", "65b33c20bemsh62b8681e1673b40p15bf96jsnc4060ec61864");
            headers.set("Accept", "application/json"); // Request JSON response

            // Create an entity with the headers
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Define the URL
            String url = "https://cricbuzz-cricket.p.rapidapi.com/teams/v1/international";

            // Make the GET request
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, CricketResponse.class);

            // Print the response
            System.out.println("Response: " + responseEntity.getBody());
        } catch (
                HttpClientErrorException e) {
            System.err.println("Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (
                Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
