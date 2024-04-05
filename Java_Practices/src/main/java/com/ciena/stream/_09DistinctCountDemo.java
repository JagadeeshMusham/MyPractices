package com.ciena.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jmusham
 *
 */
public class _09DistinctCountDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> strList = Arrays.asList("Centina", "BluePlanet", "Ciena", "BluePlanet", "Ciena", "BluePlanet",
				"BPUAA");

		System.out.println(strList);
		List<String> distinctList = strList.stream().distinct().collect(Collectors.toList());

		System.out.println(distinctList);

		long count = strList.stream().distinct().count();
		System.out.printf("\n\n%d/%d unique items found in the given list", count, strList.size());
	}
}
