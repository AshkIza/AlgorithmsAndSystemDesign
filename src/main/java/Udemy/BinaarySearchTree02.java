package Udemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*  * more complex algorithms
 * post/pre order traversal (DF)/ reverse Inorder
 * find k-th maximum/minimum
 * find node's ancestors
	

Depth First Traversals:
(a) Inorder (Left, Node, Right) : returns sorted (ascending) list of nodes
(b) Preorder (Node, Left, Right) : returns root first, then all left parents (and most left leaf), then rest of nodes
(c) Postorder (Left, Right, Node) : returns root as last elements, first element (min value- left most leaf)

https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
Preorder: used in pre-fix expression of an expression tree  & Copy tree
postorder: used in post-fix expression of an expression tree & Delete tree
 * */
public class BinaarySearchTree02 extends BinaarySearchTree{
	
	
	/* pre-order :  Node - left - right
	 * fast way to find all left parents ( from root down to last left leaf [min value]), root first element
	 * */
	List<Integer> preOrderRecursive(BSTNode node, List<Integer> nodes){
		if(node == null){
			return nodes;
		}
		nodes.add(node.key);//node
		nodes = preOrderRecursive(node.left, nodes);//left
		nodes = preOrderRecursive(node.right, nodes);//right
		return nodes;
	}
	List<Integer> preOrderDFTraverse(){
		List<Integer> nodes = new ArrayList<>();
		return preOrderRecursive(root, nodes);
	}
	
	
	
	/* pre-order :  left - right - Node 
	 *  fastest way to reach  the most left leaf (min value) : 
	 *  first element is min value, next elements (no ordering)
	 *  root comes as last element
	 * */
	List<Integer> postOrderRecursive(BSTNode node, List<Integer> nodes){
		if(node == null){
			return nodes;
		}
		nodes = postOrderRecursive(node.left, nodes);//left
		nodes = postOrderRecursive(node.right, nodes);//right
		nodes.add(node.key);//node
		return nodes;
	}
	List<Integer> postOrderDFTraverse(){
		List<Integer> nodes = new ArrayList<>();
		return postOrderRecursive(root, nodes);
	}
	
	/*          Inorder: left-node-right,       (sorted ascending)
	 *  reverse Inorder:  right-node-left       (sorted descending)
	 *  
	 *  http://analgorithmaday.blogspot.com/2011/06/reverse-in-order-traversal-of-bst.html
	 * */
	List<Integer> reverseInorderRecursive(BSTNode node, List<Integer> nodes){
		if(node == null){
			return nodes;
		}
		nodes = reverseInorderRecursive(node.right, nodes);//right
		nodes.add(node.key);//node
		nodes = reverseInorderRecursive(node.left, nodes);//left
		return nodes;
	}
	List<Integer> reverseInOrderDFTraverse(){
		List<Integer> nodes = new ArrayList<>();
		return reverseInorderRecursive(root, nodes);
	}
	
	List<Integer> inOrderDFTraverse(){
		List<Integer> nodes = new ArrayList<>();
		return inOrderTraverse(root, nodes);
	}
	
	
	/*		https://www.techiedelight.com/find-kth-smallest-largest-element-bst/
	 * */
	int findKthMinimum(int k){
		BSTNode foundNode =  findKthMinimumRecursive(root, k, new BSTNode(0));
		if(foundNode != null){
			return  foundNode.key;
		}
		return Integer.MIN_VALUE;
	}
	BSTNode findKthMinimumRecursive(BSTNode node, int k, BSTNode objCounter){
		if(node == null){
			return null;
		}
		BSTNode fNode = findKthMinimumRecursive (node.left, k , objCounter);
		if(fNode != null){
			return fNode;
		}
		objCounter.DATA++;
		if(objCounter.DATA == k){
			return node;
		}
	    fNode = findKthMinimumRecursive (node.right, k , objCounter);
	    if(fNode != null){
			return fNode;
		}
	    return null;
	}

		
	/* reverse- inorder and keep counting till reach the k-th element
	 * */
	int findKthMaximum(int k){
		BSTNode foundNode =  findKthMaximumRecursive(root, k, new BSTNode(0));
		if(foundNode != null){
			return  foundNode.key;
		}
		return Integer.MIN_VALUE;
	}
	BSTNode findKthMaximumRecursive(BSTNode node, int k, BSTNode objCounter){
		if(node == null){
			return null;
		}
		BSTNode fNode = findKthMaximumRecursive (node.right, k , objCounter);
		if(fNode != null){
			return fNode;
		}
		objCounter.DATA++;
		if(objCounter.DATA == k){
			return node;
		}
	    fNode = findKthMaximumRecursive (node.left, k , objCounter);
	    if(fNode != null){
			return fNode;
		}
	    return null;
	}
	
	
	/* show difference between primitive and objects as passing arguments to Recursive calls 
		(USE OBJECTS if need the passing variable to keep updated in each recursive call)
	*/
	private static void RecursiveCallDownTheCallStackAndComeUP(BSTNode node, int intcounter, BSTNode objectCounter, AtomicInteger atomic){
		if(node == null){
			return;
		}
		RecursiveCallDownTheCallStackAndComeUP(node.left, intcounter, objectCounter, atomic);//left
		System.out.println("  node : " + node.key + "   counter :" + intcounter + " objectCounter.DATA : " + objectCounter.DATA + " atomic : " + atomic.get());
		intcounter++;
		objectCounter.DATA++;
		atomic.incrementAndGet();
		RecursiveCallDownTheCallStackAndComeUP(node.left, intcounter, objectCounter, atomic);//left
	}
	
	static void incrementByTen(int primitiveData, BSTNode objectData){
		primitiveData+=10;
		objectData.DATA+=10;
	}

	
	
	
	

	public static void main(String[] args) {
		BinaarySearchTree02 bstBalanaced = new BinaarySearchTree02();
		bstBalanaced.addLogicInNode(6);
		bstBalanaced.addLogicInNode(8);
		bstBalanaced.addLogicInNode(9);
		bstBalanaced.addLogicInNode(3);
		bstBalanaced.addLogicInNode(2);
		bstBalanaced.addLogicInNode(4);
		bstBalanaced.addLogicInNode(5);
		bstBalanaced.addLogicInNode(7);
		bstBalanaced.addLogicInNode(1);
		System.out.println("bstBalanaced.traverseBFandPrint()");
		bstBalanaced.traverseBFandPrint();
		System.out.println("bstBalanaced.preOrderDFTraverse()" + bstBalanaced.preOrderDFTraverse());
		System.out.println("  [6, 3, 2, 1] are left parents including root and moset left leaf");
		System.out.println("bstBalanaced.postOrderDFTraverse()" + bstBalanaced.postOrderDFTraverse());
		System.out.println("  fastest way to reach  the most left leaf (min value = 1), root  ");
		System.out.println("bstBalanaced.reverseInOrderDFTraverse()" + bstBalanaced.reverseInOrderDFTraverse());
		System.out.println("  reverse inorder (right-node-left) retrun sorted descending");
		System.out.println("bstBalanaced.inOrderDFTraverse()" + bstBalanaced.inOrderDFTraverse());
		System.out.println("  inorder (left-node-right) retrun sorted ascending");
		System.out.println("");
		
		BinaarySearchTree02 bst02 = new BinaarySearchTree02();
		bst02.addLogicInTree(20);
		bst02.addLogicInTree(10);
		bst02.addLogicInTree(5);
		bst02.addLogicInTree(4);
		bst02.addLogicInTree(15);
		bst02.addLogicInTree(12);
		bst02.addLogicInTree(17);
		bst02.addLogicInTree(8);
		bst02.addLogicInTree(30);
		bst02.addLogicInTree(25);
		bst02.addLogicInTree(31);
		System.out.println("bst02.traverseBFandPrint()");
		bst02.traverseBFandPrint();
		System.out.println("bst02.preOrderDFTraverse()" + bst02.preOrderDFTraverse());
		System.out.println("  [20, 10, 5, 4] are left parents including root and moset left leaf");
		System.out.println("bst02.postOrderDFTraverse()" + bst02.postOrderDFTraverse());
		System.out.println("  fastest way to reach  the most left leaf (min value = 4) : ");
		System.out.println("bst02.reverseInOrderDFTraverse()" + bst02.reverseInOrderDFTraverse());
		System.out.println("  reverse inorder (right-node-left) retrun sorted descending");
		System.out.println("bst02.inOrderDFTraverse()" + bst02.inOrderDFTraverse());
		System.out.println("  inorder (left-node-right) retrun sorted ascending");		
		System.out.println("");

		// set vs add (List interface)
		System.out.println("List add(index, element) vs set(index, element)");
		List<Integer> javaList = new ArrayList();
		javaList.add(1);javaList.add(2);javaList.add(3);javaList.add(4);javaList.add(5);
		System.out.println("javaList : " + javaList);
		System.out.println(" javaList.set(1, 99)");
		javaList.set(1, 99);
		System.out.println("javaList : " + javaList);
		System.out.println(" javaList.add(1, -55)");
		javaList.add(1, -55);
		System.out.println("javaList : " + javaList);
		System.out.println("");

	    //passing variables (primitive vs object) as argument in recursive functions
		System.out.println("   how passing PRIMITIVE variables (int instead of AtomicInteger) as recursive function argument is dangerour");
		System.out.println("     variable gets updated till call stack rolls back from inner calls backs:");
		System.out.println("     (when inOrder traversal comes back from all left nodes to parent");
		System.out.println(" ==> TO MAKE SURE VALUES KEEP get UPDATED DURING RECUSRSIVE CALLS, "
								+ "ALWAY RETURN UPDATED VALUE AS THE RECURSIVE RETURN VALUE OR USE OBJECTS VALUES (NOT PRIMITIVE VALUES) AS FUNCTION ARGUMENTS");
		System.out.println("               (return values, DO NOT use PRIMITIVE values as funtion arguments)");
		bst02.traverseBFandPrint();
		RecursiveCallDownTheCallStackAndComeUP(bst02.root, 0, new BSTNode(0), new AtomicInteger(0));
		
		System.out.println();
		int primitveData = 0;
		BSTNode objectData = new BSTNode(0);
		System.out.println("  primitiveData and objectData definined out of function, then pass into the function");
		System.out.println("  function internal modification changes Objects but not primitive data passed in");
		System.out.println("primitiveData : " + primitveData + " objectData.DATA " + objectData.DATA);
		incrementByTen(primitveData, objectData);
		System.out.println("incrementByTen(primitveData, objectData)");
		System.out.println("primitiveData : " + primitveData + " objectData.DATA " + objectData.DATA);
		System.out.println("");
		System.out.println("");

		
		System.out.println(" find k-tk min/max recusrsive using ObjectCounter as a recursive function argument");
		//find k-th minimum   ( in-order and find k-th traverse)
		System.out.println("bst02.inOrderDFTraverse()" + bst02.inOrderDFTraverse());
		System.out.println("bst02.findKthMinimum(1): " + bst02.findKthMinimum(1));
		System.out.println("bst02.findKthMinimum(2): " + bst02.findKthMinimum(2));
		System.out.println("bst02.findKthMinimum(3): " + bst02.findKthMinimum(3));
		System.out.println("bst02.findKthMinimum(4): " + bst02.findKthMinimum(4));
		System.out.println("bst02.findKthMinimum(5): " + bst02.findKthMinimum(5));
		System.out.println("bst02.findKthMinimum(6): " + bst02.findKthMinimum(6));
		System.out.println("bst02.findKthMinimum(7): " + bst02.findKthMinimum(7));
		System.out.println("bst02.findKthMinimum(8): " + bst02.findKthMinimum(8));
		System.out.println("bst02.findKthMinimum(9): " + bst02.findKthMinimum(9));
		System.out.println("bst02.findKthMinimum(10): " + bst02.findKthMinimum(10));
		System.out.println("bst02.findKthMinimum(11): " + bst02.findKthMinimum(11));
		System.out.println("bst02.findKthMinimum(12): " + bst02.findKthMinimum(12));



		System.out.println("");
		//find k-th maximum   ("reverse" in order and find k-th traverse)
		System.out.println("bst02.reverseInOrderDFTraverse()" + bst02.reverseInOrderDFTraverse());
		System.out.println("bst02.findKthMaximum(1): " + bst02.findKthMaximum(1));
		System.out.println("bst02.findKthMaximum(2): " + bst02.findKthMaximum(2));
		System.out.println("bst02.findKthMaximum(3): " + bst02.findKthMaximum(3));
		System.out.println("bst02.findKthMaximum(4): " + bst02.findKthMaximum(4));
		System.out.println("bst02.findKthMaximum(5): " + bst02.findKthMaximum(5));
		System.out.println("bst02.findKthMaximum(6): " + bst02.findKthMaximum(6));
		System.out.println("bst02.findKthMaximum(7): " + bst02.findKthMaximum(7));
		System.out.println("bst02.findKthMaximum(8): " + bst02.findKthMaximum(8));
		System.out.println("bst02.findKthMaximum(9): " + bst02.findKthMaximum(9));
		System.out.println("bst02.findKthMaximum(10): " + bst02.findKthMaximum(10));
		System.out.println("bst02.findKthMaximum(11): " + bst02.findKthMaximum(11));
		System.out.println("bst02.findKthMaximum(12): " + bst02.findKthMaximum(12));
	}

}
