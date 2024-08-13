package com.musham.secureappwithpostgre;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String home()
	{
		LoggerFactory.getLogger(HomeController.class);
		System.out.println("First page");
		return "home.jsp";
	}
}
