package com.musham.ThreadTest;

public class PrintSequenceRunnable implements Runnable{
	public int PRINT_NUMBERS_UPTO=10;
    static int  number=1;
    int remainder;
    static Object lock=new Object();
 
    PrintSequenceRunnable(int remainder)
    {
        this.remainder=remainder;
    }
 
    public void run() {
        while (number < PRINT_NUMBERS_UPTO-1) {
        	System.out.println("Before sync" + Thread.currentThread().getName());
            synchronized (lock) {
            	System.out.println("In sync" + Thread.currentThread().getName());
                while (number % 3 != remainder) { // wait for numbers other than remainder
                    try {
                    	System.out.println("Before wait" + Thread.currentThread().getName());
                        lock.wait();
                        System.out.println("After wait" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " " + number);
                number++;
                try {
                	System.out.println("Before sleep" + Thread.currentThread().getName());
					Thread.sleep(1000);
					System.out.println("After sleep" + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	System.out.println("Before sync" + Thread.currentThread().getName());
                lock.notifyAll();
            }
        	System.out.println("After sync" + Thread.currentThread().getName());
        }
    }
}
