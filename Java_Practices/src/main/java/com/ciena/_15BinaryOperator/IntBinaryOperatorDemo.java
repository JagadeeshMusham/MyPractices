package com.ciena._15BinaryOperator;

import java.util.function.IntBinaryOperator;

/**
 * @author jmusham
 *
 */
public class IntBinaryOperatorDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntBinaryOperator ibo = (input1, input2) -> input1 + input2;
		System.out.println(ibo.applyAsInt(10, 20));
	}
}
