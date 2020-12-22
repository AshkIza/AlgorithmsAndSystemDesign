package Udemy;

/*Given a Binary Tree and a key, write a function that prints all the ancestors of the key in the 
 * given binary tree.For example, if the given tree is following Binary Tree and key is 7,
 *  then your function should print 4, 2 and 1.
              1
            /   \
          2      3
        /  \
      4     5
     /
    7
 * https://www.geeksforgeeks.org/print-ancestors-of-a-given-node-in-binary-tree/
 * */

public class printAncestorsBTree{
	
	public static class Node{
		int key;
		Node left;
		Node right;
		Node(int k){
			key = k;
			left = null;
			right = null;
		}
	}
	
	private static boolean AncestorRecursive(Node n, int key){
		if(n == null) {
			return false;//we have reach to the leaves (impossible to be an ancestor)
		}
		//base case (small problem)
		if(n.key == key){
			System.out.println(" ");
			return false;//Node is the key (no Ancestor found)
		}
		//big problem
		if((n.left!= null && n.left.key == key) || (n.right!= null && n.right.key == key)){
			System.out.println(n.key);
			return true;//parent
		}
		boolean left = AncestorRecursive(n.left, key);
		boolean right = AncestorRecursive(n.right, key);
		if(left || right){//grandparent or earlier ancestor
			System.out.println(n.key);
			return true;
		}
		return false;//wrong branch 
	}
	
	public static void main(String[] args) {
		//case 1
		Node  root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.left.left.left = new Node(7);
		AncestorRecursive(root, 7);
		
		//case 2
		System.out.println("*****");
		AncestorRecursive(root, 1);
		
		//case 3
		System.out.println("*****");
		AncestorRecursive(root, 3);
		
		//case 4
		System.out.println("*****");
		AncestorRecursive(root, 4);
		
		//case 5
		System.out.println("*****");
		root.left.left.right = new Node(8);
		root.left.right.left = new Node(9);
		root.left.right.right = new Node(10);
		root.left.right.right.left = new Node(11);
		root.left.right.right.right = new Node(12);
		AncestorRecursive(root, 12);
	}
}
