package Udemy;

/* Note: when working with linked list, always approach solution from both Head and tail before
 * going to doubly-linked list
 * some problems can be solvd from Head (stack.push stack.pop, queue.Dequeue)
 * some problems can be solved from tail (queue.enqueue)
 *   Head--> Node --> Node --> ... --> Node --> Tail
 *   
 *   https://www.geeksforgeeks.org/implement-a-stack-using-singly-linked-list/
 * */
public class StackUsingLinkedList { //LinkedStack
	
	private class Node{
		int value;
		Node next;
		
		Node(int val){
			value = val;
		}
	}
	
	Node top;
	StackUsingLinkedList(){
		top = null;//empty stack
	}
	
	void push(int val){
		Node n = new Node(val);
		n.next = top;
		top = n;
	}
	
	int pop(){
		if(top == null){
			System.out.println("Stack empty. can NOT pop");
			return Integer.MIN_VALUE;
		}
		Node n = top;
		top = top.next;
		return n.value;
	}
	
	int peek(){
		if(top == null){
			System.out.println("Stack empty. can NOT peek");
			return Integer.MIN_VALUE;
		}
		return top.value;
	}
	
	void display(){
		String st = "Stack: ";
		Node n = top;
		while (n != null){
			st +=  n.value +", ";
			n = n.next;
		}
		System.out.println(st);
	}
	

	public static void main(String[] args) {
		StackUsingLinkedList stack = new StackUsingLinkedList();
		stack.peek();
		stack.pop();
		stack.display();
		
		stack.push(10);
		stack.push(11);
		stack.push(12);
		stack.push(13);
		stack.display();
		stack.push(14);
		stack.push(15);
		stack.push(16);
		stack.push(17);
		stack.push(18);
		stack.display();
		
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.push(19);
		stack.push(120);
		stack.display();
		

	}

}
