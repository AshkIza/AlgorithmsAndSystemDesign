package leetcode.LinkedList;

import leetcode.LinkedList.DeleteNodeNoAccessToHead.ListNode;

/*
	Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
	
	Example:
	
	Input: 1->2->4, 1->3->4
	Output: 1->1->2->3->4->4
	
	Solution: 
		https://www.programcreek.com/2012/12/leetcode-merge-two-sorted-lists-java/ 
		     fake head 
		     	use fakeHead where real head is fakeHead.next, 
		     	This reduces the logic (no need to check if head == null)
 * 
 * */
public class MergeTwoSortedList {

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
	 
	// use fakeHead where real head is fakeHeas.next, This reduces the logic (no need to check if  head == null)
    public static  ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        ListNode node = null;
        ListNode head = null;
        while(l1 != null && l2 != null){
            ListNode val = null;
            if(l1.val < l2.val){
                val = l1;
                l1 = l1.next;
            }else{
                val = l2;
                l2 = l2.next;
            }
            if(head == null){
                head = val;
                node = head;
            }else{
                node.next = val;
                node = node.next;
            }
        }
        ListNode n = (l1 == null) ? l2 : l1;
        if(n != null){
            if(head == null){
                head = n;
            }else{
                node.next = n;
            }
        }
        return head;
    }
    
    
    public static  ListNode mergeTwoListsFakeHead(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        ListNode fakeHead = new ListNode(0);
        ListNode node = fakeHead;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
            	node.next = l1;
                l1 = l1.next;
            }else{
            	 node.next = l2;
            	 l2 = l2.next;
            }
            node = node.next;
        }
        if(l1 != null){
        	node.next = l1;
        }
        if(l2 != null){
        	node.next = l2;
        }
        return fakeHead.next;
    }
    

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(2);
		l2.next.next = new ListNode(4);
		
		printNodes(l1);
		printNodes(l2);
		System.out.println("\nmergeTwoLists(l1, null)");
		printNodes(mergeTwoLists(l1,null));

		System.out.println("\nmergeTwoLists(l1, l2)");
		printNodes(mergeTwoLists(l1,l2));

		
		System.out.println("\n\nusing fakeHead ( head is fakeHead.next)");
		System.out.println(" This reduces the logic (no need to check if head == null)");

		ListNode n1 = new ListNode(5);
		n1.next = new ListNode(7);
		n1.next.next = new ListNode(9);
		
		ListNode n2 = new ListNode(4);
		n2.next = new ListNode(6);
		n2.next.next = new ListNode(8);
		n2.next.next.next = new ListNode(10);
		
		printNodes(n1);
		printNodes(n2);
		
		System.out.println("\nmergeTwoListsFakeHead(n1, n2)");
		printNodes(mergeTwoLists(n1,n2));
		
	}

}
