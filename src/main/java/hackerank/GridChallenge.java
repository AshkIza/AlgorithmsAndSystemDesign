package hackerank;

import java.util.Arrays;
import java.util.stream.Collectors;

/* https://www.hackerrank.com/challenges/grid-challenge/problem
 * 
 * Given a square grid of characters in the range ascii[a-z], 
 * rearrange elements of each row alphabetically, ascending. 
 * Determine if the columns are also in ascending alphabetical order, 
 * 	top to bottom. Return YES if they are or NO if they are not.

For example, given:

a b c
a d e
e f g

The rows are already in alphabetical order.
 The columns a a e, b d f and c e g are also in alphabetical order,
  so the answer would be YES. Only elements within the same row can be rearranged.
   They cannot be moved to a different row.
 * 
 * 
 * */
public class GridChallenge {
	
	static String gridChallenge(String[] grid) {
        if(grid.length == 1) return "YES";
        int rowcount = 0 ;
        while(rowcount+1 < grid.length){
            char[] preLine = grid[rowcount].toCharArray();
            Arrays.sort(preLine);
            rowcount++;
            char[] nextLine = grid[rowcount].toCharArray();
            Arrays.sort(nextLine);
            if(!areSorted(preLine, nextLine)) return "NO";
        }
        return  "YES";
    }
 
    static boolean areSorted(char[] top, char[] below){
        for(int i = 0 ; i < top.length; i++){
            if(top[i] > below[i]) return false;
        }
        return true;
    }

	public static void main(String[] args) {
		String[] input = new String[] {
				"eabcd",
				"fghij",
				"olkmn",
				"trpqs",
				"xywuv"};
		System.out.println("input : \n" + Arrays.stream(input).map(st -> "\n" + st ).collect(Collectors.toList()));
		System.out.println( "gridChallenge(input) :" + gridChallenge(input));
		
		/* Sorting char array in Java example
		 * https://beginnersbook.com/2014/07/sorting-char-array-in-java-example/
		 * */
		System.out.println("\nSorting char array in Java");
		System.out.println("1) Complete sorting using sort(char[] a)  "
				+ "\n2) Sorting specified range of characters only using sort(char[] a, int fromIndex, int toIndex) ");
		char[] charArray = new char[] { 'A', 'Q', 'S', 'Z', 'P' };
		Arrays.sort(charArray);
		System.out.println("char[] charArray = new char[] { 'A', 'Q', 'S', 'Z', 'P' }");
		System.out.println("Arrays.sort(charArray)");
		
	
	}

}
