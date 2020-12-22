package freecodecamp;

import Udemy.SinglyLinkedList;

/* you need to use 3 temporary variables holing previous->current->next  nodes, 
 * so you can keep the linkage (3rd node) before cutting the linkage and changing its direction
 * https://www.geeksforgeeks.org/reverse-a-linked-list/
 * */
public class ReverseALinkedList extends SinglyLinkedList {
	ReverseALinkedList() {
		super();
	}
	
	public void badReverseInfiniteLoop(){
		if(head == null || head.next == null){
			return;
		}
		Node node = head;
		Node oldHead = head;
		int counter = 0;
		while(node!= null && node.next != null){
			Node current = node;
			Node previous = node.next;
			previous.next = current;
			node = node.next;
			System.out.println("infinite lopp! node.value" + node.value);
			//just for demonstration of creating an infinite loop
			counter++;
			if(counter ==10){
				return;
			}
		}
		head = node;
		oldHead.next = null;//new tail
	}
	
	/* using previous, current, next might become confusing so I used :
	 * node, nextNode, thirdNode
	 * */
	private void  reverse(){
		if (head == null || head.next == null){
			return; //empty or single-element lists do not need reversion
		}
		Node node = head;
		Node nextNode = head.next;
		head.next = null;//becomes new tail
		while(node!= null && nextNode!=null){
			Node thirdNode = nextNode.next;//retrieve next node before loosing the linkage
			nextNode.next = node;//reversing the linkage
			node = nextNode;//prepping the nodes for next iterations
			nextNode = thirdNode;
		}
		head = node;//by now, we have reach the old tail (new head)
	}

	public static void main(String[] args) {
		ReverseALinkedList singleLinkedList = new ReverseALinkedList();
		singleLinkedList.display();
		singleLinkedList.reverse();
		singleLinkedList.display();
		System.out.println();
		singleLinkedList.insertLast(1);
		singleLinkedList.display();
		singleLinkedList.reverse();
		singleLinkedList.display();
		System.out.println();
		singleLinkedList.insertLast(2);
		singleLinkedList.display();
		singleLinkedList.reverse();
		singleLinkedList.display();
		System.out.println();
		singleLinkedList.insertLast(3);
		singleLinkedList.insertLast(4);
		singleLinkedList.insertLast(10);
		singleLinkedList.insertLast(100);
		singleLinkedList.display();
		singleLinkedList.reverse();
		singleLinkedList.display();
		System.out.println();
		System.out.println();
		
		System.out.println("singleLinkedList.badReverseInfiniteLoop()");
		System.out.println("demonstration of creating an infinite loop: Node1->Node2 & Node2->Node1");
		singleLinkedList.badReverseInfiniteLoop();
	}

}
