package com.ciena.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jmusham
 *
 */
public class _13ConcatDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> strList1 = Arrays.asList("Centina1", "BluePlanet", "Ciena");
		
		List<String> strList2 = Arrays.asList("BluePlanet", "Ciena", "BluePlanet",
				"BPUAA");
		
		List<String> concatedList = Stream.concat(strList1.stream(), strList2.stream()).collect(Collectors.toList());
		
		System.out.println(strList1);
		System.out.println(strList2);
		System.out.println(concatedList);
	}
}
