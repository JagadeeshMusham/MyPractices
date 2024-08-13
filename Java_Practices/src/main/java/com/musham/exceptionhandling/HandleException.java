package general.exceptionhandling;

public class HandleException {
	private static void throwException() {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Throw an Exception: ");
		throwException();
	}

}
