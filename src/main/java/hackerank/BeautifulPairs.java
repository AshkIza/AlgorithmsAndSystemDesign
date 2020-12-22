package hackerank;

import java.util.Arrays;

/* https://www.hackerrank.com/challenges/beautiful-pairs/problem
 * You are given two arrays,  and , both containing  integers.

A pair of indices  is beautiful if the  element of array  is equal to the  element of array .
 In other words, pair  is beautiful if and only if . 
A set containing beautiful pairs is called a beautiful set.
A beautiful set is called pairwise disjoint if for every pair  
belonging to the set there is no repetition of either  or  values. For instance, if  and  the beautiful set  is not pairwise disjoint as there is a repetition of , that is .

Your task is to change exactly  element in  so that the size of the pairwise disjoint beautiful set is maximum.
 * */
public class BeautifulPairs {
	
	  static int beautifulPairs(int[] A, int[] B) {
	        Arrays.sort(A);
	        Arrays.sort(B);
	        int ia = 0;
	        int ib = 0;
	        int N = A.length;
	        int paircount = 0;
	        while(ia < N && ib < N){
	            if(A[ia] == B[ib]){
	                ia++;
	                ib++;
	                paircount++;
	            }else if(A[ia] < B[ib]){
	                ia++;
	            }else{
	               ib++; 
	            }
	        }
	        return (paircount < N) ? paircount+1 : N -1;
	    }


	public static void main(String[] args) {
		System.out.println(" READ CAREFULLY EACH LINE OF THE PROBLEM, EVERY PIECE IS A CLUE! (Your task is to change 'exactly ONE' element )");
		System.out.println("beautifulPairs(new int[] {3, 5, 7, 11, 5, 8}, new int[] {5, 7, 11, 10, 5, 8}) : " + 
				beautifulPairs(new int[] {3, 5, 7, 11, 5, 8}, new int[] {5, 7, 11, 10, 5, 8}));
		System.out.println("beautifulPairs(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 3}) : " + 
				beautifulPairs(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 3}));
		System.out.println("beautifulPairs(new int[] {1}, new int[] {1}) : " + 
				beautifulPairs(new int[] {1}, new int[] {1}));
	}

}
