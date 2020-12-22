package leetcode.DP;

/*  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
 The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28


optimization steps:
 1 - use DP (memCache) to save already calculated subProblems
 2 - use a less time-extensive memCache --> int[][] instead of Map<int[], Integer>
 3 - avoid going to un-necessary recursive calls ( if i+1 < m )
 * 
 * */
public class UniquePaths {
	
    public int uniquePaths(int m, int n) {
        int[][] memCache = new int[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                memCache[i][j] = -1;
            }
        }
        return numPaths(m, n , 0, 0, memCache);
    }
    private int numPaths(int m, int n, int i, int j, int[][] memCache) {
        if(i>=m || j>=n){
            return 0;
        }
        if(i == m-1 && j == n-1){
            return 1;
        }
        int[] key = new int[] {i,j};
        if(memCache[i][j] != -1){
            return memCache[i][j];
        }else{
            int a = 0;
            int b = 0;
            if( i+1 < m){
                a = numPaths(m, n, i+1, j, memCache);
            }
            if(j+1 < n){
                b = numPaths(m, n, i, j+1, memCache);
            }
            int p = a + b;
            memCache[i][j]=p;
            return p;
        }
    }

	public static void main(String[] args) {
		UniquePaths uniquePaths = new UniquePaths();
		
		System.out.println("uniquePaths.uniquePaths(3,2) : " + uniquePaths.uniquePaths(3,2));
		
		System.out.println("uniquePaths.uniquePaths(7,3) : " + uniquePaths.uniquePaths(7,3));

		System.out.println("\noptimization steps:");
		System.out.println(" 1 - use DP (memCache) to save already calculated subProblems");
		System.out.println(" 2 - use a less time-extensive memCache --> int[][] instead of Map<int[], Integer>");
		System.out.println(" 3 - avoid going to un-necessary recursive calls ( if i+1 < m )");




	}

}
