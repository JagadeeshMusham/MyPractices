package hacker_rank.count_inversions;

import java.util.Scanner;

public class CountSwaps {
	private static int totalSwaps;

	private static void merge(int[] array, int start, int mid, int end) {
		int[] tempArray = new int[end + 1];

		int counter;
		int subCounter;
		int tempArrayCounter;
		for (counter = start, subCounter = mid + 1, tempArrayCounter = start; counter <= mid
				&& subCounter <= end; tempArrayCounter++) {
			if (array[counter] > array[subCounter]) {
				tempArray[tempArrayCounter] = array[subCounter++];
				totalSwaps += mid - counter + 1;
			} else {
				tempArray[tempArrayCounter] = array[counter++];
			}
		}

		while (counter <= mid) {
			tempArray[tempArrayCounter++] = array[counter++];
		}

		while (subCounter <= end) {
			tempArray[tempArrayCounter++] = array[subCounter++];
		}

		for (counter = start; counter <= end; counter++) {
			array[counter] = tempArray[counter];
		}
	}

	private static void sort(int[] array, int start, int end) {
		int mid;

		if (start < end) {
			mid = (start + end) / 2;
			sort(array, start, mid);
			sort(array, mid + 1, end);
			merge(array, start, mid, end);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the number of data sets to sort: ");
		int nDataSets = scanner.nextInt();

		for (int counter = 0; counter < nDataSets; counter++) {
			totalSwaps = 0;
			System.out.println("Enter the number of integers to be sorted:");
			int total = scanner.nextInt();

			int array[] = new int[total];

			int subCounter;
			for (subCounter = 0; subCounter < total; subCounter++) {
				array[subCounter] = scanner.nextInt();
			}

			System.out.println("The unsorted array: ");
			for (subCounter = 0; subCounter < total; subCounter++) {
				System.out.print(array[subCounter]);
			}

			sort(array, 0, total - 1);
			System.out.println("\nThe sorted array: ");
			for (subCounter = 0; subCounter < total; subCounter++) {
				System.out.print(array[subCounter]);
			}

			System.out.println("\nTotal number swaps is: " + totalSwaps);
		}
	}
}
