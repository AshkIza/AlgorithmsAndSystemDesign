package leetcode.LinkedList;

import leetcode.LinkedList.DeleteNodeNoAccessToHead.ListNode;

/*   Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

 

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.


Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

solution : http://buttercola.blogspot.com/2014/08/leetcode-linked-list-cycle-ii.html
 * 
 * */
public class LinkedListCycle2 {

	   public ListNode detectCycle(ListNode head) {
	        ListNode node = cycle(head);
	        if(node==null){
	            return null;//no cycle
	        }
	        ListNode nodeHead = head;
	        while(node != nodeHead){
	            node = node.next;
	            nodeHead = nodeHead.next;
	        }
	        return node;
	    }
	    
	   /* better to split the code in sub-functions, 
	    * 	otherwise had to call break; in while loop */
	    private ListNode cycle(ListNode head) {
	        if(head == null){//check for null head
	            return null;
	        }
	        ListNode slow = head;
	        ListNode fast = head;
	        while(slow.next != null &&
	               fast.next != null &&
	                fast.next.next != null){
	            slow = slow.next;
	            fast = fast.next.next;
	            if(slow == fast){
	                return fast;//cycle detected!
	            }
	        }
	        return null;//no cycle
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
		LinkedListCycle2 cycle_2 = new LinkedListCycle2();
		
		ListNode nullHead  = null;
		printNodes(nullHead);
		
		ListNode sizeOneList = new ListNode(1);
		printNodes(sizeOneList);
		ListNode cycle02 = cycle_2.detectCycle(sizeOneList);
		if(cycle02!=null){
			System.out.println(" detectCycle(sizeOneList) " + cycle02.val);
		}

		
		ListNode noCycle = new ListNode(1);
		noCycle.next = new ListNode(2);
		noCycle.next.next = new ListNode(3);
		noCycle.next.next.next = new ListNode(4);
		printNodes(noCycle);	
		if(noCycle!=null){
			System.out.println(" detectCycle(sizeOneList) " + noCycle.val);
		}

		ListNode hasCycle = new ListNode(1);
	    hasCycle.next = new ListNode(2);
	    hasCycle.next.next = new ListNode(3);
	    hasCycle.next.next.next = new ListNode(4);
	    hasCycle.next.next.next.next = hasCycle.next;
		printNodes(hasCycle);
		System.out.println(" detectCycle(hasCycle) " + cycle_2.detectCycle(hasCycle).val);



	}

}
