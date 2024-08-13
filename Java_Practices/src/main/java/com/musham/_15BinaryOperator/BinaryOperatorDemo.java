package com.musham._15BinaryOperator;

import java.util.function.BinaryOperator;

/**
 * @author jmusham
 *
 */
public class BinaryOperatorDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryOperator<String> bo = (s1, s2) -> s1 + " " + s2;
		System.out.println(bo.apply("Ciena", "BluePlanet"));
	}
}
