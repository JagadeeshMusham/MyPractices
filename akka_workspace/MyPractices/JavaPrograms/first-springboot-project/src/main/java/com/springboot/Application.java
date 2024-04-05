package com.springboot;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class Application {
	
	public static void main(String [] args)
	{
		
		try {
			ApplicationContext context = (ApplicationContext) SpringApplication.run(Application.class, args);
			if (context == null) {
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
