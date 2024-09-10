package com.musham.mySpringProject.controller;

import com.musham.mySpringProject.service.ClientCricketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/external-data")
public class ClientCricketController {

    @Autowired
    ClientCricketService cricketService;

    @GetMapping
    public ResponseEntity<?> getExternalData() {
        try {
            return cricketService.GetInformation();

//            // Create RestTemplate instance
//            RestTemplate restTemplate = new RestTemplate();
//
//            // Set up the headers
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("x-rapidapi-host", "cricbuzz-cricket.p.rapidapi.com");
//            headers.set("x-rapidapi-key", "65b33c20bemsh62b8681e1673b40p15bf96jsnc4060ec61864");
//            headers.set("Accept", "application/json"); // Request JSON response
//
//            // Create an entity with the headers
//            HttpEntity<String> entity = new HttpEntity<>(headers);
//
//            // Define the URL
//            String url = "https://cricbuzz-cricket.p.rapidapi.com/teams/v1/international";
//
//            // Make the GET request
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//
//            // Print the response
//            System.out.println("Response: " + response.getBody());
//
//            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

        } catch (HttpClientErrorException e) {
            System.err.println("Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
