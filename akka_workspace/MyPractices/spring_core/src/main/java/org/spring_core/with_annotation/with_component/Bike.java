package org.spring_core.with_annotation.with_component;

import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle {

	public void driving() {
		System.out.println("Bike in driving");
	}

	@Override
	public String toString() {
		return "Bike is riding";
	}

}
