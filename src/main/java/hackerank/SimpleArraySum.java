package hackerank;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* https://www.hackerrank.com/challenges/simple-array-sum/problem
 * 
 * Given an array of integers, find the sum of its elements.

	For example, if the array , , so return .
 * 
 * 
 * */
public class SimpleArraySum {
	
	static int simpleArraySum(int[] ar) {
	        Optional<Integer> op =  Arrays.stream(ar).boxed().reduce( Integer::sum);
	        return op.isPresent() ? op.get() : -1;
	    }


	public static void main(String[] args) {
		
		/* int[]  --> List<Integer>
		 * Arrays.stream(IntArray).boxed
		 * https://stackoverflow.com/questions/1073919/how-to-convert-int-into-listinteger-in-java
		 * 
		 * IntStream boxed() in Java
		 * https://www.geeksforgeeks.org/intstream-boxed-java/
		 * */
		System.out.println("IntStream boxed()");
		System.out.println("IntStream primitiveIntStream = IntStream.range(1, 7)");
		System.out.println("Stream<Integer> integerStream = primitiveIntStream.boxed();");
		IntStream primitiveIntStream = IntStream.range(1, 7);
		Stream<Integer> integerStream = primitiveIntStream.boxed();
		System.out.println(integerStream.collect(Collectors.toList()).toString());
		
		
		System.out.println("\nint simpleArraySum(int[] ar)");
		System.out.println("simpleArraySum(new int[] {1, 2, 3, 4, 10, 11}) : " + simpleArraySum(new int[] {1, 2, 3, 4, 10, 11}));
	}

}
