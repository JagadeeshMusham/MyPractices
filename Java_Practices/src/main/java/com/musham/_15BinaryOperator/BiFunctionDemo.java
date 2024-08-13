package com.musham._15BinaryOperator;

import java.util.function.BiFunction;

/**
 * @author jmusham
 *
 */
public class BiFunctionDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BiFunction<String, String, String> biFunc = (s1, s2) -> s1 + " " + s2;
		System.out.println(biFunc.apply("Ciena", "Blueplanet"));
	}

}
