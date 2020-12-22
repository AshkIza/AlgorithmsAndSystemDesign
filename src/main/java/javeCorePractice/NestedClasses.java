package javeCorePractice;

public class NestedClasses {
	
	///Nested interfaces are declared static implicitly.
	interface NestedInterface{
		void interfaceMethod();
	}
	
	
    class NONstaticImplOfNestedInterface implements NestedInterface{

		@Override
		public void interfaceMethod() {
	    	System.out.println("from inner class NONstaticImplOfNestedInterface - implementing Nested Interface");
		}
		
	}
    
	static class StaticImplOfNestedInterface implements NestedInterface{

		@Override
		public void interfaceMethod() {
	    	System.out.println("from inner class staticImplOfNestedInterface - implementing Nested Interface");
		}
		
	}
	
	

	public static void main(String[] args) {
		System.out.println("****************** Static nested class *******************");
		OuterClass.StaticNestedClass.staticMethod();
		OuterClass.StaticNestedClass staticNested = new OuterClass.StaticNestedClass("fooooooo");
		staticNested.NoNstaticMethod();
		
		System.out.println("\n\n************** Inner class (Non-Static Nested class) ***************");
		System.out.println("1) member inner class:");
		OuterClass outer = new OuterClass("dummmmy");
		OuterClass.innerClass  inner = outer.new innerClass("goooody");
		inner.nonStaticMethod();
		
		System.out.println("\n 2) Anonymous inner class:");
		outer.concreteMethod();
		OuterClass anonymousInnerClass = new OuterClass("blah blah"){
			
			@Override
		    void concreteMethod(){
		    	System.out.println("from Anonymous inner class");
		    }
			
		};
		anonymousInnerClass.concreteMethod();
		
		NestedClasses.NestedInterface nestedInterfaceInstance = new NestedClasses.NestedInterface(){
			@Override
			public void interfaceMethod() {
		    	System.out.println("from Anonymous inner class - implementing Nested Interface");
			}
		};
		nestedInterfaceInstance.interfaceMethod();
		
		System.out.println("\n 3) local inner class:");
		outer.methodWithLocalInnerClass();
		
		
		System.out.println("\n\n******************  Nested Interface (declared static implicitly) *******************");
		NestedClasses nestedClasses = new NestedClasses(); 
		NestedClasses.NONstaticImplOfNestedInterface option01 = nestedClasses.new NONstaticImplOfNestedInterface();
		option01.interfaceMethod();
		
		NestedClasses.NestedInterface option02 = new NestedClasses.StaticImplOfNestedInterface();
		option02.interfaceMethod();

	}

}
