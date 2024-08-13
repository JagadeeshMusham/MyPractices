package com.musham.codility.format_mobile_number;

public class Solution {
	private static String provideSolution(String s) {
		s = s.replace(" ", "");
		s = s.replace("-", "");

		char[] sArray = s.toCharArray();

		if (sArray[1] == ' ') {
			
		}
		StringBuilder sbr = new StringBuilder();
		int strLength = sArray.length; //s.length();
		int counter = 0;
		
		int diffCounter = 3;
		for (char ch : sArray) {
			sbr.append(ch);
			if (strLength == 1)
				break;
			if (strLength-- > 2) {
				if (++counter == diffCounter) {
					sbr.append("-");
					counter = 0;
					
					if (strLength == 4) {
						diffCounter = 2;
					}
				}
			}
			
			//--strLength;
		}

		return sbr.toString();
	}

	public static void main(String[] args) {
		String s = "00-44   48 5555 8361";
		System.out.println(provideSolution(s));

		s = "00-44   48 5555 83610";
		System.out.println(provideSolution(s));

		s = "0 - 22 1985--324";
		System.out.println(provideSolution(s));
	}
}
