package com.musham._01ThreadDemo;

/**
 * @author jmusham
 *
 */
class ThreadEx implements Runnable
{

	/**
	 * overriding the run method
	 */
	public void run() {
		for (int counter=0; counter < 10; counter++)
		{
			System.out.println("ThreadDemo_: In the child thread: " + counter);
		}
	}	
}

/**
 * @author jmusham
 *
 */
public class ThreadDemo_ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Runnable te = new ThreadEx();
		Thread t = new Thread(te);
		t.start();

		for (int counter=0; counter < 10; counter++)
		{
			System.out.println("ThreadDemo_: In the main thread: " + counter);
		}
	}
}
