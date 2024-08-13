package com.musham.stream;

import java.util.ArrayList;

/**
 * @author jmusham
 *
 */
public class _06ForEachDemo {
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
		
		iArray.stream().forEach(System.out::println);
		
		System.out.println("\n\n\n");
		
		for(int num : iArray) {
			System.out.println(num);
		}
	}
}
