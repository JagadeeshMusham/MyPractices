package DynamicProgramming.src.main.java.ds.blocking_queue;

import java.util.Scanner;

class BlockingQueue {

	Integer queue[];
	int nQueue;

	int size;

	BlockingQueue(int size) {
		nQueue = 0;
		this.size = size;
		queue = new Integer[this.size];
	}

	public synchronized void insert(int value) throws InterruptedException {
		while (nQueue == size) {
			System.out.println("queue full");
			wait();
		}

		queue[nQueue++] = value;

		System.out.println("inserted value " + value + " nQueue: " + nQueue);

		notifyAll();

	}

	public synchronized void delete() throws InterruptedException {
		while (nQueue == 0) {
			System.out.println("queue empty");
			wait();
		}

		System.out.println("Deleting the value " + queue[nQueue - 1] + " at position: " + nQueue);
		queue[--nQueue] = 0;

		notifyAll();

	}

	public void print() {
		for (int counter = 0; counter < nQueue; counter++) {
			System.out.print(queue[counter] + " ");
		}

		System.out.println();

	}
}

class Insert extends Thread {
	BlockingQueue bq;

	Insert(BlockingQueue bq) {
		this.bq = bq;
	}

	public void run() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Give Input data");
			int input = scanner.nextInt();
			bq.insert(input);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

class DeleteThread extends Thread {
	BlockingQueue bq;

	DeleteThread(BlockingQueue bq) {
		this.bq = bq;
	}

	public void run() {
		try {
			bq.delete();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class BlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue bq = new BlockingQueue(5);

		int inputValue;
		Scanner scanner = new Scanner(System.in);

		Insert insert = new Insert((bq));
		DeleteThread dt = new DeleteThread(bq);

		while (true) {
			Thread.sleep(5000);
			
			
			System.out.println("Please enter following values");
			System.out.println("1. Insert");
			System.out.println("2. Delete");
			System.out.println("3. List");
			System.out.println("4. Exit");

			inputValue = scanner.nextInt();
			
			System.out.println("THe opted values is: " + inputValue);

			switch (inputValue) {
			case 1: 
				insert.start();
				break;
			

			case 2: 
				dt.start();
				break;
			

			case 3:
				bq.print();
				break;

			case 4:
				return;
			}
		}
	}
}

/*
 * class BlockingQueue1 {
 * 
 * List<Integer> queue; int size;
 * 
 * BlockingQueue1(int size) { this.size = size; queue = new
 * ArrayList<Integer>(); }
 * 
 * public synchronized void insert(int value) throws InterruptedException {
 * while (queue.size() == size) { wait(); }
 * 
 * if (queue.isEmpty()) notifyAll();
 * 
 * queue.add(value);
 * 
 * 
 * System.out.println("inserted value " + value + " " + queue.toString()); }
 * 
 * public synchronized void delete() throws InterruptedException { while
 * (queue.isEmpty()) { wait(); }
 * 
 * queue.remove(0); notifyAll();
 * 
 * } }
 */

//class BlockingQueue {
//
//	private List<String> queue = new LinkedList<String>();
//
//	private int limit = 10;
//
//	public BlockingQueue(int limit) {
//		this.limit = limit;
//	}
//
//	public synchronized void enqueue(String item) throws InterruptedException {
//		while (this.queue.size() == this.limit) {
//			wait();
//		}
//		if (this.queue.size() == 0) {
//			notifyAll();
//		}
//		this.queue.add(item);
//	}
//
//	public synchronized String dequeue() throws InterruptedException {
//		while (this.queue.size() == 0) {
//			wait();
//		}
//		if (this.queue.size() == this.limit) {
//			notifyAll();
//		}
//
//		return this.queue.remove(0);
//	}
//
//}
//
//public class BlockingQueueDemo {
//	 
//    public static void main(String[] args)
//        throws InterruptedException
//    {
// 
//        int capacity = 5;
// 
//        ArrayBlockingQueue<String> queue
//            = new ArrayBlockingQueue<String>(capacity);
// 
//        queue.put("StarWars");
//        queue.put("SuperMan");
//        queue.put("Flash");
//        queue.put("BatMan");
//        queue.put("Avengers");
// 
//        System.out.println("queue contains " + queue);
// 
//        queue.remove();
//        queue.remove();
// 
//        queue.put("CaptainAmerica");
//        queue.put("Thor");
// 
//        System.out.println("queue contains " + queue);
//    }
//}