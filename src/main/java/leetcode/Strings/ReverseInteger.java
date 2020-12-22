package leetcode.Strings;

import java.util.Arrays;

/*  Reverse Integer
Solution
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
 [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 0 when 
 the reversed integer overflows.
 * 
 * */
public class ReverseInteger {
	
    public static int reverse(int x) {
        boolean negative = (x <0) ? true : false;
        x = Math.abs(x);
        int result = 0;
        while ( x > 0) {
            int m = x % 10;
            int temp = result * 10 + m;
            if((temp - m)/10 != result){ //check for overflow
            	return 0;
            }else{
            	result = temp;
            }
            x /= 10;
      }
      return (negative ?  (result * -1 ) : result);
    }
    
    
    
    
    private static int returnWithinWhileLoop(){
    	int i = 0;
    	while( i < 202){
    		System.out.println("in while loop : " + i);
    		if( i == 5){
    			return Integer.MIN_VALUE;
    		}
    		i++;
    	}
    	
    	return i;
    }
    
    private static int returnWithinInnerWhileLoop(){
    	int i = 1;
    	while( i < 20){
    		System.out.println("in outr while loop : " + i);
    		int j = i -2;
    		while( j <= i){
        		System.out.println("in inner while loop : " + j);
    			if( i == 5){
        			return Integer.MIN_VALUE;
        		}
    			j++;
    		}
    		
    		i++;
    	}
    	
    	return i;
    }
	
	

	public static void main(String[] args) {
		System.out.println("return Within While Loop - returns from the loop and method");
		System.out.println(returnWithinWhileLoop());
		
		System.out.println("\nreturn Within inner While Loop - still returns from the loop and method");
		System.out.println(returnWithinInnerWhileLoop());
		
		System.out.println(" \n Integer.MAX_VAUE : " + Integer.MAX_VALUE);
		System.out.println(" Integer.MIN_VALUE : " + Integer.MIN_VALUE);

		System.out.println("reverse(1534236469) : " + reverse(1534236469));
		System.out.println("reverse(123) : " + reverse(123));
		System.out.println("reverse(120) : " + reverse(120));
		System.out.println("reverse((-123) : " + reverse(-123));
		
		System.out.println("\n overflow value can be any int value - positive or negative");
		System.out.println("1534236469 * 10  : " + 1534236469 * 10);
		System.out.println("(Integer.MAX_VALUE  / 9) * 10  : " + (Integer.MAX_VALUE  / 9) * 10 );
	}
	
	/*   Java - Does returning a value break a loop?
	 * https://stackoverflow.com/questions/10661081/java-does-returning-a-value-break-a-loop
	 * 
	 * Yes*
			Yes, usually (and in your case) it does break out of the loop and returns from the method.

	  An Exception*
		One exception is that if there is a finally block inside the loop and surrounding the return statement then the code in the finally block will be executed before the method returns. The finally block might not terminate - for example it could contain another loop or call a method that never returns. In this case you wouldn't ever exit the loop or the method.

			while (true)
			{
			    try
			    {
			        return;  // This return technically speaking doesn't exit the loop.
			    }
			    finally
			    {
			        while (true) {}  // Instead it gets stuck here.
			    }
			}
			
	  *NOTE: should also add that if you want to break the current iteration of the loop, 
	  	and instantly start the next one, you can use:
			continue;
	 * 
	 * 
	 * */

}
