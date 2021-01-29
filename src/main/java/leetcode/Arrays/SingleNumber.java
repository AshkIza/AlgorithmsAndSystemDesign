package leetcode.Arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*  Single Number
Solution
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4

SOLUTION: https://www.programcreek.com/2012/12/leetcode-solution-of-single-number-in-java/
 * */
public class SingleNumber {

	//todo: algorithms with linear time and no extra space O(1)
	
	
	/*  used hashSet to represent unique values (filter out duplicates)
	 * O(N) with O(N) extra space
	 * */
	public static int singleNumber(int[] nums) {
        Set<Integer> keys = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(keys.contains(nums[i])){
                keys.remove(nums[i]);
            }else{
                keys.add(nums[i]);
            }
        }
        
        if(keys.size() == 1){
            return keys.iterator().next();
        }else{
            return -1;
        }
    }
	
	
	 public static int singleNumberXOR(int[] nums) {
	        int single = 0;
	        for(int num : nums){
	            single ^= num;
	        }
	        return single;
	    }
	
	
	
	public static void main(String[] args) {
		int[] nums01 = new int[] {4,1,2,1,2};
		System.out.println("singleNumber({4,1,2,1,2}) is : " + singleNumber(nums01) +  " - \n O(N) time with O(N) extra space (used hashing)");
	
		System.out.println("singleNumberXOR({4,1,2,1,2}) is : " + singleNumberXOR(nums01) +  " - \n O(N) time with O(1) extra space (noticing special characteritics of data -> all elements comming twice except one element --> a ^ a = 0)");

	}
	
	/*  How to get the first item from Set ? 
	 * https://www.mkyong.com/java/java-how-to-get-the-first-item-from-set/
	 *   
	 *   since Set does NOT have positional operations (such as get() in List), we need to rely on iterator.next()
	 * 		set.iterator().next()
	 * 
	 * Set<String> examples = new HashSet<>();
        examples.add("1");
        examples.add("2");
        examples.add("3");
        examples.add("4");
        examples.add("5");

        System.out.println(examples.iterator().next());
	 * 
	 * */

}
