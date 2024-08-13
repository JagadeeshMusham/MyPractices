package design_pattern.strategy;

public class Context {
	private Strategy strategy;
	
	public Context(Strategy strategy) {
		this.strategy = strategy; 
	}
	
	public int executeStrategy(int first, int second) {
		return strategy.doOperation(first, second);
	}
}
