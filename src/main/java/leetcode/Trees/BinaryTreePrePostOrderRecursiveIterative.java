package leetcode.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import leetcode.Trees.TreeNode;

/*
 * https://www.techiedelight.com/preorder-tree-traversal-iterative-recursive/
 * 
 * */
public class BinaryTreePrePostOrderRecursiveIterative {
	


    public static List<Integer> preOrderRecursive(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        return preOrderR(root, nodes);
        
    }
    public static List<Integer> preOrderR(TreeNode root, List<Integer> nodes){
        if(root == null){
            return nodes;
        }
        nodes.add(root.val);//node
        nodes = preOrderR(root.left, nodes);//left
        nodes = preOrderR(root.right, nodes);//right
        return nodes;
    }
    
    public static List<Integer> preOrderIterative(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        Stack<TreeNode> rightChildren = new Stack<>();
        TreeNode node = root;
        while(node != null || !rightChildren.isEmpty()){
        	if(node !=  null){
        		nodes.add(node.val);
        		if(node.right != null){
        			rightChildren.push(node.right);
        		}
        		node = node.left;
        	}else{
        		node = rightChildren.pop();
        	}
        }
        
        return nodes;
    }
    
    
	public static void main(String[] args) {
		TreeNode root0 = new TreeNode(1);
		root0.right = new TreeNode(2);
		root0.right.left = new TreeNode(3);
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		root.right.left.left = new TreeNode(7);
		root.right.left.right = new TreeNode(8);
		
		TreeNode root02 = new TreeNode(1);
		root02.left = new TreeNode(2);
		root02.right = new TreeNode(3);

		root02.left.left = new TreeNode(4);
		root02.left.right = new TreeNode(5);
		
		root02.left.left.left = new TreeNode(7);
		root02.left.left.right = new TreeNode(8);
		
		root02.left.left.left.left = new TreeNode(15);
		root02.left.left.left.left.left = new TreeNode(16);
		
		root02.right.left = new TreeNode(6);
		root02.right.left.left = new TreeNode(9);
		root02.right.left.right = new TreeNode(10);

		root02.right.left.right.right = new TreeNode(11);
		root02.right.left.right.right.left = new TreeNode(13);
		root02.right.left.right.right.right = new TreeNode(12);
		
		System.out.println("pre-order recursive");
		System.out.println(Arrays.toString(preOrderRecursive(root0).toArray()));
		System.out.println(Arrays.toString(preOrderRecursive(root).toArray()));
		System.out.println(Arrays.toString(preOrderRecursive(root02).toArray()));

		System.out.println("pre-order iterative");
		System.out.println(Arrays.toString(preOrderIterative(root0).toArray()));
		System.out.println(Arrays.toString(preOrderIterative(root).toArray()));
		System.out.println(Arrays.toString(preOrderIterative(root02).toArray()));

	}

}
