package com.ciena._07Function;

import java.util.function.Function;

/**
 * @author jmusham
 *
 */
public class FunctionDemo {
	/**
	 * @param args
	 */
	public static void main (String [] args)
	{
		Function<Integer, Integer> func = num->num*num;

		System.out.println(func.apply(4));
		System.out.println(func.apply(5));
	}
}
