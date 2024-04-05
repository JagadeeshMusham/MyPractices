package com.amphora.engineering_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Admin This is main class which is used to start the application
 */

@SpringBootApplication
public class App {
	/**
	 * The main method
	 * 
	 * @param args
	 *            The arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("Hello Amphorian!");
	}
}
