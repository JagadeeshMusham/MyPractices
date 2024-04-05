package design_pattern.strategy;

public class StrategyDemo {
	public static void main(String[] args) {
		Context context = new Context(new OperationAdd());
		System.out.println("The addition of 10, 5 is: " + context.executeStrategy(10, 5));

		context = new Context(new OperationMultiplication());
		System.out.println("The multiplication of 10 and 5 is: " + context.executeStrategy(10, 5));

		context = new Context(new OperationSubtraction());
		System.out.println("The subtraction of 10, 5 is: " + context.executeStrategy(10, 5));
	}
}
