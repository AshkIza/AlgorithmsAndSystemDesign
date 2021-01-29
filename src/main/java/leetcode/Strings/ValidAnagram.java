package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/* Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up

What if the inputs contain unicode characters? How would you adapt your solution to such case?

Answer

Use a hash table instead of a fixed size counter.
 Imagine allocating a large size array to fit the entire range of unicode characters, 
 which could go up to more than 1 million. A hash table is a more generic solution 
 and could adapt to any range of characters.
 * 
 * */
public class ValidAnagram {
	
	public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        if(s.length()==0) return true;
        int[] footprint = new int[26];//26 characters in english alphabet
        for(char ch : s.toCharArray()){
            int i = (int)(ch - 'a');
            footprint[i]++;
        }
        for(char ch : t.toCharArray()){
            int i = (int)(ch - 'a');
            if(footprint[i] == 0){
                return false;
            }else{
               footprint[i]--; 
            }
        }
        return true;
    }

	
	  public static boolean isAnagramUnicode(String s, String t) {
	        if(s.length() != t.length()){
	            return false;
	        }
	        Map<Character, Integer> anag = new HashMap<>();
	        for(char ch : s.toCharArray()){
	            if(anag.containsKey(ch)){
	                int count = anag.get(ch);
	                anag.put(ch, ++count);//first increment count then put it in map --> ++count
	            }else{
	                anag.put(ch, 1);
	            }
	        }
	        
	        for(char ch : t.toCharArray()){
	            if(!anag.containsKey(ch)){
	                return false;
	            }else{
	                int count = anag.get(ch);
	                if(count == 1){
	                    anag.remove(ch);
	                }else{
	                    count--;
	                    anag.put(ch, count);
	                }
	            }
	        }
	        
	        if(anag.isEmpty()){
	            return true;
	        }
	        return false;
	    }
	  
	  
	public static void main(String[] args) {
		
		System.out.println(" isAnagram('anagram', 'nagaram') : " + isAnagram("anagram", "nagaram"));
		System.out.println(" isAnagram('rat', 'car') : " + isAnagram("rat", "car"));
		

	}
	
	/*  size() versus length() versus length
	 * Collections.size() 
	 * String.length()   
	 * https://stackoverflow.com/questions/20192843/difference-between-size-and-length-methods
	 * 
	 * size() is a method specified in java.util.Collection,
	 * length is a field on any array (arrays are objects, you just don't see the class normally),
	 * length() is a method on java.lang.String, which is just a thin wrapper on a char[] anyway.
	 * 
	 * */
	
	/* HashMap containsKey() 
	 * https://www.geeksforgeeks.org/hashmap-containskey-method-in-java/
	 *  hash_map.put(10, "Geeks"); 
        hash_map.put(15, "4"); 
        hash_map.put(20, "Geeks"); 
        
        hash_map.containsKey(20)); 
	 * */
}
