package com.musham.ClassDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Locale;

public class ClsDemo {

	public static void main(String[] args) {
		ClassDemoInput demo = new ClassDemoInput();
		Class<ClassDemoInput> cls = (Class<ClassDemoInput>) demo.getClass();
		Method[] methods = cls.getMethods();

		for (Method method : methods) {
			System.out.println("Get method: " + method.getName());
		}

		methods = cls.getDeclaredMethods();

		for (Method method : methods) {
			System.out.print("Get declared method: " + method.getName());

			Parameter[] parameters = method.getParameters();
			System.out.print("\tParameters are:\t");
			for (Parameter parameter : parameters) {
				System.out.print(parameter + "\t");
			}

			System.out.println("\n");

		}

		Method method;
		try {
			method = cls.getDeclaredMethod("f2");
			method.invoke(demo);

			method = cls.getDeclaredMethod("f1", String.class);
			method.invoke(demo, "abc");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Constructor[] constructors = cls.getConstructors();
		for (Constructor constructor : constructors) {
			System.out.print("Get declared method: " + constructor.getName());

			Parameter[] parameters = constructor.getParameters();
			System.out.print("\tParameters are:\t");
			for (Parameter parameter : parameters) {
				System.out.print(parameter + "\t");
			}
			System.out.println("\n");
		}
		
		
		Locale locale=Locale.getDefault();  
		//Locale locale=new Locale("fr","fr");//for the specific locale  
		  
		System.out.println(locale.getDisplayCountry());  
		System.out.println(locale.getDisplayLanguage());  
		System.out.println(locale.getDisplayName());  
		System.out.println(locale.getISO3Country());  
		System.out.println(locale.getISO3Language());  
		System.out.println(locale.getLanguage());  
		System.out.println(locale.getCountry());  

	}

}