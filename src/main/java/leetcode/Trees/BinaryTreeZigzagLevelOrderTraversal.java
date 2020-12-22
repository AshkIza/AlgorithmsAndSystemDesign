package leetcode.Trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import leetcode.Trees.SymmetricTreeRecursive_Iterative.TreeNode;

/*  Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

		For example:
		Given binary tree [3,9,20,null,null,15,7],
		    3
		   / \
		  9  20
		    /  \
		   15   7
		return its zigzag level order traversal as:
		[
		  [3],
		  [20,9],
		  [15,7]
		]
 * 
 * 
 * */
public class BinaryTreeZigzagLevelOrderTraversal {
	
	
	/*  DoublyLinkedList (Dequq can implement 2-sides queue)  leftToRight and rightToLeft add/poll
	 * normal queue (leftToRight)
	 *  queue.add()   queue.addLast()
	 *  queue.poll()  queue.pollFirst()
	 *  
	 *  reverse queue (rightToLeft) 
	 *  queue.addFirst()
	 *  queue.pollLast()
	 * 
	 * */
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Deque<TreeNode> level = new ArrayDeque<>();
		if(root == null){
			return result;
		}
		level.add(root);
		boolean rightToLeft = false;
		while(!level.isEmpty()){
			Deque<TreeNode> nextLevel = new ArrayDeque<>();
			List<Integer> levelInt = new ArrayList<>();
			while(!level.isEmpty()){
				TreeNode node = null;
				if(rightToLeft){
					node = level.pollLast();
					if(node.right != null){
						nextLevel.addFirst(node.right);
					}
					if(node.left != null){
						nextLevel.addFirst(node.left);
					}
				}else {
					node = level.pollFirst();
					if(node.left != null){
						nextLevel.addLast(node.left);
					}
					if(node.right != null){
						nextLevel.addLast(node.right);
					}
				}
				levelInt.add(node.val);
			}
			result.add(levelInt);
			rightToLeft = (rightToLeft == true) ? false : true;
			level = nextLevel;
		}
		return result;
	}
	
	static void printLevels(List<List<Integer>> allLevels){
		System.out.println("[");
		for(List<Integer> level : allLevels){
			System.out.print("[ ");
			System.out.print(Arrays.toString(level.toArray()));
			System.out.println(" ]");

		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		
		root.left.left = new TreeNode(10);
		root.left.right = new TreeNode(15);
		root.right.left = new TreeNode(8);
		root.right.right = new TreeNode(16);
		
		TreeNode root02 = new TreeNode(3);
		root02.left = new TreeNode(9);
		root02.right = new TreeNode(20);
		root02.right.left = new TreeNode(15);
		root02.right.right = new TreeNode(7);
		
		printLevels(zigzagLevelOrder(root));
		printLevels(zigzagLevelOrder(root02));
		
		
		
		
	}

}
