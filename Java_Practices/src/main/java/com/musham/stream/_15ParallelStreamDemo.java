package com.musham.stream;

import java.util.stream.IntStream;

/**
 * @author jmusham
 *
 */
public class _15ParallelStreamDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntStream.range(1, 10).forEach(num -> {
			System.out.println("Thread: " + Thread.currentThread().getName() + num);
		});

		IntStream.range(1, 10).parallel().forEach(num -> {
			System.out.println("Thread: " + Thread.currentThread().getName() + ": " + num);
		});
	}
}
