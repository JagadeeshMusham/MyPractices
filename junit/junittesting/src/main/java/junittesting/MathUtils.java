package junittesting;

/**
 * Defining the arithmetic operations
 */
enum Operation {
	SUM, DIF, MUL, DIV
}

/**
 * @author jmusham
 *
 */
public class MathUtils {

	/**
	 * Defining of MathService object
	 */
	public MathService mathService;

	/**
	 * @param mathService
	 */
	public MathUtils(MathService mathService) {
		this.mathService = mathService;
	}

	/**
	 * @param firstValue  Input value 1
	 * @param secondValue Input Value 2
	 * @param operation   Operation name to perform the arithmetic operation
	 * @return returns the result value
	 */
	public int performOperation(int firstValue, int secondValue, Operation operation) {
		System.out.println(MathService.displayWithArgument(2));
		System.out.println(MathService.display());
		MathService.displayVoid();
		
		System.out.println("Testing...");
		switch (operation) {
		case SUM:
			int num = mathService.addition(firstValue, secondValue);
			return num + firstValue;
		case DIF:
			return mathService.subtraction(firstValue, secondValue);
		case MUL:
			return mathService.multiplication(firstValue, secondValue);
		case DIV:
			return mathService.division(firstValue, secondValue);
		default:
			System.out.println("No proper service found");
		}

		return -1;
	}

}
