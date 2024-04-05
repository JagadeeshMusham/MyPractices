package com.ciena.stream;

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
		List<String> strList = Arrays.asList("Centina", "BluePlanet", "Ciena", "BluePlanet", "Ciena", "BluePlanet",
				"BPUAA");

		System.out.println(strList);

		long count = strList.stream().distinct().count();
		System.out.printf("\n\n%d/%d unique items found in the given list", count, strList.size());

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int initialValue = 10;
		int result = numbers.stream().reduce(initialValue, (accumulatorValue, element) -> accumulatorValue + element);

		System.out.printf("\n\nThe sum of the elements: %d", result);
	}
}
