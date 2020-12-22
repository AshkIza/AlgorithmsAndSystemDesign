package hackerank;

import java.util.Arrays;

/*  https://www.hackerrank.com/challenges/two-arrays/problem
 * 
 * Consider two -element arrays of integers A, B, 
 *   You want to permute them into some  A' and B' such that 
 *   	the relation  A'[i] + B'[i] >= kholds for all 
 * */
public class PermutingTwoArrays {
	
	  static String twoArrays(int k, int[] A, int[] B) {
	        int N = A.length;
	        Arrays.sort(A);
	        Arrays.sort(B);
	        for(int i =0; i < N; i++ ){
	            //compare A[i] with B[N-1-i]
	            if(A[i] + B[N-1-i] < k) return "NO";
	        }
	        return "YES";
	    }

	public static void main(String[] args) {
		/*
		 *  3 10
			2 1 3
			7 8 9
		 * */
		System.out.println(twoArrays(10, new int[] {2,1,3}, new int[] {7, 8, 9}));
		/*
			4 5
			1 2 2 1
			3 3 3 4
		 * */
		System.out.println(twoArrays(5, new int[] {1,2,2,1}, new int[] {3,3,3,4}));

	}

}
