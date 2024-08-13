package design_pattern.builder;

public class Meal {
	private String drink;
	private String mainCourse;
	private String desert;
	/**
	 * @return the drink
	 */
	public String getDrink() {
		return drink;
	}
	/**
	 * @param drink the drink to set
	 */
	public void setDrink(String drink) {
		this.drink = drink;
	}
	/**
	 * @return the mainCourse
	 */
	public String getMainCourse() {
		return mainCourse;
	}
	/**
	 * @param mainCourse the mainCourse to set
	 */
	public void setMainCourse(String mainCourse) {
		this.mainCourse = mainCourse;
	}
	/**
	 * @return the desert
	 */
	public String getDesert() {
		return desert;
	}
	/**
	 * @param desert the desert to set
	 */
	public void setDesert(String desert) {
		this.desert = desert;
	}
}
