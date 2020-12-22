package Udemy;

public class SinglyLinkedList {
	
	public class Node{
		public int value;
		public Node next;
		Node(int val){
			value = val;
			next = null;
		}
	}
	
	protected Node head;
	protected SinglyLinkedList(){
		//default ctr (head = null)
	}
	
	public void insertFirst(int val){
		Node n = new Node(val);
		n.next = head;
		head = n;
	}
	
	int getFirst(){
		if(head == null){//empty list
			System.out.println("empty List. can NOT getFirst");
			return Integer.MIN_VALUE;
		}
		return head.value;
	}
	
	int size(){
		int s = 0;
		Node n = head;
		while (n!=null){
			s++;
			n = n.next;
		}
		return s;
	}
	
	int getLast(){
		if(head == null){//empty list
			System.out.println("empty List. can NOT getLast");
			return Integer.MIN_VALUE;
		}
		Node n = head;
		while(n.next != null){//has not reached tail yet
			n = n.next;
		}
		//found tail
		return n.value;
	}
	
	/*  empty link list of any node
	 * In Java, automatic garbage collection happens, so deleting a linked list is easy. 
	 * We just need to change head to null.
	 * https://www.geeksforgeeks.org/write-a-function-to-delete-a-linked-list/
	 * */
	void clear(){
		head = null;
	}
	/* just like clear, the de-referenced old head gets garbage-collected.
	 * just need to push head reference to the next node
	 * */
	void removeFirst(){
		if(head != null){
			head = head.next;
		}
	}
	
	/*  https://www.geeksforgeeks.org/remove-last-node-of-the-linked-list/
	 * */
	void removeLast(){
		if(head == null) {//empty list case
			return;
		}
		if(head.next == null){//1 element list case
			head = null;
			return;
		}
		Node n = head;//2+ element case
		while (n.next != null && n.next.next!= null){
			n = n.next;
		}
		//found the last 2 nodes
		n.next = null;//new tail
	}
	
	public void insertLast(int val){
		Node node = new Node(val);
		if(head == null){//empty list case
			head = node;
			return;
		}
		Node n = head;//non-empty list case
		while(n.next != null){
			n = n.next;
		}
		//we have reached to the tail
		n.next = node;//new tail is node
	}
	
	/* List interface methods : 
	 * getAt(index) insertAt(index) removeAt(index)
	 * */
	Node getAt(int index){
		if(head == null){
			System.out.println("empty list!");
			return null;
		}
		Node n = head;
		int counter = 0;
		while(counter < index && n != null){
			n = n.next;
			counter++;
		}
		//by now,b counter should be index if n not null
		if(n == null){
			System.out.println("index " + index + " is out of boud");
		}
		return n;
	}
	/* code re-use (re-use of getAt())
	 * ******* CODE RE-USE IS A BIG PLUS IN AN INTERVIEW SETTING *******
	 * */
	void removeAt(int index){
		if(head == null){//empty list
			System.out.println("empty list! can NOT removeAt()");
			return;
		}
		if(index == 0){ //remove head
			head = head.next;
			return;
		}
		Node prevoius = getAt(index - 1);//remove a middle element or tail
		if(prevoius == null || prevoius.next == null){
			System.out.println("index "+ index + " out of bound. can NOT removeAt()");
			return;
		}
		prevoius.next = prevoius.next.next;
	}
	
	/* code re-use (re-use of getAt())
	 * */
	void insertAt(int index, int val){
		if(head == null){//empty list
			System.out.println("empty list! can NOT removeAt()");
			return;
		}
		Node node = new Node(val);
		if(index == 0){ //insert at head
			node.next = head;
			head = node;
			return;
		}
		Node prevoius = getAt(index - 1);//insert at a middle element or tail
		if(prevoius == null || prevoius.next == null){
			System.out.println("index "+ index + " out of bound. can NOT insertAt()");
			return;
		}
		node.next = prevoius.next;
		prevoius.next  = node;
	}
	
	public void display(){
		Node n = head;
		String st = "";
		while (n != null){
			st += n.value + ", ";
			n = n.next;
		}
		System.out.println("LinkedList : " + st);
	}

	public static void main(String[] args) {
		SinglyLinkedList singleLinkedList = new SinglyLinkedList();
		System.out.println("singleLinkedList.size() : " + singleLinkedList.size());
		System.out.println("LinkedList.getFirst() : " + singleLinkedList.getFirst());
		System.out.println("LinkedList.getLast() : " + singleLinkedList.getLast());
		System.out.println("LinkedList.clear() : ");
		System.out.println("LinkedList.removeFirst()");
		System.out.println("LinkedList.removeFirst()");
		singleLinkedList.removeFirst();
		singleLinkedList.removeLast();
		singleLinkedList.clear();
		singleLinkedList.display();
		System.out.println("singleLinkedList.getAt(0) : " + singleLinkedList.getAt(0));
		System.out.println("singleLinkedList.insertLast(222)");
		singleLinkedList.insertLast(222);
		System.out.println("singleLinkedList.getAt(0).value : " + singleLinkedList.getAt(0).value);
		System.out.println("singleLinkedList.getAt(1) : " + singleLinkedList.getAt(1));
		System.out.println("singleLinkedList.insertFirst(1)");
		System.out.println("singleLinkedList.insertFirst(2)");
		System.out.println("singleLinkedList.insertFirst(3)");
		System.out.println("singleLinkedList.insertLast(333)");
		singleLinkedList.insertFirst(1);
		singleLinkedList.insertFirst(2);
		singleLinkedList.insertFirst(3);
		singleLinkedList.insertLast(333);
		singleLinkedList.display();
		System.out.println("singleLinkedList.getAt(2).value : " + singleLinkedList.getAt(2).value);
		System.out.println("singleLinkedList.getAt(20) : " + singleLinkedList.getAt(20));
		System.out.println("LinkedList.getFirst() : " + singleLinkedList.getFirst());
		System.out.println("LinkedList.getLast() : " + singleLinkedList.getLast());
		singleLinkedList.insertFirst(4);
		singleLinkedList.display();
		System.out.println("LinkedList.removeLast()");
		singleLinkedList.removeLast();
		singleLinkedList.display();
		singleLinkedList.insertFirst(500);
		singleLinkedList.insertFirst(600);
		singleLinkedList.insertFirst(700);
		singleLinkedList.display();
		System.out.println("singleLinkedList.getAt(5).value : " + singleLinkedList.getAt(5).value);
		System.out.println("singleLinkedList.size() : " + singleLinkedList.size());
		System.out.println("LinkedList.getFirst() : " + singleLinkedList.getFirst());
		System.out.println("LinkedList.getLast() : " + singleLinkedList.getLast());
		System.out.println("LinkedList.removeFirst()");
		singleLinkedList.removeFirst();
		singleLinkedList.display();
		System.out.println("singleLinkedList.size() : " + singleLinkedList.size());
		System.out.println("LinkedList.removeLast()");
		singleLinkedList.removeLast();
		singleLinkedList.display();
		System.out.println("singleLinkedList.insertLast(999)");
		singleLinkedList.insertLast(999);
		singleLinkedList.display();
		System.out.println("singleLinkedList.getAt(5).value : " + singleLinkedList.getAt(5).value);
		System.out.println("singleLinkedList.getAt(6).value : " + singleLinkedList.getAt(6).value);
		System.out.println("singleLinkedList.getAt(7) : " + singleLinkedList.getAt(7));
		singleLinkedList.display();
		
		//removeAt
		System.out.println("\nremoveAt:");
		System.out.println("singleLinkedList.removeAt(0) - head");
		singleLinkedList.removeAt(0);
		singleLinkedList.display();
		System.out.println("singleLinkedList.removeAt(5) - tail");
		singleLinkedList.removeAt(5);
		singleLinkedList.display();
		System.out.println("singleLinkedList.removeAt(5) - out of bound");
		singleLinkedList.removeAt(5);
		singleLinkedList.display();
		System.out.println("singleLinkedList.removeAt(100) - out of bound");
		singleLinkedList.removeAt(100);
		singleLinkedList.display();
		System.out.println("singleLinkedList.removeAt(3) - middle element");
		singleLinkedList.removeAt(3);
		System.out.println("LinkedList.removeFirst()");
		singleLinkedList.removeFirst();
		singleLinkedList.display();
		System.out.println("singleLinkedList.size() : " + singleLinkedList.size());
		
		//insertAt
		System.out.println("\ninsertAt:");
		System.out.println("singleLinkedList.insertAt(0, 100) - head");
		singleLinkedList.insertAt(0, 100);
		singleLinkedList.display();
		System.out.println("singleLinkedList.insertAt(3, 200) - tail");
		singleLinkedList.insertAt(3, 200);
		singleLinkedList.display();
		System.out.println("singleLinkedList.insertAt(5, 300) - out of bound");
		singleLinkedList.insertAt(5, 300);
		singleLinkedList.display();
		System.out.println("singleLinkedList.insertAt(10, 400) - out of bound");
		singleLinkedList.insertAt(10, 400);
		singleLinkedList.display();
		System.out.println("singleLinkedList.insertAt(2, 500) - middle element");
		singleLinkedList.insertAt(2, 500);
		singleLinkedList.display();


		//clear
		System.out.println("\nclear n size:");
		System.out.println("LinkedList.clear() : ");
		singleLinkedList.clear();
		System.out.println("singleLinkedList.size() : " + singleLinkedList.size());
		singleLinkedList.display();
		System.out.println("LinkedList.removeLast()");
		singleLinkedList.removeLast();
		System.out.println("singleLinkedList.removeAt(0) - empty list");
		singleLinkedList.removeAt(5);
		singleLinkedList.display();
	}

}
