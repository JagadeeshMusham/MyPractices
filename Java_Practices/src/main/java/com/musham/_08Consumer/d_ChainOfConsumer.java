package com.musham._08Consumer;

import java.util.function.Consumer;

/**
 * @author jmusham
 *
 */
public class d_ChainOfConsumer {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Consumer<String> consumer1 = str -> System.out.println("This is first consumer: " + str);
		Consumer<String> consumer2 = str -> System.out.println("This is second consumer: " + str);
		Consumer<String> consumer3 = str -> System.out.println("This is third consumer: " + str);

		Consumer<String> chainOfConsumer = consumer3.andThen(consumer2).andThen(consumer1);

		consumer1.accept("Ciena");
		consumer2.accept("BluePlanet");
		consumer3.accept("Centina");
		
		System.out.println();
		chainOfConsumer.accept("BPUAA");

	}

}
