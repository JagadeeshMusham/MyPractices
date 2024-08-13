package hacker_rank.fibonacci;

import java.util.Scanner;

public class FibonacciWithRecursion {

	private static int[] array;

	public static int fibonacci(int n) {
		if (n < 2) {
			return n;
		}

		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		array = new int[n + 2];
		scanner.close();

		String str;

		System.out.println(fibonacci(n));
	}
}
