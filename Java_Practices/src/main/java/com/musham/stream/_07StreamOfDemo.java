package com.musham.stream;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author jmusham
 *
 */
public class _07StreamOfDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> iList = new ArrayList<>();
		iList.add(10);
		iList.add(5);
		iList.add(25);
		iList.add(34);
		iList.add(22);
		iList.add(35);
		iList.add(3);

		Integer[] iArray = iList.stream().toArray(Integer[]::new);
		Stream.of(iArray).forEach(System.out::println);

		Stream<Integer> stream = Stream.of(9, 99, 999, 9999, 99999);
		stream.forEach(System.out::println);
		
		Integer[] iArray2 = {1,34,56, 3, 6, 65, 76};
		Stream.of(iArray2).forEach(System.out::println);
	}
}
