package leetcode.Math;

/*  Given a 32-bit signed integer, reverse digits of an integer.

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
 [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 
 solution : https://stackoverflow.com/questions/21070506/reverse-integer-leetcode-how-to-handle-overflow
 
NOTE : 32-BIT INTEGER OVERFLOW can be Positive or Negative   : 
 964632435 * 10 --> 1056389758
 864632435 * 10 --> 56389758
 664656435 * 10 --> -1943370242
 
 NOTE :  Math.abs(Integer.MIN_VALUE) = Integer.MIN_VALUE
 * 
 * */
public class ReverseInteger {
	
    public int reverse(int x) {
        boolean negative = x < 0;
        x = Math.abs(x); // Math.abs(Integer.MIN_VALUE) = Integer.MIN_VALUE
        int result = 0;
        while ( x > 0) {
            int m = x % 10;
            int a = result * 10 + m;
            /* check for integer overflow 
             * 32-BIT INTEGER OVERFLOW can be Positive or Negative
             * */
            if ((a-m)/10 != result){ 
                return 0;
            }
            result = a;
            x /= 10;
        }
        return negative ?  0 -  result : result;
    }
    
    public int reverseCastToLong(int x) {
        boolean negative = x < 0;
        long n = x;
        n = Math.abs(n);
        long result = 0;
        while ( n > 0 ) {
            result = result * 10 + n % 10;
            n /= 10;
        }
        result = negative ?  0 -  result : result;
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
        	return 0;
        }
        return (int) result;
    }

	public static void main(String[] args) {
		ReverseInteger reverseInteger = new ReverseInteger();
		System.out.println("reverseInteger.reverse(1534236469) = "  + reverseInteger.reverse(1534236469)); 
		System.out.println("reverseInteger.reverse(Integer.MIN_VALUE) = "  + reverseInteger.reverse(Integer.MIN_VALUE)); 
		System.out.println("reverseInteger.reverse(Integer.MAX_VALUE) = "  + reverseInteger.reverse(Integer.MAX_VALUE)); 
		System.out.println("reverseInteger.reverse(123) = "  + reverseInteger.reverse(123)); 
		System.out.println("reverseInteger.reverse(-123) = "  + reverseInteger.reverse(-123)); 


		System.out.println("Integer.MIN_VALUE = "  + Integer.MIN_VALUE); 
		
		System.out.println("\nNOTE : 32-BIT INTEGER OVERFLOW can be Positive or Negative  " + 964632435   + " : 964632435 * 10 --> " + (964632435 * 10)); 
		System.out.println("NOTE : 32-BIT INTEGER OVERFLOW can be Positive or Negative  " + 864632435  +   " : 864632435 * 10 --> " + (864632435 * 10)); 
		System.out.println("NOTE : 32-BIT INTEGER OVERFLOW can be Positive or Negative  "  + 664656435  + " : 664656435 * 10 --> " + (664656435 * 10)); 
		
		System.out.println("\nreverseInteger.reverseCastToLong(1534236469) = "  + reverseInteger.reverseCastToLong(1534236469)); 
		System.out.println("reverseInteger.reverseCastToLong(Integer.MIN_VALUE) = "  + reverseInteger.reverseCastToLong(Integer.MIN_VALUE)); 
		System.out.println("reverseInteger.reverseCastToLong(Integer.MAX_VALUE) = "  + reverseInteger.reverseCastToLong(Integer.MAX_VALUE)); 
		System.out.println("reverseInteger.reverseCastToLong(123) = "  + reverseInteger.reverseCastToLong(123)); 
		System.out.println("reverseInteger.reverseCastToLong(-123) = "  + reverseInteger.reverseCastToLong(-123)); 
	}
}
