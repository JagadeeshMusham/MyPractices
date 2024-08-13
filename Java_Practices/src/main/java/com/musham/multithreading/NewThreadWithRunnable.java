package general.multithreading;

public class NewThreadWithRunnable implements Runnable {

	Thread t;

	CountInteger ci;

	public NewThreadWithRunnable(CountInteger ci) {
		this.ci = ci;
		t = new Thread(this, "NewThreadWithRunnable");
		System.out.println("Creating new thread with Runnable");
		t.start();
	}

	@Override
	public void run() {
		for (int counter = 0; counter < 5; counter++) {
			System.out.println("With Runnable going to wait");
			synchronized (ci) {
				try {
					ci.wait(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("With Runnable coming out from wait");
				ci.increment();
				System.out.println("Counter: " + ci.getCount());

				// try {
				// Thread.sleep(1000);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }

				ci.notify();
			}

		}
	}
}
