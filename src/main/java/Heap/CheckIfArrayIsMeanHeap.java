package Heap;
/* 
 * https://www.techiedelight.com/check-given-array-represents-min-heap-not/
 * 
 * */
public class CheckIfArrayIsMeanHeap {
	
	/*
	 *  time complexity O(n)
	 *  extra space: O(1) (correct answer : O(log n) recursive calls consumes stack space)
	 *  */
	static boolean checkIfMinHeap(int[] array, int index){
		if(index >= array.length){//leaf is always a min heap
			return true;
		}
		int left =  2 * index + 1;
		int right = 2 * index + 2;
		if(left < array.length && array[left] < array[index]){
			return false;
		}
		if(right < array.length && array[right] < array[index]){
			return false;
		}
		return checkIfMinHeap(array, left) && checkIfMinHeap(array, right);
	}

	public static void main(String[] args) {
        System.out.println(" checkIfMinHeap(new int[] {2, 3, 4, 5, 10, 15}, 0) :  " + checkIfMinHeap(new int[] {2, 3, 4, 5, 10, 15}, 0));
        System.out.println(" checkIfMinHeap(new int[] {2, 10, 4, 5, 3, 15}, 0) :  " + checkIfMinHeap(new int[] {2, 10, 4, 5, 3, 15}, 0));
        System.out.println(" checkIfMinHeap(new int[] {10, 20, 30, 40, 15, 60, 80}, 0) :  " + checkIfMinHeap(new int[] {10, 20, 30, 40, 15, 60, 80}, 0));
        System.out.println(" checkIfMinHeap(new int[] {10, 20, 30, 40, 15, 60, 80}, 0) :  " + checkIfMinHeap(new int[] {10, 20, 30, 40, 50, 60, 80}, 0));
	}

}
