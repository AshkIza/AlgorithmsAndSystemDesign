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
	        if(head == null || head.next == null){
	            return true;
	        }
	        ListNode s = head;
	        ListNode f = head;
	        ListNode prev = null;
	        ListNode next = null;
	        ListNode n1 = null;
	        ListNode n2 = null;
	        while(f.next != null && f.next.next != null){
	            f= f.next.next;
	            next = s.next;
	            s.next = prev;
	            prev = s;
	            s = next;
	        }
	        if(f.next == null){//odd size linked-list
	            n1 = prev;
	            n2 = s.next;
	        }else{//even size linked-list
	            n2 = s.next;
	            s.next = prev;
	            n1 = s;
	        }
	        return areEqual(n1, n2);
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
	   
	public static void main(String[] args) {
		ListNode oddSizeListHead = new ListNode(1);
		oddSizeListHead.next = new ListNode(2);
		oddSizeListHead.next.next = new ListNode(3);
		oddSizeListHead.next.next.next = new ListNode(2);
		oddSizeListHead.next.next.next.next = new ListNode(1);
		printNodes(oddSizeListHead);
		System.out.println("isPalindrome(oddSizeListHead)  " + isPalindrome(oddSizeListHead) + "\n");

		
		ListNode evenSizeListHead = new ListNode(1);
		evenSizeListHead.next = new ListNode(2);
		evenSizeListHead.next.next = new ListNode(3);
		evenSizeListHead.next.next.next = new ListNode(3);
		evenSizeListHead.next.next.next.next = new ListNode(2);
		evenSizeListHead.next.next.next.next.next = new ListNode(1);
		printNodes(evenSizeListHead);
		System.out.println("isPalindrome(evenSizeListHead)  " + isPalindrome(evenSizeListHead) + "\n");

		
		
		ListNode nonPalindromeListHead = new ListNode(1);
		nonPalindromeListHead.next = new ListNode(2);
		nonPalindromeListHead.next.next = new ListNode(3);
		nonPalindromeListHead.next.next.next = new ListNode(4);
		nonPalindromeListHead.next.next.next.next = new ListNode(5);
		nonPalindromeListHead.next.next.next.next.next = new ListNode(6);
		printNodes(nonPalindromeListHead);
		System.out.println("isPalindrome(nonPalindromeListHead)  " + isPalindrome(nonPalindromeListHead) + "\n");

	}

}
