package leetcode.sortAndSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*  Top K Frequent Elements
	Given a non-empty array of integers, return the k most frequent elements.
	
	Example 1:
	
	Input: nums = [1,1,1,2,2,3], k = 2
	Output: [1,2]
	Example 2:
	
	Input: nums = [1], k = 1
	Output: [1]
	Note:
	
	You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
	Your algorithm's time complexity must be better than O(n log n), where n is the array's size
 * 
 * */
public class TopKFrequentElements {
	
	
    public static List<Integer> topKFrequent(int[] nums, int k) {
    	Map<Integer, Integer> numFreq = new HashMap<>();
    	List<Integer> frequencies = new LinkedList<>();
    	
    	for(int number : nums){
    		if(!numFreq.containsKey(number)){
    			numFreq.put(number, 1);
    		}else{
    			numFreq.put(number, numFreq.get(number) + 1);
    		}
    	}
    	for(Integer key : numFreq.keySet()){
    		frequencies.add(numFreq.get(key));
    	}
    	Collections.sort(frequencies, Collections.reverseOrder());
    	LinkedList<Integer> topK =  (LinkedList<Integer>) frequencies.subList(0, k);
   
    	while(!topK.isEmpty()){
    		
    		for(Integer key : numFreq.keySet()){
        		numFreq.get(key);
        	}
    		
    	}
    	return topK;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
