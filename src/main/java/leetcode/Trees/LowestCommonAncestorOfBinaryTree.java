package leetcode.Trees;

import java.util.ArrayList;
import java.util.List;

import leetcode.Trees.TreeNode;

/*   Lowest Common Ancestor of a Binary Tree
	Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
	
	According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
	
	Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
	
	
	 
	
	Example 1:
	
	Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
	Output: 3
	Explanation: The LCA of nodes 5 and 1 is 3.
	Example 2:
	
	Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
	Output: 5
	Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
	 
	
	Note:
	
	All of the nodes' values will be unique.
	p and q are different and both values will exist in the binary tree.
	
	solution : https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
	
	
	Always test your logic for all use cases: 
		 use cases = general cases (depending on the problem) + corner cases
		 corner cases : node = leaf, node = root, node = null are corner cases in all tree problems  
 
 * 
 * */
public class LowestCommonAncestorOfBinaryTree {
	
	
	/* optimum solution : using 1 tree travesral (instead of 2) & no extra space to save ancestors
	 * o(n) time / O(1) extra space
	 * 
	 *  small problem (when the recursion stops) : 
	 *    node is LCA(p,q)  when either
	 *      a) p is ancestor of node.left/ q is ancestor of node.right (no nood to go deeper)
	 *      b) p = node and q is ancestor of node.left or node.right
	 *    otherwise:
	 *      keep going down, and if cannot find LCA on that sub-tree, return null
	 *      we keep going down if LCA of that subtree is NOT null
	 *      
	 *  Always test your logic for all use cases:
	 *    node is option a
	 *    node is option b 
	 *    node is not deepest ancester, logic should try LCA(node.left) & LCA(node.right)
	 *    node is null
	 *    node is leaf
	 *    
	 * NOTE: node = leaf, node = root, node = null are corner cases in all tree problems   
	 * */
	public static TreeNode lowestCommonAncestorOptimum(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null){ //return null if node is null (definitely not a common ancestor)
			return null;
		}
		if(root == p && 
				(hasChild(root.left, q) || hasChild(root.right, q))){
			return root;
		}
		if(root == q && 
				(hasChild(root.left, p) || hasChild(root.right, p))){
			return root;
		}
		
		if( (hasChild(root.left, p) && hasChild(root.right, q)) ||
			(hasChild(root.left, q) && hasChild(root.right, p))){
			return root;
		}
		
		TreeNode leftSearch = lowestCommonAncestorOptimum(root.left, p, q);
		if(leftSearch != null){
				return leftSearch;
		}
		
		TreeNode rightSearch = lowestCommonAncestorOptimum(root.right, p, q);
		if(rightSearch != null){
			return rightSearch;
		}		
		return null;//return null if node is leaf or not a common ancestor
	}
	
	public static boolean hasChild(TreeNode node, TreeNode p) {
		if(node == null){
			return false;
		}
		if(node == p){
			return true;
		}
		
		return hasChild(node.left, p) ||  hasChild(node.right, p);
	}

	
	
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		List<TreeNode> first = ancestor(root, p.val, null);
		List<TreeNode> second = ancestor(root, q.val, null);
		int M = first.size();
		int N = second.size();
		int min = Math.min(M, N);
		while(min >=1){
			if( first.get(M - min).val == second.get(N - min).val){
				return first.get(M - min);
			}
			min--;
		}
		return root;
	}
	
	public static List<TreeNode> ancestor(TreeNode root, int val, List<TreeNode> nodes) {
		if (root == null){
			return nodes;
		}
		if(root.val == val){
			nodes = new ArrayList<TreeNode>();
			nodes.add(root);
			return nodes;
		}
		
		List<TreeNode> left = ancestor(root.left, val, nodes);
		List<TreeNode> right = ancestor(root.right, val, nodes);
		
		if(left != null){
			left.add(root);
			return left;
		}
		
		if(right != null){
			right.add(root);
			return right;
		}
		return null;
	}


	public static void main(String[] args) {
		
		System.out.println("  Tree = Recursive (90% of problems) " );
		System.out.println("  if using Iterative for tree -> use Queue aka LinkedList \n\n" );
		
		
		TreeNode root = new TreeNode(3);
	    root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);  
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);
		
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);  
		
		root.left.right.left.left = new TreeNode(10);
		root.left.right.left.right = new TreeNode(12);
		
		TreeNode lca01 = lowestCommonAncestor(root,  root.left, root.right);
		TreeNode lca02 = lowestCommonAncestor(root,  root.left, root.left.right.right);
		TreeNode lca03 = lowestCommonAncestor(root,  root.left.left, root.left.right.right);
		TreeNode lca04 = lowestCommonAncestor(root,  root.left.right.left, root.left.right.right);
		TreeNode lca05 = lowestCommonAncestor(root,  root.left.right.left.left, root.left.right.right);

		
		System.out.println("LowestCommonAncestorOfBinaryTree(5,1) : " + lca01.val);
		System.out.println("LowestCommonAncestorOfBinaryTree(5, 4) : " + lca02.val);
		System.out.println("LowestCommonAncestorOfBinaryTree(6, 4) : " + lca03.val);
		System.out.println("LowestCommonAncestorOfBinaryTree(7, 4) : " + lca04.val);
		System.out.println("LowestCommonAncestorOfBinaryTree(10, 4) : " + lca05.val);
		
		
		System.out.println("\nLowestCommonAncestorOfBinaryTree - optimum solution (only one tree traversal)");
		TreeNode lca01Op = lowestCommonAncestorOptimum(root,  root.left, root.right);
		TreeNode lca02Op = lowestCommonAncestorOptimum(root,  root.left, root.left.right.right);
		TreeNode lca03Op = lowestCommonAncestorOptimum(root,  root.left.left, root.left.right.right);
		TreeNode lca04Op = lowestCommonAncestorOptimum(root,  root.left.right.left, root.left.right.right);
		TreeNode lca05Op = lowestCommonAncestorOptimum(root,  root.left.right.left.left, root.left.right.right);

		
		System.out.println("LowestCommonAncestorOfBinaryTree(5,1) : " + lca01Op.val);
		System.out.println("LowestCommonAncestorOfBinaryTree(5, 4) : " + lca02Op.val);
		System.out.println("LowestCommonAncestorOfBinaryTree(6, 4) : " + lca03Op.val);
		System.out.println("LowestCommonAncestorOfBinaryTree(7, 4) : " + lca04Op.val);
		System.out.println("LowestCommonAncestorOfBinaryTree(10, 4) : " + lca05Op.val);
		
		
		System.out.println("\n\nAlways test your logic for all use cases: " );
		System.out.println(" use cases = general cases (depending on the problem) + corner cases" );
		System.out.println(" corner cases : node = leaf, node = root, node = null are corner cases in all tree problems  " );



	}

}
