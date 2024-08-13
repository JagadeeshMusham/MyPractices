package com.musham.strings;

public class StringUtilities {

	private static String reverseString(String str) {
		char[] chars = str.toCharArray();
		int pos = 0;
		for (int counter = 0; counter < chars.length / 2; counter++) {
			char c = chars[pos];
			chars[pos++] = chars[chars.length - counter - 1];
			chars[chars.length - counter - 1] = c;
		}
		return String.valueOf(chars);
	}
	
	private static void immutable() {
		String s = "abc";
		s.concat("def");
		System.out.println(s);//it will print only "abc"; because of mutable property
		
		String r = "abc";
		System.out.println(s);
		
		r = "xyz";
		System.out.println(s);
	}

	public static void main(String[] args) {
		immutable();
		int a = Integer.valueOf("30");
		int b = Integer.getInteger("30");

		String str = "This is a Strings";
		String reverseOfStr = reverseString(str);
		System.out.println("Reverse of a string is: " + reverseOfStr);
		
		reverseOfStr = reverseStringUsingRecursion(str);
		System.out.println("Reverse of a string using recursion is: " + reverseOfStr);
	}

	private static String reverseStringUsingRecursion(String str) {
		if (str.length() == 1) {
			return str;
		}

		return str.charAt(str.length() - 1) + reverseStringUsingRecursion(str.substring(0, str.length() - 1));
	}

}
