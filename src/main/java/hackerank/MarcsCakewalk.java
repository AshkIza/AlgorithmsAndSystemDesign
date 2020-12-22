package hackerank;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * https://www.hackerrank.com/challenges/marcs-cakewalk/problem
 * 
 *  Each cupcake has a calorie count, and Marc can walk a distance to expend those calories. 
 *  If Marc has eaten  cupcakes so far, after eating a cupcake with  calories he must walk at least
 *  	  miles to maintain his weight.

For example, if he eats  cupcakes with calorie counts in the following order: , 
the miles he will need to walk are . This is not the minimum, though, so we need to test other orders of consumption. In this case, our minimum miles is calculated as .

Given the individual calorie counts for each of the cupcakes, 
determine the minimum number of miles Marc must walk to maintain his weight. Note that he can eat the cupcakes in any order.


 * 
 * */
public class MarcsCakewalk {
	
	
    static long marcsCakewalk(int[] calorie) {
        Arrays.sort(calorie);
        long minsum = 0;
        long base = 1;
        for(int i = calorie.length -1; i >= 0 ; i--){
            minsum += (long) (base * calorie[i]);
            base *=2;
        }
        return minsum;
    }

	public static void main(String[] args) {
		System.out.println("marcsCakewalk(7, 4, 9, 6) : " + marcsCakewalk(IntStream.of(7, 4, 9, 6).toArray()));
		System.out.println("marcsCakewalk(504, 378, 291, 380, 728, 630, 797, 212, 32, 792, 895, 635, 850, 853, 859, 127, 653, 655, 476, 748)) : " 
				+ marcsCakewalk(IntStream.of(504, 378, 291, 380, 728, 630, 797, 212, 32, 792, 895, 635, 850, 853, 859, 127, 653, 655, 476, 748).toArray()));

		
		
	}

}
