package Udemy;
/* Return Nth node from the end in a linked list
 * a good strategy dealing with linked-list problems:
 *  using 2 pointers:  slow/fast   ahead/behind
 *  solves many linked-list questions such as: find midpoint of linked-list, detect loop in a linked-list
 *  
 *  fast/slow pointer: different speed
 *  fast pointer increments by 2   fast.next.next    
 *  slow pointer increments by 1   slow.next
 *  
 *  ahead/behind: same speed but ahead is leading by N element
 *  
 * different methods including the best solution (using two pointers - ahead and behind pointers))
 * https://www.geeksforgeeks.org/nth-node-from-the-end-of-a-linked-list/
 * */
public class ReturnNthNodeFromEnd extends SinglyLinkedList {
	ReturnNthNodeFromEnd() {
		super();
	}
	
	Node fromLast(int N){
		Node ahead = head;
		Node behind = head;
		//make sure ahead is always N elements ahead of behind
		while (ahead != null && N > 0){
			ahead = ahead.next;
			N--;
		}
		if (N != 0){//N > list.size()
			return null;
		}
		while(ahead.next != null){
			ahead = ahead.next;
			behind = behind.next;
		}
		return behind;
	}

	public static void main(String[] args) {
		ReturnNthNodeFromEnd singleLinkedList = new ReturnNthNodeFromEnd();
		singleLinkedList.insertLast(1);
		singleLinkedList.insertLast(2);
		singleLinkedList.insertLast(3);
		singleLinkedList.insertLast(4);
		singleLinkedList.insertLast(5);
		singleLinkedList.insertLast(6);
		singleLinkedList.insertLast(7);
		singleLinkedList.insertLast(8);
		singleLinkedList.display();
		System.out.println("singleLinkedList.fromLast(0) : " + singleLinkedList.fromLast(0).value);
		System.out.println("singleLinkedList.fromLast(1) : " + singleLinkedList.fromLast(1).value);
		System.out.println("singleLinkedList.fromLast(2) : " + singleLinkedList.fromLast(2).value);
		System.out.println("singleLinkedList.fromLast(3) : " + singleLinkedList.fromLast(3).value);
		System.out.println("singleLinkedList.fromLast(7) : " + singleLinkedList.fromLast(7).value);
		if(singleLinkedList.fromLast(10) == null){
			System.out.println("singleLinkedList.fromLast(10) : null" );
		}
	}

}
