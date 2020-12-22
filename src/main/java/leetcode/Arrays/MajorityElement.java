package leetcode.Arrays;

import java.util.HashMap;
import java.util.Map;

/*  Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

	You may assume that the array is non-empty and the majority element always exist in the array.
	
	Example 1:
	
	Input: [3,2,3]
	Output: 3
	Example 2:
	
	Input: [2,2,1,1,1,2,2]
	Output: 2
	
	Solution:https://www.techiedelight.com/find-majority-element-in-an-array-boyer-moore-majority-vote-algorithm/
	
	Boyer–Moore majority vote algorithm : 
	https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
	However, if there is no majority, the algorithm will not detect that fact, and will still output one of the elements.
	
 * 
 * */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        if (nums.length == 0){
            return -1;
        }
        if (nums.length == 1){
            return nums[0];
        }
        Map<Integer, Integer> map = new HashMap();
        int N = (int) (nums.length / 2);
        for(int element : nums){
            if(map.containsKey(element)){
                int count = map.get(element);
                if(count + 1 > N){
                    return element;
                }
                 map.put(element, count + 1);
            }else{
                map.put(element, 1);
            }
        }
        return -1;
    }
    
    
	public static void main(String[] args) {
		MajorityElement majorityVotingAlgorithm = new MajorityElement();
		System.out.println("majorityVotingAlgorithm.majorityElement(new int[] {2,2,1,1,1,2,2}) : " 
				+ majorityVotingAlgorithm.majorityElement(new int[] {2,2,1,1,1,2,2}));
		
		System.out.println("majorityVotingAlgorithm.majorityElement(new int[] {1}) : " 
				+ majorityVotingAlgorithm.majorityElement(new int[] {1}));
		
		System.out.println("majorityVotingAlgorithm.majorityElement(new int[] {}) : " 
				+ majorityVotingAlgorithm.majorityElement(new int[] {}));

		System.out.println("majorityVotingAlgorithm.majorityElement(new int[] {1,2,3,3}) : " 
				+ majorityVotingAlgorithm.majorityElement(new int[] {1,2,3,3}));
		
		System.out.println("\nALWAYS CONSIDER empty ARRAY and ARRAY of size 1 IN YOUR TEST CASES");

	}

}
