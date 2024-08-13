package org.spring_core.with_annotation.with_component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {
	
	@Autowired
	@Qualifier("bike")
	Vehicle vehicle;

	@Override
	public String toString() {
		return "Person [vehicle=" + vehicle + "]";
	}
}
