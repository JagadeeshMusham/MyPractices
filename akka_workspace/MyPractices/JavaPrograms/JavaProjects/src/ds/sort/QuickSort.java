package ds.sort;

import java.util.Scanner;

public class QuickSort {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of integers to be sorted");
		int count = scanner.nextInt();

		int array[] = new int[count];
		for (int counter = 0; counter < count; counter++) {
			System.out.println("Enter the number for" + counter + "position: ");
			array[counter] = scanner.nextInt();
		}

		System.out.println("The unsorted array is: ");
		for (int counter = 0; counter < count; counter++) {
			System.out.print(array[counter] + " ");
		}

		quickSort(array, 0, array.length - 1);

		System.out.println("\nThe sorted array is: ");
		for (int counter = 0; counter < count; counter++) {
			System.out.print(array[counter] + " ");
		}
	}

	private static void quickSort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}

		int pivot = array[right];
		int partitionPoint = partition(array, left, right, pivot);
		quickSort(array, left, partitionPoint - 1);
		quickSort(array, partitionPoint + 1, right);
	}

	private static int partition(int[] array, int left, int right, int pivot) {
		int leftPointer = left - 1;
		int rightPointer = right;

		while (true) {
			while (array[++leftPointer] < pivot) {

			}

			while (rightPointer >0 && array[--rightPointer] > pivot) {

			}

			if (leftPointer >= rightPointer) {
				break;
			} else {
				swap(array, leftPointer, rightPointer);
			}
		}

		swap(array, leftPointer, right);
		return leftPointer;
	}

	private static void swap(int[] array, int leftPointer, int rightPointer) {
		int temp = array[leftPointer];
		array[leftPointer] = array[rightPointer];
		array[rightPointer] = temp;
	}
}
