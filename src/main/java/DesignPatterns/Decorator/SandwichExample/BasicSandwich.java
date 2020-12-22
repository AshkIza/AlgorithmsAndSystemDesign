package DesignPatterns.Decorator.SandwichExample;

public class BasicSandwich implements Sandwich {
	String orderType;
	public BasicSandwich(String orderType) {
		this.orderType = orderType;
	}
	public String make() {
		return "Bread : " + orderType;
	}
}
