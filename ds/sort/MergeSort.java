package ds.sort;

import java.util.Scanner;

public class MergeSort {
	private static void merge(int[] array, int start, int mid, int total) {
		int tempArray[] = new int[total+1];
		int tempArrayCounter = start;
		int counter, subCounter;
		for (counter = start, subCounter = mid + 1; counter <= mid && subCounter <= total; tempArrayCounter++) {
			if (array[counter] < array[subCounter]) {
				tempArray[tempArrayCounter] = array[counter++];
			} else {
				tempArray[tempArrayCounter] = array[subCounter++];
			}
		}

		while (counter <= mid) {
			tempArray[tempArrayCounter++] = array[counter++];
		}

		while (subCounter <= total) {
			tempArray[tempArrayCounter++] = array[subCounter++];
		}

		for (counter = start; counter <= total; counter++) {
			array[counter] = tempArray[counter];
		}
	}

	private static void sort(int[] array, int start, int total) {
		int mid;
		if (start < total) {
			mid = (start + total) / 2;
			sort(array, start, mid);
			sort(array, mid + 1, total);
			merge(array, start, mid, total);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number integers to be sorted: ");
		int total = scanner.nextInt();

		int array[] = new int[total];

		int counter;
		for (counter = 0; counter < total; counter++) {
			System.out.println("Enter the number for position: " + counter + 1);
			array[counter] = scanner.nextInt();
		}

		System.out.println("The unsorted values of the array is: ");
		for (counter = 0; counter < total; counter++) {
			System.out.print(array[counter] + " ");
		}

		sort(array, 0, total - 1);

		System.out.println("\nThe sorted values of the array is: ");
		for (counter = 0; counter < total; counter++) {
			System.out.print(array[counter] + " ");
		}
	}
}
