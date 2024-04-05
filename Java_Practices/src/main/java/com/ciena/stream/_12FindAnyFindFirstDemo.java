package com.ciena.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author jmusham
 *
 */
public class _12FindAnyFindFirstDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> strList = Arrays.asList("Centina1", "BluePlanet", "Ciena", "BluePlanet", "Ciena", "BluePlanet",
				"BPUAA");
		
		Optional<String> strAny = strList.parallelStream().findAny();
		System.out.printf("The findAny returned value is: %s\n\n", strAny.get());
		
		Optional<String> strFirst = strList.stream().findFirst();
		System.out.printf("The foundFirst returned value is: %s\n\n", strFirst.get());
		
		strFirst = strList.stream().sorted().findFirst();
		System.out.printf("The foundFirst returned value is: %s\n\n", strFirst.get());
	}
}
