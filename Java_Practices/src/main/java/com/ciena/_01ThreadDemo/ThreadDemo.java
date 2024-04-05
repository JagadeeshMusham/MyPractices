package com.ciena._01ThreadDemo;

/**
 * @author jmusham
 *
 */
public class ThreadDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Runnable te = () -> {
			for (int counter = 0; counter < 10; counter++) {
				System.out.println("ThreadDemo_: In the child thread: " + counter);
			}
		};

		Thread t = new Thread(te);
		t.start();

		for (int counter = 0; counter < 10; counter++) {
			System.out.println("ThreadDemo_: In the main thread: " + counter);
		}
	}
}
