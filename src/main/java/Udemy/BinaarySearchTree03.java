package Udemy;
//todo
/*   Inorder successor/predecessor of a node
 *   remove a node
 *   delete a tree
 *   copy a tree
 *   expression tree / prefix

 * 
 * */
public class BinaarySearchTree03 extends BinaarySearchTree02 {
	
	BSTNode inOrderSuccessor(BSTNode node){
		BSTNode n = findRecursive(root, node.key);
		return inOrderRecursive(n, node.key);
	}
	
	BSTNode findRecursive(BSTNode root, int val){
		if(root == null){
			return null;
		}
		if(root.right!= null && val > root.right.key){
			return findRecursive(root.right, val);
		}
		if(root.left != null && val < root.left.key){
			return findRecursive(root.left, val);
		}
		return root;
	}
	
	BSTNode inOrderRecursive(BSTNode node, int val){
		if(node == null){
			return null;
		}
		BSTNode left = inOrderRecursive(node.left, val);
		if(left != null){
			return left;
		}
		if(node.key  > val){
			return node;
		}
		BSTNode right = inOrderRecursive(node.right, val);
		if(right != null){
			return right;
		}
		return null;
	}

	public static void main(String[] args) {
		BinaarySearchTree03 bt = new BinaarySearchTree03();
		bt.addLogicInTree(20);
		bt.addLogicInTree(22);
		bt.addLogicInTree(8);
		bt.addLogicInTree(12);
		bt.addLogicInTree(10);
		bt.addLogicInTree(14);
		bt.addLogicInTree(4);
		bt.traverseBFandPrint();
		System.out.println("bt.inOrderSuccessor(new BSTNode(8)) : " + bt.inOrderSuccessor(new BSTNode(8)));
		System.out.println("bt.inOrderSuccessor(new BSTNode(10)) : " + bt.inOrderSuccessor(new BSTNode(10)));
		System.out.println("bt.inOrderSuccessor(new BSTNode(14)) : " + bt.inOrderSuccessor(new BSTNode(14)));
		System.out.println("bt.inOrderSuccessor(new BSTNode(22)) : " + bt.inOrderSuccessor(new BSTNode(22)));







		
	}

}
