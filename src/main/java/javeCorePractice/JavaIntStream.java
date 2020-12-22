package javeCorePractice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaIntStream {

	public static void main(String[] args) {
		
		List<Integer> listInteger = Arrays.asList(4,5,7,8);
		System.out.println("List<Integer> -> int[]");
		System.out.println("int[] intArray = listInteger.stream().mapToInt(i -> (int) i).toArray();\n" + "");
		int[] intArray = listInteger.stream().mapToInt(i -> (int) i).toArray();

		System.out.println("List<Integer> -> Integer[]");
		System.out.println("Integer[] integerArray = listInteger.stream().mapToInt(i -> (int) i).boxed().toArray(Integer[]::new)");
		Integer[] integerArray = listInteger.stream().mapToInt(i -> (int) i).boxed().toArray(Integer[]::new);
		
		
		System.out.println("\nint[] --> List<Integer>");
		System.out.println("List<Integer> intArrayToList = Arrays.stream(intArray).boxed().collect(Collectors.toList())");
		List<Integer> intArrayToList = Arrays.stream(intArray).boxed().collect(Collectors.toList());

		/* https://www.geeksforgeeks.org/intstream-boxed-java/
		 * https://www.geeksforgeeks.org/intstream-toarray-java-examples/
		 * https://www.geeksforgeeks.org/stream-maptoint-java-examples/
		 * */
		System.out.println("\n\nIntStream methods:");
		System.out.println("	Stream<Integer> boxed()");
		System.out.println("	int[] toArray()");
		
	
		
		System.out.println("Stream method  (Stream -> IntStream)");
		System.out.println("	IntStream mapToInt(ToIntFunction<? super T> mapper)");
		

		/* https://www.geeksforgeeks.org/intstream-sum-java/
		 * */
		System.out.println("\n\nIntStream sum():");
		System.out.println("IntStream sum() returns the sum of elements in this stream."
				+ " \n -> This is a special case of a reduction. IntStream sum() is a terminal operation ");
		IntStream intstream = IntStream.of(2, 4, 6, -2, -4);
		System.out.println("IntStream intstream = IntStream.of(2, 4, 6, -2, -4)");
		System.out.println("intstream.sum() : " + intstream.sum());

		/* https://javadevcentral.com/java-stream-sum
		 * */
		System.out.println("\n\nThree Ways to Sum a Stream of Numbers");
		System.out.println("List<Integer> integers = List.of(1, 2, 3, 4, 5)");
		List<Integer> integers = List.of(1, 2, 3, 4, 5);
		System.out.println("1- mapToInt on the Stream :");
		System.out.println("integers.stream().mapToInt(Integer::intValue).sum()" 
				+ integers.stream().mapToInt(Integer::intValue).sum());
		System.out.println("2-Summing by reducing the stream");
		System.out.println("The reduce method performs a reduction on the stream elements using the provided \n" + 
				"	identity element and an accumulator function.");
		System.out.println("integers.stream().reduce(0, (a,b)-> a+b) : " + 
				integers.stream().reduce(0, (a,b)-> a+b));
		System.out.println("3-Using Collectors.summingInt");
		System.out.println("The Collectors.summingInt is a Collector that sums the primitive integers. "
				+ "\n	It accepts a ToIntFunction (the same passed for the mapToInt method) to convert an element in the stream to an int.");
		System.out.println("integers.stream().collect(Collectors.summingInt(Integer::intValue) : " + 
				integers.stream().collect(Collectors.summingInt(Integer::intValue)));
		
		System.out.println("\n\nIntStream  <---> array");
		IntStream streamFromarray = IntStream.of(2,6,8,9);
		int[] arrayFromStream = streamFromarray.toArray();
		System.out.println("IntStream streamFromarray = IntStream.of(2,6,8,9)");
		System.out.println("int[] arrayFromStream = streamFromarray.toArray()");
		
		//https://www.geeksforgeeks.org/intstream-range-java/
		System.out.println("\n\nIntStream.range() -> iterating through an array/list by index");
		System.out.println("IntStream range(int startInclusive, int endExclusive)");
		System.out.println("IntStream rangeStream  = IntStream.range(1, 10);");
		IntStream rangeStream  = IntStream.range(1, 10);
		rangeStream.forEach( i -> System.out.print(i + " "));
		//https://www.baeldung.com/java-stream-indices
		 System.out.println("\n	How to Iterate Over a Stream With Indices");
		 String[] names  = {"Afrim", "Bashkim", "Besim", "Lulzim", "Durim", "Shpetim"};
		 List<String> nameslist = IntStream.range(0, names.length).mapToObj(i -> names[i]).collect(Collectors.toList());
		 System.out.println("	String[] names");
		 System.out.println("	IntStream.range(0, names.length).mapToObj(i -> names[i])");
	}

}
