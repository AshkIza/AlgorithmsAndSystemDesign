package hackerank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* https://www.hackerrank.com/challenges/grading/problem
 * 
 * Sam is a professor at the university and likes to round each student's  according to these rules:

If the difference between the  and the next multiple of  is less than , round  up to the next multiple of .
If the value of  is less than , no rounding occurs as the result will still be a failing grade.
 * 
 * */
public class GradingStudents {
	
	   public static List<Integer> gradingStudents(List<Integer> grades) {
	        return grades.stream().mapToInt(Integer::valueOf)
	            .map(grade -> round(grade)).boxed().collect(Collectors.toList());
	    

	    }
	    private static int round(int grade){
	        if(grade < 38 || grade%5 ==0) return grade;
	        int nextMultiple = ((grade / 5 ) + 1) * 5;
	        return (nextMultiple -  grade) < 3 ?  nextMultiple : grade;
	    }


	public static void main(String[] args) {
		
		/* https://www.geeksforgeeks.org/stream-maptoint-java-examples/
		 * https://www.geeksforgeeks.org/intstream-boxed-java/
		 * https://howtodoinjava.com/java8/convert-intstream-collection-array/
		 * */
		System.out.println("IntStream mapToInt(ToIntFunction<? super T> mapper)");
		System.out.println("Stream<Integer> boxed()\n");
		List<Integer> listOfInteger = Arrays.asList(15, 26, -40, 1987);
		IntStream streamInt = listOfInteger.stream().mapToInt(Integer::valueOf);
		Stream<Integer> streamInteger = streamInt.boxed();
		System.out.println(" IntStream streamInt = listOfInteger.stream().mapToInt(Integer::valueOf)");
		System.out.println(" Stream<Integer> streamInteger = streamInt.boxed()");
		System.out.println(streamInteger.collect(Collectors.toList()).toString());
		
		List<Integer> grades = Arrays.stream(new int[] {73,67,38,33}).boxed().collect(Collectors.toList());
		System.out.println("\ngrades , before : " + grades);
		System.out.print("	grades , gradingStudents() " + gradingStudents(grades));	
	}

}
