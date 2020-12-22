package Udemy;

/*  detect loop in linked-list
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
 * different methods including slow-fast pointers solution (best solutions)
 *   slow-fast pointers solution : Floyd’s Cycle-Finding Algorithm 
 * https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/
 * */
public class CircularLoopInLinkedList extends SinglyLinkedList {
	CircularLoopInLinkedList() {
		super();
	}
	
	//todo
	/*boolean isCircularUsingHash(){
		
	}*/
	
	//most optimum case : floyd's cycle-finding algorithm
	boolean isCircular(){
		if (head == null || head.next == null){
			return false;
		}
		Node fast = head;
		Node slow = head;
		while(fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
			if(fast == slow){
				return true;
			}
		}
		return false;
	}
	

	public static void main(String[] args) {
		CircularLoopInLinkedList singleLinkedList = new CircularLoopInLinkedList();
		singleLinkedList.insertLast(1);
		singleLinkedList.insertLast(2);
		singleLinkedList.insertLast(3);
		singleLinkedList.insertLast(4);
		singleLinkedList.insertLast(5);
		singleLinkedList.insertLast(6);
		singleLinkedList.insertLast(7);
		singleLinkedList.insertLast(8);
		System.out.println("singleLinkedList.isCircular() : " + singleLinkedList.isCircular());
		
		singleLinkedList.getAt(4).next = singleLinkedList.getAt(2);	
		System.out.println("singleLinkedList.isCircular() : " + singleLinkedList.isCircular());
	}

}
