package ds.sort;

import java.util.Scanner;

public class SelectionSort {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the number of integer to sort");
		int totalNumbers = scanner.nextInt();

		int values[] = new int[totalNumbers];
		
		for (int counter = 0; counter < totalNumbers; counter++) {
			values[counter] = scanner.nextInt();
		}

		System.out.println("The values in the array: ");
		printValues(values);
		doSelectionSort(values);

		System.out.println();
		printValues(values);
	}

	private static void doSelectionSort(int[] values) {
		for (int counter = 0; counter < values.length; counter++) {
			for (int subCounter = counter; subCounter < values.length; subCounter++) {
				if (values[counter] > values[subCounter]) {
					swapValues(values, counter, subCounter);
				}
			}
		}
	}

	private static void swapValues(int[] values, int counter, int subCounter) {
		int tempValue = values[counter];
		values[counter] = values[subCounter];
		values[subCounter] = tempValue;
		
		printValues(values);
	}

	private static void printValues(int[] values) {
		for (int counter = 0; counter < values.length; counter++) {
			System.out.print(values[counter] + " ");
		}
		System.out.println();
	}

}
