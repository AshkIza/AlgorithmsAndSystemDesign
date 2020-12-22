package javeCorePractice;

import java.util.stream.IntStream;

public class JavaBitWise {

	public static void main(String[] args) {
		
		System.out.println("reverese Bits (n) : ");
		System.out.println("int numberBits = ((int)(Math.log(n)/Math.log(2))) + 1");
		System.out.println("int allOne = (int) Math.pow(2, numberBits) - 1");
		System.out.println("int reversed = n ^ allOne\n");
		IntStream.of(22, 1, 55, 10012).forEach( n -> {
			int numberBits = ((int)(Math.log(n)/Math.log(2))) + 1;
			int allOne = (int) Math.pow(2, numberBits) - 1;
			int reversed = n ^ allOne;
			System.out.println(String.format("reverese Bits (%s) = %S", n, reversed));
		});
		
		
		System.out.println("\n\n** when dealng with long numbers, make sure to always use"
				+ "	long variable/cast --> not to loose precesion**");
		System.out.println("EXAMPLE of loosing precesion by"
				+ " NOT casting the left-wise bit shift to long:");
		IntStream.of(30, 40, 50).forEach( nBits -> {
			long f = 1 << nBits;
			long a = (long) 1 << nBits;
			long b = (long)Math.pow(2, nBits); 
			System.out.println("nBits =" + nBits);
			System.out.println(String.format("	       1 << %d        : ", nBits)  + f);
			System.out.println(String.format("	(long) 1 << %d        : ", nBits)  + a);
			System.out.println(String.format("	(long)Math.pow(2, %d) : ", nBits) + b);
		});
		
		long allOne = (long)Math.pow(2, 32) -1;
		System.out.println("\n\nhow to show 32-bit unsigned int ->>> using long");
		System.out.println(" "
				+ "32-bit allOne -> long allOne = (long)Math.pow(2, 32) -1 : " + allOne);
		
		
		

	}

}
