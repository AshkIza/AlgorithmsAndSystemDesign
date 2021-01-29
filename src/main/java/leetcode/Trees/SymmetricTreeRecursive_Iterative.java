package leetcode.Trees;

import java.util.LinkedList;
import java.util.stream.Stream;

import static leetcode.Trees.TreeNode.*;

/*  Symmetric Tree
	Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	
	For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
	
	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	 
	
	But the following [1,2,2,null,3,null,3] is not:
	
	    1
	   / \
	  2   2
	   \   \
	   3    3
	 
	
	Note:
	Bonus points if you could solve it both recursively and iteratively.
	
	Solution: 
	Recursive:  https://www.programcreek.com/2014/03/leetcode-symmetric-tree-java/
	Iterative: https://www.geeksforgeeks.org/check-symmetric-binary-tree-iterative-approach/
 * 
 * 
 * */
public class SymmetricTreeRecursive_Iterative {
	
    public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetricRecursive(root.left, root.right);
        
    }
    
     public static boolean isSymmetricRecursive(TreeNode left, TreeNode right) {
         if(left==null && right==null) return true;
         if(left == null || right==null) return false;
         if(left.val != right.val) return false;
         return isSymmetricRecursive(left.left, right.right) && 
        		 isSymmetricRecursive(left.right, right.left);
    }
     
     
     // iterative ---> Queue
     public boolean isSymmetricIterative(TreeNode root) {
         if(root == null || bothNull(root.left, root.right)) return true;
         if(oneNull(root.left, root.right)) return false;
         LinkedList<TreeNode> pairs = new LinkedList<>();
         pairs.add(root.left);
         pairs.add(root.right);
         while(!pairs.isEmpty()){
             TreeNode left = pairs.remove();
             TreeNode right = pairs.remove();
             if(left.val != right.val) return false;
             TreeNode a = left.left;
             TreeNode b = right.right;
             TreeNode c = left.right;
             TreeNode d = right.left;
             if(!bothNull(a,b)){
                 if(oneNull(a,b)) return false;
                 pairs.add(a);
                 pairs.add(b);
             }
             if(!bothNull(c,d)){
                 if(oneNull(c,d)) return false;
                 pairs.add(c);
                 pairs.add(d);
             }
         }
         return true;
     }
     
     public boolean bothNull(TreeNode a, TreeNode b){
         return a==null && b==null;
     }
     public boolean oneNull(TreeNode a, TreeNode b){
         return a==null || b==null;
     }

	public static void main(String[] args) {
		System.out.println("  Tree = Recursive (90% of problems) " );
		System.out.println("  if using Iterative for tree -> use Queue aka LinkedList \n\n" );
		
		
		TreeNode root_case01 = createTree(Stream.of(1,2,2,3,4,4,3));
		System.out.println("isSymmetric(root): " + isSymmetric(root_case01));

		
		TreeNode root_case02 = createTree(Stream.of(1, 2,2, 3,4,4,3, null,6,7,8,8,7,6,null));
		System.out.println("isSymmetric(root): " + isSymmetric(root_case02));

		
		TreeNode root_case03 = createTree(Stream.of(1, 2,2, 3,4,4,3, null,6,7,8,8,7,6,10));
		System.out.println("isSymmetric(root): " + isSymmetric(root_case03));
		
	}

}
