package hackerank;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * A Decent Number has the following properties:

Its digits can only be 3's and/or 5's.
The number of 3's it contains is divisible by 5.
The number of 5's it contains is divisible by 3.
It is the largest such number for its length.
 * */
public class SherlockandTheBeast {
	
	 static void decentNumber(int n) {
	        if (n % 3 == 0) {
	            fill(n/3, 0);
	            return;
	        }
	        int maxFives = n / 3;
	        while(maxFives > 0){
	            int remaining = n - (maxFives * 3);
	            if( remaining % 5 == 0){// is remaining 5k
	                fill(maxFives , remaining / 5);
	                return;
	            }
	            maxFives--;
	        }
	        // no 3k in it, now search for 5k
	        if(n % 5 == 0) {
	            fill(0, n/5);
	            return;
	        }
	        System.out.println(-1);
	    }
	    static void fill(int fivecounts, int threecount){
	        char[] s1 = new char[fivecounts * 3];
	        Arrays.fill(s1, '5');
	        char[] s2 = new char[threecount * 5];
	        Arrays.fill(s2, '3');
	        System.out.println(new String(s1) + new String(s2));
	    }

	public static void main(String[] args) {
		System.out.println("input:");
		IntStream.of(1,3,5,11).forEach(System.out::println);
		System.out.println("output:");
		IntStream.of(1,3,5,11).forEach(SherlockandTheBeast::decentNumber);
	}

}
