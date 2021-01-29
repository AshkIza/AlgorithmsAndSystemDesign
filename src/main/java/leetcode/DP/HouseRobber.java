package leetcode.DP;

import java.util.Arrays;

/* https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/576/
 * You are a professional robber planning to rob houses along a street.
 *  Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of 
 *  them is that adjacent houses have security system connected and it will automatically contact 
 *  the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
 determine the maximum amount of money you can rob tonight without alerting the police.
 
 Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
 * 
 * */
public class HouseRobber {
	
	public static int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int N = nums.length - 1;
        int[] tableresult = new int[nums.length];
        tableresult[N] = nums[N];
        tableresult[N-1] = nums[N-1];
        int maxprofit = Math.max(nums[N], nums[N-1]);
        for(int i = N-2; i >=0; i--){
            int p1 = tableresult[i+2];
            int p2 = -1;
            if(i+3 <=N) p2 = tableresult[i+3];
            tableresult[i] = nums[i] + Math.max(p1, p2);
            maxprofit = Math.max(maxprofit, tableresult[i]);
        }
        return maxprofit;
    }

	public static void main(String[] args) {
		int[] case01 = new int[] {2,7,9,3,1};
		System.out.println("case01 : " + Arrays.toString(case01));
		System.out.println("rob(case01) : " + rob(case01));
		
		int[] case02 = new int[] {2, 1, 3, 9, 1};
		System.out.println("\ncase02 : " + Arrays.toString(case02));
		System.out.println("rob(case02) : " + rob(case02));
		
		System.out.println("NOTE on maximization problems:");
		System.out.println("	maximization problem --> screams for Dynamic programming ");
		System.out.println("	Dynamic programming --> trying to break in them problem into 'overlapping' sub-problem");
		System.out.println("	'overlapping' sub-problems --> saving the subproblem results (recursively or iteratively).");
		System.out.println("	recursively -> memoizedCache (top to bottom approach)");
		System.out.println("	iteratively -> tabulation (bottom up approach)");

		System.out.println("NOTE on maximization problems on Arrays (Dynamic Programming with Arrays):");
		System.out.println("	Iterate through array (recursive solution is not best performing) -> tabular approach");
		System.out.println("	tabular approach (bottom-up)—> approach array from end to being (bottom-up) to build your tabular results");

		

	}

}
