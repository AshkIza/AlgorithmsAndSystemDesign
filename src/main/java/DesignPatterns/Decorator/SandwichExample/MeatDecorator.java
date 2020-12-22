package DesignPatterns.Decorator.SandwichExample;

public class MeatDecorator implements Sandwich{
	Sandwich basecomponent;
	String meatType;
	public MeatDecorator(String meatType, Sandwich basecomponent){
		this.basecomponent = basecomponent;
		this.meatType = meatType;
	}
	
	public String make() {
		return basecomponent.make() + addMeat();
	}
	
	private String addMeat() {
		return ", Meat: " + meatType;
	}
}
