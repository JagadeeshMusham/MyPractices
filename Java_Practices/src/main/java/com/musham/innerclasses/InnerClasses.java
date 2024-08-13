package com.musham.innerclasses;

public class InnerClasses {
	public int x = 0;
	private int y = 15;
	static int a = 28;

	static class StaticNestedClass {
		public int y = 10;

		void methodInNestedClass(int z) {
			System.out.println("z = " + z);
			System.out.println("this.y = " + this.y);
			System.out.println("a = " + a);
			/*
			 * Following is an error (i.e., No enclosing instance of the type
			 * InnerClasses is accessible in scope)
			 */
			// System.out.println("InnerClasses.this.x = " +
			// InnerClasses.this.x);
		}

	}

	class InnerClass {

		public int y = 1;

		void methodInInnerClass(int z) {
			System.out.println("x = " + z);
			System.out.println("this.y = " + this.y);
			System.out.println("x = " + x);
			System.out.println("InnerClasses.this.y = " + InnerClasses.this.y);
		}
	}

	public static void main(String... args) {
		InnerClasses st = new InnerClasses();
		InnerClasses.InnerClass fl = st.new InnerClass();
		fl.methodInInnerClass(23);

		InnerClasses.StaticNestedClass snc = new InnerClasses.StaticNestedClass();
		snc.methodInNestedClass(23);
	}
}
