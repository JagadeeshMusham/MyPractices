package com.centina.Kafka.model;

public class Employee {
	
	private String name;
	private String address;
	
	public Employee() {
        super();
    }
	
	/**
	 * @param name
	 * @param address
	 */
	public Employee(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", address=" + address + "]";
	}
	
	

}
