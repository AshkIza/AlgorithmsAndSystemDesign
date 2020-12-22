package Udemy;

public class QueueUsingArrayV02 {
	int[] arr;
	int capacity;
	int tail;//head always index 0
	
	QueueUsingArrayV02(int N){
		capacity = N;
		arr = new int[N];
		tail = -1;//empty queue
	}
	
	void enqueue(int val){
		if(tail + 1 == capacity){
			System.out.println("Queue is full. can not enqueue");
			return;
		}
		tail++;
		arr[tail] = val;
	}
	
	int dequeue(){
		if(tail == -1){
			System.out.println("Queue is empty. can not dequeue");
			return Integer.MIN_VALUE;
		}
		int val = arr[0];//head(first element) always at element 0
		for(int i = 1; i<= tail; i++){
			arr[i-1] = arr[i];
		}
		tail--;
		return val;
	}
	
	void displayQueue(){
		if(tail == -1){
			System.out.println("Queue is empty. can not dequeue");
			return;
		}
		String s = "";
		for(int i = 0; i <= tail; i++){
			s = s + arr[i] + " ";
		}
		System.out.println(s);
	}

	public static void main(String[] args) {
		QueueUsingArrayV02 queue = new QueueUsingArrayV02(5);
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
