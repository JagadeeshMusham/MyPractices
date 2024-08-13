package com.musham._14UnaryOperator;

import java.util.function.UnaryOperator;

/**
 * @author jmusham
 *
 */
public class _UnaryOperatorDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UnaryOperator<Integer> uo = num -> num * num;
		System.out.println(uo.apply(3));
	}
}
