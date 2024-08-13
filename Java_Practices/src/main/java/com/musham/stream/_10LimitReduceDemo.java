package com.musham.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author jmusham
 *
 */
public class _10LimitReduceDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int initialValue = 10;
		int result = numbers.stream().reduce(initialValue, (accumulatorValue, element) -> accumulatorValue + element);

		System.out.printf("\n\nThe sum of the elements: %d", result);
	}
}
