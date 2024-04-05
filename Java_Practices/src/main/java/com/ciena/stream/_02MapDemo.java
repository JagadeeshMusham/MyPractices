package com.ciena.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jmusham
 *
 */
public class _02MapDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> iArray = new ArrayList<>();
		iArray.add(10);
		iArray.add(5);
		iArray.add(25);
		iArray.add(34);
		iArray.add(22);
		iArray.add(35);
		iArray.add(3);

		System.out.println(iArray);
		List<Integer> updatedList = iArray.stream().map(num -> num + 6).collect(Collectors.toList());
		System.out.println(updatedList);

		Function<Integer, Integer> function = num -> num + 6;
		List<Integer> functionList = iArray.parallelStream().map(function).collect(Collectors.toList());
		System.out.println(functionList);
	}
}
