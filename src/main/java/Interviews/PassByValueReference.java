package Interviews;

/*  Java is Pass by Value and Not Pass by Reference

I hope the above explanation clears all the doubts,
 just remember that variables are references or pointers and its copies are passed to the methods,
  so java is always pass-by-value.
  
It would be more clear when you will learn about Heap and Stack memory and where different objects and references are stored,
 for a detailed explanation with reference to a program, read Java Heap vs Stack.


 * 
 * */
public class PassByValueReference {
	
	public static class Balloon {

		private String color;

		public Balloon(){}
		
		public Balloon(String c){
			this.color=c;
		}
		
		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
	}
	
	private static void foo(Balloon balloon) { //baloon=100
		balloon.setColor("Red"); //baloon=100
		balloon = new Balloon("Green"); //baloon=200
		balloon.setColor("Blue"); //baloon = 200
	}

	//Generic swap method
	public static void swap(Object o1, Object o2){
		Object temp = o1;
		o1=o2;
		o2=temp;
	}

	public static void main(String[] args) {
		Balloon red = new Balloon("Red"); //memory reference 50
		Balloon blue = new Balloon("Blue"); //memory reference 100
		swap(red, blue);
		System.out.println("red color="+red.getColor());
		System.out.println("blue color="+blue.getColor());
		System.out.println("If you look at the first two lines of the output, "
				+ "it’s clear that swap method didn’t worked."
				+ "\n This is because Java is pass by value, "
				+ "this swap() method test can be used with any programming language"
				+ " to check whether it’s pass by value or pass by reference.");
		
		System.out.println("----------");
		
		foo(blue);
		System.out.println("blue color="+blue.getColor());
		System.out.println(" The first line is the important one,"
				+ " when we call a method the method is called on the Object at the reference location. "
				+ "At this point, balloon is pointing to 100 and hence it’s color is changed to Red.\n" + 
				"In the next line, ballon reference is changed to 200 and any further methods executed are happening on the object at memory location 200 and not having any effect on the object at memory location 100. "
				+ "This explains the third line of our program output printing blue color=Red.");

	}
}
