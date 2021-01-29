package leetcode.Trees;

/*  Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
 * 
 * */
public class MaximumDepthofBinaryTree {
	
	

	  //Definition for a binary tree node.
	  public static class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }
	 
	
	
	
	  public static int maxDepth(TreeNode root) {
	        if(root==null)  return 0;
	        else if(isLeaf(root)) return 1;
	        return 1 + Math.max(maxDepth(root.left),  maxDepth(root.right));
	    }
	    
	 static boolean  isLeaf(TreeNode node){
	        return node.left == null && node.right == null;
	  }

	public static void main(String[] args) {
		System.out.println("  Tree = Recursive (90% of problems) " );
		System.out.println("  if using Iterative for tree -> use Queue aka LinkedList \n\n" );


	}

}
