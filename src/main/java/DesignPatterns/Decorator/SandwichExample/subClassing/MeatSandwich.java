package DesignPatterns.Decorator.SandwichExample.subClassing;

import DesignPatterns.Decorator.SandwichExample.BasicSandwich;

public class MeatSandwich extends BasicSandwich{
	String orderType;
	
	public MeatSandwich(String breadType, String orderType){
		super(breadType);
		this.orderType = orderType;
	}
	
	@Override
	public String make() {
		return super.make() + addMeat();
	}
	
	private String addMeat() {
		return ", Meat: " + orderType;
	}
}
