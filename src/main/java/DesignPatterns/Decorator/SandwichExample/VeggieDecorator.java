package DesignPatterns.Decorator.SandwichExample;

public class VeggieDecorator implements Sandwich {
	Sandwich basecomponent;
	String veggieType;
	public VeggieDecorator(String veggieType, Sandwich basecomponent){
		this.basecomponent = basecomponent;
		this.veggieType = veggieType;
	}
	
	public String make() {
		return basecomponent.make() + addVeggie();
	}
	
	private String addVeggie() {
		return ", Veggie: " + veggieType;
	}
}

