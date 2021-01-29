package leetcode.BitWise;

import java.util.Arrays;

/*Missing Number
 * 
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?


 * 
 * */
public class MissingNumber {
	
	public static int missingNumber(int[] nums) {
        int N = nums.length;
        int sum = (int) (N * (N+1))/2;
        int missingSum = 0;
        for(int i = 0; i < N; i++){
            missingSum += nums[i];
        }
        return sum - missingSum;
    }

	public static void main(String[] args) {
		int[] nums = new int[] {9,6,4,2,3,5,7,0,1};
		System.out.println(Arrays.toString(nums) + " missingNumber() : " + missingNumber(nums));

	}

}
