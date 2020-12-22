package leetcode.LinkedList;

import leetcode.LinkedList.DeleteNodeNoAccessToHead.ListNode;

/*  Merge k Sorted Lists
	Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
	
	Example:
	
	Input:
	[
	  1->4->5,
	  1->3->4,
	  2->6
	]
	Output: 1->1->2->3->4->4->5->6
 * 
 *  solution : 
 * */
public class MergeKSortedLists {
	
	public static ListNode mergeKLists(ListNode[] lists) {
		ListNode root = null;
		ListNode merged = null;
		while(lists!= null && lists.length > 0){
			int index = findMin(lists);
			ListNode node = lists[index];
			if(root == null){
				root = node;
				merged = root;
			}else{
				merged.next = node;
				merged = merged.next;
			}
			
			if(node.next != null){
				lists[index] = node.next;
			}else{
				lists = removeList(lists, index);
			}
		}
		merged.next = null;
		return root;
	}
	
	private static int findMin(ListNode[] lists) {
		int min = 0;
		for(int i = 1; i < lists.length; i++){
			if(lists[i].val < lists[min].val){
				min = i;
			}
		}
		return min;
	}
	
	private static ListNode[] removeList(ListNode[] lists, int index) {
		if(lists.length == 0 ){
			return null;
		}
		ListNode[] newList = new ListNode[lists.length - 1];
		int count = 0;
		for(int i = 0; i < lists.length; i++){
			if(i != index){
				newList[count] = lists[i];
				count++;
			}
		}
		return newList;
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
		ListNode list01 = new ListNode(1);
		list01.next = new ListNode(4);
		list01.next.next =new ListNode(5);
		
		ListNode list02 = new ListNode(1);
		list02.next = new ListNode(3);
		list02.next.next =new ListNode(4);
		
		ListNode list03 = new ListNode(2);
		list03.next = new ListNode(6);
		
		ListNode[] lists = new ListNode[3];
		lists[0] = list01;
		lists[1] = list02;
		lists[2] = list03;
		printNodes(mergeKLists(lists));
		
		
	}

}
