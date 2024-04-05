package com.ciena._03AnonymousClasses;

/**
 * @author jmusham
 *
 */
public class AnonymousDemo_ {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				for (int counter = 0; counter < 10; counter++) {
					System.out.println("Child thread: " + counter);
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		for (int counter = 0; counter < 10; counter++) {
			System.out.println("Main Thread: " + counter);
		}
	}
}
