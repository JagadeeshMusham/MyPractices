package com.ciena._02comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author jmusham
 *
 */
public class ComparatorDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> iArray = new ArrayList<Integer>();
		iArray.add(40);
		iArray.add(45);
		iArray.add(30);
		iArray.add(20);
		iArray.add(25);
		iArray.add(15);
		iArray.add(35);
		
		//comparator
		Comparator<Integer> comparator = (input1, input2) -> {
			if (input1 < input2)
				return -1;
			else if (input1 > input2)
				return 1;
			else
				return 0;
		};

		System.out.println(iArray);
		Collections.sort(iArray, comparator);
		System.out.println(iArray);
	}
}
