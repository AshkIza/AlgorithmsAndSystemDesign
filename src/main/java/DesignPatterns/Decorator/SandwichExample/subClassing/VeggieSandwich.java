package DesignPatterns.Decorator.SandwichExample.subClassing;

import DesignPatterns.Decorator.SandwichExample.BasicSandwich;

public class VeggieSandwich extends BasicSandwich{
	String orderType;
	
	public VeggieSandwich(String breadType, String orderType){
		super(breadType);
		this.orderType = orderType;
	}
	
	@Override
	public String make() {
		return super.make() + addVeggie();
	}
	
	private String addVeggie() {
		return ", Veggie: " + orderType;
	}
}
