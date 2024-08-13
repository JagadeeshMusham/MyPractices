package com.musham.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author jmusham
 *
 */
public class _11MatchesDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> strList = Arrays.asList("Centina", "BluePlanet", "Ciena", "BluePlanet", "Ciena", "BluePlanet",
				"BPUAA");

		boolean result = strList.stream().anyMatch(value -> {
			return value.startsWith("B");
		});
		System.out.printf("Do we have strings which are starting with 'B' %b  \n\n", result);

		result = strList.stream().allMatch(value -> {
			return value.startsWith("B");
		});
		System.out.printf("Does all strings are starting with 'B' %b  \n", result);

		result = strList.stream().noneMatch(value -> {
			return value.startsWith("z");
		});

		System.out.printf("No string is starting with 'z': %b \n\n", result);
	}
}
