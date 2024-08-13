package com.musham._12BiConsumer;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * @author jmusham
 *
 */
class Employee {
	String name;
	int salary;

	/**
	 * @param name
	 * @param salary
	 */
	Employee(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}
}

/**
 * @author jmusham
 *
 */
public class BiConsumerDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		BiFunction<String, Integer, Employee> biFunc = (name, salary) -> new Employee(name, salary);
		empList.add(biFunc.apply("IJK", 1000));
		empList.add(biFunc.apply("LMN", 1500));
		empList.add(biFunc.apply("OPQ", 2000));

		BiConsumer<Employee, Integer> biConsumer = (emp, incr) -> emp.salary += incr;

		for (Employee emp : empList) {
			biConsumer.accept(emp, 50);
			System.out.printf("Employee Name: %s\\tEmployee Salary: %d\n", emp.name, emp.salary);
		}
	}
}
