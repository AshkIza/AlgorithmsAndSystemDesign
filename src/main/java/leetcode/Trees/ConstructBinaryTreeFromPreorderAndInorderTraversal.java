package leetcode.Trees;

import leetcode.Trees.SymmetricTreeRecursive_Iterative.TreeNode;

/*  Construct Binary Tree from Preorder and Inorder Traversal
	Given preorder and inorder traversal of a tree, construct the binary tree.
	
	Note:
	You may assume that duplicates do not exist in the tree.
	
	For example, given
	
	preorder = [3,9,20,15,7]
	inorder = [9,3,15,20,7]
	Return the following binary tree:
	
	    3
	   / \
	  9  20
	    /  \
   	  15   7
   	  
   	  solution: 
   	  https://algorithms.tutorialhorizon.com/make-a-binary-tree-from-given-inorder-and-preorder-traveral/
 * 
 * */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
	
	
	/* time complexity Teta(n log n) 
	 *                O(n ^2) (in case of linked-list tree - tree height is n instead of logn )
	 * 
	 * */
	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		int N = preorder.length;
		if (N == 0){
			return null;
		}
		if(N == 1){
			return new TreeNode(preorder[0]);
		}
		return buildRecursive(preorder, 0 , inorder, 0 , N);
	}
	
	private static TreeNode buildRecursive(int[] preorder, int preStart,
											int[] inorder, int inStart, int size) {
		if(size <= 0 ){//null node
			return null;
		}
		TreeNode node = new TreeNode(preorder[preStart]);
		if(size == 1){
			return node;//leaf
		}
		int nodeIndexInorder = findIndex(inorder, preorder[preStart], inStart, size);
		int leftSize = nodeIndexInorder - inStart;
		int rightSize =  size - leftSize - 1;
		if(leftSize > 0){
			node.left = buildRecursive(preorder, preStart + 1,
										inorder, inStart, leftSize);
		}
		if(rightSize > 0){
			node.right = buildRecursive(preorder, preStart + size - rightSize,
										inorder, nodeIndexInorder + 1, rightSize);
		}
		
		return node;
	}
	
	private static int findIndex(int[] arr, int value, int startIndex, int size){
		if(size < 0){
			return -1;
		}
		int offset = 0;
		while(offset < size){
			if(arr[startIndex + offset] == value){
				return startIndex + offset;
			}
			offset++;
		}
		return -1;
	}

	public static void main(String[] args) {
		
		
		TreeNode tree01 = buildTree(new int[]{3,9,20,15,7}, //preorder
								new int[]{9,3,15,20,7}); //inorder
		
		TreeNode tree02 = buildTree(new int[]{30, 20, 15, 10, 18, 25, 22, 28,
					40, 35, 33, 37, 45, 43, 49}, //preorder
				new int[]{10, 15, 18, 20, 22, 25, 28, 30,
					33, 35, 37, 40, 43, 45, 49}); //inorder
		
		
		//corner cases
		TreeNode tree3 = buildTree(new int[]{3, 2, 1, 4}, //preorder
				new int[]{1,2, 3, 4}); //inorder
		
		TreeNode nullleft = buildTree(new int[]{1,2}, //preorder
						new int[]{1,2}); //inorder
		
		TreeNode nullright = buildTree(new int[]{3, 2, 1}, //preorder
				new int[]{1,2, 3}); //inorder
		
		
		System.out.println("blah");
		
		
	}

}
