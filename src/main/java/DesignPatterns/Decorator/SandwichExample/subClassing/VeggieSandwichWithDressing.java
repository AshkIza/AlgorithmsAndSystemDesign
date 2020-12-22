package DesignPatterns.Decorator.SandwichExample.subClassing;

public class VeggieSandwichWithDressing extends VeggieSandwich{
	String orderType;
	
	VeggieSandwichWithDressing(String breadType, String veggieType, String orderType){
		super(breadType, veggieType);
		this.orderType = orderType;
	}
	
	@Override
	public String make() {
		return super.make() + addDressing();
	}
	
	private String addDressing() {
		return  ", Dressing: " + orderType;
	}
	
}
