package com.musham._09Supplier;

import java.util.Date;
import java.util.function.Supplier;

/**
 * @author jmusham
 *
 */
public class SupplierDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Supplier<Date> sDate = () -> new Date();
		System.out.println(sDate.get());
	}
}
