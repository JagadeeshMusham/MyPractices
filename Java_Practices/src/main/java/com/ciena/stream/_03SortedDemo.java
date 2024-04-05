package com.ciena.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jmusham
 *
 */
public class _03SortedDemo {
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
		List<Integer> sortedList = iArray.stream().sorted().collect(Collectors.toList());
		System.out.println(sortedList);
		
		System.out.println("\n\nUsing Lambda operator");
		List<Integer> sortedList1 = iArray.stream().sorted((num1, num2) -> num1 > num2 ? 1 : num2 > num1 ? -1 : 0)
				.collect(Collectors.toList());
		System.out.println(sortedList1);

		List<Integer> riverseList = iArray.stream().sorted((num2, num1) -> num1 > num2 ? 1 : num2 > num1 ? -1 : 0)
				.collect(Collectors.toList());
		System.out.println(riverseList);

		// By using comparator
		System.out.println("\n\nUsing Comparator");
		List<Integer> comparatorList = iArray.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
		System.out.println(comparatorList);
		
		List<Integer> comparatorReverseList = iArray.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(comparatorReverseList);
		
		//By Using CompareTo
		System.out.println("\n\nUsing Compareto");
		
		List<Integer> compareToList = iArray.stream().sorted((num1, num2) -> num1.compareTo(num2)).collect(Collectors.toList());
		System.out.println(compareToList);

		//Todo: have to work on this
		List<Integer> compareToReverseList = iArray.stream().sorted((num2, num1) -> num1.compareTo(num2)).collect(Collectors.toList());
		System.out.println(compareToList);
	}
}
