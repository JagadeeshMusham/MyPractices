package com.musham._02comparator.Employee;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author jmusham
 *
 */
class Employee {
	String name;
	int eno;

	/**
	 * @param name
	 * @param eno
	 */
	Employee(String name, int eno) {
		this.name = name;
		this.eno = eno;
	}

	/**
	 * This is to give the Employee class in String
	 */
	public String toString() {
		return eno + ": " + name;
	}
}

/**
 * @author jmusham
 *
 */
public class EmployeeDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee("Ciena", 2222));
		empList.add(new Employee("Centina", 1111));
		empList.add(new Employee("UAA", 4444));
		empList.add(new Employee("BluePlanet", 3333));

		System.out.println(empList);
		Collections.sort(empList, (emp1, emp2) -> emp1.eno < emp2.eno ? -1 : emp1.eno > emp2.eno ? 1 : 0);
		System.out.println(empList);

		Collections.sort(empList, (emp1, emp2) -> emp1.name.compareTo(emp2.name));
		System.out.println(empList);
	}
}