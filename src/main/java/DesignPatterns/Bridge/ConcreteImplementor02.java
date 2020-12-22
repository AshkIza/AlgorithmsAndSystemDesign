package DesignPatterns.Bridge;

public class ConcreteImplementor02 extends Implementor{

	public ConcreteImplementor02(Abstraction anotherInterface) {
		super(anotherInterface);
	}

	@Override
	public void concreteMethod() {
		System.out.print(" ConcreteImplementor02 ");
	}
}
