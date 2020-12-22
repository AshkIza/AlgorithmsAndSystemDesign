package hackerank;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*  https://www.hackerrank.com/challenges/priyanka-and-toys/problem
 * 
 * Priyanka works for an international toy company that ships by container.
 *  Her task is to the determine the lowest cost way to combine her orders for shipping. 
 *  She has a list of item weights. 
 *  
 *  The shipping company has a requirement that all items loaded in a container
 *  	 must weigh less than or equal to 4 units plus the weight of the minimum weight item. 
 *  		All items meeting that requirement will be shipped in one container.

What is the smallest number of containers that can be contracted to ship the items based on 
	the given list of weights?
 * 
 * */
public class PriyankaAndToys {
	
	   static int toys(int[] w) {
	        Arrays.sort(w);
	        int min = w[0];
	        int count = 1;
	        for(int i = 1; i < w.length; i++){
	            if(w[i] - min > 4){
	                count++;
	                min = w[i];
	            }
	        }
	        return count;
	    }

	public static void main(String[] args) {
		Supplier<IntStream> case01 = () -> IntStream.of(16, 18, 10, 13, 2, 9, 17, 17, 0, 19);
		System.out.println("case01 : " + case01.get().mapToObj( i -> String.valueOf(i)).collect(Collectors.toList()));
		System.out.println("toys(case01) : " + toys(case01.get().toArray()));
		

	}

}
