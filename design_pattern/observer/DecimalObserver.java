package design_pattern.observer;

public class DecimalObserver extends Observer{
	
	public DecimalObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	public void update() {
		System.out.println("Display decimal value: " + this.subject.getState());
	}
}
