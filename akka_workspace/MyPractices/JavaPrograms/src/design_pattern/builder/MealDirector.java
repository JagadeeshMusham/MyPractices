package design_pattern.builder;

public class MealDirector {
	public Meal createMeal(MealBuilder builder) {
		builder.buildDrink();
		builder.buildMainCourse();
		builder.buildDesert();

		Meal meal = builder.getMeal();

		System.out.println("The drinks in the meal is: " + meal.getDrink());
		System.out.println("The main course in the meal is: " + meal.getMainCourse());
		System.out.println("The desert in the meal is: " + meal.getDesert());

		return meal;
	}
}
