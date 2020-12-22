package javaCollectionsDS;

import java.util.AbstractList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

// class Stack<E>  extends Vector<E> 
// class Vector<E> implements List<E>      (Vector is legacy, but synchronized)

/*Vector: If you need synchronization, 
 * a Vector will be slightly faster than an ArrayList synchronized with Collections.synchronizedList.
But Vector has loads of legacy operations*/

public class ListImplementationAsStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> stackMethods = new Stack<>();

	}

}
