package general;

class Generics<T, U> {
	private T t;
	private U u;

	Generics(T t, U u) {
		this.t = t;
		this.u = u;
	}

	public void printGenericTypes() {
		System.out.println("The type of the t is: " + t.getClass().getName());
		System.out.println("The type of the u is: " + u.getClass().getName());
	}

	public T getT() {
		return t;
	}

	public U getU() {
		return u;
	}
}

public class GenericsDemo {
	public static void main(String[] args) {
		int num = 10;
		String str = "My Generics";
		Generics<Integer, String> generics = new Generics<>(num, str);

		generics.printGenericTypes();

		System.out.println("The integer value is: " + generics.getT());
		System.out.println("The String value is: " + generics.getU());
	}
}
