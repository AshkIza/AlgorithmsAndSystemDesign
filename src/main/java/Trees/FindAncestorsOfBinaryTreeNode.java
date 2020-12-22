package Trees;

import leetcode.Trees.SymmetricTreeRecursive_Iterative.TreeNode;

/*   https://www.techiedelight.com/find-ancestors-of-given-node-binary-tree/
 * 
 * time complexity : O(n)
 * extra space: O(h) stack
 * 
 * */
public class FindAncestorsOfBinaryTreeNode {
	
	public static void printAncestors(TreeNode root, int nodeVal){
		if(root == null){
			return;
		}
		if(root.val == nodeVal){
			System.out.println("root node, no ancestor");
			return;
		}
		isAncestor(root, nodeVal);
	}
	
	public static boolean isAncestor(TreeNode root, int nodeVal){
		if(root == null){
			return false;
		}
		
		if(root.left!= null &&
				(root.left.val == nodeVal || isAncestor(root.left, nodeVal))){
			System.out.print(" " + root.val);
			return true;
		}
		if(root.right!= null &&
				(root.right.val == nodeVal || isAncestor(root.right, nodeVal))){
			System.out.print(" " + root.val);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		/* Construct below tree
		  1
		/   \
	   /     \
	  2       3
	   \     / \
		4   5   6
		   / \
		  7   8
	   */
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		root.right.left.left = new TreeNode(7);
		root.right.right.right = new TreeNode(8);

		System.out.print("printAncestors(root, 7) : ");
		printAncestors(root, 7);
		System.out.println();
		
		System.out.print("printAncestors(root, 6) : ");
		printAncestors(root, 6);
		System.out.println();

		System.out.print("printAncestors(root, 4) : ");
		printAncestors(root, 4);
		System.out.println();
		

		System.out.print("printAncestors(root, 2) : ");
		printAncestors(root, 2);
		System.out.println();
		

		System.out.print("printAncestors(root, 1) : ");
		printAncestors(root, 1);
		System.out.println();

	}

}
