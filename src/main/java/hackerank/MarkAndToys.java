package hackerank;

import java.util.Arrays;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*  https://www.hackerrank.com/challenges/mark-and-toys/problem
 * 
 * Greedy and Sort
 * 
 * Mark and Jane are very happy after having their first child. 
 * Their son loves toys, so Mark wants to buy some.
 *  There are a number of different toys lying in front of him, 
 *  	tagged with their prices. Mark has only a certain amount to spend,
 *  	 and he wants to maximize the number of toys he buys with this money. 
 *  Given a list of toy prices and an amount to spend, 
 *  	determine the maximum number of gifts he can buy.
 * 
 * 
 * 
 * */
public class MarkAndToys {

	   static int maximumToys(int[] prices, int k) {
	       Arrays.sort(prices);
	       int p = 0;
	       int count = 0;
	       for(int i = 0; i < prices.length; i++){
	           if(p + prices[i] <=k){
	               p +=prices[i];
	               count++;
	           }else{
	               return count;
	           }
	       }
	       return count;
	    }
	   
	public static void main(String[] args) {
		/*
		 * 7 50
		   1 12 5 111 200 1000 10
		 * 
		 * */
		Supplier<IntStream> case01 = () -> IntStream.of(1, 12, 5, 111, 200, 1000, 10);
		System.out.println( 50 + " " + 
				case01.get().mapToObj(Integer::valueOf).collect(Collectors.toList()));
		System.out.println(maximumToys(case01.get().toArray(), 50));
	}

}
