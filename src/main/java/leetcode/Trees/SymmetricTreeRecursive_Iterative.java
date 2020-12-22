package leetcode.Trees;

import leetcode.Trees.MaximumDepthofBinaryTree.TreeNode;

/*  Symmetric Tree
	Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	
	For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
	
	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	 
	
	But the following [1,2,2,null,3,null,3] is not:
	
	    1
	   / \
	  2   2
	   \   \
	   3    3
	 
	
	Note:
	Bonus points if you could solve it both recursively and iteratively.
	
	Solution: 
	Recursive:  https://www.programcreek.com/2014/03/leetcode-symmetric-tree-java/
	Iterative: https://www.geeksforgeeks.org/check-symmetric-binary-tree-iterative-approach/
 * 
 * 
 * */
public class SymmetricTreeRecursive_Iterative {
	
	  //Definition for a binary tree node.
	  public static class TreeNode {
	      public int val;
	      public TreeNode left;
	      public TreeNode right;
	      public TreeNode(int x) { val = x; }
	  }
	 
	
	
    public static boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetricRecursive(root.left, root.right);
        
    }
    
     public static boolean isSymmetricRecursive(TreeNode left, TreeNode right) {
         if(left == null && right == null){
             return true;
         }
         if(left == null || right == null){
             return false;
         }
         if(left.val != right.val){
             return false;
         }
         
         return isSymmetricRecursive(left.left, right.right) &&
        		 isSymmetricRecursive(left.right, right.left);
    }

	public static void main(String[] args) {
		System.out.println("  Tree = Recursive (90% of problems) " );
		System.out.println("  Tree = Recursive (90% of problems) " );
		System.out.println("  Tree = Recursive (90% of problems) " );
		System.out.println("  Tree = Recursive (90% of problems)\n\n " );
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		
		root.left.left.left = null;
		root.left.left.right = new TreeNode(6);
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(8);
		
		root.right.left.left = new TreeNode(8);
		root.right.left.right = new TreeNode(7);
		root.right.right.left = new TreeNode(6);
		root.right.right.right = null;
		
		System.out.println("isSymmetric(root): " + isSymmetric(root));
		System.out.println(" root.right.right.right = new TreeNode(10)");
		root.right.right.right = new TreeNode(10);
		System.out.println("isSymmetric(root): " + isSymmetric(root));
	}

}
