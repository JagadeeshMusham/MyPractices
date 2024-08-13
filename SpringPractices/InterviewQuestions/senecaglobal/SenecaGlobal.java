package com.musham.InterviewQuestions.senecaglobal;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import sun.reflect.generics.tree.Tree;

public class SenecaGlobal implements Serializable {

	public static void main(String args[]) {
		SenecaGlobal sg = new SenecaGlobal();
		try {
			FileOutputStream fos = new FileOutputStream("F.ser");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(sg);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
