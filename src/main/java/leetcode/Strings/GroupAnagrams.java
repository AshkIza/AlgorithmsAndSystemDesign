package leetcode.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*  Group Anagrams

	Given an array of strings, group anagrams together.
	
	Example:
	
	Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
	Output:
	[
	  ["ate","eat","tea"],
	  ["nat","tan"],
	  ["bat"]
	]
	Note:
	
	All inputs will be in lowercase.
	The order of your output does not matter.
 * 
 * */
public class GroupAnagrams {
	
	
	
    public static  List<List<String>> groupAnagrams(String[] strs) {
    	Map<String, List<String>> map = new TreeMap<>();
    	for(String s : strs){
    		String key = toPriorityQueueAndBack(s);
    		if(map.get(key) == null){
    			List<String> list = new ArrayList<>();
    			list.add(s);
    			map.put(key, list);
    		}else{
    			List<String> list = map.get(key);
    			list.add(s);
    		}
    	}
    	
    	 List<List<String>> groups = new ArrayList<List<String>>();
    	 for(String groupKey : map.keySet()){
    		 groups.add(map.get(groupKey));
    		 
    	 }
    	 return groups;
	}
	

	
	private static String toPriorityQueueAndBack(String string){
		PriorityQueue<Character> queue = new PriorityQueue<>();
		for(char ch : string.toCharArray()){
			queue.add(ch);
		 }
		 String fromSet="";
		 while(!queue.isEmpty()){
			 fromSet += String.valueOf(queue.poll());
		 }
		 return fromSet;
	}
	
	private static void pringGroupAnagrams(List<List<String>> anagrams){
		for(List<String> group : anagrams){
			String anagram = "[";
			for(String str : group){
				anagram += "" + str + ", "; 
			}
			anagram += "],";
			System.out.println(anagram);
		}
		
	}
	
	private static String toTreeSetAndBack(String string){
		Set<Character> set = new TreeSet<>();
		for(char ch : string.toCharArray()){
			 set.add(ch);
		 }
		 Iterator<Character> it = set.iterator();
		 String fromSet="";
		 while(it.hasNext()){
			 fromSet += String.valueOf(it.next());
		 }
		 return fromSet;
	}

	public static void main(String[] args) {
	 String s01 = "eat";
	 String s02 = "tea";
	 String s03 = "ate";
	 System.out.println(s01 + "--> " + toTreeSetAndBack(s01));
	 System.out.println(s02 + "--> " + toTreeSetAndBack(s02));
	 System.out.println(s03 + "--> " + toTreeSetAndBack(s03));
	 
	 String t01 = "ashkan";
	 String t02 = "naksha";
	 System.out.println(t01 + "--> " + toTreeSetAndBack(t01));
	 System.out.println(t02 + "--> " + toTreeSetAndBack(t02));
	 
	 System.out.println(t01 + "--> " + toPriorityQueueAndBack(t01));
	 System.out.println(t02 + "--> " + toPriorityQueueAndBack(t02));
	 
	 String[] first = 	new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
	 System.out.println(Arrays.toString(first));
	 List<List<String>> res = groupAnagrams(first);
	 pringGroupAnagrams(groupAnagrams(first));
	
	}

}
