package ds.sort;

import java.util.Scanner;

public class MSort {
	private static void merge(int[] array, int start, int mid, int end) {
		int tempArray[] = new int[end + 1];

		int counter;
		int subCounter;
		int tempArrayCount;

		for (counter = start, subCounter = mid + 1, tempArrayCount = start; counter <= mid
				&& subCounter <= end; tempArrayCount++) {
			if (array[counter] > array[subCounter]) {
				tempArray[tempArrayCount] = array[subCounter++];
			} else {
				tempArray[tempArrayCount] = array[counter++];
			}
		}

		while (counter <= mid) {
			tempArray[tempArrayCount++] = array[counter++];
		}

		while (subCounter <= end) {
			tempArray[tempArrayCount++] = array[subCounter++];
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
		System.out.println("Enter the number of integer to sort: ");
		int total = scanner.nextInt();

		int array[] = new int[total];

		int counter;
		for (counter = 0; counter < total; counter++) {
			array[counter] = scanner.nextInt();
		}

		System.out.println("\nThe unsorted array is: ");
		for (counter = 0; counter < total; counter++) {
			System.out.print(array[counter] + " ");
		}

		sort(array, 0, total - 1);

		System.out.println("\nThe sorted array is: ");
		for (counter = 0; counter < total; counter++) {
			System.out.print(array[counter] + " ");
		}
	}
}
