package Udemy;

import java.util.Stack;
/* https://www.geeksforgeeks.org/queue-using-stacks/
 * */
public class QueueUsingStack {
	Stack<Integer> s1;
	Stack<Integer> s2;
	
	QueueUsingStack(){
		s1 = new Stack<>();
		s2 = new Stack<>();
	}

	void enqueue(int val){
		s1.push(val);
	}
	
	void dequeue(){
		if(s2.isEmpty()){//deal with cases when s2 is empty
			if(s1.isEmpty()){
				System.out.println("Queue is Empty. can NOT dequeue!");
				return;
			}
			while(!s1.isEmpty()){
				s2.push(s1.pop());
			}
		}
		System.out.println("dequeueing Queue element:" + s2.pop());
	}
	
	int peek(){
		if(s2.isEmpty()){//deal with cases when s2 is empty
			if(s1.isEmpty()){
				System.out.println("Queue is Empty. can NOT peek!");
				return Integer.MIN_VALUE;
			}
			while(!s1.isEmpty()){
				s2.push(s1.pop());
			}
		}
		return s2.peek();
	}
	
	public static void main(String[] args) {
		QueueUsingStack queue = new QueueUsingStack();
		queue.dequeue();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(8);
		queue.enqueue(9);
		queue.enqueue(10);
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(11);
		queue.enqueue(12);
		queue.enqueue(13);
		queue.enqueue(14);
		queue.enqueue(15);
		queue.enqueue(16);
		queue.enqueue(17);
		queue.enqueue(18);
		queue.enqueue(19);
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();

	}

}
