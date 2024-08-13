package com.musham._16MethodReference;

/**
 * @author jmusham
 *
 */
public class _2StaticMethodReferenceDemo {

	/**
	 * 
	 */
	public static void print() {
		for (int num = 0; num < 10; num++) {
			System.out.println("Child Thread - " + num);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable runnable = _2StaticMethodReferenceDemo::print;
		Thread thread = new Thread(runnable);
		thread.start();

		for (int num = 0; num < 10; num++) {
			System.out.println("Main Thread - " + num);
		}
	}
}
