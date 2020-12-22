package Trees;

import leetcode.Trees.SymmetricTreeRecursive_Iterative.TreeNode;

/*  https://www.techiedelight.com/insertion-in-bst/
 * 
 * */
public class InsertionInBST {
	
	public TreeNode insert(TreeNode root, int key){
		if(root == null){
			root = new TreeNode(key);
			return root;
		}
		if(key > root.val){
			root.right = insert(root.right, key);
		}else{
			root.left = insert(root.left, key);
		}
		return root;
	}
	
	public TreeNode insertIterative(TreeNode root, int key){
		if(root == null){
			return new TreeNode(key);
		}
		
		TreeNode node = root;
		while(node !=null){
			if(key > node.val){
				if(node.right == null){
					node.right = new  TreeNode(key);
					return root;
				}
				node = node.right;
			}else{
				if(node.left == null){
					node.left = new  TreeNode(key);
					return root;
				}
				node = node.left;
			}
		}
		return root;
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
		InsertionInBST insertionInBST = new InsertionInBST();
		TreeNode root = null;
		TreeNode rootIterative = null;

		int[] keys = { 15, 10, 20, 8, 12, 16, 25 };

		for (int key: keys) {
			root = insertionInBST.insert(root, key);
			rootIterative = insertionInBST.insertIterative(rootIterative, key);
		}
		
		System.out.print("         insert : ");
		insertionInBST.inOrder(root);
		System.out.print("\ninsertIterative : ");
		insertionInBST.inOrder(rootIterative);
	}

}
