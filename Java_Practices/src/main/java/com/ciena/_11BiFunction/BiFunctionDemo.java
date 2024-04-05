package com.ciena._11BiFunction;

import java.util.ArrayList;
import java.util.function.BiFunction;

/**
 * @author jmusham
 *
 */
class Employee {
	int number;
	String name;

	/**
	 * @param number
	 * @param name
	 */
	Employee(int number, String name) {
		this.number = number;
		this.name = name;
	}
}

/**
 * @author jmusham
 *
 */
public class BiFunctionDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		BiFunction<Integer, String, Employee> biFunc = (num, name) -> new Employee(num, name);
		empList.add(biFunc.apply(100, "ABC"));
		empList.add(biFunc.apply(200, "DEF"));
		empList.add(biFunc.apply(300, "GHI"));

		for (Employee emp : empList) {
			System.out.printf("Employee Number: %d\tEmployee Name: %s\n", emp.number, emp.name);
		}
	}
}
