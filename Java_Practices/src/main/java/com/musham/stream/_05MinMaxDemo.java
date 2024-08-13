package com.musham.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

/**
 * @author jmusham
 *
 */
public class _05MinMaxDemo {
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
		Optional<Integer> min = iArray.stream().min((num1, num2) -> num1.compareTo(num2));
		Optional<Integer> max = iArray.stream().max((num1, num2) -> num1.compareTo(num2));
		
		System.out.println("The Minimum value in the array is: " + min.get());
		System.out.println("The Maximum value in the array is: " + max.get());

		min = iArray.stream().min(Comparator.naturalOrder());
		max = iArray.stream().max(Comparator.naturalOrder());

		System.out.println("\nThe Minimum value in the array is: " + min.get());
		System.out.println("The Maximum value in the array is: " + max.get());
	}
}
