package general.ClassTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class A {
	int counter;

	A(int counter) {
		this.counter = counter;
	}

	public void display() {
		System.out.println("This is in A class");
	}

	public void testInts() {
		System.out.println("Test integers");
	}
}

class B extends A {
	int[] a;
	int[] b;

	Integer[] c;
	Integer[] d;

	Integer m;
	int n;

	String str1;
	String str2;

	B(int counter) {
		super(counter);

		a = new int[10];
		d = new Integer[10];

		str1 = new String();
	}

	public void testIntegers() {
		a[0] = 10;
		int x[] = { 1, 2, 3, 4, 5 };
		d[0] = 10;
		Integer y[] = { 1, 2, 3, 4, 5 };

		m = 20;
		n = 20;

		System.out.println("a: " + a[0]);
		System.out.println("x: " + x[0]);
		System.out.println("d: " + d[0]);
		System.out.println("y: " + y[0]);

		str1 = "abc";
		str2 = "abc";

	}

	public void displayIntegers() {
		System.out.println("m: " + m + " n: " + n);
		System.out.println(str1);
		System.out.println(str2);
	}

	public void show() {
		System.out.println("This is in B class");
	}
}

public class ClassDemo {

	public static void main(String[] args) {

		System.out.println("Displaying A methods\n");
		A a = new A(2);
		displayMethods(a);

		A ab = new B(1);
		System.out.println("Displaying B methods\n");
		displayMethods(ab);

		System.out.println("Displaying A constructors\n");
		displayConstructors(a);

		System.out.println("Displaying B constructors\n");
		displayConstructors(ab);

		ab.testInts();

		B b = new B(2);
		b.testIntegers();
		b.displayIntegers();
	}

	public static void displayConstructors(A a) {
		Class cls = a.getClass();
		Constructor[] constructors = cls.getDeclaredConstructors();
		
		System.out.println("\nConstructors: ");
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor.getName());
		}
	}

	public static void displayMethods(A a) {
		Class<? extends A> cls = a.getClass();
		Method[] methodsList = cls.getDeclaredMethods();

		System.out.println("\nDeclared Methods: ");
		for (Method method : methodsList) {
			System.out.println(method.getName());
		}

		methodsList = cls.getMethods();
		System.out.println("\nMethods: ");
		for (Method method : methodsList) {
			System.out.println(method.getName());
		}

	}
}
