package com.musham.spring_boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * public void methodName(@PathVariable("name") String name)
public void methodName(@RequestBody Class object)

URL:		http://localhost:8080/welcome

@RequestMapping(path="", produces="application/xml", consumes="{application/json"})

 */
@RestController
public class Controller {
	@RequestMapping(path = "/welcome")
	public String getWelcomeMessage() {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(20);
//		String encodedTest = bCryptPasswordEncoder.encode("test");
//		System.out.println("encodedTest: " + encodedTest);

		return "Welcome to my first spring boot application: ";
		

	}
	
	@GetMapping("/welcome/{personName}")
	public String getWelcomeMessageWithArgument(@PathVariable("personName") String personName) {
		return "welcome " + personName + " to my first spring boot application";
	}

	@GetMapping("/welcome/{personName}/{age}")
	public String getWelcomeMessageWithArguments(@PathVariable("personName") String personName, @PathVariable("age") int age) {
		return "welcome " + personName + "(" + age + ") to my first spring boot application";
	}
	
	@PostMapping("/welcome")
	public String getWelcomeMessageWithInput(@RequestBody String input) {
		return "Welcome to my first spring boot application with post method with input data: " + input;
	}

}
