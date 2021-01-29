package leetcode.LinkedList;

import leetcode.LinkedList.RemoveNthNodeFromTheEnd.ListNode;

/* Palindrome Linked List
	Given a singly linked list, determine if it is a palindrome.
	
	Example 1:
	
	Input: 1->2
	Output: false
	Example 2:
	
	Input: 1->2->2->1
	Output: true
	Follow up:
	Could you do it in O(n) time and O(1) space?
	
	solution:   break and reverse the half, then compare two half (one of the halves already reversed)
	https://www.programcreek.com/2014/07/leetcode-palindrome-linked-list-java/
 * 
 * */
public class PalindromeLinkedList {

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
	
	
	 /* O(N) with O(1) extra space
	  * */
	   public static boolean isPalindrome(ListNode head) {
		     if(head == null || head.next ==null) return true;
		        if(head.next.next==null) return head.val == head.next.val;
		        ListNode slow = head;
		        ListNode fast = head;
		        ListNode nextslow = head.next;
		        while(fast.next!=null && fast.next.next!=null){
		            fast=fast.next.next;
		            ListNode pre = slow;
		            slow = nextslow;
		            nextslow = nextslow.next;
		            slow.next = pre;
		        }
		        ListNode left;
		        ListNode right;
		        if(fast.next == null){//odd size
		            //slow is the middle element
		            left = slow.next;
		            right = nextslow;
		        }else{//even size
		            // slow and nextslow are middle elements
		            left = slow;
		            right = nextslow;
		        }
		        while(left!=null && right!=null){
		            if(left.val != right.val) return false;
		            left = left.next;
		            right = right.next;
		        }
		        return true;
	    }
	    
	   static  boolean  areEqual(ListNode n1, ListNode n2){
	        while(n1 != null && n2 != null){
	            if(n1.val != n2.val){
	                return false;
	            }else{
	                n1 = n1.next;
	                n2 = n2.next;
	            }
	        }
	        return true;
	    }
	   
	   
	static ListNode createLinkedlist(int[] array) {
		ListNode head = new ListNode(array[0]);
		ListNode node = head;
		for(int i = 1; i < array.length; i++) {
			node.next = new ListNode(array[i]);
			node = node.next;
		}
		return head;
	}
	   
	public static void main(String[] args) {
		ListNode oddSizeListHead = createLinkedlist(new int[] {1,2,3,2,1});
		printNodes(oddSizeListHead);
		System.out.println("isPalindrome(oddSizeListHead)  " + isPalindrome(oddSizeListHead) + "\n");

		ListNode evenSizeListHead = createLinkedlist(new int[] {1,2,3,3,2,1});
		printNodes(evenSizeListHead);
		System.out.println("isPalindrome(evenSizeListHead)  " + isPalindrome(evenSizeListHead) + "\n");

		ListNode nonPalindromeListHead = createLinkedlist(new int[] {1,2,3,4,5,6});
		printNodes(nonPalindromeListHead);
		System.out.println("isPalindrome(nonPalindromeListHead)  " + isPalindrome(nonPalindromeListHead) + "\n");

		ListNode biglinkedlist = createLinkedlist(new int[] {-1,2,3,4,5,6,7,8,9,10,11,10,9,8,7,6,5,4,3,3,-1});
		printNodes(biglinkedlist);
		System.out.println("isPalindrome(biglinkedlist)  " + isPalindrome(biglinkedlist) + "\n");

	}

}
