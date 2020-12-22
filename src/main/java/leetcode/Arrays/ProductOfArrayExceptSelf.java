package leetcode.Arrays;

import java.util.Arrays;

/*  Product of Array Except Self
Solution
Given an array nums of n integers where n > 1, 
 return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity?
 (The output array does not count as extra space for the purpose of space complexity analysis.)
 
 NOTE:
 I used Dynamic programming and recursion
 OTHER solutions: https://www.programcreek.com/2014/07/leetcode-product-of-array-except-self-java/
 * 
 * */
public class ProductOfArrayExceptSelf {
	
	public static int[] productExceptSelf(int[] nums) {
		int N = nums.length;
		int[] out = new int[N];
		int d = 1;
		if( N > 2) {
			int outerProduct = nums[0] * nums[N-1];
		    d = innerProduct(nums, 1, N-2, outerProduct, out);
		}	
		out[0]   = d * nums[N-1];
		out[N-1] = d * nums[0];
		return out;
    }
	
	public static int innerProduct(int[] nums, int ia, int ib, int outerProduct, int[] out) {
		if(ia > ib){
			return 1;
		}
		if(ia == ib){
			out[ia] = outerProduct;
			return nums[ia];
		}
		int d = innerProduct(nums, ia+1, ib-1, outerProduct * nums[ia] * nums[ib], out);
		out[ia] = outerProduct * d * nums[ib];
		out[ib] = outerProduct * d * nums[ia];
		return d * nums[ia] * nums[ib];
	}

	public static void main(String[] args) {
		
		System.out.println(" productExceptSelf({3,4}) : " + Arrays.toString(productExceptSelf(new int[]{3,4})));
		System.out.println(" productExceptSelf({7,8,9}) : " + Arrays.toString(productExceptSelf(new int[]{7,8,9})));
		System.out.println(" productExceptSelf({1,2,3,4}) : " + Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));
		System.out.println(" productExceptSelf({1,2,3,4,5}) : " + Arrays.toString(productExceptSelf(new int[]{1,2,3,4,5})));
		System.out.println(" productExceptSelf({1,2,3,4,5,6,7,8}) : " + Arrays.toString(productExceptSelf(new int[]{1,2,3,4,5,6,7,8})));
	}
}
