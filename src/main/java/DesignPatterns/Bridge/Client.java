package DesignPatterns.Bridge;

/* * 
  Dealing with 2 contracts(interfaces). 
  Each can have 2 or more implementations. (expect change from both sides)
Bridge pattern -> decouple abstraction and implementation

Interfaces and abstract classes 
abstractImplementor takes the second interface as composition.
We can have multiple implementations of abstractImplementor, and second interface

		Abstraction interfaceOne = new RefinedAbstraction01();
		Implementor interfaceTwo = new ConcreteImplementor02(interfaceOne);
		interfaceTwo.operation();
 * 
 * */
public class Client {
	
	public static void main(String[] args) {
		Abstraction interfaceOne = new RefinedAbstraction01();
		Implementor interfaceTwo = new ConcreteImplementor02(interfaceOne);
		interfaceTwo.operation();
		
		System.out.println("\n  Dealing with 2 contracts(interfaces). \n" + 
				"  Each can have 2 or more implementations. (expect change from both sides)\n" + 
				"Bridge pattern -> decouple abstraction and implementation\n" + 
				"\n" + 
				"Interfaces and abstract classes \n" + 
				"abstractImplementor takes the second interface as composition.\n" + 
				"We can have multiple implementations of abstractImplementor, and second interface\n" + 
				"\n" + 
				"		Abstraction interfaceOne = new RefinedAbstraction01();\n" + 
				"		Implementor interfaceTwo = new ConcreteImplementor02(interfaceOne);\n" + 
				"		interfaceTwo.operation();");
	}
}
