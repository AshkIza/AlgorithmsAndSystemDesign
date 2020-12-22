package Heap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/*https://www.techiedelight.com/sort-k-sorted-array/
 * 
 * NOTE: we can use Heap(of size k) when we know the minimum value is in  the next k elements, 
  			so we heapify next k elements and get its root node
 * */
public class SortKSortedArray {
	
	/* time complexity : log(k) create heap + n * log(k) removing from heap n-times -->  O(n*log(k))
	 * extra space: O(k)  heap of size k+1
	 * 
	 * 
	 * */
	static void sortKSortedArray(List<Integer> list, int k){
		PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1);
		for(int i = 0 ; i <= k; i++){
			heap.add(list.get(i));
		}
		
		for(int j = 0; j < list.size(); j++){
			Integer min = heap.poll();
			list.set(j, min); //*Replaces the element at the specified position in this list*
			if(j + k + 1 < list.size()){
				heap.add(list.get(j + k + 1));
			}
		}
	}
	
	static void printQueue(PriorityQueue<Integer> minHeap){
		Iterator<Integer> it = minHeap.iterator();
		String s = "["; 
		int count = 0;
		while(it.hasNext()){
			if(count == 0){
				s = String.valueOf(it.next());
			}else{
				s = s + ", " + String.valueOf(it.next());
			}
			count++;
		}
		s = s + "]";
		System.out.println(s);

	}

	public static void main(String[] args) {
		System.out.println(" we can use Heap(of size k) when we know the minimum value is in  the next k elements, \n  "
				+ "so we heapify next k elements and get its root node\n");

		List<Integer> l = Arrays.asList(1,4,5,2,3,7,8,6,10,9);
		System.out.println(" k-sort array          : " + l);
		sortKSortedArray(l, 2);
		System.out.println(" k-sort array - sorted : " + l + "\n");
		
		
		System.out.println(" PriorityQueue<Integer> heap = new PriorityQueue<>(unsorted_list) ");
		List<Integer> unsorted_list = Arrays.asList(20, 1, 4, 6, 65, 2, 45, 200, 62);
		PriorityQueue<Integer> heap = new PriorityQueue<>(unsorted_list);
		System.out.println(" unsorted_list : " + unsorted_list);
		System.out.print(" heap representation (partially-ordered) : ");
		printQueue(heap);
	}

}
