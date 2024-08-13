package com.musham.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jmusham
 *
 */
public class _08FlatMapDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> iList1 = Arrays.asList(10, 66);
		List<Integer> iList2 = Arrays.asList(15, 20);
		List<Integer> iList3 = Arrays.asList(30, 12);
		
		List<List<Integer>> listHeirarchy = Arrays.asList(iList1, iList2, iList3);
		List<Integer> finalList = listHeirarchy.stream().flatMap(num -> num.stream()).collect(Collectors.toList());
		
		System.out.println(listHeirarchy);
		System.out.println(finalList);
	}
}
