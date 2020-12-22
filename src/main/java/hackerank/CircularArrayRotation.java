package hackerank;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* https://www.hackerrank.com/challenges/circular-array-rotation/problem
 * 
 * For each array, perform a number of right circular rotations and return the value of the element at a given index.
 * 
 * */
public class CircularArrayRotation {
	
   static int[] circularArrayRotation(int[] a, int k, int[] queries) {
	     int kMod = k % a.length; // 0 < < a.length-1
	     int[] results = new int[queries.length];
	     for(int i = 0; i < queries.length ; i++){
	         int index = queries[i] - kMod;
	         int indexP = index < 0 ? (index + a.length) : index;
	         results[i] = a[indexP];
	     }
	     return results;
	}

	public static void main(String[] args) {
		int[] originalArray = IntStream.of(1,2,3).toArray();
		int[] queriesIndices = IntStream.of(0,1,2).toArray();
		int[] queryResults = circularArrayRotation(originalArray, 50, queriesIndices);
		System.out.println("originalArray :" + Arrays.stream(originalArray).boxed().collect(Collectors.toList()));
		System.out.println("queryResults = circularArrayRotation(originalArray, 50, queriesIndices)" );
		System.out.println("queriesIndices :" + Arrays.stream(queriesIndices).boxed().collect(Collectors.toList()));
		System.out.println("queryResults :" + Arrays.stream(queryResults).boxed().collect(Collectors.toList()));
	}

}
