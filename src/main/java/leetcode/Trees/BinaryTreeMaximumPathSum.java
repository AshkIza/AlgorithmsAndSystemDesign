package leetcode.Trees;

import leetcode.Trees.TreeNode;

/*   Given a non-empty binary tree, find the maximum path sum.
		
		For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
		
		Example 1:
		
		Input: [1,2,3]
		
		       1
		      / \
		     2   3
		
		Output: 6
		Example 2:
		
		Input: [-10,9,20,null,null,15,7]
		
		   -10
		   / \
		  9  20
		    /  \
		   15   7

Output: 42
 * 
 * 
 * */
public class BinaryTreeMaximumPathSum {

	public static int maxPathSum(TreeNode root) {
		if(root == null){
			return Integer.MIN_VALUE;
		}
		int leftChuldrenAndNode = weigthLeft(root);
		int rightChuldrenAndNode = weigthRight(root);
		int all = weigth(root);
		int a = Math.max(leftChuldrenAndNode, Math.max(rightChuldrenAndNode, all));
		int b = maxPathSum(root.left);
		int c = maxPathSum(root.right);
		return Math.max(a, Math.max(b, c));
    }
	
	public static int weigthLeft(TreeNode node) {
		if(node.left == null){
			return node.val;
		}
		int rootVal = node.val;
		node = node.left;
		int sum = node.val;
		while(node.left != null){
			node = node.left;
			sum += node.val;
		}
		return Math.max(sum, Math.max(sum + rootVal, rootVal));
	}
	
	public static int weigthRight(TreeNode node) {
		if(node.right == null){
			return node.val;
		}
		int rootVal = node.val;
		node = node.right;
		int sum = node.val;
		while(node.right != null){
			node = node.right;
			sum += node.val;
		}
		return Math.max(sum, Math.max(sum + rootVal, rootVal));
	}
	
	public static int weigth(TreeNode node) {
		if(node.left == null && node.right == null){
			return node.val;
		}
		
		int sum = node.val;
		TreeNode rightNode  = node.right;
		TreeNode leftNode = node.left;
		if(leftNode != null){
			sum += leftNode.val;
			while(leftNode.left != null){
				leftNode = leftNode.left;
				sum += leftNode.val;
			}
		}
		
		if(rightNode != null){
			sum += rightNode.val;
			while(rightNode.right != null){
				rightNode = rightNode.right;
				sum += rightNode.val;
			}
		}
		
		
		return sum;
	}
	
	public static void main(String[] args) {
		TreeNode root01 = new TreeNode(-10);
	    root01.left = new TreeNode(9);
	    root01.right = new TreeNode(20);
	    root01.right.left = new TreeNode(15);
	    root01.right.right = new TreeNode(7);
	    System.out.println("maxPathSum : " + maxPathSum(root01));
	
	    
	    TreeNode root02 = new TreeNode(1);
		root02.left = new TreeNode(2);
		root02.right = new TreeNode(3);
	    System.out.println("maxPathSum : " + maxPathSum(root02));
	    
	    
	    
		TreeNode root03 = new TreeNode(-10);
	    System.out.println("maxPathSum : " + maxPathSum(root03));
	    
	    TreeNode root04 = new TreeNode(1);
	     root04.right = new TreeNode(2);
	    System.out.println("maxPathSum : " + maxPathSum(root04));
	    
	    TreeNode root05 = new TreeNode(2);
	     root05.left = new TreeNode(-1);
	     root05.right = new TreeNode(-2);
	    System.out.println("maxPathSum : " + maxPathSum(root05));
	    


	}

}
