package com.ciena.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author jmusham
 *
 */
public class _01FilterDemo {
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
		List<Integer> evenList = iArray.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
		System.out.println(evenList);

		Predicate<Integer> predicate = num -> num % 2 == 0;
		List<Integer> predicateList = iArray.stream().filter(predicate).collect(Collectors.toList());
		System.out.println(predicateList);
	}
}
