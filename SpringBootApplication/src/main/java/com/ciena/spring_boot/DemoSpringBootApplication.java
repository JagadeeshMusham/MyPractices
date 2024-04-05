package com.ciena.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
		
		SpringApplication.run(AdditionController.class, args);

		System.out.println("First Spring boot");
	}

}


@RestController
class AdditionController {

    @GetMapping("/add")
    public String addNumbers(
            @RequestParam(name = "num1") int num1,
            @RequestParam(name = "num2") int num2) {
        int sum = num1 + num2;
        return "The sum of " + num1 + " and " + num2 + " is: " + sum;
    }
}

