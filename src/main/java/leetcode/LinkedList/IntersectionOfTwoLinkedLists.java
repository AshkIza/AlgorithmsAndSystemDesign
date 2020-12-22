package leetcode.LinkedList;

import leetcode.LinkedList.DeleteNodeNoAccessToHead.ListNode;

/*  Intersection of Two Linked Lists
Solution
Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:


begin to intersect at node c1.

 

Example 1:


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
Output: Reference of the node with value = 8
Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 

Example 2:


Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Reference of the node with value = 2
Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 

Example 3:


Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: null
Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
 

Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

solution: https://www.programcreek.com/2014/02/leetcode-intersection-of-two-linked-lists-java/
 * 
 * */
public class IntersectionOfTwoLinkedLists {
	
	
	/*  time : O(m+n)    extra space: O(1)
	 *   O(m+n) length()  + O(m-n)  skip + O(n) intersect ~ O(m+n)
	 * */
	 public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	        if(headA == null || headB == null){
	            return null;
	        }
	        int a = length(headA);
	        int b = length(headB);
	        int N = Math.abs(a-b);
	        ListNode nodeA = null;
	        ListNode nodeB = null;

	        if(a==b){
	            nodeA = headA;
	            nodeB = headB;
	        }else if(a > b){
	            nodeA = skip(headA, N);
	            nodeB = headB;
	        }else{
	            nodeA = headA;
	            nodeB = skip(headB, N);
	        }
	        return intersect(nodeA, nodeB);
	    }
	    
	    int length(ListNode head){
	        ListNode node = head;
	        int n = 1;
	        while(node.next != null){
	            node = node.next;
	            n++;
	        }
	        return n;
	    }
	    
	    ListNode skip(ListNode head, int N){
	        ListNode node = head;
	        while(N>0){
	            node = node.next;
	            N--;
	        }
	        return node;
	    }
	    
	    ListNode intersect(ListNode nodeA, ListNode nodeB) {
	        if(nodeA == nodeB){
	            return nodeA;
	        }
	        while(nodeA.next!= null && nodeA.next!=nodeB.next){
	            nodeA = nodeA.next;
	            nodeB = nodeB.next;
	        }
	        
	        if(nodeA.next!=null && nodeA.next.val == nodeB.next.val){
	            return nodeA.next;
	        }else{
	            return null;
	        }
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
