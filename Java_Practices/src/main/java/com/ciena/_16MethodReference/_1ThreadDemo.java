package com.ciena._16MethodReference;

/**
 * @author jmusham
 *
 */
public class _1ThreadDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable runnable = () -> {
			for (int num = 0; num < 10; num++) {
				System.out.println("Child Thread - " + num);
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();

		for (int num = 0; num < 10; num++) {
			System.out.println("Main Thread - " + num);
		}
	}
}
