package Sorting;

import java.util.Arrays;

/*  https://www.geeksforgeeks.org/merge-sort/
 * 
 * Like QuickSort, Merge Sort is a Divide and Conquer algorithm. 
 *  MergeSort(arr[], l,  r)
	If r > l
     1. Find the middle point to divide the array into two halves:  
             middle m = (l+r)/2
     2. Call mergeSort for first half:   
             Call mergeSort(arr, l, m)
     3. Call mergeSort for second half:
             Call mergeSort(arr, m+1, r)
     4. Merge the two halves sorted in step 2 and 3:
             Call merge(arr, l, m, r)
             
             
     Time Complexity: O(nlogn)        
     Auxiliary Space: O(n)
     Sorting In Place: No in a typical implementation
     Stable: Yes

     Applications of Merge Sort
     Merge Sort is useful for sorting "linked lists" in O(nLogn) time.
     
     the case is different mainly due to the difference in memory allocation of arrays and linked lists. 
     Unlike arrays, linked list nodes may not be adjacent in memory. 
     Unlike an array, in the linked list, we can insert items in the middle in O(1) extra space and O(1) time.
     Therefore merge operation of merge sort can be implemented without extra space for linked lists.


 * 
 * */
public class MergeSort {

	public static void mergeSort(int[] nums){
		if(nums.length <=1){
			return;
		}
	    mergeSortRecursive(nums, 0 , nums.length-1);
	}
	
	public static void mergeSortRecursive(int[] nums, int l, int h){
		if(h - l <= 0){
			return;
		}
		int mid = (h + l)/2;
		mergeSortRecursive(nums, l, mid);
		mergeSortRecursive(nums, mid + 1, h);
		merge(nums, l, mid, h);
	}
	
	public static void merge(int[] nums, int l, int mid, int h){
		if(l > mid && mid + 1 > h){
			return;//nothing to merge
		}
		// creating auxiliary space for assumed-sorted arrays
		int[] left = new int[mid - l + 1];
		int[] right = new int[h - mid];
		for(int i = 0 ; i < left.length; i++){
			left[i] = nums[l + i];
		}
		for(int j = 0 ; j < right.length; j++){
			right[j] = nums[mid + 1 + j];
		}
		//merge sorted  left and right arrays
		int i = 0;
		int j = 0;
		int n = l;
		while(i < left.length && j < right.length ){
			if(left[i] < right[j]){
				nums[n] = left[i];
				i++;
			}else{
				nums[n] = right[j];
				j++;
			}
			n++;
		}
		//looping through the only array remaining
		while(i < left.length){
			nums[n] = left[i];
			i++;
			n++;
		}
		while(j < right.length ){
			nums[n] = right[j];
			j++;
			n++;
		}
	}
	
	public static void main(String[] args) {
		int[] array0 = new int[] {10, 7, 8,9, 1, 5};
		mergeSort(array0);
		System.out.println("mergeSort({10, 7, 8,9, 1, 5}) : " +  Arrays.toString(array0));
	}

}
