package com.musham.InterviewQuestions.pramati.codility;

import java.io.IOException;

class AirPlane {
	public AirPlane() throws IOException {
		System.out.println("AirPlane");
		throw new IOException();
	}
}

class AirJet extends AirPlane {
	public AirJet() throws IOException {
			super();
	}
}

public class General {
	public static int divide(int a, int b) {
		int c = -1;
		try {
			c= a/b;
		} catch (Exception e) {
			System.err.print("Exception");
		}
		finally {
			System.err.println("Finally");
		}
		
		return c;
	}
	
	public static void main(String args[]) {
		
		int c = divide(1, 2);
		
		String a[] = {"a", "b"}; 
		try {
			new AirJet();
		} catch (IOException e) {
			System.out.println("IOException is thrown in Tester");
		}
	}
}
