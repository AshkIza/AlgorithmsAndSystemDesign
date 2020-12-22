package leetcode.Strings;

import java.util.Arrays;

/* Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.

 

Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
 * */
public class ReverseString {

	
	public static void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i <= j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
	public static void main(String[] args) {
		char[] input = new char[]{'h','e','l','l','o'};
		reverseString(input);
		System.out.println("reverseString({'h','e','l','l','o'}) is : " + Arrays.toString(input)+ " -  O(N) with O(1) extra spcae");

	}
	
	/* Arrays.toString(array[]) 
	 * https://www.geeksforgeeks.org/arrays-tostring-in-java-with-examples/
	 * Returns a string representation of the contents of the specified array. 
	 * The string representation consists of a list of the array’s elements,
	 * 	 enclosed in square brackets (“[]”)
	 * 
	 *  boolean[] boolArr = new boolean[] { true, true, false, true }; 
        byte[] byteArr = new byte[] { 10, 20, 30 }; 
        char[] charArr = new char[] { 'g', 'e', 'e', 'k', 's' }; 
        double[] dblArr = new double[] { 1, 2, 3, 4 }; 
        float[] floatArr = new float[] { 1, 2, 3, 4 }; 
        int[] intArr = new int[] { 1, 2, 3, 4 }; 
        long[] lomgArr = new long[] { 1, 2, 3, 4 }; 
        Object[] objArr = new Object[] { 1, 2, 3, 4 }; 
        short[] shortArr = new short[] { 1, 2, 3, 4 }; 
  
        System.out.println(Arrays.toString(boolArr)); 
        System.out.println(Arrays.toString(byteArr)); 
        System.out.println(Arrays.toString(charArr)); 
        System.out.println(Arrays.toString(dblArr)); 
        System.out.println(Arrays.toString(floatArr)); 
        System.out.println(Arrays.toString(intArr)); 
        System.out.println(Arrays.toString(lomgArr)); 
        System.out.println(Arrays.toString(objArr)); 
        System.out.println(Arrays.toString(shortArr)); 
        
        	Output:

			[true, true, false, true]
			[10, 20, 30]
			[g, e, e, k, s]
			[1.0, 2.0, 3.0, 4.0]
			[1.0, 2.0, 3.0, 4.0]
			[1, 2, 3, 4]
			[1, 2, 3, 4]
			[1, 2, 3, 4]
			[1, 2, 3, 4]
	 * 
	 * */

}
