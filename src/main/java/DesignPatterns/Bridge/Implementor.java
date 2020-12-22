package DesignPatterns.Bridge;

public abstract class Implementor {
	private Abstraction anotherInterface;
	
	Implementor(Abstraction anotherInterface){
		this.anotherInterface = anotherInterface;
	}
	
	public void operation() {
		anotherInterface.method();
		concreteMethod();
	}
	
	public abstract void concreteMethod();
}
