package DesignPatterns.Bridge;

public class ConcreteImplementor01 extends Implementor{

	public ConcreteImplementor01(Abstraction anotherInterface) {
		super(anotherInterface);
	}

	@Override
	public void concreteMethod() {
		System.out.println(" ConcreteImplementor01 ");
	}

}
