package leetcode.Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeNode {
	 public int val;
     public TreeNode left;
     public TreeNode right;
     public TreeNode(int x) { val = x; }
     
     public static List<String> levelOrder(TreeNode root) {
     	List<TreeNode> parents = new ArrayList<>();
     	List<String> levelorder = new ArrayList<>();
    	parents.add(root);
    	levelorder.add(String.valueOf(root.val));
    	while(!parents.isEmpty()) {
    		List<TreeNode> siblings = new ArrayList<>(); 
    		List<String> level = new ArrayList<>();
    		for(TreeNode parent : parents) {
    			if(parent.left == null) {
    				level.add("null");
    			}else {
    				level.add(String.valueOf(parent.left.val));
    				siblings.add(parent.left);
    			}
    			
    			if(parent.right == null) {
    				level.add("null");
    			}else {
    				level.add(String.valueOf(parent.right.val));
    				siblings.add(parent.right);
    			}
    		}
    		if(!siblings.isEmpty()) levelorder.addAll(level);
    		parents = siblings;
    	}
    	return levelorder;
     }
     
     public static TreeNode createTree(Stream<Integer> breathFirstStream) {
	    	LinkedList<String> breathFirstList = breathFirstStream
	    			.map( integer -> (integer == null) ? "null" : String.valueOf(integer))
	    			.collect(Collectors.toCollection(LinkedList::new));
	      	System.out.println("tree : " +  breathFirstList);
	    	TreeNode root = new TreeNode(Integer.valueOf(breathFirstList.remove()));
	    	List<TreeNode> parents = new ArrayList<>();
	    	parents.add(root);
	    	while(!parents.isEmpty() && !breathFirstList.isEmpty()) {
	    		List<TreeNode> siblings = new ArrayList<>(); 
	    		for(TreeNode parent : parents) {
	    			if(!breathFirstList.isEmpty()) {
	    				String s = breathFirstList.remove();
	    				parent.left = (s == "null") ? null : new TreeNode(Integer.valueOf(s));
	    				siblings.add(parent.left);
	    				if(!breathFirstList.isEmpty()) {
	    					s = breathFirstList.remove();
	    	    			parent.right = (s == "null") ? null : new TreeNode(Integer.valueOf(s));
	    	    			siblings.add(parent.right);
	    				}else {
	    					return root;
	    				}
	    			}else {
	    				return root;
	    			}
	    		}
	    		parents = siblings;
	    	}
	    	return root;
	    } 

}
