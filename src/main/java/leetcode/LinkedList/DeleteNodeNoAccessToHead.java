package leetcode.LinkedList;

/* Delete Node in a Linked List
	Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
	
	Given linked list -- head = [4,5,1,9], which looks like following:
	
	Example 1:
	
	Input: head = [4,5,1,9], node = 5
	Output: [4,1,9]
	Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
	Example 2:
	
	Input: head = [4,5,1,9], node = 1
	Output: [4,5,9]
	Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
	 
	
	Note:
	
	The linked list will have at least two elements.
	All of the nodes' values will be unique.
	The given node will not be the tail and it will always be a valid node of the linked list.
	Do not return anything from your function.
	
	Solution:  https://www.programcreek.com/2014/07/leetcode-delete-node-in-a-linked-list-java/
 * 
 * */
public class DeleteNodeNoAccessToHead {
	
	 public static class ListNode {
		 int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }
	 
	 /* since there is no Access to head, we can NOT get the previous node,
	 	    hint: node is NOT the tail (always make use of ALL the ASSUMPTIONS in the problem): they are there for a reason!
	  	--> since we can not delete this node (since previous.next not available), 
	 			we can delete next node and move its value/link to the current node
	 			(this major assumptions let us delete node without accessing its head)
	 			--> this solution does NOT work if we want to delete the 'tail node'
	 */
	 public static void deleteNode(ListNode node) {
		 // delete the next node and move its value / linkage (next) to the current node
		 // since current node is NOT tail, we always have node.next (node.next != null) : major assumption!
	        node.val = node.next.val;
	        node.next = node.next.next;
	 }
	 
	 public static void  printNodes(ListNode head){
		 ListNode n = head;
		 String nodes = "";
		 while(n != null){
			 nodes += n.val + ",";
			 n = n.next;
		 }
		 System.out.println(nodes);
	 }
	    

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		printNodes(head);
		deleteNode(head.next.next);
		System.out.println("deleteNode(head.next.next) ");
		printNodes(head);
	}
}
