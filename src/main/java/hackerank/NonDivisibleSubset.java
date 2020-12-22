package hackerank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  https://www.hackerrank.com/challenges/non-divisible-subset/problem
 * 
 * Given a set of distinct integers,
 * 	 print the size of a maximal subset of S 
 * 		where the sum of any 2 numbers in S' is not evenly divisible by k.
 *   
 *   1<n<10^5
 *   1<k<100
 *   1<S[i]<10^9
 * 
 * */
public class NonDivisibleSubset {
	
	
	  public static int nonDivisibleSubset(int k, List<Integer> s) {
	        Map<Integer, Integer> modMap = new HashMap<>();//size of K at most
	        for(Integer value : s){
	            int modVal = value % k;
	            if(modMap.containsKey(modVal)){
	                modMap.put(modVal, modMap.get(modVal) + 1);
	            }else{
	                modMap.put(modVal, 1);
	            }
	        }
	        // now counting max length
	        int maxSize = 0;
	        for(Integer key : modMap.keySet()){
	            if(key == 0 || (key == k - key)){
	                maxSize ++;
	            }else{
	               int a = modMap.getOrDefault(key, 0);
	               int b =  modMap.getOrDefault(k - key, 0);
	               maxSize += Math.max(a, b);
	               modMap.computeIfPresent(key, (k01,v01) ->  0);//avoid duplicate calculation
	               modMap.computeIfPresent(k - key, (k02,v02) ->  0);
	            }
	        }
	        return maxSize;
	    }

	public static void main(String[] args) {
		/*
		 * 4 3
		   1 7 2 4
		 * */
		List<Integer> case01 = Arrays.asList(1, 7, 2, 4);
		System.out.println("case01 : " + case01);
		System.out.println("nonDivisibleSubset(3, case01) : "  + nonDivisibleSubset(3, case01));
		
		
		/*
		 * 15 7
			278 576 496 727 410 124 338 149 209 702 282 718 771 575 436
		 * */
		List<Integer> case02 = Arrays.asList(278, 576, 496, 727, 410, 124,
				338, 149, 209, 702, 282, 718, 771, 575, 436);
		System.out.println("case02 : " + case02);
		System.out.println("nonDivisibleSubset(7, case02) : " + nonDivisibleSubset(7, case02));

	}

}
