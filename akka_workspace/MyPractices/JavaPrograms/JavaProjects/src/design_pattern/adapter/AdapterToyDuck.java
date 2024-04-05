package design_pattern.adapter;

public class AdapterToyDuck implements ToyDuck {
	Bird bird;

	public AdapterToyDuck(Bird bird) {
		this.bird = bird;
	}

	public void makeSound() {
		bird.makeSound();
	}
}
