package hacker_rank.tale_of_two_stacks;

import java.util.Scanner;
import java.util.Stack;

public class TaleOfTwoStacks {
	public static class MyQueue<T> {
		Stack<T> stackNewestOnTop = new Stack<T>();
		Stack<T> stackOldestOnTop = new Stack<T>();

		public void enqueue(T value) { // Push onto newest stack
			stackNewestOnTop.push(value);
		}

		public T peek() {
			if (stackNewestOnTop.size() > 0) {
				return stackNewestOnTop.elementAt(0);
			}

			return null;
		}

		public T dequeue() {
			if (stackNewestOnTop.size() > 0) {
				T t = stackNewestOnTop.get(0);
				stackNewestOnTop.remove(0);

				return t;
			}

			return null;
		}
	}

	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<Integer>();

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			int operation = scan.nextInt();
			if (operation == 1) { // enqueue
				queue.enqueue(scan.nextInt());
			} else if (operation == 2) { // dequeue
				queue.dequeue();
			} else if (operation == 3) { // print/peek
				System.out.println(queue.peek());
			}
		}
		scan.close();
	}
}
