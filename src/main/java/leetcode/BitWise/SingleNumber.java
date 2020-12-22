package leetcode.BitWise;

/*  Single Number
Solution
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4

SOLUTION:  using bitwise operations
https://www.programcreek.com/2012/12/leetcode-solution-of-single-number-in-java/
 * */
public class SingleNumber {
	
	
	public static int singleNumberBitWise(int[] nums) {
		int number = 0;// x ^ 0 = x ; x^ 1 = ~x
		for(int a : nums){
			number ^= a;
		}
		return number;
	}
	

	public static void main(String[] args) {
		
		System.out.println( " singleNumberBitWise -  O(N) time with O(N) extra space O(1) - improved the space complexity by using XOR bitwise operation property");

		int[] nums01 = new int[] {4,1,2,1,2};
		System.out.println("\n  singleNumberBitWise({4,1,2,1,2}) is : " + singleNumberBitWise(nums01));
		
		int[] nums02 = new int[] {4,1,-20, 33, 0, 2, 1, -20, 2, 33, 4};
		System.out.println("  singleNumberBitWise({4,1,-20, 33, 0, 2, 1, -20, 2, 33, 4}) is : " + singleNumberBitWise(nums02));

		
		System.out.println( "\n General Rules for optimum solutions: \n 1) least optimum solution: 2 inner loops O(N^2) \n 2) use extra space O(N) by hashing (or another data structure like tree, linkedlist, queue) to improve time to o(N) / O(logN) \n 3) use special feature of the data in the problem to reduce space complexity to O(1)");

	
	}

}
