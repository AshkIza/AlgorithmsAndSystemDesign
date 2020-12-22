package DesignPatterns.Decorator.SandwichExample;

public class DressingDecorator implements Sandwich {
	Sandwich basecomponent;
	String dressing;
	public DressingDecorator(String dressing, Sandwich basecomponent){
		this.basecomponent = basecomponent;
		this.dressing = dressing;
	}
	
	public String make() {
		return basecomponent.make() + addDressing();
	}
	
	private String addDressing() {
		return ", Dressing: " + dressing;
	}
}
