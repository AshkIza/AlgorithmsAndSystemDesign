package DesignPatterns.Decorator.SandwichExample;

public class SideDishDecorator implements Sandwich {
	Sandwich basecomponent;
	String sideDish;
	public SideDishDecorator(String sideDish, Sandwich basecomponent){
		this.basecomponent = basecomponent;
		this.sideDish = sideDish;
	}
	
	public String make() {
		return basecomponent.make() + addSideDish();
	}
	
	private String addSideDish() {
		return ", Side dish: " + sideDish;
	}
}
