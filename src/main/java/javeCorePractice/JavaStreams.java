package javeCorePractice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreams {
	static class Serveredtime {
        Integer servedtime;
        Integer customerNum;
        Serveredtime(Integer servedtime, int customerNum){
            this.servedtime = servedtime;
            this.customerNum = customerNum;
        }
        @Override
		public String toString() {
        	return "servedtime : " + servedtime + ", customerNum : " + customerNum + "\n";
        }
    }
	static int[][] case02 = new int[][] { 
		  {8, 1},
		  {4, 5},
		  {5, 6},
		  {6, 1},
		  {3, 3},
		  {3, 1},
		  {4, 3}};
	static Supplier<Stream<Serveredtime>> serveredtimeSupplier = () -> {
		List<Serveredtime> list = new ArrayList<Serveredtime>();
		for(int[] entry : case02) {
			list.add(new Serveredtime(entry[0], entry[1]));
		}
		return list.stream();
	};

	public static void main(String[] args) {
		// https://stackoverflow.com/questions/36255007/is-there-any-way-to-reuse-a-stream/36255069
		System.out.println("1)reuse a Stream ? ");
		System.out.println(" ONLY 1 Terminal operation allowed "
				+ "\n  A stream should be operated on (invoking an intermediate or terminal stream operation) only once.\n" + 
				"\n" + 
				"A stream implementation may throw IllegalStateException if it detects that the stream is being reused.");
		System.out.println("\nHOWEVER, If you want to have the effect of reusing a stream, "
				+ "\nyou might wrap the stream expression in a Supplier and call myStreamSupplier.get() whenever you want a fresh one ");
		Supplier<IntStream> sup = () -> IntStream.of(1,2,3,4,5);
		int minimumValue = sup.get().min().getAsInt();
		Stream<Integer> streamOfInteger = sup.get().mapToObj(Integer::new);
		System.out.println("\n	Supplier<IntStream> sup = () -> IntStream.of(1,2,3,4,5);");
		System.out.println("	int minimumValue = sup.get().min().getAsInt()");
		System.out.println("	Stream<Integer> streamOfInteger = sup.get().mapToObj(Integer::new);");
		
		System.out.println("\n2)min value of an int array with stream ->  IntStream.of(args).min().getAsInt()");
		//https://www.geeksforgeeks.org/intstream-maptoobj-java/
		System.out.println("\n3)IntStream mapToObj : "
				+ "\n <U> Stream<U> mapToObj(IntFunction<? extends U> mapper)\n" + 
				"\n Stream<Integer> streamInteger = IntStream.of(7,9,9,78,5).mapToObj(Integer::new)");
		
		System.out.println("\n4) Stream mapToInt");
		System.out.println("	IntStream mapToInt(ToIntFunction<? super T> mapper)");
		//https://www.geeksforgeeks.org/stream-maptoint-java-examples/
		Stream<Integer> integerStream = Stream.of(1,2,3,4);
		IntStream streamToIntstream = integerStream.mapToInt(integer -> (int) integer);
		System.out.println("		Stream<Integer> integerStream = Stream.of(1,2,3,4);");
		System.out.println("		IntStream streamToIntstream = integerStream.mapToInt(integer -> (int) integer);");
		
		System.out.println("\n5)stream.sorted() using costume comparator & chaining comparators (thenComparing)");
		//https://stackoverflow.com/questions/29964108/sorting-using-comparator-interface-and-java-8-streams
		Comparator<Serveredtime> sortByServedtime = (s1, s2) -> s1.servedtime.compareTo(s2.servedtime);
		Comparator<Serveredtime> sortByCustomerNumber = (s1, s2) -> s1.customerNum.compareTo(s2.customerNum);
		Comparator<Serveredtime> sortByServeTimeThenCustomerNumber = sortByServedtime.thenComparing(sortByCustomerNumber);
		System.out.println("	Comparator<Serveredtime> sortByServedtime = (s1, s2) -> s1.servedtime.compareTo(s2.servedtime)");
		System.out.println("	Comparator<Serveredtime> sortByCustomerNumber = (s1, s2) -> s1.customerNum.compareTo(s2.customerNum);");
		System.out.println("	Comparator<Serveredtime> sortByServeTimeThenCustomerNumber = sortByServedtime.thenComparing(sortByCustomerNumber);");
		System.out.println("serveredtimeSupplier.get().sorted(sortByServedtime) \n" + 
				serveredtimeSupplier.get().sorted(sortByServedtime).collect(Collectors.toList()));
		System.out.println("serveredtimeSupplier.get().sorted(sortByServeTimeThenCustomerNumber) \n" + 
				serveredtimeSupplier.get().sorted(sortByServeTimeThenCustomerNumber).collect(Collectors.toList()));

		
		//https://www.baeldung.com/java-lambda-effectively-final-local-variables
		System.out.println("\n\nLocal Variables Used in Lambdas Have to Be Final or Effectively Final");
		System.out.println("following gives compilation error (not 'effectively' final)!");
		System.out.println("final int count = 0;");
		System.out.println("IntStream.range(1,10).forEach(i -> count +=i)");

		
		
	}

}
