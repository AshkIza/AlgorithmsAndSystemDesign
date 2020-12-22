package Udemy;

import freecodecamp.ReverseALinkedList;

/* a good strategy dealing with linked-list problems:
 *  using 2 pointers:  slow/fast   ahead/behind
 *  solves many linked-list questions such as: find midpoint of linked-list, detect loop in a linked-list
 *  
 *  fast/slow pointer: different speed
 *  fast pointer increments by 2   fast.next.next    
 *  slow pointer increments by 1   slow.next
 *  
 *  ahead/behind: same speed but ahead is leading by N element
 *  
 *  https://www.geeksforgeeks.org/write-a-c-function-to-print-the-middle-of-the-linked-list/
 * */
public class MidpointOfLinkedList extends SinglyLinkedList {
	MidpointOfLinkedList() {
		super();
	}
	
	/* this is also kind of fast and slow pointers
	 * midPoint : slow pointer (just increments on off nodes)
	 * node : fast pointer (increments on all nodes)
	 * */
	Node findMidPointMethodOne(){
		if(head == null || head.next == null){
			return head;
		}
		Node midPoint = head;
		boolean odd = true;
		Node node = head.next;
		while(node != null){
			odd = odd ? false : true;//switch every time
			if(odd){
				midPoint = midPoint.next;
			}
			node = node.next;
		}
		return midPoint;
	}
	
	Node findMidPoint_fastSlowPointers(){
		if(head == null){
			return null;
		}
		Node fast = head;
		Node slow = head;
		while (fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	

	public static void main(String[] args) {
		MidpointOfLinkedList singleLinkedList = new MidpointOfLinkedList();

		singleLinkedList.insertLast(1);
		singleLinkedList.display();
		System.out.println(" findMidPointMethodOne: " + singleLinkedList.findMidPointMethodOne().value);
		System.out.println(" findMidPoint_fastSlowPointers: " + singleLinkedList.findMidPoint_fastSlowPointers().value);
		
		singleLinkedList.insertLast(2);
		singleLinkedList.display();
		System.out.println(" findMidPointMethodOne: " + singleLinkedList.findMidPointMethodOne().value);
		System.out.println(" findMidPoint_fastSlowPointers: " + singleLinkedList.findMidPoint_fastSlowPointers().value);

		singleLinkedList.insertLast(3);
		singleLinkedList.display();
		System.out.println(" findMidPointMethodOne: " + singleLinkedList.findMidPointMethodOne().value);
		System.out.println(" findMidPoint_fastSlowPointers: " + singleLinkedList.findMidPoint_fastSlowPointers().value);

		singleLinkedList.insertLast(4);
		singleLinkedList.display();
		System.out.println(" findMidPointMethodOne: " + singleLinkedList.findMidPointMethodOne().value);
		System.out.println(" findMidPoint_fastSlowPointers: " + singleLinkedList.findMidPoint_fastSlowPointers().value);

		singleLinkedList.insertLast(5);
		singleLinkedList.insertLast(6);
		singleLinkedList.insertLast(7);
		singleLinkedList.insertLast(8);
		singleLinkedList.display();
		System.out.println(" findMidPointMethodOne: " + singleLinkedList.findMidPointMethodOne().value);
		System.out.println(" findMidPoint_fastSlowPointers: " + singleLinkedList.findMidPoint_fastSlowPointers().value);

		singleLinkedList.insertLast(9);
		singleLinkedList.display();
		System.out.println(" findMidPointMethodOne: " + singleLinkedList.findMidPointMethodOne().value);
		System.out.println(" findMidPoint_fastSlowPointers: " + singleLinkedList.findMidPoint_fastSlowPointers().value);

		singleLinkedList.insertLast(10);
		singleLinkedList.insertLast(11);
		singleLinkedList.insertLast(12);
		singleLinkedList.insertLast(13);
		singleLinkedList.insertLast(14);
		singleLinkedList.insertLast(15);
		singleLinkedList.insertLast(15);
		singleLinkedList.insertLast(16);
		singleLinkedList.insertLast(17);
		singleLinkedList.insertLast(18);
		singleLinkedList.insertLast(19);
		singleLinkedList.insertLast(20);
		singleLinkedList.display();
		System.out.println(" findMidPointMethodOne: " + singleLinkedList.findMidPointMethodOne().value);
		System.out.println(" findMidPoint_fastSlowPointers: " + singleLinkedList.findMidPoint_fastSlowPointers().value);
   	}
}
