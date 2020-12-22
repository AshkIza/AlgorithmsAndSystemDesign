package Sorting;

import java.util.Arrays;

/*  https://www.geeksforgeeks.org/insertion-sort/
 * 
 *  Insertion sort is a simple sorting algorithm that works the way we sort playing cards in our hands.
	Algorithm
	// Sort an arr[] of size n
	insertionSort(arr, n)
	Loop from i = 1 to n-1.
	……a) Pick element arr[i] and insert it into sorted sequence arr[0…i-1]
	
	
	Time Complexity: O(n*2)
	Auxiliary Space: O(1)
	Boundary Cases: Insertion sort takes maximum time to sort if elements are sorted in reverse order. 
	Sorting In Place: Yes
	Stable: Yes
	Uses: Insertion sort is used when number of elements is small.
	 		It can also be useful when input array is almost sorted, only few elements are misplaced in complete big array.
	* 
 * */
public class InsertionSort {
	
	public static void insertionSort(int[] nums){
		if (nums.length <= 1){
			return; 
		}
		for(int i = 1; i < nums.length; i++){
			insert(nums, i);
		}
	}
	
	public static void insert(int[] nums, int index){
		int val = nums[index];
		int i = index;
		while(i > 0 && nums[i-1] > val){
			nums[i] = nums[i-1];
			i--;
		}
		nums[i] = val;
	}


	public static void main(String[] args) {
		int[] array0 = new int[] {4, 3, 2, 10, 12, 1, 5, 6};
		insertionSort(array0);
		System.out.println("insertionSort({4, 3, 2, 10, 12, 1, 5, 6}) : " +  Arrays.toString(array0));
		
		int[] array01 = new int[] {10, 80, 30, 90, 40, 50, 70};
		insertionSort(array01);
		System.out.println("insertionSort({10, 80, 30, 90, 40, 50, 70}) : " +  Arrays.toString(array01));
		
		int[] array02 = new int[] {5, 10, 3, 6, 7, 4, 15, 2, 9};
		insertionSort(array02);
		System.out.println("insertionSort({5, 10, 3, 6, 7, 4, 15, 2, 9}) : " +  Arrays.toString(array02));
		
		int[] array03 = new int[] {1, 10 , 7, 9, 15, 13, 22, 40};
		insertionSort(array03);
		System.out.println("insertionSort({1, 10 , 7, 9, 15, 13, 22, 40}) : " +  Arrays.toString(array03));
		
		int[] array04 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		insertionSort(array04);
		System.out.println("insertionSort({1, 2, 3, 4, 5, 6, 7, 8, 9}) : " +  Arrays.toString(array04));
		
		int[] array05 = new int[] {9, 8, 7, 6, 5, 4,3, 2, 1, -5};
		insertionSort(array05);
		System.out.println("insertionSort({9, 8, 7, 6, 5, 4,3, 2, 1, -5}) : " +  Arrays.toString(array05));
		
		int[] array06 = new int[] {1, -5};
		insertionSort(array06);
		System.out.println("insertionSort({1, -5}) : " +  Arrays.toString(array06));
	}

}
