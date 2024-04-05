package general.lambda_expression;

interface MyNumber {
	Double getValue();
}

// Another functional interface.
interface NumericTest {
	boolean test(int n);
}

public class LambdaDemo {
	public static void main(String args[]) {
		MyNumber myNum; // declare an interface reference
		// Here, the lambda expression is simply a constant expression.
		// When it is assigned to myNum, a class instance is
		// constructed in which the lambda expression implements
		// the getValue() method in MyNumber.
		myNum = () -> 123.45;

		// Call getValue(), which is provided by the previously assigned
		// lambda expression.
		System.out.println("A fixed value: " + myNum.getValue());
		// Here, a more complex expression is used.
		Double d = Math.random();
		myNum = () -> d * 100;
		// These call the lambda expression in the previous line.
		System.out.println("A random value: " + myNum.getValue());
		System.out.println("Another random value: " + myNum.getValue());
		// A lambda expression must be compatible with the method
		// defined by the functional interface. Therefore, this won't work:
		// myNum = () -> "123.03"; // Error!
		
		// A lambda expression that tests if a number is even.
		NumericTest isEven = (n) -> (n % 2)==0;
		NumericTest isByZero = (n) -> (n /2)==0;
		System.out.println("isEven: " + isEven.test(10));
	}
}
