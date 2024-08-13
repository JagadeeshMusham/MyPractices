package com.musham.InterviewQuestions.pramati.codility;

public class MaxDiff {
	private static int maxDiff(int a[]) {
		// Create a diff array of size n-1. The array will hold
		// the difference of adjacent elements
		int arraySize = a.length;
		int difference[] = new int[arraySize - 1];
		for (int i = 0; i < arraySize - 1; i++)
			difference[i] = a[i + 1] - a[i];

		// Now find the maximum sum subarray in diff array
		int maxDiff = difference[0];
		for (int i = 1; i < arraySize - 1; i++) {
			if (difference[i - 1] > 0)
				difference[i] += difference[i - 1];
			if (maxDiff < difference[i])
				maxDiff = difference[i];
		}
		return maxDiff;
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		int arr[] = { 4, 1, 3, 2 };
		int size = 4;
		System.out.println("Maximum difference is " + maxDiff(arr));
	}
}
