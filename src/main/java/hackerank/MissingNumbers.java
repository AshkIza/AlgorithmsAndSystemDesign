package hackerank;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/*  https://www.hackerrank.com/challenges/missing-numbers/problem
 * 
 * Numeros the Artist had two lists that were permutations of one another.
 *  He was very proud. Unfortunately, while transporting them from one exhibition to another,
 *   some numbers were lost out of the first list. 
 *   Can you find the missing numbers?
 * 
 * */
public class MissingNumbers {
	
	
	  static int[] missingNumbers(int[] arr, int[] brr) {
	        if(arr.length == brr.length) return new int[0];
	        Arrays.sort(arr);//missing
	        Arrays.sort(brr);//original
	        int index = 0;
	        int indexDiff = 0;
	        Set<Integer> missing = new TreeSet<>();//sortedSet
	        while( index < arr.length && indexDiff <= brr.length - arr.length){
	            if(brr[index + indexDiff] == arr[index]){
	                index++;
	            }else{
	                missing.add(brr[index + indexDiff]);
	                indexDiff++;
	            }
	        }
	        int ind = index + indexDiff;
	        while( ind < brr.length){
	            missing.add(brr[ind]);
	            ind++;
	        }
	        return  missing.stream().mapToInt(i->(int)i).toArray();
	    }
	  
	   private static void printIntArray(int[] arr) {
	    	String st =  Arrays.stream(arr).boxed()
	    			.map(String::valueOf).reduce("[", (a,b) -> a + " " + b) + "]";
	    	System.out.println(st);
	    	
	    }

	public static void main(String[] args) {
		System.out.println("missingNumbers(\n" + 
				"				new int[] {203, 204, 205, 206, 207, 208, 203, 204, 205, 206},\n" + 
				"				new int[] {203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204})");
		int[] res01 = missingNumbers(
				new int[] {203, 204, 205, 206, 207, 208, 203, 204, 205, 206},
				new int[] {203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204});
		
		int[] res02 = missingNumbers(
				new int[] {1, 2, 3, 4, 5},
				new int[] {1, 2, 3, 4, 5, 6, 7, 8});
		
		printIntArray(res01);
		
		System.out.println("missingNumbers(\n" + 
				"				new int[] {1, 2, 3, 4, 5},\n" + 
				"				new int[] {1, 2, 3, 4, 5, 6, 7, 8})");

		printIntArray(res02);

	
	}

}
