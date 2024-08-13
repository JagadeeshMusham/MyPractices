package hacker_rank.running_median.using_list;

import java.util.Scanner;

public class RunningMedian {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		int addedCount = 0;
		for (int a_i = 0; a_i < n; a_i++) {
			int currentNumber = in.nextInt();
			++addedCount;
			putInSortedArray(a, currentNumber, addedCount);
			System.out.println(getMedianofArray(a, addedCount));
		}
	}

	private static float getMedianofArray(int[] a, int addedCount) {
		float median;
		if (addedCount % 2 == 0) {
			median = (float) ((a[addedCount / 2] + a[(addedCount / 2) - 1]) / 2.0);
		} else {
			median = a[addedCount / 2];
		}
		return median;
	}

	private static void putInSortedArray(int[] a, int currentNumber, int addedCount) {
		if (addedCount == 1) {
			a[0] = currentNumber;
			return;
		}

		boolean bFoundPosition = false;
		for (int counter = 0; counter < addedCount - 1; counter++) {
			if (bFoundPosition) {
				int temp = a[counter];
				a[counter] = currentNumber;
				currentNumber = temp;
			} else {
				if (a[counter] < currentNumber) {
					continue;
				} else {
					bFoundPosition = true;
					int temp = a[counter];
					a[counter] = currentNumber;
					currentNumber = temp;
				}
			}
		}

		a[addedCount - 1] = currentNumber;
	}
}
