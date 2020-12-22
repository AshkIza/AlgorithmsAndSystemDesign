package Heap;

import java.util.Arrays;

/* Java Program for Heap Sort
 * https://www.geeksforgeeks.org/java-program-for-heap-sort/
 * https://www.techiedelight.com/heap-sort-place-place-implementation-c-c/
 * */
public class HeapSort {

	
	/*  Where is Heap Sort used practically?
	 * https://www.geeksforgeeks.org/where-is-heap-sort-used-practically/
	 * Although QuickSort works better in practice,
	 * 	 the advantage of HeapSort worst case upper bound of O(nLogn).

	  MergeSort also has upper bound as O(nLogn) and works better in practice when compared to HeapSort.
	   But MergeSort requires O(n) extra space
	   
	   HeapSort is not used much in practice, but can be useful in real time 
	   (or time bound where QuickSort doesn’t fit) 
	   embedded systems where less space is available (MergeSort doesn’t fit)

	 * */
	
	public static void heapSort(int[] nums){
		int n = nums.length;
		while(n > 1){
			heapify(nums, n);
			int max = getMax(nums, n);//heapsize n --> n-1
			nums[n-1] = max;
			n--;
		}
	}
	
	private static void heapify(int[] nums, int heapSize){
		if(heapSize == 1){
			return;
		}
		int leastNonLeaf = (heapSize - 2)/2;
		while(leastNonLeaf >= 0 ){
			heapifyDown(nums, heapSize, leastNonLeaf);
			leastNonLeaf--;
		}
	}
	
	private static int getMax(int[] nums, int heapSize){
		int max = nums[0];
		nums[0] = nums[heapSize-1];
		heapifyDown(nums, heapSize-1, 0);//heapSize decremented by 1
		return max;
	}
	
	private static void heapifyDown(int[] nums, int heapSize, int index){
		int maxIndex = index;
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		if(left < heapSize && nums[left] > nums[maxIndex]){
			maxIndex = left;
		}
		if(right < heapSize && nums[right] > nums[maxIndex]){
			maxIndex = right;
		}
		if(maxIndex != index){
			swap(nums, index, maxIndex);
			heapifyDown(nums, heapSize, maxIndex);
		}
	}
	
	private static void swap(int[] nums, int i , int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		
		System.out.println(" heapifyDown (compare each node with its left/right children and swap the max) --> used in heapify(array[]) and heap.remove()");
		System.out.println(" heapifyUp (compare each node with its parent and swap if node greater) --> used in heap.add()");

		
		int[] A = { 6, 4, 7, 1, 9, -2 };
		System.out.println(" A :           " + Arrays.toString(A));
		heapSort(A);
		System.out.println(" heapSort(A) : " + Arrays.toString(A));
		
		int[] B = { 6, 40, 5, 1, 40, -2, 15, 8 };
		System.out.println(" B :           " + Arrays.toString(B));
		heapSort(B);
		System.out.println(" heapSort(B) : " + Arrays.toString(B));
		
		/*  Arrays.toString(A)
		 *  Returns a string representation of the contents of the specified array.
		 * */		
		System.out.println("Arrays.toString(A) \n Returns a string representation of the contents of the specified array");

	}

}
