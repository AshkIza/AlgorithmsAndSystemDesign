package hackerank;

import java.util.Arrays;
import java.util.List;

/*
 * You are in charge of the cake for a child's birthday.
 *  You have decided the cake will have one candle for each year of their total age. They will only be able to blow out the tallest of the candles. Count how many candles are tallest.


3 2 1 3
Your Output (stdout)
2
 * */
public class BirthdayCakeCandles {
	
	public static int birthdayCakeCandles(List<Integer> candles) {
	     final Integer tallest = candles.stream().max(Integer::compare).get();
	     return (int) candles.stream().filter(cancle -> cancle.equals(tallest)).count();

	}

	public static void main(String[] args) {
		/* https://www.geeksforgeeks.org/stream-max-method-java-examples/
		 * https://www.geeksforgeeks.org/stream-count-method-java/
		 * */
		System.out.println("Stream.max() ");
		System.out.println("Optional<T> max(Comparator<? super T> comparator)\n" + 
				" ");
		System.out.println("Stream count() ");
		System.out.println("long count() ");
		

		System.out.println("\nbirthdayCakeCandles(Arrays.asList(3, 2, 1, 3)) : " + 
				birthdayCakeCandles(Arrays.asList(3, 2, 1, 3)));

	}

}
