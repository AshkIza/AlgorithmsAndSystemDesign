package javaCollectionsDS;

import java.util.Queue;
import java.util.LinkedList;

public class WeavingQueues {
	
	//ALWAYS ALWAYS TEST YOUR CODE WITH SAMPLE DATA!!!
	static Queue weave(Queue q1, Queue q2){
		Queue q3 = new LinkedList<>();
		while(q1.peek() != null || q2.peek() != null){
			if(q1.peek() != null){
				q3.add(q1.remove());
			}
			if(q2.peek() != null){
				q3.add(q2.remove());
			}
		}
		return q3;
	}

	public static void main(String[] args) {
		Queue q1 = new LinkedList<>();
		Queue q2 = new LinkedList<>();
		System.out.println("q1 " + q1 + ", " + "q2 " + q2);
		Queue weaved = weave(q1, q2);
		System.out.println("weaved " + weaved);
		System.out.println();
		
		q1.add(1);
		q1.add(2);
		q1.add(3);
		q1.add(4);
		q2.add("a");
		q2.add("b");
		q2.add("c");
		q2.add("d");
		q2.add("e");
		q2.add("f");
		q2.add("g");
		q2.add("h");
		System.out.println("q1 " + q1 + ", " + "q2 " + q2);
	    weaved = weave(q1, q2);
		System.out.println("weaved " + weaved);
		System.out.println();

	}

}
