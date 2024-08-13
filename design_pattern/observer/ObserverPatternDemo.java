package design_pattern.observer;

public class ObserverPatternDemo {
	public static void main(String[] args) {
		Subject subject = new Subject();
		
		new DecimalObserver(subject);
		new OctalObserver(subject);

		subject.setState(10);
		subject.setState(20);
	}
}
