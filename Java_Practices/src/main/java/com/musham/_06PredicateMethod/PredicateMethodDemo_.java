package com.musham._06PredicateMethod;

/**
 * @author jmusham
 *
 */
public class PredicateMethodDemo_ {
	/**
	 * @param num
	 * @return
	 */
	public static Boolean isEvent(int num)
	{
		if (num%2 == 0)
		{
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(isEvent(10));
		System.out.println(isEvent(5));
	}

}
