package Heap;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

/*  2.6.3 Heap - Heap Sort - Heapify - Priority Queues
Topics Covered 
1. Array Representation of Binary Tree
2. Complete Binary Tree
3. Heap
4. Heap Sort
5. Heapify
6. Priority Queue
https://www.youtube.com/watch?v=HqPJF2L5h9U&t=53s

1) buildHeap : https://www.geeksforgeeks.org/building-heap-from-array/

2) MaxHeap implementations and its operations: Java implementation of heap (PriorityQueue)
https://www.techiedelight.com/min-heap-max-heap-implementation-in-java/
https://www.geeksforgeeks.org/max-heap-in-java/
https://stackoverflow.com/questions/11003155/change-priorityqueue-to-max-priorityqueue

3) check if an arrray is minHeap
https://www.techiedelight.com/check-given-array-represents-min-heap-not/

 * 
 * */
public class Heap {
	
	
	/* Building Heap from Array
	 * https://www.geeksforgeeks.org/building-heap-from-array/
	 * 
	 * by heapifying ALL NON-leaf nodes (bottom-up approach) : time complexity : O(n)
	 * using top-bottom approach (and validating each node to be max heap) : time complexity O(n log n)
	 * */
    static void buildHeap(int arr[], int n) {
    	int parentOfLastleaf = (n-1)/2;//in 0-indexed array parent node is : ceil( (n-1)/2 )
    	for( int i = parentOfLastleaf ; i >=0; i--){
    		heapifyConcise(arr, i, n);//heapify(arr, i, n);
    	}
    }
    
    /*heapifying an array index means validating the parent/children to be a maxHeap
     * (the sub-tree starting from parent index should be a maxHeap) 
     * */
    static void heapify(int[] array, int index, int heapSize){
    	if( (2* index + 1) >= heapSize){// leaf node
    		return;
    	}
    	int left = array[2*index + 1];// in 0-indexed array left node is : 2 i + 1
    	if((2*index+2) >= heapSize){//only having left child
    		if(left > array[index]){
    			swap(array, index, 2 * index + 1);
    		}
    		return;
    	}
    	/*we have left and right children,
    	 *  need to swap if needed and heapify 'down'
    	 *   the swapped children recursively
    	*/
    	int right = array[2*index + 2]; // in 0-indexed array right node is : 2 i + 2
    	int max = Math.max(left, right);
    	if(max > array[index]){
    		if(left == max){
    			swap(array, index , 2 * index + 1);
    			heapify(array, 2 * index +1, heapSize);
    			
    		}else{
    			swap(array, index , 2 * index + 2);
    			heapify(array, 2 * index +2, heapSize);
    		}
    	}
    }
    
    private static void swap(int[] array, int i, int j){
    	int temp = array[i];
    	array[i] = array[j];
    	array[j] = temp;
    }
    
    /* to shorten the logic, 
     * instead of maintaining both max value and its index,
     * we maintaining maxIndex (the index/node with the max value).
     * */
    static void heapifyConcise(int[] array, int index, int heapSize){
    	int leftIndex = 2 * index + 1;
    	int rightIndex = 2 * index + 2;
    	int maxIndex = index;//default it to parent, then if having left/right,check their value and update this index
    	
    	if(leftIndex < heapSize && array[leftIndex] > array[maxIndex]){
    		maxIndex = leftIndex; //only update the maxIndex with left child when left child NOT NULL and its value is greater than parent
    	}
    	
    	if(rightIndex < heapSize && array[rightIndex] > array[maxIndex]){
    		maxIndex = rightIndex;//only update maxIndex to righ child, when right child is NOT NULL and its value is the maximum
    	}
    	
    	//if node is leaf or parent has max value no need to heapify and swap
    	// we only need recursive call - do swap and down heapify -  when left/right child has the max value
    	if(maxIndex != index){
    		swap(array, index, maxIndex);
    		heapifyConcise(array, maxIndex, heapSize);
    	}
    }
    
    public static void printHeap(int[] array, int heapSize){
    	String s = " Heap : [";
    	for(int i = 0; i < heapSize ; i++){
    		if(i == heapSize-1){
    			s += array[i] + "]";
    		}else{
        		s += array[i] + ", ";
    		}
    	}
    	System.out.println(s);
    }
    
    
    /* Max Heap in Java
		https://www.techiedelight.com/min-heap-max-heap-implementation-in-java/
     * 
     * Operations on Max Heap:
		1) getMax(): It returns the root element of Max Heap. Time Complexity of this operation is O(1).
		2) extractMax(): Removes the maximum element from MaxHeap. O(Log n)  
		3) insert(): Inserting a new key takes O(Log n) time. 
		
	Using Library Functions
		We use PriorityQueue class to implement Heaps in Java. 
		By default Min Heap is implemented by this class. 
		To implement Max Heap, we use Collections.reverseOrder()	
     * */
    public static class MaxHeap {
    	static int[] array;
    	static int maxSize;
    	static int heapSize;
    	
    	MaxHeap(int N){
    		maxSize = N;
    		array = new int[N];
    	}
    	
    	static int size(){
    		return heapSize;
    	}
    	
    	static void insert(int value){
    		if(heapSize >= maxSize){
    			System.out.println("\n  reached maximum heap size. can NOT insert " + value);
    			return;
    		}
    		array[heapSize] = value;
    		heapSize++;
    		heapifyUp(array, heapSize - 1, heapSize);
    	}
    	
    	// O(log n)
    	/* for heapify up, only need to check that index and its parent for swap/heapify 
    	 * (no need to check both children - since we already know that  parent is greater than the other children)*/
    	private static void heapifyUp(int[] arr, int index, int heapSize){
    		if(index == 0 ){
    			return;//root has no parent - reached to the small problem case
    		}
    		int parent = (index -1) / 2;


    		if(arr[index] > arr[parent]){
    			swap(arr, parent,  index);
    			heapifyUp(arr, parent, heapSize);
    		}
    	}
    	
    	// O(1) access to the root
    	static int getMax(){
    		if(heapSize ==0){
    			System.out.println("Empty HEAP!");
    			return Integer.MIN_VALUE;
    		}
    		return array[0];
    	}
    	
    	// deletion and heapiFy down O(log n)
    	static int extractMax(){
    		if(heapSize ==0){
    			System.out.println("Empty HEAP!");
    			return Integer.MIN_VALUE;
    		}
    		int maxValue = array[0];
    		array[0] = array[heapSize -1];
    		heapSize--;
    		heapifyDown(array, 0, heapSize);
    		return maxValue;
    	}
    	
    	// O(log n)
    	private static void heapifyDown(int[] arr, int index, int heapSize){
    		int maxIndex = index;
    		int left = 2 * index + 1;
    		int right = 2 * index + 2;
    		if(left < heapSize && arr[left] > arr[maxIndex]){
    			maxIndex = left;
    		}
    		if(right < heapSize && arr[right] > arr[maxIndex]){
    			maxIndex = right;
    		}
    		if(maxIndex != index){
    			swap(array, index, maxIndex);
    			heapifyDown(array, maxIndex, heapSize);
    		}
    	}
    	
    	static void print(){
    		printHeap(array, heapSize);
    	}
    	
    }
    
    /*  Min Heap in Java
     * https://www.geeksforgeeks.org/min-heap-in-java/
     * https://www.geeksforgeeks.org/max-heap-in-java/
     * */
    
    /*  Maximum element in min heap
     * https://www.geeksforgeeks.org/maximum-element-in-min-heap/
     * */
    
    
    /*  Convert BST to Min Heap
     * https://www.geeksforgeeks.org/convert-bst-min-heap/
     * */

	
    
    static void printPriorityQueue(PriorityQueue<?> pq){
    	String s = " Heap : [";
    	Iterator<?> it = pq.iterator();
    	while(it.hasNext()){
    		s += it.next() + ", ";
    	}
    	s += "]";
    	System.out.println(s);
    }


	public static void main(String[] args) {
    	System.out.println("1 ) build max Heap from Array --> Heapify O(n log n ) : \n   "
    			+ "we only  do swap and down heapify when left/right child has the max value \n  "
    			+ "  MaxHeap --> maxIndex (if maxIndex != parent , swap(index, maxIndex) and heapify recursively) \n  "
    			+ "  MinHeap --> minIndex (if minIndex != parent , swap(index, minIndex) and heapify recursively)");
		 // Binary Tree Representation 
        // of input array 
        // 1 
        //           /     \ 
        // 3         5 
        //      /    \     /  \ 
        // 4      6   13  10 
        //    / \    / \ 
        // 9   8  15 17 
        int arr[] = { 1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17 }; 
        int n = arr.length; 
        buildHeap(arr, n); //make an array (complete binary tree), a max heap (array representation)
        printHeap(arr, n); //print array representation of max Heap
        
        int[] arr02 = new int[] {18, 17, 16, 15, 13, 12, 11, 10, 9, 8, 7, 6, 5, 3, 2, 1};
        int heapSize = arr02.length;
        buildHeap(arr02, heapSize);
        printHeap(arr02, heapSize);
        
        System.out.println("\n2) MaxHeap class : insert and delete operations");
        //insert and delete from heap (deletion always removes the root - maximum element)
        MaxHeap maxHeap = new MaxHeap(10); 
        maxHeap.insert(5); 
        maxHeap.insert(3); 
        maxHeap.insert(17); 
        maxHeap.insert(10); 
        maxHeap.print(); 
        System.out.println("  heap size : " + maxHeap.size() + ", getMax() : " + maxHeap.getMax());
        maxHeap.insert(84); 
        maxHeap.insert(19); 
        maxHeap.insert(6); 
        maxHeap.insert(22); 
        maxHeap.insert(9); 
        maxHeap.insert(35); 
        maxHeap.insert(2);
        maxHeap.print(); 
        System.out.println("  heap size : " + maxHeap.size() + ", getMax() : " + maxHeap.getMax());

        System.out.println();
        System.out.println("The max val extracted(deleted) :  " + maxHeap.extractMax()); 
        maxHeap.print(); 
        System.out.println("  heap size : " + maxHeap.size() + ", getMax() : " + maxHeap.getMax());
        System.out.println("The max val extracted(deleted) :  " + maxHeap.extractMax()); 
        System.out.println("The max val extracted(deleted) :  " + maxHeap.extractMax()); 
        System.out.println("The max val extracted(deleted) :  " + maxHeap.extractMax()); 
        System.out.println("The max val extracted(deleted) :  " + maxHeap.extractMax()); 
        System.out.println("The max val extracted(deleted) :  " + maxHeap.extractMax()); 
        maxHeap.print(); 
        System.out.println("  heap size : " + maxHeap.size() + ", getMax() : " + maxHeap.getMax());
        
        /**
         * Priority queue represented as a balanced binary heap: the two
         * children of queue[n] are queue[2*n+1] and queue[2*(n+1)].  The
         * priority queue is ordered by comparator, or by the elements'
         * natural ordering, if comparator is null: For each node n in the
         * heap and each descendant d of n, n <= d.  The element with the
         * lowest value is in queue[0], assuming the queue is nonempty.
         */
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //if comparator is null it acts as min-heap (The element with the the lowest value is in queue[0])
        System.out.println("\nPriorityQueue<Integer> pq = new PriorityQueue<>()  - if comparator is null it acts as min-heap ");

        pq.add(5); 
        pq.add(3); 
        pq.add(17); 
        pq.add(10); 
        pq.add(84); 
        pq.add(19); 
        pq.add(6); 
        pq.add(22); 
        pq.add(9); 
        pq.add(35);
        printPriorityQueue(pq);
        System.out.println(" Heap root (min value O(1)) , pq.peek() : " + pq.peek());
        System.out.println(" delete Heap root (O(log n )) , pq.poll() : " + pq.poll());
        printPriorityQueue(pq);
        System.out.println(" pq.element() - differs from peek() in that it throws exception if queue empty: " + pq.element());
        System.out.println(" delete Heap root -  differs from peek() in that it throws exception if queue empty : " + pq.remove());
        System.out.println(" delete Heap root  - differs from peek() in that it throws exception if queue empty : " + pq.remove());
        printPriorityQueue(pq);

        PriorityQueue<Integer> pQueue =  new PriorityQueue<Integer>(10, Collections.reverseOrder());      
        System.out.println("\nPriorityQueue<Integer> pQueue =  new PriorityQueue<Integer>(10, Collections.reverseOrder())  - \n Change priorityQueue to max priorityqueue ");
        pQueue.offer(5); 
        pQueue.offer(3); 
        pQueue.offer(17); 
        pQueue.offer(10); 
        pQueue.offer(84); 
        pQueue.offer(19); 
        pQueue.offer(6); 
        pQueue.offer(22); 
        pQueue.offer(9); 
        pQueue.offer(35);
        printPriorityQueue(pQueue);

        
	}
}
