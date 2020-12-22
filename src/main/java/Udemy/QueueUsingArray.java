package Udemy;

/*
 * https://www.techiedelight.com/queue-implementation-in-java/
 * using size to detect empty and full (underflow and overflow)
 * front and rear index are shifting 
 * */
public class QueueUsingArray {
	
	int[] arr;
	int capacity;//array length
	int size;// empty and full indicator (since we can not define null for primitive int)
	int front;//index for dequeue
	int rear;//index for enqueue (pointing to the next index to be filled)
	
	QueueUsingArray(int capacity){
		this.capacity = capacity;
		front = 0;rear = 0; size = 0;
		arr = new int[capacity];
	}
	
	boolean enqueue(int val){
		if (size == capacity) {//queue is full
			System.out.println("Queue is full. can not enqueue");
			return false;
		}
		if (rear == capacity) rear = 0;
		arr[rear] = val;
		size++; rear++;
		return true;
	}
	
	int dequeue(){
		if(size == 0) {//queue is empty
			System.out.println("Queue is empty. can not dequeue");
			return Integer.MIN_VALUE;
		}
		int val = arr[front];
		front++; size--;
		if(front == capacity) front = 0;
		return val;
	}
	
	int peek(){
		if(size == 0) {//queue is empty
			System.out.println("Queue is empty. can not peek");
			return Integer.MIN_VALUE;
		}
		return arr[front];
	}
	
	//ALWAYS ALWAYS test your code (using your sample test sample)
	void displayQueue(){
		int index = front;
		int count = 0;
		String display = "[";
		while (count < size){
			display = display + arr[index] + " ";
			index = (index + 1) % capacity; // index ++; if index == capacity index = 0;
			count++;//commenting this out will make this an infinite loop!
		}
		display = display + "]";
		System.out.println(display);
	}
	
  
	public static void main(String[] arg){
		QueueUsingArray queue = new QueueUsingArray(5);
		queue.dequeue();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.displayQueue();
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(8);
		queue.enqueue(9);
		queue.enqueue(10);
		queue.displayQueue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.displayQueue();
		queue.dequeue();
		queue.dequeue();
		queue.displayQueue();
		queue.enqueue(11);
		queue.enqueue(12);
		queue.enqueue(13);
		queue.enqueue(14);
		queue.enqueue(15);
		queue.enqueue(16);
		queue.displayQueue();
		queue.enqueue(17);
		queue.enqueue(18);
		queue.enqueue(19);
		queue.displayQueue();
	}

}
