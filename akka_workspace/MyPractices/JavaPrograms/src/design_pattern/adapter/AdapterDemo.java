package design_pattern.adapter;

public class AdapterDemo {

	public static void main(String[] args) {
		System.out.println("Adapter demo");
		
		Parrot parrot = new Parrot();
		parrot.makeSound();
		parrot.fly();
		
		PlasticToyDuck plasticToyDuck = new PlasticToyDuck();
		plasticToyDuck.makeSound();
		
		AdapterToyDuck adapterToyDuck = new AdapterToyDuck(parrot);
		adapterToyDuck.makeSound();
	}
}
