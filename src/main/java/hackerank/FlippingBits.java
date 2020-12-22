package hackerank;

import java.util.stream.LongStream;

/* https://www.hackerrank.com/challenges/flipping-bits/problem
 * You will be given a list of 32 bit unsigned integers. 
 * Flip all the bits ( and ) and print the result as an unsigned integer.

 * */
public class FlippingBits {
	
	   static long flippingBits(long n) {
	        long allOne = (long)Math.pow(2, 32) -1 ; 
	        return n ^ allOne;
	    }


	public static void main(String[] args) {
		LongStream.of(2147483647,1,0).forEach( l -> {
			System.out.println(String.format("flippingBits(%d) : ", l) + flippingBits(l));
		});
		
		
		long allOne = (long)Math.pow(2, 32) -1;
		System.out.println("\nhow to show 32-bit unsigned int ->>> using long");
		System.out.println(" "
				+ "32-bit allOne -> long allOne = (long)Math.pow(2, 32) -1 : " + allOne);
		

	}

}
