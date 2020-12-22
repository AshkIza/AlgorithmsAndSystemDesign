package hackerank;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SumXor {
	static long sumXor(long n) {
        long nBits = ((long)(Math.log(n)/Math.log(2))) + 1;
        long bitrank = 0;
        long res = 1;
        while(bitrank < nBits){
        	long a =  (long) 1 << bitrank; // 1 << bitrank; Math.pow(2, bitrank)
            long b = n & a;
            if(b==0) res *= 2;
            bitrank++;
        }
        return res;
    }

	
	public static void main(String[] args) {
		System.out.println("** when dealng with long numbers, make sure to always use"
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
		
		System.out.println(String.format("\nsumXor(%s) : ", 1000000000000000L) + sumXor(1000000000000000L));

	}

}
