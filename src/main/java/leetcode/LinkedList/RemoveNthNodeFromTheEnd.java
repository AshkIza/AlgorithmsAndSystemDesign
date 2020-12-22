package leetcode.LinkedList;

import leetcode.LinkedList.DeleteNodeNoAccessToHead.ListNode;

/* Remove Nth Node From End of List
	Given a linked list, remove the n-th node from the end of list and return its head.
	
	Example:
	Given linked list: 1->2->3->4->5, and n = 2.
	After removing the second node from the end, the linked list becomes 1->2->3->5.
	Note:
	
	"Given n will always be valid."
	Follow up:
	Could you do this in one pass?
		Hint #1
		Maintain two pointers and update one with a delay of n steps.
		
		solution: https://www.programcreek.com/2014/05/leetcode-remove-nth-node-from-end-of-list-java/
 * 
 * */
public class RemoveNthNodeFromTheEnd {
	
	/*  * Definition for singly-linked list.
	 * */
	 public static class ListNode {
		 int val;
		 ListNode next;
	     ListNode(int x) { val = x; }
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
	
	
	 public static ListNode removeNthFromEnd(ListNode head, int n) {
	       ListNode node = head;
	       ListNode flag = head;
	       int count = 1;
	       while(count <= n && node.next != null){
	           node = node.next;
	           count++;
	       }
	       if(count == n){//n-th node is the head (head is the only node with no previous node)
	           return head.next;
	       }
	       if(count != n+1){//we could not find the previous node
	           System.out.println("invalid n");
	           return head;
	       }
	       while(node.next != null){
	           node = node.next;
	           flag = flag.next;
	       }
	       //till here, we found the previous node(n+1 th node from end)
	       flag.next = flag.next.next;//skipping the n-th node (removing it)
	       return head;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		printNodes(head);
		System.out.println("removeNthFromEnd(head, 3)");
		printNodes(removeNthFromEnd(head, 3));
		System.out.println("removeNthFromEnd(head, 3)");
		printNodes(removeNthFromEnd(head, 3));
		System.out.println("removeNthFromEnd(head, 3)");
		printNodes(removeNthFromEnd(head, 3));
		
		System.out.println("\nNOTE:\nFor removing a node, we always need to find its previous node, then previous.next = previous.next.next");
		System.out.println("   Except head node  head = head.next (head is the only node with no previous node)");

	}

}
