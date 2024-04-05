package design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	List<Observer> observerList;
	private int state;
	
	Subject() {
		observerList = new ArrayList<>();
	}
	
	public void attach(Observer observer) {
		observerList.add(observer);
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
		this.notifyAllObservers();
	}
	
	private void notifyAllObservers() {
		for (Observer observer : observerList) {
			observer.update();
		}
	}
}
