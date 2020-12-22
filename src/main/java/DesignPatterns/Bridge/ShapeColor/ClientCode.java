package DesignPatterns.Bridge.ShapeColor;

public class ClientCode {

	public static void main(String[] args) {
		System.out.println("Shape shape01 = new Circle(new Green()) -  drawing shape01");
		Shape shape01 = new Circle(new Green());
		shape01.draw();
		
		System.out.println("\nRed redColor = new Red()");
		Red redColor = new Red();
		
		System.out.println("\nShape shape02 = new Square(redColor)");
		Shape shape02 = new Square(redColor);
		shape02.draw();
		
		System.out.println("\nnew Circle(redColor).draw()");
	    new Circle(redColor).draw();
	    
	    
		System.out.println("\nnew Triangle(redColor).draw()");
	    new Triangle(redColor).draw();
		
		
	}
}
