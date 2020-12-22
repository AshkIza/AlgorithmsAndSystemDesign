package javeCorePractice;

/*Nested class is a member of outer class and can access outer class members (in case of inner class) 
 * or just static members of outer class (in case of static nested class)
 */
public class OuterClass {
    String outerMember;
    static String staticOuterMemeber = "Static member of Outer Class";
    
    OuterClass(String a){
    	outerMember = a; 
    }
    
    void concreteMethod(){
    	System.out.println("from OuterClass");
    }
    
    void methodWithLocalInnerClass(){
    	class LocalInner{
    		String localInnerVariable;
    		
    		LocalInner(String a){
    			localInnerVariable = a;
    		}
    		
    		void showOuterAndInnerVariable(){
    	    	System.out.println("\nLocalInner");
    			System.out.println("localInnerVariable : " +  localInnerVariable);
    			System.out.println("outerMember : " +  outerMember);
    			System.out.println("staticOuterMemeber : " +  staticOuterMemeber);
    			System.out.println("calling method of outer class from local inner class");
    			concreteMethod();
    		}
    	}
    	
    	LocalInner localInner01 = new LocalInner(" LocalInnerClass instance01");
    	LocalInner localInner02 = new LocalInner(" LocalInnerClass instance02");
    	localInner01.showOuterAndInnerVariable();
    	localInner02.showOuterAndInnerVariable();
    }
    
	
	
	
	static class StaticNestedClass{
		static String staticMemeberOfStaticNestedClass = " static member in the static nested class";
		String nonStaticMember = "";
		
		StaticNestedClass(String a){
			nonStaticMember = a;
		}
		public void NoNstaticMethod(){
			System.out.println("from non-static method of static nested class");
			System.out.println("	staticOuterMemeber : " + staticOuterMemeber + "\n	staticMemeberOfStaticNestedClass : " + staticMemeberOfStaticNestedClass + "\n	NONStaticMember of its own : " + nonStaticMember);
			System.out.println("StaticNestedClass can NOT access ");
			System.out.println("outerMember : " + " non-static memebr of outer class");
		}
		
		static void staticMethod(){
			System.out.println("from static method of static nested class");
			System.out.println("	staticMemeberOfStaticNestedClass : "  + staticMemeberOfStaticNestedClass );
		}
	}
	
	class innerClass {//member inner class
		String innerClassVariable;
		
		innerClass(String a){
			innerClassVariable = a;
		}
		
		public void nonStaticMethod(){
			System.out.println("inner class can access ALL instance and Class members of outer class");
			System.out.println("	outerMember : " + outerMember  + "\n	staticOuterMemeber : " + staticOuterMemeber);
			System.out.println("	innerClassVariable : " + innerClassVariable);

		}
	}
	
	

}