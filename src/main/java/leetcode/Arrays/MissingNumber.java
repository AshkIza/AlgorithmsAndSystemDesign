package leetcode.Arrays;

import java.util.HashSet;
import java.util.Set;

/*Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * 
 * */
public class MissingNumber {
	
	/* how to improve o(N^2) naive algorithms
	 * 
	 * 1) by using extra space (hashset, hashMap, mem-cache,..), reducing time complexity to o(N)
	 * 2) even improve the time complexity much more, by observing special characteristics in the data 
	 * 		(1..n, all positive, all duplicated, bit-wise operations)
	 *      so having linear time (O(N) with in-place operations (no extra space O(1))
	 * */
	
	//todo useHashing (not using special property of data) 
	 public static int missingNumberO_N_Space(int[] nums) {
	        int N = nums.length;
	        Set<Integer> keys = new HashSet<>();
	        for(int i = 0; i <= N; i++){
	        	keys.add(i);
	        }
	        for(int i = 0; i < N; i++){
	        	keys.remove(nums[i]);
	        }
	        
	        return keys.iterator().next();
	    }
	
	  //in linear time with O(1) extra space (using special characteristics of the data : sum(0...n) = n(n+1)/2)
    public static int missingNumber(int[] nums) {
        int N = nums.length;
        int sum = N * (N+1) /2;
        for(int i = 0; i < N; i++){
            sum -= nums[i];
        }
        return sum;
    }

	public static void main(String[] args) {
		int[] nums01 = new int[] {3,0,1};
		int[] nums02 = new int[] {9,6,4,2,3,5,7,0,1};
		System.out.println("missingNumber({3,0,1}) is : " + missingNumber(nums01) +  "    O(N) time with O(1) extra space (using special characteristics of the data : sum(0...n) = n(n+1)/2)");
		System.out.println("missingNumber({9,6,4,2,3,5,7,0,1}) is : " + missingNumber(nums02) +  "   O(N) time with O(1) extra space (using special characteristics of the data : sum(0...n) = n(n+1)/2)");

		System.out.println();
		System.out.println("missingNumberO_N_Space({3,0,1}) is : " + missingNumberO_N_Space(nums01) +  "    O(N) time with O(N) extra space ");
		System.out.println("missingNumberO_N_Space({9,6,4,2,3,5,7,0,1}) is : " + missingNumberO_N_Space(nums02) +  "    O(N) time with O(N) extra space");
	}

}
