package design_pattern.builder;

public class KidsMeal extends MealBuilder{

	Meal meal = new Meal();
	@Override
	public void buildDrink() {
		meal.setDrink("Sprite");
	}

	@Override
	public void buildMainCourse() {
		meal.setMainCourse("Fried Rice");
	}

	@Override
	public void buildDesert() {
		meal.setDesert("Venila");
	}

	@Override
	public Meal getMeal() {
		return meal;
	}

}
