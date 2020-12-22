package Sorting;
/*   https://www.geeksforgeeks.org/quick-sort/
 * 
 *   QuickSort
 *   Like Merge Sort, QuickSort is a Divide and Conquer algorithm. It picks an element as pivot and partitions the given array around the picked pivot. There are many different versions of quickSort that pick pivot in different ways.
		
		Always pick first element as pivot.
		Always pick last element as pivot (implemented below) ***
		Pick a random element as pivot.
		Pick median as pivot.
		
		The key process in quickSort is partition(). Target of partitions is,
		 given an array and an element x of array as pivot, put x at its correct position in sorted array
		  and put all smaller elements (smaller than x) before x, and put all greater elements (greater than x) after x. 
		  All this should be done in linear time.
		  
    Analysis of QuickSort
		Time taken by QuickSort in general can be written as following.
		
		 T(n) = T(k) + T(n-k-1) + \theta(n)
		 Worst Case O(n^2): The worst case occurs when the partition process always picks greatest or smallest element as pivot 
		 Best Case O(n logn): The best case occurs when the partition process always picks the middle element as pivot. 
		 
		Is QuickSort stable? no
			The default implementation is not stable. 
		Is QuickSort In-place? yes
		 it qualifies as an in-place sorting algorithm as it uses extra space only for storing recursive function calls but not for manipulating the input.
		 
		 Why Quick Sort is preferred over MergeSort for sorting Arrays?
		 Why MergeSort is preferred over QuickSort for Linked Lists?
 * 
 * */
import java.util.Arrays;

public class QuickSort {
	
	public static void QuickSort(int[] nums){
		QuickSortRecursive(nums, 0, nums.length -1);
	}
	
	public static void QuickSortRecursive(int[] nums, int l, int h){
		if(h-l <= 0) {
			return;
		}
		int pivot = nums[h];
		int pivotIndex = partition(nums, l, h, pivot);
		QuickSortRecursive(nums, l, pivotIndex - 1);
		QuickSortRecursive(nums, pivotIndex + 1, h);
	}
	
	public static int partition(int[] nums, int l, int h, int pivot){
		int i = l;
		int j = h;
		while(i < j){
			while(i < h && nums[i] < pivot ){
				i++;
			}
			while(j > l && nums[j] >= pivot){
				j--;
			}
			if(i <j){
				swap(nums, i, j);
			}
		}
		swap(nums, h, i);//i-th index is the proper position of the pivot value --> pivot index
		return i ;//pivot index
	}
	
	public static void swap(int[] nums, int a, int b){
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	public static void main(String[] args) {
		int[] array01 = new int[] {10, 80, 30, 90, 40, 50, 70};
		QuickSort(array01);
		System.out.println("QuickSort({10, 80, 30, 90, 40, 50, 70}) : " +  Arrays.toString(array01));
		
		int[] array02 = new int[] {5, 10, 3, 6, 7, 4, 15, 2, 9};
		QuickSort(array02);
		System.out.println("QuickSort({5, 10, 3, 6, 7, 4, 15, 2, 9}) : " +  Arrays.toString(array02));
		
		int[] array03 = new int[] {1, 10 , 7, 9, 15, 13, 22, 40};
		QuickSort(array03);
		System.out.println("QuickSort({1, 10 , 7, 9, 15, 13, 22, 40}) : " +  Arrays.toString(array03));
		
		int[] array04 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		QuickSort(array04);
		System.out.println("QuickSort({1, 2, 3, 4, 5, 6, 7, 8, 9}) : " +  Arrays.toString(array04));
		
		int[] array05 = new int[] {9, 8, 7, 6, 5, 4,3, 2, 1, -5};
		QuickSort(array05);
		System.out.println("QuickSort({9, 8, 7, 6, 5, 4,3, 2, 1, -5}) : " +  Arrays.toString(array05));
		
	}

}
