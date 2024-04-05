package junittesting;

public class MathService {

	/**
	 * @param firstValue
	 * @param secondValue
	 * @return returns the result value
	 */
	public int addition(int firstValue, int secondValue) {
		return firstValue + secondValue;
	}

	/**
	 * @param firstValue
	 * @param secondValue
	 * @return returns the result value
	 */
	public int subtraction(int firstValue, int secondValue) {
		return firstValue - secondValue;
	}

	/**
	 * @param firstValue
	 * @param secondValue
	 * @return returns the result value
	 */
	public int multiplication(int firstValue, int secondValue) {
		return firstValue * secondValue;
	}

	/**
	 * @param firstValue
	 * @param secondValue
	 * @return returns the result value
	 */
	public int division(int firstValue, int secondValue) {
		return firstValue / secondValue;
	}

	public static String display() {
		return "do nothing";
	}

	public static String displayWithArgument(int intValue) {
		return "do nothing" + intValue;
	}

	public static void displayVoid() {
		System.out.println("displayVoid");
	}

}
