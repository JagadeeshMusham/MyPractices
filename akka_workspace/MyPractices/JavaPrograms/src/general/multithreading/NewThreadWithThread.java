package general.multithreading;

public class NewThreadWithThread extends Thread {

	CountInteger ci;

	public NewThreadWithThread(CountInteger ci) {
		super("NewThreadWithThread");
		this.ci = ci;
		System.out.println("New thread is creating");
		start();
	}

	@Override
	public void run() {
		for (int counter = 0; counter < 5; counter++) {
			try {
				System.out.println("With Thread going to wait");
				synchronized (ci) {
					ci.wait(5);

					System.out.println("With Thread coming out from wait");
					ci.increment();
					System.out.println("run: " + ci.getCount());

					ci.notify();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
