package general.lambda_expression;

interface Lambda {
	public void getLambda();
}

public class LambdaExpression {
	// Lambda lambda;

	public static void main(String[] args) {
		Lambda lambda = () -> System.out.println("This is my own lambda methodology");

		lambda.getLambda();
		lambda.getLambda();

		callLambda(lambda);

		Lambda anonymous = new Lambda() {
			public void getLambda() {
				System.out.println("This is an anonymous inner class");
			}
		};

		anonymous.getLambda();
		callLambda(anonymous);
	}

	private static void callLambda(Lambda lambda) {
		lambda.getLambda();
	}
}
