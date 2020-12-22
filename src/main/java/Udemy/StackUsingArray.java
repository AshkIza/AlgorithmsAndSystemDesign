package Udemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*https://www.techiedelight.com/stack-implementation-in-java/
 * stack core operations: push pop peek
 * stack can easily be implemented as array
 * */
public class StackUsingArray {
	int[] arr;
	int capacity;
	int top;//index of last element in (will be first element out) LIFO
	
	StackUsingArray(int N){
		capacity = N;
		arr = new int[N];
		top = -1;//empty stack
	}
	
	void push(int val){
		if (top == capacity -1){
			System.out.println("Stack is full! can NOT push");
			return;
		}
		top++;
		arr[top] = val;
	}
	
	int pop(){
		if(top == -1){
			System.out.println("Stack is empty! can NOT pop");
			return Integer.MIN_VALUE;
		}
		int val = arr[top];
		top--;
		return val;
	}
	
	int peek(){
		if(top == -1){
			System.out.println("Stack is empty! can NOT peek");
			return Integer.MIN_VALUE;
		}
		return arr[top];
	}
	
	int size(){
		return top+1;
	}
	
	void display(){
		String s ="";
		for(int i = top; i>=0; i--){
			s += arr[i] + " ";
		}
		System.out.println("Stack : " + s);
	}
	
	public static void main(String[] args) {
		StackUsingArray stack  = new StackUsingArray(5);
		stack.display();
		stack.pop();
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
