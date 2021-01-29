package leetcode.Trees;

import static leetcode.Trees.TreeNode.createTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/* https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/628/
 * 
 * 
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 * 
 * */
public class BinaryTreeLevelOrderTraversal {
	
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if(root == null) return levels;
        LinkedList<TreeNode> parents = new LinkedList<>();
        parents.add(root);
        while(!parents.isEmpty()){
            LinkedList<TreeNode> siblings = new LinkedList<>();
            List<Integer> level = new ArrayList<>();
            while(!parents.isEmpty()){
                 TreeNode p = parents.remove();
                 level.add(p.val);
                 if(p.left != null)  siblings.add(p.left);
                 if(p.right != null) siblings.add(p.right);
            }
            levels.add(level);
            parents = siblings;
        }
        return levels;
    }

	public static void main(String[] args) {
		System.out.println("  Tree = Recursive (90% of problems) " );
		System.out.println("  if using Iterative for tree -> use Queue aka LinkedList \n\n" );
				
		
		TreeNode root_case01 =  createTree(Stream.of(3,9,20,null,null,15,7));
		System.out.println("levelOrder() " + levelOrder(root_case01));
	}

}
