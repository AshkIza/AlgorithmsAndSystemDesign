package leetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

/* Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

solution: https://www.programcreek.com/2014/02/leetcode-combination-sum-java/

Combinations vs Permutations
https://medium.com/i-math/combinations-permutations-fa7ac680f0ac

Combinations and Permutations
https://www.mathsisfun.com/combinatorics/combinations-permutations.html
	When the order doesn't matter, it is a Combination.
	When the order does matter it is a Permutation.
	
where n is the number of things to choose from,
and we choose r of them:

1. Permutations with Repetition     n × n × ... (r times) = n^r

2. Permutations without Repetition  P(n, r) = n! / (n-r)!

3. Combinations without Repetition  C(n, r) =  n! / ( r! (n-r)! ) 

4. Combinations with Repetition  C(n+r-1, r) = (n+r-1)!/ (r! (n-1)! )

But at least now you know how to calculate all 4 variations of:
 "Order does/does not matter" and
 "Repeats are/are not allowed".
 * 
 * */
public class CombinationSum {
	
	   List<List<Integer>> results = new ArrayList();
	    
	   //The solution set must not contain duplicate combinations
	    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	    	results.clear();
	        backTrack(candidates, target, 0, new ArrayList<Integer>());
	        return results;
	    }
	    
	    private void backTrack(int[] candidates, int remaining, int startIndex, 
	                           List<Integer> result){
	        if(remaining == 0){
	            results.add(result);
	            return;
	        }
	        // repeateing combination (instead of repeating permutation) - perfoemnace enhancment
	        for(int index = startIndex; index<candidates.length; index++){
	            if(candidates[index] <= remaining){
	                List<Integer> newResult = new ArrayList<>(result);
	                newResult.add(candidates[index]);
	                backTrack(candidates, remaining - candidates[index],
	                          index, newResult);
	            }
	        }
	    }
	    
	    // 
	    public List<List<Integer>> combinationSumPermutation(int[] candidates, int target) {
	    	results.clear();
	    	backTrackPermutation(candidates, target, new ArrayList<Integer>());
	        return results;
	    }
	    
	    private void backTrackPermutation(int[] candidates, int remaining, List<Integer> result){
	        if(remaining == 0){
	            results.add(result);
	            return;
	        }
	        for(int index = 0; index<candidates.length; index++){
	            if(candidates[index] <= remaining){
	                List<Integer> newResult = new ArrayList<>(result);
	                newResult.add(candidates[index]);
	                backTrackPermutation(candidates, remaining - candidates[index], newResult);
	            }
	        }
	    }
	    
	    void print(List<List<Integer>> results){
	    	for(List<Integer> row : results){
	    		System.out.println( row.toString());

	    	}
	    }

	public static void main(String[] args) {
		CombinationSum combinationSum = new CombinationSum();
		
		System.out.println("combinationSum.combinationSum(new int[]{2,3,6,7}, 7)");
		combinationSum.print(combinationSum.combinationSum(new int[]{2,3,6,7}, 7));
		
		System.out.println("combinationSum.combinationSum(new int[]{2,3,5}, 8)");
		combinationSum.print(combinationSum.combinationSum(new int[]{2,3,5}, 8));
		
		System.out.println("\n***in permutations, we care about the order of the elements, whereas with combinations we don’t.***");
		System.out.println("combinationSum.combinationSumPermutation(new int[]{2,3,6,7}, 7)");
		combinationSum.print(combinationSum.combinationSumPermutation(new int[]{2,3,6,7}, 7));
		System.out.println("Here, we needed 'repeating combinations', NOT 'repeating permutations'");


	}

}
