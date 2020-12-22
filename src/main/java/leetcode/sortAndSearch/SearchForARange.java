package leetcode.sortAndSearch;

import java.util.Arrays;

/**  Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

		Your algorithm's runtime complexity must be in the order of O(log n).
		
		If the target is not found in the array, return [-1, -1].
		
		Example 1:
		
		Input: nums = [5,7,7,8,8,10], target = 8
		Output: [3,4]
		Example 2:
		
		Input: nums = [5,7,7,8,8,10], target = 6
		Output: [-1,-1]
		
		https://www.programcreek.com/2014/04/leetcode-search-for-a-range-java/
 * 
 * 
 * */
public class SearchForARange {
	
    public static int[] searchRange(int[] nums, int target) {
    	return searchRange(nums, 0, nums.length -1, target);
    }
    
    public static int[] searchRange(int[] nums, int start, int stop, int target) {
    	if(stop  - start < 0){
    		return new int[]{-1, -1};
    	}
    	int mid = (start + stop)/2;//binary search (logn)
    	if(nums[mid] == target){
    		return findRange(nums, mid);
    	}else if(nums[mid] < target){
    		return searchRange(nums, mid+1, stop, target);
    	}else{
    		return searchRange(nums, start, mid-1, target);
    	}
    }
    
    public static int[] findRange(int[] nums, int index) {
    	int i = index;
    	int j = index;
    	while(i >=1 && nums[i-1] == nums[index]){
    		i = i - 1;
    	}
    	while(j+1 <=nums.length-1 && nums[j+1] == nums[index]){
    		j++;
    	}
    	return new int[]{i,j};
    }
    
	public static void main(String[] args) {
		int[] nums = new int[]{5,7,7,8,8,10};
		System.out.println("  searchRange({5,7,7,8,8,10}, 8): " + Arrays.toString(searchRange(nums, 8)));
		System.out.println("  searchRange({5,7,7,8,8,10}, 6): " + Arrays.toString(searchRange(nums, 6)));
		
		System.out.println("  searchRange({5}, 6): " + Arrays.toString(searchRange(new int[]{5}, 6)));
		System.out.println("  searchRange({5,10}, 6): " + Arrays.toString(searchRange(new int[]{5,10}, 6)));
		System.out.println("  searchRange({5,6,10}, 6): " + Arrays.toString(searchRange(new int[]{5,6,10}, 6)));
		System.out.println("  searchRange({5,6,6,6,6,6,10}, 6): " + Arrays.toString(searchRange(new int[]{5,6,6,6,6,6,10}, 6)));
		System.out.println("  searchRange({5,6,7,8,9,10,10,10}, 6): " + Arrays.toString(searchRange(new int[]{5,6,7,8,9,10,10,10}, 6)));







	}

}
