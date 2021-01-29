package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/*  Climbing Stairs
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/569/
 * 
 * You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * */
public class ClimbingStairs {
	
	static Map<Integer, Integer> memoizedCache = new HashMap<>();
    public static int climbStairsMemoized(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        if(memoizedCache.containsKey(n)){
            return memoizedCache.get(n);//retrieving the saved solution
        }else{
            int result = climbStairsMemoized(n-1) + climbStairsMemoized(n-2);
            memoizedCache.put(n, result);//saving the soltion
            return result;
        }
    }
    
    static Map<Integer, Integer> tableResults = new HashMap<>();
    public static int climbStairsTabular(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        tableResults.put(1,1);
        tableResults.put(2,2);
        for(int i = 3; i <= n; i++){
            int result = tableResults.get(i-1) + tableResults.get(i-2);
            tableResults.put(i, result);
        }
        return tableResults.get(n);
    }
    
    

	public static void main(String[] args) {
		
		System.out.println("climbStairsMemoized(6) : " + climbStairsMemoized(6) );
		System.out.println("climbStairsTabular(6) : " + climbStairsTabular(6) );
		
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
