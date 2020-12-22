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
        if( nums.length == 0 ){
            return 0;
        }
        int SubArraySum = nums[nums.length -1];
        int max = SubArraySum;
        for(int i = nums.length -2; i >= 0; i--){
            SubArraySum = Math.max(nums[i], nums[i]+SubArraySum);//tabulized value
            max = Math.max(max, SubArraySum);
            //maximum value from tabulation table (table of subproblems)
        }
        return max;
    }
    
    //TODO
    public static int maxSubArrayDAC(int[] nums) {return 0;}


	public static void main(String[] args) {
		System.out.println("maxSubArrayDP(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4} ) : "  + maxSubArrayDP(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4} ) );
		System.out.println(" \nmaximization problem --> screams for Dynamic Problamming " );
		System.out.println(" 	Dynamic Problamming --> trying to break in problem to 'overlapping' sub-problems " );
		System.out.println(" 	     'overlapping' sub-problems --> saving the subproblem results in mem-Cache(random and recursive) or tabulized (iteratively)" );
		System.out.println(" 	        since sub-problems are 'overlapping' -->  results from previous steps help us to build results for next steps" );



	}

}
