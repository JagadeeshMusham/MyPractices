package design_pattern.strategy;

public class OperationSubtraction implements Strategy {

	@Override
	public int doOperation(int first, int second) {
		return first - second;
	}
}
