package com.ciena._08Consumer;

import java.util.function.Consumer;

/**
 * @author jmusham
 *
 */
public class ConsumerDemo {
	/**
	 * @param args
	 */
	public static void main(String [] args)
	{
		Consumer<String> consumer = str -> System.out.println(str);

		consumer.accept("Ciena");
		consumer.accept("Blueplanet");
	}
}
