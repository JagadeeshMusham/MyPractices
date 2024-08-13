package com.musham.comparable;

import java.util.ArrayList;
import java.util.Collections;

public class ComparableDemo {
	public static void main(String args[]) {
		ArrayList<Student> al = new ArrayList<>();
		al.add(new Student(101, "Vijay", 23));
		al.add(new Student(106, "Ajay", 27));
		al.add(new Student(105, "Jai", 21));

		System.out.println("Before sorting: ");
		for (Student st : al) {
			System.out.println(st.rollno + " " + st.name + " " + st.age);
		}

		Collections.sort(al);
		System.out.println("\nAfter sorting: ");
		for (Student st : al) {
			System.out.println(st.rollno + " " + st.name + " " + st.age);
		}
	}
}
