package com.musham.streams_file;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int a[] = new int[n];
		int b[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
			b[(n - k + a_i) % n] = a[a_i];
		}

		for (int counter = 0; counter < b.length; counter++) {
			System.out.println(b[counter]);
		}
	}
}
