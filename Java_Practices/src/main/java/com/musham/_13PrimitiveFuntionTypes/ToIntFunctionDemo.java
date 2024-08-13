package com.musham._13PrimitiveFuntionTypes;

import java.util.function.ToIntFunction;

/**
 * @author jmusham
 *
 */
public class ToIntFunctionDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ToIntFunction<String> tif = str -> str.length();
		System.out.println(tif.applyAsInt("Centina"));
		System.out.println(tif.applyAsInt("Ciena"));
		System.out.println(tif.applyAsInt("BluePlanet"));
	}
}
