package Heap;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


/*https://www.techiedelight.com/merge-m-sorted-lists-variable-length/
 * 
 * NOTE: KEY concept to use heap in algorithms: 
		 we can use Heap(size k) when we know the minimum value is in  the next k elements, 
		 so we heapify next k elements and get its root node
 * 
 * */
public class MergeMSortedLists {
	
	public static void printSorted(List<List<Integer>> list){
		String mergedList = "";
		int M = list.size();
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		boolean finished = false;
		int columnCount = 0;
		while(!finished){
			finished = true;
			for(int i = 0; i < M; i++){
				if(columnCount < list.get(i).size()){
					finished = false;
					heap.add(list.get(i).get(columnCount));
				}
			}
			if(columnCount == 0){
				mergedList = String.valueOf(heap.poll());

			}else{
				mergedList += ", " + String.valueOf(heap.poll());
			}
			columnCount++;
		}
		
		while(!heap.isEmpty()){
			mergedList += ", " + String.valueOf(heap.poll());
		}
		System.out.println(mergedList);
		
	}
	public static void main(String[] args) {
		List<List<Integer>> list = Arrays.asList(
				Arrays.asList(10, 20, 30, 40),
				Arrays.asList(15, 25, 35),
				Arrays.asList(27, 29, 37, 48, 93),
				Arrays.asList(32, 33)
			);

	printSorted(list);
	
	
	List<List<Integer>> list_02 = Arrays.asList(
			Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9),
			Arrays.asList(10, 20, 30, 40, 50),
			Arrays.asList(100, 200, 300),
			Arrays.asList(1000, 2000, 3000, 4000, 5000)
		);
	printSorted(list_02);

	
	System.out.println("\nNOTE: KEY concept to use heap in algorithms: \n "
			+ "we can use Heap(size k) when we know the minimum value is in  the next k elements, \n "
			+ "so we heapify next k elements and get its root node\n");
	}

}
