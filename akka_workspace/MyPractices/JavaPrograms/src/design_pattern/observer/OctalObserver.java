package design_pattern.observer;

public class OctalObserver extends Observer {
	Subject subject;

	public OctalObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	public void update() {
		System.out.println("The Octal value is: " + this.subject.getState());
	}
}
