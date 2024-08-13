package design_pattern.builder;

public class AdultMeal extends MealBuilder {

	Meal meal = new Meal();
	@Override
	public void buildDrink() {
		meal.setDrink("Coke");
	}

	@Override
	public void buildMainCourse() {
		meal.setMainCourse("Biryani");
	}

	@Override
	public void buildDesert() {
		meal.setDesert("Butterscotch");
	}

	@Override
	public Meal getMeal() {
		return meal;
	}

}
