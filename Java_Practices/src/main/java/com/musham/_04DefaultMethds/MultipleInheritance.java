package com.musham._04DefaultMethds;

/**
 * @author jmusham
 *
 */
interface Interface1 {
	/**
	 * The print method in Interface1
	 */
	default public void print() {
		System.out.println("Default print method in  Interface1");
	}
}

/**
 * @author jmusham
 *
 */
interface Interface2 {
	/**
	 * The print method in Interface2
	 */
	public default void print() {
		System.out.println("Default print method in  Interface2");
	}
}


/**
 * @author jmusham
 *
 */
public class MultipleInheritance implements Interface1, Interface2{
	
	/**
	 * The print method in class
	 */
	public void print() {
		Interface1.super.print();
		Interface2.super.print();
		System.out.println("print method in implementer class");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MultipleInheritance mi = new MultipleInheritance();
		mi.print();
	}
}
