package hackerank;

import java.util.Arrays;

/*  https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem
 * 
 * Given an array of integers, 
 * 	find and print the minimum absolute difference between any two elements in the array.
 *  For example, given the array  we can create  pairs of numbers:  and . 
 * The absolute differences for these pairs are ,  and . The minimum absolute difference is .
 * 
 * 
 * */
public class MinimumAbsoluteDifferenceinanArray {
	
	 static int minimumAbsoluteDifference(int[] arr) {
	        Arrays.sort(arr);
	        int min = arr[1] - arr[0];
	        for(int i = 2 ; i < arr.length ; i++){
	            min = (arr[i] - arr[i-1] < min) ? arr[i] - arr[i-1] : min;
	        }
	        return min;
	    }

	public static void main(String[] args) {
		System.out.println("minimumAbsoluteDifference(new int[] {-59, -36, -13, 1, -53, -92, -2, -96, -54, 75}) : " + 
				minimumAbsoluteDifference(new int[] {-59, -36, -13, 1, -53, -92, -2, -96, -54, 75}));
	}

}
