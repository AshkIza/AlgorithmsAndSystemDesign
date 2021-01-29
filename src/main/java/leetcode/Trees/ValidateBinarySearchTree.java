package leetcode.Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static leetcode.Trees.TreeNode.*;


public class ValidateBinarySearchTree {
	
	   static class Node {
	        Integer value;
	        void set(int val){
	            this.value = val;
	        }
	    }
	    public static boolean isValidBST(TreeNode root) {
	        if(root == null || isLeaf(root)) return true;
	        Node lastnode = new Node();
	        return inorder(root, lastnode);
	    }
	    static boolean inorder(TreeNode node, Node lastnode) {
	        if(node == null) return true;
	        if(!inorder(node.left, lastnode)) return false;
	        if(lastnode.value!=null && lastnode.value >= node.val) return false;
	        lastnode.set(node.val);
	        return inorder(node.right, lastnode);
	    }
	    static boolean isLeaf(TreeNode node){
	        return node.left == null && node.right == null;
	    }
	    
	    
	     
	 
	public static void main(String[] args) {
		
		System.out.println("  Tree = Recursive (90% of problems) " );
		System.out.println("  if using Iterative for tree -> use Queue aka LinkedList \n\n" );
				
		TreeNode root_case01 =  createTree(Stream.of(1,1));
		System.out.println("isValidBST() " + isValidBST(root_case01));
		
		
		TreeNode root_case02 = createTree(Stream.of(5,1,4,null,null,3,6));
		System.out.println("isValidBST() " + isValidBST(root_case02));
		
		TreeNode root_case03 = createTree(Stream.of(3,1,5,null,null,4,6));
		System.out.println("isValidBST() " + isValidBST(root_case03));
		
		

	}

}
