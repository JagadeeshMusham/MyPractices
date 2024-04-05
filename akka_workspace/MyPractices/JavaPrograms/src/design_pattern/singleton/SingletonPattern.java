package design_pattern.singleton;

public class SingletonPattern {
	private static SingletonPattern instance;
	
	private SingletonPattern() {
		
	}
	
	public static SingletonPattern getInstance() {
		if (instance == null) {
			instance = new SingletonPattern();
		}
		
		return instance;
	}
	
	public void display() {
		System.out.println("Single pattern demo");
	}
}
