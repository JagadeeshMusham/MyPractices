package design_pattern.adapter;

public class Parrot implements Bird {

	public void makeSound() {
		System.out.println("Parrot sound");
	}
	
	public void fly() {
		System.out.println("Parrot can fly");
	}
}
