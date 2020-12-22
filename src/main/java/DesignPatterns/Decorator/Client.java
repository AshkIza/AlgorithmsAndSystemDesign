package DesignPatterns.Decorator;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import DesignPatterns.Decorator.SandwichExample.*;
import DesignPatterns.Decorator.SandwichExample.subClassing.*;

public class Client {
	
	

	public static void main(String[] args) {
		// sandwich example - wrong way (using subclassing)
		MeatSandwich meatSandwich = new MeatSandwich("Rye", "chicken");
		System.out.println(meatSandwich.make());
		
		VeggieSandwichWithSideDish veggieSandwichWithSideDish = 
				new VeggieSandwichWithSideDish("Whole wheat", "corn", "apple pie");
		System.out.println(veggieSandwichWithSideDish.make());
		
		VeggieSandwichWithDressingAndSideDish veggieSandwichWithDressingAndSideDish = 
				new VeggieSandwichWithDressingAndSideDish("Wheat", "lentil", "ketchup", "salad");
		System.out.println(veggieSandwichWithDressingAndSideDish.make());
		
		/*sandwich example - proper way, daisy-chaining decorators
	 	decorators are used as different concrete components 
	 	instead of subclassing base components (multi-level hierarchy)
	 	by decorating we limit concrete components to a single-level hierarchy, then with 
	 	daisy-chaining decorators, we can combine different functionalities of sub-components
		 */
		
		
		System.out.println("\ndecorator is still a concrete component, but we limit inheritance hierarchy to single level\n" + 
				"multiple decorators can be stacked on top of each other, \n" + 
				"  each adding new functionality\n" + 
				"(utilized both composition and inheritance)\n" + 
				"alternate to subclass (subclass completely overrides base functionalities and can access private base methods)\n\n");
		
		
		System.out.println("new SideDishDecorator(\" fries\",new DressingDecorator(\"mustard\",new VeggieDecorator(\"lentil & corn\",new BasicSandwich(\"Rye\"))));" + 
				"");
		Sandwich veggieSandwichWithDressingAndSideDish_decorator = 
		new SideDishDecorator(" fries",new DressingDecorator("mustard",new VeggieDecorator("lentil & corn",new BasicSandwich("Rye"))));
		System.out.println(veggieSandwichWithDressingAndSideDish_decorator.make());		
		
		// invent your own sandwiches 
		System.out.println("\nnew VeggieDecorator(\"lentil\", new MeatDecorator(\"beef\", new BasicSandwich(\"whole wheat\")))");
		Sandwich veggieMeatSandwich = new VeggieDecorator("lentil", new MeatDecorator("beef", new BasicSandwich("whole wheat")));
		System.out.println(veggieMeatSandwich.make());	

	}

}
