package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/*
 * 	Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 * 
  solution: https://www.programcreek.com/2014/05/leetcode-unique-binary-search-trees-java/
    ( each of the numbers 1..n can be the root )
 * 
 * */
public class UniqueBinarySearchTrees {
	
    Map<Integer, Integer> subSol = new HashMap();
    
    public int numTrees(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        if(subSol.containsKey(n)){
            return  subSol.get(n);//Integer.valueOf(n)
        }
        int count = 0 ;
        for(int index = 1; index<=n; index++){
            int left = index - 1;
            int right = n - index;
            count += numTrees(left) * numTrees(right);
        }
        subSol.put(n, count);
        return count;
    }
	public static void main(String[] args) {
		UniqueBinarySearchTrees uniqueBST = new UniqueBinarySearchTrees();
		
		System.out.println( "uniqueBST.numTrees(3) : " + uniqueBST.numTrees(3));
		
		System.out.println( "uniqueBST.numTrees(4) : " + uniqueBST.numTrees(4));
		
		System.out.println( "uniqueBST.numTrees(5) : " + uniqueBST.numTrees(5));
	}

}
