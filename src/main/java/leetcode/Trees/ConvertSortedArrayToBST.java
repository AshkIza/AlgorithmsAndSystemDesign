package leetcode.Trees;

import leetcode.Trees.SymmetricTreeRecursive_Iterative.TreeNode;

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
        if(nums.length == 0 ){
            return null;
        }
        return toBSTRec(nums, 0 , nums.length - 1);
    }
    
    public static TreeNode toBSTRec(int[] array, int ib, int ie){
       if(ie < ib) {
           return null;
        }
        int median =  ib + (ie - ib) /2 ;
        TreeNode root = new TreeNode(array[median]);
        root.left = toBSTRec(array, ib, median -1);
        root.right = toBSTRec(array, median + 1, ie);
        return root;
    }
    
    public static boolean equal(TreeNode tree01, TreeNode tree02){
    	if ((tree01 == null && tree02 !=null) || (tree01 != null && tree02 == null)){
    		return false;
    	}
    	if(tree01 != null && tree02 != null){
    		if(tree01.val != tree02.val){
    			return false;
    		}
    		return equal(tree01.left, tree02.left) && equal(tree01.right, tree02.right);
    	}
    	return true;//both null
    }
    
    public static  int findMedianValue(int[] array, int low, int high){
    	return array[low + (high - low) /2];
    	/* for odd (high - low) values, it rounds down to the smaller integer.
    		So, the median will always be within (low, high) bound
    	*/
    }

	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(-3);
		root.right = new TreeNode(9);
		root.left.left = new TreeNode(-10);
		root.right.left = new TreeNode(5);
		TreeNode rootFound = sortedArrayToBST(new int[] {-10, -3, 0 , 5 , 9});
		System.out.println("  equal(root, rootFound) "  + equal(root, rootFound));
		
		
		System.out.println("\narray[low + (high - low) /2] : (always safe to use)");
		System.out.println("findMedianValue(new int[] {10} , 0, 0) : "  + findMedianValue(new int[] {10} , 0, 0) );
		System.out.println("findMedianValue(new int[] {10, 20} , 0, 1) : "  + findMedianValue(new int[] {10, 20} , 0, 1) );
		System.out.println("findMedianValue(new int[] {10, 20, 30} , 0, 2) : "  + findMedianValue(new int[] {10, 20, 30} , 0, 2) );
		System.out.println("findMedianValue(new int[] {10, 20, 30} , 1, 2) : "  + findMedianValue(new int[] {10, 20, 30} , 1, 2) );
		System.out.println("   array[low + (high - low) /2] :\n     for odd (high - low) values, it rounds down to the smaller integer.\n     So, the median will always be within (low, high) bound");


	}

}
