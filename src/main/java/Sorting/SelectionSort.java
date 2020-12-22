package Sorting;
import java.util.Arrays;

/*   https://www.geeksforgeeks.org/selection-sort/
 * 
 * The selection sort algorithm sorts an array by repeatedly finding the minimum element (considering ascending order) 
 * from unsorted part and putting it at the beginning. The algorithm maintains two subarrays in a given array.
	1) The subarray which is already sorted.
	2) Remaining subarray which is unsorted.
	
Time Complexity: O(n^2) as there are two nested loops.

Auxiliary Space: O(1)
The good thing about selection sort is it never makes more than O(n) swaps and can be useful when memory write is a costly operation.

In Place : Yes, it does not require extra space.

Stability : The default implementation is not stable
 * */
public class SelectionSort {
	
	
	public static void selectionSort(int[] nums){
		for(int i = 0 ; i < nums.length - 1; i++){
			int minIndex = findMinIndex(nums, i);
			swap(nums, i, minIndex);
		}
	}
	
	public static int findMinIndex(int[] nums, int start){
		int index = start;
		for(int j = start +1; j < nums.length; j++){
			if(nums[j] < nums[index]){
				index = j;
			}
		}
		return index;
	}
	
	public static void swap(int[] nums, int a , int b){
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b]= temp;
	}

	public static void main(String[] args) {
		
		int[] array01 = new int[] {10, 80, 30, 90, 40, 50, 70};
		selectionSort(array01);
		System.out.println("selectionSort({10, 80, 30, 90, 40, 50, 70}) : " +  Arrays.toString(array01));
		
		int[] array02 = new int[] {5, 10, 3, 6, 7, 4, 15, 2, 9};
		selectionSort(array02);
		System.out.println("selectionSort({5, 10, 3, 6, 7, 4, 15, 2, 9}) : " +  Arrays.toString(array02));
		
		int[] array03 = new int[] {1, 10 , 7, 9, 15, 13, 22, 40};
		selectionSort(array03);
		System.out.println("selectionSort({1, 10 , 7, 9, 15, 13, 22, 40}) : " +  Arrays.toString(array03));
		
		int[] array04 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		selectionSort(array04);
		System.out.println("selectionSort({1, 2, 3, 4, 5, 6, 7, 8, 9}) : " +  Arrays.toString(array04));
		
		int[] array05 = new int[] {9, 8, 7, 6, 5, 4,3, 2, 1, -5};
		selectionSort(array05);
		System.out.println("selectionSort({9, 8, 7, 6, 5, 4,3, 2, 1, -5}) : " +  Arrays.toString(array05));

	}

}
