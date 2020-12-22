package leetcode.Arrays;

/*  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

	(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
	
	You are given a target value to search. If found in the array return its index, otherwise return -1.
	
	You may assume no duplicate exists in the array.
	
	Your algorithm's runtime complexity must be in the order of O(log n).
	
	Example 1:
	
	Input: nums = [4,5,6,7,0,1,2], target = 0
	Output: 4
	Example 2:
	
	Input: nums = [4,5,6,7,0,1,2], target = 3
	Output: -1
	
	solution : https://www.programcreek.com/2014/06/leetcode-search-in-rotated-sorted-array-java/
 * 
 * */
public class SearchinRotatedSortedArray {
	
	public int search(int[] nums, int target) {
        if(nums.length == 0){return -1;}
        if(nums.length == 1){
            return (nums[0] == target) ? 0 : -1;
        }
        return search(nums, target, 0, nums.length-1);
    }
    
    private int search (int[] nums, int target, int a, int b){
        if(b-a == 1){
            if(nums[a] == target || nums[b] == target){
                return (nums[a] == target) ? a : b;
            }
            return -1;
        }
        if(nums[a] < nums[b]){//already sorted subArray
            return binarysearch(nums, target, a, b);
        }   
        int mid = (a+b)/2;
        if(nums[mid]==target){
            return mid;
        }    
        if(nums[a] < nums[mid]){//lower half is sorted
            if(nums[a] <= target && nums[mid] > target){
                return binarysearch(nums, target, a, mid);
            }
            return search(nums, target, mid, b);
            
        }else if(nums[mid] < nums[b]){//upper hald is sorted
            if(nums[mid] < target && nums[b] >= target){
                return binarysearch(nums, target, mid, b);
            }
            return search(nums, target, a, mid);
        }      
        return -1;
    }
    
    private int binarysearch(int[] nums, int target, int a, int b){
        if(b==a){
            return (nums[a]==target) ? a : -1;
        }
        int mid = (a+b)/2;
        if(nums[mid] == target){
            return mid;
        }
        if(nums[mid] < target){
            return binarysearch(nums, target, mid+1,b);
        }else{
            return binarysearch(nums, target, a, mid);
        }
    }
    

	public static void main(String[] args) {
		SearchinRotatedSortedArray searchRotatedSortedArray = new SearchinRotatedSortedArray();
		System.out.println("searchRotatedSortedArray.search(new int[]{}, 0) : " + searchRotatedSortedArray.search(new int[]{}, 0));
		System.out.println("searchRotatedSortedArray.search(new int[]{0}, 0) : " + searchRotatedSortedArray.search(new int[]{0}, 0));
		System.out.println("searchRotatedSortedArray.search(new int[]{4,5,6,7,0,1,2}, 0) : " + searchRotatedSortedArray.search(new int[]{4,5,6,7,0,1,2}, 0));
		System.out.println("searchRotatedSortedArray.search(new int[]{4,5,6,7,0,1,2}, 2) : " + searchRotatedSortedArray.search(new int[]{4,5,6,7,0,1,2}, 2));
		System.out.println("searchRotatedSortedArray.search(new int[]{4,5,6,7,0,1,2}, 3)) : " + searchRotatedSortedArray.search(new int[]{4,5,6,7,0,1,2}, 3));
	}

}
