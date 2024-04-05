package com.ciena._13PrimitiveFuntionTypes;

import java.util.function.IntToDoubleFunction;

/**
 * @author jmusham
 *
 */
public class IntToDoubleFunctionDemo {
	/**
	 * @param args
	 */
	public static void main(String [] args)
	{
		IntToDoubleFunction itdf = num -> Math.sqrt(num);
		System.out.println(itdf.applyAsDouble(9));
		System.out.println(itdf.applyAsDouble(15));
		System.out.println(itdf.applyAsDouble(25));
	}
}
