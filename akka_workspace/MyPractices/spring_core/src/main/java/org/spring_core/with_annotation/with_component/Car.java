package org.spring_core.with_annotation.with_component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Car implements Vehicle {

	@Override
	public String toString() {
		return "Car is working";
	}

	public void driving() {
		System.out.println("Car in driving");
		
	}
}
