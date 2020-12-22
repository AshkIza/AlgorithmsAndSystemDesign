package leetcode.sortAndSearch;

/*
 * Kth Largest Element in an Array

	Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
	
	Example 1:
	
	Input: [3,2,1,5,6,4] and k = 2
	Output: 5
	Example 2:
	
	Input: [3,2,3,1,2,4,5,5,6] and k = 4
	Output: 4
	Note:
	You may assume k is always valid, 1 ≤ k ≤ array's length.
	
	Solution :(3 ways)
	 https://www.programcreek.com/2014/05/leetcode-kth-largest-element-in-an-array-java/
 * */
public class kthLargestElementInArray {
	
	
	//todo:  quicksort + heapify methods
	
	/*Method 1 - find max in a subarray starting from 0-kth index 
	 * time : O( n * k)  space : O (1)
	 * */
	  public static int findKthLargest(int[] nums, int k) {
	        int i = 0;
	        while ( i < k){
	            findMaxAt(nums, i);
	            i++;
	        }
	        return nums[k-1];
	    }
	    
	    public static void findMaxAt(int[] nums, int index){
	        for(int i = index + 1 ; i < nums.length; i++){
	            if(nums[i] > nums[index]){
	                swap(nums, i , index);
	            }
	        }
	    }
	    
	    public static void swap(int[] nums, int n, int m){
	        int temp = nums[n];
	        nums[n] = nums[m];
	        nums[m] = temp;
	    }

	public static void main(String[] args) {
		int[] array01 = new int[]{3,2,1,5,6,4};
		int[] array02 = new int[]{3,2,3,1,2,4,5,5,6};
		System.out.println("array01 = new int[]{3,2,1,5,6,4} --> findKthLargest(array01 , 2) :  "  + findKthLargest(array01 , 2));
		System.out.println("array02 = new int[]{3,2,3,1,2,4,5,5,6} --> findKthLargest(array02 , 4) :  "  + findKthLargest(array02 , 4));

	}

}
