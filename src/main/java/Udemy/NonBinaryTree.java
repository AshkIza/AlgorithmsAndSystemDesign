package Udemy;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/* 	MOST TREE PROBLEMS :
 *  DO traverse the tree (DF or BF) and do some extra processing while traversing through tree
 * */
public class NonBinaryTree<T> {
	Node<T> root;
	NonBinaryTree(){
		root = null;
	}
	
	/*using a queue to maintain parent and children order properly
	 *  (remove children after all parents are poped out*/
	void traverseBFUsingQueue(){
		System.out.println("breath first traverse uing Queue : ");
		Queue<Node<T>> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()){
			Node<T> p = q.remove();//pop element out
			q.addAll(p.children);//get all children of the element
			System.out.println(p.data.toString());//process (traverse) the element
		}
	}
	
	/* https://www.geeksforgeeks.org/dfs-traversal-of-a-tree-using-recursion/
	 * */
	void traverseDF(){
		System.out.println("depth first traverse recursive (call stack): ");
		if (root == null) {
			return;
		}
		traverseDFRecuersive(root);
	}
	void traverseDFRecuersive(Node<T> node){
		System.out.println(node.data.toString());
		for(Node<T> child : node.children){
			traverseDFRecuersive(child);
		}
	}
	
	/* https://algorithms.tutorialhorizon.com/depth-first-searchtraversal-in-binary-tree/
	 * */
	void traverseDFUsingStack(){
		System.out.println("depth first traverse using stack: ");
		Stack<Node<T>> s = new Stack<>();
		s.push(root);
		while (!s.isEmpty()){
			Node<T> node = s.pop();
			System.out.println(node.data.toString());
			for(int i = node.children.size() -1; i>= 0; i--){
				s.push(node.children.get(i));
			}
		}
	}
	
	/*	width problems--> breath first traversal
	 * */
	 ArrayList<Integer> levelWidth(){
		 int width = 1;
		 ArrayList<Integer> levelWidth = new ArrayList<>();
		 Queue<Node<T>> q = new LinkedList<>();
		 levelWidth.add(width);
		 q.add(root);
		 while(!q.isEmpty()){
			 if(width == 0){
				 // all children have been retrieved
				 width = q.size();
				 levelWidth.add(width);
			 }
			 //still in process of retrieving children
			 q.addAll(q.remove().children);
			 width --;
		 }
		 return levelWidth;
	}
	
	public static class Node<T>{
		public T data;
		public ArrayList<Node<T>> children;
		
		Node(T value){
			data = value;
			children = new ArrayList<>();
		}
		
		void add(T value){
			Node<T> child = new Node<>(value);
			children.add(child);
		}
		
		Node<T> add(Node<T> node){
			children.add(node);
			return this;
		}
		
		Node<T> add(T... value){
			for(T val : value){
				children.add(new Node<>(val));
			}
			return this;
		}
		
		void remove(T value){
			Node<T> child = new Node<>(value);
			if(children.contains(child)){
				children.remove(child);
			}
		}
		
		void displayChildren(){
			String s = "children : ";
			for(Node<T> child : children){
				s+= child.data.toString() + ", ";
			}
			System.out.println(s);
		}
		
		/*since we are using List.constains(), we should implement equals(Object ob) 
		for each custom object we are using as Node data*/
		@Override
		/*should always use Object as argument type (NOT like this: Node obj)*/
	    public boolean equals(Object obj){
			if(this == obj){
				return true;
			}
			/*Cannot perform instanceof check against parameterized type ArrayList<Foo>.
			Use the form ArrayList<?> instead since further generic type information 
			will be erased at runtime*/
			if(obj instanceof Node<?>){
				if (((Node<?>) obj).data.equals(((Node<?>) this).data)){
					return true;
				}
			}
			return false;
		}
	}
	
	//todo use proper hashCode and equals overrides 
	public static class CustomObject{
		String name;
		Integer rank;
		
		CustomObject(String n, Integer r){
			name = n;
			rank = r;
		}
		
		@Override
		public String toString(){
			return this.name + " " + this.rank;
		}
		
		@Override
		public boolean equals(Object ob){
			if (this == ob){//reference check
				return true;
			}
			if(this.name.equals( ((CustomObject) ob).name) && //object elements check
					this.rank.equals( ((CustomObject) ob).rank)){
						return true;
			}
			return false;
		}
	}
	

	public static void main(String[] args) {
		System.out.println("Node<String>");
		Node<String> node = new Node<>("parent");
		node.add("firstChild");
		node.add("secondChild");
		node.add("thirdChild");
		node.displayChildren();
		node.remove("thirdChild");
		node.displayChildren();
		System.out.println("");

		System.out.println("Node<CustomObject>");
		Node<CustomObject> customNode = new Node<>(new CustomObject("PARENT", 1));
		customNode.add(new CustomObject("firstChild", 2));
		customNode.add(new CustomObject("secondChild", 3));
		customNode.add(new CustomObject("thirdChild", 4));
		customNode.displayChildren();
		customNode.remove(new CustomObject("thirdChild", 4));
		customNode.displayChildren();
		System.out.println("");
		
		//tree
		System.out.println("Breath first travesre (queue)");
		NonBinaryTree<Integer> treeInt = new NonBinaryTree<>();
		treeInt.root = new Node<Integer>(1);
		treeInt.traverseBFUsingQueue();
		treeInt.root.add(new Node<Integer>(2).add(5,6,7));
		treeInt.root.children.add(new Node<Integer>(3).add(new Node<Integer>(8).add(9,10)));
		treeInt.root.children.add(new Node<Integer>(4).add(44, 444, 4444, 44444));
		treeInt.traverseBFUsingQueue();
		
		System.out.println("");
		System.out.println("Depth first traverse (reach to bottom of tree as quickly as possible -->");
		System.out.println(" Recursive (call stack) or using Stack (Recursive is Stack)");
		treeInt.traverseDF();
		treeInt.traverseDFUsingStack();
		
		System.out.println("");
		System.out.println("tree.levelWith() (number of nodes in each level) :");
		System.out.println("width tree  problems--> breath first traversal");
		Node<Integer> n11 = new Node<Integer>(11);
		n11.children.add(new Node<Integer>(12).add(14,15,16));
		n11.children.add(new Node<Integer>(13));
		treeInt.root.children.add(n11);
		System.out.println(treeInt.levelWidth().toString());

	}

}
