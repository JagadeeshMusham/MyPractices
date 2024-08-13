package com.musham.InterviewQuestions.amphora.engineering_test.src.main.java.com.amphora.engineering_test.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * The person pojo which will be used for entire service 
 * 
 */
/**
 * @author Admin
 *
 */
/**
 * @author Admin
 *
 */
public class Person {
	final String KEY_SEPARATOR = "|";

	/**
	 * The name of the person
	 */
	private String name;

	/**
	 * The age of the person
	 */
	private int age;

	/**
	 * List parents at max can have two parents
	 */
	private List<String> parents = new ArrayList<>();

	/**
	 * List of kids, there is no limit for kids
	 */
	private List<String> kids = new ArrayList<>();

	/**
	 * The generation of the person
	 */
	private int generation = 0;

	/**
	 * boolean flag to indicate if the person is already done some action
	 */
	private boolean bVisisted;

	/**
	 * The default constructor
	 */
	public Person() {
		bVisisted = false;
	}

	/**
	 * @return returns the boolean flag to indicate the visited person or not
	 */
	public boolean isVisisted() {
		return bVisisted;
	}

	/**
	 * @param bVisisted
	 *            The boolean flag to set visited condition or not
	 */
	public void setVisisted(boolean bVisisted) {
		this.bVisisted = bVisisted;
	}

	/**
	 * @return returns the person name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name of the person
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return returns the age of the person
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            The age of the person
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return returns the list of parent names in string format
	 */
	public List<String> getParents() {
		return parents;
	}

	/**
	 * @param parents
	 *            The list of parent names
	 */
	public void setParents(ArrayList<String> parents) {
		this.parents = parents;
	}

	/**
	 * @return returns the list of kids names in the String format
	 */
	public List<String> getKids() {
		return kids;
	}

	/**
	 * @param kids
	 *            The list of kid names
	 */
	public void setKids(ArrayList<String> kids) {
		this.kids = kids;
	}

	/**
	 * @return returns the generation of the family
	 */
	public int getGeneration() {
		return generation;
	}

	/**
	 * @param generation
	 *            The generation of the family
	 */
	public void setGeneration(int generation) {
		this.generation = generation;
	}

	/* 
	 * To print the Person object
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Person [name=").append(name).append(", age=").append(age);

		if (parents.size() > 0) {
			result.append(", parents= ").append(parents);
		}

		if (kids.size() > 0) {
			result.append(", kids= ").append(kids);
		}

		result.append("]");

		return result.toString();
	}
}
