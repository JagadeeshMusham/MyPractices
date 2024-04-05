package com.ciena._05StaticMethods;

interface Interface {

	public static void print() {
		System.out.println("Static m1 method");
	}
}

public class StaticMethodDemo implements Interface {

	public static void main(String[] args) {
		Interface.print();

		// below are invalid calls
//		print();
//		StaticMethodDemo.print();
//
//		StaticMethodDemo demo = new StaticMethodDemo();
//		StaticMethodDemo.print();
	}
}
