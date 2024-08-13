package org.spring_core.with_annotation.without_component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean
	public Car getcar()
	{
		return new Car();
	}
	
	@Bean
	public Bike getBike()
	{
		return new Bike();
	}
}
