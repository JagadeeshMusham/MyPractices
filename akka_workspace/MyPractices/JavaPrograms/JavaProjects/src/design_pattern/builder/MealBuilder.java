package design_pattern.builder;

public abstract class MealBuilder {
	protected Meal meal = new Meal();
	public abstract void buildDrink();
	public abstract void buildMainCourse();
	public abstract void buildDesert();
	public abstract Meal getMeal();
}
