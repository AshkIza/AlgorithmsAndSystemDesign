package leetcode.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import leetcode.Trees.TreeNode;

/*  Given a binary tree, return the inorder traversal of its nodes' values.

		Example:
		
		Input: [1,null,2,3]
		   1
		    \
		     2
		    /
		   3
		
		Output: [1,3,2]
		Follow up: Recursive solution is trivial, could you do it iteratively?
		
		Solution: https://www.techiedelight.com/inorder-tree-traversal-iterative-recursive/
 * 
 * */
public class BinaryTreeInorderTraversalRecursiveIterative {
	
	
    public static List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        return inorderR(root, nodes);
        
    }
    
    public static List<Integer> inorderR(TreeNode root, List<Integer> nodes){
        if(root == null){
            return nodes;
        }
        nodes = inorderR(root.left, nodes);
        nodes.add(root.val);
        nodes = inorderR(root.right, nodes);
        return nodes;
    }
    
    /* we use a node to iterate through the left and right children   iterate : node = node.left or node = node.right
     * we use a stack to save parents                                 save node   stack.push(node) 
     *                                                                traverese node (print it) print(node.val)
     *		 except root, every node is its parents left child or its parent's right child
     *		so, we first keep iterating through left children ( and put them(sub-parents) in to the stack)
     *        once, no more left children is remaining, we go back and look at the parents from the stack, 
     *        wheneve we pop a parent from the stack, visit it (print it), and then iterate through its right children
     * */
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        Stack<TreeNode> parents = new Stack<>();
        TreeNode node = root;
        while(node != null || !parents.isEmpty()){
        	if(node != null){
        		parents.push(node);
        		node = node.left;
        	}else{ // up to here, we have made sure no left children is remaining
        		node = parents.pop();//now time to visit the parent
        		nodes.add(node.val);
        		node = node.right;//once left children and parent are visited, time to iteratre through right children
        	}
        }
        return nodes;
    }
	public static void main(String[] args) {
		// in order recursive
		System.out.println("in order recursive");
		TreeNode root0 = new TreeNode(1);
		root0.right = new TreeNode(2);
		root0.right.left = new TreeNode(3);
		System.out.println(Arrays.toString(inorderTraversalRecursive(root0).toArray()));
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		root.right.left.left = new TreeNode(7);
		root.right.left.right = new TreeNode(8);
		System.out.println(Arrays.toString(inorderTraversalRecursive(root).toArray()));

		
		// in order iterative
		System.out.println("in order iterative");
		System.out.println(Arrays.toString(inorderTraversalIterative(root0).toArray()));
		System.out.println(Arrays.toString(inorderTraversalIterative(root).toArray()));
	}

}
