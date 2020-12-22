package Udemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Udemy.BinaryTree.BTNode;


/*  NOTE ON Binary Search tree:
 * in BST, every Node based on its key has a specific place in the tree (how we validate tree is BST)
 *  while in a general binary tree, we can place the Node as a child to any Node
 *  BST feature:
 *   a unique characteristic of BST:
	 *    FOR  left--node--right , allowed value ranges for left node and right node descendants:
	 *    node.left.key <  "node.left.right" descendants  < node.key
	 *         node.key <   "node.right.left" descendants < node.right.key   
	 *    "node.left.left"   descendants  < node.left.key
	 *    "node.right.right" descendants > node.right.key
 * 
 * */

/*  NOTE ON RECUSRION :
 *  MAKE SURE TO RETURN ANY INTERNAL RECURSIVE FUNCTION CALL! 
 * (otherwise the bottom recursive calls/values don't reach (get up) to the top)
 * A recursive function usually receive an argument to be processed and return a final value 
 * 	that bubbles(returned) up to the first caller.
 * https://stackoverflow.com/questions/33513358/how-to-save-the-return-value-of-a-recursive-function-in-a-variable-javascript
 * */
/*  {search, add}
 *   1) logic in node
 *   2) logic in tree
 *   
 *   {validate, } : more complex algorithms
 * */

/* NODE ON WHITEBOARDING:
 * ALWAYS leave enough space between lines and end of each method in case of any code changes happening
 * during debug or re-design
 * OTHERWISE:you end of with little space : increase chance of writing erroneous codes AND observers don not follow
 * */
public class BinaarySearchTree {
	
	static class BSTNode{
		int key;//in case case, BST is a set of keys : Set of keys ( key[] ) --> in Set data structure , order of insertion is not maintained!
		int DATA; // in this case, BST is a map with (key,DATA) pairs : map[key] = DATA;
		BSTNode left;
		BSTNode right;
		
		BSTNode(int k){
			key = k;
		}
		
		@Override
		public
		String toString(){
			return " key: " + key + " ,Data : " + DATA;
			
		}
		
		public BSTNode addChildrenNotBST(BSTNode l, BSTNode r){
			left = l;
			right = r;
			return this;
		}
		
		void addChild(int k){
			if( k <= key){//left child
				if(left == null){
					left = new BSTNode(k);
				}else{
					left.addChild(k);
				}
			}else { //k > key (right child)
				if(right == null){
					right = new BSTNode(k); 
				}else{
					right.addChild(k);
				}
			}
		}
		
		BSTNode searchNode(int k){
			if(k == key){
				return this;
			}
			if(k > key && right != null){//right
				return right.searchNode(k);
			}else if(k <= key && left != null){//left
				return left.searchNode(k);
			}else{
				return null;
			}
		}
		
	}

	BSTNode root;
	BinaarySearchTree(){
		//default ctr
	}
	
	//logic in Node
	void addLogicInNode(int key){
		if(root == null){//small problem
			root = new BSTNode(key);
		}else{//big problem
			root.addChild(key);
		}
	}
	
	BSTNode searchLogicInNode(int key){
		if(root == null){
			return null;
		}
		return root.searchNode(key);
	}
	
	// Logics in the tree 
	/*	https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
	 * */
	BSTNode searchLogicInTree(int key){
		return searchRecursive(root, key);
	}
	
	/*  NOTE ON RECUSRION :
	 *  MAKE SURE TO RETURN ANY INTERNAL RECURSIVE FUNCTION CALL! 
	 * (otherwise the bottom recursive calls/values don't reach (get up) to the top)
	 * A recursive function usually receive an argument to be processed and return a final value 
	 * 	that bubbles(returned) up to the first caller.
	 * */
	BSTNode searchRecursive(BSTNode node, int k){
		if(node == null){
			return null;//reached to the leaf and didn NOT find key
		}
		if(node.key == k){
			return node;//found the key
		}
		//delegating to children
		if(k > node.key){//right 
			return searchRecursive(node.right, k);
		}else{//left
			return searchRecursive(node.left, k);
		}
	}

	/*	https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
	 * */
	void addLogicInTree(int k){
		root = addRecursive(root, k); 
	}

	/*  NOTE ON RECUSRION :
	 *  MAKE SURE TO RETURN ANY INTERNAL RECURSIVE FUNCTION CALL! 
	 * (otherwise the bottom recursive calls/values don't reach (get up) to the top)
	 * A recursive function usually receive an argument to be processed and return a final value 
	 * 	that bubbles(returned) up to the first caller.
	 * */
	/* (returns each node - updated node)                           - BSTNode addRecursive(BSTNode node, int k)
	 * if we do not return the updated node, all updates get LOST!  - void addRecursive(BSTNode node, int k)  - WRONG!
	 * for each node, add the new node if node is null
	 * otherwise, delegates the process to children
	 * */
	BSTNode addRecursive(BSTNode node, int k){
		if(node == null){
			return new BSTNode(k);
		}
		if(k <= node.key){
			node.left = addRecursive(node.left, k);
		}else{
			node.right = addRecursive(node.right, k);
		}
		return node;
	}
	
	
	/* check if binary tree is a BST
	 * */
	
	/*  time: (N) extraSpace: O(N)
	 * Java Solution 3 - In-order traversal
		https://www.programcreek.com/2012/12/leetcode-validate-binary-search-tree-java/
	 * */
	public boolean validateLogicInOrderAndIfSorted(){
		List<Integer> nodekeys = new ArrayList<>();
		nodekeys = inOrderTraverse(root, nodekeys);
		System.out.println("inOrder : " + nodekeys);
		if(nodekeys.size() == 0 || nodekeys.size() == 1){
			return true;
		}
		int prevNode = nodekeys.get(0);
		for(int index = 1; index < nodekeys.size(); index++){
			if(nodekeys.get(index) < prevNode){
				return false;
			}
			prevNode = nodekeys.get(index);
		}
		return true;
	}
	public List<Integer> inOrderTraverse(BSTNode n, List<Integer> nodekeys){
		if(n == null){
			return nodekeys;
		}
		nodekeys = inOrderTraverse(n.left, nodekeys);
		nodekeys.add(n.key);
		nodekeys = inOrderTraverse(n.right, nodekeys);
		return nodekeys;
		/*  NOTE ON RECUSRION :
		 *  MAKE SURE TO RETURN ANY INTERNAL RECURSIVE FUNCTION CALL! 
		 * (otherwise the bottom recursive calls/values don't reach (get up) to the top)
		 * A recursive function usually receive an argument to be processed and return a final value 
		 * 	that bubbles(returned) up to the first caller.
		 * */
	}
	
	/*   time: (N^2)
	 * NOT optimized
	 * */
	public boolean validateLogicRecursiveNOTOptimized(){
		return validateNode(root);
	}
	/*A recursive function usually receive an argument to be processed and return a final value 
	 * that bubbles(returned) up to the first caller.*/
	private boolean validateNode(BSTNode node){
		if(node == null){//ALWAYS deal with NULL case as early as possible
			return true;
		}
		//validate Node first, then recursively validate descendants
		if( node.left != null && max(node.left) > node.key){
			return false;
		}
		if(node.right != null && min(node.right) < node.key){
			return false;
		}
		//recursively validate children and other descendants
		return validateNode(node.left) && validateNode(node.right);
		/*A recursive function usually receive an argument to be processed and return a final value 
		 * that bubbles(returned) up to the first caller.*/
	}
	private int min(BSTNode node){
		int min = node.key;
		//update min by left descendants
		if(node.left != null){
			int left = min(node.left);
			min = (left < min) ? left : min;
		}
		//update min by right descendants
		if(node.right != null){
			int right = min(node.right);
			min = (right < min) ? right : min;
		}	
		return min;
	}
	private int max(BSTNode node){
		int max = node.key;
		//update max by left descendants
		if(node.left != null){
			int left = max(node.left);
			max = (left > max) ? left : max;
		}
		//update max by right descendants
		if(node.right != null){
			int right = max(node.right);
			max = (right > max) ? right : max;
		}	
		return max;
	}
	
	/* a unique characteristic of BST:
	 *    FOR  left--node--right , allowed value ranges for left node and right node descendants:
	 *    node.left.key <  "node.left.right" descendants  < node.key
	 *         node.key <   "node.right.left" descendants < node.right.key   
	 *    "node.left.left"   descendants  < node.left.key
	 *    "node.right.right" descendants > node.right.key
	 *    
	 *    OPTIMIZED!
	 *    METHOD 3 (Correct and Efficient):
	 *    https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
	 * */
	public boolean validateDescendantsRanges(BSTNode node, int minRange, int maxRange){
		if(node == null) {
			return true;
		}
		if(node.key < minRange || node.key > maxRange) {
			return false;
		}
		return validateDescendantsRanges(node.left, minRange, node.key) &&
				validateDescendantsRanges(node.right, node.key, maxRange);
	}
	public boolean validateLogicUsingAllowedValueRange(){
		return validateDescendantsRanges(root, Integer.MIN_VALUE, Integer.MAX_VALUE);//Initialize the recursive call (starting from small problem(here root)
	}

	//utility functions
	void traverseBFandPrint(){
		int width = 1;
		Queue<BSTNode> q = new LinkedList<>();
		q.add(root);
		System.out.println(root.key);//level 1 (root) 
		while(!q.isEmpty()){
			if(width == 0){//all nodes are next level nodes
				width = q.size();
				String s = "";
				for(BSTNode n : q){
					s+= n.key + " ";
				}
				System.out.println(s);
			}
			BSTNode node = q.remove();
			width--;
			if(node.left != null){
				q.add(node.left);
			}
			if(node.right != null){
				q.add(node.right);
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Order of insertion affects the binary search tree higth (from a balanced tree (log(N)) to a full linkedList (N)");
		System.out.println();

		BinaarySearchTree bstRighttLinkedList = new BinaarySearchTree();
		bstRighttLinkedList.addLogicInNode(1);
		bstRighttLinkedList.addLogicInNode(2);
		bstRighttLinkedList.addLogicInNode(3);
		bstRighttLinkedList.addLogicInNode(4);
		bstRighttLinkedList.addLogicInNode(5);
		bstRighttLinkedList.addLogicInNode(6);
		bstRighttLinkedList.addLogicInNode(7);
		bstRighttLinkedList.addLogicInNode(8);
		System.out.println("bstRighttLinkedList.traverseBFandPrint()");
		bstRighttLinkedList.traverseBFandPrint();
		System.out.println();

		System.out.println("bstLefttLinkedList.traverseBFandPrint()");
		BinaarySearchTree bstLefttLinkedList = new BinaarySearchTree();
		bstLefttLinkedList.addLogicInTree(8);
		bstLefttLinkedList.addLogicInTree(7);
		bstLefttLinkedList.addLogicInTree(6);
		bstLefttLinkedList.addLogicInTree(5);
		bstLefttLinkedList.addLogicInTree(4);
		bstLefttLinkedList.addLogicInTree(3);
		bstLefttLinkedList.addLogicInTree(2);
		bstLefttLinkedList.addLogicInTree(1);
		bstLefttLinkedList.traverseBFandPrint();
		System.out.println();

		System.out.println("bstBalanaced.traverseBFandPrint()");
		BinaarySearchTree bstBalanaced = new BinaarySearchTree();
		bstBalanaced.addLogicInNode(6);
		bstBalanaced.addLogicInNode(8);
		bstBalanaced.addLogicInNode(9);
		bstBalanaced.addLogicInNode(3);
		bstBalanaced.addLogicInNode(2);
		bstBalanaced.addLogicInNode(4);
		bstBalanaced.addLogicInNode(5);
		bstBalanaced.addLogicInNode(7);
		bstBalanaced.addLogicInNode(1);
		bstBalanaced.traverseBFandPrint();
		System.out.println();

		
		System.out.println("search BST");
		System.out.println("	bstBalanaced.searchLogicInTree(6)" +  bstBalanaced.searchLogicInTree(6).toString());
		System.out.println("	bstBalanaced.searchLogicInTree(9)" +  bstBalanaced.searchLogicInTree(9).toString());
		System.out.println("	bstBalanaced.searchLogicInTree(17)" +  bstBalanaced.searchLogicInTree(17));
		System.out.println();

		System.out.println("	bstBalanaced.searchLogicInNode(6)" +  bstBalanaced.searchLogicInNode(6));
		System.out.println("	bstBalanaced.searchLogicInNode(9)" +  bstBalanaced.searchLogicInTree(9).toString());
		System.out.println("	bstBalanaced.searchLogicInNode(17)" +  bstBalanaced.searchLogicInTree(17));
		System.out.println();

		
		System.out.println("validate BST");
		System.out.println("validateLogicRecursiveNOTOptimized : time: (N^2) extraSpace: O(h) call stack");
		System.out.println("validateLogicInOrderAndIfSorted : time: (N) extraSpace: O(N)");
		System.out.println("validateLogicUsingAllowedValueRange  (Optimized) : time: O(N) extraSpace: O(h) call stack");
		
		BSTNode n6 = new BSTNode(6).addChildrenNotBST(new BSTNode(4).addChildrenNotBST(new BSTNode(5), null),
													  new BSTNode(10).addChildrenNotBST(new BSTNode(7), new BSTNode(9)) );
		BSTNode n2 = new BSTNode(2).addChildrenNotBST(new BSTNode(1), null);
		BinaarySearchTree treeNOTbst = new BinaarySearchTree();
		treeNOTbst.root = new BSTNode(3).addChildrenNotBST(n2, n6);
		System.out.println("treeNOTbst");
		treeNOTbst.traverseBFandPrint();
		System.out.println("treeNOTbst.validateLogicRecursiveNOTOptimized() :" + treeNOTbst.validateLogicRecursiveNOTOptimized());
		System.out.println("treeNOTbst.validateLogicInOrderAndIfSorted() :" + treeNOTbst.validateLogicInOrderAndIfSorted());
		System.out.println("treeNOTbst.validateLogicUsingAllowedValueRange() :" + treeNOTbst.validateLogicUsingAllowedValueRange());
		System.out.println();
		
		
		BSTNode n12 = new BSTNode(12).addChildrenNotBST(new BSTNode(11), 
															new BSTNode(20).addChildrenNotBST(
																						new BSTNode(17), new BSTNode(99)));
		BSTNode n0 = new BSTNode(0).addChildrenNotBST(new BSTNode(-1).addChildrenNotBST(null, new BSTNode(15)), 
													  new BSTNode(4));
		BinaarySearchTree treeNOTbstUdemy = new BinaarySearchTree();
		treeNOTbstUdemy.root = new BSTNode(10).addChildrenNotBST(n0, n12);
		System.out.println("treeNOTbstUdemy");
		treeNOTbstUdemy.traverseBFandPrint();
		System.out.println("treeNOTbstUdemy.validateLogicRecursiveNOTOptimized() :" + treeNOTbstUdemy.validateLogicRecursiveNOTOptimized());
		System.out.println("treeNOTbstUdemy.validateLogicInOrderAndIfSorted() :" + treeNOTbstUdemy.validateLogicInOrderAndIfSorted());
		System.out.println("treeNOTbstUdemy.validateLogicUsingAllowedValueRange() :" + treeNOTbstUdemy.validateLogicUsingAllowedValueRange());
		System.out.println();
		
		
		BSTNode node5 = new BSTNode(5).addChildrenNotBST(new BSTNode(3), 
				new BSTNode(8).addChildrenNotBST(
											new BSTNode(4), new BSTNode(11)));
		BSTNode node20 = new BSTNode(20).addChildrenNotBST(
										new BSTNode(9), new BSTNode(25));
		BinaarySearchTree treeNOTbstInAllLevels = new BinaarySearchTree();
		treeNOTbstInAllLevels.root = new BSTNode(10).addChildrenNotBST(node5, node20);
		treeNOTbstInAllLevels.traverseBFandPrint();
		System.out.println("treeNOTbstInAllLevels.validateLogicRecursiveNOTOptimized() :" + treeNOTbstInAllLevels.validateLogicRecursiveNOTOptimized());
		System.out.println("treeNOTbstInAllLevels.validateLogicInOrderAndIfSorted() :" + treeNOTbstInAllLevels.validateLogicInOrderAndIfSorted());
		System.out.println("treeNOTbstInAllLevels.validateLogicUsingAllowedValueRange() :" + treeNOTbstInAllLevels.validateLogicUsingAllowedValueRange());
		System.out.println();
		
		System.out.println("bstBalanaced");
		bstBalanaced.traverseBFandPrint();
		System.out.println("bstBalanaced.validateLogicRecursiveNOTOptimized() :" + bstBalanaced.validateLogicRecursiveNOTOptimized());
		System.out.println("bstBalanaced.validateLogicInOrderAndIfSorted() :" + bstBalanaced.validateLogicInOrderAndIfSorted());
		System.out.println("bstBalanaced.validateLogicUsingAllowedValueRange() :" + bstBalanaced.validateLogicUsingAllowedValueRange());
	}

}
