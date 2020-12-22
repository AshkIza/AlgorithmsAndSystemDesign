package leetcode.Arrays;
/*  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

	(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
	
	Find the minimum element.
	
	You may assume no duplicate exists in the array.
	
	Example 1:
	
	Input: [3,4,5,1,2] 
	Output: 1
	Example 2:
	
	Input: [4,5,6,7,0,1,2]
	Output: 0
	
	solution: optimizaed solution : binary search --> O(log n)
	https://www.programcreek.com/2014/02/leetcode-find-minimum-in-rotated-sorted-array/
	If we pick the middle element, we can compare the middle element with the leftmost (or rightmost) element.
	 If the middle element is less than leftmost, the left half should be selected;
	  if the middle element is greater than the leftmost (or rightmost), the right half should be selected. 
	  Using recursion or iteration, this problem can be solved in time log(n).
 * 
 * */
public class FindMinimumInRotatedSortedArray {
	
    public int findMin(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        return findMin(nums, 0, nums.length-1);
    }
    
    private int findMin(int[] nums, int a, int b){
        if( b-a == 1){//small problem
            return Math.min(nums[a], nums[b]);
        }
        if(nums[a] < nums[b]){//first check if sorted subarray is rotated)
            return nums[a];
        }
        //sorted subArray is rotated
        int mid = (a+b)/2;
        if(nums[mid]<nums[a]){
            return findMin(nums, a, mid);
        }else{
            return findMin(nums, mid, b);
        }
    }

	public static void main(String[] args) {
		FindMinimumInRotatedSortedArray findMinimumInRotatedSortedArray = new FindMinimumInRotatedSortedArray();
		System.out.println("test against all applicable test cases");
		System.out.println(" array of size 1, findMin(new int[]{3}) : " + findMinimumInRotatedSortedArray.findMin(new int[]{3}));
		System.out.println(" array of size 2, findMin(new int[]{5,3}): " + findMinimumInRotatedSortedArray.findMin(new int[]{5,3}));
		System.out.println(" array NOT rotated, findMin(new int[]{1,2,3}): " + findMinimumInRotatedSortedArray.findMin(new int[]{1,2,3}));
		System.out.println(" array rotated min at the end, findMin(new int[]{1,2,3,-1}): " + findMinimumInRotatedSortedArray.findMin(new int[]{1,2,3,-1}));
		System.out.println(" array rotated even size, findMin(new int[]{4,5,7,0,1,2}): " + findMinimumInRotatedSortedArray.findMin(new int[]{4,5,7,0,1,2}));
		System.out.println(" array rotated odd size, findMin(new int[]{3,4,5,1,2}): " + findMinimumInRotatedSortedArray.findMin(new int[]{3,4,5,1,2}));
		System.out.println(" array rotated odd size, findMin(new int[]{4,5,6,7,0,1,2}): " + findMinimumInRotatedSortedArray.findMin(new int[]{4,5,6,7,0,1,2}));



	}

}
