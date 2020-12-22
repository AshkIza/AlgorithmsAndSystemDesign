package leetcode.Math;

/*  Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

	Example 1:
	
	Input: 16
	Output: true
	Example 2:
	
	Input: 5
	Output: false
	Follow up: Could you solve it without loops/recursion?
	
	
NOTE: Find fractional part of  decimal numbers (double) : 
                    d % 1
   1) for integer numbers           -> n % 1 = 0  ( n/1 = n)
   2) for decimal numbers (d = n.f) -> d % 1 = f  ( d/1 = n)
 if( d % 1 == 0 ) -> fraction part of decimal number is 0.0 (it is an integer number) 
 * 
 * */

public class PowerOfFour {
	
    public boolean isPowerOfFour(int num) {
        if(num <= 0){
            return false;
        }
        while(num % 4 == 0 ){
            num /= 4;
        }
        return num == 1;
    }
    
    public boolean isPowerOfFourUseMath(int num) {
        return noFraction( Math.log(num) / Math.log(4) );
    }
    
    public boolean noFraction(double d){
    	return d % 1 == 0;
    }

	public static void main(String[] args) {
		PowerOfFour powerOfFour = new PowerOfFour();
		System.out.println("  Naive approach "); 

		System.out.println("powerOfFour.isPowerOfFour(16) " + powerOfFour.isPowerOfFour(16)); 
		System.out.println("powerOfFour.isPowerOfFour(-5) " + powerOfFour.isPowerOfFour(-5)); 
		System.out.println("powerOfFour.isPowerOfFour(5) " + powerOfFour.isPowerOfFour(5)); 
		System.out.println("powerOfFour.isPowerOfFour(1) " + powerOfFour.isPowerOfFour(1)); 
		
		System.out.println(" \n Mathematical approach: use mathematical characteristics if(log4(n) is integer) -> n is power of 4 "); 
		System.out.println("powerOfFour.isPowerOfFourUseMath(16) " + powerOfFour.isPowerOfFourUseMath(16)); 
		System.out.println("powerOfFour.isPowerOfFourUseMath(-5) " + powerOfFour.isPowerOfFourUseMath(-5)); 
		System.out.println("powerOfFour.isPowerOfFourUseMath(5) " + powerOfFour.isPowerOfFourUseMath(5)); 
		System.out.println("powerOfFour.isPowerOfFourUseMath(1) " + powerOfFour.isPowerOfFourUseMath(1)); 
		
		System.out.println("\nNOTE : Find fractional part of  decimal numbers (double) : d % 1"); 
		System.out.println("   1) for integer numbers           -> n % 1 = 0  ( n/1 = n)"); 
		System.out.println("   2) for decimal numbers (d = n.f) -> d % 1 = f  ( d/1 = n)"); 
		System.out.println(" if( d % 1 == 0 ) -> fraction part of decimal number is 0.0 (it is an integer number) "); 
		
		System.out.println("\n  123 % 1 = " + (123 % 1)); 
		System.out.println("  123.45 % 1 = " + (123.45 % 1)); 
		System.out.println("  noFraction(1.2) " + powerOfFour.noFraction(1.2)); 
		System.out.println("  noFraction(1) " + powerOfFour.noFraction(1)); 
		System.out.println("  noFraction(23.898) " + powerOfFour.noFraction(23.898)); 
		System.out.println("  noFraction(8766.0) " + powerOfFour.noFraction(8766.0)); 
	}

}
