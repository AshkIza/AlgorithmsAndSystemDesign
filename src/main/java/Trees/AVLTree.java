package Trees;

import leetcode.Trees.SymmetricTreeRecursive_Iterative.TreeNode;

/* https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * 
 * */
public class AVLTree {
	AVLNode root;
	public static class AVLNode extends TreeNode{
	      public int height;
		public AVLNode(int val) {
			super(val);
			height = 0;
		}
	}
	
	AVLTree(){
		//default ctr
	}
	
	void insert(int key){
		root = insert(root, key);
	}

	
	AVLNode insert(AVLNode root, int key){
		if(root == null){
			return new AVLNode(key);
		}
		
		if(key <= root.val){
			if(root.left == null){
				root.left = new AVLNode(key); 
			}else{
				root.left = insert((AVLNode)root.left, key);
			}
			
		}else if( key > root.val ){
			if(root.right == null){
				root.right = new AVLNode(key); 
			}else{
				root.right = insert((AVLNode)root.right, key);
			}
		}
		updateHeight(root);
		int bf = balanaceFactor(root);
		if(bf <= 1 && bf >= -1){
			return root;
		}else if(bf > 1){//left-heavy
			return  rightDoubleRotate(root);
		}else{//right-heacy
			return  leftDoubleRotate(root);
		}
	}
	
	AVLNode rightDoubleRotate(AVLNode node){//LR and LL Case
		if(node==null || node.left == null){
			return node;
		}
		int bf = balanaceFactor((AVLNode)node.left);
		if(bf<0){//right-heavy--> LR Case
			node.left = rightRotation((AVLNode)node.left);
		}
		//now it is LL Case
		AVLNode rotatedNode = leftRotation((AVLNode)node);
		updateHeight(rotatedNode);
		return rotatedNode;
	}
	
	AVLNode leftDoubleRotate(AVLNode node){//RL and RR Case
		if(node==null || node.right == null){
			return node;
		}
		int bf = balanaceFactor((AVLNode) node.right);
		if(bf>0){//left-heavy--> RL Case
			node.right = leftRotation((AVLNode) node.right);
		}
		//now it is RR Case
		AVLNode rotatedNode = rightRotation((AVLNode) node);
		updateHeight(rotatedNode);
		return rotatedNode;
	}
	
	AVLNode rightRotation(AVLNode node){
		if(node==null || node.right == null){
			return node;
		}
		AVLNode x = node;
		AVLNode y = (AVLNode) node.right;
		AVLNode t = (AVLNode) y.left;
		y.left = x;
		x.right = t;
		return y;
	}
	
	AVLNode leftRotation(AVLNode node){
		if(node==null || node.left == null){
			return node;
		}
		AVLNode x = node;
		AVLNode y = (AVLNode) node.left;
		AVLNode t = (AVLNode) y.right;
		y.right = x;
		x.left = t;
		return y;
	}
	
	private int updateHeight(AVLNode node){
		if (node == null || (node.left == null && node.right == null)){
			node.height = 0;//ATTETION TO DETAIL -> Troubleshoot all cases(leaf nodes as well as nodes with 1/2 children)
			return 0;
		}
		int l = 0;
		int r = 0;
		if(node.left != null){
			l = updateHeight((AVLNode)node.left) + 1;
		}
		if(node.right != null){
			r = updateHeight((AVLNode)node.right) + 1;
		}
		node.height = Math.max(l,r);
		return node.height;
	}
	
	private int balanaceFactor(AVLNode node){
		if (node == null || (node.left == null && node.right == null)){
			return 0;
		}
		int l = 0;
		int r = 0;
		if(node.left != null){
			l = ((AVLNode)node.left).height + 1;
		}
		if(node.right != null){
			r = ((AVLNode)node.right).height + 1;
		}
		return l - r;
	}

	public static void main(String[] args) {
		LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
        AVLTree avlTree = new AVLTree(); 
        
        System.out.println("Root of AVL tree changes as insertion goes on "); 
        
        /* Constructing tree given in the above figure */
        avlTree.insert(10); 
        avlTree.insert(20); 
        avlTree.insert(30); 
        avlTree.insert(40); 
        avlTree.insert(50); 
		levelOrderTraversal.levelOrderTraversal(avlTree.root);
        
        avlTree.insert(25); 
        /* The constructed AVL Tree would be 
             30 
            /  \ 
          20   40 
         /  \     \ 
        10  25    50 
        */

		levelOrderTraversal.levelOrderTraversal(avlTree.root);
		
		avlTree.insert(28); 
	    avlTree.insert(26); 
	    avlTree.insert(29); 
		levelOrderTraversal.levelOrderTraversal(avlTree.root);
		
	    avlTree.insert(24); 
		levelOrderTraversal.levelOrderTraversal(avlTree.root);
	}
}
