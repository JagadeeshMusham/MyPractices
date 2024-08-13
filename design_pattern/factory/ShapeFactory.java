package design_pattern.factory;

public class ShapeFactory {
	public Shape getShape(String shapeType) {
		Shape shape = null;
		if (shapeType == null || shapeType.length() == 0) {
			System.out.println("Invalid input. Please try with valid input");
			return shape;
		}

		if (shapeType.equalsIgnoreCase("Circle")) {
			shape = new Circle();
		} else if (shapeType.equalsIgnoreCase("Square")) {
			shape = new Square();
		} else if (shapeType.equalsIgnoreCase("Rectangle")) {
			shape = new Rectangle();
		}

		return shape;
	}
}
