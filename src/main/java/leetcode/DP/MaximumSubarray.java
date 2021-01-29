package leetcode.DP;

/*  Maximum Subarray
Solution
	Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
	
	Example:
	
	Input: [-2,1,-3,4,-1,2,1,-5,4],
	Output: 6
	Explanation: [4,-1,2,1] has the largest sum = 6.
	Follow up:
	
	If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 *   
 *   Solution DP: https://www.programcreek.com/2013/02/leetcode-maximum-subarray-java/  
					O(N) with O(1) extra space
			 D&C :https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d
			      https://algorithms.tutorialhorizon.com/maximum-subarray-or-largest-sum-contiguous-subarray-problem-divide-and-conquer/
 * */
public class MaximumSubarray {
	
    public static int maxSubArrayDP(int[] nums) {
        if(nums.length == 1) return nums[0];
        int N = nums.length - 1;
        int maxSum = nums[N];//maximum susbarray at each index(tabulized value)
        int maxSubArraySum = maxSum;//maximum value from tabulation table (subproblems)
        for(int i = N-1; i>=0; i--){
            maxSum = Math.max(nums[i], nums[i] + maxSum);// include i+1 elements or not
            maxSubArraySum = Math.max(maxSubArraySum, maxSum);
        }
        return maxSubArraySum;
    }
    
    //TODO
    public static int maxSubArrayDAC(int[] nums) {return 0;}


	public static void main(String[] args) {
		System.out.println("maxSubArrayDP(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4} ) : "  + maxSubArrayDP(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4} ) );
		
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
