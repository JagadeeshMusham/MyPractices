package com.service;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService {
	public String getWelcomeMessage()
	{

		return "this is Spring boot application";
	}
}
