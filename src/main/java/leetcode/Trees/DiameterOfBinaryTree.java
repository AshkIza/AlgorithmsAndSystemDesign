package leetcode.Trees;

import leetcode.Trees.SymmetricTreeRecursive_Iterative.TreeNode;

/*    Given a binary tree, you need to compute the length of the diameter of the tree. 
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
 * This path may or may not pass through the root.

	Example:
	Given a binary tree
	          1
	         / \
	        2   3
	       / \     
	      4   5    
	Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
	
	Note: The length of path between two nodes is represented by the number of edges between them.
 * 
 *  solution : https://www.techiedelight.com/find-diameter-of-a-binary-tree/
 * */
public class DiameterOfBinaryTree {
    int maxLength;
    
    public int diameterOfBinaryTree(TreeNode root) {
        maxLength = 0;
        depth(root);
        return maxLength;
    }
    
    private int depth(TreeNode node){
        if(node == null || (node.left == null && node.right == null)){
            return 0;
        }
        int left = depth(node.left);
        int right = depth(node.right);
        int path = 0;
        if(node.left != null){
            path = left + 1;
        }
        if(node.right != null){
            path = path + right + 1;
        }
        maxLength = Math.max(maxLength, path);
        return 1 + Math.max(left, right);
    }

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);
		
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		root1.left.right = new TreeNode(4);
		root1.right.left = new TreeNode(5);
		root1.right.right = new TreeNode(6);
		root1.right.left.left = new TreeNode(7);
		root1.right.left.right = new TreeNode(8);
		
		DiameterOfBinaryTree diameter = new DiameterOfBinaryTree();
		System.out.println("diameterOfBinaryTree : " + diameter.diameterOfBinaryTree(root));
		System.out.println("diameterOfBinaryTree : " + diameter.diameterOfBinaryTree(root1));



	}

}
