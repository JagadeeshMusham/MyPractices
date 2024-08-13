package com.musham.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author jmusham
 *
 */
public class _14ParallelStreamDemo {
	/**
	 * @param args
	 */
	public static void main(String [] args) 
	{
		long start = 0;
		long end = 0;
		
		start = System.currentTimeMillis();
		IntStream.range(1, 1000).forEach(System.out::println);
		end = System.currentTimeMillis();
		
		long firstTime = end - start;
		
		
		start = System.currentTimeMillis();
		IntStream.range(1, 1000).parallel().forEach(System.out::println);
		end = System.currentTimeMillis();
		
		long secondTime = end - start;
		
		System.out.println("\n\n"+firstTime);
		System.out.println(secondTime);
	}
}
