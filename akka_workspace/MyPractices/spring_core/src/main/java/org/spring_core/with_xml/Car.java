package org.spring_core.with_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {

	private int id;
	
	@Autowired
	private Tyre tyre;

	public Tyre getTyre() {
		return tyre;
	}

	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}

	public Car() {
	}
	
	public Car(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void drive() {
		System.out.println("Car is working with id: " + id + " with tyre: " + tyre);
	}
}
