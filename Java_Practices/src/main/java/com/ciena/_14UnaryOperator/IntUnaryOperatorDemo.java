package com.ciena._14UnaryOperator;

import java.util.function.IntUnaryOperator;

/**
 * @author jmusham
 *
 */
public class IntUnaryOperatorDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntUnaryOperator iuo = num -> num * num;
		System.out.println(iuo.applyAsInt(3));
	}
}
