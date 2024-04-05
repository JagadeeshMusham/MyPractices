package general.multithreading;

public class ThreadDemo {

	public static void main(String [] args)
	{
		CountInteger ci = new CountInteger();
		NewThreadWithThread ntt = new NewThreadWithThread(ci);
		
		NewThreadWithRunnable nt = new NewThreadWithRunnable(ci);
		
		try {
			ntt.join();
			nt.t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Exiting main thread");
	}
}
