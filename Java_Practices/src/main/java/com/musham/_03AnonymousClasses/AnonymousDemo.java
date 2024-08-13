package com.musham._03AnonymousClasses;

/**
 * @author jmusham
 *
 */
public class AnonymousDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//lambda expression
		Runnable r = () -> {
			for (int counter = 0; counter < 10; counter++) {
				System.out.println("Child Thread: " + counter);
			}
		};
		
		Thread t = new Thread(r);
		t.start();
		for (int counter = 0; counter < 10; counter++) {
			System.out.println("Main Thread: " + counter);
		}
	}
}
