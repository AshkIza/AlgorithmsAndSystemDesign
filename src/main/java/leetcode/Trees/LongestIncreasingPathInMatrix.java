package leetcode.Trees;

import java.util.Arrays;

/*  Given an integer matrix, find the length of the longest increasing path.

	From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
	
	Example 1:
	
	Input: nums = 
	[
	  [9,9,4],
	  [6,6,8],
	  [2,1,1]
	] 
	Output: 4 
	Explanation: The longest increasing path is [1, 2, 6, 9].
	Example 2:
	
	Input: nums = 
	[
	  [3,4,5],
	  [3,2,6],
	  [2,2,1]
	] 
	Output: 4 
	Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
	
	solution : 
	https://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/
 * 
 * */
public class LongestIncreasingPathInMatrix {
	
	
	public static int longestIncreasingPath(int[][] matrix) {
		int M = matrix.length;
		if(M == 0){
			return 0;
		}
		int N = matrix[0].length;
		int[][] ipl = new int[M][N];// memoized-cache (Dynamic programming) overlapping sub-problems
		for(int i = 0; i < M; i++){
			Arrays.fill(ipl[i], -1);
		}
		int lip = 0;
		for(int i = 0 ; i < M; i++){
			 for(int j = 0 ; j < N; j++){
				 int result = increasingPathLength(matrix, i , j, ipl);
				 if(result > lip){
					 lip = ipl[i][j];
				 }
			 }
		}
		return lip + 1;
	}
	
	public static int increasingPathLength(int[][] matrix, int m , int n, int[][] ipl) {
		if(ipl[m][n] != -1){//already calculated from other overlapping sub-problems
			 return ipl[m][n];
		 }
		
		int M = matrix.length;
		int N = matrix[0].length;
		int l = 0;// all 4 directions
		int r = 0;
		int u = 0;
		int d = 0;
		// checking increasing condition, and solve it recursively (sub-problems)
		if( n+1 < N && matrix[m][n+1] > matrix[m][n]){//right
			r =  1 + increasingPathLength(matrix, m, n+1, ipl);
		}
		if( n-1 >= 0 && matrix[m][n-1] > matrix[m][n]){//left
			l =  1 + increasingPathLength(matrix, m, n-1, ipl);
		}
		if( m+1 < M && matrix[m+1][n] > matrix[m][n]){//down
			d =  1 + increasingPathLength(matrix, m+1, n, ipl);
		}
		if( m-1 >= 0 && matrix[m-1][n] > matrix[m][n]){//up
			u =  1 + increasingPathLength(matrix, m-1, n, ipl);
		}
		ipl[m][n] = Math.max(l, Math.max(r, Math.max(u, d)));
		return ipl[m][n];
	}


	public static void main(String[] args) {
		int[][] matrix01 = new int[3][3];
		matrix01[0][0] = 3;
		matrix01[0][1] = 4;
		matrix01[0][2] = 5;
		
		matrix01[1][0] = 3;
		matrix01[1][1] = 2;
		matrix01[1][2] = 6;
		
		matrix01[2][0] = 2;
		matrix01[2][1] = 2;
		matrix01[2][2] = 1;
		System.out.println("longestIncreasingPath(matrix01) : " + longestIncreasingPath(matrix01));
		
		
		int[][] matrix02 = new int[3][3];
		matrix02[0][0] = 9;
		matrix02[0][1] = 9;
		matrix02[0][2] = 4;
		
		matrix02[1][0] = 6;
		matrix02[1][1] = 6;
		matrix02[1][2] = 8;
		
		matrix02[2][0] = 2;
		matrix02[2][1] = 1;
		matrix02[2][2] = 1;
		System.out.println("longestIncreasingPath(matrix02) : " + longestIncreasingPath(matrix02));
		


		
		
	}

}
