package com.musham._16MethodReference;

/**
 * @author jmusham
 *
 */
public class _3InstanceMethodReference {

	/**
	 * The print method
	 */
	public void print()
	{
		for (int num = 0; num < 10; num++) {
			System.out.println("Child Thread - " + num);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String [] args) {
		_3InstanceMethodReference methodRef = new _3InstanceMethodReference();
		Runnable runnable = methodRef::print;
		Thread thread = new Thread(runnable);
		thread.start();
		
		for (int num = 0; num < 10; num++) {
			System.out.println("Main Thread - " + num);
		}
	}
}
