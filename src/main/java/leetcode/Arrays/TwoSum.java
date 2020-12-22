package leetcode.Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*  Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 * 
 * */
public class TwoSum {
	
    //naive solutions (ON2)
	/* + for integers, always test against 0 and Negative values
	 * + to improve time complexity, 
	 * 				you need to sacrifice space (introducing extra space) by 
	 * 					adding mem-cache, hashing(hashTables, hashMaps, hashSets),..
	 * */
    public static int[] twoSumNaive(int[] nums, int target) {
        int[] indices = new int[2];
        for(int i = 0; i < nums.length -1; i++){
            int index = find(i+1, target - nums[i], nums);
            if( index != -1){
                  indices[0] = i;
                indices[1] = index;
                return indices;
            }
        }
        return indices;
    }
    
    public static int find(int index, int n, int[] nums){
        for(int i = index; i < nums.length; i++){
            if(nums[i] == n){
                return i;
            }
        }
        return -1;
    }
    
    /* optimum solution,
     *  O(N) but extra space/hashing O(N)
    */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> pairs = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            pairs.put(target - nums[i], i);
        }
         for(int i = 0; i < nums.length; i++){
            if(pairs.get(nums[i]) != null && pairs.get(nums[i]) != i){ 
                return new int[]{i, pairs.get(nums[i])};
            }
        }
        return new int[]{0, 0};
    }


	public static void main(String[] args) {
		int[] nums01 = new int[] {2, 7, 11, 15};
		System.out.println("twoSumNaive({2, 7, 11, 15}, 9) is : " + Arrays.toString(twoSumNaive(nums01, 9)) + " -  O(N^2)");
		System.out.println("twoSum({2, 7, 11, 15}, 9) is : " + Arrays.toString(twoSumNaive(nums01, 9)) + " - O(N) & O(N) extra space ");

	}

}
