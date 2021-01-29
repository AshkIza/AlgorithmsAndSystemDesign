package leetcode.Trees;

import static leetcode.Trees.TreeNode.*;

/* Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

		For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
		
		Example:
		
		Given the sorted array: [-10,-3,0,5,9],
		
		One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
		
		      0
		     / \
		   -3   9
		   /   /
		 -10  5
		 
		 
 Solution: https://medium.com/@harycane/convert-sorted-array-to-bst-35781e940ca5		 
 * 
 * 
 * */
public class ConvertSortedArrayToBST {
    
		public static TreeNode sortedArrayToBST(int[] nums) {
	       return sortedArrayToBST(nums, 0, nums.length -1);
	    }
	    
		public static TreeNode sortedArrayToBST(int[] nums, int a, int b) {
	        if(b < a) return null;
	        int mid = (a+b) / 2;
	        TreeNode root = new TreeNode(nums[mid]);
	        if(b == a) return root;
	        root.left = sortedArrayToBST(nums, a , mid -1);
	        root.right = sortedArrayToBST(nums, mid + 1 , b);
	        return root;
	    }

		 static int findMedianValue(int[] nums, int low, int high) {
			return nums[(low+high)/2];
		}
		 
	public static void main(String[] args) {
		TreeNode root_case0 = sortedArrayToBST(new int[] {-10,-3,0,5,9});
		System.out.println(levelOrder(root_case0));

		System.out.println("\narray[(low+hight) /2] : (always safe to use)");
		System.out.println("findMedianValue(new int[] {10} , 0, 0) : "   + findMedianValue(new int[] {10} , 0, 0));
		System.out.println("findMedianValue(new int[] {10, 20} , 0, 1) : "  + findMedianValue(new int[] {10, 20} , 0, 1) );
		System.out.println("findMedianValue(new int[] {10, 20, 30} , 0, 2) : "  + findMedianValue(new int[] {10, 20, 30} , 0, 2) );
		System.out.println("findMedianValue(new int[] {10, 20, 30} , 1, 2) : "  + findMedianValue(new int[] {10, 20, 30} , 1, 2) );
		System.out.println("   array[(low+high)/2] :\n     for odd (high - low) values, it rounds down to the smaller integer.\n     So, the median will always be within (low, high) bound");


	}

}
