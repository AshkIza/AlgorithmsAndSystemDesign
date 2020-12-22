package hackerank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://www.hackerrank.com/challenges/diagonal-difference/problem
 * Given a square matrix, calculate the absolute difference between the sums of its diagonals.
   For example, the square matrix  is shown below:

1 2 3
4 5 6
9 8 9  
The left-to-right diagonal = . 
The right to left diagonal = . Their absolute difference is .
 * */
public class DiagonalDifference {
	
	public static int diagonalDifference(List<List<Integer>> arr) {
        int N = arr.size();
        if(N ==0) return -1;
        int leftDiagonal = -1;
        int rightDiagonal = N;
        int leftDiagonalSum = 0;
        int rightDiagonalSum = 0;

        for (List<Integer> row : arr){
            if(leftDiagonal+1 < N && rightDiagonal-1>=0){
                leftDiagonal++;
                rightDiagonal--;
                leftDiagonalSum+= row.get(leftDiagonal);
                rightDiagonalSum+= row.get(rightDiagonal);
            }
        }
        return Math.abs(leftDiagonalSum- rightDiagonalSum);
    }

	public static void main(String[] args) {

		List<List<Integer>> matrix = new ArrayList<>();
		matrix.add(Arrays.asList(11, 2, 4));
		matrix.add(Arrays.asList(4, 5, 6));
		matrix.add(Arrays.asList(10, 8, -12));
		System.out.println(matrix.toString());
		System.out.println("diagonalDifference(matrix) : "  + diagonalDifference(matrix));

		
	
	}

}
