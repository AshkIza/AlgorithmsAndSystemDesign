package leetcode.Trees;

import leetcode.Trees.SymmetricTreeRecursive_Iterative.TreeNode;

/*  Given a binary tree, flatten it to a linked list in-place.

	For example, given the following tree:
	
	    1
	   / \
	  2   5
	 / \   \
	3   4   6
	The flattened tree should look like:
	
	1
	 \
	  2
	   \
	    3
	     \
	      4
	       \
	        5
	         \
	          6
	          
	    solution:
	          https://medium.com/@fabianterh/iteratively-solving-flatten-binary-tree-to-linked-list-in-place-50ac7825f75b
 * */
public class FlattenBinaryTreeToLinkedList {
	
    public void flatten(TreeNode root) {
        flattenR(root);
    }
   
    TreeNode flattenR(TreeNode node){
        if(node==null){
            return null;
        }
        if(node.left == null && node.right == null){
            return node;
        }
        TreeNode leftTree = flattenR(node.left);
        TreeNode rightTree = flattenR(node.right);
        node.left = null;
        if(leftTree != null){
            node.right = leftTree;
            TreeNode n  = leftTree;
            while(n.right!=null){
                n = n.right;
            }
            n.right = rightTree;
        }else{
            node.right = rightTree;
        }
        return node;
    }
    
    void printFlattenTree(TreeNode root){
    	TreeNode node = root;
    	String st = "[ " + root.val + ", ";
    	while(node.right!= null){
    		node = node.right;
    		st += node.val + ", ";
    	}
    	st+="]";
		System.out.println(st);
    }

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);

		FlattenBinaryTreeToLinkedList falttenBt = new FlattenBinaryTreeToLinkedList();
		falttenBt.flatten(root);
		falttenBt.printFlattenTree(root);
	}

}
