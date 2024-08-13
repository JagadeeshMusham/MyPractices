package com.musham._10BiPrediction;

import java.util.function.BiPredicate;

/**
 * @author jmusham
 *
 */
public class BiPredictionDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BiPredicate<Integer, Integer> bp = (input1, input2) -> (input1 + input2) % 2 == 0;
		System.out.println(bp.test(10, 20));
		System.out.println(bp.test(10, 25));
	}
}
