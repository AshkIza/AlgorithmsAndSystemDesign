package leetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

/*  Given a set of distinct integers, nums, return all possible subsets (the power set).

	Note: The solution set must not contain duplicate subsets.
	
	Example:
	
	Input: nums = [1,2,3]
	Output:
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
 * 
 * solution : http://www.goodtecher.com/leetcode-78-subsets-java/
 * 
 * 
 * schema for BackTrack algorithm
   ( to save extra space for maining states during recursive calal down and backtracking up)
 *  
 *  for(state : states)
 *     states.add(state)
 *     backTrackRecursive(states)// state added for the next step
 *     states.remove(state)//backTrack : 
 *     // only revert state back to previous state. 
 *     //do NOT tamper results generated so far (only tamper states, backTrack to previous state - one layer up)
 *     // generate result as much as you need (do NOT reuse Or tamper)
 *     // result.add(value)  result.remove(value)  WRONG!!!!  end up having empty Lists as results
 *     [ [] [] [] [] [] ]
 * 
 * */
public class Subsets {
	
    List<List<Integer>> results = new ArrayList();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> result = new ArrayList<>();
        backTrack(nums, 0 , result);
        return results;
    }

    
    public void backTrack(int[] nums, int index, List<Integer> result) {
        if(index == nums.length){//end of branch (a solution found)
            results.add(result);
            return;
        }
        // going down the space-state tree (index not-included/included)
        backTrack(nums, index+1, result);
        
        List<Integer> result1 = new ArrayList<>(result);
        result1.add(nums[index]);
        backTrack(nums, index+1, result1);
    }
    
    void print(List<List<Integer>> results){
    	for(List<Integer> row : results){
    		System.out.println( row.toString());

    	}
    }

	public static void main(String[] args) {
		Subsets subsets = new Subsets();
		System.out.println( "permutations.permute(new int[] {1,2,3})");
		subsets.print(subsets.subsets(new int[] {1,2,3}));

	}

}
