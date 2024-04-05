package design_pattern.builder;

public class BuilderDemo {
	public static void main(String[] args) {
		MealDirector mealDirector = new MealDirector();

		MealBuilder mealBuilder = new KidsMeal();
		mealDirector.createMeal(mealBuilder);

		mealBuilder = new AdultMeal();
		mealDirector.createMeal(mealBuilder);
	}
}
