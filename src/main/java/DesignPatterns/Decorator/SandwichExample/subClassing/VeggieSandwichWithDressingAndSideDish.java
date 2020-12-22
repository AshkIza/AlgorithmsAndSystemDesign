package DesignPatterns.Decorator.SandwichExample.subClassing;

public class VeggieSandwichWithDressingAndSideDish extends VeggieSandwichWithDressing{
	String orderType;
	
	public VeggieSandwichWithDressingAndSideDish(String breadType, String veggieType, String dressing, String orderType){
		super(breadType, veggieType, dressing);
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

