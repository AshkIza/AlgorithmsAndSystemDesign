package DesignPatterns.Bridge.ShapeColor;

public class Circle extends Shape {
	public Circle(Color color) {
		super(color);
	}

	@Override
	public void draw() {
		System.out.println( color.getColor() + " Circle ");
	}
}