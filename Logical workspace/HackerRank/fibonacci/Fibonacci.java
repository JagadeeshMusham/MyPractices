package hacker_rank.fibonacci;

import java.util.Scanner;

public class Fibonacci {
	public static int fibonacci(int n) {
		if (n < 2) {
			return n;
		}

		int value1 = 0;
		int value2 = 1;
		int fibonacci = 0;

		for (int counter = 2; counter <= n; counter++) {
			fibonacci = value1 + value2;
			value1 = value2;
			value2 = fibonacci;
		}

		return fibonacci;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		System.out.println(fibonacci(n));
	}

}
