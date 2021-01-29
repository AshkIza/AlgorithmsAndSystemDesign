package leetcode.Arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/578/
 * 
 * */
public class ContainsDuplicate {
	
	public static boolean containsDuplicate(int[] nums) {
        if(nums.length <= 1) return false;
        Arrays.sort(nums);
        int lastvalue = nums[0];
        int index = 1;
        while(index < nums.length){
            if(nums[index] == lastvalue) return true;
            lastvalue = nums[index];
            index++;
        }
        return false;
    }
	
	public static boolean containsDuplicateUsingHashtable(int[] nums) {
        if(nums.length <= 1) return false;
        Set<Integer> table = new HashSet<>();
        int index = 0;
        while(index < nums.length){
            if(table.contains(nums[index])) return true;
            table.add(nums[index]);
            index++;
        }
        return false;
    }
	

	public static void main(String[] args) {				
		int[] nums = new int[] {1,1,1,3,3,4,3,2,4,2};
		System.out.println("nums : " + Arrays.toString(nums) + 
				" -> containsDuplicate(nums) : " + containsDuplicate(nums));
		
		System.out.println("nums : " + Arrays.toString(nums) + 
				" -> containsDuplicateUsingHashtable(nums) : " + containsDuplicateUsingHashtable(nums));

	}

}
