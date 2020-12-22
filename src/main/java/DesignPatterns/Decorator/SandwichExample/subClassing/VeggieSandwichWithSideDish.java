package DesignPatterns.Decorator.SandwichExample.subClassing;

public class VeggieSandwichWithSideDish extends VeggieSandwich{
	String orderType;
	
	public VeggieSandwichWithSideDish(String breadType, String veggieType, String orderType){
		super(breadType, veggieType);
		this.orderType = orderType;
	}
	
	@Override
	public String make() {
		return super.make() + addSideDish();
	}
	
	private String addSideDish() {
		return ", side dish: " + orderType;
	}
}

