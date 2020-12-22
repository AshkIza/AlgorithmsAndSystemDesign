package hackerank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*  https://www.hackerrank.com/challenges/closest-numbers/problem
 * 
 * Sorting is useful as the first step in many different tasks.
 *  The most common task is to make finding things easier, 
 *  but there are other uses as well. In this case, 
 *  	it will make it easier to determine which pair or pairs of elements have the smallest absolute difference between them.
 * 
 * */
public class ClosestNumbers {
	
    static int[] closestNumbers(int[] arr) {
        Arrays.sort(arr);//sorting array first
        int MinDistance = arr[1] - arr[0];//array size >=2
        for(int i = 0 ; i < arr.length-1; i++){
            int nextDistance = arr[i+1] - arr[i];//always positive and relatively closet
            MinDistance = nextDistance <= MinDistance ? nextDistance : MinDistance;
        }
        List<Integer> elem = new ArrayList<>();
        for(int i = 0 ; i < arr.length-1; i++){
            if(arr[i+1] - arr[i] == MinDistance){
                elem.add(arr[i]);
                elem.add(arr[i+1]);
            }
        }
        return elem.stream().mapToInt( i -> (int) i).toArray();// List<Integer> --> int[]
    }
    
    private static void printIntArray(int[] arr) {
    	String st =  Arrays.stream(arr).boxed()
    			.map(String::valueOf).reduce("[", (a,b) -> a + " " + b) + "]";
    	System.out.println(st);
    	
    }

	public static void main(String[] args) {
		int[] inputArray = new int[] {-20, -3916237, -357920, -3620601, 7374819,
				-7330761, 30, 6246457, -6461594, 266854, -520, -470 };
		System.out.println("closestNumbers(-20, -3916237, -357920, -3620601, 7374819, -7330761, 30, 6246457, -6461594, 266854, -520, -470)");
		printIntArray(closestNumbers(inputArray));
		
		/* https://www.geeksforgeeks.org/intstream-boxed-java/
		 * https://www.geeksforgeeks.org/intstream-toarray-java-examples/
		 * https://www.geeksforgeeks.org/stream-maptoint-java-examples/
		 * */
		System.out.println("\n\nIntStream methods:");
		System.out.println("	Stream<Integer> boxed()");
		System.out.println("	int[] toArray()");
		
		System.out.println("Stream method  (Stream -> IntStream)");
		System.out.println("	IntStream mapToInt(ToIntFunction<? super T> mapper)");

	}

}
