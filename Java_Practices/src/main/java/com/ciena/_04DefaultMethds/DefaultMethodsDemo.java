package com.ciena._04DefaultMethds;

/**
 * @author jmusham
 *
 */
interface Interface {
	/**
	 * The default method
	 */
	default public void show() {
		System.out.println("This is a default method in interfce");
	}
}

class Implementer implements Interface {
	public void print() {
		System.out.println("This is a print method in class");
		show();
	}
}

/**
 * @author jmusham
 *
 */
public class DefaultMethodsDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Implementer impl = new Implementer();
		impl.print();

		impl.show();
	}
}