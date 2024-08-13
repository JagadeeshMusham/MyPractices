package com.musham.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jmusham
 *
 */
public class _04SortedonLength {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> strings = new ArrayList<String>();
		strings.add("Centina");
		strings.add("Ciena");
		strings.add("BPUAA");
		strings.add("Blue Plannet");
		strings.add("vSure");
		
		System.out.println(strings);
		
		Comparator<String> comparator = ((string1, string2) -> {
			int length1 = string1.length();
			int length2 = string2.length();
			return (length1 > length2)?1:length2>length1?-1:0;
		});
		
		List<String> sortedList = strings.stream().sorted(comparator).collect(Collectors.toList());
		System.out.println(sortedList);
	}
}
