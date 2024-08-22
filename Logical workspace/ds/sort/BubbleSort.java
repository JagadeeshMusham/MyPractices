package ds.sort;

import java.util.Scanner;

public class BubbleSort {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of integers to be sorted");
		int count = scanner.nextInt();

		int input[] = new int[count];
		for (int counter = 0; counter < count; counter++) {
			System.out.println("Enter the number for" + counter + "position: ");
			input[counter] = scanner.nextInt();
		}

		System.out.print("\n\nThe unsorted array: ");
		printArray(input);

		doSort(input);

		System.out.print("The sorted array: ");
		printArray(input);
	}

	private static void printArray(int[] input) {
		for (int counter = 0 ; counter < input.length; counter++) {
			System.out.print((counter == 0 ? "[" : "") +
					input[counter] +
					(counter < input.length - 1 ? ", " : "]\n"));
		}
	}

	private static void doSort(int[] a) {
		for (int firstCounter = 0; firstCounter < a.length; firstCounter++) {
			for (int counter = 0; counter < a.length - 1 - firstCounter; counter++) {
				if (a[counter] > a[counter + 1]) {
					int temp = a[counter];
					a[counter] = a[counter + 1];
					a[counter + 1] = temp;
				}
			}
		}
	}
}
