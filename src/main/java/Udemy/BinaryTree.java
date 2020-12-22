package Udemy;

import java.util.LinkedList;
import java.util.Queue;
/* NOTE ON TREES: in most tree problems, we need to traverse through the tree (either BF or DF) AND 
 * DO SOME EXTRA STUFFS WHILE TRAVERSEING THROUGH EACH NODE
 * BF : QUEUE        DF: RECURSIVE CALL or STACK
 * Witdth of tree--> Breath first traversal
 * try DF approach first , then use BF approach
 * */

/*  binary tree algorithms:
 *   depth , height of node and tree
 *   find ancestors of a given node
 * */
public class BinaryTree {
	
	public static class BTNode{
		int data;
		public BTNode left;
		public BTNode right;
		BTNode(Integer value){
			data = value;
		}
		public BTNode addChildren(BTNode l, BTNode r){
			left = l;
			right = r;
			return this;
		}
	}
	
	public BTNode root;
	BinaryTree(BTNode node){
		root = node;
	}
	
	
	
	/*  Maximum width of a binary tree (maxWidth = 3  - level 3)
	 * https://www.geeksforgeeks.org/maximum-width-of-a-binary-tree/
	 *   1
        /  \
       2    3
     /  \     \
    4    5     8 
              /  \
             6    7
             
             	Method 2 (Using Level Order Traversal with Queue)
	 * */
	/* by assuming we always have previous level width (root level width is always1)
	 * , and adding all children in a queue
	 * we can make sure, we can identify when each previous level ends
	 *  hence, all remaining nodes in the queue will all be the children aka next level width
	 * */
	int maxWidth(){
		int width = 1;//previous level width
		int maxtWidth = 1;
		Queue<BTNode> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()){
			if(width == 0){
			//reached the end of previous level, all remaining nodes in queue are from next level
				width = q.size();
				maxtWidth = (width > maxtWidth) ? width : maxtWidth;//check if this level has higher width
			}
			BTNode node = q.remove();
			width--;
			if(node.left != null){
				q.add(node.left);
			}
			if(node.right != null){
				q.add(node.right);
			}
		}
		return maxtWidth;
	}
	
	/* https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/
	 *   Find the Maximum Depth or Height of a Tree
	 * height of a node (downward) : is number of edges from the node to the deepest leaf
	 * depth of node (upward) : is number of edges from node to root
	 * root depth = 0     leaf height = 0
	 * tree height = height of root
	 * tree depth = depth of deepest leaf
	 * tree depth = tree height
	 * */
	 int heightRecursive(BTNode node){
		 if((node == null) || (node.left == null && node.right == null)){
			 return 0;//leaf
		 }
		 //node has children (1 or 2)
		 return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
	 }
	 
	 /* using breath first traversal (using a queue and integer width to keep track of where each level ends),
	  *  check each level while saving each level's children (next level),
	  * 	 if find the node, return the level
	  * 	 if not, go to the next level
	  * */
	 int level(BTNode node){
		 if(root == null){
			 return -1;
		 }
		 int level = 1;
		 int width = 1;
		 Queue<BTNode> q = new LinkedList<BTNode>();
		 q.add(root);
		 while(!q.isEmpty()){
			 if(width == 0) {//start of next level
				 width = q.size();
				 level++;
			 }
			 BTNode n = q.remove();
			 if(n.data == node.data){
				 return level;//node found, returning its level
			 }
			 //node not found, save its children , and keep searching
			 if(n.left != null) {
				 q.add(n.left);
			 }
			 if(n.right != null) {
				 q.add(n.right);
			 }
			 width--;
		 }
		 return -1;//could not find the node
	 }
	 
	 int depth(BTNode node){
		 return level(node) - 1;
	 }
	 
	 /*  https://www.geeksforgeeks.org/get-level-of-a-node-in-a-binary-tree/
	  * */
	 int depthFromRecursive(BTNode n, BTNode key){
		 if ((n == null) || (n.left == null && n.right == null)){
			 return -1;//null node or reached the leaf
		 }
		 if(n == key){
			 return 0;//root is key
		 }
		 if(n.left == key || n.right ==key){
			 return 1;//children is key
		 }
		 //search later descendants
		 int left = depthFromRecursive(n.left, key);
		 int right = depthFromRecursive(n.right, key);
		 int max = Math.max(left, right);
		 return (max > 0) ? max + 1 : -1;
	 }
	 
	 
		/*    https://www.techiedelight.com/find-ancestors-of-given-node-binary-tree/
		 *  /* Construct below tree
	         1
	       /   \
	      /     \
	     2      3
	      \    / \
	       4  5   6
	         / \
	        7   8
	    */
	 void printAncestors(BTNode root, BTNode descendant){
		 if(root == descendant || !isAncestor(root, descendant)){
			 System.out.println("no ancestors found");
		 }
	 }
	 
	 

 	boolean isAncestor(BTNode node, BTNode descendant){
 		if(node == null){
 			return false;
 		}
 		if((node.left != null && node.left.data == descendant.data) ||
 				(node.right != null && node.right.data == descendant.data)){
 			System.out.println(node.data);
 			return true;
 		} 
 		if( isAncestor(node.left, descendant) || isAncestor(node.right, descendant)){
 			System.out.println(node.data);
			return true;
 		}
 		return false;
 	}
		
	
	public static void main(String[] args) {
		BTNode node6 = new BTNode(6);
		BTNode node8 = new BTNode(8).addChildren
				(node6, new BTNode(7));
		BTNode root = new BTNode(1).addChildren(new BTNode(2).addChildren(new BTNode(4), new BTNode(5)),
												new BTNode(3).addChildren(node8, null));
																			//(new BTNode(6).addChildren(new BTNode(10), new BTNode(11)), new BTNode(7)), new BTNode(9)));
		BinaryTree binaryTree = new BinaryTree(root);
		System.out.println("");
		System.out.println(" binaryTree.maxWidth() : " + 	binaryTree.maxWidth());
		
		System.out.println("");
		System.out.println(" Node height = binaryTree.heightRecursive(node8) : " + 	binaryTree.heightRecursive(node8));
		System.out.println(" Tree height = binaryTree.heightRecursive(root) : " + 	binaryTree.heightRecursive(root));
		
		System.out.println("");
		System.out.println(" Node level using queue (BF) -  binaryTree.level(node8) : " + 	binaryTree.level(node8));
		System.out.println(" Node depth (node level -1)  -  binaryTree.depth(node8) : " + 	binaryTree.depth(node8));
		System.out.println(" Node depth recursive (DF)   - binaryTree.depthFromRecursive(root, node8) : " + 	binaryTree.depthFromRecursive(root, node8));
		System.out.println(" Depth of root = 0 ==> Node depth recursive (DF)   - binaryTree.depthFromRecursive(root, root) : " + 	binaryTree.depthFromRecursive(root, root));


		System.out.println("");
		System.out.println(" Tree height = binaryTree.heightRecursive(root) : " + 	binaryTree.heightRecursive(root));
		System.out.println(" Tree depth (depth of deapest leaf) = binaryTree.depth(node6) : " + 	binaryTree.depth(node6));
		System.out.println(" Tree depth (depth of deapest leaf) = binaryTree.depthFromRecursive(root, node6): " + 	binaryTree.depthFromRecursive(root, node6));
		System.out.println("");
		
		
		System.out.println("find Ancestors:");
	/* Construct below tree
         1
       /   \
      /     \
     2      3
      \    / \
       4  5   6
         / \
        7   8
    */
		BTNode node5 = new BTNode(5).addChildren(new BTNode(7), new BTNode(8));
		BTNode root02 = new BTNode(1).addChildren(new BTNode(2).addChildren(null, new BTNode(4)),
												new BTNode(3).addChildren(node5, new BTNode(6)));
		BinaryTree binaryTree02 = new BinaryTree(root02);
		System.out.println("binaryTree02.printAncestors(root02, new BTNode(7)):");
		binaryTree02.printAncestors(root02, new BTNode(7));
		System.out.println("binaryTree02.printAncestors(root02, new BTNode(4)):");
		binaryTree02.printAncestors(root02, new BTNode(4));
		System.out.println("binaryTree02.printAncestors(root02, new BTNode(1)):");
		binaryTree02.printAncestors(root02, new BTNode(1));
		System.out.println("binaryTree02.printAncestors(root02, new BTNode(100)):");
		binaryTree02.printAncestors(root02, new BTNode(100));

	
	}

}
