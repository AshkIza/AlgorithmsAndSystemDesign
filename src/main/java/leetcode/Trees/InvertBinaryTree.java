package leetcode.Trees;

import leetcode.Trees.SymmetricTreeRecursive_Iterative.TreeNode;

/* Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
 * */
public class InvertBinaryTree {
	
	   public TreeNode invertTree(TreeNode root) {
	        if(root == null){
	            return null;
	        }
	        TreeNode leftNode = invertTree(root.left);
	        TreeNode rightNode = invertTree(root.right);
	        root.left = rightNode;
	        root.right = leftNode;
	        return root;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
