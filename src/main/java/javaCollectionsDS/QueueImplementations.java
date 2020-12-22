package javaCollectionsDS;

import java.util.Queue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/* BlockingQueue implementations: java.uti.concurrent
 *  priorityBlockingQueue, ArrayBlockingQueue, LinkedBlockingQueue
 *  
 *  priorityQueue:  compareTo (return -1 higher priority)
 *  https://www.callicoder.com/java-priority-queue/
 * */
public class QueueImplementations {//LinkedList and PriorityQueue
	
	private static class Name implements Comparable<Name>{
		String firstName;
		String familyName;
		
		Name(String f, String l){
			firstName = f;
			familyName = l;
		}
		
		@Override
		public int compareTo(Name that) {
		  if(this.familyName.contains("smith") && !that.familyName.contains("smith")){
				return -1;//this higher priority than that (-1)
			}
			if(!this.familyName.contains("smith") && that.familyName.contains("smith")){
				return 1;//this lower priority than that (1)
			}
			return 0;
		}
		public String toString(){
			return firstName + " " + familyName;
		}
	}
	
	
	
	public static void main(String[] args) {
		//LinkedList
		System.out.println("Queue interface methods (add remove peek / offer poll element: ");

		Queue<String> linkedQueue = new LinkedList<>();
		linkedQueue.add("January");//add or offer
		linkedQueue.offer("February");
		linkedQueue.add("March");
		System.out.println("Queue : " + linkedQueue);
		
		System.out.println("head of Queue (element): " + linkedQueue.element());//remove or poll
		System.out.println("Queue : " + linkedQueue);
		System.out.println("removing (remove): " + linkedQueue.remove());//peek or element
		System.out.println("Queue : " + linkedQueue);

		System.out.println("head of Queue (peek): " + linkedQueue.peek());
		System.out.println("Queue : " + linkedQueue);
		System.out.println("removing (poll): " + linkedQueue.poll());
		System.out.println("Queue : " + linkedQueue);
		
		linkedQueue.remove();
		System.out.println("Queue : " + linkedQueue);
		System.out.println("removing from an empty Queue using poll : " + linkedQueue.poll());
		try{
			System.out.println("removing from an empty Queue using remove : " + linkedQueue.remove());
		}catch(NoSuchElementException e){
			System.out.println("removing from an empty Queue using remove throws NoSuchElementException");
		}
		System.out.println("Queue : " + linkedQueue);
		System.out.println("examing  an empty Queue using peek : " + linkedQueue.peek());
		try{
			System.out.println("examing  an empty Queue using element : " + linkedQueue.element());
		}catch(NoSuchElementException e){
			System.out.println("examing  an empty Queue using element throws NoSuchElementException");
		}
		System.out.println();
		//Priority Queue
		//**** in priority queue comparator:  if s1 < s2 (-1) --> s1 higher priority than s2****
		 Queue<String> priorityQueueWithComparator = new PriorityQueue<>( 5, new Comparator<String>(){
			public int compare(String s1, String s2) {
				//return s1.length() - s2.length();
			    if(s1.contains("smith") && !s2.contains("smith")){
					return -1;//s1 higher priority than s2 (-1)
				}
				if(!s1.contains("smith") && s2.contains("smith")){
					return 1;//s1 lower priority than s2 (1)
				}
				return 0;
			}
		 });
		 
		 priorityQueueWithComparator.offer("Yin Shi");
		 priorityQueueWithComparator.offer("Abraham lincoln");
		 priorityQueueWithComparator.offer("Vladimir putin");
		 priorityQueueWithComparator.offer("John smith");
		 priorityQueueWithComparator.offer("Jane smith");
		 priorityQueueWithComparator.offer("Ali karimi");
		 
		System.out.println("priorityQueue (max-heap) array: " + priorityQueueWithComparator);
		System.out.println("smith family have highest priority, then based on order of enqueue(insertion)");
        System.out.println("head of priorityQueue : " + priorityQueueWithComparator.peek());
		System.out.println("first element removed, priorityQueue : " + priorityQueueWithComparator.remove());
		System.out.println("second element removed, priorityQueue  : " + priorityQueueWithComparator.poll());
		System.out.println("third element of priorityQueue : " + priorityQueueWithComparator.element());
		priorityQueueWithComparator.poll();
		System.out.println("forth element of priorityQueue removed  : " + priorityQueueWithComparator.poll());
		System.out.println("fifth element of priorityQueue  removed : " + priorityQueueWithComparator.remove());
		System.out.println("sixth element of priorityQueue removed : " + priorityQueueWithComparator.remove());

		System.out.println();
		Comparator pickLargerString = new Comparator<String>(){
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		 };
		 
		 String first = "abcd";
		 String second = "ab";
		System.out.println("first : " + first + ", second : " + second);


		 if ( pickLargerString.compare(first, second) < 0) {
				System.out.println("pickLargerString : first < second");
		 }else{
				System.out.println("pickLargerString : first > second");
		 }
		 
		 System.out.println();
		 priorityQueueWithComparator = new PriorityQueue<>( 5, pickLargerString);
		 priorityQueueWithComparator.add("ab");
		 priorityQueueWithComparator.add("abcd");
		 priorityQueueWithComparator.add("ab");
		 priorityQueueWithComparator.add("abcdefgh");
		 priorityQueueWithComparator.add("abc");
		 priorityQueueWithComparator.add("a"); 
		System.out.println("priorityQueue (max-heap) array: " + priorityQueueWithComparator);
		System.out.println("head of priorityQueue removed: " + priorityQueueWithComparator.poll());
		System.out.println("next element of priorityQueue removed: " + priorityQueueWithComparator.poll());
		System.out.println("next element of priorityQueue removed : " + priorityQueueWithComparator.poll());
		System.out.println("next element of priorityQueue removed : " + priorityQueueWithComparator.poll());
		System.out.println("next element of priorityQueue removed : " + priorityQueueWithComparator.poll());
		System.out.println("next element of priorityQueue removed : " + priorityQueueWithComparator.poll());
		System.out.println("next element of priorityQueue removed : " + priorityQueueWithComparator.poll() + " (poll() return null, remove() throws exception)");

		System.out.println();
		System.out.println("priorityQueue with Comparable Object ");
		Queue<Name> priorityQueueWithComparableObject = new PriorityQueue<>();
		priorityQueueWithComparableObject.offer(new Name("Yin", "Shi"));
		priorityQueueWithComparableObject.offer(new Name("Abraham", "lincoln"));
		priorityQueueWithComparableObject.offer(new Name("Vladimi",  "putin"));
		priorityQueueWithComparableObject.offer(new Name("John", "smith"));
		priorityQueueWithComparableObject.offer(new Name("Jane", "smith"));
		priorityQueueWithComparableObject.offer(new Name("Ali",  "karimi"));
		
		System.out.println("smith family have highest priority, then based on order of enqueue(insertion)");
        System.out.println("head of priorityQueue : " + priorityQueueWithComparableObject.peek().toString());
		System.out.println("first element removed, priorityQueue : " + priorityQueueWithComparableObject.remove().toString());
		System.out.println("second element removed, priorityQueue  : " + priorityQueueWithComparableObject.poll().toString());
		System.out.println("third element of priorityQueue : " + priorityQueueWithComparableObject.element().toString());
		priorityQueueWithComparableObject.poll();
		System.out.println("forth element of priorityQueue removed  : " + priorityQueueWithComparableObject.poll().toString());
		System.out.println("fifth element of priorityQueue  removed : " + priorityQueueWithComparableObject.remove().toString());
		System.out.println("sixth element of priorityQueue removed : " + priorityQueueWithComparableObject.remove().toString());
	
	}
	

}
