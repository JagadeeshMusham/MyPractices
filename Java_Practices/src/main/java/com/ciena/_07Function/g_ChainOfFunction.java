package com.ciena._07Function;

import java.util.function.Function;

/**
 * @author jmusham
 *
 */
public class g_ChainOfFunction {
	/**
	 * @param args
	 */
	public static void main(String [] args)
	{
		Function<Integer, Integer> func1 = num -> num+5;
		Function<Integer, Integer> func2 = num -> num*num;
		System.out.println(func1.andThen(func2).apply(4));
		System.out.println(func1.compose(func2).apply(4));
	}
}
