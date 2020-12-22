package hackerank;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/* https://www.hackerrank.com/challenges/lonely-integer/problem
 * 
 * You will be given an array of integers. All of the integers except one occur twice. That one is unique in the array.

Given an array of integers, find and print the unique element.

For example, , the unique element is .
 * 
 * */
public class LonelyInteger {
	
	 static int lonelyinteger(int[] a) {
	        int re = 0;
	        for(int element : a){
	            re ^= element;
	        }
	        return re;
	    }
	 

	public static void main(String[] args) {
		int[][] integerarrays = new int[][] 
				{{0, 0, 1, 2, 1},
				{1, 1, 2},		
				{4, 9, 95, 93, 57, 4, 57, 93, 9}};
				
		Arrays.stream(integerarrays).forEach( arr -> {
			System.out.print("array " + Arrays.stream(arr).boxed().collect(Collectors.toList()));
			System.out.println(" -> lonelyinteger(array) : " + lonelyinteger(arr));
		});
		
		
		/* XORing a 32 bit integer with itself
		 * https://stackoverflow.com/questions/40144208/xoring-a-32-bit-integer-with-itself
		 * 
		 * In general Xoring a number with itself should provide you with the value 0
		 * */
		System.out.println("\n\nXORing a 32 bit integer with itself");
		System.out.println("In general Xoring a number with itself should provide you with the value 0\n");
		IntStream.of(-839404, 2433, -282, 3, 0).forEach( i-> System.out.println(i + " ^ " + i + " = " + (i ^ i)));
		LongStream.of(1L, 384738723987L, -8484884).forEach( i-> System.out.println(i + " ^ " + i + " = " + (i ^ i)));
	}

}
