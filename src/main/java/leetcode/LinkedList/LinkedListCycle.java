package leetcode.LinkedList;

import leetcode.LinkedList.ReverseLinkedList.ListNode;

/*
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/773/
 * 
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.

 * 
 * 
 * */
public class LinkedListCycle {
	
	/*  * Definition for singly-linked list.
	 * */
	 public static class ListNode {
		 int val;
		 ListNode next;
	     ListNode(int x) { val = x; }
	 }

	
	public static boolean hasCycle(ListNode head) {
        //edge cases size 0-2
        if(head == null || head.next == null || head.next.next == null) return false;
        if(head.next.next == head) return true;
        ListNode s = head;
        ListNode f = head;
        while(s.next!=null && f.next!=null && f.next.next!=null){
            s = s.next;
            f = f.next.next;
            if(s == f) return true;
        }
        return false;
    }
	
	static ListNode createCircularLinedList(int[] list, int pos) {
		ListNode head = new ListNode(list[0]);
		ListNode node = head;
		ListNode cyclenode = pos == 0 ? head : null;
		for(int index = 1 ; index < list.length; index++) {
			node.next = new ListNode(list[index]);
			if(pos == index) cyclenode = node.next;
			node =  node.next;
		}
		node.next = cyclenode;
		return head;
	}
	
	
	public static void main(String[] args) {
		ListNode cycleList01 = createCircularLinedList(new int[]{3,2,0,-4}, 1);//head = [3,2,0,-4], pos = 1
		System.out.println("{3,2,0,-4}, 1 : hasCycle ? " + hasCycle(cycleList01) );
		
		
		ListNode cycleList02 = createCircularLinedList(new int[]{1,2}, 0);
		System.out.println("{1,2}, 0 : hasCycle ? " + hasCycle(cycleList02) );

		
		ListNode cycleList03 = createCircularLinedList(new int[]{1}, -1);
		System.out.println("{1}, -1 : hasCycle ? " + hasCycle(cycleList03) );

		
		ListNode cycleList04 = createCircularLinedList(new int[]{1,2,3,4,5,6}, -1);
		System.out.println("{1,2,3,4,5,6}, -1 : hasCycle ? " + hasCycle(cycleList04) );

		
		ListNode cycleList05 = createCircularLinedList(new int[]{1,2,3,4,5,6}, 3);
		System.out.println("{1,2,3,4,5,6}, 3 : hasCycle ? " + hasCycle(cycleList05) );
		
	}

}
