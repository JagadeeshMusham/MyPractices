package com.musham.stream;

public class PatternDemo {
	public static void main(String[] args) {
		int n, i, j, fs, bs;
		n = 5;
		fs = n - 1;
		// bs=-1;
		for (i = 1; i < n; i++) {
			for (j = 1; j <= fs; j++)
				System.out.printf(" ");
			fs--;
			for (j = 1; j <= i; j++)
				System.out.printf("* ");
			System.out.printf("\n");
		}
		for (j = 1; j <= n; j++)
			System.out.printf("* ");

	}

}
