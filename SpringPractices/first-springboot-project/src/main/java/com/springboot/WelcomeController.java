package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.WelcomeService;

@RestController
public class WelcomeController {

	@Autowired
	private WelcomeService service;
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return service.getWelcomeMessage();
	}
}

