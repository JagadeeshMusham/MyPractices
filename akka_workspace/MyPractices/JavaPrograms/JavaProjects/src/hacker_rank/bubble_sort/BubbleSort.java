package hacker_rank.bubble_sort;

import java.util.Scanner;

public class BubbleSort {
	private static int nSwaps;

	public static void main(String[] args) {
		nSwaps = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of integers to be sorted");
		int count = scanner.nextInt();

		int a[] = new int[count];
		for (int counter = 0; counter < count; counter++) {
			System.out.println("Enter the number for" + counter + "position: ");
			a[counter] = scanner.nextInt();
		}

		doSort(a);

		System.out.println(nSwaps);
		System.out.println(a[0]);
		System.out.println(a[count - 1]);
	}

	private static void doSort(int[] a) {
		for (int firstCounter = 0; firstCounter < a.length; firstCounter++) {
			for (int counter = 0; counter < a.length - 1 - firstCounter; counter++) {
				if (a[counter] > a[counter + 1]) {
					++nSwaps;
					int temp = a[counter];
					a[counter] = a[counter + 1];
					a[counter + 1] = temp;
				}
			}
		}
	}
}
