package org.spring_core.with_xml;

import org.springframework.stereotype.Component;

@Component
public class Tyre {
	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return " [brand=" + brand + "]";
	}

}
