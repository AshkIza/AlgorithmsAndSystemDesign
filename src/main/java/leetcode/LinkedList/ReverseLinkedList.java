package leetcode.LinkedList;


/* Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

*** NOTE: A linked list can be reversed either iteratively or recursively. Could you implement both?***

HINT: MAKE SURE TO HANDLE HEAD & TAIL PROPERLY (head and tail are corner cases here)
 * 
 * */
import leetcode.LinkedList.RemoveNthNodeFromTheEnd.ListNode;

public class ReverseLinkedList {

	
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

    public static  ListNode reverseList(ListNode head) {
    	   if(head == null || head.next == null) return head;
           ListNode pre = head;
           ListNode node = head.next;  
           pre.next = null;//avoid infinite circular loop
           while(node.next != null){
               //saving the next node before we loose tracking of it
               ListNode nextnode = node.next;
               node.next = pre;
               pre=node;
               node = nextnode;
           }
         /* node is pointing to tail now (node.next = null), 
          * we need to link it to prev 
          * (otherwise head will get linked to null - LinkedList of size 1)
         */
         node.next = pre;
         return node;// new head 
    }
    
    public static ListNode reverseListRecursive(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode node = head.next;
        pre.next = null;//avoid infinite circular loop
        return reverseListR(pre, node);
    }
    
    static ListNode reverseListR(ListNode pre, ListNode node){
        if(node.next == null){
            node.next = pre;
            return node;
        }
        ListNode nextnode = node.next;
        node.next = pre;
        return reverseListR(node, nextnode);
    }
    
    
    /*  reverseRecursive(null, head)
     * */
    public static  ListNode reverseRecursive(ListNode node, ListNode next) {
    	if(next == null){
    		return node;
    	}
    	ListNode newNode = next.next;
    	next.next = node;
    	return reverseRecursive(next, newNode);
    }

    
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		System.out.println("1) Reverse a linked list Iterativedly");
		printNodes(head);
		System.out.println("reverseList(head)");
		ListNode reveresedListHead = reverseList(head);
		printNodes(reveresedListHead);
		
		System.out.println("\nNOTE: ALWAYS TEST YOUR CODE FOR ALL DATA (1->2->3->4->5)");
		System.out.println(" if we dont check last data (5), we will forget to add line 'node.next = prev' after the loop, \n  and function returns linkedlist of size 1 (only this node)\n");
	
		System.out.println("2) Reverse a linked list Recursively");
		printNodes(reveresedListHead);
		//System.out.println("reverseRecursive(null, head)");
		//printNodes(reverseRecursive(null,reveresedListHead));
		System.out.println("reverseRecursive(head)");
		printNodes(reverseListRecursive(reveresedListHead));

	}

}
