package Udemy;
/* implement dequeue using 'Circular' Array (fixed-size array)
 * https://www.geeksforgeeks.org/implementation-deque-using-circular-array/
 * */
public class DequeueUsingCircularArray {
	int[] arr;
	int capacity;
	int size;
	int front;
	int rear;
	
	DequeueUsingCircularArray(int N){
		capacity = N;
		arr = new int[N];
		size = 0;
		front = rear = -1;
	}
	
	boolean isEmpty(){
		return size == 0;
	}
	
	boolean isFull(){
		return size == capacity;
	}
	
	void addFirst(int val){
		if(isFull()){
			System.out.println("Dequeue is full. can NOT add");
			return;
		}
		if(front == -1){//in case of empty dequeue
			front = rear = 0;
		}else{
			front = (capacity -1 + front) % capacity;
		}
		arr[front] = val;
		size++;
	}
	
	void addLast(int val){
		if(isFull()){
			System.out.println("Dequeue is full. can NOT add");
			return;
		}
		if(rear == -1){//in case of empty dequeue
			front = rear = 0;
		}else{
			rear = (rear+1) % capacity;
		}
		arr[rear] = val;
		size++;
	}
	
	int removeFirst(){
		if(isEmpty()) {
			System.out.println("Dequeue is empty. can NOT remove");
			return Integer.MIN_VALUE;
		}
		int val = arr[front];
		front = (front +1) % capacity;
		size--;
		if(size == 0){//in case of empty dequeue
			front = rear = -1;
		}
		return val;
	}
	
	int removeLast(){
		if(isEmpty()) {
			System.out.println("Dequeue is empty. can NOT remove");
			return Integer.MIN_VALUE;
		}
		int val = arr[front];
		rear = (capacity -1 + rear) % capacity;
		size--;
		if(size == 0){//in case of empty dequeue
			front = rear = -1;
		}
		return val;
	}
	
	int getFirst(){
		if(isEmpty()) {
			System.out.println("Dequeue is empty. can NOT retrive element");
			return Integer.MIN_VALUE;
		}
		return arr[front];
	}
	
	int getLast(){
		if(isEmpty()) {
			System.out.println("Dequeue is empty. can NOT retrive element");
			return Integer.MIN_VALUE;
		}
		return arr[rear];
	}
	
	void displayDequeue(){
		String st = "Dequeue : ";
		for(int index = front ; index < front + size; index++){
			st +=  arr[index % capacity] + ", ";
		}
		System.out.println(st);
		return;
	}
	
	

	public static void main(String[] args) {
		DequeueUsingCircularArray arrayDeque = new DequeueUsingCircularArray(5);
		arrayDeque.getFirst();
		arrayDeque.removeLast();
		arrayDeque.displayDequeue();
		arrayDeque.addFirst(1);
		arrayDeque.displayDequeue();
		arrayDeque.removeLast();
		arrayDeque.removeLast();
		arrayDeque.displayDequeue();
		arrayDeque.addLast(2);
		arrayDeque.addLast(3);
		arrayDeque.addFirst(4);
		arrayDeque.addFirst(5);
		arrayDeque.addFirst(6);
		arrayDeque.addFirst(7);
		arrayDeque.displayDequeue();
		arrayDeque.removeLast();
		arrayDeque.removeLast();
		arrayDeque.displayDequeue();
		System.out.println("arrayDeque.getFirst() : " + 		arrayDeque.getFirst());
		System.out.println("arrayDeque.getLast() : " + 		arrayDeque.getLast());
		arrayDeque.removeFirst();
		arrayDeque.displayDequeue();



		





		
	}

}
