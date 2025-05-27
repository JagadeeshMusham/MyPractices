package ds.sort;

import java.util.Scanner;

/*
 * This is an in-place comparison-based sorting algorithm. Here, a sub-list is maintained
 * which is always sorted. For example, the lower part of an array is maintained to be sorted. 
 * An element which is to be 'insert'ed in this sorted sub-list, has to find its appropriate 
 * place and then it has to be inserted there. Hence the name, insertion sort.
 * */

/*
 * Algorithm:
 * 
 * Step 1 - If it is the first element, it is already sorted. return 1;
 * Step 2 - Pick next element
 * Step 3 - Compare with all elements in the sorted sub-list
 * Step 4 - Shift all the elements in the sorted sub-list that is greater than the value to be sorted
 * Step 5 - Insert the value
 * Step 6 - Repeat until list is sorted
 * */

public class InsertionSort {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of integers to sort ");
		int totalNumbers = scanner.nextInt();
		int values[] = new int[totalNumbers];

		int counter = 0;
		for (; counter < totalNumbers; counter++) {
			System.out.println("Enter a number: ");
			values[counter] = scanner.nextInt();
		}

		scanner.close(); // Closing scanner to prevent resource leaks

		doInsertionSort(values);

		System.out.println("The sorted array is: ");
		printValues(values);
	}

	private static void doInsertionSort(int[] values) {
		int holePosition;
		int valueToInsert;
		
		for (int i = 1; i < values.length; i++) {

			/* select value to be inserted */
			valueToInsert = values[i];
			holePosition = i;

			/* locate hole position for the element to be inserted */
			while (holePosition > 0 && values[holePosition - 1] > valueToInsert) {
				values[holePosition] = values[holePosition - 1];
				--holePosition;
			}

			/* insert the number at hole position */
			values[holePosition] = valueToInsert;
			
			printValues(values);
		}
	}

	private static void printValues(int[] values) {
		for (int counter = 0; counter < values.length; counter++) {
			System.out.print(values[counter] + " ");
		}
		System.out.println();
	}
}
