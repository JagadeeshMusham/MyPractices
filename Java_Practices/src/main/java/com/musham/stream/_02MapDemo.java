package com.musham.stream;

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
		List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(5);
		list.add(25);
		list.add(34);
		list.add(22);
		list.add(35);
		list.add(3);

		System.out.println(list);
		List<Integer> updatedList = list.stream().map(num -> num + 6).collect(Collectors.toList());
		System.out.println(updatedList);

		Function<Integer, Integer> function = num -> num + 6;
		List<Integer> functionList = list.parallelStream().map(function).collect(Collectors.toList());
		System.out.println(functionList);
	}
}
