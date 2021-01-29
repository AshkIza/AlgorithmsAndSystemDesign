package Trees;
import java.util.ArrayList;
import java.util.List;

/*  https://algorithms.tutorialhorizon.com/inorder-predecessor-and-successor-in-binary-search-tree/
 * 
 * */
public class InorderSuccessorPredecessor {
	
	public void successorPredecessor(TreeNode root, int val) {
		succPred(root, val, null, null);
	}
	
	private void succPred(TreeNode node, int val, TreeNode parent, TreeNode  grandParent){
		if(node == null){
			return;
		}
		if(val > node.val){
			grandParent = parent;
			parent = node;
			succPred(node.right, val, parent, grandParent);
		}
		if(val < node.val){
			grandParent = parent;
			parent = node;
			succPred(node.left, val, parent, grandParent);
		}
		if( val == node.val){
			TreeNode pred = null;
			if(node.left != null){
				pred = findPred(node.left);
			}else if(parent!=null && parent .val < val){ //RR 
				pred = parent;
			}else if(grandParent != null && grandParent.val < val){//RL
				pred = grandParent;
			}
			TreeNode succ = null;
			if(node.right != null){
				succ = findSucc(node.right);
			}else if(parent!=null && parent .val > val){ //LL
				succ = parent;
			}else if(grandParent != null && grandParent.val > val){//LR
				succ = grandParent;
			}
			String result = (pred == null) ? "NO Predeccesor" : "Predeccesor :" + pred.val; 
			result += ", node :" + val;
			result += (succ==null) ? ", NO Successor" : ", Successor :" + succ.val; 
			System.out.println(result);
			
		}
	
	}
	
	TreeNode findPred(TreeNode node){
		TreeNode pred = node;
		while(pred.right != null){
			pred = pred.right;
		}
		return pred;
	}
	
	TreeNode findSucc(TreeNode node){
		TreeNode succ = node;
		while(succ.left != null){
			succ = succ.left;
		}
		return succ;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(20);
		root.left = new TreeNode(10);
		root.right = new TreeNode(30);
		root.left.left = new TreeNode(5);
		root.left.left.right = new TreeNode(7);
		root.left.right = new TreeNode(15);
		root.right.left = new TreeNode(25);
		root.right.right = new TreeNode(35);
		root.right.left.right = new TreeNode(28);
		root.left.right.left = new TreeNode(13);
		root.left.right.right = new TreeNode(18);
		
		InorderSuccessorPredecessor inorderSuccessorPredecessor = new InorderSuccessorPredecessor();
		inorderSuccessorPredecessor.successorPredecessor(root, 10);//case where node has children (find predSucc in children)
		inorderSuccessorPredecessor.successorPredecessor(root, 30);
		inorderSuccessorPredecessor.successorPredecessor(root, 25);//case where grandparent is the predecessor (RL)
		inorderSuccessorPredecessor.successorPredecessor(root, 35);//case where parent is predecessor
		inorderSuccessorPredecessor.successorPredecessor(root, 7);//case where parent is predecessor, grandparent is successor (LR)

		System.out.println("\n(root, 10) : case where node has children (find pred/Succ in children)");
		System.out.println("(root, 25) : case where grandparent is the predecessor (RL)");
		System.out.println("(root, 35) : case where parent is predecessor");
		System.out.println("(root, 7) : case where parent is predecessor, grandparent is successor (LR)");
	}

}
