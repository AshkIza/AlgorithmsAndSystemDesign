package leetcode.Strings;

import java.util.Arrays;

/*		Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
Example 1:

Input: "42"
Output: 42
Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.
 * 
 * */
public class StringToInteger {
	
	 public static int myAtoi(String str) {
	        int index = 0;
	        while(index < str.length() && str.charAt(index) == ' '){
	            index++;
	        }
	        char ch = str.charAt(index);
	        if(ch == '-'){
	            return convert(str, index +1, true);
	        }else if(ch >='0' && ch<='9'){
	            return convert(str, index, false);
	        }else{
	            return 0;
	        }
	    }
	    
	    private static int convert(String str, int index, boolean isNegative){
	        int result = 0;
	        while(index < str.length() && str.charAt(index) >='0' && str.charAt(index)<='9'){
	            int val = Integer.parseInt(String.valueOf(str.charAt(index)));
	            int temp = result * 10 + val;
	            int reverse = (temp - val) / 10;
	            if( reverse != result){
	                return Integer.MIN_VALUE;
	            }else{
	                result = temp;
	            }
	              index++;

	        }
	        return isNegative ? result * -1 : result;
	    }

	public static void main(String[] args) {
		System.out.println("myAtoi('   -124') is : " + myAtoi("     -124"));
		System.out.println("myAtoi('-91283472332') is : " + myAtoi("-91283472332"));
		System.out.println("myAtoi('words and 987') is : " + myAtoi("words and 987"));
		System.out.println("myAtoi('4193 with words') is : " + myAtoi("4193 with words"));
		System.out.println("myAtoi('42') is : " + myAtoi("42"));
		System.out.println("myAtoi('   21474836485n') is : " + myAtoi("   21474836485n"));
		System.out.println("myAtoi('   00214748364n') is : " + myAtoi("   00214748364n"));
		
		System.out.println(" \n\nconvert char to int");
		System.out.println(" Character.getNumericValue('9') : " + Character.getNumericValue('9'));
		System.out.println(" Integer.parseInt(String.valueOf('9')) : " + Integer.parseInt(String.valueOf('9')));
		System.out.println("  ((int) '9' : " +  ((int) '9') + "   (ASCII INTEGER VALUE OF CHARACTER)");
		
	}
	
	
	/*	Convert char to int
	 * https://www.javatpoint.com/java-char-to-int
	 * 1) Get ASCII value   :  (int) '9'
	 * 2) Character.getNumericValue()              
	 * 3) Integer.parseInt(String.valueOf(c))
	 * */
	
	/*  Integer to String 
	 * Integer.toString(int i) vs String.valueOf(int i)
	 * https://stackoverflow.com/questions/3335737/integer-tostringint-i-vs-string-valueofint-i
	 * 
	 * 
	 * 
	 * 
	 * */

}
