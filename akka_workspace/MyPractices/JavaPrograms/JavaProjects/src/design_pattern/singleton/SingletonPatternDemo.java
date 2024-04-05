package design_pattern.singleton;

public class SingletonPatternDemo {
	public static void main(String [] args) {
		SingletonPattern instance = SingletonPattern.getInstance();
		instance.display();
	}
}
