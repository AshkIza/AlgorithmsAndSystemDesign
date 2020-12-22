package leetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

/*  backtrack : return back to the step before (backtrack)
 * Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 * 
 * 
 * The idea is that we can build a solution step by step using recursion; 
 * if during the process we realise that is not going to be a valid solution, 
 * then we stop computing that solution and we return back to the step before (backtrack). 
 * 
 * 
 *  * schema for BackTrack algorithm
   ( to save extra space for maining states during recursive calal down and backtracking up)
 *  
 *  for(state : states)
 *     states.add(state)
 *     backTrackRecursive(states)// state added for the next step
 *     states.remove(state)//backTrack : 
 *     // only revert state back to previous state. 
 *     //do NOT tamper results generated so far (only tamper states, backTrack to previous state - one layer up)
 * */
public class Permutations {

	   public List<List<Integer>> permute(int[] nums) {
	        List<List<Integer>> results = new ArrayList();
	        List<Integer> remaining = new ArrayList();
	        for(int i = 0; i < nums.length; i++){
	            remaining.add(nums[i]);   
	        }
	        for(int i = 0; i < nums.length; i++){
	            List<Integer> result = new ArrayList();
	            result.add(nums[i]);
	            remaining.remove(Integer.valueOf(nums[i]));//remove the Object NOT the index
	            results = backTrack(remaining, result, results);
	            remaining.add(nums[i]);//backtrack/going up to previous step
	            //does the autoboxing int --> Integer
	        }
	        return results;
	    }
	    
	   /*    The idea is that we can build a solution step by step using recursion; 
			  if during the process we realise that is not going to be a valid solution, 
			  then we stop computing that solution and we return back to the step before (backtrack). 
	    * */
	    private List<List<Integer>> backTrack(List<Integer> remaining, List<Integer> result, 
	                                        List<List<Integer>> results){
	        if(remaining.isEmpty()){
	            results.add(result);
	            return results;
	        }
	        
	        //can not loop through List and remove object (concurrentException) - loop through its copy
	        List<Integer> states = new ArrayList<Integer>(remaining); 
	        for(Integer state : states){
	            List<Integer> newResult = new ArrayList(result);
	            newResult.add(state);
	            remaining.remove(state);//recursively going down the branch (new state/ next level)
	            results = backTrack(remaining, newResult, results);
	            remaining.add(state);//return back to the step before (backtrack) and recosntructing the state at upper level 
	        }
	        return results;
	    }
	    
	    void print(List<List<Integer>> results){
	    	for(List<Integer> row : results){
	    		System.out.println( row.toString());

	    	}
	    }
	public static void main(String[] args) {
		System.out.println( "return back to the step before (backtrack)");

		Permutations permutations = new Permutations();
		System.out.println( "permutations.permute(new int[] {1,2,3})");
		permutations.print(permutations.permute(new int[] {1,2,3}));
		System.out.println( "\npermutations.permute(new int[] {1})");
		permutations.print(permutations.permute(new int[] {1}));
		System.out.println( "\npermutations.permute(new int[] {2,6,9,20})");
		permutations.print(permutations.permute(new int[] {2,6,9,20}));
		System.out.println( "\npermutations.permute(new int[] {1,1,2})");
		permutations.print(permutations.permute(new int[] {1, 1, 2}));
		
		System.out.println( "\nreturn back to the step before (backtrack)");

		
	}

}
