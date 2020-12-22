package javaCollectionsDS;

import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayDeque;

import java.util.List;


public class DequeImplementations {
	
	static void  displayDequeue(Deque<Integer> deque){
		String st = "";
		for(int i : deque){
			st += i + ", ";
		}
		System.out.println("Deque :" + st);
	}
	
	public static void main(String[] args){
		//***ArrayDeque implements Deque
		System.out.println("******ArrayDeque implements Deque******");
		Deque<Integer> arrayDeque = new ArrayDeque<>();
		// 1) Deque Methods 
		System.out.println("1) Deque Methods");
		System.out.println("peekFirst()/peekLast() returns null if empty :" + arrayDeque.peekFirst());
		try{
			arrayDeque.getLast();
		}catch(Exception e){
			System.out.println("getFirst()/getLast() throws Exception if empty :" + e.getClass());
		}
		arrayDeque.addFirst(1);
		displayDequeue(arrayDeque);
		arrayDeque.removeLast();
		try{
			arrayDeque.removeLast();
		}catch(Exception e){
			System.out.println("removeFist()/removeLast() throws Exception if empty :" + e.getClass());
		}
		System.out.println("pollFirst()/pollkLast() returns null if empty :" + arrayDeque.pollLast());
		displayDequeue(arrayDeque);
		arrayDeque.offerLast(2);
		arrayDeque.addLast(3);
		arrayDeque.addFirst(4);
		arrayDeque.offerFirst(5);//offer like add but not throwing exception (returns false) if full
		arrayDeque.addFirst(6);
		arrayDeque.addFirst(7);
		displayDequeue(arrayDeque);
		System.out.println("removeLast :" + arrayDeque.removeLast());
		System.out.println("removeLast :" + arrayDeque.removeLast());
		displayDequeue(arrayDeque);
		System.out.println("arrayDeque.peekFirst() : " + 		arrayDeque.peekFirst());
		System.out.println("arrayDeque.peekLast() : " + 		arrayDeque.peekLast());
		System.out.println("pollFirst :" + arrayDeque.pollFirst());
		System.out.println("removeFirst :" + arrayDeque.removeFirst());
		displayDequeue(arrayDeque);
		
		// 2) Queue Methods
		System.out.println("2) Queue Methods");
		displayDequeue(arrayDeque);
		System.out.println("arrayDeque.add(10) :" + arrayDeque.add(10));
		System.out.println("arrayDeque.offer(11) :" + arrayDeque.offer(11));
		displayDequeue(arrayDeque);
		System.out.println("arrayDeque.remove() :" + arrayDeque.remove());
		System.out.println("arrayDeque.remove() :" + arrayDeque.remove());
		System.out.println("arrayDeque.remove() :" + arrayDeque.remove());
		System.out.println("arrayDeque.remove() :" + arrayDeque.remove());
		System.out.println("poll() returns null if empty (remove() throws excpetion):" + arrayDeque.poll());
		displayDequeue(arrayDeque);
		try{
			arrayDeque.element();
		}catch(Exception e){
			System.out.println("element() throws Exception if empty (peek() returns null) :" + e.getClass());
		}
		// 3) Stack Methods
		System.out.println("3) Stack Methods");
		arrayDeque.push(40);//Pushes an element onto the stack represented by this deque (in other words, at the head of this deque) 
		System.out.println("arrayDeque.push(40)");
		arrayDeque.push(50);
		arrayDeque.push(60);
		arrayDeque.push(70);
		arrayDeque.push(80);
		displayDequeue(arrayDeque);
		System.out.println("arrayDeque.pop() :" + arrayDeque.pop());
		System.out.println("arrayDeque.peek() :" + arrayDeque.peek());
		
		//**LinkedList implements Deque, List
		System.out.println();
		System.out.println("******LinkedList implements Deque, List******");

		// 1) Deque Methods 
		System.out.println("1) Deque Methods:addFirst removeLast addLast removeFirst offer() getFirst() getLast() peekFirst peekLast");
		Deque<Integer> linkedListDeque = new LinkedList();
		System.out.println("linkedListDeque.peek() :" + linkedListDeque.peek());
		linkedListDeque.addLast(1);
		linkedListDeque.addLast(2);
		linkedListDeque.offerLast(3);
		linkedListDeque.offerFirst(4);
		displayDequeue(linkedListDeque);
		System.out.println("linkedListDeque.pollFirst() :" + linkedListDeque.pollFirst());
		displayDequeue(linkedListDeque);
		System.out.println("linkedListDeque.getFirst() :" + linkedListDeque.getFirst());

		//  2) Queue Methods
		System.out.println("2) Queue Methods:add() offer() remove()  peek() poll() element()");
		linkedListDeque.add(10);//Inserts the specified element into the queue represented by this deque
		linkedListDeque.add(20);
		linkedListDeque.offer(30);
		displayDequeue(linkedListDeque);
		System.out.println("linkedListDeque.poll() :" + linkedListDeque.poll());
		System.out.println("linkedListDeque.remove() :" + linkedListDeque.remove());
		displayDequeue(linkedListDeque);
		System.out.println("linkedListDeque.element() :" + linkedListDeque.element());
		
		// 3) Stack Methods
		System.out.println("3) Stack Methods:push pop peek");
		linkedListDeque.push(100);
		linkedListDeque.push(200);
		linkedListDeque.push(300);
		linkedListDeque.push(400);
		displayDequeue(linkedListDeque);
		System.out.println("linkedListDeque.pop() :" + linkedListDeque.pop());

	   // 4) List Methods : since this LinkedList was created as a  Deque,
		// should cast this LinkedList to List in order to see List Methods
		System.out.println("4) List Methods : since this LinkedList was created as a  Deque, should cast this LinkedList to List in order to see List Methods");
		displayDequeue(linkedListDeque);
		System.out.println("((List) linkedListDeque).get(0) :" + ((List) linkedListDeque).get(0));
		System.out.println("((List) linkedListDeque).add(2, -222) :");
		System.out.println("((List) linkedListDeque).add(999) :");
		System.out.println("((List) linkedListDeque).set(5, -1) :");
		 ((List) linkedListDeque).add(2, -222);//Inserts the specified element at the specified position in this list
		 ((List) linkedListDeque).add(999);//Appends the specified element to the end of this list
		 ((List) linkedListDeque).set(5, -1);//Replaces the element at the specified position in this list with the specified element
		 
		 System.out.println("((List) linkedListDeque).indexOf(30) :" + ((List) linkedListDeque).indexOf(30));
		System.out.println("List : " + ((List) linkedListDeque).toString());
	}

}
