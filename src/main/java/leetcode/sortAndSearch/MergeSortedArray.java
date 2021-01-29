package leetcode.sortAndSearch;

import java.util.Arrays;

/*  https://leetcode.com/explore/interview/card/top-interview-questions-easy/96/sorting-and-searching/587/
 * 
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

The number of elements initialized in nums1 and nums2 are m and n respectively. You may assume that nums1 has a size equal to m + n such that it has enough space to hold additional elements from nums2.

 

Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
 * */
public class MergeSortedArray {
	
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;//nums1
        int l = m-1;
        int other = 0;//nums2
        while(i <= l){
            if(other < n && nums1[i] > nums2[other]){
                shift(nums1, i, l);
                nums1[i] = nums2[other];
                other++;
                l++;
                i++;
            }else{
                i++;
            }
        }
        while(other < n && l < m+n-1){
            l++;
            nums1[l] = nums2[other];
            other++;
        }
    }
    
    public static void shift(int[] nums, int start, int end){
        int index = end;
        while(index >= start){
            nums[index+1]=nums[index];
            index--;
        }
    }
	

	public static void main(String[] args) {
		System.out.println("nums1 finishes first ->");
		int[] nums1_case01 = new int[] {1,2,3,0,0,0};
		int[] nums2_case01 = new int[] {2,5,6};
		System.out.println("nums1_case01 : " + Arrays.toString(nums1_case01));
		System.out.println("nums2_case02 : " + Arrays.toString(nums2_case01));
		merge(nums1_case01, 3, nums2_case01, 3);
		System.out.println("merge(nums1_case01, 3, nums2_case02, 3) " + Arrays.toString(nums1_case01));
		
		System.out.println("\nnums2 finishes first -> ");
		int[] nums1_case02 = new int[] {2,2,4,5,20,0,0,0,0};
		int[] nums2_case02 = new int[] {1,2,9,10};
		System.out.println("nums1_case02 : " + Arrays.toString(nums1_case02));
		System.out.println("nums2_case02 : " + Arrays.toString(nums2_case02));
		merge(nums1_case02, 5, nums2_case02, 4);
		System.out.println("merge(nums1_case02, 5, nums2_case02, 4) " + Arrays.toString(nums1_case02));
		

		
	}

}
