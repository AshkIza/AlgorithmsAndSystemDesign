package leetcode.LinkedList;

import leetcode.LinkedList.DeleteNodeNoAccessToHead.ListNode;

/*Odd Even Linked List
	Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
	
	You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
	
	Example 1:
	
	Input: 1->2->3->4->5->NULL
	Output: 1->3->5->2->4->NULL
	Example 2:
	
	Input: 2->1->3->5->6->4->7->NULL
	Output: 2->3->6->7->1->5->4->NULL
	Note:
	
	The relative order inside both the even and odd groups should remain as it was in the input.
	The first node is considered odd, the second node even and so on ...
	
	SOLUTION: LEETCODE HAS ALL THE SOLUTIONS
 * */
public class OddEvenLinkedList {
	
	public static ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode evenHead = head.next;
        ListNode oddNode = head;
        ListNode evenNode = evenHead;
        while(oddNode.next != null && oddNode.next.next != null){
            oddNode.next = oddNode.next.next;
            oddNode = oddNode.next;
            evenNode.next = oddNode.next;
            evenNode = evenNode.next;
        }
        oddNode.next = evenHead;//attach tail of odds to head of evens
        return head;
    }

	 static void  printNodes(ListNode head){
		 ListNode n = head;
		 String nodes = "";
		 while(n != null){
			 nodes += n.val + ",";
			 n = n.next;
		 }
		 System.out.println(nodes);
	 }
	 
	public static void main(String[] args) {
		System.out.println(" NOTE: \n  make sure to test/debug your code agains ALL use/corner cases you can think of");
		System.out.println("    ALL TEST CASES SHOULD PASS YOUR LOGIC");

		
		ListNode l0 = new ListNode(1);
		System.out.print("\nprintNodes(l0) : ");
		printNodes(l0);
		System.out.print("printNodes(oddEvenList(l0)) : ");
		printNodes(oddEvenList(l0));

		

		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		System.out.print("\nprintNodes(l1) : ");
		printNodes(l1);
		System.out.print("printNodes(oddEvenList(l1)) : ");
		printNodes(oddEvenList(l1));
		
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(2);
		l2.next.next = new ListNode(3);
		System.out.print("\nprintNodes(l2) : ");
		printNodes(l2);
		System.out.print("printNodes(oddEvenList(l2)) : ");
		printNodes(oddEvenList(l2));
		
		ListNode l3 = new ListNode(1);
		l3.next = new ListNode(2);
		l3.next.next = new ListNode(3);
		l3.next.next.next = new ListNode(4);
		System.out.print("\nprintNodes(l3) : ");
		printNodes(l3);
		System.out.print("printNodes(oddEvenList(l3)) : ");
		printNodes(oddEvenList(l3));
		
		ListNode l4 = new ListNode(1);
		l4.next = new ListNode(2);
		l4.next.next = new ListNode(3);
		l4.next.next.next = new ListNode(4);
		l4.next.next.next.next = new ListNode(5);
		System.out.print("\nprintNodes(l4) : ");
		printNodes(l4);
		System.out.print("printNodes(oddEvenList(l4)) : ");
		printNodes(oddEvenList(l4));

		
		
	}

}
