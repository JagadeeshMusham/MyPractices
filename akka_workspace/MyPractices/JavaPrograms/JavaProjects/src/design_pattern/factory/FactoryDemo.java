package design_pattern.factory;

public class FactoryDemo {
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();

		Shape shape = shapeFactory.getShape("Circle");
		shape.drawShape();

		shape = shapeFactory.getShape("Rectangle");
		shape.drawShape();

		shape = shapeFactory.getShape("Square");
		shape.drawShape();
	}
}
