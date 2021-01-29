package Trees;

/*
 * 
 *  https://www.techiedelight.com/deletion-from-bst/
 * */
public class DeletionFromBST {
	
	public TreeNode deleteNode(TreeNode root, int key){
		if(root == null){
			return null;
		}
		boolean isLeft = false;
		boolean isRoot = root.val == key;
		TreeNode parentNode = findParent(root, key);
		if(parentNode == null && !isRoot){
			return root;//could not find the key
		}
		TreeNode node = root;
		if(!isRoot){
			if(parentNode.left != null && parentNode.left.val == key){
				node = parentNode.left;
				isLeft = true;
			}else if(parentNode.right != null){
				node = parentNode.right;
			}
		}
		
		if(node.left == null || node.right == null){// node has 0 or 1 child
			TreeNode child = null;
			if(node.left != null){
				child = node.left;
			}
			if(node.right != null){
				child = node.right;
			}
			if(isRoot){
				return child;
			}
			if(isLeft){
				parentNode.left = child;
			}else{
				parentNode.right = child;
			}
			return root;
		}
		
		if(node.left != null && node.right != null){//node has 2 children
			TreeNode predecessor = findPred(node.left);
			node.val = predecessor.val;
			node.left = deleteNode(node.left, predecessor.val);
			return root;
		}
		return root;
	}
	
	private TreeNode findParent(TreeNode root, int key){
		if(root == null || root.val == key){
			return null;
		}
		if(root.left != null && root.left.val == key){
			return root;
		}
		if(root.right != null && root.right.val == key){
			return root;
		}
		if(key > root.val){
			return findParent(root.right, key);
		}else{
			return findParent(root.left, key);
		}
	}
	
	private TreeNode findPred(TreeNode node){
		TreeNode pred = node;
		while(pred.right != null){
			pred = pred.right;
		}
		return pred;
	}

	public static void main(String[] args) {
		InsertionInBST insertionInBST = new InsertionInBST();
		DeletionFromBST deletionFromBST = new DeletionFromBST();

		TreeNode root = null;
		/*insertionInBST.inOrder(root);
		root = deletionFromBST.deleteNode(root, 12);//delete the null root
		insertionInBST.inOrder(root);
		
		root = insertionInBST.insert(root, 4);
		insertionInBST.inOrder(root);
		root = deletionFromBST.deleteNode(root, 4);//delete the  root (BST of size 1)
		insertionInBST.inOrder(root);
		
		root = insertionInBST.insert(root, 4);//delete root (root is a linked list)
		root = insertionInBST.insert(root, 7);
		root = insertionInBST.insert(root, 8);
		root = insertionInBST.insert(root, 5);
		insertionInBST.inOrder(root);
		root = deletionFromBST.deleteNode(root, 4);
		insertionInBST.inOrder(root);*/

		int[] keys = {15, 10, 20, 8, 12, 18, 25,16,19,17,11,13,14 };
		for (int key: keys) {
			root = insertionInBST.insert(root, key);
		}
		insertionInBST.inOrder(root);
		System.out.print("\ndeleteNode(root, 18) : ");
		root = deletionFromBST.deleteNode(root, 18);//delete node with 2 children
		insertionInBST.inOrder(root);
		
		System.out.print("\ndeleteNode(root, 25) : ");
		root = deletionFromBST.deleteNode(root, 25);//delete a leaf
		insertionInBST.inOrder(root);
		
		System.out.print("\ndeleteNode(root, 17) : ");
		root = deletionFromBST.deleteNode(root, 17);//delete node with 1 child
		insertionInBST.inOrder(root);
		
		System.out.print("\ndeleteNode(root, 16) : ");
		root = deletionFromBST.deleteNode(root, 16);//delete node with 1 child
		insertionInBST.inOrder(root);

	}

}
