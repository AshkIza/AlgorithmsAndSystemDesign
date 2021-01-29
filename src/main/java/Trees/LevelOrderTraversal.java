package Trees;

import java.util.LinkedList;
import java.util.List;

/*  https://www.techiedelight.com/level-order-traversal-binary-tree/
 * 
 * */
public class LevelOrderTraversal {
	
	/* time complexity : O(n)
	 * extra space: O(n) --> for the queue
	 * 
	 * */
	public void levelOrderTraversal(TreeNode root){
		String bfs = "\nlevel Order Traversal (BFS): ";
		if(root == null){
			return;
		}
		LinkedList<TreeNode> q = new LinkedList<>();

		q.add(root);
		while(!q.isEmpty()){
			TreeNode node = q.poll();
			if(node.left!=null){
				q.add(node.left);
			}
			if(node.right!=null){
				q.add(node.right);
			}
			bfs += node.val + ", ";
		}
		System.out.println(bfs);
	}
	
	public void inOrder(TreeNode root){
		if(root == null){
			return;
		}
		inOrder(root.left);
		System.out.print(root.val + ", ");
		inOrder(root.right);
	}


	public static void main(String[] args) {
		TreeNode root = new TreeNode(15);
		root.left = new TreeNode(10);
		root.right = new TreeNode(20);
		root.left.left = new TreeNode(8);
		root.left.right = new TreeNode(12);
		root.right.left = new TreeNode(16);
		root.right.right = new TreeNode(25);
		
		LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
		System.out.print("inOrder Traversal(DFS) : ");
		levelOrderTraversal.inOrder(root);
		levelOrderTraversal.levelOrderTraversal(root);
		
		
	}

}
