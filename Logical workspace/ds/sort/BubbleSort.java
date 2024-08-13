package ds.sort;

import java.util.Scanner;

public class BubbleSort {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of integers to be sorted");
		int count = scanner.nextInt();

		int a[] = new int[count];
		for (int counter = 0; counter < count; counter++) {
			System.out.println("Enter the number for" + counter + "position: ");
			a[counter] = scanner.nextInt();
		}

		System.out.println("The unsorted array is: ");
		for (int counter = 0 ; counter < count; counter++) {
			System.out.print(a[counter] + " ");
		}
		
		doSort(a);

		System.out.println("\nThe sorted array is: ");
		for (int counter = 0 ; counter < count; counter++) {
			System.out.print(a[counter] + " ");
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
