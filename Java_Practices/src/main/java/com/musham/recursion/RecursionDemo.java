package com.musham.recursion;

import java.util.ArrayList;
import java.util.List;

public class RecursionDemo {

	public static void main(String[] args) {
		String str = "abc";
		String reverseString = reverseString(str);

		System.out.println("The reverse of the string is: " + reverseString);

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		// List<Integer> reverseList = reverseList(list);
		// for (int counter : reverseList) {
		// System.out.print(counter);
		// }
	}

	private static String reverseString(String str) {
		if (str.length() == 1) {
			return str;
		}

		return str.charAt(str.length() - 1) + reverseString(str.substring(0, str.length() - 1));
	}

	private List<Integer> reverseList(List<Integer> list) {
		if (list.size() == 1) {
			return list;
		}

		// return list.get(list.size() - 1);

		return null;
	}

}
